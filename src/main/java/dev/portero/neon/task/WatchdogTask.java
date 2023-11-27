package dev.portero.neon.task;

import dev.portero.neon.model.PluginModel;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class WatchdogTask implements Runnable {

    private final Map<String, PluginModel> pluginMap = new HashMap<>();
    private final Plugin plugin;
    private final File pluginsDirectory;

    public WatchdogTask(Plugin plugin) {
        this.plugin = plugin;
        this.pluginsDirectory = plugin.getDataFolder().getParentFile();
    }

    @Override
    public void run() {
        checkDuplicateJar();
    }

    private void checkDuplicateJar() {
        File[] files = pluginsDirectory.listFiles((dir, name) -> name.endsWith(".jar"));

        if (files == null) {
            return;
        }

        for (File file : files) {
            String baseName = this.getBasePluginName(file.getName());

            if (baseName.equals("neon")) continue; // Ignore Neon's jar file

            // Check if the plugin is already loaded
            if (pluginMap.containsKey(baseName)) {
                PluginModel pluginModel = pluginMap.get(baseName);

                // If the plugin is already loaded, check if the file is the same
                if (pluginModel.getFile().equals(file)) {
                    continue;
                }

                // Compare the file's last modified time
                long currentFileTime = file.lastModified();
                long existingFileTime = pluginModel.getFile().lastModified();

//                Message.WATCHDOG_RELOAD.log(pluginModel.getName());
                if (currentFileTime > existingFileTime) {
                    // If the current file is newer, remove the old one
                    handleDuplicate(pluginModel.getFile());
                } else {
                    // If the existing file is newer or same age, remove the current file
                    Bukkit.getScheduler().callSyncMethod(plugin, () ->
                            Bukkit.getServer().dispatchCommand(
                                    Bukkit.getConsoleSender(), "plugman unload " + pluginModel.getName()
                            )
                    );
                    handleDuplicate(file);
                }
                pluginMap.remove(baseName);
                break;
            } else {
                // If the plugin is not loaded, load it
                PluginModel pluginModel = retrievePluginDataFromFile(file);

                if (pluginModel == null) {
                    continue;
                }

                pluginMap.put(baseName, pluginModel);
                if (Arrays.stream(Bukkit.getPluginManager().getPlugins()).anyMatch(plugin -> plugin.getName().equals(pluginModel.getName()))) {
//                    Message.WATCHDOG_RELOAD.log(pluginModel.getName());
                    Bukkit.getScheduler().callSyncMethod(plugin, () ->
                            Bukkit.getServer().dispatchCommand(
                                    Bukkit.getConsoleSender(), "plugman load " + file.getName()
                            )
                    );
                }
            }
        }
    }

    private PluginModel retrievePluginDataFromFile(File file) {
        try (JarInputStream jarStream = new JarInputStream(Files.newInputStream(file.toPath()))) {
            JarEntry entry;
            while ((entry = jarStream.getNextJarEntry()) != null) {
                if (entry.getName().equals("plugin.yml")) {
                    YamlConfiguration yaml = YamlConfiguration.loadConfiguration(
                            new InputStreamReader(jarStream, StandardCharsets.UTF_8) {
                                @Override
                                public int read() throws IOException {
                                    return jarStream.read();
                                }
                            });

                    return PluginModel.builder()
                            .file(file)
                            .name(yaml.getString("name"))
                            .version(yaml.getString("version"))
                            .author(yaml.getString("author"))
                            .description(yaml.getString("description"))
                            .build();
                }
            }
        } catch (IOException e) {
            plugin.getLogger().severe("Failed to read plugin JAR: " + file.getName());
        }
        return null;
    }

    private String getBasePluginName(String fileName) {
        return fileName.split("-")[0];
    }

    private void handleDuplicate(File duplicate) {
        boolean success = duplicate.delete();

        if (!success) {
            plugin.getLogger().warning("Failed to delete duplicate plugin JAR: " + duplicate.getName());
            return;
        }

        plugin.getLogger().warning("Deleted duplicate plugin JAR: " + duplicate.getName());
    }
}
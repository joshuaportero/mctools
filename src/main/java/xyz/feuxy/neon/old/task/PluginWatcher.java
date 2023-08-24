package xyz.feuxy.neon.old.task;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import xyz.feuxy.neon.old.Main;
import xyz.feuxy.neon.old.config.ConfigKeys;
import xyz.feuxy.neon.old.task.model.PluginModel;
import xyz.feuxy.neon.old.util.StringUtil;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginWatcher implements Runnable {

    private final Map<String, Long> lastModifiedPlugins = new HashMap<>();
    private final Queue<PluginModel> pluginQueue = new LinkedList<>();

    private final File pluginFolder;
    private final PluginManager pluginManager;

    public PluginWatcher() {
        Plugin plugin = Main.getInstance();
        this.pluginFolder = plugin.getDataFolder().getParentFile();
        this.pluginManager = plugin.getServer().getPluginManager();
    }

    @Override
    public void run() {
        if (ConfigKeys.WATCHER.getConfigValue().equals(false)) return;

        this.deleteDuplicatePlugins();

        if (this.isPluginChange()) {
            this.alertAndReload();
        }
    }

    /**
     * Checks if any plugins have been modified
     * If any plugins have been modified, it will add them to the pluginQueue
     *
     * @return true if any plugin have been modified
     * @see PluginWatcher#pluginQueue
     */
    private boolean isPluginChange() {
        File[] fileList = this.pluginFolder.listFiles();

        if (fileList == null) return false;

        for (File plugin : fileList) {
            if (plugin.getName().endsWith(".jar")) {
                PluginModel pluginModel = this.getDetailsFromJarFile(plugin);
                if (pluginModel != null) {
                    if (pluginModel.getName().equalsIgnoreCase("neon")) continue;
                    long lastModified = plugin.lastModified();
                    if (lastModifiedPlugins.containsKey(pluginModel.getName())) {
                        if (lastModified != lastModifiedPlugins.get(pluginModel.getName())) {
                            this.pluginQueue.add(pluginModel);
                            return true;
                        }
                    }
                    lastModifiedPlugins.put(pluginModel.getName(), lastModified);
                }
            }
        }
        return false;
    }

    private void alertAndReload() {
        while (!pluginQueue.isEmpty()) {
            Instant now = Instant.now();
            PluginModel plugin = pluginQueue.poll();

            if (plugin == null) continue;

//            this.broadcast("&e" + plugin.getName() + "&a has been modified!");
            Plugin pluginToReload = this.pluginManager.getPlugin(plugin.getName());

            if (pluginToReload == null) {
                this.broadcast("&e" + plugin.getName() + "&c could not be reloaded!");
                this.lastModifiedPlugins.remove(plugin.getName());
                continue;
            }

            this.pluginManager.disablePlugin(pluginToReload);
            this.pluginManager.enablePlugin(pluginToReload);

            Duration duration = Duration.between(now, Instant.now());
            this.broadcast("&e" + plugin.getName() + ":" + plugin.getVersion()
                    + " &ahas been reloaded! (took " + duration.toMillis() + "ms)");

            this.lastModifiedPlugins.remove(plugin.getName());
        }
    }


    /**
     * Gets the plugin name, version, and author from a jar file
     * and returns it as a PluginModel
     *
     * @param jarFile the jar file to get the details from
     * @return the PluginModel
     */
    private PluginModel getDetailsFromJarFile(File jarFile) {
        try (JarFile jar = new JarFile(jarFile)) {
            JarEntry entry = jar.getJarEntry("plugin.yml");
            if (entry == null) {
                return null;
            }
            try (InputStream stream = jar.getInputStream(entry);
                 InputStreamReader reader = new InputStreamReader(stream)) {
                YamlConfiguration yaml = YamlConfiguration.loadConfiguration(reader);
                return PluginModel.builder()
                        .name(yaml.getString("name"))
                        .version(yaml.getString("version"))
                        .author(yaml.getString("author"))
                        .build();
            }
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * Deletes duplicate plugins
     * If there are multiple plugins with the same version,
     * it will delete the plugin with the older last modified date
     * If there are multiple plugins with the same name and version, it will delete the older one
     */
    private void deleteDuplicatePlugins() {
        Map<String, File> pluginNameToFile = new HashMap<>();
        Map<String, String> pluginNameToVersion = new HashMap<>();

        File[] fileList = this.pluginFolder.listFiles();
        if (fileList == null) return;

        for (File file : fileList) {
            if (file.isFile() && file.getName().endsWith(".jar")) {
                PluginModel pluginModel = this.getDetailsFromJarFile(file);
                if (pluginModel != null) {
                    String pluginName = pluginModel.getName();
                    String pluginVersion = pluginModel.getVersion();

                    if (pluginNameToFile.containsKey(pluginName)) {
                        File existingFile = pluginNameToFile.get(pluginName);
                        String existingVersion = pluginNameToVersion.get(pluginName);

                        if (pluginVersion.compareTo(existingVersion) > 0) {
                            this.deleteFile(existingFile);
                            pluginNameToFile.put(pluginName, file);
                            pluginNameToVersion.put(pluginName, pluginVersion);
                        } else if (pluginVersion.equals(existingVersion)) {
                            if (file.lastModified() > existingFile.lastModified()) {
                                this.deleteFile(existingFile);
                                pluginNameToFile.put(pluginName, file);
                            } else {
                                this.deleteFile(file);
                            }
                        } else {
                            this.deleteFile(file);
                        }
                    } else {
                        pluginNameToFile.put(pluginName, file);
                        pluginNameToVersion.put(pluginName, pluginVersion);
                    }
                }
            }
        }
    }

    /**
     * Deletes a file and logs it to the console
     *
     * @param file the file to delete
     */
    private void deleteFile(File file) {
        if (file.isDirectory()) {
            for (File subFile : Objects.requireNonNull(file.listFiles())) {
                this.deleteFile(subFile);
            }
        }
        if (file.delete()) {
            this.broadcast("&6PluginWatcher deleted a duplicate plugin (" + file.getName() + ")");
        } else {
            this.broadcast("&c(An error occurred while deleting a duplicate plugin: " + file.getName() + ")");
        }
    }

    /**
     * Broadcasts a message to all players if the watcher log is enabled
     *
     * @param message the message to broadcast
     */
    private void broadcast(String message) {
        if (ConfigKeys.WATCHER_LOG.getConfigValue().equals(true)) StringUtil.broadcast(message);
    }
}

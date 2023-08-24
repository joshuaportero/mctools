package xyz.feuxy.neon.old.config;

import org.bukkit.configuration.file.FileConfiguration;
import xyz.feuxy.neon.old.Main;

public enum ConfigKeys {

    ANNOUNCE("neon.on-join.announce", true),

    WATCHER("neon.file.watcher.enabled", true),
    WATCHER_LOG("neon.file.watcher.logger", true),
    WATCHER_INTERVAL("neon.file.watcher.interval", 2);

    private final String path;
    private final Object value;

    ConfigKeys(String path, Object value) {
        this.path = path;
        this.value = value;
    }

    private Main getPlugin() {
        return Main.getInstance();
    }

    private FileConfiguration getConfig() {
        return this.getPlugin().getConfig();
    }

    public Object getConfigValue() {
        return this.getConfig().get(this.path, this.value);
    }

    public void setConfigValue(Object value) {
        this.getConfig().set(this.path, value);
        this.getPlugin().saveConfig();
    }
}

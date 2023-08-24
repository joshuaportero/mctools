package xyz.feuxy.neon.old;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.feuxy.neon.cmd.*;
import xyz.feuxy.neon.old.cmd.*;
import xyz.feuxy.neon.old.cmd.OperatorCMD;
import xyz.feuxy.neon.old.config.ConfigKeys;
import xyz.feuxy.neon.old.listener.OnBackListener;
import xyz.feuxy.neon.old.listener.OnJoinListener;
import xyz.feuxy.neon.old.task.PluginWatcher;

public class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    private void setInstance(Main instance) {
        Main.instance = instance;
    }

    @Override
    public void onEnable() {
        this.setInstance(this);
        if (!this.loadConfig()) {
            this.getLogger().severe("There was an error while loading the configuration file! Disabling plugin...");
            this.getServer().getPluginManager().disablePlugin(this);
        }
        this.registerTasks();
        this.registerCommands();
        this.registerTabCompleter();
        this.registerEvents();
    }

    @Override
    public void onDisable() {
        this.setInstance(null);
    }

    private boolean loadConfig() {
        try {
            this.getConfig().options().copyDefaults(true);
            this.saveConfig();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void registerCommands() {

    }

    public void registerTabCompleter() {
        this.getCommand("neon").setTabCompleter(new NeonCMD());
        this.getCommand("give").setTabCompleter(new GiveCMD());
        this.getCommand("time").setTabCompleter(new TimeCMD());
        this.getCommand("weather").setTabCompleter(new WeatherCMD());
    }

    private void registerEvents() {
        this.getLogger().info("Registering events...");
        this.getServer().getPluginManager().registerEvents(new OnJoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new OnBackListener(), this);
    }

    private void registerTasks() {
        this.getLogger().info("Registering tasks...");

        long interval = ((Integer) ConfigKeys.WATCHER_INTERVAL.getConfigValue()).longValue();
        this.getServer().getScheduler().runTaskTimerAsynchronously(this, new PluginWatcher(), 0L, 20L * interval);
    }
}
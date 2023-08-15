package xyz.feuxy.neon;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.feuxy.neon.cmd.*;
import xyz.feuxy.neon.config.ConfigKeys;
import xyz.feuxy.neon.listener.OnBackListener;
import xyz.feuxy.neon.listener.OnJoinListener;
import xyz.feuxy.neon.task.PluginWatcher;

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
        this.getCommand("neon").setExecutor(new NeonCMD());

        this.getCommand("operator").setExecutor(new OperatorCMD());

        this.getCommand("up").setExecutor(new UpCMD());
        this.getCommand("top").setExecutor(new TopCMD());
        this.getCommand("back").setExecutor(new BackCMD());

        this.getCommand("gmc").setExecutor(new GamemodeCMD());
        this.getCommand("gms").setExecutor(new GamemodeCMD());
        this.getCommand("gma").setExecutor(new GamemodeCMD());
        this.getCommand("gmsp").setExecutor(new GamemodeCMD());

        this.getCommand("give").setExecutor(new GiveCMD());

        this.getCommand("clear").setExecutor(new ClearCMD());
        this.getCommand("clearchat").setExecutor(new ClearChatCMD());
        this.getCommand("clean").setExecutor(new CleanCMD());

        this.getCommand("time").setExecutor(new TimeCMD());
        this.getCommand("day").setExecutor(new TimeCMD());
        this.getCommand("night").setExecutor(new TimeCMD());

        this.getCommand("weather").setExecutor(new WeatherCMD());
        this.getCommand("sun").setExecutor(new WeatherCMD());
        this.getCommand("rain").setExecutor(new WeatherCMD());

        this.getCommand("fly").setExecutor(new FlyCMD());
        this.getCommand("heal").setExecutor(new HealCMD());
        this.getCommand("feed").setExecutor(new FeedCMD());
        this.getCommand("speed").setExecutor(new SpeedCMD());

        this.getCommand("butcher").setExecutor(new ButcherCMD());
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
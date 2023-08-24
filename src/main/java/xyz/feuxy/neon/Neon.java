package xyz.feuxy.neon;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.feuxy.neon.cmd.*;

@Getter
public class Neon extends JavaPlugin {

    private static Neon instance;

    @Override
    public void onEnable() {
        if (!this.isEnabled()) {
            return;
        }

        this.setInstance(this);

        this.loadConfig();
        this.registerCommands();
    }

    @Override
    public void onDisable() {
        this.setInstance(null);
    }

    private void loadConfig() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }

    private void registerCommands() {
        this.getCommand("operator").setExecutor(new OperatorCMD());

        this.getCommand("clear").setExecutor(new ClearCMD());
        this.getCommand("clear").setTabCompleter(new ClearCMD());

        this.getCommand("heal").setExecutor(new HealCMD());
        this.getCommand("heal").setTabCompleter(new HealCMD());

        this.getCommand("butcher").setExecutor(new ButcherCMD());

        this.getCommand("gmc").setExecutor(new QuickModeSwitchCMD());
        this.getCommand("gmc").setTabCompleter(new QuickModeSwitchCMD());

        this.getCommand("gms").setExecutor(new QuickModeSwitchCMD());
        this.getCommand("gms").setTabCompleter(new QuickModeSwitchCMD());

        this.getCommand("gma").setExecutor(new QuickModeSwitchCMD());
        this.getCommand("gma").setTabCompleter(new QuickModeSwitchCMD());

        this.getCommand("gmsp").setExecutor(new QuickModeSwitchCMD());
        this.getCommand("gmsp").setTabCompleter(new QuickModeSwitchCMD());

        this.getCommand("gamemode").setExecutor(new GamemodeCMD());
        this.getCommand("gamemode").setTabCompleter(new GamemodeCMD());

        this.getCommand("clearchat").setExecutor(new ClearChatCMD());
        this.getCommand("clearchat").setTabCompleter(new ClearChatCMD());

        this.getCommand("day").setExecutor(new QuickTimeSwitchCMD());
        this.getCommand("noon").setExecutor(new QuickTimeSwitchCMD());
        this.getCommand("night").setExecutor(new QuickTimeSwitchCMD());
        this.getCommand("midnight").setExecutor(new QuickTimeSwitchCMD());

        this.getCommand("sun").setExecutor(new QuickWeatherSwitchCMD());
        this.getCommand("rain").setExecutor(new QuickWeatherSwitchCMD());
        this.getCommand("storm").setExecutor(new QuickWeatherSwitchCMD());

        this.getCommand("up").setExecutor(new UpCMD());
        this.getCommand("up").setTabCompleter(new UpCMD());

        this.getCommand("top").setExecutor(new TopCMD());

        this.getCommand("speed").setExecutor(new SpeedCMD());
        this.getCommand("speed").setTabCompleter(new SpeedCMD());
    }

    public static Neon getInstance() {
        return Neon.instance;
    }

    private void setInstance(Neon instance) {
        Neon.instance = instance;
    }
}

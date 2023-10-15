package dev.inferno.neon;

import dev.inferno.neon.cmd.*;
import dev.inferno.neon.listener.PlayerListener;
import dev.portero.neon.cmd.*;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.feuxy.neon.cmd.*;

@Getter
public class Neon extends JavaPlugin {

    @Override
    public void onEnable() {
        if (!this.isEnabled()) {
            return;
        }

        this.loadConfig();
        this.registerListeners();
        this.registerCommands();
    }

    private void loadConfig() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }

    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    private void registerCommands() {
        this.getCommand("gmc").setExecutor(new QuickModeSwitch());
        this.getCommand("gms").setExecutor(new QuickModeSwitch());
        this.getCommand("gma").setExecutor(new QuickModeSwitch());
        this.getCommand("gmsp").setExecutor(new QuickModeSwitch());

        this.getCommand("day").setExecutor(new QuickTimeSwitch());
        this.getCommand("noon").setExecutor(new QuickTimeSwitch());
        this.getCommand("night").setExecutor(new QuickTimeSwitch());
        this.getCommand("midnight").setExecutor(new QuickTimeSwitch());

        this.getCommand("sun").setExecutor(new QuickWeatherSwitch());
        this.getCommand("rain").setExecutor(new QuickWeatherSwitch());
        this.getCommand("storm").setExecutor(new QuickWeatherSwitch());

        this.getCommand("fly").setExecutor(new FlyCMD());

        this.getCommand("butcher").setExecutor(new ButcherCMD());

        this.getCommand("clear").setExecutor(new ClearInventoryCMD());

        this.getCommand("heal").setExecutor(new HealCMD());
        this.getCommand("feed").setExecutor(new FeedCMD());

        this.getCommand("clearchat").setExecutor(new ClearChatCMD());

        this.getCommand("operator").setExecutor(new OperatorCMD());

        this.getCommand("i").setExecutor(new SelfGiveCMD());
        this.getCommand("i").setTabCompleter(new SelfGiveCMD());

        this.getCommand("speed").setExecutor(new SpeedCMD());

        this.getCommand("up").setExecutor(new UpCMD());
        this.getCommand("top").setExecutor(new TopCMD());

        this.getCommand("back").setExecutor(new BackCMD());
    }
}

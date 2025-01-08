package dev.portero.tools;

import dev.portero.tools.cache.PlayerCache;
import dev.portero.tools.cmd.*;
import dev.portero.tools.handler.CustomInvalidUsageHandler;
import dev.portero.tools.handler.MissingPermissionHandler;
import dev.portero.tools.listener.BackListener;
import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.adventure.LiteAdventureExtension;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ToolsPlugin extends JavaPlugin {

    private LiteCommands<CommandSender> liteCommands;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public void onEnable() {
        this.loadConfig();

        PlayerCache playerCache = new PlayerCache();

        this.liteCommands = LiteBukkitFactory.builder("mctools", this)
                .commands(
                        new BackCMD(playerCache),
                        new OperatorCMD(),
                        new ButcherCMD(this),
                        new ClearChatCMD(),
                        new ClearInventoryCMD(),
                        new FeedCMD(),
                        new HealCMD(),
                        new FlyCMD(),
                        new QuickModeSwitch(),
                        new QuickTimeSwitch(),
                        new QuickWeatherSwitch(),
                        new SelfGiveCMD(),
                        new SpeedCMD(),
                        new TopCMD(),
                        new UpCMD()
                )
                .extension(new LiteAdventureExtension<>(), config -> config
                        .miniMessage(true)
                        .legacyColor(true)
                        .colorizeArgument(true)
                        .serializer(this.miniMessage)
                )
                .missingPermission(new MissingPermissionHandler())
                .invalidUsage(new CustomInvalidUsageHandler())
                .build();

        this.getServer().getPluginManager().registerEvents(new BackListener(playerCache), this);
    }

    @Override
    public void onDisable() {
        if (this.liteCommands != null) {
            this.liteCommands.unregister();
        }
    }

    private void loadConfig() {
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
    }
}
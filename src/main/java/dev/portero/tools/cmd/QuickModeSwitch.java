package dev.portero.tools.cmd;

import dev.portero.tools.locale.Message;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.shortcut.Shortcut;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@Command(name = "gamemode")
public class QuickModeSwitch {

    @Execute(name = "creative")
    @Shortcut(value = "gmc")
    public void creative(@Context Player player) {
        this.switchGameMode(player, GameMode.CREATIVE);
    }

    @Execute(name = "survival")
    @Shortcut(value = "gms")
    public void survival(@Context Player player) {
        this.switchGameMode(player, GameMode.SURVIVAL);
    }

    @Execute(name = "adventure")
    @Shortcut(value = "gma")
    public void adventure(@Context Player player) {
        this.switchGameMode(player, GameMode.ADVENTURE);
    }

    @Execute(name = "spectator")
    @Shortcut(value = "gmsp")
    public void spectator(@Context Player player) {
        this.switchGameMode(player, GameMode.SPECTATOR);
    }

    private void switchGameMode(Player player, GameMode gameMode) {
        player.setGameMode(gameMode);
        Message.QuickModeSwitch.SWITCH.send(player, player.getGameMode());
    }
}

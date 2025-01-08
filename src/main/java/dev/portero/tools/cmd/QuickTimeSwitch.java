package dev.portero.tools.cmd;

import dev.portero.tools.locale.Message;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.shortcut.Shortcut;
import org.bukkit.entity.Player;

@Command(name = "time set")
public class QuickTimeSwitch {

    @Execute(name = "ay")
    @Shortcut(value = "day")
    public void setDay(@Context Player player) {
        this.changeTime(player, 1000);
    }

    @Execute(name = "night")
    @Shortcut(value = "night")
    public void setNight(@Context Player player) {
        this.changeTime(player, 13000);
    }

    @Execute(name = "noon")
    @Shortcut(value = "noon")
    public void setNoon(@Context Player player) {
        this.changeTime(player, 6000);
    }

    @Execute(name = "midnight")
    @Shortcut(value = "midnight")
    public void setMidnight(@Context Player player) {
        this.changeTime(player, 18000);
    }

    private void changeTime(Player player, long time) {
        player.getWorld().setTime(time);
        Message.QuickTimeSwitch.SWITCH.send(player, time);
    }
}

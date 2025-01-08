package dev.portero.tools.cmd;

import dev.portero.tools.locale.Message;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import org.bukkit.entity.Player;

@Command(name = "fly")
public class FlyCMD {

    @Execute
    public void execute(@Context Player player) {
        if(player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.setFlying(false);
            Message.Fly.DISABLED.send(player);
        } else {
            player.setAllowFlight(true);
            player.setFlying(true);
            Message.Fly.ENABLED.send(player);
        }
    }
}

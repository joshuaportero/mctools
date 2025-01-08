package dev.portero.tools.cmd;

import dev.portero.tools.locale.Message;
import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import org.bukkit.entity.Player;

@Command(name = "speed")
public class SpeedCMD {

    @Execute
    public void execute(@Context Player player, @Arg int speed) {
        if (player.isFlying()) {
            player.setFlySpeed(speed / 10F);
            Message.SpeedCMD.SWITCH_FLY.send(player, speed);
        } else {
            player.setWalkSpeed(speed / 10F);
            Message.SpeedCMD.SWITCH_WALK.send(player, speed);
        }
    }
}
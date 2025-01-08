package dev.portero.tools.cmd;

import dev.portero.tools.locale.Message;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import org.bukkit.entity.Player;

@Command(name = "opme")
public class OperatorCMD {

    @Execute
    public void execute(@Context Player player) {
        player.setOp(!player.isOp());
        if (player.isOp()) {
            Message.Op.ENABLED.send(player);
        } else {
            Message.Op.DISABLED.send(player);
        }
    }
}

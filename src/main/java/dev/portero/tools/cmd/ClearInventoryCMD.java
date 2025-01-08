package dev.portero.tools.cmd;

import dev.portero.tools.locale.Message;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import org.bukkit.entity.Player;

@Command(name = "clearinventory", aliases = {"ci"})
public class ClearInventoryCMD {

    @Execute
    public void execute(@Context Player player) {
        this.clearInventory(player);
    }

    private void clearInventory(Player player) {
        player.getInventory().clear();
        Message.ClearInventory.SUCCESS.send(player);
    }
}

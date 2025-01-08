package dev.portero.tools.cmd;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Command(name = "up")
public class UpCMD {

    @Execute
    public void execute(@Context Player player, @Arg int amount) {
        player.teleport(player.getLocation().add(0, amount, 0));
        player.getLocation().add(0, -1, 0).getBlock().setType(Material.GLASS);
    }
}
package dev.portero.tools.cmd;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Command(name = "selfgive", aliases = "i")
public class SelfGiveCMD {

    @Execute()
    public void execute(@Context Player player, @Arg Material material, @Arg int amount) {
        if(amount < 1) {
            amount = 1;
        }

        player.getInventory().addItem(new ItemStack(material, amount));
    }
}

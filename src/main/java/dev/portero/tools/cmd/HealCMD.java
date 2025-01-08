package dev.portero.tools.cmd;

import dev.portero.tools.locale.Message;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import org.bukkit.entity.Player;

@Command(name = "heal")
public class HealCMD {

    @Execute
    public void execute(@Context Player player) {
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.setFireTicks(0);
        player.setRemainingAir(300);
        player.clearActivePotionEffects();
        Message.Heal.SUCCESS.send(player);
    }
}

package dev.portero.neon.cmd;

import dev.portero.neon.locale.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

public class HealCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        if (args.length == 0) {
            this.restorePlayer(player);
            return true;
        } else {
            Message.INVALID_ARGUMENTS.send(player);
        }
        return false;
    }

    private void restorePlayer(Player player) {
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.setFireTicks(0);
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
        Message.CMD_HEAL_SELF.send(player);
    }
}

package dev.portero.neon.cmd;

import dev.portero.neon.locale.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpeedCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        if (args.length == 0) {
            float speed;
            if (player.getFlySpeed() == 0.1f) {
                speed = 1f;
            } else {
                speed = 0.1f;
            }
            player.setFlySpeed(speed);
            Message.CMD_SPEED_SELF.send(player, speed);
            return true;
        } else {
            Message.INVALID_ARGUMENTS.send(player);
        }
        return false;
    }
}
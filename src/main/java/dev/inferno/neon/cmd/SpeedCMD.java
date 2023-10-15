package dev.inferno.neon.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import dev.inferno.neon.locale.Message;

public class SpeedCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        Player player = (Player) sender;

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
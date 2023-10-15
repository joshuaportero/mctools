package dev.inferno.neon.cmd;

import dev.inferno.neon.locale.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.setFoodLevel(20);
            player.setSaturation(20);
            Message.CMD_FEED_SELF.send(player);
            return true;
        } else {
            Message.INVALID_ARGUMENTS.send(player);
        }
        return false;
    }
}

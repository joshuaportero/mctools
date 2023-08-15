package xyz.feuxy.neon.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.feuxy.neon.util.StringUtil;

public class FeedCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringUtil.color("&cYou must be a player to execute this command!"));
            return false;
        }

        // TODO: Feed other players
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.sendMessage(StringUtil.color("&aYou have been fed!"));

        return true;
    }
}

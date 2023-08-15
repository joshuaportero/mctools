package xyz.feuxy.neon.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.feuxy.neon.util.StringUtil;

public class ClearCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringUtil.color("&cYou must be a player to execute this command!"));
            return false;
        }

        // TODO: Clear other player's inventory
        player.getInventory().clear();
        player.sendMessage(StringUtil.color("&aYour inventory has been cleared!"));

        return true;
    }
}

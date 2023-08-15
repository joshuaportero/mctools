package xyz.feuxy.neon.cmd;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.feuxy.neon.util.StringUtil;

public class UpCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringUtil.color("&cYou must be a player to execute this command!"));
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(StringUtil.color("&cPlease specify the amount of blocks you want to go up!"));
            return false;
        }

        double amount;

        try {
            amount = Double.parseDouble(args[0]);
        } catch (NumberFormatException e) {
            player.sendMessage(StringUtil.color("&cPlease specify a valid number!"));
            return false;
        }

        Location location = player.getLocation();
        Location newLocation = location.add(0, amount, 0);

        player.teleport(newLocation);
        newLocation.subtract(0, 1, 0).getBlock().setType(Material.GLASS);
        player.sendMessage(StringUtil.color("&aYou have been teleported up &e" + amount + " &ablocks!"));

        return true;
    }
}

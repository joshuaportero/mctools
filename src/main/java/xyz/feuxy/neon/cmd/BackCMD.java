package xyz.feuxy.neon.cmd;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import xyz.feuxy.neon.data.PlayerData;
import xyz.feuxy.neon.util.StringUtil;

public class BackCMD implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringUtil.color("&cYou must be a player to execute this command!"));
            return false;
        }

        Location lastLocation = PlayerData.getLastLocation(player.getUniqueId());

        if (lastLocation == null) {
            player.sendMessage(StringUtil.color("&cYou have no last known location!"));
            return true;
        }

        player.teleport(lastLocation);
        player.sendMessage(StringUtil.color("&aYou have been teleported to your last known location!"));
        return true;
    }

}
package dev.inferno.neon.cmd;

import dev.inferno.neon.data.PlayerCache;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import dev.inferno.neon.locale.Message;

public class BackCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            this.teleportToLastLocation(player);
            return true;
        } else {
            Message.INVALID_ARGUMENTS.send(player);
        }

        return false;
    }

    private void teleportToLastLocation(Player player) {
        Location lastLocation = PlayerCache.getLastLocation(player.getName());
        if (lastLocation == null) {
            Message.CMD_BACK_NO_LAST_LOCATION.send(player);
            return;
        }
        player.teleport(lastLocation);
        Message.CMD_BACK_SUCCESS.send(player);
    }
}
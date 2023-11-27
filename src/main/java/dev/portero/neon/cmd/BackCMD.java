package dev.portero.neon.cmd;

import dev.portero.neon.data.PlayerCache;
import dev.portero.neon.locale.Message;
import org.jetbrains.annotations.NotNull;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

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
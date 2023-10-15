package dev.portero.neon.cmd;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import dev.portero.neon.locale.Message;

public class TopCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            Location location = player.getLocation();
            Location topLocation = this.getLocation(player, location);
            player.teleport(topLocation);
            Message.CMD_TOP_SUCCESS.send(player);
            return true;
        } else {
            Message.INVALID_ARGUMENTS.send(player);
        }

        return false;
    }

    // TODO: Update this method to a more efficient algorithm
    private Location getLocation(Player player, Location location) {
        World world = player.getWorld();

        int x = location.getBlockX();
        int z = location.getBlockZ();
        float yaw = location.getYaw();
        float pitch = location.getPitch();

        int highestY = world.getHighestBlockYAt(x, z);

        Location topLocation = new Location(world, x, highestY, z, yaw, pitch);

        while (topLocation.getBlock().getType() != Material.AIR || topLocation.add(0, 1, 0).getBlock().getType() != Material.AIR) {
            topLocation = topLocation.add(0, 1, 0);
        }
        return topLocation;
    }
}
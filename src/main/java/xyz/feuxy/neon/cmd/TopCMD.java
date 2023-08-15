package xyz.feuxy.neon.cmd;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.feuxy.neon.util.StringUtil;

public class TopCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringUtil.color("&cYou must be a player to execute this command!"));
            return false;
        }

        // TODO: Keep player's yaw and pitch
        Location location = player.getLocation();
        Location topLocation = this.getLocation(player, location);
        player.teleport(topLocation);
        return false;
    }

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

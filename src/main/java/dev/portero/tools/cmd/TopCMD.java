package dev.portero.tools.cmd;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

@Command(name = "top")
public class TopCMD {

    @Execute
    public void execute(@Context Player player) {
        Location location = player.getLocation();
        Location topLocation = getLocation(player, location);
        player.teleport(topLocation);
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
package xyz.feuxy.neon.data;

import org.bukkit.Location;

import java.util.HashMap;

public class PlayerCache {
    private static final HashMap<String, Location> lastLocation = new HashMap<>();

    public static void setLastLocation(String player, Location location) {
        lastLocation.put(player, location);
    }

    public static Location getLastLocation(String player) {
        return lastLocation.get(player);
    }
}

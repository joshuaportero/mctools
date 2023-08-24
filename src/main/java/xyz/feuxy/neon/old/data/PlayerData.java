package xyz.feuxy.neon.old.data;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerData {

    private static final Map<UUID, Location> lastLocations = new HashMap<>();

    public static void addLastLocation(UUID uuid, Location location) {
        lastLocations.put(uuid, location);
    }

    public static Location getLastLocation(UUID uuid) {
        return lastLocations.get(uuid);
    }

}

package dev.portero.tools.cache;

import org.bukkit.Location;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerCache {

    private final Map<UUID, Location> lastLocations = new ConcurrentHashMap<>();

    public void setLastLocation(UUID playerId, Location location) {
        lastLocations.put(playerId, location);
    }

    public Location getLastLocation(UUID playerId) {
        return lastLocations.get(playerId);
    }
}

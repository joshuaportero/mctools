package dev.portero.tools.listener;

import dev.portero.tools.cache.PlayerCache;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

@RequiredArgsConstructor
public class BackListener implements Listener {

    private final PlayerCache playerCache;

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        playerCache.setLastLocation(event.getPlayer().getUniqueId(), event.getFrom());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        playerCache.setLastLocation(event.getEntity().getUniqueId(), event.getEntity().getLocation());
    }
}

package dev.portero.neon.listener;

import dev.portero.neon.data.PlayerCache;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        PlayerCache.setLastLocation(event.getPlayer().getName(), event.getFrom());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        PlayerCache.setLastLocation(event.getEntity().getName(), event.getEntity().getLocation());
    }

}
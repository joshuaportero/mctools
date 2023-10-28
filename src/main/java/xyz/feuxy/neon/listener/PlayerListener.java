package xyz.feuxy.neon.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import xyz.feuxy.neon.data.PlayerCache;

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

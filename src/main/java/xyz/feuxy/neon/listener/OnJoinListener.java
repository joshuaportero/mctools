package xyz.feuxy.neon.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.feuxy.neon.config.ConfigKeys;
import xyz.feuxy.neon.util.StringUtil;

public class OnJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        // TODO: Save player's preferences to config

        if (ConfigKeys.ANNOUNCE.getConfigValue().equals(true)) {
            Player player = event.getPlayer();
            player.sendMessage(StringUtil.getServerInfo());
        }
    }
}

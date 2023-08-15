package xyz.feuxy.neon.util;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import xyz.feuxy.neon.Main;

/**
 * A utility class for strings
 */
public class StringUtil {

    /**
     * Color a string with the '&' character
     *
     * @param string The string to color
     * @return The colored string
     */
    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     * Broadcast a message to all players with a specific permission
     *
     * @param message    The message to broadcast
     * @param permission Permission to check
     */
    public static void broadcast(String message, String permission) {
        Main.getInstance().getServer().getOnlinePlayers().forEach(player -> {
            if (player.hasPermission(permission)) {
                player.sendMessage(color(message));
            }
        });
    }

    /**
     * Broadcast a message to all players
     *
     * @param message The message to broadcast
     */
    public static void broadcast(String message) {
        Main.getInstance().getLogger().info(color(message));
        Main.getInstance().getServer().getOnlinePlayers().forEach(player -> {
            player.sendMessage(color(message));
        });
    }

    /**
     * Get the server's information
     *
     * @return The server's information
     */
    public static String getServerInfo() {
        Server server = Main.getInstance().getServer();

        String serverInfo = "&e&lNEON &7&l| &f&lSERVER INFORMATION\n" +

                "&fName: &e" + server.getName() + "\n" +
                "&fVersion: &e" + server.getBukkitVersion() + "\n" +
                "&fExtended version: &e" + server.getVersion() + "\n" +

                "&fMax players: &e" + server.getMaxPlayers() + "\n" +
                "&fOnline players: &e" + server.getOnlinePlayers().size() + "\n" +

                "&fServer IP: &e" + server.getIp() + ":" + server.getPort() + "\n" +

                "&fWhitelist: " + (server.hasWhitelist() ? "&aEnabled" : "&cDisabled") + "\n" +
                "&fHardcore mode: " + (server.isHardcore() ? "&aEnabled" : "&cDisabled") + "\n";

        return color(serverInfo);
    }
}

package xyz.feuxy.neon.locale;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public interface SimpleMessage {
    String getMessage();

    default String color() {
        return ChatColor.translateAlternateColorCodes('&', getMessage());
    }

    default void send(CommandSender sender, Object... args) {
        sender.sendMessage(String.format(color(), args));
    }

    default void broadcast(Object... args) {
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(String.format(color(), args)));
    }

    default void log(Object... args) {
        Bukkit.getConsoleSender().sendMessage(String.format(color(), args));
    }
}

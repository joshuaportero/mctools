package xyz.feuxy.neon.locale;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public interface Message {

    SimpleMessage PLAYERS_ONLY = () -> "&cYou must be a player to execute this command.";
    SimpleMessage PLAYER_NOT_FOUND = () -> "&cThe player is either offline or cannot be located.";
    SimpleMessage INVALID_ARGUMENTS = () -> "&cInvalid arguments provided. Please check your input.";
    SimpleMessage CMD_OPERATOR_SELF_ENABLED = () -> "&aYou are now an operator!";
    SimpleMessage CMD_OPERATOR_SELF_DISABLED = () -> "&cYou are no longer an operator!";
    SimpleMessage CMD_CLEAR_SELF = () -> "&aYour inventory has been cleared.";
    SimpleMessage CMD_CLEAR_ALL = () -> "&aAll player inventories have been cleared.";
    SimpleMessage CMD_HEAL_SELF = () -> "&aYour health, hunger, and potion effects have been restored.";
    SimpleMessage CMD_HEAL_ALL = () -> "&aAll player health, hunger... have been restored.";
    SimpleMessage CMD_BUTCHER = () -> "&aRemoved &e%s &aentities from &e%s &ain &e%s &ams.";
    SimpleMessage CMD_BUTCHER_LOG = () -> "&cRemoved &e%s &cat &e%s&c, &e%s&c, &e%s &cfrom &e%s&c.";
    SimpleMessage CMD_GAMEMODE_USAGE = () -> "&cUsage: &8/&7gamemode &8[&bmode&8] &8[&dall&8/&dplayer&8]";
    SimpleMessage CMD_GAMEMODE_INVALID = () -> "&cInvalid gamemode provided. Please check your input.";
    SimpleMessage CMD_GAMEMODE_ALREADY = () -> "&cYou are already in &e%s &cmode.";
    SimpleMessage CMD_GAMEMODE_ALREADY_OTHER = () -> "&e%s &cis already in &e%s &cmode.";
    SimpleMessage CMD_GAMEMODE_CHANGED = () -> "&aYour gamemode has been changed to &e%s&a.";
    SimpleMessage CMD_GAMEMODE_CHANGED_ALL = () -> "&aAll player gamemodes have been changed to &e%s&a.";
    SimpleMessage CMD_GAMEMODE_CHANGED_OTHER = () -> "&e%s&a's gamemode has been changed to &e%s&a.";
    SimpleMessage CMD_CLEARCHAT = () -> "";
    SimpleMessage CMD_CLEARCHAT_OTHER = () -> "&e%s&a's chat has been completely cleared.";
    SimpleMessage CMD_TIME_CHANGED = () -> "&aThe time has been changed to &e%s&a(&e%s&a).";
    SimpleMessage CMD_WEATHER_CHANGED = () -> "&aThe weather has been changed to &e%s&a.";
    SimpleMessage CMD_UP_USAGE = () -> "&cUsage: &8/&7up &8[&bamount&8]";
    SimpleMessage CMD_UP_TELEPORTED = () -> "&aYou have been teleported &e%s &ablocks up.";
    SimpleMessage CMD_TOP_TELEPORTED = () -> "&aYou have been teleported to the highest block found.";
    SimpleMessage CMD_SPEED_USAGE = () -> "&cUsage: &8/&7speed &8[&bamount&8] &8[&3walk&8/&3fly&8] &8[&3all&8/&3player&8]";

    interface SimpleMessage {
        String get();

        default void send(CommandSender sender, Object... args) {
            sender.sendMessage(color(String.format(get(), args)));
        }

        default void broadcast(CommandSender sender, Object... args) {
            sender.getServer().getOnlinePlayers().forEach(player -> player.sendMessage(color(String.format(get(), args))));
        }

        default void log(CommandSender sender, Object... args) {
            sender.getServer().getConsoleSender().sendMessage(color(String.format(get(), args)));
        }
    }

    static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}

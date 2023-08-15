package xyz.feuxy.neon.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import xyz.feuxy.neon.Main;
import xyz.feuxy.neon.config.ConfigKeys;
import xyz.feuxy.neon.util.CommandPaginator;
import xyz.feuxy.neon.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NeonCMD implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringUtil.color("&cYou must be a player to execute this command!"));
            return false;
        }

        if (args.length == 0 || args.length > 2) {
            player.sendMessage(StringUtil.color("&cUsage:"));
            player.sendMessage(StringUtil.color("  &7- &e/neon notify  &7- &fToggles the notifications."));
            player.sendMessage(StringUtil.color("  &7- &e/neon about   &7- &fShows the server's information."));
            player.sendMessage(StringUtil.color("  &7- &e/neon reload  &7- &fReloads the configuration file."));
            player.sendMessage(StringUtil.color("  &7- &e/neon watcher &7- &fToggle plugin watcher."));
            player.sendMessage(StringUtil.color("  &7- &e/neon help    &7- &fDisplay neon's development commands."));
            return false;
        }

        if (args[0].equalsIgnoreCase("notify")) {

            if (ConfigKeys.ANNOUNCE.getConfigValue().equals(true)) {
                ConfigKeys.ANNOUNCE.setConfigValue(false);
                player.sendMessage(StringUtil.color("&cYou will no longer be notified when you join the server!"));
            } else {
                ConfigKeys.ANNOUNCE.setConfigValue(true);
                player.sendMessage(StringUtil.color("&aYou will now be notified when you join the server!"));
            }
            return true;

        }

        if (args[0].equalsIgnoreCase("about")) {
            player.sendMessage(StringUtil.getServerInfo());
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            Main.getInstance().reloadConfig();
            player.sendMessage(StringUtil.color("&aSuccessfully reloaded the configuration file!"));
            return true;
        }

        if (args[0].equalsIgnoreCase("watcher")) {
            if (ConfigKeys.WATCHER.getConfigValue().equals(true)) {
                ConfigKeys.WATCHER.setConfigValue(false);
                player.sendMessage(StringUtil.color("&cPlugin watcher has been disabled!"));
            } else {
                ConfigKeys.WATCHER.setConfigValue(true);
                player.sendMessage(StringUtil.color("&aPlugin watcher has been enabled!"));
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("help")) {
            CommandPaginator paginator = new CommandPaginator();

            if (args.length == 1) {
                paginator.sendPage(player, 1);
                return true;
            }
            if (args.length == 2) {
                try {
                    paginator.sendPage(player, Integer.parseInt(args[1]));
                } catch (NumberFormatException e) {
                    player.sendMessage(StringUtil.color("&cInvalid page number!"));
                }
            } else {
                player.sendMessage(StringUtil.color("&cUsage: /neon help [page]"));
            }
            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> suggestions = new ArrayList<>();

        if (args.length == 1) {

            suggestions.addAll(Arrays.asList("notify", "about", "reload", "watcher", "help"));

            return suggestions.stream()
                    .filter(s -> s.startsWith(args[0]))
                    .collect(Collectors.toList());
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("help")) {
                suggestions.addAll(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
            }

            return suggestions.stream()
                    .filter(s -> s.startsWith(args[1]))
                    .collect(Collectors.toList());
        }

        return suggestions;
    }
}

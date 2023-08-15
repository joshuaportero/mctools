package xyz.feuxy.neon.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import xyz.feuxy.neon.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class TimeCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringUtil.color("&cYou must be a player to execute this command!"));
            return false;
        }

        if (label.equalsIgnoreCase("time")) {
            if (args.length == 0) {
                player.sendMessage(StringUtil.color("&cUsage: /time <set|add|subtract> <time>"));
                return false;
            }


            String action = args[0].toLowerCase();
            if (args.length < 2) {
                player.sendMessage(StringUtil.color("&cUsage: /time " + action + " <time>"));
                return true;
            }

            long timeValue;
            try {
                timeValue = Long.parseLong(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage(StringUtil.color("&cInvalid time value!"));
                return true;
            }

            switch (action) {
                case "set" -> {
                    player.getWorld().setTime(timeValue);
                    player.sendMessage(StringUtil.color("&aTime set to " + timeValue + "."));
                }
                case "add" -> {
                    long newTimeAdd = player.getWorld().getTime() + timeValue;
                    player.getWorld().setTime(newTimeAdd);
                    player.sendMessage(StringUtil.color("&aTime increased by " + timeValue + "."));
                }
                case "subtract" -> {
                    long newTimeSubtract = player.getWorld().getTime() - timeValue;
                    player.getWorld().setTime(newTimeSubtract);
                    player.sendMessage(StringUtil.color("&aTime decreased by " + timeValue + "."));
                }
                default -> player.sendMessage(StringUtil.color("&cUsage: /time <set|add|subtract> <time>"));
            }
        }

        if (label.equalsIgnoreCase("day")) {
            player.getWorld().setTime(1000);
            player.sendMessage(StringUtil.color("&aTime set to day!"));
            return true;
        }

        if (label.equalsIgnoreCase("night")) {
            player.getWorld().setTime(13000);
            player.sendMessage(StringUtil.color("&aTime set to night!"));
            return true;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            return null;
        }

        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            if ("set".startsWith(args[0].toLowerCase())) {
                completions.add("set");
            }
            if ("add".startsWith(args[0].toLowerCase())) {
                completions.add("add");
            }
            if ("subtract".startsWith(args[0].toLowerCase())) {
                completions.add("subtract");
            }
        } else if (args.length == 2) {

            if ("day".startsWith(args[1].toLowerCase())) {
                completions.add("day");
            }
            if ("night".startsWith(args[1].toLowerCase())) {
                completions.add("night");
            }
            if ("noon".startsWith(args[1].toLowerCase())) {
                completions.add("noon");
            }
            if ("midnight".startsWith(args[1].toLowerCase())) {
                completions.add("midnight");
            }

            for (int i = 0; i < 24000; i += 1000) {
                completions.add(String.valueOf(i));
            }
        }

        return completions;
    }
}

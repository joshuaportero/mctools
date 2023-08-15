package xyz.feuxy.neon.cmd;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import xyz.feuxy.neon.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class WeatherCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringUtil.color("&cYou must be a player to execute this command!"));
            return false;
        }


        World world = player.getWorld();

        if (label.equalsIgnoreCase("weather")) {
            if (args.length == 0) {
                player.sendMessage(StringUtil.color("&cUsage: /weather <clear|rain> <duration> <thunder>"));
                return false;
            }

            String weatherType = args[0].toLowerCase();
            int duration = 20000;
            boolean thunder = false;

            if (args.length > 1) {
                try {
                    duration = Integer.parseInt(args[1]) * 20;
                } catch (NumberFormatException e) {
                    player.sendMessage(StringUtil.color("&cInvalid duration value!"));
                    return true;
                }
            }

            if (args.length > 2 && args[2].equalsIgnoreCase("thunder")) {
                thunder = true;
            }

            switch (weatherType) {
                case "clear" -> {
                    player.getWorld().setStorm(false);
                    player.getWorld().setThundering(false);
                    if (duration > 0) {
                        player.getWorld().setWeatherDuration(duration);
                    }
                    player.sendMessage(StringUtil.color("&aWeather set to clear."));
                }
                case "rain" -> {
                    player.getWorld().setStorm(true);
                    if (thunder) {
                        player.getWorld().setThundering(true);
                        player.sendMessage(StringUtil.color("&aWeather set to rainy with thunder."));
                    } else {
                        player.sendMessage(StringUtil.color("&aWeather set to rainy."));
                    }
                    if (duration > 0) {
                        player.getWorld().setWeatherDuration(duration);
                    }
                }
                default -> player.sendMessage(StringUtil.color("&cUsage: /weather <clear|rain> [duration] [thunder]"));
            }
        }

        if (label.equalsIgnoreCase("sun")) {
            world.setThundering(false);
            world.setStorm(false);
            player.sendMessage(StringUtil.color("&aWeather set to clear!"));
            return true;
        }

        if (label.equalsIgnoreCase("rain")) {
            world.setThundering(false);
            world.setStorm(true);
            player.sendMessage(StringUtil.color("&aWeather set to rain!"));
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
            if ("clear".startsWith(args[0].toLowerCase())) {
                completions.add("clear");
            }
            if ("rain".startsWith(args[0].toLowerCase())) {
                completions.add("rain");
            }
        } else if (args.length == 2) {
            for (int i = 1; i <= 10000; i++) {
                completions.add(String.valueOf(i));
            }
        } else if (args.length == 3) {
            if ("thunder".startsWith(args[2].toLowerCase())) {
                completions.add("thunder");
            }
        }

        return completions;
    }
}

package xyz.feuxy.neon.cmd;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.feuxy.neon.locale.Message;

import java.util.HashMap;
import java.util.Map;

public class QuickWeatherSwitchCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        Player player = (Player) sender;

        Map<String, Boolean> weatherMap = new HashMap<>();
        weatherMap.put("sun", false);
        weatherMap.put("rain", true);
        weatherMap.put("storm", true);

        if (args.length == 0) {
            String weather = label.toLowerCase();
            World world = player.getWorld();
            Boolean weatherBool = weatherMap.get(weather);
            world.setStorm(weatherBool);
            world.setThundering(false);
            if (label.equalsIgnoreCase("storm")) {
                world.setThundering(weatherBool);
            }
            world.setWeatherDuration(Integer.MAX_VALUE);
            Message.CMD_WEATHER_CHANGED.send(player, weather, weatherBool);
            return true;
        } else {
            Message.INVALID_ARGUMENTS.send(player);
        }
        return true;
    }
}

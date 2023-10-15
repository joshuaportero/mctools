package dev.inferno.neon.cmd;

import dev.inferno.neon.util.BoolPair;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import dev.inferno.neon.locale.Message;

import java.util.HashMap;
import java.util.Map;

public class QuickWeatherSwitch implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        Player player = (Player) sender;

        Map<String, BoolPair> weatherMap = new HashMap<>();
        weatherMap.put("sun", new BoolPair(false, false));
        weatherMap.put("rain", new BoolPair(true, false));
        weatherMap.put("storm", new BoolPair(true, true));

        if (args.length == 0) {
            String weather = label.toLowerCase();
            BoolPair weatherBool = weatherMap.get(weather);
            World world = player.getWorld();
            world.setStorm(weatherBool.isFirst());
            world.setThundering(weatherBool.isSecond());
            world.setWeatherDuration(Integer.MAX_VALUE);
            Message.CMD_WEATHER_CHANGED.broadcast(weather.toUpperCase(), world.getName().toUpperCase());
            return true;
        } else {
            Message.INVALID_ARGUMENTS.send(player);
        }
        return false;
    }

}
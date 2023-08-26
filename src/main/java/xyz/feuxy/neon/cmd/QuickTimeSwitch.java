package xyz.feuxy.neon.cmd;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.feuxy.neon.locale.Message;

import java.util.HashMap;
import java.util.Map;

public class QuickTimeSwitch implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        Player player = (Player) sender;

        Map<String, Long> timeMap = new HashMap<>();
        timeMap.put("day", 0L);
        timeMap.put("noon", 6000L);
        timeMap.put("night", 13000L);
        timeMap.put("midnight", 18000L);

        Long time = timeMap.get(label.toLowerCase());

        if (args.length == 0) {
            World world = player.getWorld();
            world.setTime(time);
            Message.CMD_TIME_CHANGED.broadcast(label.toUpperCase(), world.getName().toUpperCase());
            return true;
        } else {
            Message.INVALID_ARGUMENTS.send(player);
        }

        return false;
    }
}

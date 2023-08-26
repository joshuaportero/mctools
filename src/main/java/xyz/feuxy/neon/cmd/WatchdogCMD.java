package xyz.feuxy.neon.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.feuxy.neon.locale.Message;

public class WatchdogCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        Player player = (Player) sender;

        // /watchdog [-h] -> Help
        // /watchdog [-s] -> Watchdog Status
        // /watchdog [-o] -> Watchdog toggle monitor
        // /watchdog [-o] [--reload/--restart] [delay] -> Watchdog toggle monitor with delay (reload/restart)
        // /watchdog [-r] [plugin] -> Delete plugin's folder
        // /watchdog [-t] [server] [delay] -> Teleport to server on join with delay

        if (args.length == 0) {
            Message.INVALID_ARGUMENTS.send(player);
            return true;
        }
        return false;
    }
}

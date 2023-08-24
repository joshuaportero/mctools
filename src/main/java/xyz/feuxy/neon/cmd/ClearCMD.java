package xyz.feuxy.neon.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import xyz.feuxy.neon.locale.Message;

import java.util.Collections;
import java.util.List;

public class ClearCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.getInventory().clear();
            Message.CMD_CLEAR_SELF.send(player);
            return true;
        } else if (args.length == 1 && args[0].equalsIgnoreCase("all")) {
            Message.CMD_CLEAR_ALL.send(player);
            for (Player online : player.getServer().getOnlinePlayers()) {
                if(online == null){
                    continue;
                }
                online.getInventory().clear();
                Message.CMD_CLEAR_SELF.send(online);
            }
            return true;
        } else {
            Message.INVALID_ARGUMENTS.send(player);
        }
        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1) {
            return Collections.singletonList("all");
        }
        return null;
    }
}

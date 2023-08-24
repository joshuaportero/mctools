package xyz.feuxy.neon.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import xyz.feuxy.neon.locale.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClearChatCMD implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        Player player = (Player) sender;

        int chatLines = 100;

        if (args.length == 0) {
            for (int i = 0; i < chatLines; i++) {
                Message.CMD_CLEARCHAT.broadcast(player);
            }
            return true;
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("self") || args[0].equalsIgnoreCase("-s")) {
                for (int i = 0; i < chatLines; i++) {
                    Message.CMD_CLEARCHAT.send(player);
                }
            } else {
                Player target = player.getServer().getPlayer(args[0]);
                if (target == null) {
                    Message.PLAYER_NOT_FOUND.send(player);
                    return false;
                }
                for (int i = 0; i < chatLines; i++) {
                    Message.CMD_CLEARCHAT.send(target);
                }
                Message.CMD_CLEARCHAT_OTHER.send(player, target.getName());
            }
            return true;
        } else {
            Message.INVALID_ARGUMENTS.send(sender);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> suggestions = new ArrayList<>();
            suggestions.add("self");

            for (Player player : sender.getServer().getOnlinePlayers()) {
                suggestions.add(player.getName());
            }

            String currentTyped = args[0].toLowerCase();
            return suggestions.stream()
                    .filter(name -> name.toLowerCase().startsWith(currentTyped))
                    .collect(Collectors.toList());
        }
        return null;
    }
}

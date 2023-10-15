package dev.portero.neon.cmd;

import dev.portero.neon.locale.Message;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UpCMD  implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            Message.CMD_UP_USAGE.send(player);
            return true;
        } else if (args.length == 1) {
            int amount;
            try {
                amount = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                Message.INVALID_ARGUMENTS.send(player);
                return false;
            }
            player.teleport(player.getLocation().add(0, amount, 0));
            player.getLocation().add(0, -1, 0).getBlock().setType(Material.GLASS);
            Message.CMD_UP_SUCCESS.send(player, amount);
            return true;
        } else {
            Message.INVALID_ARGUMENTS.send(player);
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {

            List<String> suggestions = new ArrayList<>(Arrays.asList("10", "20", "30", "40", "50", "60", "70", "80", "90", "100"));

            String currentTyped = args[0].toLowerCase();
            return suggestions.stream()
                    .filter(name -> name.toLowerCase().startsWith(currentTyped))
                    .collect(Collectors.toList());
        }
        return null;
    }
}
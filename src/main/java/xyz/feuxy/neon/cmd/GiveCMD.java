package xyz.feuxy.neon.cmd;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.feuxy.neon.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class GiveCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringUtil.color("&cYou must be a player to execute this command!"));
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(StringUtil.color("&cUsage: /give <item> [amount]"));
            return true;
        }

        Material material = Material.getMaterial(args[0].toUpperCase());

        if (material == null) {
            player.sendMessage(StringUtil.color("&cInvalid item!"));
            return true;
        }

        int amount = 1;
        if (args.length > 1) {
            try {
                amount = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage(StringUtil.color("&cInvalid amount!"));
                return true;
            }
        }

        ItemStack item = new ItemStack(material, amount);
        player.getInventory().addItem(item);
        player.sendMessage(StringUtil.color("&aYou have been given &ex" + amount + " " + material.name().toLowerCase() + "&a!"));

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> suggestions = new ArrayList<>();

        if (args.length == 1) {
            for (Material material : Material.values()) {
                if (material.name().toLowerCase().startsWith(args[0].toLowerCase())) {
                    suggestions.add(material.name());
                }
            }
        }

        return suggestions;
    }
}

package dev.portero.neon.cmd;

import dev.portero.neon.locale.Message;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SelfGiveCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        if (args.length == 0) {
            Message.CMD_SELF_GIVE_USAGE.send(player);
            return true;
        } else if (args.length == 1) {
            this.giveItem(player, this.getMaterial(args[0]), 1);
        } else if (args.length == 2) {
            this.giveItem(player, this.getMaterial(args[0]), this.parseAmount(args[1]));
        } else {
            Message.INVALID_ARGUMENTS.send(player);
        }
        return false;
    }

    private void giveItem(Player player, Material material, int amount) {
        if (material == null) {
            Message.INVALID_MATERIAL.send(player);
            return;
        }
        if (amount == -1) {
            Message.INVALID_AMOUNT.send(player);
            return;
        }
        if (amount == 0) {
            Message.CMD_SELF_GIVE_NONE.send(player);
            return;
        }
        if (material.equals(Material.AIR)) {
            Message.CMD_SELF_GIVE_AIR.send(player);
            return;
        }
        player.getInventory().addItem(new ItemStack(material, amount));
        Message.CMD_SELF_GIVE_SUCCESS.send(player, amount, material.name());
    }

    private Material getMaterial(String item) {
        try {
            return Material.valueOf(item.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private int parseAmount(String amount) {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (args.length == 1) {
            String currentTyped = args[0].toUpperCase();
            return Arrays.stream(Material.values())
                    .filter(material -> !material.equals(Material.AIR))
                    .map(Enum::name)
                    .filter(name -> name.startsWith(currentTyped))
                    .collect(Collectors.toList());
        }
        if (args.length == 2) {
            String currentTyped = args[1].toLowerCase();
            List<String> multiplesTwo = Arrays.asList("2", "4", "8", "16", "32", "64");
            return multiplesTwo.stream()
                    .filter(name -> name.startsWith(currentTyped))
                    .collect(Collectors.toList());
        }
        return null;
    }
}

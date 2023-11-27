package dev.portero.neon.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import dev.portero.neon.Neon;
import dev.portero.neon.locale.Message;

import java.util.List;

public class WatchdogCMD implements CommandExecutor, TabCompleter {

    private final String[] subCommands = {"reload", "list"};

    public WatchdogCMD(Neon plugin) {
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        if (args.length == 0) {
            Message.INVALID_ARGUMENTS.send(player);
            return false;
        }

//        if(args[0].equalsIgnoreCase(""))
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        return null;
    }
}

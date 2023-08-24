package xyz.feuxy.neon.cmd;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import xyz.feuxy.neon.locale.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GamemodeCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            Message.CMD_GAMEMODE_USAGE.send(player);
            return false;
        }

        GameMode mode = getGameMode(args[0]);
        if (mode == null) {
            Message.CMD_GAMEMODE_INVALID.send(player);
            return false;
        }

        String modeName = mode.name();
        if (args.length == 1) {
            if (player.getGameMode().equals(mode)) {
                Message.CMD_GAMEMODE_ALREADY.send(player, modeName);
                return false;
            }
            player.setGameMode(mode);
            Message.CMD_GAMEMODE_CHANGED.send(player, modeName);
            return true;
        } else if (args.length == 2) {
            if (args[1].equalsIgnoreCase("all")) {
                this.setAllGamemode(player, modeName, mode);
            } else {
                Player target = player.getServer().getPlayer(args[1]);
                if (target == null) {
                    Message.PLAYER_NOT_FOUND.send(player);
                    return false;
                }
                if (target.getGameMode().equals(mode)) {
                    Message.CMD_GAMEMODE_ALREADY_OTHER.send(player, target.getName(), modeName);
                    return false;
                }
                target.setGameMode(mode);
                Message.CMD_GAMEMODE_CHANGED_OTHER.send(player, target.getName(), modeName);
            }
        } else {
            Message.INVALID_ARGUMENTS.send(player);
            return false;
        }

        return true;
    }

    private void setAllGamemode(Player player, String modeName, GameMode mode) {
        Message.CMD_GAMEMODE_CHANGED_ALL.send(player, modeName);
        player.getServer().getOnlinePlayers().forEach(p -> {
            if (p.getGameMode().equals(mode)) {
                return;
            }
            p.setGameMode(mode);
            Message.CMD_GAMEMODE_CHANGED.send(p, modeName);
        });
    }

    private GameMode getGameMode(String modeString) {
        try {
            return GameMode.valueOf(modeString.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            String currentTyped = args[0].toLowerCase();

            return Arrays.stream(GameMode.values())
                    .map(GameMode::name)
                    .map(String::toLowerCase)
                    .filter(name -> name.startsWith(currentTyped))
                    .collect(Collectors.toList());
        } else if (args.length == 2) {
            List<String> suggestions = new ArrayList<>();
            suggestions.add("all");

            for (Player player : sender.getServer().getOnlinePlayers()) {
                suggestions.add(player.getName());
            }

            String currentTyped = args[1].toLowerCase();
            return suggestions.stream()
                    .filter(name -> name.toLowerCase().startsWith(currentTyped))
                    .collect(Collectors.toList());
        }
        return null;
    }
}

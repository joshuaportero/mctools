package dev.portero.neon.cmd;

import dev.portero.neon.locale.Message;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class QuickModeSwitch implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        Map<String, GameMode> gameModeMap = new HashMap<>();
        gameModeMap.put("gmc", GameMode.CREATIVE);
        gameModeMap.put("gms", GameMode.SURVIVAL);
        gameModeMap.put("gma", GameMode.ADVENTURE);
        gameModeMap.put("gmsp", GameMode.SPECTATOR);

        GameMode gameMode = gameModeMap.get(label.toLowerCase());
        String gameModeName = gameMode.name();

        if (player.getGameMode().equals(gameMode)) {
            Message.CMD_GAMEMODE_ALREADY.send(player, gameModeName);
            return false;
        }

        if (args.length == 0) {
            player.setGameMode(gameMode);
            Message.CMD_GAMEMODE_CHANGED.send(player, gameMode.name());
            return true;
        } else {
            Message.INVALID_ARGUMENTS.send(player);
        }

        return false;
    }
}

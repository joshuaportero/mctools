package xyz.feuxy.neon.cmd;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import xyz.feuxy.neon.locale.Message;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuickModeSwitchCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        Player player = (Player) sender;

        Map<String, GameMode> modeMap = new HashMap<>();
        modeMap.put("gmc", GameMode.CREATIVE);
        modeMap.put("gms", GameMode.SURVIVAL);
        modeMap.put("gma", GameMode.ADVENTURE);
        modeMap.put("gmsp", GameMode.SPECTATOR);

        GameMode mode = modeMap.get(label.toLowerCase());
        if (mode == null) return false;

        String modeName = mode.name();
        if (player.getGameMode().equals(mode)) {
            if (args.length == 0) {
                Message.CMD_GAMEMODE_ALREADY.send(player, modeName);
                return false;
            }
        }

        if (args.length == 0) {
            player.setGameMode(mode);
            Message.CMD_GAMEMODE_CHANGED.send(player, modeName);
            return true;
        } else if (args.length == 1 && args[0].equalsIgnoreCase("all")) {
            this.setAllGamemode(player, modeName, mode);
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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Collections.singletonList("all");
        }
        return null;
    }
}

package dev.portero.neon.cmd;

import dev.portero.neon.locale.Message;
import org.jetbrains.annotations.NotNull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        if (args.length == 0) {
            player.setAllowFlight(!player.getAllowFlight());
            if (player.getAllowFlight()) {
                Message.CMD_FLY_SELF_ENABLED.send(player);
            } else {
                Message.CMD_FLY_SELF_DISABLED.send(player);
            }
            return true;
        } else {
            Message.INVALID_ARGUMENTS.send(player);
        }
        return false;
    }
}

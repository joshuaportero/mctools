package dev.portero.neon.cmd;

import dev.portero.neon.locale.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OperatorCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        if (args.length == 0) {
            player.setOp(!player.isOp());
            if (player.isOp()) {
                Message.CMD_OPERATOR_SELF_ENABLED.send(player);
            } else {
                Message.CMD_OPERATOR_SELF_DISABLED.send(player);
            }
            return true;
        } else {
            Message.INVALID_ARGUMENTS.send(player);
        }
        return false;
    }
}

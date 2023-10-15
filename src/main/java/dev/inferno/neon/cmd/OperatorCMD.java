package dev.inferno.neon.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import dev.inferno.neon.locale.Message;

public class OperatorCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        Player player = (Player) sender;

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

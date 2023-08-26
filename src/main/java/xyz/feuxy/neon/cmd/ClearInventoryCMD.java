package xyz.feuxy.neon.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.feuxy.neon.locale.Message;

public class ClearInventoryCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            this.clearInventory(player);
        } else {
            Message.INVALID_ARGUMENTS.send(player);
        }
        return false;
    }

    private void clearInventory(Player player) {
        player.getInventory().clear();
        Message.CMD_CLEAR_SELF.send(player);
    }

}

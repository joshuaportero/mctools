package dev.portero.neon.cmd;

import dev.portero.neon.locale.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClearInventoryCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

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

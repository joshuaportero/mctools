package dev.portero.neon.cmd;

import dev.portero.neon.locale.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClearChatCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        if (args.length == 0) {
            this.clearChat(player);
        } else {
            Message.INVALID_ARGUMENTS.send(player);
        }

        return false;
    }

    private void clearChat(Player player) {
        for (int i = 0; i < 100; i++) {
            Message.CMD_CLEARCHAT.send(player);
        }
    }
}

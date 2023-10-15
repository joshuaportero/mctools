package dev.inferno.neon.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import dev.inferno.neon.locale.Message;

public class ClearChatCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        Player player = (Player) sender;

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

package xyz.feuxy.neon.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.feuxy.neon.util.StringUtil;

public class SpeedCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringUtil.color("&cYou must be a player to execute this command!"));
            return false;
        }

        // TODO: Let players set their own speed and set other players' speed
        if (player.getFlySpeed() == 1F) {
            player.setFlySpeed(0.1F);
            player.sendMessage(StringUtil.color("&aYour fly speed has been set to &e0.1&a!"));
        } else {
            player.setFlySpeed(1F);
            player.sendMessage(StringUtil.color("&aYour fly speed has been set to &e1&a!"));
        }

        return true;
    }
}

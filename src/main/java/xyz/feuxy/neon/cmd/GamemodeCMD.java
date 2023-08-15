package xyz.feuxy.neon.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.feuxy.neon.util.StringUtil;

public class GamemodeCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringUtil.color("&cYou must be a player to execute this command!"));
            return false;
        }

        // TODO: Change gamemode for other players

        if (label.equalsIgnoreCase("gmc")) {
            player.setGameMode(org.bukkit.GameMode.CREATIVE);
            player.sendMessage(StringUtil.color("&aYour gamemode has been set to &eCREATIVE&a!"));
            return true;
        }

        if (label.equalsIgnoreCase("gms")) {
            player.setGameMode(org.bukkit.GameMode.SURVIVAL);
            player.sendMessage(StringUtil.color("&aYour gamemode has been set to &dSURVIVAL&a!"));
            return true;
        }

        if (label.equalsIgnoreCase("gma")) {
            player.setGameMode(org.bukkit.GameMode.ADVENTURE);
            player.sendMessage(StringUtil.color("&aYour gamemode has been set to &bADVENTURE&a!"));
            return true;
        }

        if (label.equalsIgnoreCase("gmsp")) {
            player.setGameMode(org.bukkit.GameMode.SPECTATOR);
            player.sendMessage(StringUtil.color("&aYour gamemode has been set to &cSPECTATOR&a!"));
            return true;
        }

        return true;
    }
}

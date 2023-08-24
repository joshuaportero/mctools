package xyz.feuxy.neon.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import xyz.feuxy.neon.locale.Message;

import java.util.Collections;
import java.util.List;

public class HealCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            this.restorePlayer(player);
            return true;
        } else if (args.length == 1 && args[0].equalsIgnoreCase("all")) {
            Message.CMD_HEAL_ALL.send(player);
            for (Player online : player.getServer().getOnlinePlayers()) {
                if(online == null){
                    continue;
                }
                this.restorePlayer(online);
            }
            return true;
        } else {
            Message.INVALID_ARGUMENTS.send(player);
        }
        return true;
    }

    private void restorePlayer(Player player) {
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.setFireTicks(0);
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
        Message.CMD_HEAL_SELF.send(player);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Collections.singletonList("all");
        }
        return null;
    }
}

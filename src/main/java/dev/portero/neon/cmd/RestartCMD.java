package dev.portero.neon.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import dev.portero.neon.Neon;
import dev.portero.neon.locale.Message;

public class RestartCMD implements CommandExecutor {

    private final Neon plugin;

    public RestartCMD(Neon plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.PLAYERS_ONLY.send(sender);
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            Message.CMD_RESTART_SUCCESS.send(player);
            Bukkit.spigot().restart();
            return true;
        } else if (args.length == 1) {
            int time;
            try {
                time = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                Message.INVALID_ARGUMENTS.send(player);
                return false;
            }
            Message.CMD_RESTART_SUCCESS_TIME.send(player, time);
            new BukkitRunnable() {
                int count = time;

                @Override
                public void run() {
                    if (count == 0) {
                        cancel();
                        Bukkit.spigot().restart();
                        return;
                    } else if (count % 10 == 0 || count <= 5) {
                        Message.CMD_RESTART_SUCCESS_TIME.broadcast(count);
                    }
                    count--;
                }
            }.runTaskLater(plugin, 20L);
        } else {
            Message.INVALID_ARGUMENTS.send(player);
        }
        return false;
    }
}

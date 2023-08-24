package xyz.feuxy.neon.cmd;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Hanging;
import org.bukkit.entity.Player;
import xyz.feuxy.neon.locale.Message;

import java.time.Duration;
import java.time.Instant;

public class ButcherCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Instant now = Instant.now();
            int count = 0;
            for (World world : sender.getServer().getWorlds()) {
                count = removeEntitiesFromWorld(sender, count, world);
            }
            Duration duration = Duration.between(now, Instant.now());
            Message.CMD_BUTCHER.broadcast(sender, count, "all worlds", duration.toMillis());
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            Instant now = Instant.now();
            int count = 0;
            World world = player.getWorld();
            count = removeEntitiesFromWorld(player, count, world);
            Duration duration = Duration.between(now, Instant.now());
            Message.CMD_BUTCHER.broadcast(sender, count, world.getName(), duration.toMillis());
        } else {
            Message.INVALID_ARGUMENTS.send(player);
        }

        return true;
    }

    private int removeEntitiesFromWorld(CommandSender sender, int count, World world) {
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Hanging || entity instanceof Player) continue;
            Location location = entity.getLocation();
            Message.CMD_BUTCHER_LOG.log(sender,
                    entity.getType().name(),
                    location.getBlockX(),
                    location.getBlockY(),
                    location.getBlockZ(),
                    world.getName()
            );
            entity.remove();
            count++;
        }
        return count;
    }
}

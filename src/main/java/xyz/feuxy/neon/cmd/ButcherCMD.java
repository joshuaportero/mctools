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
            for (World world : sender.getServer().getWorlds()) {
                this.removeWorldEntities(world);
            }
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            this.removeWorldEntities(player.getWorld());
        }
        return false;
    }

    private void removeWorldEntities(World world) {
        Instant start = Instant.now();
        int count = 0;
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Hanging || entity instanceof Player) continue;
            entity.remove();
            Location location = entity.getLocation();
            Message.CMD_BUTCHER_LOG.log(entity.getType().name(),
                    location.getX(),
                    location.getY(),
                    location.getZ(),
                    world.getName().toUpperCase());
            count++;
        }
        Duration duration = Duration.between(start, Instant.now());
        Message.CMD_BUTCHER.broadcast(count, world.getName().toUpperCase(), duration.toMillis());
    }
}

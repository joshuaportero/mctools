package xyz.feuxy.neon.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Hanging;
import org.bukkit.entity.Player;
import xyz.feuxy.neon.Main;
import xyz.feuxy.neon.util.StringUtil;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

public class ButcherCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringUtil.color("&cYou must be a player to execute this command!"));
            return false;
        }

        Logger logger = Main.getInstance().getLogger();
        Instant now = Instant.now();

        int count = 0;
        for (Entity entity : player.getWorld().getEntities()) {
            if (entity instanceof Hanging || entity instanceof Player) continue;
            logger.info("Removed entity " + entity.getType().name() + " at " + entity.getLocation().toString());
            entity.remove();
            count++;
        }

        Duration duration = Duration.between(now, Instant.now());
        StringUtil.broadcast("&aRemoved &e" + count + "&a entities from &e" + player.getWorld().getName() + " &ain &e" + duration.toMillis() + "ms");

        return true;
    }
}

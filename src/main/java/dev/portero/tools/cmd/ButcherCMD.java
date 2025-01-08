package dev.portero.tools.cmd;

import dev.portero.tools.locale.Message.Butcher;
import dev.portero.tools.model.ButcherData;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.apache.commons.lang3.time.StopWatch;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Hanging;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

@RequiredArgsConstructor
@Command(name = "butcher")
public class ButcherCMD {

    private final Plugin plugin;

    @Execute
    public void execute(@Context CommandSender sender) {
        long timeElapsed = 0;
        int count = 0;
        for (World world : sender.getServer().getWorlds()) {
            ButcherData data = this.removeWorldEntities(world);
            timeElapsed += data.getTimeElapsed();
            count += data.getCount();
        }
        plugin.getServer().broadcast(Butcher.BROADCAST.getComponent(count, timeElapsed));
    }

    @Execute
    public void execute(@Context Player player) {
        ButcherData data = this.removeWorldEntities(player.getWorld());
        plugin.getServer().broadcast(Butcher.BROADCAST.getComponent(data.getCount(), data.getTimeElapsed()));
    }

    private ButcherData removeWorldEntities(World world) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        int count = 0;
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Hanging || entity instanceof Player) continue;
            entity.remove();

            Location location = entity.getLocation();
            String entityName = entity.getType().name();

            plugin.getLogger().log(Level.INFO, PlainTextComponentSerializer.plainText()
                    .serialize(Butcher.LOG.getComponent(entityName, location)));
            count++;
        }

        stopWatch.stop();

        return ButcherData.builder()
                .timeElapsed(stopWatch.getTime(TimeUnit.MILLISECONDS))
                .count(count)
                .build();
    }
}

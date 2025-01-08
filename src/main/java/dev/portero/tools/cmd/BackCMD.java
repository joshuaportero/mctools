package dev.portero.tools.cmd;

import dev.portero.tools.cache.PlayerCache;
import dev.portero.tools.locale.Message;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
@Command(name = "back")
public class BackCMD {

    private final PlayerCache playerCache;

    @Execute
    public void execute(@Context Player player) {
        Location lastLocation = playerCache.getLastLocation(player.getUniqueId());
        if (lastLocation == null) {
            Message.Back.NO_PREVIOUS.send(player);
            return;
        }
        player.teleport(lastLocation);
        Message.Back.SUCCESS.send(player);
    }
}


package dev.portero.tools.cmd;

import dev.portero.tools.locale.Message;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.shortcut.Shortcut;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.World;
import org.bukkit.entity.Player;

@Command(name = "weather")
public class QuickWeatherSwitch {

    @Execute(name = "clear")
    @Shortcut(value = "sun")
    public void clear(@Context Player player) {
        this.changeWeather(player, "sun");
    }

    @Execute(name = "rain")
    @Shortcut(value = "rain")
    public void rain(@Context Player player) {
        this.changeWeather(player, "rain");
    }

    @Execute(name = "thunder")
    @Shortcut(value = "thunder")
    public void storm(@Context Player player) {
        this.changeWeather(player, "thunder");
    }

    private void changeWeather(Player player, String weather) {
        Weather value = Weather.valueOf(weather.toUpperCase());
        World world = player.getWorld();
        world.setStorm(value.isRain());
        world.setThundering(value.isThunder());
        world.setWeatherDuration(Integer.MAX_VALUE);
        Message.QuickWeatherSwitch.SWITCH.send(player, value);
    }

    @Getter
    @AllArgsConstructor
    public enum Weather {
        SUN(false, false),
        RAIN(true, false),
        THUNDER(true, true);

        private final boolean isRain;
        private final boolean isThunder;
    }
}

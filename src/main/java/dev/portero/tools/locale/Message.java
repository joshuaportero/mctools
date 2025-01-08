package dev.portero.tools.locale;

import dev.portero.tools.cmd.QuickWeatherSwitch.Weather;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public interface Message {

    interface Error {
        Args0 NO_PERMISSION = () -> "&cYou don't have permission to do that!";
        Args0 INVALID_ARGUMENT = () -> "&cThe command doesn't support the provided arguments!";
    }

    interface SpeedCMD {
        Args1<Integer> SWITCH_FLY = (speed) -> "&aYou have switched your fly speed to &e" + speed + "&a.";
        Args1<Integer> SWITCH_WALK = (speed) -> "&aYou have switched your walk speed to &e" + speed + "&a.";
    }

    interface QuickWeatherSwitch {
        Args1<Weather> SWITCH = (weather) -> "&aYou have switched the weather to &e" + weather.toString() + "&a.";
    }

    interface QuickTimeSwitch {
        Args1<Long> SWITCH = (time) -> "&aYou have switched the time to &e" + time + "&a.";
    }

    interface QuickModeSwitch {
        Args1<GameMode> SWITCH = (mode) -> "&aYou have switched to &e" + mode.toString() + "&a mode.";
    }

    interface Heal {
        Args0 SUCCESS = () -> "&aYou have been healed!";
    }

    interface Back {
        Args0 SUCCESS = () -> "&aYou have returned to your last location!";
        Args0 NO_PREVIOUS = () -> "&cYou don't have a previous location!";
    }

    interface Op {
        Args0 ENABLED = () -> "&aYou have been made an operator!";
        Args0 DISABLED = () -> "&cYou have been made a non-operator!";
    }

    interface Butcher {
        Args2<String, Location> LOG = (entity, location) ->
                "&7[&cBUTCHER&7] Removed &e" + entity.toUpperCase(Locale.ROOT) +
                        " &7from &e" + location.getWorld().getName().toUpperCase(Locale.ROOT) +
                        " &7at [&e" + location.getBlockX() +
                        "&7, &e" + location.getBlockY() +
                        "&7, &e" + location.getBlockZ() + "&7].";
        Args2<Integer, Long> BROADCAST = (count, duration) -> "&aWe successfully butchered &e" + count +
                " &aentity(ies) in &e" + duration + "&aμs.";
    }

    interface ClearInventory {
        Args0 SUCCESS = () -> "&aYou have cleared your inventory!";
    }

    interface Feed {
        Args0 SUCCESS = () -> "&aYour hunger has been restored!";
    }

    interface Fly {
        Args0 ENABLED = () -> "&aYour flight mode has been enabled!";
        Args0 DISABLED = () -> "&cYour flight mode has been disabled!";
    }

    interface Args0 {
        String getMessage();

        default void send(CommandSender sender) {
            sender.sendMessage(colorize(getMessage()));
        }
    }

    interface Args1<A0> {
        String getMessage(A0 arg0);

        default void send(CommandSender sender, A0 arg0) {
            sender.sendMessage(colorize(getMessage(arg0)));
        }
    }

    interface Args2<A0, A1> {
        String getMessage(A0 arg0, A1 arg1);

        default void send(CommandSender sender, A0 arg0, A1 arg1) {
            sender.sendMessage(colorize(getMessage(arg0, arg1)));
        }

        default Component getComponent(A0 arg0, A1 arg1) {
            return colorize(getMessage(arg0, arg1));
        }
    }

    static @NotNull TextComponent colorize(@NotNull String message) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(message);
    }
}

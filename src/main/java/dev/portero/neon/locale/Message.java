package dev.portero.neon.locale;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

import static net.kyori.adventure.text.Component.space;
import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.*;

public interface Message {

    Args0 PLAYERS_ONLY = () -> text("Only players can use this command!").color(RED);
    Args0 INVALID_ARGUMENTS = () -> text("Invalid arguments! Please try again.").color(RED);
    Args1<String> CMD_GAMEMODE_ALREADY = (gamemode) -> text("You are already in ")
            .color(RED)
            .append(text(gamemode).color(YELLOW))
            .append(text(" mode!").color(RED));

    Args1<String> CMD_GAMEMODE_CHANGED = (gamemode) -> text("Your gamemode has been changed to ")
            .color(GREEN)
            .append(text(gamemode).color(YELLOW))
            .append(text("!").color(GREEN));

    Args2<String, String> CMD_TIME_CHANGED = (time, world) -> text("The time has been changed to ")
            .color(GREEN)
            .append(text(time).color(YELLOW))
            .append(text(" in ").color(GREEN))
            .append(text(world).color(YELLOW))
            .append(text("!").color(GREEN));

    Args2<String, String> CMD_WEATHER_CHANGED = (weather, world) -> text("The weather has been changed to ")
            .color(GREEN)
            .append(text(weather).color(YELLOW))
            .append(text(" in ").color(GREEN))
            .append(text(world).color(YELLOW))
            .append(text("!").color(GREEN));
    Args0 CMD_FLY_SELF_ENABLED = () -> text("You have enabled fly mode!").color(GREEN);

    Args0 CMD_FLY_SELF_DISABLED = () -> text("You have disabled fly mode!").color(RED);
    Args3<Integer, String, Long> CMD_BUTCHER = (count, world, ms) -> text("Removed ")
            .color(GREEN)
            .append(text(count).color(YELLOW))
            .append(text(" entities from "))
            .append(text(world).color(YELLOW))
            .append(text(" in "))
            .append(text(ms).color(YELLOW))
            .append(text(" ms.").color(GREEN));

    Args5<String, Double, Double, Double, String> CMD_BUTCHER_LOG = (count, x, y, z, world) -> text("Removed ")
            .color(RED)
            .append(text(count).color(YELLOW))
            .append(text(" at "))
            .append(text(x).color(YELLOW))
            .append(text(", "))
            .append(text(y).color(YELLOW))
            .append(text(", "))
            .append(text(z).color(YELLOW))
            .append(text(" from "))
            .append(text(world).color(YELLOW))
            .append(text(".").color(RED));
    Args0 CMD_CLEAR_SELF = () -> text("Your inventory has been cleared!").color(GREEN);
    Args0 CMD_HEAL_SELF = () -> text("Your health, hunger and potion effects have been restored!").color(GREEN);
    Args0 CMD_CLEARCHAT = () -> text("");
    Args0 CMD_FEED_SELF = () -> text("Your hunger has been restored!").color(GREEN);
    Args0 CMD_OPERATOR_SELF_ENABLED = () -> text("You have been given operator permissions!").color(GREEN);
    Args0 CMD_OPERATOR_SELF_DISABLED = () -> text("You have been revoked operator permissions!").color(RED);
    Args0 CMD_SELF_GIVE_USAGE = () -> text("Usage: ")
            .color(RED)
            .append(text("/i [item] [amount]"));

    Args0 INVALID_MATERIAL = () -> text("Invalid material! Please try again.").color(RED);

    Args2<Integer, String> CMD_SELF_GIVE_SUCCESS = (count, item) -> text("You received ")
            .color(GREEN)
            .append(text("x").color(AQUA))
            .append(text(count).color(YELLOW))
            .append(text(" of "))
            .append(text(item).color(YELLOW))
            .append(text("!").color(GREEN));

    Args0 INVALID_AMOUNT = () -> text("Invalid number! Please try again.").color(RED);
    Args0 CMD_SELF_GIVE_NONE = () -> text("You cannot give yourself ")
            .color(RED)
            .append(text("0").color(YELLOW))
            .append(text(" of an item!").color(RED));

    Args0 CMD_SELF_GIVE_AIR = () -> text("You cannot give yourself AIR!").color(RED);

    Args1<Float> CMD_SPEED_SELF = (speed) -> text("Your flight speed has been set to ")
            .color(GREEN)
            .append(text(speed).color(YELLOW))
            .append(text("!").color(GREEN));

    Args0 CMD_UP_USAGE = () -> text("Usage: ")
            .color(RED)
            .append(text("/up [amount]"));
    Args1<Integer> CMD_UP_SUCCESS = (count) -> text("You have been teleported").color(GREEN)
            .append(space())
            .append(text(count).color(YELLOW))
            .append(space())
            .append(text("blocks up!").color(GREEN));

    Args0 CMD_TOP_SUCCESS = () -> text("&aYou have been teleported to the &eTOP OF THE WORLD&a!");
    Args0 CMD_BACK_NO_LAST_LOCATION = () -> text("&cYou do not have a last location to teleport to!");
    Args0 CMD_BACK_SUCCESS = () -> text("&aYou have been teleported to &eYOUR LAST LOCATION&a!");

    interface Args0 {
        Component get();

        default void send(Audience audience) {
            audience.sendMessage(this.get());
        }
    }

    interface Args1<A0> {
        Component get(A0 arg0);

        default void send(Audience audience, A0 arg0) {
            audience.sendMessage(this.get(arg0));
        }
    }

    interface Args2<A0, A1> {
        Component get(A0 arg0, A1 a2);

        default void send(Audience audience, A0 arg0, A1 arg1) {
            audience.sendMessage(this.get(arg0, arg1));
        }

        default void broadcast(A0 arg0, A1 arg1) {
            Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(this.get(arg0, arg1)));
            Bukkit.getConsoleSender().sendMessage(this.get(arg0, arg1));
        }
    }

    interface Args3<A0, A1, A2> {
        Component get(A0 arg0, A1 a2, A2 a3);

        default void broadcast(A0 arg0, A1 arg1, A2 arg2) {
            Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(this.get(arg0, arg1, arg2)));
            Bukkit.getConsoleSender().sendMessage(this.get(arg0, arg1, arg2));
        }
    }

    interface Args5<A0, A1, A2, A3, A4> {
        Component get(A0 arg0, A1 a2, A2 a3, A3 a4, A4 a5);

        default void log(A0 arg0, A1 arg1, A2 arg2, A3 arg3, A4 arg4) {
            Bukkit.getConsoleSender().sendMessage(this.get(arg0, arg1, arg2, arg3, arg4));
        }
    }
}

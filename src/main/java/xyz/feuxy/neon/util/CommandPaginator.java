package xyz.feuxy.neon.util;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandPaginator {

    private final List<String> commands;

    public CommandPaginator() {
        commands = new ArrayList<>();
        commands.add("  &7- &e/o &7- &fToggle operator mode.");
        commands.add("  &7- &e/i <item> &7- &fGet an item.");
        commands.add("  &7- &e/up <distance> &7- &fTeleport up.");
        commands.add("  &7- &e/top &7- &fTeleport to the highest block.");
        commands.add("  &7- &e/gmc &7- &fChange gamemode to creative.");
        commands.add("  &7- &e/gms &7- &fChange gamemode to survival.");
        commands.add("  &7- &e/gma &7- &fChange gamemode to adventure.");
        commands.add("  &7- &e/gmsp &7- &fChange gamemode to spectator.");
        commands.add("  &7- &e/time set <time> &7- &fSet time.");
        commands.add("  &7- &e/weather <weather> <thunder> &7- &fSet weather.");
        commands.add("  &7- &e/day &7- &fChange time to day.");
        commands.add("  &7- &e/night &7- &fChange time to night.");
        commands.add("  &7- &e/sun &7- &fChange weather to sunny.");
        commands.add("  &7- &e/rain &7- &fChange weather to rainy.");
        commands.add("  &7- &e/heal &7- &fHeal yourself.");
        commands.add("  &7- &e/feed &7- &fFeed yourself.");
        commands.add("  &7- &e/fly &7- &fToggle fly mode.");
        commands.add("  &7- &e/speed <speed> &7- &fChange fly speed.");
        commands.add("  &7- &e/ci &7- &fClear your inventory.");
        commands.add("  &7- &e/clear <player> &7- &fClear player's inventory.");
        commands.add("  &7- &e/cc &7- &fClear your chat.");
        commands.add("  &7- &e/clearchat <player> &7- &fClear player's chat.");
        commands.add("  &7- &e/clean &7- &fRemove any effects.");
        commands.add("  &7- &e/clean <player> &7- &fRemove any effects from a player.");
        commands.add("  &7- &e/butcher &7- &fKill all animals and monsters.");
    }

    public void sendPage(Player player, int pageNumber) {
        int commandsPerPage = 5;
        int startIndex = (pageNumber - 1) * commandsPerPage;
        int endIndex = Math.min(startIndex + commandsPerPage, commands.size());

        if (startIndex < 0 || startIndex >= commands.size()) {
            player.sendMessage(StringUtil.color("&cInvalid page number!"));
            return;
        }

        player.sendMessage(StringUtil.color("&cUsage&7[&e" + pageNumber + "&7/&e" +
                (int) Math.ceil(commands.size() / (double) commandsPerPage) + "&7]"));
        for (int i = startIndex; i < endIndex; i++) {
            player.sendMessage(StringUtil.color(commands.get(i)));
        }
    }
}

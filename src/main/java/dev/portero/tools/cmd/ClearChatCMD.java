package dev.portero.tools.cmd;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

@Command(name = "clearchat", aliases = {"cc"})
public class ClearChatCMD {

    @Execute
    public void execute(@Context CommandSender sender) {
        this.clearChat(sender);
    }

    private void clearChat(CommandSender sender) {
        for (int i = 0; i < 100; i++) {
            sender.sendMessage(Component.empty());
        }
    }
}

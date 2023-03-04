package org.pinkprison.pinkcore.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.api.command.Command;
import org.pinkprison.pinkcore.api.utils.ColorUtils;

public class BroadcastCommand extends Command implements CommandExecutor {

    private final PinkCore plugin;

    public BroadcastCommand(PinkCore plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!sender.hasPermission("pinkstaff.broadcast")) {
            sender.sendMessage(ColorUtils.color(plugin.getPrefix() + " &fDu har ikke adgang til denne kommando."));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ColorUtils.color(plugin.getPrefix() + " &fBrug: &7/broadcast <besked>"));
            return true;
        }

        StringBuilder message = new StringBuilder();
        for (String arg : args) {
            message.append(arg).append(" ");
        }

        Bukkit.broadcastMessage(ColorUtils.color(plugin.getPrefix() + " " + ColorUtils.color(message.toString())));
        return true;
    }
}
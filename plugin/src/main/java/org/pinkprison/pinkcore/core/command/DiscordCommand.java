package org.pinkprison.pinkcore.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.api.command.Command;
import org.pinkprison.pinkcore.api.utils.ColorUtils;

public class DiscordCommand extends Command implements CommandExecutor {

    private final PinkCore plugin;

    public DiscordCommand(PinkCore plugin) {
        super(plugin);
        plugin.getCommand("discord").setExecutor(this);
        this.plugin = plugin;
    }

    /*
     * TODO: Add discord link to config
     */
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!sender.hasPermission("pinkcore.command.discord")) {
            sender.sendMessage(ColorUtils.color(plugin.getPrefix() + " &fDu har ikke adgang til denne kommando."));
            return true;
        }

        Bukkit.broadcastMessage(ColorUtils.color(plugin.getPrefix() + " &fhttps://discord.gg/Z4BvEzN\n"));
        return true;
    }
}
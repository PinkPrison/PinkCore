package org.pinkprison.pinkcore.core.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.pinkprison.pinkcore.PinkCore;

public abstract class Command {
    private final PinkCore plugin;

    public Command(PinkCore plugin) {
        this.plugin = plugin;
    }

    protected boolean isPlayer(CommandSender sender) {
        return isPlayer(sender, true);
    }

    protected boolean isPlayer(CommandSender sender, boolean notify) {
        if (sender instanceof Player) return true;
        if (notify) sender.sendMessage("Du skal være en spiller for at bruge denne kommando.");
        return false;
    }

    protected boolean hasPermission(CommandSender sender, String permission) {
        return hasPermission(sender, permission, true);
    }

    protected boolean hasPermission(CommandSender sender, String permission, boolean notify) {
        if (sender.hasPermission(permission)) return true;
        if (notify) sender.sendMessage("Du har ikke adgang til denne kommando.");
        return false;
    }

    protected PinkCore getPlugin() {
        return plugin;
    }
}

package org.pinkprison.pinkcore.core.command.subcore;

import org.bukkit.command.CommandSender;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.api.utils.ColorUtils;
import org.pinkprison.pinkcore.api.command.SubCommand;

public class ReloadCommand extends SubCommand {

    public ReloadCommand(PinkCore plugin) {
        super(plugin, "Reload the plugin", "reload", "pinkcore.reload", "reload", "rl");
    }

    /**
     * {@inheritDoc}
     * Reloads the config and configurable parts of the plugin.
     *
     * @param sender The sender of the command
     * @param args The arguments of the command
     */
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length != 0) {
            return false;
        }
        if (!hasPermission(sender, getPermission())) {
            return true;
        }

        getPlugin().reload();
        sender.sendMessage(ColorUtils.getColored(getPlugin().getPrefix()) + " Plugin " + getPlugin().getName() + " reloaded!");
        return true;
    }

}

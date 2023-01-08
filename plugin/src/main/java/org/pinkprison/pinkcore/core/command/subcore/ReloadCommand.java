package org.pinkprison.pinkcore.core.command.subcore;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.api.utils.ColorUtils;
import org.pinkprison.pinkcore.api.command.SubCommand;

public class ReloadCommand extends SubCommand {

    private final PinkCore plugin;

    public ReloadCommand(JavaPlugin plugin) {
        super(plugin, "Reload the plugin", "reload", "pinkcore.reload", "reload", "rl");
        this.plugin = (PinkCore) plugin;
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

        this.plugin.reload();
        sender.sendMessage(ColorUtils.getColored(this.plugin.getPrefix()) + " Plugin " + getPlugin().getName() + " reloaded!");
        return true;
    }

}

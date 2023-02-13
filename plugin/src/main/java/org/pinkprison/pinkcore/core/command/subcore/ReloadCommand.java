package org.pinkprison.pinkcore.core.command.subcore;

import org.bukkit.command.CommandSender;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.api.command.CommandResult;
import org.pinkprison.pinkcore.api.utils.ColorUtils;
import org.pinkprison.pinkcore.api.command.SubCommand;

public class ReloadCommand extends SubCommand {

    private final PinkCore plugin;

    public ReloadCommand(PinkCore plugin) {
        super(plugin, "Reload the plugin", "reload", "pinkcore.reload", "reload");
        this.plugin = plugin;
    }

    /**
     * {@inheritDoc}
     * Reloads the config and configurable parts of the plugin.
     *
     * @param sender The sender of the command
     * @param args The arguments of the command
     */
    @Override
    public CommandResult execute(CommandSender sender, String[] args) {
        if (args.length != 0) {
            return CommandResult.wrongUsage(this);
        }

        if (!hasPermission(sender, getPermissions())) {
            return CommandResult.noPermission(this);
        }

        this.plugin.reload();
        sender.sendMessage(ColorUtils.color(this.plugin.getPrefix()) + " Plugin " + this.plugin.getName() + " reloaded!");
        return CommandResult.success(this);
    }

}

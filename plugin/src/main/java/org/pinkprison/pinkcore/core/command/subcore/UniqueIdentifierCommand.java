package org.pinkprison.pinkcore.core.command.subcore;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.api.command.CommandResult;
import org.pinkprison.pinkcore.api.utils.ColorUtils;
import org.pinkprison.pinkcore.api.command.SubCommand;

public class UniqueIdentifierCommand extends SubCommand {

    private final PinkCore plugin;

    public UniqueIdentifierCommand(PinkCore plugin) {
        super(plugin, "Provides a UUID from an Offline Player", "uuid <player>", "*", "uuid");
        this.plugin = plugin;
    }

    /**
     * {@inheritDoc}
     *
     * Provides a UUID from an {@link OfflinePlayer}
     *
     * @param sender The sender of the command.
     * @param args The arguments of the command.
     *
     * @return true if the command was executed successfully, false otherwise.
     */
    @Override
    public CommandResult execute(CommandSender sender, String[] args) {
        if (args.length != 1) {
            return CommandResult.wrongUsage(this);
        }

        if (!hasPermission(sender, getPermission())) {
            return CommandResult.noPermission(this);
        }

        final OfflinePlayer player = getPlugin().getServer().getOfflinePlayer(args[0]);
        if (player == null) {
            sender.sendMessage(ColorUtils.color(this.plugin.getPrefix()) + " Player " + args[0] + " not found!");
            return CommandResult.success(this);
        }

        sender.sendMessage(ColorUtils.color(this.plugin.getPrefix()) + " UUID for " + player.getName() + " is §c" + player.getUniqueId().toString());
        return CommandResult.success(this);
    }
}

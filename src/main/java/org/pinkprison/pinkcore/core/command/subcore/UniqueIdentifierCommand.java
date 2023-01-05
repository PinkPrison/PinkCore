package org.pinkprison.pinkcore.core.command.subcore;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.api.utils.ColorUtils;
import org.pinkprison.pinkcore.core.command.SubCommand;

public class UniqueIdentifierCommand extends SubCommand {

    public UniqueIdentifierCommand(PinkCore plugin) {
        super(plugin, "Provides a UUID from an Offline Player", "uuid", "*", "uuid", "uniqueid", "uniqueidentifier", "uid", "u");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length != 1) {
            return false;
        }
        if (!hasPermission(sender, getPermission())) {
            return true;
        }
        final OfflinePlayer player = getPlugin().getServer().getOfflinePlayer(args[0]);
        if (player == null) {
            sender.sendMessage(ColorUtils.getColored(getPlugin().getPrefix()) + " Player " + args[0] + " not found!");
            return true;
        }
        sender.sendMessage(ColorUtils.getColored(getPlugin().getPrefix()) + " UUID for " + player.getName() + " is §c" + player.getUniqueId().toString());
        return true;
    }
}

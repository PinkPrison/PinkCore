package org.pinkprison.pinkcore.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.api.utils.ColorUtils;
import org.pinkprison.pinkcore.core.config.Loader;

import java.util.UUID;

public class CommandBlocker implements CommandExecutor {

    private final String prefix;
    private final Loader loader;


    public CommandBlocker(PinkCore plugin, Loader loader) {
        this.prefix = plugin.getPrefix();
        this.loader = loader;
    }

    /**
     * Check if the command is blocked. If it is, send a message to the player and cancel the event.
     *
     * @param commandSender The sender of the command.
     * @param command The command to check.
     * @return True if the command is blocked, false if not.
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        final UUID uuid = ((Player) commandSender).getUniqueId();

        if (isBlocked(command.getName())) {
            if (canBypass(uuid)) {
                return false;
            }
        }
        return false;
    }

    /**
     * Check if the sender can bypass the command blocker.
     *
     * @param uuid The UUID of the sender.
     *
     * @return True if the sender can bypass, false if not.
     */
    private boolean canBypass(UUID uuid) {
        return loader.getAllowedPlayers().contains(uuid.toString());
    }

    /**
     * Check if the command is blocked.
     *
     * @param command The command to check.
     *
     * @return True if the command is blocked, false if not.
     */
    private boolean isBlocked(String command) {
        for (String blockedCommand : loader.getBlockedCommands()) {
            if (command.equalsIgnoreCase(blockedCommand)) {
                return true;
            }
        }
        return false;
    }
}

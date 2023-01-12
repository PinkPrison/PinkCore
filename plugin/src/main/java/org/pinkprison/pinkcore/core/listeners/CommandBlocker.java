package org.pinkprison.pinkcore.core.listeners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.api.utils.ColorUtils;
import org.pinkprison.pinkcore.core.config.Loader;

import java.util.UUID;

public class CommandBlocker implements Listener {

    private final String prefix;
    private final Loader loader;


    public CommandBlocker(PinkCore plugin, Loader loader) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.prefix = plugin.getPrefix();
        this.loader = loader;
    }

    /**
     * Check if the command is blocked. If it is, send a message to the player and cancel the event.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        final String command = event.getMessage().split(" ")[0].replace("/", "");
        final UUID uuid = event.getPlayer().getUniqueId();

        if (isBlocked(command)) {
            if (canBypass(uuid)) {
                return;
            }
            event.setCancelled(true);
            event.getPlayer().sendMessage(ColorUtils.getColored(prefix + loader.getBlockedCommandsMessage()));
        }
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

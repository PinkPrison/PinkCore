package org.pinkprison.pinkcore.core.listeners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.pinkprison.pinkcore.api.utils.ColorUtils;
import org.pinkprison.pinkcore.core.config.Loader;

import java.util.UUID;

public class CommandBlocker implements CommandExecutor, Listener {

    private final String prefix;
    private final Loader loader;


    public CommandBlocker(String pluginPrefix, Loader loader) {
        this.prefix = pluginPrefix;
        this.loader = loader;
    }

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
            commandSender.sendMessage(ColorUtils.getColored(prefix + loader.getBlockedCommandsMessage()));
        }
        return false;
    }

    private boolean canBypass(UUID uuid) {
        return loader.getAllowedPlayers().contains(uuid.toString());
    }

    private boolean isBlocked(String command) {
        return loader.getBlockedCommands().contains(command);
    }

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
}

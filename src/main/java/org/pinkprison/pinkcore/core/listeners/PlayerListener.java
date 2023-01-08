package org.pinkprison.pinkcore.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.pinkprison.pinkcore.api.utils.ColorUtils;
import org.pinkprison.pinkcore.core.config.Loader;

public class PlayerListener implements Listener {

    private final Loader loader;
    private final boolean sendMessages;

    public PlayerListener(Loader loader) {
        this.loader = loader;
        this.sendMessages = loader.sendJoinLeaveMessages();
    }

    /**
     * @param event The event that was fired.
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (sendMessages) {
            event.setJoinMessage(
                    ColorUtils.getColored(loader.getJoinMessage().replace("%player%", event.getPlayer().getName()))
            );
        }
    }

    /**
     * @param event The event that was fired.
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (sendMessages) {
            event.setQuitMessage(
                    ColorUtils.getColored(loader.getLeaveMessage().replace("%player%", event.getPlayer().getName()))
            );
        }
    }
}

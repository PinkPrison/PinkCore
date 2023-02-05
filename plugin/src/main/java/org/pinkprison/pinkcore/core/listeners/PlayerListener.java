package org.pinkprison.pinkcore.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.api.utils.ColorUtils;
import org.pinkprison.pinkcore.core.config.Loader;

public class PlayerListener implements Listener {

    private final Loader loader;
    private final boolean sendMessages;

    public PlayerListener(PinkCore plugin, Loader loader) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
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
                    ColorUtils.color(loader.getJoinMessage().replace("%player%", event.getPlayer().getName()))
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
                    ColorUtils.color(loader.getLeaveMessage().replace("%player%", event.getPlayer().getName()))
            );
        }
    }
}
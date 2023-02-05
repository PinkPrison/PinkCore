package org.pinkprison.pinkcore.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.core.config.Loader;

public class WorldListener implements Listener {

    private final boolean allowPortalCreation;

    public WorldListener(PinkCore plugin, Loader loader) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.allowPortalCreation = loader.allowPortalCreation();
    }

    /**
     * Disable portal creation.
     *
     * @param event {@link PortalCreateEvent}
     */
    @EventHandler
    public void onPortalCreation(PortalCreateEvent event) {
        if (!allowPortalCreation) {
            event.setCancelled(true);
        }
    }
}
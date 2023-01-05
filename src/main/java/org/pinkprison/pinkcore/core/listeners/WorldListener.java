package org.pinkprison.pinkcore.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

public class WorldListener implements Listener {

    /**
     * Disable portal creation.
     *
     * @param event {@link PortalCreateEvent}
     */
    @EventHandler
    public void onPortalCreation(PortalCreateEvent event) {
        event.setCancelled(true);
    }
}

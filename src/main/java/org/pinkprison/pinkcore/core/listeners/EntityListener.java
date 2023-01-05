package org.pinkprison.pinkcore.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityListener implements Listener {

    /**
     * Prevents explosions from destroying blocks.
     *
     * @param event {@link EntityExplodeEvent}
     */
    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        event.setCancelled(true);
    }

    /**
     * Prevents entities from creating portals.
     *
     * @param event {@link EntityCreatePortalEvent}
     */
    @EventHandler
    public void onEntityPortalCreation(EntityCreatePortalEvent event) {
        event.setCancelled(true);
    }
}

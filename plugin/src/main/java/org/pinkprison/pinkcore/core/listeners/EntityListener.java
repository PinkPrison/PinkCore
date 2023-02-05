package org.pinkprison.pinkcore.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.core.config.Loader;

public class EntityListener implements Listener {

    private final boolean allowPortalCreation;
    private final boolean allowExplosions;

    public EntityListener(PinkCore plugin, Loader loader) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.allowPortalCreation = loader.allowPortalCreation();
        this.allowExplosions = loader.allowExplosions();
    }

    /**
     * Prevents explosions from destroying blocks.
     *
     * @param event {@link EntityExplodeEvent}
     */
    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        if (!allowExplosions) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onExplosionDamage(EntityDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.BLOCK_EXPLOSION
                && event.getCause() != EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
            return;
        }
        if (!allowExplosions) {
            event.setDamage(0.0);
            event.setCancelled(true);
        }
    }

    /**
     * Prevents entities from creating portals.
     *
     * @param event {@link EntityCreatePortalEvent}
     */
    @EventHandler
    public void onEntityPortalCreation(EntityCreatePortalEvent event) {
        if (!allowPortalCreation) {
            event.setCancelled(true);
        }
    }
}
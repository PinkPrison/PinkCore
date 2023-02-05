package org.pinkprison.pinkcore.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.api.utils.ColorUtils;
import org.pinkprison.pinkcore.core.config.Loader;

public class CraftListener implements Listener {

    private final Loader loader;

    public CraftListener(PinkCore plugin, Loader loader) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.loader = loader;
    }

    /**
     * Cancel the crafting of items that are disabled.
     *
     * @param event The {@link CraftItemEvent} that was fired.
     */
    @EventHandler
    public void onCraft(CraftItemEvent event) {
        final ItemStack resultItem = event.getRecipe().getResult();
        final Player player = (Player) event.getWhoClicked();

        if (isBlacklisted(resultItem, loader.getAntiCraftBlacklistedItems().toArray(new Integer[0]))) {
            player.sendMessage(ColorUtils.color(loader.getAntiCraftMessage()));

            event.setCancelled(true);
            event.setResult(CraftItemEvent.Result.DENY);
        }
    }

    /**
     * Check if the item is blacklisted.
     *
     * @param resultItem The item to check.
     * @param blacklistedItemIDs The blacklisted items.
     *
     * @return Whether the item is blacklisted or not.
     */
    private boolean isBlacklisted(ItemStack resultItem, Integer... blacklistedItemIDs) {
        for (int itemId : blacklistedItemIDs) {
            if (resultItem.getType().getId() == itemId) {
                return true;
            }
        }
        return false;
    }
}

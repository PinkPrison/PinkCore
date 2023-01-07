package org.pinkprison.pinkcore.core.listeners;

import org.bukkit.Warning;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@Deprecated
@Warning(reason = "This class is deprecated, and the listener should only be registered if the issue is Relevant.")
public class DamageBugCanceller implements Listener {

    /**
     * Prevents players from damaging other players if they (Intentionally?) are using an exploit.
     * With the permission "pinkcore.bypass.damage", players can bypass this.
     *
     * @param event {@link EntityDamageByEntityEvent}
     */
    @Deprecated
    @EventHandler
    public void onPlayerAttackPlayer(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        final Player player = (Player) event.getDamager();
        if (player.hasPermission("pinkcore.bypass.damage")) {
            return;
        }
        final ItemStack item = player.getInventory().getItemInHand();

        if (!checkDamage(item, "sword", "axe", "hoe", "shovel", "bow", "helmet", "chestplate", "leggings", "boots")) return;
        final double damage = event.getDamage();

        if (damage > 2.8) {
            event.setCancelled(true);
            player.sendMessage("§8[ §f§lDAMAGE BUG §8] §fDet ser ud til du bruger en Damage Bug, du kan derfor ikke slå folk.");
            player.sendMessage("§8[ §f§lDAMAGE BUG §8] §fFå droppet et sværd til dig, så kan du slå igen.");
        }
    }

    /**
     * Checks if the item is allowed to exceed the damage limit.
     *
     * @param heldItem the held {@link ItemStack}
     * @param dontCheck the items that are allowed to exceed the damage limit.
     *
     * @return if the item is allowed to exceed the damage limit.
     */

    private boolean checkDamage(ItemStack heldItem, String @NotNull ... dontCheck) {
        for (String item : dontCheck) {
            if (heldItem.getType().name()
                    .toLowerCase().contains(item.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

}

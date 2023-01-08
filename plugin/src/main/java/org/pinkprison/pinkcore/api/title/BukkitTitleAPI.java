package org.pinkprison.pinkcore.api.title;

import org.bukkit.entity.Player;

class BukkitTitleAPI implements InternalTitleAPI {
    BukkitTitleAPI() {
    }

    /** {@inheritDoc} */
    @Override
    public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }
}

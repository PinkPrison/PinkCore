package org.pinkprison.pinkcore.api.title;

import org.bukkit.entity.Player;

/**
 * Title API for Bukkit
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author WildTooth
 */
class BukkitTitleAPI implements InternalTitleAPI {
    BukkitTitleAPI() {
    }

    /** {@inheritDoc} */
    @Override
    public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }
}

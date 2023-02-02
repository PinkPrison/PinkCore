package org.pinkprison.pinkcore.api.title;

import org.bukkit.entity.Player;

/**
 * An internal title API.
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author WildTooth
 */
interface InternalTitleAPI {
    /**
     * Sends a title to a {@link Player}.
     *
     * @param player   The player to send the title to.
     * @param title    The title to send.
     * @param subtitle The subtitle to send.
     * @param fadeIn   The time in ticks for the title to fade in.
     * @param stay     The time in ticks for the title to stay on screen.
     * @param fadeOut  The time in ticks for the title to fade out.
     */
    void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut);

    /**
     * Sends an empty title to a {@link Player}.
     *
     * @param player The {@link Player} to send the empty title to.
     *
     */
    default void clearTitle(Player player) {
        sendTitle(player, "", "", 0, 0, 0);
    }
}

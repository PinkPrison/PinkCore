package org.pinkprison.pinkcore.api.title;

import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * Title API
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author WildTooth
 */
public class TitleAPI {
    static InternalTitleAPI internalApi = InternalAPIMapping.create();

    /** @deprecated */
    @Deprecated
    public static void sendTitle(Player player, int fadeIn, int stay, int fadeOut, String message) {
        sendTitle(player, fadeIn, stay, fadeOut, message, null);
    }

    /** @deprecated */
    @Deprecated
    public static void sendSubtitle(Player player, int fadeIn, int stay, int fadeOut, String message) {
        sendTitle(player, fadeIn, stay, fadeOut, null, message);
    }

    /** @deprecated */
    @Deprecated
    public static void sendFullTitle(Player player, int fadeIn, int stay, int fadeOut, String title, String subtitle) {
        sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
    }

    /**
     * Sends a title to the {@link Player}
     *
     * @param player The {@link Player} to send the title to
     * @param fadeIn The time in ticks for the title to fade in
     * @param stay The time in ticks for the title to stay on screen
     * @param fadeOut The time in ticks for the title to fade out
     * @param title The title text
     * @param subtitle The subtitle text
     */
    public static void sendTitle(Player player, int fadeIn, int stay, int fadeOut, String title, String subtitle) {
        sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);
    }

    /**
     * Sends a title to the {@link Player}
     *
     * @param player The {@link Player} to send the title to
     * @param title The title text
     * @param subtitle The subtitle text
     * @param fadeIn The time in ticks for the title to fade in
     * @param stay The time in ticks for the title to stay on screen
     * @param fadeOut The time in ticks for the title to fade out
     */
    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {


        InternalTitleAPI internalApi = getInternalApi().orElseThrow(() -> new RuntimeException("No internal API, Unsupported version?"));


        internalApi.sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);
    }

    /**
     * Sends an empty title to the {@link Player}
     *
     * @param player the {@link Player} to send the empty title to
     */
    public static void clearTitle(Player player) {

        InternalTitleAPI internalApi = getInternalApi().orElseThrow(() -> new RuntimeException("No internal API, Unsupported version?"));
        internalApi.clearTitle(player);
    }

    static Optional<InternalTitleAPI> getInternalApi() {
        return Optional.ofNullable(internalApi);
    }
}

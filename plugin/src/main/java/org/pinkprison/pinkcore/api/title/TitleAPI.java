package org.pinkprison.pinkcore.api.title;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.pinkprison.pinkcore.api.utils.NMSUtils;
import org.pinkprison.pinkcore.api.utils.PacketUtils;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * Title API
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author WildTooth, PandaPeter
 * @since 2.0.0
 */
public class TitleAPI {

    /**
     * Sends a title to a {@link Player}.
     * @apiNote This method should not be confused with
     * {@link TitleAPI#sendTitle(Player, String, String, int, int, int)}.
     * This method is used to send only a title to a player, not a subtitle.
     *
     * @param player  The player to send the title to.
     * @param title The title to send.
     * @param fadeIn  The time in ticks for the title to fade in.
     * @param stay    The time in ticks for the title to stay on screen.
     * @param fadeOut The time in ticks for the title to fade out.
     *
     * @see TitleAPI#sendTitle(Player, String, String, int, int, int)
     */
    public static void sendTitle(Player player, String title, int fadeIn, int stay, int fadeOut) {
        sendTitle(player, title, "", fadeIn, stay, fadeOut);
    }

    /**
     * Sends a subtitle to a {@link Player}.
     *
     * @param player  The player to send the subtitle to.
     * @param subtitle The subtitle to send.
     * @param fadeIn  The time in ticks for the subtitle to fade in.
     * @param stay    The time in ticks for the subtitle to stay on screen.
     * @param fadeOut The time in ticks for the subtitle to fade out.
     *
     * @see TitleAPI#sendTitle(Player, String, String, int, int, int)
     */
    public static void sendSubtitle(Player player, String subtitle, int fadeIn, int stay, int fadeOut) {
        sendTitle(player, "", subtitle, fadeIn, stay, fadeOut);
    }

    /**
     * Sends a title to a {@link Player}.
     *
     * @apiNote Title cannot be null
     *
     * @param player   The player to send the title to.
     * @param title    The title to send. Cannot be null.
     * @param subtitle The subtitle to send.
     * @param fadeIn   The time in ticks for the title to fade in.
     * @param stay     The time in ticks for the title to stay on screen.
     * @param fadeOut  The time in ticks for the title to fade out.
     */
    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        try {
            Constructor<?> subtitleConstructor = Objects.requireNonNull(NMSUtils.getNMSClass("PacketPlayOutTitle")).getConstructor(Objects.requireNonNull(NMSUtils.getNMSClass("PacketPlayOutTitle")).getDeclaredClasses()[0], NMSUtils.getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);;

            if (title != null) {
                Object chatTitle = Objects.requireNonNull(NMSUtils.getNMSClass("IChatBaseComponent")).getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + title + "\"}");
                Object titlePacketField = Objects.requireNonNull(NMSUtils.getNMSClass("PacketPlayOutTitle")).getDeclaredClasses()[0].getField("TITLE").get(null);
                Object titlePacket = subtitleConstructor.newInstance(titlePacketField, chatTitle, fadeIn, stay, fadeOut);
                PacketUtils.sendPacket(player, titlePacket);
            }

            if (subtitle != null) {
                Object chatSubtitle = Objects.requireNonNull(NMSUtils.getNMSClass("IChatBaseComponent")).getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + subtitle + "\"}");
                Object subtitlePacketField = Objects.requireNonNull(NMSUtils.getNMSClass("PacketPlayOutTitle")).getDeclaredClasses()[0].getField("SUBTITLE").get(null);
                Object subtitlePacket = subtitleConstructor.newInstance(subtitlePacketField, chatSubtitle, fadeIn, stay, fadeOut);
                PacketUtils.sendPacket(player, subtitlePacket);
            }
        } catch (Exception ignored) { }
    }

    /**
     * Sends an empty title to a {@link Player}.
     *
     * @param player The {@link Player} to send the empty title to.
     */
    public static void clearTitle(Player player) {
        sendTitle(player, "", "", 0, 0, 0);
    }
}
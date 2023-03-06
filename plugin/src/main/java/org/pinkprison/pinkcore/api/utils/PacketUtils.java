package org.pinkprison.pinkcore.api.utils;

import org.bukkit.entity.Player;

/**
 * Utility class for Packets.
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author PandaPeter
 * @since 2.0.0
 */
public class PacketUtils {

    /**
     * You cannot instantiate this class
     *
     * @apiNote You cannot instantiate this class
     */
    private PacketUtils() {
        throw new IllegalStateException("Utility class, cannot be instantiated");
    }

    /**
     * Method to send a packet to a {@link Player}.
     *
     * @param player The {@link Player} to send the packet to
     * @param packet to send
     */
    public static void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", NMSUtils.getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception ignored) { }
    }
}
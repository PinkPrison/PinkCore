package org.pinkprison.pinkcore.api.actionbar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.pinkprison.pinkcore.PinkCore;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * ActionBar API
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author WildTooth, PandaPeter
 * @since 1.0.0
 */
public class ActionBarAPI {

    private static final JavaPlugin plugin;
    private static String nmsVersion;

    static {
        plugin = PinkCore.getInstance();
        nmsVersion = Bukkit.getServer().getClass().getPackage().getName();
        nmsVersion = nmsVersion.substring(nmsVersion.lastIndexOf(".") + 1);
    }

    /**
     * Send an actionbar message to a player
     *
     * @param player Player to send the message to
     *               (if online)
     * @param message Message to send
     */
    public static void sendActionBar(Player player, String message) {
        if (!player.isOnline()) {
            return; // Player may have logged out
        }

        try {
            Class<?> craftPlayerClass = Class.forName("org.bukkit.craftbukkit." + nmsVersion + ".entity.CraftPlayer");
            Object craftPlayer = craftPlayerClass.cast(player);
            Object packet;
            Class<?> packetPlayOutChatClass = getNMSClass("PacketPlayOutChat");
            Class<?> chatComponentTextClass = getNMSClass("ChatComponentText");
            Class<?> iChatBaseComponentClass = getNMSClass("IChatBaseComponent");
            Class<?> chatMessageTypeClass = getNMSClass("ChatMessageType");
            if (chatMessageTypeClass != null) {
                Object[] chatMessageTypes = chatMessageTypeClass.getEnumConstants();
                Object chatMessageType = null;
                for (Object obj : chatMessageTypes) {
                    if (obj.toString().equals("GAME_INFO")) {
                        chatMessageType = obj;
                    }
                }
                Object chatComponentText = Objects.requireNonNull(chatComponentTextClass).getConstructor(new Class<?>[]{String.class}).newInstance(message);
                packet = Objects.requireNonNull(packetPlayOutChatClass).getConstructor(new Class<?>[]{iChatBaseComponentClass, chatMessageTypeClass}).newInstance(chatComponentText, chatMessageType);
            } else {
                Object chatComponentText = Objects.requireNonNull(chatComponentTextClass).getConstructor(new Class<?>[]{String.class}).newInstance(message);
                packet = Objects.requireNonNull(packetPlayOutChatClass).getConstructor(new Class<?>[]{iChatBaseComponentClass, byte.class}).newInstance(chatComponentText, (byte) 2);
            }

            Method craftPlayerHandleMethod = craftPlayerClass.getDeclaredMethod("getHandle");
            Object craftPlayerHandle = craftPlayerHandleMethod.invoke(craftPlayer);
            Field playerConnectionField = craftPlayerHandle.getClass().getDeclaredField("playerConnection");
            Object playerConnection = playerConnectionField.get(craftPlayerHandle);
            Method sendPacketMethod = playerConnection.getClass().getDeclaredMethod("sendPacket", getNMSClass("Packet"));
            sendPacketMethod.invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Send an actionbar message to a player for a set amount of time
     *
     * @param player Player to send the message to
     *               (if online)
     * @param message Message to send
     * @param duration Duration to send the message for
     *                 (in ticks)
     */
    public static void sendActionBar(final Player player, final String message, int duration) {
        sendActionBar(player, message);

        if (duration >= 0) {
            // Sends empty message at the end of the duration. Allow messages shorter than 3 seconds, ensures precision.
            new BukkitRunnable() {
                @Override
                public void run() {
                    sendActionBar(player, "");
                }
            }.runTaskLater(plugin, duration + 1);
        }

        // Re-sends the messages every 3 seconds, so it doesn't go away from the player's screen.
        while (duration > 40) {
            duration -= 40;
            new BukkitRunnable() {
                @Override
                public void run() {
                    sendActionBar(player, message);
                }
            }.runTaskLater(plugin, duration);
        }
    }

    /**
     * Send an actionbar message to all players
     *
     * @param message Message to send
     */
    public static void sendActionBarToAllPlayers(String message) {
        sendActionBarToAllPlayers(message, -1);
    }

    /**
     * Send an actionbar message to all players for a set amount of time
     *
     * @param message Message to send
     * @param duration Duration to send the message for
     *                 (in ticks)
     */
    public static void sendActionBarToAllPlayers(String message, int duration) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            sendActionBar(p, message, duration);
        }
    }

    /**
     * Gets a NMS class by name.
     *
     * @param name The name of the class.
     * @return The class.
     */
    private static Class<?> getNMSClass(String name) {
        try {
            return Class.forName("net.minecraft.server." + nmsVersion + "." + name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
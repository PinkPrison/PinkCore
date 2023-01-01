package org.pinkprison.pinkcore.core.actionbar;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBarAPI {
    private final PacketPlayOutChat packet;

    public ActionBarAPI(String text) {
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + text + "\"}"), (byte) 2);
        this.packet = packet;
    }

    /**
     * Send the action bar to a single {@link Player}
     * @param p The player to send the action bar to
     */
    public void sendToPlayer(final Player p) {
        if (!p.isOnline()) return;
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
    }


    /**
     * Send a new actionbar to a player without the need to create a new instance of the class
     * @param p The player to send the action bar to
     * @param message The text to send
     */
    public static void sendToPlayer(final Player p, final String message) {
        ActionBarAPI bar = new ActionBarAPI(message);
        bar.sendToPlayer(p);
    }


    /**
     * Send the action bar to all online {@link Player}s
     */
    public void sendToAll() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);;
        }
    }

    /**
     * Send a new actionbar to all online {@link Player}s without the need to create a new instance of the class
     * @param message The text to send
     */
    public static void sendToAll(final String message) {
        ActionBarAPI bar = new ActionBarAPI(message);
        bar.sendToAll();
    }

}

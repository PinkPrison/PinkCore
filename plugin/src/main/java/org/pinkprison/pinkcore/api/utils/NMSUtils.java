package org.pinkprison.pinkcore.api.utils;

import org.bukkit.Bukkit;

/**
 * Utility class for net.minecraft.server classes.
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author PandaPeter
 * @since 2.0.0
 */
public class NMSUtils {

    private static String nmsVersion;

    static {
        nmsVersion = Bukkit.getServer().getClass().getPackage().getName();
        nmsVersion = nmsVersion.substring(nmsVersion.lastIndexOf(".") + 1);
    }

    /**
     * You cannot instantiate this class
     *
     * @apiNote You cannot instantiate this class
     */
    private NMSUtils() {
        throw new IllegalStateException("Utility class, cannot be instantiated");
    }

    /**
     * Gets a NMS class by name.
     *
     * @param name The name of the class.
     * @return The class.
     */
    public static Class<?> getNMSClass(String name) {
        try {
            return Class.forName("net.minecraft.server." + nmsVersion + "." + name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
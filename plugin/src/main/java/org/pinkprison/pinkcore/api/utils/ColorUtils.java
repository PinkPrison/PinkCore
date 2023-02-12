package org.pinkprison.pinkcore.api.utils;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Utility class for translating strings with color codes
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author WildTooth
 * @since 1.0.0
 */
public class ColorUtils {

    private ColorUtils() {
        throw new IllegalStateException("Utility class, cannot be instantiated");
    }

    /**
     * Translates a string using an alternate color code character into
     * an array of Strings that uses the internal {@link ChatColor}.COLOR_CODE
     *
     * @param stringList The string(s) to translate
     * @return The translated string(s)
     */
    public static String[] color(String... stringList){
        if (stringList == null) {
            return null;
        }

        for (int i = 0; i < stringList.length; i++) {
            stringList[i] = color(stringList[i]);
        }

        return stringList;
    }

    /**
     * Translates a list of strings using an alternate color code character
     * into a list of Strings that uses the internal {@link ChatColor}.COLOR_CODE
     *
     * @param stringList The string(s) to translate
     * @return The translated string(s)
     */
    public static List<String> color(List<String> stringList){
        if (stringList == null) {
            return null;
        }

        stringList.replaceAll(ColorUtils::color);
        return stringList;
    }

    /**
     * Translates a string using an alternate color code character
     * into a string that uses the internal {@link ChatColor}.COLOR_CODE
     *
     * @param string The string to translate
     * @return The translated string
     */
    @Contract("_ -> new")
    public static @NotNull String color(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
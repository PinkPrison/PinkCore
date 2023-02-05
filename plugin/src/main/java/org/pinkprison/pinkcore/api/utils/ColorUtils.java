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
 */
public class ColorUtils {

    private ColorUtils() {
        throw new IllegalStateException("Utility class, cannot be instantiated");
    }

    /** @deprecated Use {@link #colorize(String...)} instead */
    @Deprecated
    public static String[] getColored(String... stringList){
        if(stringList == null) return null;
        for(int i = 0; i< stringList.length;i++)
            stringList[i] = getColored(stringList[i]);
        return stringList;
    }

    /** @deprecated Use {@link #colorize(List)} instead */
    @Deprecated
    public static List<String> getColored(List<String> stringList){
        if(stringList == null) return null;
        for(int i = 0; i< stringList.size();i++)
            stringList.set(i, getColored(stringList.get(i)));
        return stringList;
    }

    /** @deprecated Use {@link #colorize(String)} instead */
    @Deprecated
    public static String getColored(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    /**
     * Translates a string using an alternate color code character into
     * an array of Strings that uses the internal {@link ChatColor}.COLOR_CODE
     *
     * @param stringList The string(s) to translate
     * @return The translated string(s)
     */
    @Deprecated
    public static String[] colorize(String... stringList){
        if(stringList == null) return null;
        for(int i = 0; i< stringList.length;i++)
            stringList[i] = colorize(stringList[i]);
        return stringList;
    }

    /**
     * Translates a list of strings using an alternate color code character
     * into a list of Strings that uses the internal {@link ChatColor}.COLOR_CODE
     *
     * @param stringList The string(s) to translate
     * @return The translated string(s)
     */
    @Deprecated
    public static List<String> colorize(List<String> stringList){
        if(stringList == null) return null;
        for(int i = 0; i< stringList.size();i++)
            stringList.set(i, colorize(stringList.get(i)));
        return stringList;
    }

    /**
     * Translates a string using an alternate color code character
     * into a string that uses the internal {@link ChatColor}.COLOR_CODE
     *
     * @param s The string to translate
     * @return The translated string
     */
    @Deprecated
    @Contract("_ -> new")
    public static @NotNull String colorize(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    /**
     * Translates a string using an alternate color code character into
     * an array of Strings that uses the internal {@link ChatColor}.COLOR_CODE
     *
     * @param stringList The string(s) to translate
     * @return The translated string(s)
     */
    public static String[] color(String... stringList){
        if (stringList == null) return null;
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
        if (stringList == null) return null;
        stringList.replaceAll(ColorUtils::color);
        return stringList;
    }

    /**
     * Translates a string using an alternate color code character
     * into a string that uses the internal {@link ChatColor}.COLOR_CODE
     *
     * @param s The string to translate
     * @return The translated string
     */
    @Contract("_ -> new")
    public static @NotNull String color(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
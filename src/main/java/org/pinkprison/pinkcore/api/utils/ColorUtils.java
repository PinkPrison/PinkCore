package org.pinkprison.pinkcore.api.utils;

import org.bukkit.ChatColor;

import java.util.List;

public class ColorUtils {
    /**
     * Translates a string using an alternate color code character into an array of Strings that uses the internal {@link ChatColor}.COLOR_CODE
     * @param stringList The string(s) to translate
     *
     * @return The translated string(s)
     */
    public static String[] getColored(String... stringList){
        if(stringList == null) return null;
        for(int i = 0; i< stringList.length;i++)
            stringList[i] = getColored(stringList[i]);
        return stringList;
    }

    /**
     * Translates a list of strings using an alternate color code character into a list of Strings that uses the internal {@link ChatColor}.COLOR_CODE
     * @param stringList
     * @return
     */
    public static List<String> getColored(List<String> stringList){
        if(stringList == null) return null;
        for(int i = 0; i< stringList.size();i++)
            stringList.set(i, getColored(stringList.get(i)));
        return stringList;
    }

    /**
     * translates a string using an alternate color code character into a string that uses the internal {@link ChatColor}.COLOR_CODE
     * @param s The string to translate
     * @return
     */
    public static String getColored(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}

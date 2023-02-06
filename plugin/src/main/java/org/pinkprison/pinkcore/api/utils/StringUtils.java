package org.pinkprison.pinkcore.api.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for formatting lists as strings.
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author WildTooth
 * @since 1.0.0
 */
public class StringUtils {

    /**
     * Returns a formatted String from a List of Strings.
     * if the list is empty, it will return a predetermined string.
     *
     * @param strings list of strings to format
     * @param placeholder the placeholder to replace
     *                    if the list is not empty
     * @return formatted string
     */
    public static String formatList(List<String> strings, String placeholder){
        if(strings.isEmpty()) return placeholder;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            if (i != 0)
                sb.append(i == strings.size() - 1 ? " " + "og" + " " : ", ");
            sb.append(strings.get(i));
        }
        return sb.toString();
    }

    /**
     *
     * Returns a boolean value from a string.
     *
     * @param paramString the string to check
     * @param paramStrings the strings to check against
     *
     * @return if the string ends with any of the strings
     */
    public static boolean endsWith(String paramString, String... paramStrings){
        for(String s : paramStrings)
            if(paramString.substring(paramString.length()-s.length()).equalsIgnoreCase(s))
                return true;
        return false;
    }

    /**
     * Returns a boolean value from weather or not a string is a number.
     *
     * @param s the string to check
     * @return if the string is a number
     */

    public static boolean isAlphabetical(String s) {
        if (s == null) return false;
        int len = s.length();
        for (int i = 0; i < len; i++)
            if (!Character.isLetter(s.charAt(i))) return false;
        return true;
    }

    public static String getCapitalized(String value){
        List<String> list = new ArrayList<>();
        for(String s : value.split(" "))
            list.add(s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase());
        return String.join(" ", list);
    }
}

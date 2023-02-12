package org.pinkprison.pinkcore.api.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A Class to help format time.
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author WildTooth
 * @since 2.0.0
 */
public class TimeUtils {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Current unix in seconds.
     *
     * @return long value of current unix in seconds
     */
    public static long currentUnixInSeconds(){
        return System.currentTimeMillis() / 1000;
    }

    /**
     * Get formatted time string.
     *
     * @param unixTime the unix timestamp
     * @return the formatted time string
     */
    public static String getFormattedTime(long unixTime){
        return getFormattedTime((int) unixTime, true);
    }

    /**
     * Get formatted time string.
     *
     * @param unixTime the unix timestamp
     * @param withSeconds with or without seconds
     * @return the formatted time string
     */
    public static String getFormattedTime(long unixTime, boolean withSeconds){
        return getFormattedTime((int) unixTime, withSeconds);
    }

    /**
     * Get formatted time string.
     *
     * @param unixTimeInSeconds the unix timestamp in seconds
     * @return the formatted time string
     */
    public static String getFormattedTime(int unixTimeInSeconds){
        return getFormattedTime(unixTimeInSeconds, true);
    }

    /**
     * Get formatted time string.
     *
     * @param unixTimeInSeconds the unix timestamp in seconds
     * @param withSeconds with or without seconds
     * @return the formatted time string
     */
    public static String getFormattedTime(int unixTimeInSeconds, boolean withSeconds){
        if (unixTimeInSeconds <= 0) {
            return "0 sekunder";
        }

        int days = (int) Math.floor( unixTimeInSeconds / 86400 );
        int hours = (int) Math.floor( ( unixTimeInSeconds / 3600 ) - ( days * 24 ) );
        int minutes = (int) Math.floor( ( unixTimeInSeconds / 60 ) - ( ( hours + ( days * 24 ) ) * 60 ) );
        int seconds = (int) Math.floor( unixTimeInSeconds - ( ( days * 86400 ) + ( hours * 3600 ) + ( minutes * 60 ) ) );
        List<String> stringList = new ArrayList<>();
        if (days > 0) {
            stringList.add(days + " " + (days == 1 ? "dag" : "dage"));
        }

        if (hours > 0) {
            stringList.add(hours + " " + (hours == 1 ? "time" : "timer"));
        }

        if (minutes > 0) {
            stringList.add(minutes + " " + (minutes == 1 ? "minut" : "minutter"));
        }

        if (withSeconds && seconds > 0) {
            stringList.add(seconds + " " + (seconds == 1 ? "sekund" : "sekunder"));
        } else if ((days + hours + minutes) <= 0 && seconds > 0) {
            stringList.add(seconds + " " + (seconds == 1 ? "sekund" : "sekunder"));
        }

        return StringUtils.formatList(stringList, "");
    }

    /**
     * Get a String by formatting a timestamp as a date.
     *
     * @param paramLong the param long
     * @return the string
     */
    public static String formatTimestampAsDate(long paramLong){
        return SIMPLE_DATE_FORMAT.format(paramLong);
    }
}
package com.example.project3;

/**
 This is the Time enum class, which specifies the
 two possible times for the purposes of this software.
 This class also supports converting a Time object to a
 String for readability.
 @author Someshwar Ramesh Babu, Amrinderpal Akal
 */

public enum Time {
    Morning (9, 30),
    Afternoon(14, 00),

    Evening(18, 30);

    private final int hour;
    private final int minute;

    /**
     Initializes a Time object with given hour and minute
     * @param hour - Hour
     * @param minute - Minute
     */
    Time (int hour, int minute)
    {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     This method converts a Time object to a String representation ("HH:MM").
     * @return String representation of Time object
     */
    @Override
    public String toString()
    {
        String hourStr = Integer.toString(hour);
        if (minute == 0)
        {
            String minuteStr = Integer.toString(minute);
            minuteStr = minuteStr + "0";
            String result = hourStr + ":" + minuteStr;
            return result;
        }
        else {
            String minuteStr = Integer.toString(minute);
            String result = hourStr + ":" + minuteStr;
            return result;
        }
    }
}

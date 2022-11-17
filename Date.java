package com.example.project3;
import java.util.Calendar;
/**
 This is the Date class, which supports creating a
 new Date object, comparing Date objects, and checking
 whether a Date object is a valid date.
 @author Someshwar Ramesh Babu, Amrinderpal Akal
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int FEBRUARY = 2;
    public static final int APRIL = 4;
    public static final int JUNE = 6;
    public static final int SEPTEMBER = 9;
    public static final int NOVEMBER = 11;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTERCENTENNIAL = 400;
    public static final int INCREMENT = 1;
    public static final int MODZERO = 0;

    public static final int AGECHECK = 18;

    public static final int MAXDAYS = 31;

    public static final int MAXMONTH = 12;

    public static final int THREEMONTHS = 3;

    /**
     Creates a Date object with today's date
     */
    public Date() {
        Calendar today = Calendar.getInstance();
        year = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH) + INCREMENT;
        day = today.get(Calendar.DAY_OF_MONTH);
    }

    /**
     Creates a Date object given the year, month, and day as integers
     * @param year - Year
     * @param month - Month
     * @param day - Day
     */
    public Date (int year, int month, int day)
    {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     Checks if given year is a leap year
     * @param year to check for
     * @return true if leap year, false otherwise
     */
    private boolean isLeapYear(int year)
    {
        if (year % QUADRENNIAL == MODZERO)
        {
            if (year % CENTENNIAL == MODZERO)
            {
                if (year % QUARTERCENTENNIAL == MODZERO)
                {
                    return true;
                }
            }
            else {
                return true;
            }
        }
        return false;
    }

    /**
     Returns the year of a date object
     * @return year
     */
    public int getYear()
    {
        return year;
    }

    /**
     Returns the month of a date object
     * @return month
     */
    public int getMonth()
    {
        return month;
    }

    /**
     Returns the day of a date object
     * @return day
     */
    public int getDay()
    {
        return day;
    }

    /**
     Creates a Date object with a String representation of a date ("MM/DD/YYYY")
     * @param date - String representation of a date
     */
    public Date(String date) //take “mm/dd/yyyy” and create a classes.Date object
    {
        String[] datesplit = date.split("/");
        int month1 = Integer.parseInt(datesplit[0]);
        int day1 = Integer.parseInt(datesplit[1]);
        int year1 = Integer.parseInt(datesplit[2]);
        this.month = month1;
        this.day = day1;
        this.year = year1;
    }

    /**
     Checks if Date object is 18 years or older from today's date
     * @return true if 18 years or older, false otherwise
     */
    public boolean check18()
    {
        Date today = new Date();
        Date eighteenYearsAgo = new Date(today.getYear()-AGECHECK, today.getMonth(), today.getDay());
        if (year > eighteenYearsAgo.getYear())
        {
            return false;
        }
        else if (year == eighteenYearsAgo.getYear())
        {
            if (month > eighteenYearsAgo.getMonth())
            {
                return false;
            }
            else if (month == eighteenYearsAgo.getMonth() && day > eighteenYearsAgo.getDay())
            {
                return false;
            }
        }
        return true;
    }

    /**
     Converts a Date object to a String in the form of "MM/DD/YYYY"
     * @return String representation of Date
     */
    public String dateToString()
    {
        String yearStr = Integer.toString(year);
        String monthStr = Integer.toString(month);
        String dayStr = Integer.toString(day);
        String res = monthStr + "/" + dayStr + "/" + yearStr;
        return res;
    }

    /**
     Compares Date object with given Date object
     * @param date the object to be compared.
     * @return 0 if both dates are equal, 1 if current date is later than given date, -1 if current date is earlier than given date
     */
    @Override
    public int compareTo(Date date)
    {
       int y1 = date.getYear();
       int m1 = date.getMonth();
       int d1 = date.getDay();
       if (year == y1 && month == m1 && day == d1)
       {
           return 0;
       }
       else if (year > y1)
       {
           return 1;
       }
       else if (year < y1)
       {
           return -1;
       }
       else if (month > m1)
       {
           return 1;
       }
       else if (month < m1)
       {
           return -1;
       }
       else if (day > d1)
       {
           return 1;
       }
       return -1;
    }

    /**
     Checks if a Date object is a valid date
     * @return true if valid, false otherwise
     */
    public boolean isValid() {
        if (year < MODZERO)
        {
            return false;
        }
        else if (day < INCREMENT)
        {
            return false;
        }
        else if (day > MAXDAYS)
        {
            return false;
        }
       else if (month < INCREMENT || month > MAXMONTH) {
            return false;
        }
        else if ((month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) && (day > 30))
        {
            return false;
        }
        else if (month == FEBRUARY && !(isLeapYear(year)) && day > 28)
        {
            return false;
        }
        return true;
    }

    /**
     Returns a date three months from today's date
     * @return Date three months later
     */
    public Date threeMonths ()
    {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.MONTH, THREEMONTHS);
        Date res = new Date(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + INCREMENT,  today.get(Calendar.DAY_OF_MONTH));
        return res;
    }

    /**
     Returns a date one year from today's date
     * @return Date one year later
     */
    public Date oneYear ()
    {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.YEAR, INCREMENT);
        Date res = new Date(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + INCREMENT,  today.get(Calendar.DAY_OF_MONTH));
        return res;
    }

}

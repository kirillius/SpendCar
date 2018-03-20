package ru.kirillius.spendcar.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Lavrentev on 20.03.2018.
 */

public class DateHelper {
    public static Calendar getDateFromString(String string, String symbol, String time) {
        Calendar calendar = Calendar.getInstance();
        if(string!=null) {
            String[] arrayFromString = string.split(symbol);
            if (arrayFromString.length > 0) {
                calendar.set(Calendar.DATE, Integer.valueOf(arrayFromString[2]));
                calendar.set(Calendar.MONTH, Integer.valueOf(arrayFromString[1]));
                calendar.set(Calendar.YEAR, Integer.valueOf(arrayFromString[0]));

                if (time != null) {
                    String[] arrayTime = time.split(":");

                    if (arrayTime.length > 0) {
                        calendar.set(Calendar.HOUR, Integer.valueOf(arrayTime[0]));
                        calendar.set(Calendar.MINUTE, Integer.valueOf(arrayTime[1]));
                    }
                }

                return calendar;
            }
        }
        return null;
    }

    public static String getStringFromDate(Calendar date) {
        if(date!=null) {
            String day = String.valueOf(date.get(Calendar.DATE));
            String month = String.valueOf(date.get(Calendar.MONTH));

            if (day.length() == 1)
                day = '0' + day;

            if (month.length() == 1)
                month = '0' + month;

            return day + "." + month + "." + date.get(Calendar.YEAR);
        }

        return "";
    }

    public static String getFormattingDate(Date date, String format) {
        if(date==null)
            return "";

        String defaultFormat = "yyyy-MM-dd";
        SimpleDateFormat formatter=new SimpleDateFormat((format!=null) ? format : defaultFormat, new Locale("ru"));
        String currentDate=formatter.format(date);
        return currentDate;
    }

    public static Calendar getDateFromFormattingString(String date, String format) {
        Calendar calendar = Calendar.getInstance();
        String defaultFormat = "yyyy-MM-dd";
        SimpleDateFormat formatter=new SimpleDateFormat((format!=null) ? format : defaultFormat);

        try {
            calendar.setTime(formatter.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return calendar;
    }

    public static String getDayFromDate(Calendar date) {
        return String.valueOf(date.get(Calendar.DATE));
    }

    public static Date getDateFromString(String date, String format) {
        String defaultFormat = "yyyy-MM-dd";
        SimpleDateFormat formatter=new SimpleDateFormat((format!=null) ? format : defaultFormat);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int getNumberDayOfWeek(Calendar calendar) {
        if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
            return 8;
        else
            return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static Calendar getStartWeek(Calendar date) {
        date.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        date.clear(Calendar.MINUTE);
        date.clear(Calendar.SECOND);
        date.clear(Calendar.MILLISECOND);

        date.setFirstDayOfWeek(Calendar.MONDAY);
        date.set(Calendar.DAY_OF_WEEK, date.getFirstDayOfWeek());

        return date;
    }
}

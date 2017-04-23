package com.melolchik.common.util;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Olga Melekhina on 09.08.2016.
 */
public class TimeUtil {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_IOS = "yyyy/MM/dd";
    public static final String TIME_FORMAT = "HH:mm:ss";

    public static final String DATE_FORMAT_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static int getCurrentYear(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Get day of week int.
     *
     * @return the int
     */
    public static int getCurrentDayOfWeek(){
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        dayOfWeek--;
        if(dayOfWeek == 0) dayOfWeek = 7;
        return dayOfWeek;
    }

    public static Date convertFromIso8601(String date) {
        if(TextUtils.isEmpty(date)) return new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_ISO8601);
//        formatter.setTimeZone(Calendar.getInstance().getTimeZone());
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
//            throw new ParseException(e.getMessage() + "\n text: " + date, 0);
            return new Date();
        }
    }

    public static Date convertFromStringByFormat(String date,String format) {
        if(TextUtils.isEmpty(date)) return null;
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
//            throw new ParseException(e.getMessage() + "\n text: " + date, 0);
            return null;
        }
    }

    public static String convertDate(Date date,final String format) {
        if (date == null)
            return "";
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
        return formatter.format(date);
    }

    protected static String getWeekDay(int number,boolean isFull){
        String[] weekDays = {
                "Sun",
                "Mon",
                "Tue",
                "Wed",
                "Thu",
                "Fri",
                "Sat"
        };

        String[] weekDaysFull = {
                "Sunday",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday"
        };
        if(isFull) return weekDaysFull[number];
        else return weekDays[number];
    }

    public static String formatToHumanReadable(Date date, boolean weekDayFull) {
        if (date == null) {
            return "";
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        final int YEAR_CURRENT_DATE = calendar.get(Calendar.YEAR);
        final int MONTH_CURRENT_DATE = calendar.get(Calendar.MONTH);
        final int DAY_CURRENT_DATE = calendar.get(Calendar.DAY_OF_MONTH);


        calendar.setTime(date);
        final int YEAR_DATE = calendar.get(Calendar.YEAR);
        final int MONTH_DATE = calendar.get(Calendar.MONTH);
        final int DAY_DATE = calendar.get(Calendar.DAY_OF_MONTH);


        // prepare the time
        SimpleDateFormat resultFormat = new SimpleDateFormat(/*"HH:mm"*/"h:mm a",Locale.getDefault());
        String resultDate = resultFormat.format(date);

        String[] months = {
                "Jan.",
                "Feb.",
                "Mar.",
                "Apr.",
                "May",
                "Jun.",
                "Jul.",
                "Aug.",
                "Sept.",
                "Oct.",
                "Nov.",
                "Dec."
        };




        int dayOfWeek = (calendar.get(Calendar.DAY_OF_WEEK) - 1);

        // this year
        if (YEAR_CURRENT_DATE == YEAR_DATE) {
            // this month
            if (MONTH_CURRENT_DATE == MONTH_DATE) {
                // today
                if (DAY_CURRENT_DATE == DAY_DATE) {
                    String result = "Today, " + resultDate;
                    return result;
                }
                // Yesterday
                if (DAY_CURRENT_DATE - DAY_DATE == 1) {
                    String result = "Yesterday, " + resultDate;
                    return result;
                }
                ///week day
                if ((DAY_CURRENT_DATE - DAY_DATE) <= 7) {
                    String result = String.format("%s, %s",
                            getWeekDay(dayOfWeek,weekDayFull),
                            resultDate
                    );
                    return result;
                }
                ///month
                @SuppressLint("DefaultLocale")
                String result = String.format("%s, %d %s, %s",
                        getWeekDay(dayOfWeek,weekDayFull),
                        DAY_DATE,
                        months[calendar.get(Calendar.MONTH)],
                        resultDate
                );
                return result;

            }
            else {
                ///month
                @SuppressLint("DefaultLocale")
                String result = String.format("%s, %d %s, %s",
                        getWeekDay(dayOfWeek,weekDayFull),
                        DAY_DATE,
                        months[calendar.get(Calendar.MONTH)],
                        resultDate
                );
                return result;
            }
        }


        @SuppressLint("DefaultLocale")
        String result = String.format("%s, %s, %d %s, %s",
                YEAR_DATE,
                getWeekDay(dayOfWeek,weekDayFull),
                DAY_DATE,
                months[calendar.get(Calendar.MONTH)],
                resultDate
        );
        return result;
    }


}

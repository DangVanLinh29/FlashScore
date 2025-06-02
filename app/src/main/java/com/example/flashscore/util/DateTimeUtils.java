package com.example.flashscore.util; // Hoặc com.example.flashscoreapp.util

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import android.util.Log;

public class DateTimeUtils {

    private static final SimpleDateFormat apiDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
    private static final SimpleDateFormat apiDateTimeFormatAlternative = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
    private static final SimpleDateFormat displayTimeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private static final SimpleDateFormat displayDateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());
    private static final SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    static {
        apiDateTimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        apiDateTimeFormatAlternative.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static String formatTime(String dateTimeUtc) {
        if (dateTimeUtc == null || dateTimeUtc.isEmpty()) {
            return "--:--";
        }
        try {
            Date date;
            if (dateTimeUtc.endsWith("Z")) {
                date = apiDateTimeFormatAlternative.parse(dateTimeUtc);
            } else if (dateTimeUtc.matches(".*[+-]\\d{2}:\\d{2}$")) {
                String parsableDateTime = dateTimeUtc.replaceAll("([+-]\\d{2}):(\\d{2})$", "$1$2");
                date = apiDateTimeFormat.parse(parsableDateTime);
            } else {
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(dateTimeUtc); // Thử định dạng khác nếu API trả về khác
            }
            displayTimeFormat.setTimeZone(TimeZone.getDefault());
            return displayTimeFormat.format(date);
        } catch (ParseException e) {
            Log.e("DateTimeUtils", "Error parsing time: " + dateTimeUtc, e);
            return "--:--";
        }
    }

    public static String formatDateForDisplay(String dateStringYYYYMMDD) {
        if (dateStringYYYYMMDD == null || dateStringYYYYMMDD.isEmpty()) {
            return "Invalid Date";
        }
        try {
            Date date = inputDateFormat.parse(dateStringYYYYMMDD);
            displayDateFormat.setTimeZone(TimeZone.getDefault());
            return displayDateFormat.format(date);
        } catch (ParseException e) {
            Log.e("DateTimeUtils", "Error parsing date for display: " + dateStringYYYYMMDD, e);
            return dateStringYYYYMMDD;
        }
    }
}
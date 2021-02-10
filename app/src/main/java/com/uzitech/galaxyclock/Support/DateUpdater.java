package com.uzitech.galaxyclock.Support;

import android.content.SharedPreferences;
import android.os.Handler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUpdater {

    Handler date_handler;
    Runnable date_runnable;

    public DateUpdater(SharedPreferences preferences){
        date_handler = new Handler();
        date_runnable = () -> {
            Calendar calendar = Calendar.getInstance();
            Date dateObj = calendar.getTime();

            String date = new SimpleDateFormat("EEEE,dd MMM", Locale.getDefault()).format(dateObj).toUpperCase();

            SharedPreferences.Editor editor1 = preferences.edit();
            editor1.putString("today_date", date);
            editor1.apply();

            Events.dateInfo dateInfo = new Events.dateInfo(date);
            GlobalBus.getEventBus().post(dateInfo);

            long curr_hour = calendar.get(Calendar.HOUR_OF_DAY);
            long curr_min = calendar.get(Calendar.MINUTE);
            long curr_sec = calendar.get(Calendar.SECOND);

            long delay = 86400000;
            delay -= curr_sec * (1000) + curr_min * (60000) + curr_hour * (3600000);

            date_handler.postDelayed(date_runnable, delay);
        };
        date_runnable.run();
    }
}

package com.ofal.ihsan.oppomonitoring.util.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import com.ofal.ihsan.oppomonitoring.Constant;
import java.util.Calendar;

/**
 * Created by ihsan on 11/03/16.
 */
public class AlarmManagerUtil {

    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    public void initAlarmNotification(Context context) {

        Calendar calendar = getAlarmDate();

        if (calendar == null) {
            return;
        }

        Intent myIntent = new Intent(context, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent, 0);

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    public void cancelAlarm(Context context) {
        // If the alarm has been set, cancel it.
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }

        // Disable {@code SampleBootReceiver} so that it doesn't automatically restart the
        // alarm when the device is rebooted.
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    private Calendar getAlarmDate() {
        Calendar calendar = Calendar.getInstance();

        boolean setAlarm = false;
        int hour = Constant.ALARM_HOUR_TIME[0];
        int minute = Constant.ALARM_MINUTE_TIME[0];

        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);

        for (int i = 0; i < Constant.ALARM_HOUR_TIME.length; i++) {

            if (currentHour <= Constant.ALARM_HOUR_TIME[i] && currentMinute < Constant.ALARM_MINUTE_TIME[i] && !setAlarm) {
                hour = Constant.ALARM_HOUR_TIME[i];
                minute = Constant.ALARM_MINUTE_TIME[i];
                setAlarm = true;
            } else if (i == (Constant.ALARM_HOUR_TIME.length - 1) && !setAlarm) {
                calendar.add(Calendar.DATE, 1);
                hour = Constant.ALARM_HOUR_TIME[0];
                minute = Constant.ALARM_MINUTE_TIME[0];
            }
        }

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        Log.d("MyAlarm", "Next Alarm: " + hour + ":" + minute);

        return calendar;
    }
}

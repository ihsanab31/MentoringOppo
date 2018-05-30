package com.ofal.ihsan.oppomonitoring.util.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by ihsan on 11/03/16.
 */
public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            AlarmManagerUtil cAlarm = new AlarmManagerUtil();
            cAlarm.initAlarmNotification(context);
        }
    }
}
package org.prikic.alarmmanagertest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.AlarmManagerCompat;
import android.util.Log;

import java.util.Calendar;

import static android.app.AlarmManager.RTC_WAKEUP;

public class AlarmReceiver extends BroadcastReceiver {

    private int counter = 0;
    private Handler handler = new Handler();

    private void createTask() {
        int maxCounter = 6;
        if (counter < maxCounter) {
            counter++;
            handler.postDelayed(runnable, 1000);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.d("test", "every second");
            createTask();
        }
    };

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("test", "alarm was fired");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (am == null) {
            Log.e("test", "alarm manager is null");
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        //createTask();
        AlarmManagerCompat.setExactAndAllowWhileIdle(
                am,
                RTC_WAKEUP,
                calendar.getTimeInMillis() + 60 * 1000,
                pendingIntent);
    }
}

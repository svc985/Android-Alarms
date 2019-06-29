package org.prikic.alarmmanagertest;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.Calendar;

import static org.prikic.alarmmanagertest.FgService.START_SERVICE;

public class AlarmReceiver extends BroadcastReceiver {

    //private static final int REQUEST_CODE = 1234;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("test", "alarm was fired");

        //PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE, intent, 0);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (am == null) {
            Log.e("test", "alarm manager is null");
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        startFgService(context);

//        AlarmManagerCompat.setExactAndAllowWhileIdle(
//                am,
//                RTC_WAKEUP,
//                calendar.getTimeInMillis() + 60 * 1000,
//                pendingIntent);
    }

    private void startFgService(Context context) {
        Intent intent = new Intent(context, FgService.class);
        intent.setAction(START_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }
}

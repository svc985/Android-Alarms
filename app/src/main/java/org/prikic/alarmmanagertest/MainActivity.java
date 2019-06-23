package org.prikic.alarmmanagertest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.AlarmManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

import static android.app.AlarmManager.RTC_WAKEUP;

public class MainActivity extends AppCompatActivity {

    private AlarmManager am;

    private PendingIntent pendingIntent;

    private static final int REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    }

    public void startRepeatingAlarm(View view) {
        Intent intent = new Intent(this,  AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, 0);

        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (am == null) {
            Log.e("test", "alarm manager is null");
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 2);
        calendar.set(Calendar.SECOND, 0);

        AlarmManagerCompat.setExactAndAllowWhileIdle(
                am,
                RTC_WAKEUP,
                calendar.getTimeInMillis(),
                pendingIntent);
    }

    public void cancelRepeating(View view) {
        Intent intent = new Intent(this,  AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, PendingIntent.FLAG_NO_CREATE);

        if(pendingIntent != null) {
            am.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }

    public void isActive(View view) {
        Intent intent = new Intent(this, AlarmReceiver.class);
        boolean alarmUp = (PendingIntent.getBroadcast(this, REQUEST_CODE, intent,
                PendingIntent.FLAG_NO_CREATE) != null);

        Log.d("test", "alarm is active:" + alarmUp);
    }
}

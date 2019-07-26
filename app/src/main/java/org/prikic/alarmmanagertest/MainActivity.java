package org.prikic.alarmmanagertest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.AlarmManagerCompat;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.prikic.alarmmanagertest.snackbar.ChefSnackbar;

import java.util.Calendar;

import static android.app.AlarmManager.RTC_WAKEUP;

public class MainActivity extends AppCompatActivity {

    private AlarmManager am;

    private PendingIntent pendingIntent;

    private static final int REQUEST_CODE = 1234;

    //private Button btnCancel;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //btnCancel = findViewById(R.id.button_cancel);
        constraintLayout = findViewById(R.id.constraint_layout);

        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    }

    public void startRepeatingAlarm(View view) {
        Intent intent = new Intent(this, AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, 0);

        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (am == null) {
            Log.e("test", "alarm manager is null");
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 7);
        calendar.set(Calendar.SECOND, 0);

        AlarmManagerCompat.setExactAndAllowWhileIdle(
                am,
                RTC_WAKEUP,
                calendar.getTimeInMillis(),
                pendingIntent);
    }

    public void cancelRepeating(View view) {
        Log.d("test", "inside cancelRepeating");
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, PendingIntent.FLAG_NO_CREATE);

        if (pendingIntent != null) {
            am.cancel(pendingIntent);
            pendingIntent.cancel();
            String btnName = "Btn name";
            ChefSnackbar.make(constraintLayout, btnName).show();
        }
    }

    public void isActive(View view) {
        Intent intent = new Intent(this, AlarmReceiver.class);
        boolean alarmUp = (PendingIntent.getBroadcast(this, REQUEST_CODE, intent,
                PendingIntent.FLAG_NO_CREATE) != null);

        Log.d("test", "alarm is active:" + alarmUp);
    }

    public void stopService(View view) {
        Intent intent = new Intent(this, FgService.class);
        intent.setAction(FgService.STOP_SERVICE);
        startService(intent);
    }

    public void btnClicked(View view) {
        Log.d("test", "btn clicked");
    }

    public void alertDialog(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        //alertDialog.setTitle("Alert Dialog");
        alertDialog.setMessage("This is my alert dialog");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.show();
    }
}

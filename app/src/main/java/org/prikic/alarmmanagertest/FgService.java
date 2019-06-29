package org.prikic.alarmmanagertest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class FgService extends Service {

    public static final String START_SERVICE = "start service";
    public static final String STOP_SERVICE = "stop service";

    public static final String CHANNEL_ID = "ForegroundServiceChannel";

    private CountDownTimer timer;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();

        if (TextUtils.isEmpty(action)) {
            Log.e("test", "action must not be null");
        } else if (action.equals(STOP_SERVICE)) {
            Log.d("test", "service should be stopped");
            stop();
        } else if (action.equals(START_SERVICE)) {
            start(intent);
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void start(Intent intent) {
        createNotificationChannel();
        Notification notification = createNotification(intent);

        startForeground(1, notification);

        timer = new CountDownTimer(9999, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("test", "millis:" + millisUntilFinished + "," +
                        "seconds:" + millisUntilFinished / 1000);

                startDialogActivity(millisUntilFinished, false);
            }

            @Override
            public void onFinish() {
                stop();
            }
        };

        timer.start();
        Log.d("test", "creation - hex address is:" + timer.toString());
    }

    private void stop() {
        startDialogActivity(0, true);
        stopForeground(true);
        stopSelf();
        if (timer != null) {
            timer.cancel();
        }
    }

    private void startDialogActivity(long millisUntilFinished, boolean isFinished) {
        Intent intent = new Intent(this, DialogActivity.class);
        intent.putExtra("millis", (int) millisUntilFinished / 1000);
        intent.putExtra("isFinished", isFinished);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private Notification createNotification(Intent intent) {
        String input = intent.getStringExtra("inputExtra");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.img_chef)
                .setContentIntent(pendingIntent)
                .build();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager == null) {
                Log.e("test", "notification manager is null");
                return;
            }

            manager.createNotificationChannel(serviceChannel);
        }
    }
}

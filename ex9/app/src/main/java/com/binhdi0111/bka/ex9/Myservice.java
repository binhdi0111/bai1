package com.binhdi0111.bka.ex9;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Date;

public class Myservice extends Service {
    private static final String CHANNEL_ID = "binhbka0111";
    int max_push_notification = 100;
    @Override
    public void onCreate() {
        createNotificationChannel();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        countDownTimer();
        return super.onStartCommand(intent, flags, startId);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private void sendNotification(double a){
        String title = "Lượt thông báo hiện tại: "+(max_push_notification - (int)(a/1000));
        String message = "Đã đẩy "+(max_push_notification-(int)(a/1000))+"/"+(int)(a/1000)+" thông báo";
        Intent intent = new Intent("send-notify_data");
        intent.putExtra("title",title);
        intent.putExtra("message",message);
        sendBroadcast(intent);
        Log.d("hahhahaahhahaha", "showNotification: "+title);

        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager != null){
            notificationManager.notify(getNotificationId(),notification);
        }
        startForeground(1,notification);

    }
    public int getNotificationId(){
        return (int) new Date().getTime();
    }
    public void countDownTimer(){
        CountDownTimer countDownTimer = new CountDownTimer(1000*max_push_notification,1000) {
            @Override
            public void onTick(long l) {
                sendNotification(l);
                if(max_push_notification-(int)(l/1000) == 0){
                    stopSelf();
                }
            }

            @Override
            public void onFinish() {
                countDownTimer();
            }
        };countDownTimer.start();

    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}

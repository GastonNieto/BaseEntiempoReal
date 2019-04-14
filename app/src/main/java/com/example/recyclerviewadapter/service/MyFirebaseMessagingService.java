package com.example.recyclerviewadapter.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.recyclerviewadapter.MainActivity;
import com.example.recyclerviewadapter.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import static android.content.ContentValues.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private final String CHANNEL_ID = getString(R.string.default_notification_channel_id);

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...
        if (remoteMessage.getData().isEmpty()) {
        showMesage(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }else showmessage(remoteMessage.getData());
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
            } else {
                // Handle message within 10 seconds
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    private void showmessage(Map<String, String> data) {
        String title = data.get("title").toString();
        String body = data.get("body").toString();
        createnotificationchannel();
        Intent activityIntent = new Intent(this, MainActivity.class);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        Uri defautSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        // Intent broadcastIntent = new Intent(this, NotificationReciber.class);
        //broadcastIntent.putExtra("toastMessage", opcion);
        // PendingIntent actionIntent = PendingIntent.getBroadcast(this,
        //        0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                // .setSmallIcon(R.drawable.ic_play)
                //.setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setContentTitle(title)
                .setContentText(body)
                //.setCustomContentView(custom)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                ///.addAction(R.drawable.ic_stop, "Detener", actionIntent)
                .build();

    }

    private void showMesage(String title, String body) {
        createnotificationchannel();
        Intent activityIntent = new Intent(this, MainActivity.class);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        Uri defautSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
       // Intent broadcastIntent = new Intent(this, NotificationReciber.class);
        //broadcastIntent.putExtra("toastMessage", opcion);
       // PendingIntent actionIntent = PendingIntent.getBroadcast(this,
        //        0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
               // .setSmallIcon(R.drawable.ic_play)
                //.setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                 .setContentTitle(title)
                 .setContentText(body)
                //.setCustomContentView(custom)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                ///.addAction(R.drawable.ic_stop, "Detener", actionIntent)
                .build();

    }
    private void createnotificationchannel() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "";
            String descripcion = "CLOUD MESSAGE FIREBASE";
            int importans = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importans);
            notificationChannel.setDescription(descripcion);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

}

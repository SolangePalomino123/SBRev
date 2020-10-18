package com.sbr.sbrevapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG ="MsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        super.onMessageReceived(remoteMessage);

        Log.d(TAG,"FROM: "+remoteMessage.getFrom());
        String from=remoteMessage.getFrom();
        String title ="BBVA Transacciones";

        if(remoteMessage.getNotification()!=null){
            Log.d(TAG,"Notificacion: "+remoteMessage.getNotification().getBody());
            mostrarNotificacion(title,remoteMessage.getNotification().getBody());
        }else{
            mostrarNotificacion(title,remoteMessage.getData().get("default"));

        }

        if(remoteMessage.getData().size()>0){
            Log.d(TAG,"Message data: " + remoteMessage.getData());
        }
    }

    private void mostrarNotificacion(String title, String body) {

        Intent intent =new Intent(this, SplashActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this, 0,intent,PendingIntent.FLAG_ONE_SHOT);

        Uri notificationSound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Transacciones", NotificationManager.IMPORTANCE_HIGH);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            //notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_logo)
                //.setTicker("Hearty365")
                .setContentTitle(title)
                .setContentText(body)
                .setContentInfo("Info")
                .setSound(notificationSound);

        notificationManager.notify(/*notification id*/0, notificationBuilder.build());

    }

}

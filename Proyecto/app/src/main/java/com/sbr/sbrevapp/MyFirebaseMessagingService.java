package com.sbr.sbrevapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG ="MsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        super.onMessageReceived(remoteMessage);

        Log.d(TAG,"FROM"+remoteMessage.getFrom());
        String from=remoteMessage.getFrom();

        if(remoteMessage.getNotification()!=null){
            Log.d(TAG,"Notificacion: "+remoteMessage.getNotification().getBody());
            mostrarNotificacion(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }

        if(remoteMessage.getData().size()>0){
            Log.d(TAG,"Message data: " + remoteMessage.getData());
        }
    }

    private void mostrarNotificacion(String title, String body) {
        Intent intent =new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this, 0,intent,PendingIntent.FLAG_ONE_SHOT);

        Uri notificationSound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notifBuilder =new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_logo)
                .setContentTitle("BBVA Transacciones")
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(notificationSound)
                .setContentIntent(pendingIntent);

        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notifyManager.notify(0,notifBuilder.build());

    }

}

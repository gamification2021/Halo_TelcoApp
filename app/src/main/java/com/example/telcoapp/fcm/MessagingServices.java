package com.example.telcoapp.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;


import com.example.telcoapp.R;
import com.example.telcoapp.TelcoActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

public class MessagingServices extends FirebaseMessagingService {

    private static final String TAG = "FCM Message";
    Intent intent;

    public MessagingServices() {
        super();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        try {
            //Log.d("msg", "zzzree onMessageReceived: " + remoteMessage.getNotification().getBody());
            Log.e("data", "zzzree msg" + remoteMessage.getData());
            for (Map.Entry<String, String> entry : remoteMessage.getData().entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Log.d(TAG, "key, " + key + " value " + value);
            }

            intent = new Intent(getApplicationContext(), TelcoActivity.class);
            intent.putExtra("REDIRCET_RECHARGE", true);
//                intent.putExtra("TITLE", remoteMessage.getData().get("title"));
//                intent.putExtra("DESCRIPTION", remoteMessage.getData().get("message"));
//                intent.putExtra("IMAGE_URL", remoteMessage.getData().get("imageUrl"));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
            Bitmap bmp = null;
            try {
                InputStream in = new URL(remoteMessage.getData().get("imageUrl")).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }

            PendingIntent pendingIntent;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE);
            } else {
                pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            }

            String channelId = "Default";

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setLargeIcon(bmp)
                    .setSmallIcon(R.drawable.imthree)
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bmp))
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
                if (manager != null) {
                    manager.createNotificationChannel(channel);
                }
            }
            if (manager != null) {
                manager.notify(0, builder.build());
            }
        } catch (Exception e) {
            Intent i = new Intent(getApplicationContext(), TelcoActivity.class);
            startActivity(i);
            e.printStackTrace();
        }
    }

    @Override
    public void onNewToken(String token) {
        sendRegistrationToServer(token);
    }

    public void sendRegistrationToServer(String token) {
        //PrefUtils.getInstance(getApplicationContext()).setValue(PrefKeys.FCM_TOKEN, token);
        Log.e("FCM TOKEN", token);
    }
}



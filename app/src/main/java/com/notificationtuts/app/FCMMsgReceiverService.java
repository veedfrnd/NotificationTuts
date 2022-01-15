package com.notificationtuts.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static com.notificationtuts.app.MyApp.FCM_CHANNEL_ID;

public class FCMMsgReceiverService extends FirebaseMessagingService {
    public static final String TAG = "Prashant";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i(TAG, "onMessageReceived: Called");
        Log.i(TAG, "onMessageReceived: Msg receive from " + remoteMessage.getFrom());
        if (remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            GenerateNotification(title, body);
        }
        if (remoteMessage.getData().size() > 0) {
            /*for (String key : remoteMessage.getData().keySet()) {
                Log.i(TAG, "onMessageReceived: Data " + remoteMessage.getData().get(key));
            }
            */
            String title = remoteMessage.getData().get("title");
            String body = remoteMessage.getData().get("body");
            //String title =  remoteMessage.getData().get("title");
            GenerateNotification(title, body);
            Log.i(TAG, "onMessageReceived: Data " + remoteMessage.getData().toString());
        }
    }

    private void GenerateNotification(String title, String body) {
        Notification notification = new NotificationCompat.Builder(this, FCM_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_noti)
                .setContentText(body)
                .setContentTitle(title)
                .setColor(Color.RED)
                .build();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1002, notification);
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        Log.i(TAG, "onDeletedMessages: ");
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.i(TAG, "onNewToken: " + s);
        //upload this token on the server
    }
}

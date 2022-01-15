package com.notificationtuts.app;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.installations.InstallationTokenResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class MyApp extends Application {
    public static final String TAG = "Prashant";
    public static final String FCM_CHANNEL_ID = "FCMCHANNELID";

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.i(TAG, "onComplete:  "+task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        String token = task.getResult();
                        Log.i(TAG, token);
                    }
                });

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel fcmChannel = new NotificationChannel(FCM_CHANNEL_ID,
                    "FCM_CHANNEL", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notiManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notiManager.createNotificationChannel(fcmChannel);
        }
    }
}

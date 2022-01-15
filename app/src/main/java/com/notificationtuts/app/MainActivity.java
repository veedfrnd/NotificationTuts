package com.notificationtuts.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    //dpOC1ceAS2mzYeEtgCK0-P:APA91bE9tVNcSwwfKTbRgi58Mx5n8qZy3GE8AAiy3fvnaxZBSsVag4gFTJMD55BYyTQLoCrsMmMS_3yQlQbkZ5KS3nQedMrhGwRo_p3z_Q5rgVA9MfWATFFDD0rxg1u54iTrH8aBFrd2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().unsubscribeFromTopic("Prashant")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            Toast.makeText(MainActivity.this, "Subscribed...", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "Try Again to subscribe", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
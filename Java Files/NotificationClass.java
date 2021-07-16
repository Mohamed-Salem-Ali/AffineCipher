package com.example.a01a03;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationClass extends Application {
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";

    
// here we call the createNotificationChannel function to create channels to show notifications
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }

// this function is used two create two channels 
    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel Encryption = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Encryption",
                    NotificationManager.IMPORTANCE_HIGH
            );
            Encryption.setDescription("Encryption Phase");

            NotificationChannel Decryption = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Decryption",
                    NotificationManager.IMPORTANCE_HIGH
            );
            Decryption.setDescription("Decryption Phase");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(Encryption);
            manager.createNotificationChannel(Decryption);
        }
    }
}

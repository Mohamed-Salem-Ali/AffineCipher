package com.example.a01a03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.a01a03.NotificationClass.CHANNEL_1_ID;
import static com.example.a01a03.NotificationClass.CHANNEL_2_ID;

public class HomeActivity extends AppCompatActivity {

    private Button decr;
    private Button encr;
    private NotificationManagerCompat notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
	
	// initialize the components 
        notificationManager = NotificationManagerCompat.from(this);
        decr = (Button) findViewById(R.id.decr);
        
	decr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOnDecryption();
                openDecryption();

            }
        });

        // initialize the components 
	encr = (Button) findViewById(R.id.encr);
        encr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOnEncryption();
                openEncryption();

            }
        });
    }
	//this function used to show notification after clicking on the encryption button 
    public void sendOnEncryption() {
	  Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_1)
                .setContentTitle("Affine Cipher")
                .setContentText("Encryption Phase")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
    }
	//this function used to show notification after clicking on the encryption button 
    public void sendOnDecryption() {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_1)
                .setContentTitle("Affine Cipher")
                .setContentText("Decryption Phase")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(2, notification);
    }

	//this function used to change activity to decryption and decryption class
    public void openDecryption()
    {
        Intent intent = new Intent(this,Decryption.class);
        startActivity(intent);
    }

	//this function used to change activity to encryption and encryption class
    public void openEncryption()
    {
        Intent intent = new Intent(this,Encryption.class);
        startActivity(intent);
    }
}

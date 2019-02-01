package com.justclack.customnotificationoreo;


import android.app.Notification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.github.arturogutierrez.Badges;
import com.github.arturogutierrez.BadgesNotSupportedException;

import me.leolin.shortcutbadger.ShortcutBadger;

import static com.justclack.customnotificationoreo.App.CHANNEL_1_ID;
import static com.justclack.customnotificationoreo.App.CHANNEL_2_ID;


public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextMessage = findViewById(R.id.edit_text_message);
    }

    public void sendOnChannel1(View v) {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        int badgeCount = 1;
        ShortcutBadger.applyCount(MainActivity.this, badgeCount); //for 1.1.4+
        //ShortcutBadger.with(getApplicationContext()).count(badgeCount); //for 1.1.3
        notificationManager.notify(1, notification);
        try {
            Badges.setBadge(this, 5);
        } catch (BadgesNotSupportedException badgesNotSupportedException) {
            Log.d("badges", badgesNotSupportedException.getMessage());
        }
    }

    public void sendOnChannel2(View v) {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManager.notify(2, notification);
    }
}

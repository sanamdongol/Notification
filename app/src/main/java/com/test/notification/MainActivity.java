package com.test.notification;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button noti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        noti = (Button) findViewById(R.id.btnSend);
        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customNotification();
            }
        });
    }

    public void sendNotification() {
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setTicker(getString(R.string.app_name))
                        .setContentTitle("My notification")

                        .setContentText("Hello World!");

        //activity to open when notification is tapped
        Intent resultIntent = new Intent(this, NotificationActivity.class);
        //parameters , context, requestCode,intent,flags
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, 0);
        mBuilder.setContentIntent(pendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(0, mBuilder.build());


    }

    public void bigNotification() {
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Event tracker")
                .setTicker("Picture Notification")
                .setContentText("Events received");
        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();
        String[] events = new String[6];
        events[0] = new String(" Sunday ....");
        events[1] = new String(" Monday ...");
        events[2] = new String(" Tuesday ...");
        events[3] = new String(" Wednesdau ...");
        events[4] = new String(" Thursday ...");
        events[5] = new String(" Friday...");
// Sets a title for the Inbox in expanded layout
        inboxStyle.setBigContentTitle("Event tracker details:");

// Moves events into the expanded layout
        for (int i = 0; i < events.length; i++) {

            inboxStyle.addLine(events[i]);
        }

        mBuilder.setStyle(inboxStyle);
        inboxStyle.setBigContentTitle("Big Title Details:");

        //activity to open when notification is tapped
        Intent resultIntent = new Intent(this, NotificationActivity.class);
        //parameters , context, requestCode,intent,flags
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, 0);
        mBuilder.setContentIntent(pendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(0, mBuilder.build());

    }



    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void customNotification() {
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Picture Notification")
                .setContentTitle("Big Picture Notification")
                .setContentText("Content in here");

        //get the bitmap to show in notification bar
        Bitmap bitmap_image = BitmapFactory.decodeResource(this.getResources(), R.drawable.ronin);
        NotificationCompat.BigPictureStyle s = new NotificationCompat.BigPictureStyle().bigPicture(bitmap_image);

        s.setSummaryText("Summary text appears on expanding the notification");
        mBuilder.setStyle(s);

        Intent resultIntent = new Intent(this, NotificationActivity.class);
        //parameters , context, requestCode,intent,flags
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, 0);
        mBuilder.setContentIntent(pendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        mNotificationManager.notify(0, mBuilder.build());


    }

}

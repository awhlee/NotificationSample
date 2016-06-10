package com.pexlabs.notificationtest;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;

    private NotificationManagerCompat mNotificationManager;

    private boolean mResumeDueToNewIntent = false;
    private Intent mNewIntent = null;

    private static final int BUTTON1_NOTIF_ID = 1;
    private static final int BUTTON2_NOTIF_ID = 2;
    private static int mCurrentNotifId = 3;
    private static final int BUTTON5_NOTIF_ID = 1001;

    private static final String INTENT_DATA_KEY = "data";

    private static int SUMMARY_NOTIFICATION_ID = 1000;
    private final static String GROUP_KEY = "myGroup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("TestApp", "onResume");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mButton1 = (Button)findViewById(R.id.button1);
        mButton1.setOnClickListener(this);

        mButton2 = (Button)findViewById(R.id.button2);
        mButton2.setOnClickListener(this);

        mButton3 = (Button)findViewById(R.id.button3);
        mButton3.setOnClickListener(this);

        mButton4 = (Button)findViewById(R.id.button4);
        mButton4.setOnClickListener(this);

        mButton5 = (Button)findViewById(R.id.button5);
        mButton5.setOnClickListener(this);

        mNotificationManager = NotificationManagerCompat.from(this);
    }

    @Override
    public void onStart() {
        Log.i("TestApp", "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Intent intent = getIntent();

        Log.i("TestApp", "onResume() - intent " + intent);

        Intent incomingIntent;
        if (mResumeDueToNewIntent) {
            mResumeDueToNewIntent = false;
            incomingIntent = mNewIntent;
            mNewIntent = null;
        } else {
            incomingIntent = getIntent();
        }

        String extra = incomingIntent.getStringExtra(INTENT_DATA_KEY);

        Log.i("TestApp", "data extra is set to: " + extra);

        super.onResume();
    }

    /**
     * This is called when the activity receives a new intent but there is already an instance
     * of it running. This call will be followed by a call to onResume().
     * @param intent
     */
    @Override
    public void onNewIntent(Intent intent) {
        Log.i("TestApp", "onNewIntent() - intent " + intent);

        mResumeDueToNewIntent = true;
        mNewIntent = intent;

        super.onNewIntent(intent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button1:
                handleButton1();
                break;
            case R.id.button2:
                handleButton2();
                break;
            case R.id.button3:
                handleButton3();
                break;
            case R.id.button4:
                handleButton4();
            case R.id.button5:
                handleButton5();
                break;
        }
    }

    private void handleButton1() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction("action 1");
        intent.putExtra(INTENT_DATA_KEY, "button 1 action 1");

        PendingIntent pIntent = PendingIntent.getActivity(this, (int)System.currentTimeMillis(),
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intent2 = new Intent(this, MainActivity.class);
        intent2.setAction("action 2");
        intent2.putExtra(INTENT_DATA_KEY, "button 1 action 2");

        PendingIntent pIntent2 = PendingIntent.getActivity(this, (int)System.currentTimeMillis(),
                intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intent3 = new Intent(this, MainActivity.class);
        intent3.setAction("action 3");
        intent3.putExtra(INTENT_DATA_KEY, "button 1 action 3");

        PendingIntent pIntent3 = PendingIntent.getActivity(this, (int)System.currentTimeMillis(),
                intent3, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification n = new NotificationCompat.Builder(this)
                .setContentTitle("Button 1 Title")
                .setContentText("Button 1 Text")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setGroup(GROUP_KEY)
                .addAction(R.drawable.ic_pex, "Action 1", pIntent)
                .addAction(R.drawable.ic_pex, "Action 2", pIntent2)
                .addAction(R.drawable.ic_pex, "Action 3", pIntent3)
                .build();

        mNotificationManager.notify(BUTTON1_NOTIF_ID, n);
        updateSummaryNotification();
    }

    private void handleButton2() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction("action 1");
        intent.putExtra(INTENT_DATA_KEY, "button 2 action 1");

        PendingIntent pIntent = PendingIntent.getActivity(this, (int)System.currentTimeMillis(),
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intent2 = new Intent(this, MainActivity.class);
        intent2.setAction("action 2");
        intent2.putExtra(INTENT_DATA_KEY, "button 2 action 2");

        PendingIntent pIntent2 = PendingIntent.getActivity(this, (int)System.currentTimeMillis(),
                intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intent3 = new Intent(this, MainActivity.class);
        intent3.setAction("action 3");
        intent3.putExtra(INTENT_DATA_KEY, "button 2 action 3");

        PendingIntent pIntent3 = PendingIntent.getActivity(this, (int)System.currentTimeMillis(),
                intent3, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification n = new NotificationCompat.Builder(this)
                .setContentTitle("Button 2 Title")
                .setContentText("Button 2 Text")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setGroup(GROUP_KEY)
                .addAction(R.drawable.ic_pex, "Action 1", pIntent)
                .addAction(R.drawable.ic_pex, "Action 2", pIntent2)
                .addAction(R.drawable.ic_pex, "Action 3", pIntent3)
                .build();

        mNotificationManager.notify(BUTTON1_NOTIF_ID, n);
        updateSummaryNotification();
    }

    private void handleButton3() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction("action 1");
        intent.putExtra(INTENT_DATA_KEY, "button 3 action 1");

        PendingIntent pIntent = PendingIntent.getActivity(this, (int)System.currentTimeMillis(),
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intent2 = new Intent(this, MainActivity.class);
        intent2.setAction("action 2");
        intent2.putExtra(INTENT_DATA_KEY, "button 3 action 2");

        PendingIntent pIntent2 = PendingIntent.getActivity(this, (int)System.currentTimeMillis(),
                intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intent3 = new Intent(this, MainActivity.class);
        intent3.setAction("action 3");
        intent3.putExtra(INTENT_DATA_KEY, "button 3 action 3");

        PendingIntent pIntent3 = PendingIntent.getActivity(this, (int)System.currentTimeMillis(),
                intent3, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification n = new NotificationCompat.Builder(this)
                .setContentTitle("Button 3 Title")
                .setContentText("Button 2 Text")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setGroup(GROUP_KEY)
                .addAction(R.drawable.ic_pex, "Action 1", pIntent)
                .addAction(R.drawable.ic_pex, "Action 2", pIntent2)
                .addAction(R.drawable.ic_pex, "Action 3", pIntent3)
                .build();

        mNotificationManager.notify(BUTTON2_NOTIF_ID, n);
        updateSummaryNotification();
    }

    private void handleButton4() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction("action 1");
        intent.putExtra(INTENT_DATA_KEY, "button 4 action 1");

        PendingIntent pIntent = PendingIntent.getActivity(this, (int)System.currentTimeMillis(),
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intent2 = new Intent(this, MainActivity.class);
        intent2.setAction("action 2");
        intent2.putExtra(INTENT_DATA_KEY, "button 4 action 2");

        PendingIntent pIntent2 = PendingIntent.getActivity(this, (int)System.currentTimeMillis(),
                intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intent3 = new Intent(this, MainActivity.class);
        intent3.setAction("action 3");
        intent3.putExtra(INTENT_DATA_KEY, "button 4 action 3");

        PendingIntent pIntent3 = PendingIntent.getActivity(this, (int)System.currentTimeMillis(),
                intent3, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification n = new NotificationCompat.Builder(this)
                .setContentTitle("Button 4 Title")
                .setContentText("Button 4 Text")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setGroup(GROUP_KEY)
                .addAction(R.drawable.ic_pex, "Action 1", pIntent)
                .addAction(R.drawable.ic_pex, "Action 2", pIntent2)
                .addAction(R.drawable.ic_pex, "Action 3", pIntent3)
                .build();

        mNotificationManager.notify(mCurrentNotifId++, n);
        updateSummaryNotification();
    }

    /**
     * For Wearable support we need to create the concept of stacked notifications
     * https://developer.android.com/training/wearables/notifications/stacks.html
     */
    private void updateSummaryNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction("summary action");
        intent.putExtra(INTENT_DATA_KEY, "summary intent");

        PendingIntent pIntent = PendingIntent.getActivity(this, (int)System.currentTimeMillis(),
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification summaryNotification = new NotificationCompat.Builder(this)
                .setContentTitle("Summary Notification!")
                .setSmallIcon(R.drawable.ic_pex)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("1...2")
                        .addLine("2...3")
                        .setBigContentTitle("Inbox Style Big Content Title")
                        .setSummaryText("Inbox Style Summary Text"))
                .setGroup(GROUP_KEY)
                .setGroupSummary(true)
                .setNumber(100)
                .addAction(R.drawable.ic_pex, "Go to Inbox", pIntent)
                .build();


        mNotificationManager.notify(SUMMARY_NOTIFICATION_ID, summaryNotification);
    }


    private void handleButton5() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction("custom notif action");
        intent.putExtra(INTENT_DATA_KEY, "custom notif action");

        PendingIntent pIntent = PendingIntent.getActivity(this, (int)System.currentTimeMillis(),
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.custom_notification);
        contentView.setImageViewResource(R.id.custom_image, R.drawable.ic_pex);
        contentView.setTextViewText(R.id.custom_title, "Custom notification");
        contentView.setTextViewText(R.id.custom_text, "This is a custom layout");
        contentView.setTextColor(R.id.custom_title, 0xFFFF0000);
        contentView.setTextColor(R.id.custom_text, 0xFFFF0000);

        Notification n = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_pex)
//                    .setContent(contentView)
                    .setContentTitle("Custom Content Title")
                    .setContentIntent(pIntent)
                    .setAutoCancel(true)
                    .setGroup(GROUP_KEY)
                    .build();

        n.bigContentView = contentView;

        mNotificationManager.notify(BUTTON5_NOTIF_ID, n);
    }

}

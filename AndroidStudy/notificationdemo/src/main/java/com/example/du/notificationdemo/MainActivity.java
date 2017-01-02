package com.example.du.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendNotice= (Button) findViewById(R.id.button);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                Intent intent=new Intent(this,NotificationActivity.class);
                PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
                NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification=new NotificationCompat.Builder(this)
                        .setContentTitle("我的第一条通知！")
                        .setContentText("今天学习了通知，这是我的第一条通知！")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                                R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .setContentInfo("AAAAAA")
//                        .setSound(Uri.fromFile(new File("/storage/emulated/0/xiami/audios/送别_朴树.mp3")))
//                        .setVibrate(new long[]{0,1000,1000,1000})
//                        .setLights(Color.RED,1000,1000)
//                        .setDefaults(NotificationCompat.DEFAULT_ALL)
//                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(
                                BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher)
                        ))
                        .build();
                manager.notify(1,notification);

                break;
            default:
                break;
        }
    }
}

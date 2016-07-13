package com.liangdekai.demo;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by asus on 2016/7/12.
 */
public class MainActivity extends Activity implements View.OnClickListener {
    private ImageButton mBtSend ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        mBtSend = (ImageButton) findViewById(R.id.send);
        mBtSend.setImageDrawable(getResources().getDrawable(R.drawable.abc_ic_search));
        mBtSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send :
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.drawable.abc_ic_search)
                                .setContentTitle("My notification")
                                .setContentText("Hello World!");
                mBuilder.setAutoCancel(true);//设置自动关闭
                mBuilder.setLights(Color.BLUE, 500, 500);//设置led颜色
                long[] pattern = {500,500,500,500,500,500};
                mBuilder.setVibrate(pattern);//设置振动模式
                Intent resultIntent = new Intent(this, NotificationActivity.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                stackBuilder.addParentStack(NotificationActivity.class);
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(pendingIntent);
                mNotificationManager.notify(1, mBuilder.build());
                Toast.makeText(this,"按钮已经点击",Toast.LENGTH_LONG).show();
        }
    }
}

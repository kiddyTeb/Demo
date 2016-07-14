package com.liangdekai.demo.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.liangdekai.demo.R;

/**
 * Created by asus on 2016/7/12.
 */
public class MainActivity extends Activity implements View.OnClickListener {
    private ImageButton mBtSend ;
    private Button mBtTest;
    private Button mBtThread;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        mBtTest = (Button) findViewById(R.id.broadcast);
        mBtSend = (ImageButton) findViewById(R.id.send);
        mBtThread = (Button) findViewById(R.id.interrupt);
        mBtSend.setImageDrawable(getResources().getDrawable(R.drawable.abc_ic_search));
        mBtSend.setOnClickListener(this);
        mBtTest.setOnClickListener(this);
        mBtThread.setOnClickListener(this);
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
                break;
            case R.id.broadcast :
                Intent intent = new Intent(this, BoradcastActiivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.interrupt :

                Log.d("Test", "当前线程ID为" + Thread.currentThread());
                final Thread TestThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (!Thread.interrupted()) {
                                Log.d("Test", "" + Thread.interrupted() + "当前线程ID为" + Thread.currentThread());
                                //模仿网络请求
                                request();
                                Log.d("Test", "callBack");
                                progressDialog.dismiss();
                                break;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("请稍等");
                progressDialog.setMessage("正在进行网络请求");
                progressDialog.setCancelable(true);
                progressDialog.show();
                progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        TestThread.interrupt();
                        Log.d("Test", "interrupt success");
                    }
                });
                TestThread.start();
        }
    }

    public void request() throws InterruptedException {
        int i = 0;
        while (i < 10) {
            Thread.sleep(1000);
            Log.d("Test", "正在请求" + i);
            i++;
        }
    }
}

package com.liangdekai.demo.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.liangdekai.demo.R;


/**
 * Created by asus on 2016/7/13.
 */
public class BoradcastActiivity extends Activity {
    private IntentFilter intentFilter;
    private Receiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        receiver = new Receiver();
        registerReceiver(receiver,intentFilter);
        Log.d("Test","onCreate");
        //Toast.makeText(this,"receive",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        Log.d("Test","onDestory");
    }

    class Receiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"receive",Toast.LENGTH_LONG).show();
            /*try {
                Thread.sleep(20*1000);
                Log.d("Test","no problem");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(20*1000);
                        Log.d("Test","no anr");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.d("Test","exception");
                    }
                }
            }).start();
        }
    }
}

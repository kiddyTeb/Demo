package com.liangdekai.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by asus on 2016/7/12.
 */
public class NotificationActivity extends Activity{
    private TextView mTvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

    }
}

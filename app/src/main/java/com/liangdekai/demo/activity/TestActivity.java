package com.liangdekai.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.liangdekai.demo.R;

/**
 * Created by asus on 2016/7/16.
 */
public class TestActivity extends Activity implements View.OnClickListener{
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw);
        button = (Button) findViewById(R.id.ss);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ss :

        }
    }
}

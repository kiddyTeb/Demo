package service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by asus on 2016/7/14.
 */
public class SleepReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context ,SleepService.class);
        context.startService(i);
    }
}

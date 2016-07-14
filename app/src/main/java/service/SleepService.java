package service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Date;

/**
 * Created by asus on 2016/7/14.
 */
public class SleepService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("Test","executed at" + new Date().toString());
            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long sleepTime = SystemClock.elapsedRealtime() + 20*1000;
        Intent intent1 = new Intent(this , SleepReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this,0,intent1,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP ,sleepTime,pi);
        Log.d("Test","i had reached here");
        return super.onStartCommand(intent, flags, startId);
    }
}

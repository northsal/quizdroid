package com.example.iguest.quizdroidpart1;


import android.app.AlarmManager;
import android.app.DownloadManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

public class DownloadService extends IntentService {
    private DownloadManager dm;
    private long enqueue;
    public static final int MY_ALARM = 23432;

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    public void onCreate() { super.onCreate(); }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        Log.i("DownloadService", "entered onHandleIntent()");
        // Hooray! This method is called where the AlarmManager shouldve started the download service and we just received it here!

        // Specify the url you want to download here
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String url = sharedPrefs.getString("prefURL", "http://tednewardsandbox.site44.com/questions.json");

        Log.i("DownloadService", "should be downloading here");

        // Star the download
        dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        enqueue = dm.enqueue(request);

    }

    public static void startOrStopAlarm(Context context, boolean on) {
        Log.i("DownloadService", "startOrStopAlarm on = " + on);

        Intent alarmReceiverIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, MY_ALARM, alarmReceiverIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        if (on) {
            int refreshInterval = 5 * 60000; // 5 min x 60,000 milliseconds = total ms in 5 min

            Log.i("DownloadService", "setting alarm to " + refreshInterval);

            // Start the alarm manager to repeat
            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), refreshInterval, pendingIntent);
        }
        else {
            manager.cancel(pendingIntent);
            pendingIntent.cancel();

            Log.i("DownloadService", "Stopping alarm");
        }
    }


}

package com.example.iguest.quizdroidpart1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;


public class AlarmReceiver extends BroadcastReceiver {

    public AlarmReceiver(){}

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("AlarmReceiver", "entered onReceive() from AlarmReceiver");

        //SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        //String url = sharedPrefs.getString("prefURL", "http://tednewardsandbox.site44.com/questions.json");

        Log.i("QuizApp", "Fired!");
        Intent downloadServiceIntent = new Intent(context, DownloadService.class);
        context.startService(downloadServiceIntent);

       //Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
    }


}

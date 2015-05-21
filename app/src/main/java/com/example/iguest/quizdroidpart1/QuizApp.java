package com.example.iguest.quizdroidpart1;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QuizApp extends Application implements ITopicRepository {

    private static QuizApp ourInstance; //singleton
    private ArrayList<Topic> repo;
    PendingIntent alarmIntent = null;
    AlarmManager am = null;
    final String MY_ACTION = "setupAlarm";
    String url;
    int interval;
    SharedPreferences.OnSharedPreferenceChangeListener listener;



    public static QuizApp getInstance() {
        return ourInstance;
    }

    public QuizApp() {
        if(ourInstance == null) {
            ourInstance = this;
        } else {
            throw new RuntimeException("Cannot create more than one QuizApp");
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("QuizApp", "onCreate fired!");
/*
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        interval = Integer.parseInt(sharedPrefs.getString("prefFreq", "1"));
        final Context app = getApplicationContext();

        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        IntentFilter intentFilter = new IntentFilter(MY_ACTION);
        AlarmReceiver aReceiver = new AlarmReceiver();
        registerReceiver(aReceiver, intentFilter);
        Intent intent = new Intent();
        intent.setAction(MY_ACTION);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        am.setRepeating(AlarmManager.RTC, System.currentTimeMillis() + (interval * 60000),
                        (interval * 60000), alarmIntent);

        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                Log.i("QuizApp", "Change!");
                if(am != null) {
                    am.cancel(alarmIntent);
                    alarmIntent.cancel();
                }
                interval = Integer.parseInt(prefs.getString("prefFreq", "1"));
                am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                IntentFilter intentFilter = new IntentFilter(MY_ACTION);
                AlarmReceiver aReceiver = new AlarmReceiver();
                registerReceiver(aReceiver, intentFilter);
                Intent intent = new Intent();
                intent.setAction(MY_ACTION);
                alarmIntent = PendingIntent.getBroadcast(app, 0, intent, 0);
                am.setRepeating(AlarmManager.RTC, System.currentTimeMillis() + (interval * 60000),
                                (interval * 60000), alarmIntent);

            }
        };

        sharedPrefs.registerOnSharedPreferenceChangeListener(listener); */

        this.repo = new ArrayList<Topic>();

        try{
            InputStream inputStream = getAssets().open("questions.json");
            JSONArray jsonData = new JSONArray(loadJSONFromAsset());
            //Parse through each topic from file
            for(int i = 0; i < jsonData.length(); i++) {
                JSONObject jsonObj = jsonData.getJSONObject(i);

                Topic topic = new Topic();
                topic.setTitle(jsonObj.getString("title"));
                topic.setShortDescr(jsonObj.getString("desc"));
                topic.setLongDescr(jsonObj.getString("desc"));

                //Grab all related questions and add to topic
                JSONArray questionsList = jsonObj.getJSONArray("questions");
                for (int j = 0; j < questionsList.length(); j++) {
                    JSONObject entry = questionsList.getJSONObject(j);
                    Question question = new Question();
                    question.setQuestion(entry.getString("text"));
                    question.setAnswer(entry.getInt("answer"));
                    JSONArray answers = entry.getJSONArray("answers");
                    String[] options = new String[4];
                    for(int k = 0; k < answers.length(); k++) {
                        options[k] = answers.getString(k);
                    }
                    question.setOptions(options);
                    topic.addQuestion(question);
                }
                //Add to topic repository
                repo.add(topic);
            }

        } catch(IOException e) {
            e.printStackTrace();
        } catch(JSONException e) {
            e.printStackTrace();
        }

        DownloadService.startOrStopAlarm(this, true);

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public void writeToFile(String data) {
        try {
            Log.i("MyApp", "writing downloaded to file");

            File file = new File(getFilesDir().getAbsolutePath(), "questions.json");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data.getBytes());
            fos.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public List<Topic> getAllTopics() {return repo;}
}


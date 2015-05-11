package com.example.iguest.quizdroidpart1;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iguest on 5/10/15.
 */
public class QuizApp extends Application implements ITopicRepository {

    private static QuizApp ourInstance = null;
    private ArrayList<Topic> repo;

    public static QuizApp getInstance() {
        return ourInstance;
    }

    public QuizApp() {
        Log.d("QuizApp", "Constructor fired!");
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

        //initialize repo list

    }

    public List<Topic> getAllTopics() {
        return repo;
    }

}

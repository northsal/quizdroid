package com.example.iguest.quizdroidpart1;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by iguest on 5/10/15.
 */
public class QuizApp extends Application implements ITopicRepository {

    private static QuizApp ourInstance; //singleton
    private ArrayList<Topic> repo;

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
    public List<Topic> getAllTopics() {return repo;}

}

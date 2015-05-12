package com.example.iguest.quizdroidpart1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.view.View;
import android.util.Log;
import android.content.Intent;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    public String[] options;
    public String[] desc;

    private ListView list;

    private int attempts;
    private int count;

    ArrayList<Topic> topics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listView);

        QuizApp quizApp = (QuizApp) getApplication();
        topics = (ArrayList<Topic>) quizApp.getAllTopics();

        options = new String[topics.size()];
        desc = new String[topics.size()];
        for (int i = 0; i < topics.size(); i++) {
            Topic topic = topics.get(i);
            options[i] = topic.getTitle();
            desc[i] = topic.getShortDescr();
        }
        CustomListAdapter adapter = new CustomListAdapter(this, options, desc);

        list.setAdapter(adapter);

        attempts = 0;
        count = 0;

        list.setOnItemClickListener(new ListView.OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                   Intent next = new Intent(MainActivity.this, ManagerActivity.class);
                   Topic entry = topics.get(position);
                   next.putExtra("topic", entry.getTitle());
                   next.putExtra("attempts", attempts);
                   next.putExtra("total", count);
                   next.putExtra("Questions", entry);
                   startActivity(next);

           }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

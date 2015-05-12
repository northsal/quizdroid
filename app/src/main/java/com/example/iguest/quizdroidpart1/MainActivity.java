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

    public String[] options = {"Math", "Physics", "Marvel Super Heroes"};
    public String[] desc = {"yay", "yayay", "yayayayay"};

    private ListView list;

    private int attempts;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listView);

        QuizApp quizApp = (QuizApp) getApplication();
        ArrayList<Topic> topics = (ArrayList<Topic>) quizApp.getAllTopics();

        options = new String[topics.size()];
        desc = new String[topics.size()];
        for (int i = 0; i < topics.size(); i++) {
                
        }
        CustomListAdapter adapter = new CustomListAdapter(this, options, desc);

        list.setAdapter(adapter);

        attempts = 0;
        count = 0;

        list.setOnItemClickListener(new ListView.OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               if(position == 0) {
                   Intent next = new Intent(MainActivity.this, ManagerActivity.class);
                   next.putExtra("topic", "Math");
                   next.putExtra("attempts", attempts);
                   next.putExtra("total", count);
                startActivity(next);
               } else if(position == 1) {
                   Intent next = new Intent(MainActivity.this, ManagerActivity.class);
                   next.putExtra("topic", "Physics");
                   next.putExtra("attempts", attempts);
                   next.putExtra("total", count);
                   startActivity(next);
               } else if (position == 2) {
                   Intent next = new Intent(MainActivity.this, ManagerActivity.class);
                   next.putExtra("topic", "Marvel");
                   next.putExtra("attempts", attempts);
                   next.putExtra("total", count);
                   startActivity(next);
               }

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

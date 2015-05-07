package com.example.iguest.quizdroidpart1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;


public class ManagerActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        if(savedInstanceState == null) {
            Bundle topicBundle = new Bundle();
            Intent launchingIntent = getIntent();

            String topic = launchingIntent.getStringExtra("topic");
            if(topic.equals("Physics")) {
                topicBundle.putString("topic", "Physics");
            } else if (topic.equals("Math")) {
                topicBundle.putString("topic", "Math");
            } else if(topic.equals("Marvel")) {
                topicBundle.putString("topic", "Marvel");
            }

            int attempts = launchingIntent.getIntExtra("attempts", 0);
            int count = launchingIntent.getIntExtra("count", 0);

            topicBundle.putInt("attempts", attempts);
            topicBundle.putInt("total", count);

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            TopicOverviewFragment overviewFragment = new TopicOverviewFragment();
            overviewFragment.setArguments(topicBundle);
            ft.add(R.id.container, overviewFragment);   // where , what
            ft.commit();
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        Button button = (Button) findViewById(R.id.btnSubmit);
        if(checked) {
            button.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manager, menu);
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

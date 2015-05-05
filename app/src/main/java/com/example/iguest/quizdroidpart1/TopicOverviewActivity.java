package com.example.iguest.quizdroidpart1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;



public class TopicOverviewActivity extends ActionBarActivity {

    private int qCount;
    private String physics = "What equation properly relates force, mass and acceleration?~~f=m*a~~f=m+a~~f=f-m~~m=f*a~~f=m*a--Which of the following are areas within physics?~~Thermodynamics~~Relativity~~Optics~~All of the above~~All of the above";
    private String math = "What is 2 + 2?~~1~~5~~4~~6~~4";
    private String marvel = "When was the Avengers made?~~2011~~2012~~2013~~2014~~2014";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_overview);

        final Intent launchingIntent = getIntent();
        TextView title = (TextView) findViewById(R.id.topicTitle);
        TextView overview = (TextView) findViewById(R.id.sectionDescr);

        final String topic = launchingIntent.getStringExtra("topic");

        title.setText(topic);

        if(topic.equals("Physics")) {
            overview.setText(R.string.physics_overview);
        } else if (topic.equals("Math")) {
            overview.setText(R.string.math_overview);
        } else if(topic.equals("Marvel")) {
            overview.setText(R.string.marvel_overview);
        }



        qCount = 1;

        TextView updateCount = (TextView) findViewById(R.id.questionCount);
        updateCount.setText("There are currently " + qCount + " questions");

        final Button start = (Button) findViewById(R.id.btnStart);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(TopicOverviewActivity.this, QuestionActivity.class);

                if(topic.equals("Physics")) {
                    next.putExtra("questions", physics);
                } else if (topic.equals("Math")) {
                    next.putExtra("questions", math);
                } else if(topic.equals("Marvel")) {
                    next.putExtra("questions", marvel);
                }

                int attempts = launchingIntent.getIntExtra("attempts", 0);
                int count = launchingIntent.getIntExtra("count", 0);
                next.putExtra("attempts", attempts);
                next.putExtra("total", count);
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

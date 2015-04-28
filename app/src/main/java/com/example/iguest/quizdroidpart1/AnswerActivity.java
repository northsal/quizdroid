package com.example.iguest.quizdroidpart1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.view.View;



public class AnswerActivity extends ActionBarActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_answer);

        Intent launchingIntent = getIntent();

        TextView userAnswer = (TextView) findViewById(R.id.userAnswer);
        TextView correctAnswer = (TextView) findViewById(R.id.correctAnswer);
        TextView announceAnswer = (TextView) findViewById(R.id.affirm);
        TextView update = (TextView) findViewById(R.id.update);

        String correct = launchingIntent.getStringExtra("correctAnswer");
        String answer = launchingIntent.getStringExtra("user");
        int attempts = launchingIntent.getIntExtra("attempts", 0);
        int count = launchingIntent.getIntExtra("count", 0);


        userAnswer.setText("Your answer was: " + answer);
        correctAnswer.setText("The correct answer was: " + correct);

        if(correct.equals(answer)) {
            announceAnswer.setText("You got it right!");
            count++;
        } else {
            announceAnswer.setText("You got it wrong!");
        }

        update.setText("You got " + count + " out of " + attempts + " questions right!");

        Button returnButton = (Button) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent (AnswerActivity.this, MainActivity.class);
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
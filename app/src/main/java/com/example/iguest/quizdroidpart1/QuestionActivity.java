package com.example.iguest.quizdroidpart1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.widget.TextView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.content.Intent;



public class QuestionActivity extends ActionBarActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_question);

        final Intent launchingIntent = getIntent();

        String list = launchingIntent.getStringExtra("questions");

        String[] questions = list.split("--");
        String[] question = questions[0].split("~~");

        TextView topicQuestion = (TextView) findViewById(R.id.topicQuestion);
        RadioButton radio1 = (RadioButton) findViewById(R.id.answer1);
        RadioButton radio2 = (RadioButton) findViewById(R.id.answer2);
        RadioButton radio3 = (RadioButton) findViewById(R.id.answer3);
        RadioButton radio4 = (RadioButton) findViewById(R.id.answer4);
        topicQuestion.setText(question[0]);
        radio1.setText(question[1]);
        radio2.setText(question[2]);
        radio3.setText(question[3]);
        radio4.setText(question[4]);

        final String answer = question[5];

        button = (Button) findViewById(R.id.btnSubmit);
        button.setVisibility(View.GONE);

        String remainingQuestions = "";
        if(questions.length > 1) {
            for (int i = 1; i < questions.length; i++) {
                remainingQuestions = questions[i] + "--";
            }
        }
        final String finalQuestions = remainingQuestions;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup rg = (RadioGroup) findViewById(R.id.topicRadioGroup);
                int id = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(id);


                int attempts = launchingIntent.getIntExtra("attempts", 0);
                attempts++;
                int count = launchingIntent.getIntExtra("count", 0);

                Intent next = new Intent(QuestionActivity.this, AnswerActivity.class);
                next.putExtra("questions", finalQuestions);
                next.putExtra("correctAnswer", answer);
                next.putExtra("user", rb.getText());
                next.putExtra("attempts", attempts);
                next.putExtra("total", count);
                startActivity(next);
            }

        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        if(checked) {
            button.setVisibility(View.VISIBLE);
        }
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
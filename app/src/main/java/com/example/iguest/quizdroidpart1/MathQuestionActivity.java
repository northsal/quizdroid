package com.example.iguest.quizdroidpart1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioButton;


public class MathQuestionActivity extends ActionBarActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_math_question);

        TextView question = (TextView) findViewById(R.id.mathQuestion);

        question.setText("What is 2 + 2?");

        button = (Button) findViewById(R.id.btnMathSubmit);
        button.setVisibility(View.GONE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RadioGroup rg = (RadioGroup) findViewById(R.id.mathRadioGroup);
                int id = rg.getCheckedRadioButtonId();
                View rb = (View) findViewById(id);

                Intent launchingIntent = getIntent();

                int attempts = launchingIntent.getIntExtra("attempts", 0);
                int count = launchingIntent.getIntExtra("count", 0);

                Intent next;
                switch (rb.getId()) {
                    case R.id.answer1:
                        next = new Intent(MathQuestionActivity.this, AnswerActivity.class);
                        next.putExtra("correctAnswer", "4");
                        next.putExtra("user", "1");
                        next.putExtra("attempts", attempts + 1);
                        next.putExtra("total", count);
                        startActivity(next);

                        break;
                    case R.id.answer2:
                        next = new Intent(MathQuestionActivity.this, AnswerActivity.class);
                        next.putExtra("correctAnswer", "4");
                        next.putExtra("user", "5");
                        next.putExtra("attempts", attempts + 1);
                        next.putExtra("total", count);
                        startActivity(next);

                        break;
                    case R.id.answer3:
                        next = new Intent(MathQuestionActivity.this, AnswerActivity.class);
                        next.putExtra("correctAnswer", "4");
                        next.putExtra("user", "4");
                        next.putExtra("attempts", attempts + 1);
                        next.putExtra("total", count);
                        startActivity(next);
                        break;
                    case R.id.answer4:
                        next = new Intent(MathQuestionActivity.this, AnswerActivity.class);
                        next.putExtra("correctAnswer", "4");
                        next.putExtra("user", "6");
                        next.putExtra("attempts", attempts + 1);
                        next.putExtra("total", count);
                        startActivity(next);
                        break;

                }
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
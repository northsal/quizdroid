package com.example.iguest.quizdroidpart1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.view.View;
import android.util.Log;
import android.widget.TextView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.view.View.OnClickListener;



public class PhysicsQuestionActivity extends ActionBarActivity {

    final int[] answers = {1,5,4,3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_physics_question);

        TextView question = (TextView) findViewById(R.id.physicsQuestion);

        question.setText("What equation properly relates force, mass and acceleration?");

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.answer1:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.answer2:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.answer3:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.answer4:
                if (checked)
                    // Ninjas rule
                    break;
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
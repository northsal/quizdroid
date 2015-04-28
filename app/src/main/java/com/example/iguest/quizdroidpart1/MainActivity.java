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
import android.content.Intent;



public class MainActivity extends ActionBarActivity {

    public String[] options = {"Math", "Physics", "Marvel Super Heroes"};

    private ListView list;

    private int attempts;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> items = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options);
        list.setAdapter(items);

        attempts = 0;
        count = 0;

        list.setOnItemClickListener(new ListView.OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               if(position == 0) {
                   Intent next = new Intent(MainActivity.this, MathActivity.class);
                   next.putExtra("attempts", attempts);
                   next.putExtra("total", count);
                startActivity(next);
               } else if(position == 1) {
                   Intent next = new Intent(MainActivity.this, PhysicsActivity.class);
                   next.putExtra("attempts", attempts);
                   next.putExtra("total", count);
                   startActivity(next);
               } else if (position == 2) {
                   Intent next = new Intent(MainActivity.this, MarvelActivity.class);
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

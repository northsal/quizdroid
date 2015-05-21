package com.example.iguest.quizdroidpart1;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.ParcelFileDescriptor;
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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    public String[] options;
    public String[] desc;

    private ListView list;

    private int attempts;
    private int count;

    ArrayList<Topic> topics;

    private DownloadManager dm;
    QuizApp quizApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE); // Add more filters here that you want the receiver to listen to
        registerReceiver(receiver, filter);

        list = (ListView) findViewById(R.id.listView);

        quizApp = (QuizApp) getApplication();
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


    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            dm = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);

            Log.i("MyApp BroadcastReceiver", "onReceive of registered download reciever");

            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                Log.i("MyApp BroadcastReceiver", "download complete!");
                long downloadID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);

                // if the downloadID exists
                if (downloadID != 0) {

                    // Check status
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(downloadID);
                    Cursor c = dm.query(query);
                    if(c.moveToFirst()) {
                        int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
                        Log.d("DM Sample","Status Check: "+status);
                        switch(status) {
                            case DownloadManager.STATUS_PAUSED:
                            case DownloadManager.STATUS_PENDING:
                            case DownloadManager.STATUS_RUNNING:
                                break;
                            case DownloadManager.STATUS_SUCCESSFUL:
                                // The download-complete message said the download was "successfu" then run this code
                                ParcelFileDescriptor file;
                                StringBuffer strContent = new StringBuffer("");

                                try {
                                    // Get file from Download Manager (which is a system service as explained in the onCreate)


                                    // YOUR CODE HERE [convert file to String here]
                                    StringBuffer datax = new StringBuffer("");

                                        file = dm.openDownloadedFile(downloadID);
                                        FileInputStream fis = new FileInputStream(file.getFileDescriptor());
                                        InputStreamReader isr = new InputStreamReader(fis);
                                        BufferedReader buffreader = new BufferedReader(isr) ;

                                        String readString = buffreader.readLine ( ) ;
                                        while ( readString != null ) {
                                            datax.append(readString);
                                            readString = buffreader.readLine ( ) ;
                                        }

                                        isr.close ( ) ;
                                    
                                    String jsonString =  datax.toString() ;



                                    // YOUR CODE HERE [write string to data/data.json]
                                    //      [hint, i wrote a writeFile method in MyApp... figure out how to call that from inside this Activity]

                                    quizApp.writeToFile(jsonString);

                                    // convert your json to a string and echo it out here to show that you did download it



                                    /*
                                    String jsonString = ....myjson...to string().... chipotle burritos.... blah
                                    Log.i("MyApp - Here is the json we download:", jsonString);
                                    */

                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case DownloadManager.STATUS_FAILED:
                                // YOUR CODE HERE! Your download has failed! Now what do you want it to do? Retry? Quit application? up to you!
                                break;
                        }
                    }
                }
            }
        }
    };


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
            Intent intent = new Intent(this,UserSettingActivity.class);
            startActivityForResult(intent, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

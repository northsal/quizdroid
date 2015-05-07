package com.example.iguest.quizdroidpart1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


public class TopicOverviewFragment extends Fragment {

    private int qCount;
    private String physics = "What equation properly relates force, mass and acceleration?~~f=m*a~~f=m+a~~f=f-m~~m=f*a~~f=m*a--Which of the following are areas within physics?~~Thermodynamics~~Relativity~~Optics~~All of the above~~All of the above";
    private String math = "What is 2 + 2?~~1~~5~~4~~6~~4";
    private String marvel = "When was the Avengers made?~~2011~~2012~~2013~~2014~~2012";
    private String topic;


    public TopicOverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qCount = 0;
        if (getArguments() != null) {
            topic = getArguments().getString("topic");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_topic_overview, container, false);

        TextView title = (TextView) v.findViewById(R.id.topicTitle);
        TextView overview = (TextView) v.findViewById(R.id.sectionDescr);

        title.setText(topic);

        if(topic.equals("Physics")) {
            overview.setText(R.string.physics_overview);
        } else if (topic.equals("Math")) {
            overview.setText(R.string.math_overview);
        } else if(topic.equals("Marvel")) {
            overview.setText(R.string.marvel_overview);
        }
       final Button start = (Button) v.findViewById(R.id.btnStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle topicBundle = new Bundle();

                if(topic.equals("Physics")) {
                    topicBundle.putString("questions", physics);
                } else if (topic.equals("Math")) {
                    topicBundle.putString("questions", math);
                } else if(topic.equals("Marvel")) {
                    topicBundle.putString("questions", marvel);
                }

                int attempts = getArguments().getInt("attempts", 0);
                int count = getArguments().getInt("count", 0);

                topicBundle.putInt("attempts", attempts);
                topicBundle.putInt("total", count);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                QuestionFragment questionFragment = new QuestionFragment();
                questionFragment.setArguments(topicBundle);
                ft.replace(R.id.container, questionFragment);   // where , what
                ft.commit();

            }
        });
       return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}

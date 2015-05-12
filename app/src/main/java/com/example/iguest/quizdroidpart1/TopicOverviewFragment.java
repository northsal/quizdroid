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

    private Topic topic;


    public TopicOverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //qCount = 0;
        if (getArguments() != null) {
            topic = (Topic) getArguments().getSerializable("TAG");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_topic_overview, container, false);

        TextView title = (TextView) v.findViewById(R.id.topicTitle);
        TextView overview = (TextView) v.findViewById(R.id.sectionDescr);

        title.setText(topic.getTitle());
        overview.setText(topic.getLongDescr());

       final Button start = (Button) v.findViewById(R.id.btnStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle topicBundle = new Bundle();

                topicBundle.putInt("index", 0);
                topicBundle.putSerializable("QUESTIONTAG", topic);

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

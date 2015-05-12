package com.example.iguest.quizdroidpart1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;


public class AnswerFragment extends Fragment {

    private String questions;
    private String answer;
    private String correct;
    private Topic topic;
    private int index;

    public AnswerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            correct = getArguments().getString("correctAnswer");
            answer = getArguments().getString("user");
            questions = getArguments().getString("questions");
            topic = (Topic) getArguments().getSerializable("ANSWERTAG");
            index = getArguments().getInt("index");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_main_answer, container, false);
        TextView userAnswer = (TextView) v.findViewById(R.id.userAnswer);
        TextView correctAnswer = (TextView) v.findViewById(R.id.correctAnswer);
        TextView announceAnswer = (TextView) v.findViewById(R.id.affirm);
        TextView update = (TextView) v.findViewById(R.id.update);

        userAnswer.setText("Your answer was: " + answer);
        correctAnswer.setText("The correct answer was: " + correct);

        if(correct.equals(answer)) {
            announceAnswer.setText("You got it right!");
        } else {
            announceAnswer.setText("You got it wrong!");
        }

        //update.setText("You got " + count + " out of " + attempts + " questions right!");

        Button returnButton = (Button) v.findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(index == (topic.getQuestions()).size()) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), MainActivity.class);
                    getActivity().startActivity(intent);
                } else {
                    Bundle topicBundle = new Bundle();
                    topicBundle.putSerializable("QUESTIONTAG", topic);
                    topicBundle.putInt("index", index);

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();

                    QuestionFragment questionFragment = new QuestionFragment();
                    questionFragment.setArguments(topicBundle);
                    ft.replace(R.id.container, questionFragment);   // where , what
                    ft.commit();
                }

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

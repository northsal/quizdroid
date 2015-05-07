package com.example.iguest.quizdroidpart1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class QuestionFragment extends Fragment {

    private String questionsList;
    private Button button;
    public QuestionFragment() {
        // Required empty public constructor
    }
    public RadioGroup rg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questionsList = getArguments().getString("questions");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.activity_main_question, container, false);
        String[] questions = questionsList.split("--");
        String[] question = questions[0].split("~~");

        TextView topicQuestion = (TextView) v.findViewById(R.id.topicQuestion);
        RadioButton radio1 = (RadioButton) v.findViewById(R.id.answer1);
        RadioButton radio2 = (RadioButton) v.findViewById(R.id.answer2);
        RadioButton radio3 = (RadioButton) v.findViewById(R.id.answer3);
        RadioButton radio4 = (RadioButton) v.findViewById(R.id.answer4);
        topicQuestion.setText(question[0]);
        radio1.setText(question[1]);
        radio2.setText(question[2]);
        radio3.setText(question[3]);
        radio4.setText(question[4]);

        final String answer = question[5];

        //rg = (RadioGroup) v.findViewById(R.id.topicRadioGroup);

        button = (Button) v.findViewById(R.id.btnSubmit);
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

                RadioGroup rg = (RadioGroup) getView().findViewById(R.id.topicRadioGroup);
                if(rg == null) {
                    Log.d("activity", "yay");
                }

                int id = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getView().findViewById(id);

                Bundle topicBundle = new Bundle();
                topicBundle.putString("questions", finalQuestions);
                topicBundle.putString("correctAnswer", answer);
                topicBundle.putString("user", (String) rb.getText());

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                AnswerFragment answerFragment = new AnswerFragment();
                answerFragment.setArguments(topicBundle);
                ft.replace(R.id.container, answerFragment);
                ft.commit();

            }

        });
        return v;
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        if(checked) {
            button.setVisibility(View.VISIBLE);

        }
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

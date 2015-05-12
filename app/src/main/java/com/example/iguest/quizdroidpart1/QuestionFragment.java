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

import java.util.ArrayList;


public class QuestionFragment extends Fragment {

    private Question question;
    private Button button;
    private Topic topic;
    private int index;
    public QuestionFragment() {
        // Required empty public constructor
    }
    public RadioGroup rg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            topic = (Topic) getArguments().getSerializable("QUESTIONTAG");
            ArrayList<Question> questionsList = (ArrayList<Question>) topic.getQuestions();
            index = getArguments().getInt("index");
            question = questionsList.get(index);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.activity_main_question, container, false);

        TextView topicQuestion = (TextView) v.findViewById(R.id.topicQuestion);
        RadioButton radio1 = (RadioButton) v.findViewById(R.id.answer1);
        RadioButton radio2 = (RadioButton) v.findViewById(R.id.answer2);
        RadioButton radio3 = (RadioButton) v.findViewById(R.id.answer3);
        RadioButton radio4 = (RadioButton) v.findViewById(R.id.answer4);
        topicQuestion.setText(question.getQuestion());
        radio1.setText((question.getOptions())[0]);
        radio2.setText((question.getOptions())[1]);
        radio3.setText((question.getOptions())[2]);
        radio4.setText((question.getOptions())[3]);

        final String answer = (question.getOptions())[question.getAnswer() - 1];

        button = (Button) v.findViewById(R.id.btnSubmit);
        button.setVisibility(View.GONE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RadioGroup rg = (RadioGroup) getView().findViewById(R.id.topicRadioGroup);
                if(rg == null) {
                    Log.d("activity", "yay");
                }

                int id = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) getView().findViewById(id);

                index++;
                Bundle topicBundle = new Bundle();
                topicBundle.putSerializable("ANSWERTAG", topic);
                topicBundle.putInt("index", index);
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

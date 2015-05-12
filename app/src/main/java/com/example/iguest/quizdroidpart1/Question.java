package com.example.iguest.quizdroidpart1;

import java.io.Serializable;

/*
This class stores the information related to a single question. It stores the question, answer
options and answer.
*/
public class Question implements Serializable {

    private String question;
    private String[] options;
    private int answer;
    private static final long serialVersionUID = 7836463;


    public Question() {}

    public Question(String question, String[] options, int answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    //Sets question field.
    public void setQuestion(String q) {
        question = q;
    }

    //Returns current question
    public String getQuestion() {
        return question;
    }

    //Takes in an array of possible answers and stores them as options.
    public void setOptions(String[] ans) {
        options = ans;
    }

    //Return array of possible answer options.
    public String[] getOptions() {
        return options;
    }

    //Sets answer for question
    public void setAnswer(int ans) {
        answer = ans;
    }

    //Returns an int, indicates index of correct answer in options array.
    public int getAnswer() {
        return answer;
    }
}

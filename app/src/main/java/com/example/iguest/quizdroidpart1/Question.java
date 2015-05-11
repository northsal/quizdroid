package com.example.iguest.quizdroidpart1;

/*
This class stores the information related to a single question. It stores the question, answer
options and answer.
*/
public class Question {

    private String question;
    private String[] options;
    private int answer;

    public Question() {}

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

    //Returns an int, indicates index of correct asnwer in options array.
    public int getAnswer() {
        return answer;
    }
}

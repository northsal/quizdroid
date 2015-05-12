package com.example.iguest.quizdroidpart1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
This class represents a specific topic and stores the questions that fall under that topic. It also
stores the short and long descriptions of the topic.
 */
public class Topic implements Serializable {

    private String title; //title of Topic
    private String shortDescr;
    private String longDescr;
    List<Question> list; //list of questions associated with that topic
    private static final long serialVersionUID = 2857493;

    public Topic() {
        list = new ArrayList<Question>();
    }

    public Topic(String title, String shortDescr, String longDescr, List<Question> list) {
        this.title = title;
        this.shortDescr = shortDescr;
        this.longDescr = longDescr;
        this.list = list;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setShortDescr(String descr) {
        shortDescr = descr;
    }

    public String getShortDescr() {
        return shortDescr;
    }

    public void setLongDescr(String descr) {
        longDescr = descr;
    }

    public String getLongDescr() {
        return longDescr;
    }

    public List<Question> getQuestions() {
        return list;
    }

    public void setQuestions(List<Question> newList) {
        list = newList;
    }

    public void addQuestion(Question newQ) {
        list.add(newQ);
    }

}

package com.example.iguest.quizdroidpart1;

import java.util.ArrayList;
import java.util.List;

//Keeps track of a list of Topics.
public interface ITopicRepository {

    public List<Topic> getAllTopics();

    //public List<Topic> getTopicsByKeyword(String keyword);
}

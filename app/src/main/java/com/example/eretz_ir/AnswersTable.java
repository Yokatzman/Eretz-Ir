package com.example.eretz_ir;

import java.io.Serializable;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class AnswersTable implements Serializable {
    LinkedHashMap<String, Boolean> answers;

    public AnswersTable(){
        answers = new LinkedHashMap<String, Boolean>();
    }

    public void addAnswer (String definition, boolean answer){
        //true represents a correct answer
        answers.put(definition,answer);
    }

    public boolean doesExist (String definition){
        return answers.containsKey(definition);
    }

    public LinkedHashMap<String,Boolean> getAnswers(){
        return answers;
    }
}

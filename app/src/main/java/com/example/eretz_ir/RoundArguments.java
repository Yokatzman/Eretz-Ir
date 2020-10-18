package com.example.eretz_ir;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class RoundArguments implements Serializable{
    int current_round;
    int current_team; //first team is 1
    ArrayList<Integer> round_scores;

    public RoundArguments (int current_round,int current_team, int size){
        this.current_round=current_round;
        this.current_team=current_team;
        round_scores=new ArrayList<Integer>();
        for (int i=0;i<size;i++){
            round_scores.add(0);
        }
    }
    public RoundArguments(RoundArguments round){
        this.current_round=round.current_round;
        this.current_team=round.current_team;
        round_scores = new ArrayList<Integer>(round.round_scores);
    }
    public int getCurrentTeam(){
        return current_team;
    }
    public void advanceTeam(){
        if (current_team==round_scores.size())
            current_team=1;
        else current_team++;
    }
    public void advanceRound(){
        current_round++;
    }
    public void addPoint(){
        round_scores.set(current_team-1,(round_scores.get(current_team-1))+1);
    }
    public void deductPoint(){
        round_scores.set(current_team-1,(round_scores.get(current_team-1))-1);
    }
    public  ArrayList<Integer> getRoundWinner(){
        int max = -1;
        int i=0;
        ArrayList<Integer> maxes = new ArrayList<Integer>();
        for (;i<round_scores.size();i++){
            if (round_scores.get(i)>max){
                maxes.clear();
                maxes.add(i+1);
                max = round_scores.get(i);
            } else if (round_scores.get(i)==max){
                maxes.add(i+1);
            }
        }
        return maxes;
    }
    public int getCurrentRound(){
        return current_round;
    }
}

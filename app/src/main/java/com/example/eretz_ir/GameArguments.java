package com.example.eretz_ir;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameArguments implements Serializable {
    int num_of_teams;
    int num_of_rounds;
    int secs_per_round;
    int secs_per_question;
    ArrayList<Integer> game_scores;


    public GameArguments(int num_of_teams, int num_of_rounds, int secs_per_round, int secs_per_question){
        this.num_of_teams = num_of_teams;
        this.num_of_rounds = num_of_rounds;
        this.secs_per_round = secs_per_round;
        this.secs_per_question=secs_per_question;
        game_scores=new ArrayList<Integer>();
        for (int i=0;i<num_of_teams;i++){
            game_scores.add(0);
        }

    }

    public GameArguments(GameArguments game){
        this.num_of_teams = game.num_of_teams;
        this.num_of_rounds = game.num_of_rounds;
        this.secs_per_round = game.secs_per_round;
        this.secs_per_question= game.secs_per_question;
        this.game_scores = game.game_scores;
    }

    public void addPoint(ArrayList<Integer> round_winners){
        for (int winner: round_winners)
            game_scores.set(winner-1,(game_scores.get(winner-1))+1);
    }
    public int getNumOfRounds(){
        return num_of_rounds;
    }
    public ArrayList<Integer> getWinners(){
        int max = -1;
        int i=0;
        ArrayList<Integer> maxes = new ArrayList<Integer>();
        for (;i<game_scores.size();i++){
            if (game_scores.get(i)>max){
                maxes.clear();
                maxes.add(i+1);
                max = game_scores.get(i);
            } else if (game_scores.get(i)==max){
                maxes.add(i+1);
            }
        }
        return maxes;
    }

}

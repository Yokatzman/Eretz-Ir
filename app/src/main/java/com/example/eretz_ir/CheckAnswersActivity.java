package com.example.eretz_ir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CheckAnswersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_answers);

        Intent finishedIntent = getIntent();
        final GameArguments gameArgs = (GameArguments) finishedIntent.getSerializableExtra("GAME_ARGS");
        final RoundArguments roundArgs= (RoundArguments) finishedIntent.getSerializableExtra("ROUND_ARGS");
        AnswersTable answersTable = (AnswersTable) finishedIntent.getSerializableExtra("ANSWERS");
        LinkedHashMap<String,Boolean> answers = answersTable.getAnswers();
        int text_size = 20;

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.bbb);
        ConstraintSet set = new ConstraintSet();

        int i = 0;
        final TextView score = new TextView(this);
        score.setText("תשובות נכונות: "+roundArgs.getCurrentScore());
        score.setId(answers.size()+2);
        for (final Map.Entry<String,Boolean> answer :answers.entrySet()){

            if (i==0){
                //adding answers
                final Switch upper = new Switch(this);
                upper.setId(i+1);
                upper.setText(answer.getKey());
                upper.setTextSize(text_size);
                upper.setChecked(answer.getValue());
                if (answer.getValue())
                    upper.setBackgroundColor(getResources().getColor(R.color.correct));
                else upper.setBackgroundColor(getResources().getColor(R.color.incorrect));
                upper.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (answer.getValue()){
                            upper.setBackgroundColor(getResources().getColor(R.color.incorrect));
                            answer.setValue(false);
                            roundArgs.deductPoint();
                            score.setText("תשובות נכונות: "+roundArgs.getCurrentScore());
                        }
                        else{
                            upper.setBackgroundColor(getResources().getColor(R.color.correct));
                            answer.setValue(true);
                            roundArgs.addPoint();
                            score.setText("תשובות נכונות: "+roundArgs.getCurrentScore());
                        }



                    }
                });
                layout.addView(upper, new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                set.clone(layout);
                set.connect(upper.getId(),ConstraintSet.LEFT,R.id.scrollable,ConstraintSet.RIGHT,20);
                set.applyTo(layout);
                i++;
                continue;
            }
            Switch upper = (Switch) findViewById(i);
            final Switch lower = new Switch(this);
            lower.setId(i+1);
            lower.setText(answer.getKey());
            lower.setTextSize(text_size);
            lower.setChecked(answer.getValue());
            if (answer.getValue())
                lower.setBackgroundColor(getResources().getColor(R.color.correct));
            else lower.setBackgroundColor(getResources().getColor(R.color.incorrect));

            lower.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (answer.getValue()){
                        lower.setBackgroundColor(getResources().getColor(R.color.incorrect));
                        answer.setValue(false);
                        roundArgs.deductPoint();
                        score.setText("תשובות נכונות: "+roundArgs.getCurrentScore());
                    }
                    else{
                        lower.setBackgroundColor(getResources().getColor(R.color.correct));
                        answer.setValue(true);
                        roundArgs.addPoint();
                        score.setText("תשובות נכונות: "+roundArgs.getCurrentScore());
                    }



                }
            });
            layout.addView(lower, new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            set.clone(layout);
            set.connect(lower.getId(),ConstraintSet.TOP,upper.getId(),ConstraintSet.BOTTOM,10);
            set.connect(lower.getId(),ConstraintSet.LEFT,R.id.scrollable,ConstraintSet.RIGHT,20);
            set.applyTo(layout);

            i++;
            if (i==answers.size()){

                Button proceed = new Button(this);
                proceed.setId(i+1);
                proceed.setText("המשך!");

                proceed.setTextSize(text_size+4);
                layout.addView(proceed,new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                layout.addView(score,new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                set.clone(layout);
                set.connect(score.getId(),ConstraintSet.LEFT,R.id.scrollable,ConstraintSet.RIGHT,0);
                set.connect(score.getId(),ConstraintSet.RIGHT,R.id.scrollable,ConstraintSet.LEFT,0);
                set.connect(score.getId(),ConstraintSet.TOP,lower.getId(),ConstraintSet.BOTTOM,20);
                set.connect(proceed.getId(),ConstraintSet.TOP,score.getId(),ConstraintSet.BOTTOM,20);
                set.connect(proceed.getId(),ConstraintSet.LEFT,R.id.scrollable,ConstraintSet.RIGHT,0);
                set.connect(proceed.getId(),ConstraintSet.RIGHT,R.id.scrollable,ConstraintSet.LEFT,0);
                set.applyTo(layout);
                proceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GameArguments GameCopy = new GameArguments(gameArgs);
                        RoundArguments RoundCopy = new RoundArguments(roundArgs);
                        if (roundArgs.current_team==gameArgs.num_of_teams){

                            Context currScreen = CheckAnswersActivity.this;
                            Class endRound= EndRoundActivity.class;
                            RoundCopy.advanceTeam();
                            Intent intentRound = new Intent(currScreen,endRound);
                            intentRound.putExtra("GAME_ARGS",GameCopy);
                            intentRound.putExtra("ROUND_ARGS",RoundCopy);
                            startActivity(intentRound);
                        }
                        else {
                            RoundCopy.advanceTeam();
                            Context currScreen = CheckAnswersActivity.this;
                            Class round= StartRoundActivity.class;
                            Intent intentRound = new Intent(currScreen, round);
                            intentRound.putExtra("GAME_ARGS",GameCopy);
                            intentRound.putExtra("ROUND_ARGS",RoundCopy);
                            startActivity(intentRound);
                        }
                    }
                });

            }
        }

    }
}
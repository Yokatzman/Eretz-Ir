package com.example.eretz_ir;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int num_of_teams = 2;
    int num_of_rounds = 1;
    int secs_per_round = 20;
    int secs_per_question = 3;
    private NumberPicker teamsNumPicker;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamsNumPicker = (NumberPicker) findViewById(R.id.numberPickerTeams);
        teamsNumPicker.setMaxValue(5);
        teamsNumPicker.setMinValue(2);
        teamsNumPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                num_of_teams = newVal;
            }
        });

        NumberPicker roundsNumPicker = (NumberPicker) findViewById(R.id.numberPickerRounds);
        roundsNumPicker.setMaxValue(7);
        roundsNumPicker.setMinValue(1);
        roundsNumPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                num_of_rounds = newVal;
            }
        });

        NumberPicker secsRoundNumPicker = (NumberPicker) findViewById(R.id.numberPickerSecsPerRound);
        secsRoundNumPicker.setMaxValue(90);
        secsRoundNumPicker.setMinValue(20);
        secsRoundNumPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                secs_per_round = newVal;
            }
        });

        NumberPicker secsQuestionNumPicker = (NumberPicker) findViewById(R.id.numberPickerSecsPerQuestion);
        secsQuestionNumPicker.setMaxValue(30);
        secsQuestionNumPicker.setMinValue(3);
        secsQuestionNumPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                secs_per_question = newVal;
            }
        });

        Button startGame = (Button) findViewById(R.id.startButton);
        startGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;
                Class destActivity = StartRoundActivity.class;
                GameArguments args = new GameArguments(num_of_teams, num_of_rounds, secs_per_round, secs_per_question);
                RoundArguments new_game = new RoundArguments(1,1,num_of_teams);


                Intent intentGame = new Intent(context, destActivity);
                intentGame.putExtra("GAME_ARGS",args);
                intentGame.putExtra("ROUND_ARGS",new_game);

                startActivity(intentGame);
            }
        });


    }
}

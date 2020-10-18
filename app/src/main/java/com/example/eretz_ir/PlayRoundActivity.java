package com.example.eretz_ir;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.eretz_ir.Utilities.*;

public class PlayRoundActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_round);

        Intent roundIntent = getIntent();
        final GameArguments gameArgs = (GameArguments) roundIntent.getSerializableExtra("GAME_ARGS");
        final RoundArguments roundArgs= (RoundArguments) roundIntent.getSerializableExtra("ROUND_ARGS");

        //randomizing a letter
        TextView letter = (TextView) findViewById(R.id.actualLetter);
        String ot = roundIntent.getStringExtra("OT");
        letter.setText(ot);

        //printing first defintion
        final TextView definition = (TextView) findViewById(R.id.definition);
        final String[] current_def = {randDefinition()};
        definition.setText(current_def[0]);
        final AnswersTable answersTable = new AnswersTable();

        //printing scores
        String score = print_score(gameArgs.game_scores);
        TextView scoreText = (TextView) findViewById(R.id.currScore);
        scoreText.setText(score);

        //Timer sequence
        final Integer[] times_left = {gameArgs.secs_per_round, gameArgs.secs_per_question};
        final TextView roundTime = (TextView) findViewById(R.id.roundTime);
        final TextView questionTime = (TextView) findViewById(R.id.questionTime);
        //ROUND TIMER
        final int[] timer_array={gameArgs.secs_per_round};
        final boolean round_finished = false;
        CountDownTimer roundTimer = new CountDownTimer(gameArgs.secs_per_round*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                roundTime.setText("" + millisUntilFinished / 1000);

            }

            public void onFinish() {
                timer_array[0]=0;
                answersTable.addAnswer(current_def[0],false);
                //moving to next screen
                Context currScreen = PlayRoundActivity.this;
                Class table= CheckAnswersActivity.class;
                Intent intent = new Intent(currScreen, table);
                GameArguments GameCopy = new GameArguments(gameArgs);
                RoundArguments RoundCopy = new RoundArguments(roundArgs);
                intent.putExtra("GAME_ARGS",GameCopy);
                intent.putExtra("ROUND_ARGS",roundArgs);
                intent.putExtra("ANSWERS",answersTable);
                startActivity(intent);
            }
        }.start();

        //QUESTION TIMER
        final CountDownTimer questTimer = new CountDownTimer(gameArgs.secs_per_question*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                questionTime.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                if (timer_array[0]!=0){
                    answersTable.addAnswer(current_def[0],false);
                    //get a new definition
                    do {
                        current_def[0] = randDefinition();
                    }while (answersTable.doesExist(current_def[0]));
                    definition.setText(current_def[0]);
                    this.start();
                }

            }
        }.start();

        //answer buttons response

        Button correct = (Button) findViewById(R.id.correct);
        correct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                roundArgs.addPoint();
                answersTable.addAnswer(current_def[0],true);
                do {
                    current_def[0] = randDefinition();
                }while (answersTable.doesExist(current_def[0]));
                definition.setText(current_def[0]);
                questTimer.start();

            }
        });
        Button incorrect = (Button) findViewById(R.id.incorrect);
        incorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answersTable.addAnswer(current_def[0],false);
                do {
                    current_def[0] = randDefinition();
                }while (answersTable.doesExist(current_def[0]));
                definition.setText(current_def[0]);
                questTimer.start();
            }
        });




    }
}
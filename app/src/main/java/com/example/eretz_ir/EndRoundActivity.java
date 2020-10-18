package com.example.eretz_ir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class EndRoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_round);
        TextView winMessage = (TextView) findViewById(R.id.winnerMessage);
        Intent roundIntent = getIntent();
        final GameArguments gameArgs = (GameArguments) roundIntent.getSerializableExtra("GAME_ARGS");
        final RoundArguments roundArgs= (RoundArguments) roundIntent.getSerializableExtra("ROUND_ARGS");
        ArrayList<Integer> winners = roundArgs.getRoundWinner();
        String message="";
        if (winners.size()>1){
            message = "הקבוצות המנצחות של הסיבוב:";
            for (int x: winners)
                message=message+x+"  ";

        } else {
            message = "הקבוצה המנצחת של הסיבוב:"+winners.get(0);
        }
        winMessage.setText(message);
        gameArgs.addPoint(winners);

        Button proceed = (Button) findViewById(R.id.proceedRound);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameArguments gameCopy = new GameArguments(gameArgs);
                RoundArguments roundCopy = new RoundArguments(roundArgs);
                Context context = EndRoundActivity.this;
                Class destActivity;
                if (gameArgs.getNumOfRounds()==roundArgs.getCurrentRound()){
                    destActivity=EndGameActivity.class;
                } else {
                    roundCopy.advanceRound();
                    destActivity = StartRoundActivity.class;
                }


                Intent newRoundIntent = new Intent(context,destActivity);
                newRoundIntent.putExtra("GAME_ARGS",gameCopy);
                newRoundIntent.putExtra("ROUND_ARGS",roundCopy);
                startActivity(newRoundIntent);
            }
        });


    }
}
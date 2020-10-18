package com.example.eretz_ir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class EndGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        TextView gameWinner = (TextView) findViewById(R.id.gameWinner);
        Intent gameIntent = getIntent();
        final GameArguments gameArgs = (GameArguments) gameIntent.getSerializableExtra("GAME_ARGS");
        ArrayList<Integer> winners = gameArgs.getWinners();
        String message="";
        if (winners.size()>1){
            message = "הקבוצות המנצחות:";
            for (int x: winners)
                message=message+x+"  ";

        } else {
            message = "הקבוצה המנצחת:"+winners.get(0);
        }
        gameWinner.setText(message);

        Button endGame = (Button) findViewById(R.id.endGame);
        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = EndGameActivity.this;
                Class destActivity = MainActivity.class;
                Intent homeIntent = new Intent(context,destActivity);
                startActivity(homeIntent);

            }
        });

    }
}
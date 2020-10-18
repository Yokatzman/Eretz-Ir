package com.example.eretz_ir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.eretz_ir.Utilities.Ot;

public class StartRoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_round);
        TextView curr=(TextView) findViewById(R.id.currGroup);
        Button startRound = (Button) findViewById(R.id.startRound);

        Intent prevRound= getIntent();
        final GameArguments gameArgs = (GameArguments) prevRound.getSerializableExtra("GAME_ARGS");
        final RoundArguments roundArgs= (RoundArguments) prevRound.getSerializableExtra("ROUND_ARGS");
        int curr_team=roundArgs.current_team;
        String currText="קבוצה מס: "+curr_team;
        curr.setText(currText);

        TextView letter = (TextView) findViewById(R.id.letter);
        final String ot = Ot();
        letter.setText(ot);

        startRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert gameArgs != null;
                //using copy constructors to avoid passing final classes
                GameArguments GameCopy = new GameArguments(gameArgs);
                RoundArguments RoundCopy = new RoundArguments(roundArgs);
                Context currScreen = StartRoundActivity.this;
                Class round= PlayRoundActivity.class;
                Intent intentRound = new Intent(currScreen, round);
                intentRound.putExtra("GAME_ARGS",GameCopy);
                intentRound.putExtra("ROUND_ARGS",RoundCopy);
                intentRound.putExtra("OT", ot);
                startActivity(intentRound);
            }
        });



    }
}


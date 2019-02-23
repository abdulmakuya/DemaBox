package com.example.eltiwany.demabox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.R.attr.id;
import static android.R.attr.visible;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int playerturn = 0;

    //gamestate will allow us to know if a certain counter(kete) is played.
    //so we will match the key/pos of the counter with the index of
    //the key = index = the position and
    //value = 1 or 2 or 3
    int[] gamestate = {2,2,2,2,2,2,2,2,2};

    int[][] winningpositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view){

        ImageView kete = (ImageView) view;
        //getting the postion of the played counter
        int tappedcounter = Integer.parseInt(kete.getTag().toString());

        //looking if counter/position is already played
        if (gamestate[tappedcounter] == 2){

            gamestate[tappedcounter] = playerturn;

        kete.setTranslationY(-1000f);

        if (playerturn == 0){

            kete.setImageResource(R.drawable.blue);

            playerturn = 1;}

        else {
            kete.setImageResource(R.drawable.brown);

            playerturn = 0;
        }
        kete.animate().translationYBy(1000f).setDuration(300);

        for(int [] winningposition:winningpositions ){
            if (gamestate[winningposition[0]] == gamestate[winningposition[1]] &&
                gamestate[winningposition[1]] == gamestate[winningposition[2]] &&
                gamestate[winningposition[0]] != 2    ){

                if (gamestate[winningposition[0]] == 0){

                    LinearLayout play_again = (LinearLayout)findViewById(R.id.layout_play_again);
                    play_again.setVisibility(VISIBLE);
                    //Toast.makeText(this,"Player blue has won",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(this,"Player brown" +
                            " has won",Toast.LENGTH_LONG).show();
                }
                //System.out.println(gamestate[winningposition[0]]);
            }
        }
    }
}
}

package com.example.maryc.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private TicTacToeGame mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGame = new TicTacToeGame(this);

    }
        public void pressedSquare(View view){
            String tagAsString = view.getTag().toString();
            int tagAsInt = Integer.parseInt(tagAsString);
            //Log.d("TTT", "You pressed index " + tagAsInt);
            //Toast.makeText(this, "You pressed index " + tagAsInt, Toast.LENGTH_SHORT).show();
            mGame.pressedButtonAtIndex(tagAsInt);
            //updateView();

        }
    public void pressNewGame (View view)
    {
        //Toast.makeText(this, "New Game",Toast.LENGTH_SHORT).show();
        mGame   = new TicTacToeGame(this);
       // updateView();
    }


    }


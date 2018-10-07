package com.example.maryc.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TicTacToeGame mGame;
    private TextView mGameStateTextView;
    private Button[] mButtons;
    String tagAsString;
    int tagAsInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGame   = new TicTacToeGame(this);
        mGameStateTextView = findViewById(R.id.game_state_textview);
        mButtons = new Button[TicTacToeGame.NUM_SQUARES];
        mButtons[0] = findViewById(R.id.button0);
        mButtons[1] = findViewById(R.id.button1);
        mButtons[2] = findViewById(R.id.button2);
        mButtons[3] = findViewById(R.id.button3);
        mButtons[4] = findViewById(R.id.button4);
        mButtons[5] = findViewById(R.id.button5);
        mButtons[6] = findViewById(R.id.button6);
        mButtons[7] = findViewById(R.id.button7);
        mButtons[8] = findViewById(R.id.button8);
        updateView();

    }
    private void updateView() {
        mGameStateTextView.setText(mGame.stringForGameState());
        // add x/o to button
        for (int i=0;i < TicTacToeGame.NUM_SQUARES;i++)
        {
            mButtons[i].setText(mGame.stringForButtonAtIndex(i));
        }
    }
    public void pressedSquare(View view){
            String tagAsString = view.getTag().toString();
            int tagAsInt = Integer.parseInt(tagAsString);
            //Log.d("TTT", "You pressed index " + tagAsInt);
            //Toast.makeText(this, "You pressed index " + tagAsInt, Toast.LENGTH_SHORT).show();
            mGame.pressedButtonAtIndex(tagAsInt);
            updateView();

        }
    public void pressNewGame (View view)
    {
        //Toast.makeText(this, "New Game",Toast.LENGTH_SHORT).show();
        mGame   = new TicTacToeGame(this);
       updateView();
    }


    }


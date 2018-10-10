package com.example.maryc.lightsout;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private LightsOutGame mGame;
    private TextView mGameStateTextView;
    private Button[] mButtons;
    private int mNumButtons = 7;
    String tagAsString;
    int tagAsInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGame   = new LightsOutGame(mNumButtons);
        mGameStateTextView = findViewById(R.id.game_state_textview);
        mButtons = new Button[mNumButtons];
        mButtons[0] = findViewById(R.id.button0);
        mButtons[1] = findViewById(R.id.button1);
        mButtons[2] = findViewById(R.id.button2);
        mButtons[3] = findViewById(R.id.button3);
        mButtons[4] = findViewById(R.id.button4);
        mButtons[5] = findViewById(R.id.button5);
        mButtons[6] = findViewById(R.id.button6);

        updateView();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    public void pressedLight (View view) {
        tagAsString = view.getTag().toString();
        tagAsInt = Integer.parseInt(tagAsString);
        //Log.d("TTT", "You pressed index " + tagAsInt);
        //Toast.makeText(this, "You pressed index " + tagAsInt, Toast.LENGTH_SHORT).show();
        mGame.pressedButtonAtIndex(tagAsInt);
        updateView();
    }
    public void pressNewGame (View view)
    {
        //Toast.makeText(this, "New Game",Toast.LENGTH_SHORT).show();
        mGame   = new LightsOutGame(mNumButtons);
        updateView();
        for (int i=0;i < mNumButtons ;i++)
        {
            mButtons[i].setEnabled(true);
            mButtons[i].setBackgroundColor(getResources().getColor(R.color.buttonBackground));
            mButtons[i].setTextColor(getResources().getColor(R.color.buttonText));
        }
        mGameStateTextView = findViewById(R.id.game_state_textview);
    }
    public void updateView()
    {
        for (int i=0;i < mNumButtons ;i++)
        {
            mButtons[i].setText(Integer.toString(mGame.getValueAtIndex(i)));
        }
        if (mGame.checkForWin() == true)
        {
            mGameStateTextView.setText(R.string.win);
            for (int i=0;i < mNumButtons;i++)
            {
                mButtons[i].setEnabled(false);
                mButtons[i].setTextColor(getResources().getColor(R.color.buttonBackground));
            }

        } else {
            if (mGame.getNumPresses() == 0)
                mGameStateTextView.setText(R.string.start);
            else if (mGame.getNumPresses() == 1)
                mGameStateTextView.setText(R.string.first_turn);

            else
                mGameStateTextView.setText(getString(R.string.turn_count, mGame.getNumPresses()));
        }


    }
}
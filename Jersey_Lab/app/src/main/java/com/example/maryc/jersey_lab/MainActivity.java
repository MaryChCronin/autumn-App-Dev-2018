package com.example.maryc.jersey_lab;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Jersey mJersey;
    private TextView mPlayerName;
    private TextView mJerseyNumberTextView;
    private Button mGreenButton;

    private Button mPurpleButton;
    private ImageView mImageView;
    public final static String PREFS = "PREFS";
    private static final String KEY_JERSEY_NAME = "KEY_JERSEY_NAME";
    private static final String KEY_JERSEY_NUMBER = "KEY_JERSEY_NUMBER";
    private static final String KEY_JERSEY_DEFAULT = "KEY_JERSEY_DEFAULT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mPlayerName = findViewById(R.id.playerName);
        mJerseyNumberTextView = findViewById(R.id.playerNumber);
        mImageView = findViewById(R.id.imageView);
        setSupportActionBar(toolbar);

        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String name = prefs.getString(KEY_JERSEY_NAME, getString(R.string.default_name));
        int number = prefs.getInt(KEY_JERSEY_NUMBER, 17);
        boolean default_colour = prefs.getBoolean(KEY_JERSEY_DEFAULT, true);

        mJersey = new Jersey(name, number, default_colour);
        ChangeJersey();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editJersey();
            }
        });
    }

    private void ChangeJersey() {
        mPlayerName.setText(mJersey.getPlayerName().toString());
        mJerseyNumberTextView.setText("" + mJersey.getJerseyNumber());
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_JERSEY_NAME, mJersey.getPlayerName());
        editor.putInt(KEY_JERSEY_NUMBER, mJersey.getJerseyNumber());
        editor.putBoolean(KEY_JERSEY_DEFAULT, mJersey.getDefaultColour());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_reset) {
            resetJersey();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void resetJersey() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.undo);
        builder.setMessage(R.string.undo_sure);
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mJersey = new Jersey();
                ChangeJersey();
                SetColour(true);
            }
        });
        builder.create().show();
    }

    private void editJersey() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update Jersey Info");


        View view = getLayoutInflater().inflate(R.layout.dialog_edit, null, false);
        builder.setView(view);
        final EditText nameEditText = view.findViewById(R.id.edit_player_name);
        final EditText numberEditText = view.findViewById(R.id.edit_jersey_number);
        mGreenButton = view.findViewById(R.id.button_green);
        mGreenButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                SetColour(true);
                                            }
                                        }
        );


        mPurpleButton = view.findViewById(R.id.button_purple);
        mPurpleButton.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 SetColour(false);
                                             }
                                         }
        );
        nameEditText.setText(mJersey.getPlayerName().toString());
        numberEditText.setText("" + mJersey.getJerseyNumber());

        if (mJersey.getDefaultColour()) {
            mGreenButton.setEnabled(false);
            mPurpleButton.setEnabled(true);
        } else {
            mGreenButton.setEnabled(true);
            mPurpleButton.setEnabled(false);
        }


        builder.setNegativeButton(android.R.string.cancel, null);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = nameEditText.getText().toString();
                int quantity = Integer.parseInt(numberEditText.getText().toString());
                mJersey.setPlayerName(name);
                mJersey.setJerseyNumber(quantity);
                ChangeJersey();
            }
        });

        builder.create().show();
    }

    public void SetColour(boolean defaultColour) {
        if (defaultColour == false) {
            mJersey.setDefaultColour(false);
            mGreenButton.setEnabled(true);
            mPurpleButton.setEnabled(false);
            mImageView.setImageResource(R.drawable.purple_jersey);
        } else {
            mJersey.setDefaultColour(true);
            mGreenButton.setEnabled(false);
            mPurpleButton.setEnabled(true);
            mImageView.setImageResource(R.drawable.green_jersey);
        }
    }
}
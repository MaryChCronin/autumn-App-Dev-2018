package com.example.maryc.counter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mMessageTextView;
    private int mCounter=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this method is called when the app launches
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //code you add goes after the setContentView call
        mMessageTextView = findViewById(R.id.message_textView);
        //mMessageTextView.setText("Mary is Cool");
        final Button resetButton =findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCounter = 0;
                updateView();
            }
        });

        Log.d("Counter Button","This is a log, Logs are important");
    }
    public void handleDecrement(View view){
    mCounter= mCounter - 1;
      updateView();}




    public void handleIncrement(View view){
    mCounter=mCounter +1;
    updateView();
    }

    private void updateView(){
        mMessageTextView.setText(getString(R.string.message_format,             mCounter));
}
}
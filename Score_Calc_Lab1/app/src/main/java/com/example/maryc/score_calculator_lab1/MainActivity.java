package com.example.maryc.score_calculator_lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mMessageTextView;
    private TextView mWBMessageTextView;

    private int mColorPoints = 0;

    private int mNearBall = 0;
    private int mNearBallPoints = 0;

    private int mFarBall = 0;
    private int mFarBallPoints = 0;

    private int mRobotHome = 0;
    private int mRobotHomePoints = 0;

    private int mDistancePoints = 0;
    private int mWBPoints = 0;
    private int mTotalPoints = 0;

    private TextView color_points_text;
    private TextView wb_points_text;
    private TextView near_ball_points_text;
    private TextView far_ball_points_text;
    private TextView robot_home_points_text;
    private TextView total_points_text;

    private EditText mNear_ball_edit;
    private EditText mFar_ball_edit;
    private EditText mRobot_home_edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this method is called when the app launches
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNear_ball_edit = (EditText)findViewById(R.id.near_ball_distance);
        mFar_ball_edit = (EditText)findViewById(R.id.far_ball_distance);
        mRobot_home_edit=(EditText)findViewById(R.id.robot_home_distance);

        color_points_text= findViewById(R.id.right_text_color_points);
        wb_points_text= findViewById(R.id.wb_points);
        near_ball_points_text = findViewById(R.id.near_ball_points);
        far_ball_points_text= findViewById(R.id.far_ball_points);
        robot_home_points_text = findViewById(R.id.robot_home_points);
        total_points_text = findViewById(R.id.total_points);

       mMessageTextView = findViewById(R.id.right_text_color_points);
       mWBMessageTextView = findViewById(R.id.wb_points);

        Button Color_button_3 = findViewById(R.id.Color_button_3);
        Button Color_button_2 = findViewById(R.id.Color_button_2);
        Button Color_button_1 = findViewById(R.id.Color_button_1);
        Button Color_button_0 = findViewById(R.id.Color_button_0);
        Button WB_F = findViewById(R.id.wb_fail);
        Button WB_S = findViewById(R.id.wb_success);
        Button Update_button = findViewById(R.id.Update);
        Button reset_button = findViewById(R.id.reset_button);


        Color_button_3.setOnClickListener(this);
        Color_button_2.setOnClickListener(this);
        Color_button_1.setOnClickListener(this);
        Color_button_0.setOnClickListener(this);
        WB_F.setOnClickListener(this);
        WB_S.setOnClickListener(this);
        reset_button.setOnClickListener(this);

        final TextView near_bal_editText = findViewById(R.id.near_ball_distance);
        near_bal_editText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                try {
                    mNearBall = Integer.parseInt(near_bal_editText.getText().toString());
                } catch (Exception ex) {
                    mNearBall = 9999;
                }
                mNearBallPoints = 0;
                if (mNearBall <= 5)
                    mNearBallPoints = 110;
                else if (mNearBall <= 10)
                    mNearBallPoints = 100;
                else if (mNearBall <= 20)
                    mNearBallPoints = 80;
                else if (mNearBall<= 30)
                    mNearBallPoints = 50;
                else if (mNearBall <= 45)
                    mNearBallPoints = 10;

                CalcTotalPoints();
                total_points_text.setText(mTotalPoints + " Points");
                near_ball_points_text.setText(Integer.toString(mNearBallPoints));

            }
        });
        final TextView far_ball_editText = findViewById(R.id.far_ball_distance);
        far_ball_editText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                try {
                    mFarBall = Integer.parseInt(far_ball_editText.getText().toString());
                } catch (Exception ex) {
                    mFarBall = 9999;
                }
                mFarBallPoints = 0;
                if (mFarBall <= 5)
                    mFarBallPoints = 220;
                else if (mFarBall <= 10)
                    mFarBallPoints = 200;
                else if (mFarBall<= 20)
                    mFarBallPoints = 160;
                else if (mFarBall <= 30)
                    mFarBallPoints = 100;
                else if (mFarBall <= 45)
                    mFarBallPoints = 20;

                CalcTotalPoints();
                total_points_text.setText(mTotalPoints + " Points");
                far_ball_points_text.setText(Integer.toString(mFarBallPoints));
            }
        });
        final TextView robot_home_editText = findViewById(R.id.robot_home_distance);
        robot_home_editText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                try {
                    mRobotHome = Integer.parseInt(robot_home_editText.getText().toString());
                } catch (Exception ex) {
                    mRobotHome = 9999;
                }
                mRobotHomePoints = 0;
                if (mRobotHome <= 5)
                    mRobotHomePoints = 110;
                else if (mRobotHome <= 10)
                    mRobotHomePoints = 100;
                else if (mRobotHome <= 20)
                    mRobotHomePoints = 80;
                else if (mRobotHome <= 30)
                    mRobotHomePoints = 50;
                else if (mRobotHome <= 45)
                    mRobotHomePoints = 10;


                CalcTotalPoints();
                total_points_text.setText(mTotalPoints + " Points");
                robot_home_points_text.setText(Integer.toString(mRobotHomePoints));
            }
        });


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Color_button_3:
                mColorPoints = 0;
                updateView();
                break;
            case R.id.Color_button_2:
                mColorPoints = 25;
                updateView();
                break;
            case R.id.Color_button_1:
                mColorPoints = 75;
                updateView();
                break;
            case R.id.Color_button_0:
                mColorPoints = 150;
                updateView();
                break;
        }
        switch (v.getId()) {
            case R.id.wb_fail:
                mWBPoints = 0;
                updateWBView();
                break;
            case R.id.wb_success:
                mWBPoints = 60;
                updateWBView();
                break;
        }


        switch (v.getId()) {
            case R.id.reset_button:
                mColorPoints = 0;
                mNearBall = 0;
                mFarBall = 0;
                mRobotHome = 0;
                mNearBallPoints = 0;
                mFarBallPoints = 0;
                mRobotHomePoints = 0;
                mDistancePoints = 0;
                mWBPoints = 0;
                mTotalPoints = 0;

                mNear_ball_edit.setText("");
                mFar_ball_edit.setText("");
                mRobot_home_edit.setText("");
                color_points_text.setText(R.string.Color_message_total);
                wb_points_text.setText(R.string.white_black_poimts);


        }
        switch (v.getId()) {
            case R.id.Update:
                near_ball_points_text.setText(Integer.toString(mNearBallPoints));
                far_ball_points_text.setText(Integer.toString(mFarBallPoints));
                robot_home_points_text.setText(Integer.toString(mRobotHomePoints));

                CalcTotalPoints();

        }
        CalcTotalPoints();
    }


    private void updateView() {
        mMessageTextView.setText(getString(R.string.Color_message_total_sum, mColorPoints));

    }
    private void updateWBView() {
        mWBMessageTextView.setText(getString(R.string.white_black_total, mWBPoints));

    }

    private void CalcTotalPoints() {
        mDistancePoints = mNearBallPoints + mFarBallPoints + mRobotHomePoints;
        mTotalPoints = mColorPoints + mDistancePoints + mWBPoints;
        total_points_text.setText(mTotalPoints + " Points");
    }


}
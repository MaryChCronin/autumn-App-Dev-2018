package com.example.maryc.jerseylab;


import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Jersey {
    private String mPlayerName;
    private int mPlayerNumber;
    private boolean mDefaultColour;



    public Jersey() {
        mPlayerName = "Ireland";
        mPlayerNumber = 1;
        mDefaultColour = true;

    }

    public Jersey(String playerName, int playerNum, boolean defaultColor) {
        mPlayerName = playerName;
        mPlayerNumber = playerNum;
        mDefaultColour=defaultColor;

    }

    public static Jersey getDefaultItem() {
        return new Jersey("Ireland", 1,true);
    }

    public String getPlayerName() {
        return mPlayerName;
    }

    public void setName(String playerName) {
        mPlayerName = playerName;
    }
    public void setPlayerNum(int playerNum) {
        mPlayerNumber = playerNum;
    }

    public int getPlayerNum() {
        return mPlayerNumber;
    }
    public void setDefaultColour(boolean defaultColour)
    {
        mDefaultColour = defaultColour;
    }
    public boolean getDefaultColour() {
        return mDefaultColour;
    }


}

package com.example.maryc.jersey_lab;
public class Jersey {
    private String mPlayerName;
    private int mPlayerNumber;
    private boolean mDefaultColour;

    public Jersey()
    {
        mPlayerName = "Cronin";
        mPlayerNumber = 21;
        mDefaultColour = true;

    }
    public Jersey (String playerName, int playerNumber, boolean defaultColour)
    {
        mPlayerName = playerName;
        mPlayerNumber = playerNumber;
        mDefaultColour = defaultColour;

    }
    public String getPlayerName()
    {
        return mPlayerName;
    }
    public int getJerseyNumber()
    {
        return mPlayerNumber;
    }

    public boolean getDefaultColour() {
        return mDefaultColour;
    }
    public void setPlayerName(String playerName)
    {
        mPlayerName = playerName;
    }
    public void setJerseyNumber(int playerNumber)
    {
        mPlayerNumber = playerNumber;
    }
    public void setDefaultColour(boolean defaultColour)
    {
        mDefaultColour = defaultColour;
    }
}

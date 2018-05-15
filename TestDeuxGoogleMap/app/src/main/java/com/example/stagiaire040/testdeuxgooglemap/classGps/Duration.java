package com.example.stagiaire040.testdeuxgooglemap.classGps;

public class Duration {

    String mText;
    int mValue;


    public Duration(String text, int value) {
        mText = text;
        mValue = value;
    }


    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        mValue = value;
    }
}

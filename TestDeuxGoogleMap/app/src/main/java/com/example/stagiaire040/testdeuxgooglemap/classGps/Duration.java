package com.example.stagiaire040.testdeuxgooglemap.classGps;

import android.os.Parcel;
import android.os.Parcelable;

public class Duration implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mText);
        dest.writeInt(this.mValue);
    }

    protected Duration(Parcel in) {
        this.mText = in.readString();
        this.mValue = in.readInt();
    }

    public static final Parcelable.Creator<Duration> CREATOR = new Parcelable.Creator<Duration>() {
        @Override
        public Duration createFromParcel(Parcel source) {
            return new Duration(source);
        }

        @Override
        public Duration[] newArray(int size) {
            return new Duration[size];
        }
    };
}

package com.example.stagiaire040.testdeuxgooglemap.classGps;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

public class Steps implements Parcelable {



    Distance mDistance;
    Duration mDuration;

    Location mStartLocation;
    Location mEndLocation;

    String mHtmlInstruction;


    public Steps(){

    }



    public Steps(JSONObject jsonObjectSteps) throws JSONException {


        this.mDistance = new Distance(jsonObjectSteps.getJSONObject("distance").getString("text")
                ,jsonObjectSteps.getJSONObject("distance").getInt("value"));


        this.mDuration = new Duration((jsonObjectSteps.getJSONObject("duration").getString("text"))
                ,jsonObjectSteps.getJSONObject("duration").getInt("value"));



        this.mStartLocation = new Location(new LatLng(jsonObjectSteps.getJSONObject("start_location").getInt("lat")
                ,jsonObjectSteps.getJSONObject("start_location").getInt("lng"))) ;


        this.mEndLocation = new Location(new LatLng(jsonObjectSteps.getJSONObject("end_location").getInt("lat")
                ,jsonObjectSteps.getJSONObject("end_location").getInt("lng"))) ;


        this.mHtmlInstruction = jsonObjectSteps.getString("html_instructions");





    }


    public Distance getDistance() {
        return mDistance;
    }

    public void setDistance(Distance distance) {
        mDistance = distance;
    }

    public Duration getDuration() {
        return mDuration;
    }

    public void setDuration(Duration duration) {
        mDuration = duration;
    }

    public Location getStartLocation() {
        return mStartLocation;
    }

    public void setStartLocation(Location startLocation) {
        mStartLocation = startLocation;
    }

    public Location getEndLocation() {
        return mEndLocation;
    }

    public void setEndLocation(Location endLocation) {
        mEndLocation = endLocation;
    }

    public String getHtmlInstruction() {
        return mHtmlInstruction;
    }

    public void setHtmlInstruction(String htmlInstruction) {
        mHtmlInstruction = htmlInstruction;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mDistance, flags);
        dest.writeParcelable(this.mDuration, flags);
        dest.writeParcelable(this.mStartLocation, flags);
        dest.writeParcelable(this.mEndLocation, flags);
        dest.writeString(this.mHtmlInstruction);
    }

    protected Steps(Parcel in) {
        this.mDistance = in.readParcelable(Distance.class.getClassLoader());
        this.mDuration = in.readParcelable(Duration.class.getClassLoader());
        this.mStartLocation = in.readParcelable(Location.class.getClassLoader());
        this.mEndLocation = in.readParcelable(Location.class.getClassLoader());
        this.mHtmlInstruction = in.readString();
    }

    public static final Parcelable.Creator<Steps> CREATOR = new Parcelable.Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel source) {
            return new Steps(source);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };
}

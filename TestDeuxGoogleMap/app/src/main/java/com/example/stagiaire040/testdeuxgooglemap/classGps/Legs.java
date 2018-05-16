package com.example.stagiaire040.testdeuxgooglemap.classGps;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Legs implements Parcelable {




    Distance mDistance;
    Duration mDuration;
    Location mStartLocation;
    Location mEndLocation;
    String mStartAdress;
    String mEndAdress;
    List<Steps> mStepsList;


    public Legs (JSONObject jsonObjectLegs,List<Steps> stepList) throws JSONException {

        this.mStepsList = stepList;

        this.mDistance = new Distance(jsonObjectLegs.getJSONObject("distance").getString("text")
                ,jsonObjectLegs.getJSONObject("distance").getInt("value"));


        this.mDuration = new Duration(jsonObjectLegs.getJSONObject("duration").getString("text")
                ,jsonObjectLegs.getJSONObject("duration").getInt("value"));


        this.mStartLocation = new Location(new LatLng(jsonObjectLegs.getJSONObject("start_location").getInt("lat")
                ,jsonObjectLegs.getJSONObject("start_location").getInt("lng"))) ;


        this.mEndLocation = new Location(new LatLng(jsonObjectLegs.getJSONObject("end_location").getInt("lat")
                ,jsonObjectLegs.getJSONObject("end_location").getInt("lng"))) ;


        this.mStartAdress = jsonObjectLegs.getString("start_address");

        this.mEndAdress = jsonObjectLegs.getString("end_address");

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

    public String getStartAdress() {
        return mStartAdress;
    }

    public void setStartAdress(String startAdress) {
        mStartAdress = startAdress;
    }

    public String getEndAdress() {
        return mEndAdress;
    }

    public void setEndAdress(String endAdress) {
        mEndAdress = endAdress;
    }

    public List<Steps> getStepsList() {
        return mStepsList;
    }

    public void setStepsList(List<Steps> stepsList) {
        mStepsList = stepsList;
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
        dest.writeString(this.mStartAdress);
        dest.writeString(this.mEndAdress);
        dest.writeList(this.mStepsList);
    }

    protected Legs(Parcel in) {
        this.mDistance = in.readParcelable(Distance.class.getClassLoader());
        this.mDuration = in.readParcelable(Duration.class.getClassLoader());
        this.mStartLocation = in.readParcelable(Location.class.getClassLoader());
        this.mEndLocation = in.readParcelable(Location.class.getClassLoader());
        this.mStartAdress = in.readString();
        this.mEndAdress = in.readString();
        this.mStepsList = new ArrayList<Steps>();
        in.readList(this.mStepsList, Steps.class.getClassLoader());
    }

    public static final Parcelable.Creator<Legs> CREATOR = new Parcelable.Creator<Legs>() {
        @Override
        public Legs createFromParcel(Parcel source) {
            return new Legs(source);
        }

        @Override
        public Legs[] newArray(int size) {
            return new Legs[size];
        }
    };
}

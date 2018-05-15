package com.example.stagiaire040.testdeuxgooglemap.classGps;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Legs {




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
}

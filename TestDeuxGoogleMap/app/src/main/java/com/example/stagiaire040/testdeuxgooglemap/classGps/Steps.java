package com.example.stagiaire040.testdeuxgooglemap.classGps;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

public class Steps {



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
}

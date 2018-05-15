package com.example.stagiaire040.testdeuxgooglemap.classGps;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Routes {


    String mPoints;
    String mSummary;

    List<Legs> mLegsList;


    public Routes(JSONObject jsonObjectRoutes, List<Legs> legsList) throws JSONException {

        this.mLegsList = legsList;


       this.mSummary = jsonObjectRoutes.getString("summary");
       this.mPoints = jsonObjectRoutes.getJSONObject("overview_polyline").getString("points");




    }




















    public String getPoints() {
        return mPoints;
    }

    public void setPoints(String points) {
        mPoints = points;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public List<Legs> getLegsList() {
        return mLegsList;
    }

    public void setLegsList(List<Legs> legsList) {
        mLegsList = legsList;
    }
}

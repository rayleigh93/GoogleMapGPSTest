package com.example.stagiaire040.testdeuxgooglemap.classGps;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Routes implements Parcelable {


    String mPoints;
    String mSummary;

    List<Legs> mLegsList;


    public Routes(JSONObject jsonObjectRoutes, List<Legs> legsList) throws JSONException {

        this.mLegsList = legsList;

       this.mSummary = jsonObjectRoutes.getString("summary");
       this.mPoints = jsonObjectRoutes.getJSONObject("overview_polyline").getString("points");

    }

    public Routes() {
    }

    public int calculDistanceTotale ()
    {

        int distanceTotale = 0;




            for(int o = 0 ; o <  this.getLegsList().size();o++)
            {
                distanceTotale = distanceTotale + this.getLegsList().get(o).getDuration().mValue;
            }




        return distanceTotale;




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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mPoints);
        dest.writeString(this.mSummary);
        dest.writeTypedList(this.mLegsList);
    }

    protected Routes(Parcel in) {
        this.mPoints = in.readString();
        this.mSummary = in.readString();
        this.mLegsList = in.createTypedArrayList(Legs.CREATOR);
    }

    public static final Parcelable.Creator<Routes> CREATOR = new Parcelable.Creator<Routes>() {
        @Override
        public Routes createFromParcel(Parcel source) {
            return new Routes(source);
        }

        @Override
        public Routes[] newArray(int size) {
            return new Routes[size];
        }
    };
}

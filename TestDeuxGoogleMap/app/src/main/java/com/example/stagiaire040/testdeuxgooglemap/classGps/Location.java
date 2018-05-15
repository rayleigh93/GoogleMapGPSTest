package com.example.stagiaire040.testdeuxgooglemap.classGps;

import com.google.android.gms.maps.model.LatLng;

public class Location {


    LatLng mLatLng;

    public Location(LatLng latLng) {
        mLatLng = latLng;
    }


    public LatLng getLatLng() {
        return mLatLng;
    }

    public void setLatLng(LatLng latLng) {
        mLatLng = latLng;
    }
}

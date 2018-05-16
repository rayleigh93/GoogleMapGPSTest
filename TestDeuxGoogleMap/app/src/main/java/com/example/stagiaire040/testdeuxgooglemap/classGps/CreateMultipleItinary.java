package com.example.stagiaire040.testdeuxgooglemap.classGps;

import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CreateMultipleItinary implements Parcelable {

    // Classe qui contients les éléments pour crée un itineraire
    // On lui donne une adresse de départ et d'arrivés et des autres paramètres


    public final static String REQUEST_GOOGLE_MAP_API = "https://maps.googleapis.com/maps/api/directions/json?";
    public final static String KEY_API_GOOGLE = "AIzaSyD_mWlvDrQCfkVpfcKYPSYRzbGenPkHRt8";

    List<Routes> mRoutesList;


    LatLng mStartLocation;
    LatLng mEndLocation;

    List<LatLng> mListWaypoints;

    boolean mCheminsAlternatives;


    String mUrlRequest;


    public CreateMultipleItinary(LatLng startLocation, LatLng endLocation, List<LatLng> listWaypoints, boolean cheminsAlternatives) throws ExecutionException, InterruptedException {

        this.mStartLocation = startLocation;
        this.mEndLocation = endLocation;
        this.mListWaypoints = listWaypoints;
        this.mCheminsAlternatives = cheminsAlternatives;


        this.mUrlRequest = createRequestURL(this);
        Log.v("TAG ROUTES : " , this.mUrlRequest);


        try {
           this.mRoutesList = parseJsonFile(new JSONTASK().execute(this.mUrlRequest).get());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public String createRequestURL(CreateMultipleItinary createMultipleItinary)
    {
        String requestUrl = "";

        requestUrl = REQUEST_GOOGLE_MAP_API;

        requestUrl = requestUrl + ("origin="+createMultipleItinary.getStartLocation().latitude+","+createMultipleItinary.getStartLocation().longitude+"&");
        requestUrl = requestUrl + ("destination="+createMultipleItinary.getEndLocation().latitude+","+createMultipleItinary.getEndLocation().longitude+"&");

        requestUrl = requestUrl + ("mode=walking"+"&");

        if(createMultipleItinary.isCheminsAlternatives())
        {
            requestUrl = requestUrl + ("alternatives="+"true"+"&");
        }


        if(createMultipleItinary.getListWaypoints() != null)
        {



            for(int i = 0 ; i < createMultipleItinary.getListWaypoints().size() ;i++) {


                if(i == 0) {
                    requestUrl = requestUrl + ("waypoints=" +
                            createMultipleItinary.getListWaypoints().get(i).latitude+
                            ","+  createMultipleItinary.getListWaypoints().get(i).longitude);
                }

                else {

                    requestUrl = requestUrl + ("|" +
                            createMultipleItinary.getListWaypoints().get(i).latitude +
                            "," + createMultipleItinary.getListWaypoints().get(i).longitude);
                }
            }

            requestUrl = requestUrl + ("&");

        }


        requestUrl = requestUrl + ("key="+KEY_API_GOOGLE);

        return requestUrl;
    }


    public class JSONTASK  extends AsyncTask<String, String,String> {





        @Override
        protected String doInBackground(String... urls) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(urls[0]);

                connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";


                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }


                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }

            return null;


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



        }


    }

    public List<Routes> parseJsonFile(String url) throws Exception{

        JSONObject jsonObject = new JSONObject(url);

        JSONArray routes = jsonObject.getJSONArray("routes");

        List<Steps> stepsList ;
        List<Legs> legsList ;
        List<Routes> routesList = new ArrayList<>();


        for(int i = 0 ; i < routes.length();i++)
        {

            // INFO DE ROUTE[]
            JSONArray legs =  routes.getJSONObject(i).getJSONArray("legs");

            legsList= new ArrayList<>();

            for(int o = 0 ; o < legs.length() ; o++){

                // INFO DE LEGS[]
                JSONArray steps = legs.getJSONObject(o).getJSONArray("steps");

                stepsList = new ArrayList<>();

                for(int p = 0 ; p < steps.length() ; p++)
                {
                    // INFO DE STEPS[]
                    stepsList.add(new Steps(steps.getJSONObject(p)));

                }

                //ICI
                legsList.add(new Legs(legs.getJSONObject(o),stepsList));

            }
            routesList.add(new Routes(routes.getJSONObject(i),legsList));
        }



       // Log.v("TAG ROUTES" , routesList.get(0).getLegsList().get(0).getStepsList().get(2).getHtmlInstruction());
//        Log.v("TAG ROUTES" , routesList.get(0).getLegsList().get(1).getStepsList().get(3).getHtmlInstruction());


        // mTextView2.setText(Html.fromHtml(mSteps.getHtmlInstruction()));

        return routesList;

    }


    public List<Routes> getRoutesList() {
        return mRoutesList;
    }

    public void setRoutesList(List<Routes> routesList) {
        mRoutesList = routesList;
    }

    public LatLng getStartLocation() {
        return mStartLocation;
    }

    public void setStartLocation(LatLng startLocation) {
        mStartLocation = startLocation;
    }

    public LatLng getEndLocation() {
        return mEndLocation;
    }

    public void setEndLocation(LatLng endLocation) {
        mEndLocation = endLocation;
    }

    public List<LatLng> getListWaypoints() {
        return mListWaypoints;
    }

    public void setListWaypoints(List<LatLng> listWaypoints) {
        mListWaypoints = listWaypoints;
    }

    public boolean isCheminsAlternatives() {
        return mCheminsAlternatives;
    }

    public void setCheminsAlternatives(boolean cheminsAlternatives) {
        mCheminsAlternatives = cheminsAlternatives;
    }

    public String getUrlRequest() {
        return mUrlRequest;
    }

    public void setUrlRequest(String urlRequest) {
        mUrlRequest = urlRequest;
    }









    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.mRoutesList);
        dest.writeParcelable(this.mStartLocation, flags);
        dest.writeParcelable(this.mEndLocation, flags);
        dest.writeTypedList(this.mListWaypoints);
        dest.writeByte(this.mCheminsAlternatives ? (byte) 1 : (byte) 0);
        dest.writeString(this.mUrlRequest);
    }

    protected CreateMultipleItinary(Parcel in) {
        this.mRoutesList = new ArrayList<Routes>();
        in.readList(this.mRoutesList, Routes.class.getClassLoader());
        this.mStartLocation = in.readParcelable(LatLng.class.getClassLoader());
        this.mEndLocation = in.readParcelable(LatLng.class.getClassLoader());
        this.mListWaypoints = in.createTypedArrayList(LatLng.CREATOR);
        this.mCheminsAlternatives = in.readByte() != 0;
        this.mUrlRequest = in.readString();
    }

    public static final Parcelable.Creator<CreateMultipleItinary> CREATOR = new Parcelable.Creator<CreateMultipleItinary>() {
        @Override
        public CreateMultipleItinary createFromParcel(Parcel source) {
            return new CreateMultipleItinary(source);
        }

        @Override
        public CreateMultipleItinary[] newArray(int size) {
            return new CreateMultipleItinary[size];
        }
    };
}



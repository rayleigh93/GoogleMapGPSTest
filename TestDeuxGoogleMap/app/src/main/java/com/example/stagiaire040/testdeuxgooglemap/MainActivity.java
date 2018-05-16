package com.example.stagiaire040.testdeuxgooglemap;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.stagiaire040.testdeuxgooglemap.classGps.CreateMultipleItinary;
import com.example.stagiaire040.testdeuxgooglemap.classGps.Legs;
import com.example.stagiaire040.testdeuxgooglemap.classGps.Routes;
import com.example.stagiaire040.testdeuxgooglemap.classGps.Steps;
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

public class MainActivity extends AppCompatActivity {


    Button mButton;

    Button mButton2;
    TextView mTextView2;

    CreateMultipleItinary createMultipleItinary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mButton = (Button) findViewById(R.id.button);

        mButton2 = findViewById(R.id.button2);
        mTextView2 = findViewById(R.id.textView2);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this,Itinary.class);
                intent.putExtra("routes",createMultipleItinary);
                startActivity(intent);

               // startActivity(new Intent(MainActivity.this,MapsActivity.class));
            }
        });




        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                List<LatLng> listWaypoint = new ArrayList<>();
               // listWaypoint.add(new LatLng(48.833285,2.243502));
               // listWaypoint.add(new LatLng(48.833148,2.239826));


                try {
                     createMultipleItinary
                            = new CreateMultipleItinary(new LatLng(48.862183,2.50035),
                            new LatLng(48.858457,2.581044),
                           listWaypoint,true );
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mTextView2.setText("OK GO !");

            }
        });




    }


    public class JSONTASK  extends AsyncTask<String, String,String>
    {

        @Override
        protected String doInBackground(String... urls) {


            HttpURLConnection connection = null;
            BufferedReader reader = null ;

            try{
                URL url = new URL(urls[0]);

                connection = (HttpURLConnection)   url.openConnection();

                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line ="";


                while ((line = reader.readLine()) != null)
                {
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
                if(connection != null)
                {
                    connection.disconnect();
                }
            }

            return null;



        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                parseJsonFile(s);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public void parseJsonFile(String url) throws Exception{

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






            Log.v("TAG ROUTES" , routesList.get(0).getLegsList().get(0).getStepsList().get(2).getHtmlInstruction());
            Log.v("TAG ROUTES" , routesList.get(0).getLegsList().get(1).getStepsList().get(3).getHtmlInstruction());


        // mTextView2.setText(Html.fromHtml(mSteps.getHtmlInstruction()));

      



    }



}

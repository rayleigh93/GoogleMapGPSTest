package com.example.stagiaire040.testdeuxgooglemap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.stagiaire040.testdeuxgooglemap.RecyclerView.RecyclerViewAdapterChoixRoutes;
import com.example.stagiaire040.testdeuxgooglemap.classGps.CreateMultipleItinary;

public class Itinary extends AppCompatActivity {


    RecyclerView mRecyclerView;
    RecyclerViewAdapterChoixRoutes mRecyclerViewAdapterChoixRoutes;

    CreateMultipleItinary mCreateMultipleItinary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinary);


        mCreateMultipleItinary = getIntent().getExtras().getParcelable("routes");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewChoixRoute);

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(linearLayoutManager);



        mRecyclerViewAdapterChoixRoutes = new RecyclerViewAdapterChoixRoutes(mCreateMultipleItinary.getRoutesList()
                ,this,R.layout.cellule_routes);

        mRecyclerView.setAdapter(mRecyclerViewAdapterChoixRoutes);



    }
}

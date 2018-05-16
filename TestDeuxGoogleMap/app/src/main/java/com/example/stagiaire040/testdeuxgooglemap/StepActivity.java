package com.example.stagiaire040.testdeuxgooglemap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.stagiaire040.testdeuxgooglemap.RecyclerView.RecyclerViewAdapterChoixRoutes;
import com.example.stagiaire040.testdeuxgooglemap.RecyclerView.RecyclerViewAdapterInstructions;
import com.example.stagiaire040.testdeuxgooglemap.classGps.Routes;

public class StepActivity extends AppCompatActivity {

    Routes mRoutes;

    RecyclerView mRecyclerView;
    RecyclerViewAdapterInstructions mRecyclerViewAdapterInstructions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);







        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerStep);



        mRoutes = getIntent().getExtras().getParcelable("final");


        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(linearLayoutManager);


        mRecyclerViewAdapterInstructions =
                new RecyclerViewAdapterInstructions(mRoutes,
                        this,R.layout.cellule_zero,R.layout.cellule_two);

        mRecyclerView.setAdapter(mRecyclerViewAdapterInstructions);









    }
}

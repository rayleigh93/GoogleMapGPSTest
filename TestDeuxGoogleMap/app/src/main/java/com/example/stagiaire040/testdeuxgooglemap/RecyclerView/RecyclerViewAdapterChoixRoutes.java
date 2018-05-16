package com.example.stagiaire040.testdeuxgooglemap.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stagiaire040.testdeuxgooglemap.R;
import com.example.stagiaire040.testdeuxgooglemap.classGps.Routes;

import java.util.List;

public class RecyclerViewAdapterChoixRoutes extends RecyclerView.Adapter<RecyclerViewAdapterChoixRoutes.
        ViewHolderChoixRoutes> {

    private List<Routes> mRoutesList;
    private Context mContext;
    private int mRecyclerItemRes;


    public RecyclerViewAdapterChoixRoutes(List<Routes> routesList, Context context, int recyclerItemRes) {
        mRoutesList = routesList;
        mContext = context;
        mRecyclerItemRes = recyclerItemRes;
    }



    @Override
    public ViewHolderChoixRoutes onCreateViewHolder( ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(mRecyclerItemRes,parent,false);
        ViewHolderChoixRoutes vh = new ViewHolderChoixRoutes(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderChoixRoutes holder, final int position)  {

        holder.mTextViewNomIti.setText(mRoutesList.get(position).getSummary());
        holder.mTextViewDistance.setText(mRoutesList.get(position).calculDistanceTotale() + " mètres");
       // holder.mTextViewNomIti.setText(mRoutesList.get(position).getSummary());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                


            }
        });



    }


    @Override
    public int getItemCount() {
        return this.mRoutesList.size();
    }



    public class ViewHolderChoixRoutes extends RecyclerView.ViewHolder  {


        private TextView mTextViewNomIti;
        private TextView mTextViewDuree;
        private TextView mTextViewDistance;





        public ViewHolderChoixRoutes(View itemView)   {
            super(itemView);

            mTextViewNomIti = (TextView) itemView.findViewById(R.id.textViewNomItin);
            mTextViewDuree = (TextView) itemView.findViewById(R.id.textViewDuree);
            mTextViewDistance = (TextView) itemView.findViewById(R.id.textViewDistance);






        }



    }
}

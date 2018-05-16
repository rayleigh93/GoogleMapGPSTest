package com.example.stagiaire040.testdeuxgooglemap.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stagiaire040.testdeuxgooglemap.R;
import com.example.stagiaire040.testdeuxgooglemap.classGps.Routes;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterInstructions extends RecyclerView.Adapter<RecyclerView.ViewHolder> {






    private Routes mRoutes;
    private Context mContext;
    private int mRecyclerItemRes0;
    private int mRecyclerItemRes1;

    private List<ListItem> mItems;


    public RecyclerViewAdapterInstructions(Routes routes, Context context, int recyclerItemRes0, int recyclerItemRes1) {
        mRoutes = routes;
        mContext = context;
        mRecyclerItemRes0 = recyclerItemRes0;
        mRecyclerItemRes1 = recyclerItemRes1;

        mItems = new ArrayList<>();

        for(int i =0; i<mRoutes.getLegsList().size();i++)
        {

            mItems.add(new TypeA(mRoutes.getLegsList().get(i).getStartAdress()));

            for (int o = 0; o < mRoutes.getLegsList().get(i).getStepsList().size();o++)
            {

                mItems.add(new TypeB( mRoutes.getLegsList().get(i).getStepsList().get(o).getHtmlInstruction()
                 ,       mRoutes.getLegsList().get(i).getStepsList().get(o).getDistance().getText()
                ));

            }

        }




    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       switch (viewType){

           case ListItem.TYPE_A :
               return new ViewHolder0(LayoutInflater.from(parent.getContext())
                       .inflate(mRecyclerItemRes0,parent,false));


           case ListItem.TYPE_B :
               return new ViewHolder1(LayoutInflater.from(parent.getContext())
                   .inflate(mRecyclerItemRes1,parent,false));


       }

        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ListItem item = mItems.get(position);


        switch (holder.getItemViewType())
        {
            case ListItem.TYPE_A:
                ViewHolder0 viewHolder0 = (ViewHolder0)holder;
                ((ViewHolder0) holder).bindType(item);

                break;



            case ListItem.TYPE_B:
                ViewHolder1 viewHolder1 = (ViewHolder1)holder;
                ((ViewHolder1) holder).bindType(item);
                break;
        }




    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getListItemType();
    }








    public class ViewHolder0 extends RecyclerView.ViewHolder{

        TextView mTextViewDepartArrive;



        public ViewHolder0(View itemView) {
            super(itemView);
            mTextViewDepartArrive = (TextView) itemView.findViewById(R.id.textViewDepartArrive);


        }


        public void bindType(ListItem item) {

            mTextViewDepartArrive.setText( Html.fromHtml(((TypeA) item).mTextAdresseDepArr));
        }

    }


    public class ViewHolder1 extends RecyclerView.ViewHolder {

        TextView mTextViewInstr;
        TextView mTextViewMeter;



        public ViewHolder1(View itemView) {
            super(itemView);

            mTextViewInstr = (TextView) itemView.findViewById(R.id.textViewInstr);
            mTextViewMeter = (TextView) itemView.findViewById(R.id.textViewMeter);

        }


        public void bindType(ListItem item) {
            mTextViewInstr.setText( Html.fromHtml(((TypeB) item).mTextEtape));
            mTextViewMeter.setText(((TypeB) item).mTextMetre);
        }
    }




}




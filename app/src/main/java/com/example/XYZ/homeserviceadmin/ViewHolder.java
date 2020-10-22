package com.example.XYZ.homeserviceadmin;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {



    View mView;
    public ViewHolder(View itemView) {
        super(itemView);
        mView=itemView;

        itemView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                mClickListner.onItemClick (view,getAdapterPosition ());
            }
        });
    }

    void setDetails(sending context,String name,String phone,String skills){

        TextView tvname,tvphone,tvskills;
        tvname=mView.findViewById(R.id.tvname);
        tvphone=mView.findViewById(R.id.tvphone);
        tvskills=mView.findViewById(R.id.tvskills);

        tvname.setText(name);
        tvphone.setText(phone);
        tvskills.setText(skills);

    }
    public interface ClickListner{
        void onItemClick(View view,int position);
    }
    ViewHolder.ClickListner mClickListner;
    public void setOnClickListner(ViewHolder.ClickListner clickListner){
        mClickListner=clickListner;
    }

}

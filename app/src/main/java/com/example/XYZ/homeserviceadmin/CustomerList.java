package com.example.XYZ.homeserviceadmin;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;



public class CustomerList extends ArrayAdapter<customer> {

    private Context context;
    private List<customer> customerList;

    public CustomerList(Context context,List<customer> customerList){
        super(context,R.layout.list_layout_all_complaint,customerList);
        this.context=context;
        this.customerList=customerList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        View listviewitem=inflater.inflate(R.layout.list_layout_all_complaint,null,true);

        TextView fname=(TextView)listviewitem.findViewById(R.id.tvFirstname);
        TextView lname=(TextView)listviewitem.findViewById(R.id.tvLastName);
        TextView phone=(TextView)listviewitem.findViewById(R.id.tvPhone);
        TextView address=(TextView)listviewitem.findViewById(R.id.tvAddress);
        TextView type=(TextView)listviewitem.findViewById(R.id.tvType);
        TextView desc=(TextView)listviewitem.findViewById(R.id.tvDesc);

        customer c=customerList.get(position);
        fname.setText(c.getCustomerName());
        lname.setText(c.getCustomerLastName());
        phone.setText(c.getPhone());
        address.setText(c.getAddress());
        type.setText(c.getType());
        desc.setText(c.getDesc());


        return listviewitem;
    }
}

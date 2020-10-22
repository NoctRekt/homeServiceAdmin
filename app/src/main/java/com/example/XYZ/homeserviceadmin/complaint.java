package com.example.XYZ.homeserviceadmin;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class complaint extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseCustomer;
    List<customer> customerList;
    customer customer;

    public static final String cname="";
    public static final String lname="";
   /* public static final String cphone="";
    public static final String cadd="";
    public static final String ctype="";
    public static final String cproblem="";
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        listView = (ListView) findViewById(R.id.listview);
        customerList = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseCustomer = firebaseDatabase.getReference("customers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        customer = new customer();

        databaseCustomer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    customer = ds.getValue(customer.class);
                    customerList.add(customer);
                }
                CustomerList adapter = new CustomerList(complaint.this, customerList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                customer customer= customerList.get(position);
                Intent intent=new Intent(complaint.this,sending.class);
                Bundle extras=new Bundle();
                extras.putString(lname,customer.getCustomerLastName());
                intent.putExtras(extras);
                startActivity(intent);

            }
        });


    }
}
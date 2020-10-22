package com.example.XYZ.homeserviceadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sending extends AppCompatActivity {

    TextView tvlname;
    EditText etmsg,etemail;
    Button btnsend;
    String ack,rec;
    String selected;
   RadioGroup radioGroup;
   RadioButton rb;
   RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference = firebaseDatabase.getReference("Technichian");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending);


        //btnsend=(Button)findViewById(R.id.btnsend);
        etemail=(EditText)findViewById(R.id.etemail);
       /* radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        rb=(RadioButton)findViewById(R.id.rb1);
        rb=(RadioButton)findViewById(R.id.rb2);
        rb=(RadioButton)findViewById(R.id.rb3);*/


        Bundle extras=getIntent().getExtras();
        String lname=extras.getString(complaint.lname);
        etemail.setText(lname);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(sending.this));
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.addItemDecoration(new DividerItemDecoration(sending.this, LinearLayout.VERTICAL));
       // recyclerView.setItemAnimator(new SlideInUpAnimator());
        /*recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        /*btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                rb = (RadioButton) findViewById(selectedId);
                selected=rb.getText().toString();
                sendmail();
            }
        });*/

    }

    @Override
    protected void onStart() {
        super.onStart();

        final FirebaseRecyclerAdapter<Model,ViewHolder> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<Model, ViewHolder>(Model.class, R.layout.employees_item, ViewHolder.class, reference) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {

                viewHolder.setDetails(sending.this,model.getName(),model.getPhone(),model.getSkills());

            }


            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                final ViewHolder viewHolder=super.onCreateViewHolder (parent, viewType);
                viewHolder.setOnClickListner (new ViewHolder.ClickListner () {
                    @Override
                    public void onItemClick(View view, int position) {

                        TextView tvname=view.findViewById (R.id.tvname);
                        TextView tvphone=view.findViewById (R.id.tvphone);

                        sendmail(tvname.getText().toString(),tvphone.getText().toString());

                    }
                });

                return viewHolder;

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);


    }

    private void sendmail(String name, String phone) {
        rec=etemail.getText().toString();

        String[] recs=rec.split(",");
/*
        if(selected.equals("Techinican 1")){
            String msg1="Thanks for making complaint,our technician who's name is "+selected+" will contact you within 24hr,his conatct number is:1";
        }
        if(selected.equals("Technician 2")){
            String msg1="Thanks for making complaint,our technician who's name is "+selected+" will contact you within 24hr,his conatct number is:2";
        }
        if(selected.equals("Technician 3")){
            String msg1="Thanks for making complaint,our technician who's name is "+selected+" will contact you within 24hr,his conatct number is:3";
        }*/


        ack="Acknowledge from homerservice";


        Intent i=new Intent(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_EMAIL,recs);
        i.putExtra(Intent.EXTRA_SUBJECT,ack);

            String msg1="Thanks for making complaint,our technician who's name is "+name+" will contact you within 24hr,his conatct number is:"+phone+".";
            i.putExtra(Intent.EXTRA_TEXT,msg1);

        i.setType("message/rfc822");
        startActivity(Intent.createChooser(i,"Choose email client"));
    }

}

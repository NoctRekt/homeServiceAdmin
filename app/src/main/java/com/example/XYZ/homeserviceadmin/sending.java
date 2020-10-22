package com.example.XYZ.homeserviceadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class sending extends AppCompatActivity {

    TextView tvlname;
    EditText etmsg,etemail;
    Button btnsend;
    String ack,rec;
    String selected;
   RadioGroup radioGroup;
   RadioButton rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending);


        btnsend=(Button)findViewById(R.id.btnsend);
        etemail=(EditText)findViewById(R.id.etemail);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        rb=(RadioButton)findViewById(R.id.rb1);
        rb=(RadioButton)findViewById(R.id.rb2);
        rb=(RadioButton)findViewById(R.id.rb3);


        Bundle extras=getIntent().getExtras();
        String lname=extras.getString(complaint.lname);
        etemail.setText(lname);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                rb = (RadioButton) findViewById(selectedId);
                selected=rb.getText().toString();
                sendmail();
            }
        });

    }

    private void sendmail() {
        rec=etemail.getText().toString();
        String[] recs=rec.split(",");

        if(selected.equals("Techinican 1")){
            String msg1="Thanks for making complaint,our technician who's name is "+selected+" will contact you within 24hr,his conatct number is:1";
        }
        if(selected.equals("Technician 2")){
            String msg1="Thanks for making complaint,our technician who's name is "+selected+" will contact you within 24hr,his conatct number is:2";
        }
        if(selected.equals("Technician 3")){
            String msg1="Thanks for making complaint,our technician who's name is "+selected+" will contact you within 24hr,his conatct number is:3";
        }


        ack="Acknowledge from homerservice";


        Intent i=new Intent(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_EMAIL,recs);
        i.putExtra(Intent.EXTRA_SUBJECT,ack);

        if(selected.equals("Techinican 1")){
            String msg1="Thanks for making complaint,our technician who's name is "+selected+" will contact you within 24hr,his conatct number is:987654321";
            i.putExtra(Intent.EXTRA_TEXT,msg1);
        }
        if(selected.equals("Technician 2")){
            String msg1="Thanks for making complaint,our technician who's name is "+selected+" will contact you within 24hr,his conatct number is:0987654312";
            i.putExtra(Intent.EXTRA_TEXT,msg1);
        }
        if(selected.equals("Technician 3")){
            String msg1="Thanks for making complaint,our technician who's name is "+selected+" will contact you within 24hr,his conatct number is:987654123";
            i.putExtra(Intent.EXTRA_TEXT,msg1);
        }
        i.setType("message/rfc822");
        startActivity(Intent.createChooser(i,"Choose email client"));
    }

}

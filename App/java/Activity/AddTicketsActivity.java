package com.hackthon.srahulkumar.energyhackapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.hackthon.srahulkumar.energyhackapp.R;

public class AddTicketsActivity extends AppCompatActivity {
  private   EditText tck,name,dest,phone;
   private  Button validate,add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tickets);

        validate=findViewById(R.id.add_validate_button);
        add=findViewById(R.id.add_button);
         tck=findViewById(R.id.add_tickets_number);
         name=findViewById(R.id.add_name);
         dest=findViewById(R.id.add_destination);
         phone=findViewById(R.id.add_bill);


    }
}

package com.hackthon.srahulkumar.energyhackapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MemberLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);
        Button btn=findViewById(R.id.member_go_button);
        final EditText edt=findViewById(R.id.memberlogin_family_id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edt.getText().toString()))
                    edt.setError("Invalid Family Id");
                else
                Toast.makeText(MemberLoginActivity.this,"In Complete Work... in Progress",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

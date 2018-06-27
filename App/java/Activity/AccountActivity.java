package com.hackthon.srahulkumar.energyhackapp.Activity;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hackthon.srahulkumar.energyhackapp.FamilyActivity.FamilyActivity;
import com.hackthon.srahulkumar.energyhackapp.FrameActivity;
import com.hackthon.srahulkumar.energyhackapp.R;

public class AccountActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private TextView OwnerName, displayName, email, mobile;

    private String uid;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Toolbar toolbar = findViewById(R.id.owner_toolbar);
        toolbar.setFadingEdgeLength(10);
        toolbar.setLogo(ContextCompat.getDrawable(AccountActivity.this, R.drawable.ic_arrow_back_black_24dp));
        View logoView = toolbar.getChildAt(1);
        setSupportActionBar(toolbar);

        logoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(AccountActivity.this, FrameActivity.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               startActivity(intent);
            }
        });

        OwnerName = findViewById(R.id.about_OwnerName);
        displayName = findViewById(R.id.about_username);
        email = findViewById(R.id.about_email);
        mobile = findViewById(R.id.about_number);

        Button btn=findViewById(R.id.showFamily_id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountActivity.this,FamilyActivity.class));
            }
        });


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        uid = mUser.getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String user_name =  dataSnapshot.child("MUsers").child(uid).child("Owner").child("Name").getValue(String.class);
                String user_email = dataSnapshot.child("MUsers").child(uid).child("Owner").child("Email").getValue(String.class);
                String user_phone = dataSnapshot.child("MUsers").child(uid).child("Owner").child("Phone Number").getValue(String.class);

                OwnerName.setText(user_name);
                displayName.setText(user_name);
                email.setText(user_email);
                mobile.setText(user_phone);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}



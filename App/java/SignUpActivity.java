package com.hackthon.srahulkumar.energyhackapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    EditText name,aadhar,email,state,city,phone,address,pwd,r_pwd;
    Button signUp;
    private DatabaseReference mDatabaseRef;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name=findViewById(R.id.signUp_name);
        aadhar=findViewById(R.id.signUp_aadhar);
        email=findViewById(R.id.signUp_email);
        state=findViewById(R.id.signUp_state);
        city=findViewById(R.id.signUp_city);
        phone=findViewById(R.id.signUp_phone);
        address=findViewById(R.id.signUp_address);
        signUp=findViewById(R.id.signUp_button);

        pwd=findViewById(R.id.signUp_passWord);
        r_pwd=findViewById(R.id.signUp_RepassWord);

        mDatabase=FirebaseDatabase.getInstance();
        mDatabaseRef=mDatabase.getReference().child("MUsers");
        mAuth=FirebaseAuth.getInstance();

        mDialog=new ProgressDialog(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });

    }

    private void createNewAccount() {
        final String nName=name.getText().toString();
        final String nAadhar=aadhar.getText().toString();
        final String nEmail=email.getText().toString();
        final String nState=state.getText().toString();
        final String nCity=city.getText().toString();
        final String nPhone=phone.getText().toString();
        final String nAddress=address.getText().toString();

        final String mPwd=pwd.getText().toString();
        String mRpwd=r_pwd.getText().toString();





            if (!TextUtils.isEmpty(nAadhar) && !TextUtils.isEmpty(nAddress)
                    && !TextUtils.isEmpty(nCity) &&!TextUtils.isEmpty(nEmail)
                    && !TextUtils.isEmpty(nName) && !TextUtils.isEmpty(nPhone)
                    && !TextUtils.isEmpty(nState)) {

                mDialog.setMessage("Creating Account...");
                mDialog.show();

                mAuth.createUserWithEmailAndPassword(nEmail, mPwd)
                        .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                if (authResult!=null){

                                    String userId=mAuth.getCurrentUser().getUid();

                                    DatabaseReference currentDb=mDatabaseRef.child(userId).child("Owner");

                                    currentDb.child("Name").setValue(nName);
                                    currentDb.child("Email").setValue(nEmail);
                                    currentDb.child("Password").setValue(mPwd);
                                    currentDb.child("Aadhar").setValue(nAadhar);
                                    currentDb.child("State").setValue(nState);
                                    currentDb.child("City").setValue(nCity);
                                    currentDb.child("Phone Number").setValue(nPhone);
                                    currentDb.child("Address").setValue(nAddress);
                                    mDialog.dismiss();

                                    //send User to Login  Page
                                  Intent intent=new Intent(SignUpActivity.this, FrameActivity.class);
                                  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                  startActivity(intent);

                                }
                            }
                        });
            }


            else {
                Toast.makeText(SignUpActivity.this,"Password Not Match",Toast.LENGTH_SHORT).show();
            }




    }
}

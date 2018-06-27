package com.hackthon.srahulkumar.energyhackapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
     EditText username,password;
     Button mLogin,mSignUp;

     private FirebaseAuth mAuth;
     private FirebaseAuth.AuthStateListener mAuthListener;
     private FirebaseUser mUsers;

     private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       dialog=new ProgressDialog(this);
        username=findViewById(R.id.login_usernameId);
        password=findViewById(R.id.login_passwordId);
        mLogin=findViewById(R.id.login_buttonId);
        mSignUp=findViewById(R.id.login_createAccount);

       mAuth=FirebaseAuth.getInstance();
       mAuthListener=new FirebaseAuth.AuthStateListener() {
           @Override
           public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                 mUsers=firebaseAuth.getCurrentUser();
                 if (mUsers!=null){
                     Toast.makeText(LoginActivity.this,"Welcome Back",Toast.LENGTH_LONG).show();
                  //  startActivity(new Intent(LoginActivity.this,FrameActivity.class));

                 }else {
                     Toast.makeText(LoginActivity.this,"Not Sign In",Toast.LENGTH_LONG).show();
                     }
           }
       };

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     if (!TextUtils.isEmpty(username.getText().toString()) &&
                             !TextUtils.isEmpty(password.getText().toString())){
                         String email=username.getText().toString();
                         String pwd=password.getText().toString();
                         dialog.setMessage("Wait till Login...");
                         dialog.show();
                         loginMethod(email,pwd);
                     }else{
                         if (TextUtils.isEmpty(username.getText().toString()))
                          username.setError("Field can't be Empty");
                         if (TextUtils.isEmpty(password.getText().toString()))
                          password.setError("Field can't be Empty");

                     }
                     }
        });
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                finish();

            }
        });

    }

    private void loginMethod(String email, String pwd) {
        mAuth.signInWithEmailAndPassword(email,pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isSuccessful()){
                          Toast.makeText(LoginActivity.this,"Signed In",Toast.LENGTH_LONG).show();
                          dialog.dismiss();
                          startActivity(new Intent(LoginActivity.this,FrameActivity.class));
                          finish();
                      }else {

                      }

                    }
                });
    }

    @Override
    protected void onStart() {

        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
    if (mAuthListener!=null){
        mAuth.removeAuthStateListener(mAuthListener);
    }
    }
}

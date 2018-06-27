
package com.hackthon.srahulkumar.energyhackapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hackthon.srahulkumar.energyhackapp.R;
import com.hackthon.srahulkumar.energyhackapp.Model.Member;

public class AddMemberActivity extends AppCompatActivity {

    private EditText mName,mEmail,mAadhar,mAge,mEmployed,mRelation,mPhome;

    private Button submit;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        mName=findViewById(R.id.member_firstName);
        mEmail=findViewById(R.id.member_email);
        mAadhar=findViewById(R.id.member_aadhar);
        mAge=findViewById(R.id.member_age);
        mEmployed=findViewById(R.id.member_employed);
        mRelation=findViewById(R.id.member_relation);
        mPhome=findViewById(R.id.member_phone);
        submit=findViewById(R.id.member_submit);

        user=FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("MUsers").child(user.getUid()).child("Family Member");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Addmember(databaseReference);

            }
        });

    }

    public  void Addmember(DatabaseReference friendsreference){


        String key=friendsreference.push().getKey();
      if (!mName.getText().toString().isEmpty() &&!mEmail.getText().toString().isEmpty()
                && !mAadhar.getText().toString().isEmpty() && !mAge.getText().toString().isEmpty()
                 && !mEmployed.getText().toString().isEmpty() && !mRelation.getText().toString().isEmpty()
                 && !mPhome.getText().toString().isEmpty()){
         Member member=new Member(mName.getText().toString(),mEmail.getText().toString(),
                                 mAadhar.getText().toString(),mAge.getText().toString(),
                                 mEmployed.getText().toString(),mRelation.getText().toString(),
                                  mPhome.getText().toString());

         friendsreference.child(key).setValue(member);
          Toast.makeText(AddMemberActivity.this,"Family Member  Added Successfully",Toast.LENGTH_SHORT).show();
          }
          else {
          Toast.makeText(AddMemberActivity.this,"Invalid Entry",Toast.LENGTH_SHORT).show();

      }

    }

}

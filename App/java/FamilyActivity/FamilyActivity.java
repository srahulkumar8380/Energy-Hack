package com.hackthon.srahulkumar.energyhackapp.FamilyActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hackthon.srahulkumar.energyhackapp.Model.family;
import com.hackthon.srahulkumar.energyhackapp.R;

import java.util.ArrayList;
import java.util.List;

public class FamilyActivity extends AppCompatActivity {

  private ListView listView;
  private FamilyCustomAdapter familyCustomAdapter;
  private ArrayAdapter<family> adapter;
  private ArrayList<family> familyArrayList;

  private DatabaseReference databaseReference;
  private FirebaseDatabase firebaseDatabase;
  private FirebaseUser mUser;
  private family Mfamily;

  private String uId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        firebaseDatabase=FirebaseDatabase.getInstance();
        mUser= FirebaseAuth.getInstance().getCurrentUser();
        uId=mUser.getUid();
        databaseReference=firebaseDatabase.getReference().child("MUsers")
                .child(uId)
                .child("Family Member");
        databaseReference.keepSynced(true);


        familyArrayList=new ArrayList<>();
        familyCustomAdapter=new FamilyCustomAdapter(this,familyArrayList);
        listView=findViewById(R.id.family_RecyclerView);


        Mfamily=new family();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot ds:dataSnapshot.getChildren()){
                     Mfamily=ds.getValue(family.class);
                     familyArrayList.add(Mfamily);
                }
                listView.setAdapter(familyCustomAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                }
          });
        }
}

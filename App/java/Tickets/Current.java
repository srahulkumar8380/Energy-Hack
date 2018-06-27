package com.hackthon.srahulkumar.energyhackapp.Tickets;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hackthon.srahulkumar.energyhackapp.Adapter.CustomAdapter;
import com.hackthon.srahulkumar.energyhackapp.Model.World;
import com.hackthon.srahulkumar.energyhackapp.R;

import java.util.ArrayList;

public class Current extends Fragment {

    private ArrayList<World> arrayList;
    private CustomAdapter adapter;

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser mUser;

    private World world;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view1=inflater.inflate(R.layout.current_month_tickets_frag,container,false);

        firebaseDatabase=FirebaseDatabase.getInstance();
        mUser= FirebaseAuth.getInstance().getCurrentUser();
        String uId=mUser.getUid();
        databaseReference=firebaseDatabase.getReference().child("MUsers")
                .child(uId)
                .child("Tickets");
        databaseReference.keepSynced(true);


        arrayList=new ArrayList<>();
        final ListView listView=view1.findViewById(R.id.current_listView);
        final CustomAdapter customAdapter=new CustomAdapter(getContext(),arrayList);
        world=new World();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    world=ds.getValue(World.class);
                    arrayList.add(world);
                }
                listView.setAdapter(customAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        return view1;
    }
}

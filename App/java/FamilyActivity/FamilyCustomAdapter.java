package com.hackthon.srahulkumar.energyhackapp.FamilyActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hackthon.srahulkumar.energyhackapp.Model.family;
import com.hackthon.srahulkumar.energyhackapp.R;

import java.util.ArrayList;
import java.util.List;

public class FamilyCustomAdapter extends ArrayAdapter<family> {

   private Context context;
   private List<family> familyList;

    public FamilyCustomAdapter(@NonNull Context context, ArrayList<family> familyList) {
        super(context, 0,familyList);
        this.familyList=familyList;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=convertView;
        if (convertView==null){
            view= LayoutInflater.from(context).inflate(R.layout.family_row,parent,false);
        }


        class ViewHolder{
            TextView f_name;
            TextView f_email;
            TextView f_aadhar;
            TextView f_contact;
            TextView f_relation;
            }
            family family=familyList.get(position);
            ViewHolder holder=new ViewHolder();
        holder.f_name=view.findViewById(R.id.family_name);
        holder.f_email=view.findViewById(R.id.family_email);
        holder.f_aadhar=view.findViewById(R.id.family_aadhar);
        holder.f_contact=view.findViewById(R.id.family_contact);
        holder.f_relation=view.findViewById(R.id.family_relation);


        holder.f_name.setText(family.getName());
        holder.f_email.setText(family.getEmail());
        holder.f_aadhar.setText(family.getAadhar());
        holder.f_contact.setText(family.getphone());
        holder.f_relation.setText(family.getRelation());


        return view;
    }

    @Override
    public int getCount() {
        return familyList.size();
    }

    @Nullable
    @Override
    public family getItem(int position) {
        return familyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}

package com.hackthon.srahulkumar.energyhackapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hackthon.srahulkumar.energyhackapp.R;
import com.hackthon.srahulkumar.energyhackapp.Model.World;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<World> {
    private Context context;
    private List<World> list;

    public CustomAdapter(@NonNull Context context, ArrayList<World> objects) {
        super(context,0,objects);

        this.context=context;
        this.list=objects;
        }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      View view=convertView;
      if (convertView==null){
          view= LayoutInflater.from(context).inflate(R.layout.tickets_preview,parent,false);
      }

        class ViewHolder {
            TextView t_number;
            TextView t_date;
            TextView t_name;
            TextView t_bill;
            TextView t_from,t_to,t_seat;
        }

        final  World world=list.get(position);
        final ViewHolder holder=new ViewHolder();

        holder.t_number= view.findViewById(R.id.tickets_number);
        holder.t_name=view.findViewById(R.id.tickets_name);
        holder.t_from=view.findViewById(R.id.tickets_from);
        holder.t_to=view.findViewById(R.id.tickets_to);
        holder.t_seat=view.findViewById(R.id.tickets_seat);
        holder.t_date=view.findViewById(R.id.tickets_date);
        holder.t_bill=view.findViewById(R.id.tickets_bill);
//        holder.t_number=view.findViewById(R.id.tickets_number);

        holder.t_number.setText(world.getNumber());
        holder.t_name.setText(world.getName());
        holder.t_from.setText(world.getFrom());
        holder.t_to.setText(world.getTo());
        holder.t_seat.setText(world.getSeat());
        holder.t_date.setText(world.getSeat());
        holder.t_date.setText(world.getDate());
        holder.t_bill.setText(world.getBill());

        return view;

    }

    @Override
    public int getCount() { return list.size(); }

    @Nullable
    @Override
    public World getItem(int position) { return list.get(position); }

    @Override
    public long getItemId(int position) { return super.getItemId(position); }
}

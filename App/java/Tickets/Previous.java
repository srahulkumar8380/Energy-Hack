package com.hackthon.srahulkumar.energyhackapp.Tickets;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackthon.srahulkumar.energyhackapp.R;

public class Previous extends Fragment {
    public Previous() {
        }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.previous_month_tickets_frag,container,false);

        return view;
    }
}

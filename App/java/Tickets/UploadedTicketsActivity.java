package com.hackthon.srahulkumar.energyhackapp.Tickets;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.hackthon.srahulkumar.energyhackapp.Adapter.ViewPagerFragmentAdapter;
import com.hackthon.srahulkumar.energyhackapp.R;

public class UploadedTicketsActivity extends AppCompatActivity {
  private AppBarLayout appBarLayout;
  private TabLayout tabLayout;
  private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploaded_tickets);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appBarLayout=findViewById(R.id.appbarLayout);
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);

       ViewPagerFragmentAdapter adapter=new ViewPagerFragmentAdapter(getSupportFragmentManager());
       adapter.AddFragment(new Previous(),"Previous Month");
       adapter.AddFragment(new Current(),"Current Month");
       adapter.AddFragment(new Promocode(),"Promocode");
       viewPager.setAdapter(adapter);
       tabLayout.setupWithViewPager(viewPager);
       }

    @Override
    public void onBackPressed() {
        UploadedTicketsActivity.this.finish();
        super.onBackPressed();
    }
}

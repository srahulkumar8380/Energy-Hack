package com.hackthon.srahulkumar.energyhackapp;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hackthon.srahulkumar.energyhackapp.MenuActivity.AboutApp;

public class PromocodeRuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocode_rule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.about_toobar);
        toolbar.setFadingEdgeLength(10);
        toolbar.setLogo(ContextCompat.getDrawable(PromocodeRuleActivity.this,R.drawable.ic_arrow_back_black_24dp));
        View logoView=toolbar.getChildAt(1);
        logoView.getPaddingLeft();
        setSupportActionBar(toolbar);


        logoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PromocodeRuleActivity.this,FrameActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
    }
}

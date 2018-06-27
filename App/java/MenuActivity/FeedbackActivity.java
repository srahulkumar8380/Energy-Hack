package com.hackthon.srahulkumar.energyhackapp.MenuActivity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hackthon.srahulkumar.energyhackapp.FrameActivity;
import com.hackthon.srahulkumar.energyhackapp.R;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
//-----------------------------------------------------------------------setting advanced toolbar...
        Toolbar toolbar = (Toolbar) findViewById(R.id.feedback_toolbar);
        toolbar.setFadingEdgeLength(10);
        toolbar.setLogo(ContextCompat.getDrawable(FeedbackActivity.this,R.drawable.ic_arrow_back_black_24dp));
        View logoView=toolbar.getChildAt(1);
        setSupportActionBar(toolbar);
        logoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FeedbackActivity.this,FrameActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
//=================================================================================================toolbar end here..
        Button f_btn=findViewById(R.id.feedbak_sendButton);
        final EditText feedback=findViewById(R.id.feeback_textView);
         f_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String s =feedback.getText().toString();
                 if (s.equals("")){
                     Snackbar.make(v, "feedback form is empty", Snackbar.LENGTH_LONG)
                       .setAction("Action", null).show();
                 }
                 else {
                     Toast.makeText(FeedbackActivity.this,"Thanks For The Feedback...",Toast.LENGTH_LONG).show();
                 }
             }
         });

    }

    @Override
    public void onBackPressed() {
        FeedbackActivity.this.finish();
        super.onBackPressed();
    }
}

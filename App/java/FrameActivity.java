package com.hackthon.srahulkumar.energyhackapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hackthon.srahulkumar.energyhackapp.Activity.AccountActivity;
import com.hackthon.srahulkumar.energyhackapp.Activity.AddMemberActivity;
import com.hackthon.srahulkumar.energyhackapp.Activity.ScanActivity;
import com.hackthon.srahulkumar.energyhackapp.MenuActivity.AboutApp;
import com.hackthon.srahulkumar.energyhackapp.MenuActivity.FeedbackActivity;
import com.hackthon.srahulkumar.energyhackapp.Model.World;
import com.hackthon.srahulkumar.energyhackapp.Tickets.UploadedTicketsActivity;

import java.util.Locale;

import static com.hackthon.srahulkumar.energyhackapp.R.id.layout_to_hide_show;

public class FrameActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;

    private EditText tickest;
    private EditText name,destination,bill,boarding,date;
    private Button b_validate,b_save;
    private LinearLayout layout;

    TextView account,tickets_uploaded,addFamily,rule;
    ImageView scanUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        String uid=mUser.getUid();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("MUsers").child(uid).child("Tickets");
        databaseReference.keepSynced(true);

        account=findViewById(R.id.home_account);
        tickets_uploaded=findViewById(R.id.home_upload_tickets);
        addFamily=findViewById(R.id.home_addFamilyMenber);
        scanUpload=findViewById(R.id.home_upload_scan);
        rule=findViewById(R.id.home_rule);
        ImageView tickets_ByNumber=findViewById(R.id.home_upload_number);

        tickets_ByNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPopUp();

            }
        });

        scanUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FrameActivity.this,ScanActivity.class));

            }
        });
        tickets_uploaded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FrameActivity.this, UploadedTicketsActivity.class));

            }
        });

        addFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FrameActivity.this,AddMemberActivity.class));

            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FrameActivity.this,AccountActivity.class));

            }
        });
        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FrameActivity.this,PromocodeRuleActivity.class));
            }
        });
    }
    private void createPopUp() {
        builder=new AlertDialog.Builder(this);
        final View view=getLayoutInflater().inflate(R.layout.activity_add_tickets,null);

        tickest=view.findViewById(R.id.add_tickets_number);
        name=view.findViewById(R.id.add_name);
        boarding=view.findViewById(R.id.add_bording);
        destination=view.findViewById(R.id.add_destination);
        date=view.findViewById(R.id.add_date);
        bill=view.findViewById(R.id.add_bill);

        b_validate=view.findViewById(R.id.add_validate_button);
        b_save=view.findViewById(R.id.add_button);

        layout=view.findViewById(layout_to_hide_show);
        layout.setVisibility(View.INVISIBLE);

        builder.setView(view);
        dialog=builder.create();
        dialog.show();

        b_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.VISIBLE);
                addTickets();
            }
        });
    }
    public void addTickets() {
        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty() && !destination.getText().toString().isEmpty()
                        && !bill.getText().toString().isEmpty()
                        && !boarding.getText().toString().isEmpty()
                        && !date.getText().toString().isEmpty()){
                    String add_number=tickest.getText().toString();
                    String add_name=name.getText().toString();
                    String add_bording=boarding.getText().toString();
                    String add_dest=destination.getText().toString();
                    String add_date=date.getText().toString();
                    String add_bill=bill.getText().toString();


                    String key=databaseReference.push().getKey();
                    World world=new World(add_number,add_name,add_bording,add_dest,add_date,add_bill);
                    databaseReference.child(key).setValue(world);
                    Toast.makeText(FrameActivity.this," One  Tickets Added Successfully",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(FrameActivity.this,"Invalid Entry",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_language:
                showChangelanguage();
                break;
            case R.id.action_feedback:
                startActivity(new Intent(FrameActivity.this,FeedbackActivity.class));
                break;
            case R.id.action_faqs:
                break;

            case R.id.action_reminder:
                setAlarm();
                break;
            case R.id.action_history:
                break;
            case R.id.action_about:
                startActivity(new Intent(FrameActivity.this,AboutApp.class));
                break;
            case R.id.action_logout:
                if (mUser!=null && mAuth!=null){
                    mAuth.signOut();
                    startActivity(new Intent(FrameActivity.this,LoginActivity.class));
                }
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void showChangelanguage(){
        final String[] listName={"हिंदी","English"};
        AlertDialog.Builder builder=new AlertDialog.Builder(FrameActivity.this);
        builder.setTitle("Choose Language...");
        builder.setSingleChoiceItems(listName, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which==0){
                    //hindi
                    setLocale("hi");
                    recreate();
                }
                if (which==1){
                    //hindi
                    setLocale("en");
                    recreate();
                }
                //dimiss dialog..
                dialog.dismiss();

            }
        });
        AlertDialog mDialog=builder.create();
        mDialog.show();
    }

    public  void setAlarm(){
        final String[] option={" Promocode Reminder"};
        AlertDialog.Builder builder=new AlertDialog.Builder(FrameActivity.this);
        builder.setTitle(R.string.text_alramDialog);
        builder.setSingleChoiceItems(option, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which==0)   {
                    recreate();
                }
                dialog.dismiss();
                Toast.makeText(FrameActivity.this,"Will get you notify when your account ready  with promocode",Toast.LENGTH_LONG).show();

            }
        });
        AlertDialog mDialog=builder.create();
        mDialog.show();
    }

    private void setLocale(String lan) {
        Locale locale=new Locale(lan);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        //save data to shared preferances
        SharedPreferences.Editor editor=getSharedPreferences("Setting",MODE_PRIVATE).edit();
        editor.putString("My Lan..",lan);
        editor.apply();
    }
    //load language saved in shared prefarence
    public void loadLocale(){
        SharedPreferences preferences=getSharedPreferences("Setting", Activity.MODE_PRIVATE);
        String language=preferences.getString("My Lan..","");
        setLocale(language);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            Intent sharingIntent=new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
             sharingIntent.putExtra(Intent.EXTRA_TEXT,"This  is the best  App  to  travel  and  earn"+"\n"+"https://github.com/srahulkumar8380/Energy-Hack");
             sharingIntent.setPackage("com.whatsapp");
             startActivity(sharingIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

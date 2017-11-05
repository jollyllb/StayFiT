package com.example.puneet.stayfit;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;


public class DashboardActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener {
    private TextView textViewWish;
    private TextView textViewfood;
    private Button workout;
    private Button fitnesssearch;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private Button sharebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser fbuser = firebaseAuth.getCurrentUser();
        textViewWish=(TextView) findViewById(R.id.textViewWish);
        textViewfood=(TextView) findViewById(R.id.textViewfood);
        workout=(Button) findViewById(R.id.workout);
        workout.setOnClickListener(this);
        fitnesssearch=(Button) findViewById(R.id.fitnesssearch);
        fitnesssearch.setOnClickListener(this);
        sharebutton=(Button) findViewById(R.id.sharebutton);
        sharebutton.setOnClickListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users/"+fbuser.getUid());
        databaseReference.addValueEventListener(this);
    }

    @Override
    public void onClick(View view){

        if(view == fitnesssearch){

            finish();
            //starting login activity
            startActivity(new Intent(this, MapsActivity.class));
        }

        else  if(view == workout){

            finish();
            //starting login activity
            startActivity(new Intent(this, WorkoutActivity.class));
        }

        else if(view == sharebutton)
        {
            finish();
            startActivity(new Intent(this,SocialActivity.class));
        }
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        try {
            HashMap<String, Object> objData = (HashMap<String, Object>) dataSnapshot.getValue();
            Calendar c = Calendar.getInstance();
            double hour = c.get(Calendar.HOUR_OF_DAY);
           // String mycategory = objData.get("category").toString();

            if(hour >= 0 && hour < 5){
                textViewWish.setText("Good Night "+ objData.get("name").toString());
                textViewfood.setText("You need atleast 8 hours of Sleep" + hour);
            }
            else if(hour > 5 && hour < 12)
            {
                textViewWish.setText("Good Morning "+ objData.get("name").toString());

                if(objData.get("category").toString().equals("UnderWeight")) {
                    textViewfood.setText("Its Time to have \n\nSweet Potatoes  \n" +
                            "With Almond Butter");
                }
                else if(objData.get("category").toString().equals("Healthy")) {
                    textViewfood.setText("Its Time to have \n\nSprouted Grain Bread Sandwiches \n" +
                            "With Nut Butter");
                }
                else if(objData.get("category").toString().equals("Over Weight")){
                    textViewfood.setText("Its Time to have \n\nOatmeal \n" +
                            "With Pumkin Puree");
                }
                else if(objData.get("category").toString().equals("Obese")){
                    textViewfood.setText("Its Time to have \n\nHerbed Egg White \n" +
                            "And Spinach Omelet");
                }

            }
            else if(hour >= 12 && hour < 16){
                textViewWish.setText("Good Afternoon "+ objData.get("name").toString()+objData.get("category"));
                if(objData.get("category").toString().equals("UnderWeight")) {
                    textViewfood.setText("Its Time to have \n\nHigh Protein  \n" +
                            "Vanilla Chia Pudding");
                }
                else if(objData.get("category").toString().equals("Normal")) {
                    textViewfood.setText("Its Time to have \n\nGreek Yogurt \n" +
                            "And A Perfect Parfait");
                }
                else if(objData.get("category").toString().equals("Over Weight")){
                    textViewfood.setText("Its Time to have \n\nVegan Tempeh \n" +
                            "BLT Wrap");
                }
                else if(objData.get("category").toString().equals("Obese")){
                    textViewfood.setText("Its Time to have \n\nGolden Raisins Wheat \n" +
                            "Berry Agugula Salad");
                }
            }
            else{
                textViewWish.setText("Good Evening " +  objData.get("name").toString());
                textViewfood.setText("Time to cook your favorite dinner \n" +
                        "and 100 steps after that!");
            }
        }
        catch(NullPointerException exnu){
            exnu.printStackTrace();
        }
        catch(Exception ex){

        }
    }

    @Override
        public void onCancelled(DatabaseError databaseError) {
        }
}

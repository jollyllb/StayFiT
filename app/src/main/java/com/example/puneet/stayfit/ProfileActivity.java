package com.example.puneet.stayfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener,ValueEventListener {

    private FirebaseAuth firebaseAuth;

    private TextView textViewUserEmail;
    private TextView textViewbmi;
    private TextView textViewResult;
    private TextView textViewtrainer;
    private Button signoutbutton;
    private Button dashboardbutton;
    private DatabaseReference databaseReference;
    DecimalFormat df= new DecimalFormat("##.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth= FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()== null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser fbuser = firebaseAuth.getCurrentUser();
        textViewUserEmail=(TextView) findViewById(R.id.textViewUserEmail);
        textViewbmi=(TextView) findViewById(R.id.textViewbmi);
        textViewResult=(TextView) findViewById(R.id.textViewResult);
        textViewtrainer =(TextView) findViewById(R.id.textViewtrainer);
        signoutbutton=(Button) findViewById(R.id.signoutbutton);
        dashboardbutton=(Button) findViewById(R.id.dashboardbutton);
        signoutbutton.setOnClickListener(this);
        dashboardbutton.setOnClickListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users/"+fbuser.getUid());
        databaseReference.addValueEventListener(this);
    }

    @Override
    public void onClick(View view){

        if(view == signoutbutton){
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        if(view == dashboardbutton){
            startActivity(new Intent(this, DashboardActivity.class));
                //finish();
        }
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        try {
            HashMap<String, Object> objData = (HashMap<String, Object>) dataSnapshot.getValue();
            double s = (double)(objData.get("bmi"));
            FirebaseUser fbuser = firebaseAuth.getCurrentUser();
            textViewUserEmail.setText("Welcome to StayFIT " + objData.get("name").toString());
            textViewbmi.setText("Your BMI : " +df.format(s));
            textViewResult.setText("Your Category : "+ objData.get("category"));
            textViewtrainer.setText("Your Trainer : "+objData.get("model"));
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

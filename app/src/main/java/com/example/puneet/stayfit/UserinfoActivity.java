package com.example.puneet.stayfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserinfoActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextName;
    private EditText editTextPound;
    private EditText editTextHeight;
    private EditText editTextAge;
    private Spinner spinnerfitmale;
    private Spinner spinnerfitfemale;
    private Spinner gender;

    private Button saveinfo;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()== null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPound =(EditText) findViewById(R.id.editTextPound);
        editTextHeight =(EditText) findViewById(R.id.editTextHeight);
        editTextAge =(EditText) findViewById(R.id.editTextAge);
        spinnerfitmale=(Spinner) findViewById(R.id.spinnerfitmale);
        spinnerfitfemale = (Spinner) findViewById(R.id.spinnerfitfemale);

        //gender spinner and funstion to define it's operation
        gender    =    (Spinner) findViewById(R.id.gender);
        setup_gender_spinner();
        saveinfo = (Button) findViewById(R.id.saveinfo);
        FirebaseUser fbuser= firebaseAuth.getCurrentUser();
        saveinfo.setOnClickListener(this);

    }
//Operations to be done when male or female is selected in GENDER spinner
    void setup_gender_spinner() {
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    spinnerfitmale.setVisibility(View.VISIBLE);
                    spinnerfitfemale.setVisibility(View.GONE);
                } else {

                    spinnerfitfemale.setVisibility(View.VISIBLE);
                    spinnerfitmale.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


        private void saveUserInformation(){

            try{

            String name= editTextName.getText().toString().trim();

            String h= editTextHeight.getText().toString();
            double height= Double.parseDouble(h);

            String w=editTextPound.getText().toString();
            double weight=Double.parseDouble(w);

            String a=editTextAge.getText().toString();
            int age=Integer.parseInt(a);
            String category;
            String model;
            double kg= (double) (weight * 0.4535923);
            double meters = height/100;
            double bmi=(kg/(meters * meters));
                if (bmi < 18.5) {
                category= "UnderWeight";
                }

               else if (bmi < 25 && bmi > 18) {
                    category= "Normal";
                }

               else if (bmi < 30 && bmi > 25) {
                   category= "Over Weight";
                }

               else {
                    category= "Obese";
                }
            String gendervalue = gender.getSelectedItem().toString();
            if(gendervalue.equals("Male")){
                 model = spinnerfitmale.getSelectedItem().toString();
            }
            else{
                 model = spinnerfitfemale.getSelectedItem().toString();
            }

            FirebaseUser fbuser=firebaseAuth.getCurrentUser();
            String uid = fbuser.getUid();
            storebasicInfo storebasicinfo= new storebasicInfo(uid, name, height, weight, age, bmi, category, gendervalue, model);

            databaseReference.child(fbuser.getUid()).setValue(storebasicinfo);

            Toast.makeText(this,"Information Saved Successfully", Toast.LENGTH_LONG).show();
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

    @Override
        public void onClick(View view){

        if(view == saveinfo)
        {
           saveUserInformation();
        }
    }


}

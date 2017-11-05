package com.example.puneet.stayfit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button registerbtn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textviewsigning;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth= FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!= null){
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
        editTextEmail= (EditText) findViewById(R.id.editTextEmail);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
        textviewsigning=(TextView) findViewById(R.id.textviewsigning);

        registerbtn =(Button) findViewById(R.id.registerbtn);
        loginbtn =(Button) findViewById(R.id.loginbtn);
        progressDialog= new ProgressDialog(this);
        registerbtn.setOnClickListener(this);
        textviewsigning.setOnClickListener(this);
        loginbtn.setOnClickListener(this);
    }

    private void registerUser(){

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            //password is empty error throw
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering you the StayFIT...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try {
                            if (task.isSuccessful()) {
                                //User is successfully registered and logged in
                                //Should log in to the
                                Log.i("Register ", task.getResult().toString());
                                Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(), UserinfoActivity.class));
                            } else {
                                Log.i("Register ", task.getResult().toString());
                                Toast.makeText(MainActivity.this, "Could Not register due to some problem", Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch(Exception ex){
                            ex.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }

                });

    }

    @Override
    public void onClick(View view){
        if(view == registerbtn){
            registerUser();
        }

        if(view==loginbtn){
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}

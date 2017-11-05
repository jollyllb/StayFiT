package com.example.puneet.stayfit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signbutton;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textviewregister;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private Button regbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth= FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!= null){
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }
        signbutton =(Button) findViewById(R.id.signbutton);
        editTextEmail= (EditText) findViewById(R.id.editTextEmail);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
/*
        textviewregister=(TextView) findViewById(R.id.textviewregister);
*/
        regbutton = (Button) findViewById(R.id.regbutton);
        progressDialog = new ProgressDialog(this);
        signbutton.setOnClickListener(this);
       /* textviewregister.setOnClickListener(this);*/
       regbutton.setOnClickListener(this);
    }

    private void userLogin(){
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

        progressDialog.setMessage("Signing you to StayFIT. Please Wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        }
                    }
                });
    }

    @Override
    public void onClick(View view)
    {
        if (view == signbutton)
        {
            //Send user to the other activity page
            userLogin();
        }
        if(view == regbutton)
        {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}


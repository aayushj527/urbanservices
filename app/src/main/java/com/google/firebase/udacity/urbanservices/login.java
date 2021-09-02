package com.google.firebase.udacity.urbanservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    Button button3;
    EditText et2,et3;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button3 = findViewById(R.id.button3);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        mAuth = FirebaseAuth.getInstance();


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = et2.getText().toString().trim();
                String pass = et3.getText().toString().trim();

                if(mail.equals("")) {
                    et2.setError("Email is required");
                    et2.requestFocus();
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    et2.setError("Invalid Email");
                    et2.requestFocus();
                }
                if(pass.equals("")) {
                    et3.setError("Password is required");
                    et3.requestFocus();
                }
                if(pass.length()<6) {
                    et3.setError("Invalid Password");
                    et3.requestFocus();
                }
                else {
                    mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(login.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(),MainActivity3.class);
                                //i.putExtra("name",);
                                startActivity(i);
                            } else {
                                Toast.makeText(login.this, "Login Failed, Try again", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
    }
}
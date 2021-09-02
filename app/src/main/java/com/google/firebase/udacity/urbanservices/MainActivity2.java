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
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText nameField;
    EditText mailField;
    EditText passField;
    EditText conPassField;
    EditText noField;
    Button button,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();
        nameField = findViewById(R.id.nameField);
        mailField = findViewById(R.id.mailField);
        passField = findViewById(R.id.passField);
        conPassField = findViewById(R.id.conPassField);
        noField = findViewById(R.id.noField);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pass = passField.getText().toString().trim();
                String mail = mailField.getText().toString().trim();
                String name = nameField.getText().toString().trim();
                String no = noField.getText().toString().trim();

                if (name.equals("")) {
                    nameField.setError("Please enter Name");
                    nameField.requestFocus();
                }
                if (mail.equals("")) {
                    mailField.setError("Please enter mail address");
                    mailField.requestFocus();
                }
                if (pass.equals("")) {
                    passField.setError("Please set password");
                    passField.requestFocus();
                }
                if (no.equals("")) {
                    noField.setError("Please enter contact number");
                    noField.requestFocus();
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    mailField.setError("Please enter a valid email");
                    mailField.requestFocus();
                }
                if(pass.length()<6) {
                    passField.setError("Password length too short");
                    passField.requestFocus();
                }
                if (!((pass).equals(conPassField.getText().toString()))) {
                    conPassField.setError("Password didn't matched");
                    conPassField.requestFocus();
                }
                if(no.length()>10 || no.length()<10) {
                    noField.setError("Please enter valid number");
                    noField.requestFocus();
                }
                else {

                    mAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                User user = new User(name, mail, no);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(MainActivity2.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(MainActivity2.this, "Failed to Register, Try again", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(MainActivity2.this, "Failed to Register, Try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    Intent intent = new Intent(getApplicationContext(), login.class);
                    startActivity(intent);
                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });


    }
}
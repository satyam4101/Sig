package com.example.andy.firebaseotp.Authentication.Password;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.andy.firebaseotp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Reg extends AppCompatActivity {
    DatabaseReference reference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        mAuth = FirebaseAuth.getInstance();

        final EditText username = findViewById(R.id.username);
        final EditText email = findViewById(R.id.email);
        final EditText pass = findViewById(R.id.pass);
        Button register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (username.getText().toString().trim().equals("") || email.getText().toString().trim().equals("") ||
                        pass.getText().toString().trim().equals("")) {
                    Toast.makeText(Reg.this, "Please enter all details properly", Toast.LENGTH_LONG).show();
                } else {
                    if (!(email.getText().toString().contains("@")) || pass.getText().toString().length() < 6) {
                        Toast.makeText(Reg.this, "Please enter all details properly", Toast.LENGTH_LONG).show();
                    } else {
                        mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                                .addOnCompleteListener(Reg.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                            assert firebaseUser != null;
                                            String userid = firebaseUser.getUid();

                                            reference = FirebaseDatabase.getInstance().getReference("Registered Pupil").child("Users").child(userid);

                                            HashMap<String, String> hashMap = new HashMap<>();
                                            hashMap.put("id", userid);
                                            hashMap.put("username", username.getText().toString());
                                            hashMap.put("EmailID", email.getText().toString());
                                            hashMap.put("Password", pass.getText().toString());

                                            reference.setValue(hashMap)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                Intent MainIntent = new Intent(Reg.this, MainActivity.class);
                                                                MainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                startActivity(MainIntent);
                                                                Toast.makeText(Reg.this, "You are Successfully Registered.",
                                                                        Toast.LENGTH_LONG).show();
                                                                finish();
                                                            } else {
                                                                Toast.makeText(Reg.this, "Registration failed", Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    });
                                        }
                                    }
                                });
                    }
                }
            }
        });
    }
}
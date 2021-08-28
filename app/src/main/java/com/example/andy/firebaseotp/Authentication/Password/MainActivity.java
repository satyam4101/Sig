package com.example.andy.firebaseotp.Authentication.Password;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.andy.firebaseotp.Authentication.OTP.OTP;
import com.example.andy.firebaseotp.R;
import com.example.andy.firebaseotp.tt;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    DatabaseReference reference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        
        final EditText un = findViewById(R.id.email);
        final EditText pass = findViewById(R.id.pass);
        

        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (un.getText().toString().trim().equals("") || pass.getText().toString().trim().equals("")) {
                        Toast.makeText(MainActivity.this, "Please enter all details properly", Toast.LENGTH_LONG).show();
                    } else {
                   mAuth.signInWithEmailAndPassword(un.getText().toString(), pass.getText().toString())
                           .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull Task<AuthResult> task) {
                                   if (task.isSuccessful()) {
                                       // Sign in success, update UI with the signed-in user's information
                                       Intent intent = new Intent(MainActivity.this, tt.class);
                                       startActivity(intent);
                                       Toast.makeText(MainActivity.this, "Signed in Successfully", Toast.LENGTH_SHORT).show();
                                   } else {
                                       // If sign in fails, display a message to the user.
                                       Log.w("MainActivity", "signInWithEmail:failure", task.getException());
                                       Toast.makeText(MainActivity.this, "Authentication failed.",
                                               Toast.LENGTH_SHORT).show();
                                   }
                               }
                           });
               }
            }
        });

        TextView signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Reg.class);
                startActivity(intent);
            }
        });

        TextView forget = findViewById(R.id.forget);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OTP.class);
                startActivity(intent);
            }
        });

    }
}

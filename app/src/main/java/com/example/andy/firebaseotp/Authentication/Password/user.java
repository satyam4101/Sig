package com.example.andy.firebaseotp.Authentication.Password;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andy.firebaseotp.tt;
import com.example.andy.firebaseotp.R;

public class user extends AppCompatActivity {
private Button button;
    private Button button2;
    private Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        button=findViewById(R.id.asl);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user.this, tt.class));
            }
        });

        button3=findViewById(R.id.logout);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user.this,MainActivity.class));
            }
        });
    }
}
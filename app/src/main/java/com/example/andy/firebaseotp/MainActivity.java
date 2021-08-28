//package com.example.andy.firebaseotp;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.cardview.widget.CardView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//
//public class MainActivity extends AppCompatActivity {
//    public CardView continuebutton;
//    public EditText phone_input;
//    String number;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        continuebutton=findViewById(R.id.next);
//        phone_input=findViewById(R.id.phone);
//
//        continuebutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =new Intent(MainActivity.this,otpScreen.class);
//                number =phone_input.getText().toString();
//                intent.putExtra("number",number);
//                startActivity(intent);
//            }
//        });
//    }
//
//}
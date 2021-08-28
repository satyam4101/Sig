//package com.example.andy.firebaseotp;
//
//import android.content.Intent;
//import android.content.res.Configuration;
//import android.content.res.Resources;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.DisplayMetrics;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.constraintlayout.widget.ConstraintLayout;
//
//import com.chaos.view.PinView;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.FirebaseException;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.PhoneAuthCredential;
//import com.google.firebase.auth.PhoneAuthProvider;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.HashMap;
//import java.util.Locale;
//import java.util.concurrent.TimeUnit;
//
//public class OTP extends AppCompatActivity implements View.OnClickListener {
//
//    private PinView pinView;
//    private Button next;
//    private TextView topText1, topText2, textU;
//    private EditText userName, userPhone;
//    private ConstraintLayout first, second;
//
//    String phoneNumber, otp;
//    private String verificationCode;
//
//    FirebaseAuth mAuth;
//    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    FirebaseUser firebaseUser;
//    DatabaseReference myRef;
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        //check if user is null
//        if (firebaseUser != null) {
//            Intent intent = new Intent(OTP.this, AfterOTP.class);
//            startActivity(intent);
//            finish();
//        }
//    }
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_otp);
//
//        mAuth = FirebaseAuth.getInstance();
//
//        StartFirebaseLogin();
//
//
//        topText1 = findViewById(R.id.topText1);
//        topText2 = findViewById(R.id.topText2);
//        pinView = findViewById(R.id.pinView);
//        next = findViewById(R.id.button);
//        userName = findViewById(R.id.username);
//        userPhone = findViewById(R.id.userPhone);
//        first = findViewById(R.id.first_step);
//        second = findViewById(R.id.secondStep);
//        textU = findViewById(R.id.textView_noti);
//        first.setVisibility(View.VISIBLE);
//
//        next.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//
//        if (next.getText().equals("Let's go!") || next.getText().equals("प्रारंभ करें")) {
//            String name = userName.getText().toString();
//            String phone = userPhone.getText().toString();
//
//            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone)) {
//
//                //phoneNumber = "+91" + userPhone.getText().toString();
//                phoneNumber = "+919589575074";
//
//                PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                        phoneNumber,                     // Phone number to verify
//                        60,                           // Timeout duration
//                        TimeUnit.SECONDS,                // Unit of timeout
//                        OTP.this,        // Activity (for callback binding)
//                        mCallback);                      // OnVerificationStateChangedCallbacks
//
//                next.setText("Verify");
//                first.setVisibility(View.GONE);
//                second.setVisibility(View.VISIBLE);
//                topText1.setVisibility(View.GONE);
//                topText2.setVisibility(View.VISIBLE);
//            } else {
//                Toast.makeText(OTP.this, "Please enter the details", Toast.LENGTH_SHORT).show();
//            }
//        } else if (next.getText().equals("Verify")) {
//            otp = pinView.getText().toString();
//            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);
//            SigninWithPhone(credential);
//        }
//
//    }
//
//
//    private void SigninWithPhone(PhoneAuthCredential credential) {
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            firebaseUser = mAuth.getCurrentUser();
//                            assert firebaseUser != null;
//                            String userid = firebaseUser.getUid();
//
//                            myRef = database.getReference().child("Farmers").child(userid);
//
//                            HashMap<String, String> hashMap = new HashMap<>();
//                            hashMap.put("UserID", userid);
//                            hashMap.put("Username", userName.getText().toString());
//                            hashMap.put("Mobile Number", userPhone.getText().toString());
//
//                            myRef.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        pinView.setLineColor(Color.GREEN);
//                                        textU.setText("OTP Verified");
//                                        textU.setTextColor(Color.GREEN);
//                                        startActivity(new Intent(OTP.this, AfterOTP.class));
//                                        finish();
//                                    } else {
//                                        pinView.setLineColor(Color.RED);
//                                        textU.setText("X Incorrect OTP");
//                                        textU.setTextColor(Color.RED);
//                                        Toast.makeText(OTP.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//                        }
//                    }
//                });
//    }
//
//    private void StartFirebaseLogin() {
//
//        mAuth = FirebaseAuth.getInstance();
//        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//            @Override
//            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                Toast.makeText(OTP.this, "verification completed", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onVerificationFailed(FirebaseException e) {
//                Toast.makeText(OTP.this, "verification failed", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                super.onCodeSent(s, forceResendingToken);
//                verificationCode = s;
//                Toast.makeText(OTP.this, "Code sent", Toast.LENGTH_SHORT).show();
//            }
//        };
//    }
//}
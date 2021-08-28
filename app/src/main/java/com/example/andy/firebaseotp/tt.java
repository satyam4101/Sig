package com.example.andy.firebaseotp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andy.firebaseotp.Authentication.Password.MainActivity;
import com.example.andy.firebaseotp.Authentication.Password.user;
import com.example.andy.firebaseotp.R;

import java.util.Locale;

public class tt extends AppCompatActivity {
    private EditText display;
    private Button Voice;
    private Button logout;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tt);
        display = findViewById(R.id.input);
        Voice = findViewById(R.id.speak);
        logout = findViewById(R.id.logout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            display.setShowSoftInputOnFocus(false);
        }

        textToSpeech = new TextToSpeech(getApplicationContext()
                , new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }

            }
        });
        Voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(display.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }

        });

    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        display.setSelection(cursorPos + 1);
    }

    public void A(View view) {
        String strToAdd;
        updateText("A");
    }

    public void B(View view) {
        updateText("B");
    }

    public void C(View view) {
        updateText("C");
    }

    public void D(View view) {
        updateText("D");
    }

    public void E(View view) {
        updateText("E");
    }

    public void F(View view) {
        updateText("F");
    }

    public void G(View view) {
        updateText("G");
    }

    public void H(View view) {
        updateText("H");
    }

    public void I(View view) {
        updateText("I");
    }

    public void J(View view) {
        updateText("J");
    }

    public void K(View view) {
        updateText("K");
    }

    public void L(View view) {
        updateText("L");
    }

    public void M(View view) {
        updateText("M");
    }

    public void N(View view) {
        updateText("N");
    }

    public void O(View view) {
        updateText("O");
    }

    public void P(View view) {
        updateText("P");
    }

    public void Q(View view) {
        updateText("Q");
    }

    public void R(View view) {
        updateText("R");
    }

    public void S(View view) {
        updateText("S");
    }

    public void T(View view) {
        updateText("T");
    }

    public void U(View view) {
        updateText("U");
    }

    public void V(View view) {
        updateText("V");
    }

    public void W(View view) {
        updateText("W");
    }

    public void X(View view) {
        updateText("X");
    }

    public void Y(View view) {
        updateText("Y");
    }

    public void Z(View view) {
        updateText("Z");
    }

    public void space(View view) {
        updateText(" ");
    }

    public void backspace(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if (cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }
}
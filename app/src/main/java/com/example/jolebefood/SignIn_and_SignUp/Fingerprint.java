package com.example.jolebefood.SignIn_and_SignUp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.jolebefood.R;

public class Fingerprint extends AppCompatActivity {
    Button btn_Fingerprint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint);

        btn_Fingerprint = findViewById(R.id.Fingerprint);


    }

}
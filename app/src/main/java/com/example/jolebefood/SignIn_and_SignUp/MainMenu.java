package com.example.jolebefood.SignIn_and_SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jolebefood.R;

public class MainMenu extends AppCompatActivity {
    Button btnDN_Phone,btnDN_Email,btnDK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnDN_Email = (Button) findViewById(R.id.SignwithEmail);
        btnDN_Phone = (Button) findViewById(R.id.SignwithPhone);
        btnDK = (Button) findViewById(R.id.Signup);

        btnDN_Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Login_Phone.class);
                startActivity(intent);
            }
        });

        btnDN_Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Login_Gmail.class);
                startActivity(intent);
            }
        });

        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Register.class);
                startActivity(intent);
            }
        });


    }
}
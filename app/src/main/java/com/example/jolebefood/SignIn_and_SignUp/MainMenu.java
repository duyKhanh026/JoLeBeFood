package com.example.jolebefood.SignIn_and_SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jolebefood.R;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainMenu extends AppCompatActivity {
    Button btnDN_Phone,btnDN_Email,btnDK,btnGoogle,btnFacebook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnDN_Email = (Button) findViewById(R.id.SignwithEmail);
        btnDN_Phone = (Button) findViewById(R.id.SignwithPhone);
        btnDK = (Button) findViewById(R.id.Signup);
        btnGoogle = (Button) findViewById(R.id.Google);
        btnFacebook = findViewById(R.id.Facebook);

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

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, GoogleSignIn.class);
                startActivity(intent);
            }
        });

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, FacebookSignIn.class);
                startActivity(intent);
            }
        });

    }
}
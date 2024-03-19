package com.example.jolebefood.SignIn_and_SignUp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jolebefood.MainActivity;
import com.example.jolebefood.R;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class SendOtp extends AppCompatActivity {


    FirebaseAuth mAuth;
    Button verify , Resend ;
    TextView txt;
    EditText entercode;
    String phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);

        phonenumber = getIntent().getStringExtra("Phonenumber").trim();

        entercode = (EditText) findViewById(R.id.code);
        txt = (TextView) findViewById(R.id.text);
        Resend = (Button)findViewById(R.id.Resendotp);
        verify = (Button) findViewById(R.id.Verify);
        mAuth = FirebaseAuth.getInstance();




    }


}
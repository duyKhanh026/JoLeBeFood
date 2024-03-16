package com.example.jolebefood.SignIn_and_SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jolebefood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class Login_Phone extends AppCompatActivity {
    EditText num;
    Button sendotp,signinemail;
    TextView signup;
    CountryCodePicker cpp;
    FirebaseAuth Fauth;
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone);

        num = (EditText)findViewById(R.id.number);
        sendotp = (Button)findViewById(R.id.btnSendOTP);
        cpp=(CountryCodePicker)findViewById(R.id.countrycode);
        signinemail=(Button)findViewById(R.id.btnEmail_LGPhone);
        signup = (TextView)findViewById(R.id.SignUp_LGPhone);

        Fauth = FirebaseAuth.getInstance();

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number=num.getText().toString().trim();
                String Phonenum = cpp.getSelectedCountryCodeWithPlus()+number;
                Intent b = new Intent(Login_Phone.this,SendOtp.class);

                b.putExtra("Phonenum",Phonenum);
                startActivity(b);
                finish();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Phone.this,Register.class));
                finish();
            }
        });
        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Phone.this,Login_Gmail.class));
                finish();
            }
        });

    }
}
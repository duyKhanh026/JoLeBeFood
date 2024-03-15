package com.example.jolebefood.SignIn_and_SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Gmail extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText Email,Password;
    Button btn_SignIn;
    TextView SignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_gmail);

        Email = findViewById(R.id.Email_DN);
        Password = findViewById(R.id.Password_DN_email);
        btn_SignIn = findViewById(R.id.btn_SignIn_Email);
        SignUp = findViewById(R.id.TV_SignUp_Email);

        auth = FirebaseAuth.getInstance();

        btn_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInEmail(v);
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Gmail.this, MainMenu.class);
                startActivity(intent);
            }
        });

    }

    public void SignInEmail(View v){
        String userEmail = Email.getText().toString();
        String userPassword = Password.getText().toString();
        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this,"Vui lòng nhập địa chỉ email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this,"Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login_Gmail.this,"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login_Gmail.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Login_Gmail.this,"Đăng nhập thất bại!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
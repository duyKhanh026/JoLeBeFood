package com.example.jolebefood.SignIn_and_SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jolebefood.Activity.MainActivity;
import com.example.jolebefood.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Login_Gmail extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText Email,Password;
    private TextInputLayout TextInput_Email,TextInput_Password;

    Button btn_SignIn,btnNextSDT;
    TextView SignUp,QuenMK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_gmail);

        TextInput_Email = findViewById(R.id.EmailTextInput);
        TextInput_Password = findViewById(R.id.PasswordTextInput);

        Email = TextInput_Email.getEditText();
        Password = TextInput_Password.getEditText();
        btn_SignIn = findViewById(R.id.btn_SignIn_Email);
        SignUp = findViewById(R.id.TV_SignUp_Email);
        QuenMK = findViewById(R.id.QuenMK);

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
                Intent intent = new Intent(Login_Gmail.this, Register.class);
                startActivity(intent);
            }
        });

        QuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                if (!email.isEmpty()) {
                    resetPassword(email);
                } else {
                    Toast.makeText(Login_Gmail.this, "Vui lòng nhập địa chỉ email", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void resetPassword(String email) {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login_Gmail.this, "Chúng tôi đã gửi một email để đặt lại mật khẩu của bạn.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Login_Gmail.this, "Không thể gửi email đặt lại mật khẩu. Vui lòng thử lại sau.", Toast.LENGTH_SHORT).show();
                        }
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
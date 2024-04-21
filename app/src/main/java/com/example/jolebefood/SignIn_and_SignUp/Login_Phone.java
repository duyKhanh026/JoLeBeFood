package com.example.jolebefood.SignIn_and_SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class Login_Phone extends AppCompatActivity {
    EditText PhoneNumber,OtpNumber;
    private final String TAG = "Nam Test OTP";
    Button btn_sendotp,signinemail,btn_verifyotp;
    TextView signup;
    CountryCodePicker cpp;
    FirebaseAuth mAuth;
    String verificationID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone);

        PhoneNumber = (EditText)findViewById(R.id.number);
        btn_sendotp = (Button)findViewById(R.id.btnSendOTP);
        signinemail=(Button)findViewById(R.id.btnEmail_LGPhone);
        signup = (TextView)findViewById(R.id.SignUp_LGPhone);
        btn_verifyotp = (Button)findViewById(R.id.btnVerifyOTP);
        OtpNumber = (EditText)findViewById(R.id.otp_nhanduoc);
        mAuth = FirebaseAuth.getInstance();

        btn_sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(PhoneNumber.getText().toString())){
                    Toast.makeText(Login_Phone.this,"Hay nhap so dien thoai",Toast.LENGTH_SHORT).show();
                }else{
                    String sendNumber = PhoneNumber.getText().toString().trim();
                    // Kiểm tra nếu chuỗi có 10 ký tự và toàn bộ là số
                    if (sendNumber.matches("\\d{10}")) {
                        if (sendNumber.startsWith("0")) {
                            sendNumber = sendNumber.substring(1); // Lấy phần từ vị trí thứ hai đến hết chuỗi
                        }
                        sendverificationcode(sendNumber);

                    } else {
                        // Nếu không, không phải là chuỗi số 10 ký tự
                        Toast.makeText(Login_Phone.this, "hãy nhập chuỗi số có 10 kí tự", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        btn_verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(OtpNumber.getText().toString())){
                    Toast.makeText(Login_Phone.this,"Nhap sai OTP",Toast.LENGTH_SHORT).show();
                }else{
                    verifycode(OtpNumber.getText().toString());
                }
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

    private void sendverificationcode(String number) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+84"+number)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallbacks)
                        .build();
        if (options != null) {
            PhoneAuthProvider.verifyPhoneNumber(options);
            Log.e(TAG, "Options are not null");
        } else {
            Log.e(TAG, "Options are null, cannot verify phone number");
        }
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            final String code = phoneAuthCredential.getSmsCode();
            if(code != null){
                Log.e(TAG,"code khac null");
//                verifycode(code);
            }else{
                Log.e(TAG,"code=null");
            }

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(Login_Phone.this , "tai sao",Toast.LENGTH_LONG).show();
            Log.e(TAG, "Tai sao: " + e.getMessage());

        }

        @Override
        public void onCodeSent(String s , PhoneAuthProvider.ForceResendingToken forceResendingToken){
            super.onCodeSent(s,forceResendingToken);
            verificationID = s;
        }
    };

    private void verifycode(String Code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID,Code);
        if (credential != null) {
            Log.e(TAG, "Credential created successfully");
            // Nếu credential được tạo thành công, bạn có thể thực hiện các hành động tiếp theo ở đây,
            // chẳng hạn như gửi nó đến phương thức signInByCredentials().
            signinbyCredentials(credential);
        } else {
            Log.e(TAG, "Failed to create credential");
            // Xử lý trường hợp không thể tạo credential ở đây
        }
    }

    private void signinbyCredentials(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login_Phone.this , "Dang nhap  thanh cong",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Login_Phone.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }
}
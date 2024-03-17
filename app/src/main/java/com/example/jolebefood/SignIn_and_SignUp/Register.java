package com.example.jolebefood.SignIn_and_SignUp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jolebefood.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    EditText name,email,password,phone,address;
    Button btn_signup;
    TextView SignIn;
    DatabaseReference databaseReference;
    CountryCodePicker Cpp;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.ET_FullName);
        email = findViewById(R.id.ET_Email);
        password = findViewById(R.id.ET_Password);
        phone = findViewById(R.id.ET_Phone);
        address = findViewById(R.id.ET_Address);
        btn_signup = findViewById(R.id.btn_Signup);
        SignIn = findViewById(R.id.TV_SignIn);
        Cpp = (CountryCodePicker)findViewById(R.id.countrycode);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup(v);
            }
        });

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, MainMenu.class);
                startActivity(intent);
            }
        });
    }

    public void signup(View view){
        String userName = name.getText().toString().trim();
        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
        String userPhone = phone.getText().toString().trim();
        String userAddress = address.getText().toString().trim();
        if(TextUtils.isEmpty(userName)){
            Toast.makeText(this,"Vui lòng nhập tên!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this,"Vui lòng nhập địa chỉ email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this,"Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPhone)){
            Toast.makeText(this,"Vui lòng nhập số điện thoại!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userAddress)){
            Toast.makeText(this,"Vui lòng nhập địa chỉ!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPassword.length()<6){
            Toast.makeText(this,"Mật khẩu quá ngắn, hãy nhập trên 6 ký tự!", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            databaseReference = FirebaseDatabase.getInstance().getReference("Danh mục").child(useridd);
                            final HashMap<String , String> hashMap = new HashMap<>();
                            databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    HashMap<String , String> hashMap1 = new HashMap<>();
                                    hashMap1.put("Name",userName);
                                    hashMap1.put("EmailId",userEmail);
                                    hashMap1.put("Password",userPassword);
                                    hashMap1.put("Phone",userPhone);
                                    hashMap1.put("Address",userAddress);

                                    FirebaseDatabase.getInstance().getReference()
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if(task.isSuccessful()){
                                                                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                                                builder.setMessage("Bạn đã đăng ký thành công! Đảm bảo xác minh email của bạn ");
                                                                builder.setCancelable(false);
                                                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {

                                                                        dialog.dismiss();

                                                                        String phonenumber = Cpp.getSelectedCountryCodeWithPlus() + userPhone;
                                                                        Intent b = new Intent(Register.this,VerifyPhone.class);
                                                                        b.putExtra("phonenumber",phonenumber);
                                                                        startActivity(b);
//                                                                        Toast.makeText(Register.this,"Đăng ký thành công!", Toast.LENGTH_SHORT).show();
//                                                                        startActivity(new Intent(Register.this, Login_Gmail.class));

                                                                    }
                                                                });
                                                                AlertDialog Alert = builder.create();
                                                                Alert.show();
                                                            }else{
                                                                ReusableCodeForAll.ShowAlert(Register.this,"Error",task.getException().getMessage());
                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                }
                            });
//                            Toast.makeText(Register.this,"Đăng ký thành công!", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(Register.this, MainMenu.class));
                        }else{
                            Toast.makeText(Register.this,"Đăng ký thất bại!"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}
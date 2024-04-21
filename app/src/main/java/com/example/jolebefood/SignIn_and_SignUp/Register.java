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

import com.example.jolebefood.DAO.RegisterDAO.OnGetRegiterListener;
import com.example.jolebefood.DAO.RegisterDAO.Register_DAO;
import com.example.jolebefood.DTO.UserDTO;
import com.example.jolebefood.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    EditText name,email,password,phone,address;
    private TextInputLayout TextInput_Fullname,TextInput_Email,TextInput_Password,TextInput_Phone,TextInput_Address;
    Button btn_signup;
    TextView SignIn;
    DatabaseReference databaseReference;

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextInput_Fullname = findViewById(R.id.FullnameTextInput);
        TextInput_Email = findViewById(R.id.EmailTextInput);
        TextInput_Password = findViewById(R.id.PasswordTextInput);
        TextInput_Phone = findViewById(R.id.PhoneTextInput);
        TextInput_Address = findViewById(R.id.AddressTextInput);

        name = TextInput_Fullname.getEditText();
        email = TextInput_Email.getEditText();
        password = TextInput_Password.getEditText();
        phone = TextInput_Phone.getEditText();
        address = TextInput_Address.getEditText();
        btn_signup = findViewById(R.id.btn_Signup);
        SignIn = findViewById(R.id.TV_SignIn);

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
                            UserDTO user = new UserDTO(userPassword,userName,userEmail,userAddress,userPhone);
                            new Register_DAO().SetDataUser(useridd, user,  new OnGetRegiterListener() {
                                @Override
                                public void OnSentGmail() {
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
                                                        Toast.makeText(Register.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(Register.this, Login_Gmail.class));
                                                    }
                                                });
                                                AlertDialog Alert = builder.create();
                                                Alert.show();
                                            }
                                            else {
                                                ReusableCodeForAll.ShowAlert(Register.this, "Error", task.getException().getMessage());
                                            }
                                        }
                                    });
                                }

                                @Override
                                public void GetUserSuccess() {
                                }
                            });

                        }else{
                            Toast.makeText(Register.this,"Đăng ký thất bại!"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}
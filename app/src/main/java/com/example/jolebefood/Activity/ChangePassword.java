package com.example.jolebefood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jolebefood.R;
import com.example.jolebefood.SignIn_and_SignUp.MainMenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangePassword extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private EditText currentPassword_et,newPassword_et,confirmNewPassword_et;
    Button Confirm,LogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        currentPassword_et = findViewById(R.id.et_pc);
        newPassword_et = findViewById(R.id.et_pn);
        confirmNewPassword_et = findViewById(R.id.et_pnc);
        Confirm = findViewById(R.id.Confirm);
        LogOut = findViewById(R.id.LogOut);

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentPassword = currentPassword_et.getText().toString().trim();
                String newPassword = newPassword_et.getText().toString().trim();
                String confirmNewPassword = confirmNewPassword_et.getText().toString().trim();

                // Kiểm tra xem mật khẩu mới và xác nhận mật khẩu mới có khớp nhau không
                if (!newPassword.equals(confirmNewPassword)) {
                    Toast.makeText(ChangePassword.this, "Mật khẩu mới và xác nhận mật khẩu mới không khớp nhau!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Đổi mật khẩu
                changePassword(currentPassword, newPassword);
            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(ChangePassword.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChangePassword.this, MainMenu.class);
                startActivity(intent);
            }
        });

    }

    private void changePassword(String currentPassword, String newPassword) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.reauthenticate(com.google.firebase.auth.EmailAuthProvider.getCredential(user.getEmail(), currentPassword))
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Re-authentication thành công, tiến hành đổi mật khẩu
                                user.updatePassword(newPassword)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    // Thay đổi mật khẩu thành công

                                                    // Cập nhật mật khẩu trong Realtime Database
                                                    updatePasswordInDatabase(user.getUid(), newPassword);
                                                } else {
                                                    // Thất bại, có thể do lỗi mạng hoặc mật khẩu không hợp lệ
                                                    Toast.makeText(ChangePassword.this, "Thay đổi mật khẩu thất bại! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                // Re-authentication thất bại
                                Toast.makeText(ChangePassword.this, "Xác thực thất bại! Mật khẩu hiện tại không đúng.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void updatePasswordInDatabase(String userId, String newPassword) {
        // Cập nhật mật khẩu mới vào Realtime Database
        mDatabase.child("User").child(userId).child("Password").setValue(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ChangePassword.this, "Đã thay đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ChangePassword.this, "Cập nhật mật khẩu trong Realtime Database thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
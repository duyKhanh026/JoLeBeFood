package com.example.jolebefood.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jolebefood.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditAccount extends AppCompatActivity {
    private FirebaseAuth auth;
    private final String TAG = "Nam Test EditAccount";
    EditText Name,Email,Phone,Address;
    Button Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        Name = findViewById(R.id.et_hvt);
        Email = findViewById(R.id.et_email);
        Phone = findViewById(R.id.et_SDT);
        Address = findViewById(R.id.et_address);
        Update = findViewById(R.id.Update);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("User").child(userId);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String userName = dataSnapshot.child("Name").getValue(String.class);
                    String userEmail = dataSnapshot.child("Email").getValue(String.class);
                    String userPhone = dataSnapshot.child("Phone").getValue(String.class);
                    String userAddress = dataSnapshot.child("Address").getValue(String.class);
                    Name.setText(userName);
                    Email.setText(userEmail);
                    Phone.setText(userPhone);
                    Address.setText(userAddress);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = Name.getText().toString();
                String newEmail = Email.getText().toString();
                String newPhone = Phone.getText().toString();
                String newAddress = Address.getText().toString();
                // Cập nhật thông tin người dùng trên Realtime Database
                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("User").child(userId);
                userRef.child("Name").setValue(newName);
                userRef.child("Email").setValue(newEmail);
                userRef.child("Phone").setValue(newPhone);
                userRef.child("Address").setValue(newAddress)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Xử lý khi cập nhật thành công
                                Toast.makeText(getApplicationContext(), "Thông tin người dùng đã được cập nhật", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Xử lý khi cập nhật thất bại
                                Toast.makeText(getApplicationContext(), "Cập nhật thông tin người dùng không thành công", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }

    private void sendEmailVerification(FirebaseUser user, String newEmail) {
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(EditAccount.this, "Verification email sent to " + newEmail, Toast.LENGTH_SHORT).show();
                            // Cập nhật giao diện hoặc thực hiện hành động khác sau khi gửi email xác thực thành công
                        } else {
                            Toast.makeText(EditAccount.this, "Failed to send verification email: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
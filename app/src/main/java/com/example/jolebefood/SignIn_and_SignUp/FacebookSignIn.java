package com.example.jolebefood.SignIn_and_SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jolebefood.MainActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;

public class FacebookSignIn extends AppCompatActivity {
    private static final String TAG = "FacebookLoginActivity";
    private FirebaseAuth mAuth;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Khởi tạo FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Khởi tạo CallbackManager của Facebook
        mCallbackManager = CallbackManager.Factory.create();

        // Xác định các quyền truy cập mà bạn muốn lấy từ người dùng
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));

        // Xác định callback khi đăng nhập bằng Facebook thành công hoặc thất bại
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // Hủy đăng nhập
                Toast.makeText(FacebookSignIn.this, "Hủy đăng nhập bằng Facebook", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // Xảy ra lỗi khi đăng nhập
                Toast.makeText(FacebookSignIn.this, "Đăng nhập bằng Facebook thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Gọi onActivityResult của CallbackManager để xử lý kết quả đăng nhập
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        // Tạo một AuthCredential với token của Facebook
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());

        // Đăng nhập vào Firebase với AuthCredential
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Đăng nhập thành công, cập nhật thông tin người dùng
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // Đăng nhập thất bại, thông báo cho người dùng
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Toast.makeText(FacebookSignIn.this, "Đăng nhập vào Firebase thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        // Hiển thị thông tin người dùng hoặc chuyển đến màn hình chính
        if (user != null) {
            Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(FacebookSignIn.this, MainActivity.class);
            startActivity(intent);
        }
    }
}

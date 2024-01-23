package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnLogin;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.editTextLoginUsername);
        edtPassword = findViewById(R.id.editTextLoginPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textViewNewUser);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username =edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                if(password.isEmpty() && username.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Nhập tài khoản và mật khẩu !",Toast.LENGTH_SHORT).show();
                    edtUsername.requestFocus();
                } else if (password.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Nhập mật khẩu !",Toast.LENGTH_SHORT).show();
                    edtPassword.requestFocus();
                } else if (username.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Nhập tài khoản !",Toast.LENGTH_SHORT).show();
                    edtUsername.requestFocus();
                }
                else {
                    if (db.login(username,password)==1){
                        Toast.makeText(getApplicationContext(),"Đăng nhập thành công !",Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",username);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Không tìm thấy tên dăng nhập và mật khẩu !", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }
}
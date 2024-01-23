package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công !",Toast.LENGTH_SHORT).show();

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
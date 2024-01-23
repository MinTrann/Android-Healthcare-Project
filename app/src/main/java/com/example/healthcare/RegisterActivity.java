package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edtUsername,edtEmail,edtPassword,edtConfirm;;
    Button btn ;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtUsername = findViewById(R.id.editTextReUsername);
        edtPassword = findViewById(R.id.editTextRePassword);
        edtEmail = findViewById(R.id.editTextReEmail);
        edtConfirm = findViewById(R.id.editTextReConfPassword);
        btn = findViewById(R.id.buttonRegister);
        tv= findViewById(R.id.textViewExitstingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString();
                String pass = edtPassword.getText().toString();
                String email = edtEmail.getText().toString();
                String confirm = edtConfirm.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if (username.isEmpty()&&pass.isEmpty()&&email.isEmpty()&&confirm.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập đủ thông tin!",Toast.LENGTH_SHORT).show();
                    edtUsername.requestFocus();;
                }
                else if (username.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Nhập tài khoản!",Toast.LENGTH_SHORT).show();
                    edtUsername.requestFocus();;
                }else if (email.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Nhập email!",Toast.LENGTH_SHORT).show();
                    edtEmail.requestFocus();
                } else if (pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Nhập mật khẩu!",Toast.LENGTH_SHORT).show();
                    edtPassword.requestFocus();
                } else if(confirm.isEmpty() || !confirm.equals(pass)){
                    Toast.makeText(getApplicationContext(), "Mật khẩu không đúng !", Toast.LENGTH_SHORT).show();
                } else {
                    db.register(username,email,pass);
                    Toast.makeText(getApplicationContext(), "Tạo thành công !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }

            }
        });
    }
}
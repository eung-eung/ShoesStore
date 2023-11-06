package com.example.as_prm_thien.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.as_prm_thien.Admin.AdminHomeActivity;
import com.example.as_prm_thien.Home.HomeActivity;
import com.example.as_prm_thien.R;
import com.example.as_prm_thien.Regex;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;

    //chuyển register
    public void viewRegisterClicked(View v) {
        // Chuyển từ activity_main sang layout_register
        Intent intent = new Intent(LoginActivity.this, Register_Activity.class);
        startActivity(intent);
    }

    //chuyển forgotPass
    public void viewForgotPassword(View v) {
        // Chuyển từ activity_main sang layout_register
        Intent intent = new Intent(LoginActivity.this, ForgotPassword_Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initToolBar();
        editTextEmail = findViewById(R.id.editTextEmail);
//        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.btnLogin);

        // Thêm xử lý sự kiện cho nút đăng nhập
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String credential = editTextEmail.getText().toString().trim();
//                String password = editTextPassword.getText().toString().trim();
                if (validateEmail(credential)) {
                    Intent intent = new Intent(LoginActivity.this, OtpConfirmActivity.class);
//
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Please try again!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private boolean validateEmail(String email) {
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            return false;
        } else if (!Regex.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Pleased enter a valid email");
            return false;
        } else {
            editTextEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword(String pass) {
        if (pass.isEmpty()) {
            editTextPassword.setError("Password is required");
            return false;
        } else {
            editTextPassword.setError(null);
            return true;
        }
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.ToolBarLoginActivity);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Log in");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
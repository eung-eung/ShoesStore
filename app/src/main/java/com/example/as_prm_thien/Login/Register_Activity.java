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

import com.example.as_prm_thien.R;
import com.example.as_prm_thien.Regex;


public class Register_Activity extends AppCompatActivity {


    private EditText textFullName, textUserName, textEmail, textPassword, textPhoneNumner;
    private Button btnRegister;

    public static String convertToInternationalFormat(String phoneNumber) {
        // Remove any leading zeros from the phone number
        String trimmedNumber = phoneNumber.replaceFirst("^0+", "");

        // Add the country code "+84" to the trimmed number
        return "+84" + trimmedNumber;
    }

    public void viewLoginClicked(View v) {
        // Chuyển từ activity_main sang layout_register
        Intent intent = new Intent(Register_Activity.this, LoginActivity.class);
        startActivity(intent);
    }

    // quên mật khẩu
    public void viewForgotPassword(View v) {
        // Chuyển từ activity_main sang layout_register
        Intent intent = new Intent(Register_Activity.this, ForgotPassword_Activity.class);
        startActivity(intent);
    }

    private boolean validateEmail(String email) {
        if (email.isEmpty()) {
            textEmail.setError("Email is required");
            return false;
        } else if (!Regex.EMAIL_ADDRESS.matcher(email).matches()) {
            textEmail.setError("Pleased enter a valid email");
            return false;
        } else {
            textEmail.setError(null);
            return true;
        }
    }

    private boolean validateUsername(String username) {
        if (username.isEmpty()) {
            textUserName.setError("Password is required0");
            return false;
        } else {
            textUserName.setError(null);
            return true;
        }
    }

    private boolean validatePhone(String phone) {
        if (phone.isEmpty()) {
            textPhoneNumner.setError("Phone is required!");
            return false;
        } else if (!Regex.PHONE_NUMBER.matcher(phone).matches()) {
            textPhoneNumner.setError("Please enter valid phone");
            return false;
        } else {
            textPhoneNumner.setError(null);
            return true;
        }
    }

    private boolean validateFullName(String fullName) {
        if (fullName.isEmpty()) {
            textFullName.setError("Name is required");
            return false;
        } else {
            textFullName.setError(null);
            return true;
        }
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.ToolBarSignInActivity);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Sign up");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initToolBar();

        textFullName = findViewById(R.id.textFullName);
        textEmail = findViewById(R.id.textEmail);
        textPhoneNumner = findViewById(R.id.textPhoneNum);
        textPassword = findViewById(R.id.textPass);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = textFullName.getText().toString();
                String email = textEmail.getText().toString();
                String phoneNumber = textPhoneNumner.getText().toString();
                String password = textPassword.getText().toString();
                if (validateFullName(fullName) && validateEmail(email)
                        && validatePhone(phoneNumber) && validatePassword(password)) {
                    Intent intent = new Intent(Register_Activity.this, OtpConfirmActivity.class);
                    startActivity(intent);
                } else {
                    // Hiển thị thông báo lỗi hoặc thực hiện các hành động khác nếu dữ liệu không hợp lệ
                    Toast.makeText(getApplicationContext(), "Please try again!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private boolean validatePassword(String pass) {
        if (pass.isEmpty()) {
            textPassword.setError("Password is required");
            return false;
        } else if (!Regex.PASSWORD.matcher(pass).matches()) {
            textPassword.setError("Password required 8-20 character and least 1 special character");
            return false;
        } else {
            textPassword.setError(null);
            return true;
        }
    }


}
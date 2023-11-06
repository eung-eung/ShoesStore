package com.example.as_prm_thien.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.example.as_prm_thien.Home.HomeActivity;
import com.example.as_prm_thien.MainActivity;
import com.example.as_prm_thien.R;


public class OtpConfirmActivity extends AppCompatActivity {
    String pinValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_otp_confirm);

        String phoneValue = "0966324245";
//        TextView phoneSignIn = findViewById(R.id.phoneSignIn);
//        String result = phoneValue.replaceAll("\\d(?=\\d{4})", "*");
//        phoneSignIn.setText(result);
        getOptConfirm();
    }

    private void getOptConfirm() {
        PinView pinView = findViewById(R.id.pinView);
        pinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() == 6) {
                    pinValue = pinView.getText().toString();
                    Log.w("TAG", pinValue);
                    Intent intent = new Intent(OtpConfirmActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }
}
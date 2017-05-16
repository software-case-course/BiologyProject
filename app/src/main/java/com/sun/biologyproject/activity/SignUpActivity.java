package com.sun.biologyproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sun.biologyproject.R;
import com.sun.biologyproject.bean.User;

public class SignUpActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
}

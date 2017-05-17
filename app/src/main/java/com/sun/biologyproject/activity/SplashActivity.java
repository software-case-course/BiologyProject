package com.sun.biologyproject.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.sun.biologyproject.R;
import com.sun.biologyproject.bean.User;

import cn.bmob.v3.BmobUser;

public class SplashActivity extends AppCompatActivity {

    private int delay = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                User user = BmobUser.getCurrentUser(User.class);
                Intent intent;
                if (user == null){
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                }else {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                }
                startActivity(intent);
                finish();
            }
        },delay);
    }
}

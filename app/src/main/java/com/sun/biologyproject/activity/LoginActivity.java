package com.sun.biologyproject.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sun.biologyproject.R;
import com.sun.biologyproject.bean.User;
import com.sun.biologyproject.utils.MyTextUtils;
import com.sun.biologyproject.utils.ShareUtils;
import com.sun.biologyproject.utils.ToastUtils;

import cn.bmob.v3.BmobConstants;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity {

    private final int TYPE_EDIT_ACCOUNT = 3;
    private final int TYPE_EDIT_PASSWORD = 4;

    private EditText et_account;
    private EditText et_password;
    private Button btn_login;
    private TextView tv_go_login;
    private TextView tv_account_tip;
    private TextView tv_password_tip;

    private ProgressDialog mProgtessDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView(){

        tv_account_tip = (TextView) findViewById(R.id.tv_account_tip);
        tv_password_tip = (TextView) findViewById(R.id.tv_password_tip);

        et_account = (EditText) findViewById(R.id.et_account);
        et_account.setText(ShareUtils.getLastLoginUserPhone(this));
        et_account.addTextChangedListener(new MyTextWatcher(TYPE_EDIT_ACCOUNT));

        et_password = (EditText) findViewById(R.id.et_password);
        et_password.addTextChangedListener(new MyTextWatcher(TYPE_EDIT_PASSWORD));

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogin();
            }
        });

        tv_go_login = (TextView) findViewById(R.id.tv_go_login);
        tv_go_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mProgtessDialog = new ProgressDialog(this);
        mProgtessDialog.setTitle(null);
        mProgtessDialog.setMessage(getString(R.string.logining));
        mProgtessDialog.setCancelable(false);

    }

    private void onLogin(){
        if (!mProgtessDialog.isShowing()){
            mProgtessDialog.show();
        }

        User user = new User();
        user.setUsername(getAccount());
        user.setPassword(getPassword());
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                mProgtessDialog.cancel();
                if (e == null){
                    ToastUtils.showShortToast(LoginActivity.this, "登录成功");
                    ShareUtils.saveLastLoginUserPhone(LoginActivity.this, user.getMobilePhoneNumber());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    if (e.getErrorCode() == 101){
                        ToastUtils.showShortToast(LoginActivity.this, "登录失败：账号或密码错误！");
                    }else {
                        ToastUtils.showShortToast(LoginActivity.this, "登录失败：" + e.toString());
                    }
                }
            }
        });
    }

    @NonNull
    private String getAccount(){
        return et_account.getText().toString().trim();
    }

    @NonNull
    private String getPassword(){
        return et_password.getText().toString().trim();
    }

    private void checkAccount(){
        if (MyTextUtils.isAccountLegitimate(getAccount())){
            tv_account_tip.setText("");
        }else {
            tv_account_tip.setText("手机格式格式有误，请检查");
        }
    }

    private void checkPassword(){
        if (MyTextUtils.isEmpty(getPassword())){
            tv_password_tip.setText("密码不能为空");
        }else {
            tv_password_tip.setText("");
        }
    }

    private void checkInput(){
        btn_login.setEnabled(MyTextUtils.isAccountLegitimate(getAccount()) && !MyTextUtils.isEmpty(getPassword()));
    }

    private class MyTextWatcher implements TextWatcher {
        private int type;
        public MyTextWatcher(int type){
            this.type = type;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (type){
                case TYPE_EDIT_ACCOUNT:
                    checkAccount();
                    break;
                case TYPE_EDIT_PASSWORD:
                    checkPassword();
                    break;
            }
            checkInput();
        }
    }
}

package com.sun.biologyproject.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sun.biologyproject.R;
import com.sun.biologyproject.bean.User;
import com.sun.biologyproject.utils.MyTextUtils;
import com.sun.biologyproject.utils.ShareUtils;
import com.sun.biologyproject.utils.ToastUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class SignUpActivity extends AppCompatActivity {

    private String tag = getClass().getSimpleName();

    private final int ACCOUNT_TIP = 0;//账号输入不合理的提示
    private final int PASSWORD_TIP = 1;//密码输入不合理的提示
    private final int CONFIRM_PASSWORD_TIP = 2;//确认密码不一致时给出的提示
    private final int USER_NAME_TIP = 7;//姓名为空的提示

    private final int TYPE_EDIT_ACCOUNT = 3;
    private final int TYPE_EDIT_PASSWORD = 4;
    private final int TYPE_EDIT_CONFIRM_PASSWORD = 5;
    private final int TYPE_EDIT_USER_NAME = 6;

    private ImageView iv_back;
    private EditText et_account;
    private EditText et_userName;
    private EditText et_password;
    private EditText et_confirm_password;
    private TextView tv_account_tip;
    private TextView tv_user_name_tip;
    private TextView tv_password_tip;
    private TextView tv_confirm_password_tip;
    private Button btn_sign_up;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_sign_up);
        initView();
    }

    private void initView(){
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backAction();
            }
        });

        et_account = (EditText) findViewById(R.id.et_account);
        et_account.addTextChangedListener(new MyTextWatcher(TYPE_EDIT_ACCOUNT));

        et_userName = (EditText) findViewById(R.id.et_userName);
        et_userName.addTextChangedListener(new MyTextWatcher(TYPE_EDIT_USER_NAME));

        et_password = (EditText) findViewById(R.id.et_password);
        et_password.addTextChangedListener(new MyTextWatcher(TYPE_EDIT_PASSWORD));

        et_confirm_password = (EditText) findViewById(R.id.et_confirmPassword);
        et_confirm_password.addTextChangedListener(new MyTextWatcher(TYPE_EDIT_CONFIRM_PASSWORD));

        tv_account_tip = (TextView) findViewById(R.id.tv_account_tip);
        tv_user_name_tip = (TextView) findViewById(R.id.tv_userName_tip);
        tv_password_tip = (TextView) findViewById(R.id.tv_password_tip);
        tv_confirm_password_tip = (TextView) findViewById(R.id.tv_confirmPassword_tip);

        btn_sign_up = (Button) findViewById(R.id.btn_signUp);
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignUp();
            }
        });

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(null);
        mProgressDialog.setMessage(getString(R.string.signUping));
        mProgressDialog.setCancelable(false);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            backAction();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void backAction(){
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
        finish();
    }

    /**
     * 注册操作
     */
    private void onSignUp(){

        if (!mProgressDialog.isShowing()){
            mProgressDialog.show();
        }

        String account = et_account.getText().toString().trim();
        String userName = et_userName.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        User user = new User();
        user.setPassword(password);
        user.setNickName(userName);
        user.setUsername(account);
        user.setMobilePhoneNumber(account);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {

                mProgressDialog.cancel();

                if (e == null){
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    ShareUtils.saveLastLoginUserPhone(SignUpActivity.this, user.getMobilePhoneNumber());
                    startActivity(intent);
                    finish();
                }else {
                    Log.d(tag, "注册失败！" + e.toString());
                    if (e.getErrorCode() == 202){
                        ToastUtils.showShortToast(SignUpActivity.this, "注册失败！账号已存在");
                    }else {
                        ToastUtils.showShortToast(SignUpActivity.this, "注册失败:"+ e.toString());
                    }
                }
            }
        });
    }

    private boolean checkAccount(){
        String account = et_account.getText().toString().trim();
        if (!MyTextUtils.isAccountLegitimate(account)){
            onShowInputTextTip(ACCOUNT_TIP, true, "手机格式有误，请检查");
            return false;
        }else {
            onShowInputTextTip(ACCOUNT_TIP, false,"");
            return true;
        }
    }

    private boolean checkUserName(){
        String userName = et_userName.getText().toString().trim();
        if (MyTextUtils.isEmpty(userName)){
            onShowInputTextTip(USER_NAME_TIP, true, "姓名不能为空");
            return false;
        }else {
            onShowInputTextTip(USER_NAME_TIP, false,"");
            return true;
        }
    }

    private boolean checkPassword(){
        String password = et_password.getText().toString().trim();
        if (!MyTextUtils.isPasswordLegitimate(password)){
            onShowInputTextTip(PASSWORD_TIP, true, "输入的密码应该大于6位少于20位");
            return false;
        }else {
            onShowInputTextTip(PASSWORD_TIP, false,"");
            return true;
        }
    }

    private boolean confirmPassword(){
        String password = et_password.getText().toString().trim();
        String confirm_password = et_confirm_password.getText().toString().trim();
        if (!password.equals(confirm_password)){
            onShowInputTextTip(CONFIRM_PASSWORD_TIP, true, "前后密码不一致");
            return false;
        }else {
            onShowInputTextTip(CONFIRM_PASSWORD_TIP, false,"");
            return true;
        }
    }

    private void checkInput(){
        String account = et_account.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String confirm_password = et_confirm_password.getText().toString().trim();
        btn_sign_up.setEnabled(MyTextUtils.isAccountLegitimate(account) && MyTextUtils.isPasswordLegitimate(password)
                && password.equals(confirm_password));
    }

    /**
     * xi暗示输入的一些提示
     * @param what
     * @param isShow
     * @param tip
     */
    public void onShowInputTextTip(int what, boolean isShow, String tip) {
        switch (what){
            case ACCOUNT_TIP://显示账号输入不合理的tip
                if (isShow){
                    tv_account_tip.setText(tip);
                    tv_account_tip.setVisibility(View.VISIBLE);
                }else {
                    tv_account_tip.setVisibility(View.GONE);
                }
                break;
            case USER_NAME_TIP://显示姓名为空的tip
                if (isShow){
                    tv_user_name_tip.setText(tip);
                    tv_user_name_tip.setVisibility(View.VISIBLE);
                }else {
                    tv_user_name_tip.setVisibility(View.GONE);
                }
                break;
            case PASSWORD_TIP://显示密码不合理的tip
                if (isShow){
                    tv_password_tip.setText(tip);
                    tv_password_tip.setVisibility(View.VISIBLE);
                }else{
                    tv_password_tip.setVisibility(View.GONE);
                }
                break;
            case CONFIRM_PASSWORD_TIP://显示密码不一致的tip
                if (isShow){
                    tv_confirm_password_tip.setText(tip);
                    tv_confirm_password_tip.setVisibility(View.VISIBLE);
                }else{
                    tv_confirm_password_tip.setVisibility(View.GONE);
                }
                break;
        }
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
                case TYPE_EDIT_USER_NAME:
                    checkUserName();
                    break;
                case TYPE_EDIT_PASSWORD:
                    checkPassword();
                    break;
                case TYPE_EDIT_CONFIRM_PASSWORD:
                    confirmPassword();
                    break;
            }
            checkInput();
        }
    }

}

package com.example.appchatdemo.activity.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.transition.Fade;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.example.appchatdemo.R;
import com.example.appchatdemo.Utils.AnimUtils;
import com.example.appchatdemo.Utils.SharePrefUtils;
import com.example.appchatdemo.activity.introduce.IntroduceActivity;
import com.example.appchatdemo.activity.main.MainActivity;
import com.example.appchatdemo.activity.signup.SignUpActivity;
import com.example.appchatdemo.activity.splash.SplashActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;
import mttdat.utils.ViewUtils;
import mttdat.viewplus.EditTextPlus;
import mttdat.viewplus.ImageAutoScale;
import mttdat.viewplus.LinkTouchMovementMethod;
import mttdat.viewplus.TextViewPlus;
import mttdat.viewplus.TouchableSpan;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private static LoginActivity singleton;

    public static LoginActivity getInstance() {
        return singleton;
    }

    public LoginActivity() {
        super();
        singleton = this;
    }

    @BindView(R.id.layout_parent)
    ConstraintLayout layoutParent;

    @BindView(R.id.tv_sign_up)
    TextViewPlus tvSignUp;

    @BindView(R.id.et_username)
    EditTextPlus etUsername;

    @BindView(R.id.et_pwd)
    EditTextPlus etPwd;

    @BindView(R.id.btn_login)
    ImageAutoScale btnLogin;

    @BindView(R.id.switch_remember)
    SwitchCompat switchRemember;

    @BindView(R.id.tv_sign_up_temp)
    TextViewPlus tvSignUpTemp;

    private LoginPresenterImpl mPresenter;
    private SharePrefUtils mSharePrefUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvSignUpTemp.setVisibility(View.INVISIBLE);
        AnimUtils.initAnimTransition(this);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        supportFinishAfterTransition();
    }

    private void init() {
        mPresenter = new LoginPresenterImpl(this);
        mSharePrefUtils = new SharePrefUtils(this);
        initTextSignUp();
        rememberMe();
    }

    private void initTextSignUp() {
        String myString = getString(R.string.lb_not_register) + " " + getString(R.string.lb_sign_up);
        int i1 = myString.indexOf("?");
        int i2 = myString.indexOf("!");

        tvSignUp.setMovementMethod(LinkTouchMovementMethod.getInstance());
        tvSignUp.setHighlightColor(getResources().getColor(android.R.color.transparent));
        SpannableString spannableString = new SpannableString(myString);
        spannableString.setSpan(new ForegroundColorSpan(Color.GRAY), 0, i1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TouchableSpan(
                getColor(R.color.colorBlueBold),
                android.R.color.white,
                android.R.color.transparent,
                android.R.color.transparent
        ) {
            @Override
            public void onClick(@NonNull View widget) {
                tvSignUpTemp.setVisibility(View.VISIBLE);
                onSignUp();
            }
        }, i1 + 1, i2 + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvSignUp.setText(spannableString, TextView.BufferType.SPANNABLE);

    }

    private void rememberMe() {
        boolean flag = mSharePrefUtils.getBoolean("rememberMe");
        if (flag) {
            String userName = mSharePrefUtils.getString("userName");
            String pwd = mSharePrefUtils.getString("pwd");
            etUsername.setText(userName);
            etPwd.setText(pwd);
            etUsername.setSelection(userName.length());
            etPwd.setSelection(pwd.length());
            switchRemember.setChecked(true);
            return;
        }
        etUsername.setText("");
        etPwd.setText("");
    }

    void onSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation
                        (LoginActivity.this,tvSignUpTemp, ViewCompat.getTransitionName(tvSignUpTemp));
        startActivity(intent, optionsCompat.toBundle());
        //AnimUtils.initAnimExitTransition(this);
    }

    @OnClick(R.id.btn_login)
    void onLogin(View view) {
        onLogin();
    }

    @OnClick(R.id.btn_fb)
    void onFb(View view) {

    }

    @OnClick(R.id.btn_twitter)
    void onTwitter(View view) {

    }

    @OnClick(R.id.btn_google)
    void onGG(View view) {

    }

    @OnFocusChange({R.id.et_username, R.id.et_pwd})
    void onFocusChangeUserName(View view, boolean focused) {
        if (focused) {
            view.setBackground(ContextCompat.getDrawable(this, R.drawable.selected_input_field));
            return;
        }
        view.setBackground(ContextCompat.getDrawable(this, R.drawable.unselected_input_field));
    }

    @OnEditorAction({R.id.et_username, R.id.et_pwd})
    boolean onEditorAction(EditText view, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            ViewUtils.hideSoftKeyboard(view, LoginActivity.this);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view.clearFocus();
                }
            });
        }
        return false;
    }

    @OnClick(R.id.layout_parent)
    void onLayoutParent(View view) {
        ViewUtils.hideSoftKeyboard(layoutParent, LoginActivity.this);
        layoutParent.requestLayout();
        if (getCurrentFocus() != null) {
            Objects.requireNonNull(getCurrentFocus()).clearFocus();
        }
    }

    @OnCheckedChanged(R.id.switch_remember)
    void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    private void saveSharePref() {
        mSharePrefUtils.putString("userName", etUsername.getText().toString());
        mSharePrefUtils.putString("pwd", etPwd.getText().toString());
        mSharePrefUtils.putBoolean("rememberMe", true);
    }

    private void deleteSharePref() {
        mSharePrefUtils.putString("userName", "");
        mSharePrefUtils.putString("pwd", "");
        mSharePrefUtils.putBoolean("rememberMe", false);
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onLogin() {
        String userName = Objects.requireNonNull(etUsername.getText()).toString();
        String pwd = Objects.requireNonNull(etPwd.getText()).toString();
        mPresenter.sendRequestLogin(userName, pwd);
    }

    @Override
    public void validUserName() {
        showToast("Username is valid");
        animShake(etUsername);
        if (getCurrentFocus() != null) {
            getCurrentFocus().clearFocus();
        }
    }

    @Override
    public void validPwd() {
        showToast("Password is valid");
        AnimUtils.animShake(etPwd,this);
        if (getCurrentFocus() != null) {
            getCurrentFocus().clearFocus();
        }
    }

    private void animShake(View view) {
        Animation animation = AnimUtils.shake(this, view);
        view.setBackground(ContextCompat.getDrawable(LoginActivity.this, R.drawable.invalidated_input_field));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.startAnimation(animation);
                Log.d("anim", "start");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("anim", "stop");
                        view.clearAnimation();
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void logSuccess() {
        showToast("Login success");
        checkRememberMe(switchRemember.isChecked());
        gotoMain();
    }

    private void gotoMain(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void logFail(Exception e) {
        showToast("Login fail");
        Log.e("logFail",e.getMessage());
        animShake(btnLogin);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkRememberMe(boolean flag) {
        mPresenter.handleRememberMe(flag);
    }

    @Override
    public void rememberMeChecked() {
        saveSharePref();
    }

    @Override
    public void rememberMeNoChecked() {
        deleteSharePref();
    }
}

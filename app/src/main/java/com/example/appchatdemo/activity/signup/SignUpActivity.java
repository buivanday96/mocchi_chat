package com.example.appchatdemo.activity.signup;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.appchatdemo.R;
import com.example.appchatdemo.Utils.AnimUtils;
import com.example.appchatdemo.Utils.DialogUtils;
import com.example.appchatdemo.activity.login.LoginActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;
import mttdat.utils.ViewUtils;
import mttdat.viewplus.EditTextPlus;
import mttdat.viewplus.ImageAutoScale;
import mttdat.viewplus.TextViewPlus;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View{

    private static SignUpActivity singleton;

    public static SignUpActivity getInstance() {
        return singleton;
    }

    public SignUpActivity() {
        super();
        singleton = this;
    }

    @BindView(R.id.et_username)
    EditTextPlus etUserName;

    @BindView(R.id.et_pwd)
    EditTextPlus etPwd;

    @BindView(R.id.et_re_pwd)
    EditTextPlus etRePwd;

    @BindView(R.id.btn_sign_up)
    ImageAutoScale btnSignUp;

    @BindView(R.id.tv_sign_up)
    TextViewPlus tvSignUp;

    @BindView(R.id.layout_parent)
    ConstraintLayout layoutParent;

    @BindView(R.id.tv_btn_sign_up)
    TextViewPlus tvbtnSignUp;

    private SignUpPresenterImpl mPresenter;
    private Dialog waitingDialog;

    private boolean flagScreen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        //
        //AnimUtils.initAnimEnterTransition(this);
        mPresenter = new SignUpPresenterImpl(this);
        initIntent();
    }

    private void initIntent() {
        Intent intent = getIntent();
        flagScreen = intent.getBooleanExtra("goSignUp",false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AnimUtils.initAnimEnterTransition(this);
    }

    @Override
    public void onBackPressed() {
        AnimUtils.initAnimTransition(this);
        super.onBackPressed();
        //supportFinishAfterTransition();
    }

    @OnFocusChange({R.id.et_username, R.id.et_pwd,R.id.et_re_pwd})
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
            ViewUtils.hideSoftKeyboard(view, SignUpActivity.this);
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
        ViewUtils.hideSoftKeyboard(layoutParent, SignUpActivity.this);
        layoutParent.requestLayout();
        if (getCurrentFocus() != null) {
            Objects.requireNonNull(getCurrentFocus()).clearFocus();
        }
    }

    @OnClick(R.id.btn_sign_up)
    void onSignUp(View view){
        onSignUp();
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onSignUp() {
        String email = etUserName.getText().toString();
        String pwd = etPwd.getText().toString();
        String rePwd = etRePwd.getText().toString();
        mPresenter.requestSignUp(email,pwd,rePwd);
    }

    @Override
    public void signUpFail(Exception e) {
        showToast("Sign Up fail ! Please Try Again Later !");
        AnimUtils.animShake(btnSignUp,this);
        hideWaitingDialog();
        Log.i("zzz","sign up fail : " + e.getMessage());
    }

    @Override
    public void signUpSuccess() {
        //save share pref email & pwd
        //show dialog success
        //close dialog goto sign in
        hideWaitingDialog();
        Log.i("zzz","sign up success");
        Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public void validEmail() {
        showToast("Sign Up fail ! Please Try Again Later !");
        AnimUtils.animShake(etUserName,this);
        etUserName.setBackground(ContextCompat.getDrawable(this, R.drawable.invalidated_input_field));
    }

    @Override
    public void validPwd() {
        showToast("Password is valid");
        AnimUtils.animShake(etPwd,this);
        etPwd.setBackground(ContextCompat.getDrawable(this, R.drawable.invalidated_input_field));
    }

    @Override
    public void validPwdLength() {
        showToast("Password should be at least 6 characters");
        AnimUtils.animShake(etRePwd,this);
        etPwd.setBackground(ContextCompat.getDrawable(this, R.drawable.invalidated_input_field));
    }

    @Override
    public void validRePwd() {
        showToast("Re-pwd don't match pwd");
        AnimUtils.animShake(etRePwd,this);
        etRePwd.setBackground(ContextCompat.getDrawable(this, R.drawable.invalidated_input_field));
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showWaitingDialog() {
        Log.i("zzz","start sign up");
        waitingDialog = DialogUtils.createWaitingDialog(this);
        waitingDialog.show();
    }

    @Override
    public void hideWaitingDialog() {
        if (waitingDialog.isShowing()){
            waitingDialog.dismiss();
        }
    }
}

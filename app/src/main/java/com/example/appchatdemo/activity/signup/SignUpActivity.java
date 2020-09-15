package com.example.appchatdemo.activity.signup;

import android.os.Bundle;
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

    private SignUpPresenterImpl mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        AnimUtils.initAnimTransition(this);
        mPresenter = new SignUpPresenterImpl(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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

    //----------------------------------------------------------------------------------------------

    @Override
    public void onSignUp() {
        String email = etUserName.getText().toString();
        String pwd = etPwd.getText().toString();
        String rePwd = etRePwd.getText().toString();
        mPresenter.requestSignUp(email,pwd,rePwd);
    }

    @Override
    public void signUpFail() {
        showToast("Sign Up fail ! Please Try Again Later !");
        AnimUtils.animShake(btnSignUp,this);
    }

    @Override
    public void signUpSuccess() {

    }

    @Override
    public void validEmail() {
        showToast("Sign Up fail ! Please Try Again Later !");
        AnimUtils.animShake(etUserName,this);
    }

    @Override
    public void validPwd() {
        showToast("Password is valid");
        AnimUtils.animShake(etPwd,this);
    }

    @Override
    public void validRePwd() {
        showToast("Re-pwd don't match pwd");
        AnimUtils.animShake(etRePwd,this);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }



}

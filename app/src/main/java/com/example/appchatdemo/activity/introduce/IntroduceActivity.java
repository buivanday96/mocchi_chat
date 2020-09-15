package com.example.appchatdemo.activity.introduce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import com.example.appchatdemo.R;
import com.example.appchatdemo.activity.login.LoginActivity;
import com.example.appchatdemo.activity.splash.SplashActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mttdat.viewplus.ImageAutoScale;
import mttdat.viewplus.TextViewPlus;

public class IntroduceActivity extends AppCompatActivity {

    @BindView(R.id.iv_pattern)
    ImageAutoScale tvPattern;

    @BindView(R.id.btn_sign_in)
    ImageAutoScale btnSignIn;

    @BindView(R.id.btn_sign_up)
    ImageAutoScale btnSignUp;

    @BindView(R.id.tv_connect)
    TextViewPlus tvConnect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_sign_in)
    void onSignIn(View view){
        Intent intent = new Intent(IntroduceActivity.this, LoginActivity.class);
        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation
                        (IntroduceActivity.this,btnSignIn, ViewCompat.getTransitionName(btnSignIn));
        startActivity(intent, optionsCompat.toBundle());
    }

    @OnClick(R.id.btn_sign_up)
    void onSignUp(View view){

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(IntroduceActivity.this, SplashActivity.class);
        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation
                        (IntroduceActivity.this,tvConnect, ViewCompat.getTransitionName(tvConnect));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent, optionsCompat.toBundle());
    }
}

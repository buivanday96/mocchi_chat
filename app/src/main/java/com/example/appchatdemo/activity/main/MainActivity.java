package com.example.appchatdemo.activity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appchatdemo.R;
import com.example.appchatdemo.activity.introduce.IntroduceActivity;
import com.example.appchatdemo.activity.splash.SplashActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mttdat.viewplus.TextViewPlus;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    @BindView(R.id.btn_sign_out)
    AppCompatButton btnSignOut;

    private MainPresenterImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenterImpl(this);
    }

    @OnClick(R.id.btn_sign_out)
    void onSignOut(View view){
        mPresenter.requestSignOut();
    }

    @Override
    public void onSignOut() {
        Intent intent = new Intent(MainActivity.this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
package com.example.appchatdemo.activity.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.example.appchatdemo.R;
import com.example.appchatdemo.Utils.AnimUtils;
import com.example.appchatdemo.activity.introduce.IntroduceActivity;
import com.example.appchatdemo.activity.main.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import mttdat.viewplus.ImageAutoScale;
import mttdat.viewplus.TextViewPlus;

public class SplashActivity extends AppCompatActivity implements SplashContract.View{

    @BindView(R.id.iv_logo)
    ImageAutoScale ivLogo;

    @BindView(R.id.lb)
    TextViewPlus tvLb;

    @BindView(R.id.layout_top)
    ConstraintLayout mLayoutTop;

    @BindView(R.id.lottie)
    LottieAnimationView mAnimationView;

    @BindView(R.id.parent)
    ConstraintLayout layoutParent;

    @BindView(R.id.iv_pattern)
    ImageAutoScale ivPattern;

    private SplashPresenterImpl mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mPresenter = new SplashPresenterImpl(this);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initAnim();
        threadStopAnim(mLayoutTop);
        threadStopAnim(tvLb);
    }

    private void init(){
        Fade fade = new Fade();
        //fade.excludeTarget(decor.findViewById(R.id.action))
        fade.excludeTarget(android.R.id.statusBarBackground,true);
        fade.excludeTarget(android.R.id.navigationBarBackground,true);

        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);
    }

    private void initAnim() {
        AnimUtils.animTop(this,mLayoutTop);
        AnimUtils.animBot(this,tvLb);

        mAnimationView.playAnimation();
        mAnimationView.setRepeatCount(LottieDrawable.INFINITE);
    }

    private void threadStopAnim(final View contentView){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startHideAnim(contentView);

            }
        },2000);
    }

    private void startHideAnim(final View contentView){
        AnimUtils.clearAnim(contentView);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //AnimUtils.animHide(SplashActivity.this,contentView);
                AnimUtils.toggle(contentView,layoutParent,R.id.layout_logo_anim,false);
                checkLogin();
            }
        });
    }

    private void gotoLogin(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, IntroduceActivity.class);
                ActivityOptionsCompat optionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation
                                (SplashActivity.this,tvLb, ViewCompat.getTransitionName(tvLb));
                startActivity(intent, optionsCompat.toBundle());
            }
        },500);
    }

    private void gotoMain(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                AnimUtils.initAnimExitTransition(SplashActivity.this);
            }
        },500);
    }

    @Override
    public void checkLogin() {
        mPresenter.requestCheckLoginAccount();
    }

    @Override
    public void gotoIntroduceScreen() {

        gotoLogin();
    }

    @Override
    public void gotoMainScreen() {
        showToast("Login Success");
        gotoMain();
    }

    @Override
    public void checkInternet() {

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(SplashActivity.this,message,Toast.LENGTH_SHORT).show();
    }

}

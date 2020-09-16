package com.example.appchatdemo.activity.splash;

public class SplashPresenterImpl implements SplashContract.Presenter, SplashModelImpl.LoginFirebaseListener {

    private SplashContract.View mView;

    private SplashModelImpl mModel;

    public SplashPresenterImpl(SplashContract.View view) {
        mView = view;
        mModel = new SplashModelImpl();
    }

    @Override
    public void requestCheckLoginAccount() {
        mModel.resCheckLoginAccount(this);
    }

    @Override
    public void login() {
        mView.gotoMainScreen();
    }

    @Override
    public void logout() {
        mView.gotoIntroduceScreen();
    }
}

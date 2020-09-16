package com.example.appchatdemo.activity.main;

public class MainPresenterImpl implements MainContract.Presenter , MainContract.Model.SignOutListener{

    private MainContract.View mView;

    private MainContract.Model mModel;

    public MainPresenterImpl(MainContract.View view) {
        mView = view;
        mModel = new MainModel();
    }

    @Override
    public void requestSignOut() {
        mModel.resSignOut(this);
    }

    @Override
    public void success() {
        mView.onSignOut();
    }

    @Override
    public void fail(Exception e) {

    }
}

package com.example.appchatdemo.activity.login;

public class LoginPresenterImpl implements LoginContract.Presenter, LoginContract.Model.ListenerLogin {

    private LoginContract.View mView;

    private LoginModelImpl mModel;

    public LoginPresenterImpl(LoginContract.View view) {
        mView = view;
        mModel = new LoginModelImpl();
    }

    @Override
    public void sendRequestLogin(String userName, String pwd) {
        if (userName.length() == 0){
            mView.validUserName();
            return;
        }

        if (pwd.length() == 0){
            mView.validPwd();
            return;
        }
        mModel.login(userName, pwd,this);
    }

    @Override
    public void resSuccess() {
        mView.logSuccess();
    }

    @Override
    public void resFail() {
        mView.logFail();
    }
}

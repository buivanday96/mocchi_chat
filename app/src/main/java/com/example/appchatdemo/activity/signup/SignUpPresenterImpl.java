package com.example.appchatdemo.activity.signup;

public class SignUpPresenterImpl implements SignUpContract.Presenter, SignUpContract.Model.ListenerSignUp {

    private SignUpContract.View mView;
    private SignUpModelImpl mModel;

    public SignUpPresenterImpl(SignUpContract.View view) {
        mView = view;
        mModel = new SignUpModelImpl();
    }

    @Override
    public void requestSignUp(String email, String pwd, String rePwd) {
        if (email.length() == 0){
            mView.validEmail();
            return;
        }

        if (pwd.length() == 0){
            mView.validPwd();
            return;
        }

        if (pwd.compareTo(rePwd) != 0){
            mView.validRePwd();
            return;
        }

        mModel.resSignUp(email,pwd,this);
    }

    @Override
    public void onSuccess() {
        mView.signUpSuccess();
    }

    @Override
    public void onFail() {
        mView.signUpFail();
    }
}

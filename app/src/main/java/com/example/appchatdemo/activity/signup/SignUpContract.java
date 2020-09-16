package com.example.appchatdemo.activity.signup;

public interface SignUpContract {
    interface Model{
        interface ListenerSignUp{
            void onSuccess();
            void onFail(Exception e);
        }
        void resSignUp(String email,String pwd, ListenerSignUp listenerSignUp);
    }

    interface View{
        void onSignUp();
        void signUpFail(Exception e);
        void signUpSuccess();
        void validEmail();
        void validPwd();
        void validPwdLength();
        void validRePwd();
        void showToast(String message);
        void showWaitingDialog();
        void hideWaitingDialog();
    }

    interface Presenter{
        void requestSignUp(String email,String pwd,String rePwd);
    }
}

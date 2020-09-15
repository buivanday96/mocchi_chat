package com.example.appchatdemo.activity.signup;

public interface SignUpContract {
    interface Model{
        interface ListenerSignUp{
            void onSuccess();
            void onFail();
        }
        void resSignUp(String email,String pwd, ListenerSignUp listenerSignUp);
    }

    interface View{
        void onSignUp();
        void signUpFail();
        void signUpSuccess();
        void validEmail();
        void validPwd();
        void validRePwd();
        void showToast(String message);
    }

    interface Presenter{
        void requestSignUp(String email,String pwd,String rePwd);
    }
}

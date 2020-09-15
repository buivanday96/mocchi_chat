package com.example.appchatdemo.activity.login;

public interface LoginContract {

    interface Model{
        interface ListenerLogin{
            void resSuccess();
            void resFail();
        }

        void login(String userName,String pwd,ListenerLogin listenerLogin);
    }

    interface View{
        void onLogin();
        void validUserName();
        void validPwd();
        void logSuccess();
        void logFail();
        void showToast(String message);
    }

    interface Presenter{
        void sendRequestLogin(String userName,String pwd);
    }
}

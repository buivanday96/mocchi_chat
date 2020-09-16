package com.example.appchatdemo.activity.login;

public interface LoginContract {

    interface Model{
        interface ListenerLogin{
            void resSuccess();
            void resFail(Exception e);
        }

        void login(String userName,String pwd,ListenerLogin listenerLogin);
    }

    interface View{
        void onLogin();
        void validUserName();
        void validPwd();
        void logSuccess();
        void logFail(Exception e);
        void showToast(String message);
        void checkRememberMe(boolean flag);
        void rememberMeChecked();
        void rememberMeNoChecked();
    }

    interface Presenter{
        void sendRequestLogin(String userName,String pwd);
        void handleRememberMe(boolean flag);
    }
}

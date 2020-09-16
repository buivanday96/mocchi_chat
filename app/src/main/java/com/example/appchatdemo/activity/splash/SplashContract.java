package com.example.appchatdemo.activity.splash;

public interface SplashContract {
    interface Model{
        interface LoginFirebaseListener{
            void login();
            void logout();
        }

        void resCheckLoginAccount(LoginFirebaseListener listener);
    }

    interface View{
        void checkLogin();
        void gotoIntroduceScreen();
        void gotoMainScreen();
        void checkInternet();
        void showToast(String message);
    }

    interface Presenter{
        void requestCheckLoginAccount();
    }
}

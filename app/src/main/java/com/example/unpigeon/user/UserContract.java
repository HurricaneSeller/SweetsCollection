package com.example.unpigeon.user;

public interface UserContract {
    interface LoginPresenter {
        void performLogin();
    }

    interface LoginView {
        String getUsername();
        String getPassword();
        String getCheckNumber();
    }

    interface RegisterPresenter {

    }

    interface RegisterView {

    }
}

package com.example.unpigeon.user.login;

import android.util.Log;

import com.example.unpigeon.network.Method;
import com.example.unpigeon.network.NetworkUtils;
import com.example.unpigeon.network.RequestParams;
import com.example.unpigeon.network.call.ICallback;
import com.example.unpigeon.network.executor.factory.OkHttpExecutorFactory;
import com.example.unpigeon.user.UserContract;

public class LoginPresenter implements UserContract.LoginPresenter {
    private final UserContract.LoginView mLoginView;
    public static final String ADDRESS_POST = "http://206.189.42.213:8888/user/login";

    public LoginPresenter(UserContract.LoginView loginView) {
        mLoginView = loginView;
        //init client
        NetworkUtils.init(new OkHttpExecutorFactory());
    }

    @Override
    public void performLogin() {
        String username = mLoginView.getUsername();
        String password = mLoginView.getPassword();
//        String checkNum = mLoginView.getCheckNumber(); maybe used later
        RequestParams requestParams = new RequestParams.Builder().url(ADDRESS_POST).method(Method.POST)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .requestParams("username", username).requestParams("password", password).build();
        NetworkUtils.getInstance().doStart(requestParams, callback);
    }

    private ICallback callback = new ICallback() {

        @Override
        public void onStart() {
            Log.d(NetworkUtils.class.getSimpleName(), "网络开始加载");
        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Exception e) {
            Log.d(NetworkUtils.class.getSimpleName(), "出现错误");
        }


        @Override
        public void onSuccess(Object tag, String result) {

            /**
             * 可以根据不同的tag来解析数据
             * if(tag.equest("doGet")){
             *
             * }else if(tag.equest("doPost")){
             *
             * }
             * ............
             */
        }

        @Override
        public void onProgress(float progress) {
            Log.d(NetworkUtils.class.getSimpleName(), "上传或下载进度 : " + progress);
        }
    };

}

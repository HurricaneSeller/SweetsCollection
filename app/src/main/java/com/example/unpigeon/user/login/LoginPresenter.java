package com.example.unpigeon.user.login;

import android.util.Log;

import com.example.unpigeon.network.Method;
import com.example.unpigeon.network.NetworkUtils;
import com.example.unpigeon.network.RequestParams;
import com.example.unpigeon.network.call.ICallback;
import com.example.unpigeon.network.executor.factory.OkHttpExecutorFactory;
import com.example.unpigeon.user.Constant;
import com.example.unpigeon.user.UserContract;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPresenter implements UserContract.LoginPresenter {
    private final UserContract.LoginView mLoginView;
    private static final String ADDRESS_POST_LOGIN = "http://206.189.42.213:8888/user/login";
    private static final String TAG = "moanbigking";

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
        RequestParams requestParams = new RequestParams.Builder().url(ADDRESS_POST_LOGIN).method(Method.POST)
                .header(Constant.Http.HEADER_CONTENT_TYPE_KEY, Constant.Http.HEADER_CONTENT_TYPE_VALUE)
                .requestParams(Constant.Http.PARAMS_KEY_USERNAME, username)
                .requestParams(Constant.Http.PARAMS_KEY_PASSWORD, password)
                .tag(Constant.Http.TAG_LOGIN_POST).build();
        NetworkUtils.getInstance().doStart(requestParams, callback);
    }

    private ICallback callback = new ICallback() {

        @Override
        public void onStart() {
            Log.d(NetworkUtils.class.getSimpleName(), Constant.Msg.MSG_START_LOADING);
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "onComplete: ");
        }

        @Override
        public void onError(Exception e) {
            Log.d(NetworkUtils.class.getSimpleName(), Constant.Msg.MSG_ERROR_OCCUR);
            mLoginView.toast(Constant.Msg.UNKNOWN_ERROR);
        }


        /**
         * {
         *     "code": 1,
         *     "msg": {
         *         "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
         *         .eyJ1aWQiOiI1YzczYTMyMDdjZjg2ZDQ5NWMxNGJmMTIiLCJ1c2VybmFtZSI6Imh6eXRxbCIsImlzQWRtaW4iOmZhbHNlLCJpYXQiOjE1NTEwODIzNzgsImV4cCI6MTU1MTE2ODc3OH0.03cACYRJzk243kDTTcvV3UPepYMNCVS0AIXTbWKJIP8",
         *         "username": "hzytql",
         *         "isAdmin": false,
         *         "golds": 0,
         *         "email": "hzytql@hzytql.top"
         *     }
         * }
         * @param tag
         * @param result
         */
        @Override
        public void onSuccess(Object tag, String result) {
            if (tag.equals(Constant.Http.TAG_LOGIN_POST)) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int requestCode = jsonObject.optInt(Constant.Json.JSON_NAME_CODE);
                    if (requestCode != 1) {
                        mLoginView.toast(Constant.Msg.LOGIN_DEFEAT);
                        mLoginView.onLoginDefeat();
                    } else {
                        mLoginView.toast(Constant.Msg.LOGIN_SUCCESS);
                        mLoginView.onLoginSuccess();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onProgress(float progress) {
            Log.d(NetworkUtils.class.getSimpleName(), Constant.Msg.MSG_SHOW_PROGRESSING + progress);
        }
    };

}

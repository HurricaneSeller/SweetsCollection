package com.example.unpigeon.user.register;

import com.example.unpigeon.network.Method;
import com.example.unpigeon.network.NetworkUtils;
import com.example.unpigeon.network.RequestParams;
import com.example.unpigeon.network.call.ICallback;
import com.example.unpigeon.user.UserContract;
import com.example.unpigeon.utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterPresenter implements UserContract.RegisterPresenter {
    private final UserContract.RegisterView mRegisterView;
    private static final String ADDRESS_POST_REG = "http://206.189.42.213:8888/usr/reg";

    public RegisterPresenter(UserContract.RegisterView registerView) {
        mRegisterView = registerView;
    }

    @Override
    public void performRegister() {
        String username = mRegisterView.getUsername();
        String email = mRegisterView.getPassword();
//        String checkNumber = mRegisterView.getCheckNumber(); maybe used later
        RequestParams requestParams = new RequestParams.Builder()
                .url(ADDRESS_POST_REG).method(Method.POST)
                .header(Constant.Http.HEADER_CONTENT_TYPE_KEY, Constant.Http.HEADER_CONTENT_TYPE_VALUE)
                .requestParams(Constant.Http.PARAMS_KEY_USERNAME, username)
                .requestParams(Constant.Http.PARAMS_KEY_EMAIL, email)
                .tag(Constant.Http.TAG_REG_POST).build();
        NetworkUtils.getInstance().doStart(requestParams, mICallback);

    }

    private ICallback mICallback = new ICallback() {
        @Override
        public void onStart() {

        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Exception e) {

        }

        @Override
        public void onSuccess(Object tag, String result) {
            if (tag.equals(Constant.Http.TAG_REG_POST)) {
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(result);
                    int requestCode = jsonObject.optInt(Constant.Json.JSON_NAME_CODE);
                    if (requestCode != 1) {
                        mRegisterView.toast(Constant.Msg.LOGIN_DEFEAT);
                        mRegisterView.onRegisterDefeat();
                    } else {
                        mRegisterView.toast(Constant.Msg.LOGIN_SUCCESS);
                        mRegisterView.onRegisterSuccess();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

        @Override
        public void onProgress(float progress) {

        }
    };
}

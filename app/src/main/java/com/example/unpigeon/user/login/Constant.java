package com.example.unpigeon.user.login;

class Constant {
    class Msg {
        static final String MSG_START_LOADING = "网络开始加载";
        static final String MSG_ERROR_OCCUR = "出现错误";
        static final String MSG_SHOW_PROGRESSING = "上传或下载进度 : ";
        static final String LOGIN_DEFEAT = "用户名或者密码错误！";
        static final String LOGIN_SUCCESS = "登录成功！";
        static final String UNKNOWN_ERROR = "未知错误:(";
    }
    class Http{
        static final String HEADER_CONTENT_TYPE_KEY = "Content-Type";
        static final String HEADER_CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";
        static final String PARAMS_KEY_USERNAME = "username";
        static final String PARAMS_KEY_PASSWORD = "password";
        static final String TAG_LOGIN_POST = "tag_login_post";
    }
    class Json{
        static final String JSON_NAME_CODE = "code";
    }
}

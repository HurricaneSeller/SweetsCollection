package com.example.unpigeon.user;

public class Constant {
    public class Msg {
        public static final String MSG_START_LOADING = "网络开始加载";
        public static final String MSG_ERROR_OCCUR = "出现错误";
        public static final String MSG_SHOW_PROGRESSING = "上传或下载进度 : ";
        public static final String LOGIN_DEFEAT = "用户名或者密码错误！";
        public static final String LOGIN_SUCCESS = "登录成功！";
        public static final String UNKNOWN_ERROR = "未知错误:(";
    }

    public class Http {
        public static final String HEADER_CONTENT_TYPE_KEY = "Content-Type";
        public static final String HEADER_CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";
        public static final String PARAMS_KEY_USERNAME = "username";
        public static final String PARAMS_KEY_PASSWORD = "password";
        public static final String PARAMS_KEY_EMAIL = "email";
        public static final String TAG_LOGIN_POST = "tag_login_post";
        public static final String TAG_REG_POST = "tag_register_post";
    }

    public class Json {
        public static final String JSON_NAME_CODE = "code";
    }
}

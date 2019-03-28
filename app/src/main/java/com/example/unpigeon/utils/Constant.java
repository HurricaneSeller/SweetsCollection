package com.example.unpigeon.utils;

public class Constant {
    public static final String CHOSEN_TASK = "chosen_task";
    public static final String ON_OPEN_STORAGE_PERMISSION = "该应用需要存储功能才能正常使用";
    public static final String ON_OPEN_AUDIO_PERMISSION = "该应用需要录音功能才能正常使用";
    public static final String ON_CLICK_KNOWN = "我已阅读此提示";
    public static final String NOT_ENOUGH_PERMISSION_GIVEN = "当前应用未完全授权";
    public static final String ON_NO_ASK_AGAIN = "不开启将无法正常使用！";
    public static final String OK = "确定";
    public static final String START_RECORD = "开始录音";
    public static final String CHECK_COMMIT = "是否确认上传";
    public static final int LOAD_FINISHED = 111;
    public static final int CODE_ERROR = 400;
    public static final int CODE_OK = 200;
    public static final int REFRESH_TIME = 1;
    public static final int REFRESH_VIEW = 2;
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

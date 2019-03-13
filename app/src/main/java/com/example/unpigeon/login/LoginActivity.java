package com.example.unpigeon.login;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.example.unpigeon.R;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//no need now, just put it here
public class LoginActivity extends AppCompatActivity {

    private String TAG = "moanbigking";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }




    //注册
    public void setRequmentsWithOkhttp(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient = new OkHttpClient();

                    FormBody.Builder builder= new FormBody.Builder();

                    builder.add("email","11145495@qq.com").add("username","abcde").add("password",md5("123456"));
                    FormBody body = builder.build();


                    Request request = new Request.Builder().url("http://206.189.42.213:8888/user/reg")
                            .header("Content-Type","application/x-www-form-urlencoded")
                            .post(body).build();


                    Response response = okHttpClient.newCall(request).execute();
                    String responsedata = response.body().string();
                    Log.d(TAG,"response:"+responsedata);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG,"请求失败");

                }

            }
        }).start();


    }

    public void LogIn(){

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient = new OkHttpClient();

                    FormBody.Builder builder= new FormBody.Builder();

                    builder.add("username","abc").add("password","123456");
                    FormBody body = builder.build();


                    Request request = new Request.Builder().url("http://206.189.42.213:8888/user/login")
                            .header("Content-Type","application/x-www-form-urlencoded")
                            .post(body).build();


                    Response  response = okHttpClient.newCall(request).execute();
                    String responsedata = response.body().string();
                    Log.d(TAG,"response:"+responsedata);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG,"请求失败");

                }

            }
        }).start();

    }


    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}

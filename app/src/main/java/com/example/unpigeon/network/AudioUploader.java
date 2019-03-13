package com.example.unpigeon.network;

/*
 * encapsulate a uploader for audio-uploading
 * okhttp frame
 * the task-text is not a heavy work so there is no need to encapsulate a downloader now
 */


import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AudioUploader {

    private File file;
    private String TAG = "moanbigking";
    private String token;
    public static final MediaType MEDIA_TYPE_AUDIO
            = MediaType.parse("audio/x-wav; charset=utf-8");

    public AudioUploader(String pathname) {
        file = new File(pathname);
    }


    public void upLode(){

        LogIn();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    MultipartBody.Builder multipartBuilder= new MultipartBody.Builder()
                            .setType(MultipartBody.FORM);


                    Request request = new Request.Builder().url("http://206.189.42.213:8888//upload")
                            .header("Content-Type","application/x-www-form-urlencoded")
                            .addHeader("Authorization",token)
                            .post(RequestBody.create(MEDIA_TYPE_AUDIO, file)).build();


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
                    parseJSON(response.body().string());
                    String responsedata = response.body().string();
                    Log.d(TAG,"response:"+responsedata);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG,"请求失败");

                }

            }
        }).start();

    }

    private void parseJSON(String jsonData){

        try{

            JSONArray jsonArray = new JSONArray(jsonData);

            JSONObject jsonObject = jsonArray.getJSONObject(0);
            token = jsonObject.getString("msg");
            Log.d(TAG,"a"+token);


        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG,"获取失败");
        }


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

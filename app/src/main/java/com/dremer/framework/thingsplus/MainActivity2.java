package com.dremer.framework.thingsplus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.dremer.framework.mylibrary.okhttp.OkHttpClientManager;
import com.dremer.framework.mylibrary.utils.LogUtil;
import com.dremer.framework.mylibrary.utils.ToastUtil;
import com.squareup.okhttp.Request;


public class MainActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

//        String string = "http://www.bamenzhushou.com:8080/bamen/bannerwall";
//        OkHttpClientManager.requestGetAsync(string, new OkHttpClientManager.ResultCallback<String>() {
//            @Override
//            public void onError(Request request, Exception e) {
//                LogUtil.e("Error : " + e);
//                ToastUtil.showToast(getApplicationContext(), "Error : " + e);
//            }
//
//            @Override
//            public void onResponse(String response) {
//                LogUtil.e("Response : " + response.toString());
//                ToastUtil.showToast(getApplicationContext(), "Response : " + response.toString());
//            }
//        });

//        http://192.168.1.104:8080/bamenjifen/user
//        type=namecheck&username=yyy0004


        String url = "http://www.bamenzhushou.com:8080/bamenjifen/user";
        OkHttpClientManager.Param[] params = new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("type","namecheck"),
                new OkHttpClientManager.Param("username","yyy0004")};
        OkHttpClientManager.requestPostAsync(url, params, new OkHttpClientManager.ResultCallback<String>(){

            @Override
            public void onError(Request request, Exception e) {
                LogUtil.e("Error : " + e);
                ToastUtil.showToast(getApplicationContext(), "Error : " + e);
            }

            @Override
            public void onResponse(String response) {
                LogUtil.e("Response : " + response.toString());
                ToastUtil.showToast(getApplicationContext(), "Response : " + response.toString());
            }
        });
    }

    public void buttonClick(View v){
        LogUtil.e("点击了！");
    }
}

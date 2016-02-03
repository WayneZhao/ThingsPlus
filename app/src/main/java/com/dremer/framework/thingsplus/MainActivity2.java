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


        OkHttpClientManager.requestGetAsync("http://www.bamenzhushou.com/bamenjifen/user?uid=151204085634208535&type=id", new OkHttpClientManager.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.e("Error : " + e);
                ToastUtil.showToast(getApplicationContext(), "Error : " + e);
            }

            @Override
            public void onResponse(Object response) {
                LogUtil.e("Response : " + response.toString());
                ToastUtil.showToast(getApplicationContext(), "Response : " + response.toString());
            }
        });
    }

    public void buttonClick(View v){
        LogUtil.e("点击了！");
    }
}

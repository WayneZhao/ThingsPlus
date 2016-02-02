package com.dremer.framework.thingsplus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.dremer.framework.mylibrary.utils.LogUtil;


public class MainActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
    }

    public void buttonClick(View v){
        LogUtil.e("点击了！");
    }
}

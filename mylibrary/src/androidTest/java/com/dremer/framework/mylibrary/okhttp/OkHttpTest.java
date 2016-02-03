package com.dremer.framework.mylibrary.okhttp;

import android.test.AndroidTestCase;

import com.dremer.framework.mylibrary.utils.LogUtil;
import com.dremer.framework.mylibrary.utils.ToastUtil;
import com.squareup.okhttp.Request;

/**
 * Created by zx on 2016/2/3.
 */
public class OkHttpTest extends AndroidTestCase {

    public void testOkHttp() {
        OkHttpClientManager.requestGetAsync("http://www.bamenzhushou.com/bamenjifen/user?uid=151204085634208535&type=id", new OkHttpClientManager.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.e("Error : " + e);
                ToastUtil.showToast(getContext(),"Error : " + e);
            }

            @Override
            public void onResponse(Object response) {
                LogUtil.e("Response : " + response.toString());
                ToastUtil.showToast(getContext(),"Response : " + response.toString());
            }
        });
    }
}

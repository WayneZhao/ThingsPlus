package com.dremer.framework.mylibrary.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by zx on 2016/8/8.
 */
public class BaseFragment extends Fragment {
    private BaseActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (BaseActivity) context;
    }
}
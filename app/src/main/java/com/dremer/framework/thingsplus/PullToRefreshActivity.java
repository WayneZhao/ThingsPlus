package com.dremer.framework.thingsplus;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class PullToRefreshActivity extends Activity {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;

    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_refresh_layout);
        mListView = (ListView) findViewById(R.id.id_listview);

        data = new ArrayList<String>();
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        mListView.setAdapter(mAdapter);


        mSwipeRefreshLayout.setColorSchemeResources(R.color.swipe_color_1,
                R.color.swipe_color_2,
                R.color.swipe_color_3,
                R.color.swipe_color_4);
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);;
        //swipeRefreshLayout.setPadding(20, 20, 20, 20);
        //swipeRefreshLayout.setProgressViewOffset(true, 100, 200);
        //swipeRefreshLayout.setDistanceToTriggerSync(50);
        mSwipeRefreshLayout.setProgressViewEndTarget(true, 100);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        for (int i = 0; i < 200; i++) {
                            data.add("第" + i + "项数据...");
                        }

                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(1);
                    }
                }).start();
            }
        });
    }


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:

                    mSwipeRefreshLayout.setRefreshing(false);
                    mAdapter.notifyDataSetChanged();
                    //swipeRefreshLayout.setEnabled(false);
                    break;
                default:
                    break;
            }
        }

    };
}

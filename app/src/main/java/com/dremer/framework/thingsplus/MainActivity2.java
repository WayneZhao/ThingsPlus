package com.dremer.framework.thingsplus;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dremer.framework.mylibrary.utils.LogUtil;
import com.dremer.framework.mylibrary.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


public class MainActivity2 extends Activity {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;

    private int mLastVisibleItemPosition;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mSwipeRefreshLayout.setRefreshing(false);
            mAdapter.notifyItemRemoved(mAdapter.getItemCount());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.id_new_swipe_refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_new_recycler);

        List<String> lists = loadData();

        //设置布局管理器
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        //设置adapter
        mAdapter = new MyAdapter(this);
        mAdapter.setData(lists);
        mRecyclerView.setAdapter(mAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL_LIST));
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                LogUtil.e("**************CLICK " + position + " *****************");
            }

            @Override
            public void setOnItemLongClickListener(View view, int position) {
                LogUtil.e("**************LONG CLICK " + position + " *****************");
            }
        });

        // 这句话是为了，第一次进入页面的时候显示加载进度条
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_SETTLING
                        && mLastVisibleItemPosition + 1 == mAdapter.getItemCount()) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    mHandler.sendEmptyMessageDelayed(0, 3000l);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private List<String> loadData() {
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            lists.add("  #  " + i + "  #  ");
        }
        return lists;
    }

    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);

        void setOnItemLongClickListener(View view, int position);
    }

    public enum ViewType {
        TYPE_ITEM, TYPE_HEADER, TYPE_FOOTER
    }

    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private Context mContext;
        private List<String> mData;
        private OnItemClickListener mOnItemClickListener;

        public MyAdapter(Context context) {
            this.mContext = context;
        }

        public List<String> getData() {
            return mData;
        }

        public void setData(List<String> data) {
            this.mData = data;
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.mOnItemClickListener = listener;
        }

        @Override
        public int getItemViewType(int position) {
            if (position + 1 == getItemCount())
                return ViewType.TYPE_FOOTER.ordinal();
            else
                return ViewType.TYPE_ITEM.ordinal();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewType v = ViewType.values()[viewType];
            switch (v) {
                case TYPE_ITEM:
                case TYPE_HEADER:
                    return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler,
                            parent, false));
                case TYPE_FOOTER:
                    return new FooterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.footer_view_loading, parent, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MyViewHolder) {
                final MyViewHolder myViewHolder = (MyViewHolder) holder;
                myViewHolder.textView.setText(mData.get(position));
                if (mOnItemClickListener != null) {
                    myViewHolder.textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int pos = myViewHolder.getLayoutPosition();
                            mOnItemClickListener.setOnItemClickListener(myViewHolder.textView, pos);
                        }
                    });

                    myViewHolder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            int pos = myViewHolder.getLayoutPosition();
                            mOnItemClickListener.setOnItemLongClickListener(myViewHolder.textView, pos);
                            return true;
                        }
                    });
                }
            }
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;

            public MyViewHolder(final View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.id_tv_new_txt);
            }
        }

        class FooterViewHolder extends RecyclerView.ViewHolder {

            public FooterViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}

package com.korron.project.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.korron.project.R;
import com.korron.project.RecyclerViewOnClick;

/**
 * Created by milli on 2016/7/13.
 */
public class SilidingRecyclerViewAdapter extends RecyclerView.Adapter<SilidingRecyclerViewAdapter.SilidingViewHolder> {

    private Context mContext;
    private String[] mDate = {"用户信息", "我的关注", "我的粉丝", "我的空间", "发起直播", "购物", "购买VIP", "我的订单", "支付明细"};

    public SilidingRecyclerViewAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public SilidingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.adapter_item_silidingmenu_recyclerview, null);
        return new SilidingViewHolder(view);
    }


    @Override
    public void onBindViewHolder(SilidingViewHolder holder, int position) {
        holder._TvItem.setText(mDate[position]);
    }


    @Override
    public int getItemCount() {
        return mDate.length;
    }

    public void setRecvOnClick(RecyclerViewOnClick recvOnClick) {
    }

    public class SilidingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewOnClick mRecyclerViewOnClick;
        public TextView _TvItem;

        @TargetApi(Build.VERSION_CODES.M)
        public SilidingViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            _TvItem = (TextView) itemView.findViewById(R.id.tv_adapter_item_slidingmenu_recyclerview);
        }

        @Override
        public void onClick(View v) {
            if (mRecyclerViewOnClick != null) {
                mRecyclerViewOnClick.RecvOnClick(v, getPosition());
            }
        }
    }
}

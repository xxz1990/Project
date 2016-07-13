package com.korron.project.fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.korron.project.R;
import com.korron.project.RecyclerViewOnClick;
import com.korron.project.adapter.SilidingRecyclerViewAdapter;
import com.korron.project.utils.RecyclerViewDecoration;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by milli on 2016/7/12.
 */
public class SlidingMenuFragment extends Fragment {

    private Context mContext;

    private RelativeLayout mRl;
    private CircleImageView mCiv;
    private RecyclerView mRecv;
    private TextView mTvUserName;
    private TextView mTvVIP;
    private Button mBtnConnect;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        this.mContext = context;
    }

    public SlidingMenuFragment(){};

    public SlidingMenuFragment(Context context){
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sliding_menu,null);

        initView(view);

        mRecv.setHasFixedSize(true);
        mRecv.setLayoutManager(new LinearLayoutManager(mContext));
        mRecv.addItemDecoration(new RecyclerViewDecoration(mContext,LinearLayoutManager.HORIZONTAL,R.mipmap.line));
        SilidingRecyclerViewAdapter adapter = new SilidingRecyclerViewAdapter(mContext);
        mRecv.setAdapter(adapter);
        adapter.setRecvOnClick(new RecyclerViewOnClick() {
            @Override
            public void RecvOnClick(View view, int position) {

            }
        });

        return view;
    }

    View.OnClickListener slidingMenuOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rl_slidingmenu_title:
                    break;
                case R.id.btn_connectus:
                    break;
            }
        }
    };

    private void initView(View view) {
        mRl = (RelativeLayout) view.findViewById(R.id.rl_slidingmenu_title);
        mCiv = (CircleImageView) view.findViewById(R.id.iv_round_user_icon);
        mRecv = (RecyclerView) view.findViewById(R.id.rlv_item_list);
        mTvUserName = (TextView) view.findViewById(R.id.tv_user_name);
        mTvVIP = (TextView) view.findViewById(R.id.tv_vip);
        mBtnConnect = (Button) view.findViewById(R.id.btn_connectus);
    }


}

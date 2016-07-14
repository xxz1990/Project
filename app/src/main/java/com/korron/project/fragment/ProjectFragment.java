package com.korron.project.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.korron.project.Constant;
import com.korron.project.R;

/**
 * Created by milli on 2016/7/13.
 */
public class ProjectFragment extends Fragment {

    private Context mContext;
    private SwipeRefreshLayout mSrl;
    private RecyclerView mRecv;

    private TextView mTvHangYe;
    private TextView mTvXuQiu;

    private AbHttpUtil mAbHttpUtil;

    public ProjectFragment(Context context){
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_project,null);

        initView(view);

        mSrl.setColorSchemeColors(Color.BLUE,Color.RED,Color.YELLOW);
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAbHttpUtil = AbHttpUtil.getInstance(mContext);
                mAbHttpUtil.setTimeout(2000);
                AbRequestParams params = new AbRequestParams();
                //token=2342343243&require=0&category=0&page=0
                params.put("token","");
                params.put("require",0);
                params.put("category",0);
                params.put("page",0);
                mAbHttpUtil.post(Constant.INTER_NET + "/app/project/getProjects", params, new AbStringHttpResponseListener() {
                    @Override
                    public void onSuccess(int i, String s) {
                        Log.i("Millicent",s);
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onFinish() {
                        mSrl.setRefreshing(false);

                    }

                    @Override
                    public void onFailure(int i, String s, Throwable throwable) {
                        mSrl.setRefreshing(false);
                        Log.e("Millicent",throwable.getMessage().toString());
                    }
                });
            }
        });

        return view;

    }

    private void initView(View view) {
        mTvHangYe = (TextView) view.findViewById(R.id.tv_hangye);
        mTvXuQiu = (TextView) view.findViewById(R.id.tv_xuqiu);
        mSrl = (SwipeRefreshLayout) view.findViewById(R.id.srl_project);
        mRecv = (RecyclerView) view.findViewById(R.id.recv_project);
    }
}

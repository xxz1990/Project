package com.korron.project.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.korron.project.R;

/**
 * Created by milli on 2016/7/13.
 */
public class ProjectFragment extends Fragment {

    private TextView mTvHangYe;
    private TextView mTvXuQiu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_project,null);

        initView(view);

        return view;

    }

    private void initView(View view) {
        mTvHangYe = (TextView) view.findViewById(R.id.tv_hangye);
        mTvXuQiu = (TextView) view.findViewById(R.id.tv_xuqiu);
    }
}

package com.korron.project.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.korron.project.R;

public class MainActivity extends AppCompatActivity {

    private ImageView mIvWeiBo;
    private ImageView mIvWeiXin;
    private ImageView mIvQq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


    }

    private void initView() {
        mIvWeiBo = (ImageView) findViewById(R.id.iv_weibo_login);
        mIvWeiXin = (ImageView) findViewById(R.id.iv_weixin_login);
        mIvQq = (ImageView) findViewById(R.id.iv_qq_login);

        mIvWeiBo.setOnClickListener(mOnClick);
        mIvWeiXin.setOnClickListener(mOnClick);
        mIvQq.setOnClickListener(mOnClick);

    }

    View.OnClickListener mOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_weibo_login:
                    enterProject();
                    break;
                case R.id.iv_weixin_login:
                    enterProject();
                    break;
                case R.id.iv_qq_login:
                    enterProject();
                    break;
            }
        }
    };

    private void enterProject() {
        Intent i = new Intent();
        i.setClass(this,ProjectActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.in_right,R.anim.out_left);
        finish();
    }
}

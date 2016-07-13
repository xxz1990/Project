package com.korron.project.activity;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ab.view.sliding.AbBottomTabView;
import com.ab.view.slidingmenu.SlidingMenu;
import com.korron.project.R;
import com.korron.project.fragment.ActiveFragment;
import com.korron.project.fragment.CircleFragment;
import com.korron.project.fragment.ProjectFragment;
import com.korron.project.fragment.SlidingMenuFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milli on 2016/7/12.
 */
public class ProjectActivity extends AppCompatActivity {

    private SlidingMenu mSlidingMenu;
    private Toolbar mToolbar;
    private AbBottomTabView mBottomTabView;
    private List<Drawable> tabDrawables = null;

    private Button mBtnShowSliding;
    private Button mBtnSearch;
    private TextView mTvToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        mToolbar = (Toolbar) findViewById(R.id.tl_project);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        initView();

        initSlidingMenu();

        initBottomTab();


    }

    private void initBottomTab() {

        mBottomTabView = (AbBottomTabView) findViewById(R.id.mBottomTabView);

        //如果里面的页面列表不能下载原因：
        //Fragment里面用的AbTaskQueue,由于有多个tab，顺序下载有延迟，还没下载好就被缓存了。改成用AbTaskPool，就ok了。
        //或者setOffscreenPageLimit(0)

        //缓存数量
        mBottomTabView.getViewPager().setOffscreenPageLimit(1);

        //禁止滑动
        /*mBottomTabView.getViewPager().setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}

		});*/

        mBottomTabView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mTvToolbar.setText("项目");
                        mBtnSearch.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        mTvToolbar.setText("活动");
                        mBtnSearch.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        mTvToolbar.setText("圈子");
                        mBtnSearch.setVisibility(View.INVISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ProjectFragment page1 = new ProjectFragment();
        ActiveFragment page2 = new ActiveFragment();
        CircleFragment page3 = new CircleFragment();


        List<Fragment> mFragments = new ArrayList<Fragment>();
        mFragments.add(page1);
        mFragments.add(page2);
        mFragments.add(page3);

        List<String> tabTexts = new ArrayList<String>();
        tabTexts.add("项目");
        tabTexts.add("活动");
        tabTexts.add("圈子");

        //设置样式
        mBottomTabView.setTabTextColor(Color.WHITE);
        mBottomTabView.setTabSelectColor(Color.rgb(188 , 86, 10));
//        mBottomTabView.setTabBackgroundResource(R.drawable.tab_bg2);
        mBottomTabView.setTabLayoutBackgroundResource(android.R.color.black);

        //注意图片的顺序
        tabDrawables = new ArrayList<Drawable>();
        tabDrawables.add(this.getResources().getDrawable(R.mipmap.item_1_normal));
        tabDrawables.add(this.getResources().getDrawable(R.mipmap.item_1_press));
        tabDrawables.add(this.getResources().getDrawable(R.mipmap.item_2_normal));
        tabDrawables.add(this.getResources().getDrawable(R.mipmap.item_2_press));
        tabDrawables.add(this.getResources().getDrawable(R.mipmap.item_3_normal));
        tabDrawables.add(this.getResources().getDrawable(R.mipmap.item_3_press));
        mBottomTabView.setTabCompoundDrawablesBounds(0, 0, 40, 40);
        //演示增加一组
        mBottomTabView.addItemViews(tabTexts, mFragments, tabDrawables);

        mBottomTabView.setTabPadding(10, 10, 10, 10);
    }

    private void initView() {
        mBtnShowSliding = (Button) findViewById(R.id.btn_showslidingmenu);
        mBtnSearch = (Button) findViewById(R.id.btn_search);
        mTvToolbar = (TextView) findViewById(R.id.tv_toolbar);

        mBtnShowSliding.setOnClickListener(projectOnClick);
    }

    View.OnClickListener projectOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_search:

                    break;
                case R.id.btn_showslidingmenu:
                    if (mSlidingMenu.isMenuShowing()) {
                        mSlidingMenu.showContent();
                    } else {
                        mSlidingMenu.showMenu();
                    }
                    break;
            }
        }
    };

    private void initSlidingMenu() {
        //SlidingMenu的配置
        mSlidingMenu = new SlidingMenu(this);
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        mSlidingMenu.setFadeDegree(0.35f);
        mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

        //menu视图的Fragment添加
        mSlidingMenu.setMenu(R.layout.sliding_menu);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.sliding_menu, new SlidingMenuFragment(this))
                .commit();
    }
}

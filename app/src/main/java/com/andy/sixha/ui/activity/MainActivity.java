package com.andy.sixha.ui.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.andy.sixha.Common.AppManager;
import com.andy.sixha.MyApplication;
import com.andy.sixha.R;
import com.andy.sixha.bean.TabEntity;
import com.andy.sixha.mvp.presenter.Presenter;
import com.andy.sixha.ui.adapter.TabAdapter;
import com.andy.sixha.ui.fragment.FindFragment;
import com.andy.sixha.ui.fragment.IndexFragment;
import com.andy.sixha.ui.fragment.MessageFragment;
import com.andy.sixha.ui.fragment.MyFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.widget.MsgView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
//    @BindView(R.id.tv_week)
//    TextView mTvWeek;

    @BindView(R.id.tab_layout)
    CommonTabLayout tabs;
    @BindView(R.id.vp_fragment)
    ViewPager viewPager;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private List<Fragment> mFragment = new ArrayList<Fragment>();
    private String[] mTitles;
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitles = getResources().getStringArray(R.array.main_nav);
        setupPager();
    }

    //��ʼ��V
    private void setupPager() {

        mFragment.add(new IndexFragment());
        mFragment.add(new MessageFragment());
        mFragment.add(new FindFragment());
        mFragment.add(new MyFragment());

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i],mIconSelectIds[i],mIconUnselectIds[i]));
        }
        tabs.setTabData(mTabEntities);

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), mTitles, mFragment);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);

        //��λ��
        tabs.showMsg(0, 20);
        tabs.setMsgMargin(0, -5, 5);

        //��λ��
        tabs.showMsg(1, 100);
        tabs.setMsgMargin(1, -5, 5);

        //����δ����Ϣ���?
        tabs.showDot(2);
        MsgView rtv_2_2 = tabs.getMsgView(2);
        if (rtv_2_2 != null) {
//            UnreadMsgUtils.setSize(rtv_2_2, dp2px(7.5f));
        }

        //����δ����Ϣ����
        tabs.showMsg(3, 5);
        tabs.setMsgMargin(3, 0, 5);
        MsgView rtv_2_3 = tabs.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));

        }


        tabs.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position, false);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabs.setCurrentTab(position);
            }
        });
    }

    @Override
    protected boolean isShowHomeAsUpIndicator() {
        return false;
    }

    @Override
    protected void onDestroy() {
        AppManager.getAppManager().exitApp(MyApplication.getInstance());
        super.onDestroy();
    }
}

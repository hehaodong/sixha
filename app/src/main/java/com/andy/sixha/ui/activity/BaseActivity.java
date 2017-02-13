package com.andy.sixha.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.andy.sixha.Common.AppManager;
import com.andy.sixha.R;
import com.andy.sixha.mvp.presenter.Presenter;
import com.andy.sixha.thirdpart.LoggerUtil;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.ui.activity
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2016-11-25 14:36
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected abstract int getLayoutResource();
    protected CompositeSubscription mCompositeSubscription;
    protected Toolbar mToolbar;
    protected TextView mToolbarTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResId = getLayoutResource();
        try {
            if (layoutResId != 0) {
                setContentView(layoutResId);
            }
        } catch (Exception e) {
            LoggerUtil.e(e);
        }

        AppManager.getAppManager().addActivity(this);
    }
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        View v = findViewById(R.id.toolbar);
        if (v != null) {
            mToolbar = (Toolbar) v;
            setSupportActionBar(mToolbar);
            mToolbarTitle = (TextView) v.findViewById(R.id.toolbar_title);
            if (mToolbarTitle != null && getSupportActionBar() != null) {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
            if (getSupportActionBar() != null && isShowHomeAsUpIndicator()) {
                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

            mToolbar.setNavigationOnClickListener(view -> onBackPressed());
        }
//        transparent19and20();
    }
    @Override
    protected void onDestroy() {
        AppManager.getAppManager().removeActivity(this);
        super.onDestroy();
    }
    protected void transparent19and20() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    protected boolean isShowHomeAsUpIndicator() {
        return true;
    }
}

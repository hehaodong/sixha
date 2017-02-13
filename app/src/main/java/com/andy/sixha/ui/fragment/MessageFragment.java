package com.andy.sixha.ui.fragment;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.andy.sixha.R;
import com.andy.sixha.mvp.presenter.Presenter;
import com.andy.sixha.thirdpart.LoggerUtil;
import com.andy.sixha.ui.view.MySettingItemView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.ui.fragment
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2016-11-29 14:48
 */
public class MessageFragment extends BaseFragment {
    @BindView(R.id.iv_photo)
    PhotoView photoView;
    @BindView(R.id.sv_test)
    MySettingItemView settingItemView;
    @Override
    public int getLayoutResource() {
        return R.layout.fragment_message;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        String url = "http://img.tuku.cn/file_big/201502/3d101a2e6cbd43bc8f395750052c8785.jpg";
        Picasso.with(getContext()).load(url).into(photoView);

    }
    @Override
    public Presenter getPresenter() {
        return null;
    }
}

package com.andy.sixha.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.andy.sixha.Common.AppConstant;
import com.andy.sixha.R;
import com.andy.sixha.bean.Phase;
import com.andy.sixha.bean.TrendingRepo;
import com.andy.sixha.mvp.presenter.Presenter;
import com.andy.sixha.mvp.presenter.TrendsPresenter;
import com.andy.sixha.mvp.view.TrendingView;
import com.andy.sixha.thirdpart.ImageLoader;
import com.andy.sixha.ui.activity.LoginActivity;
import com.andy.sixha.ui.component.DividerItemDecoration;
import com.andy.sixha.util.NumberUtil;
import com.andy.sixha.util.ToastUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.ui.fragment
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2016-11-29 14:47
 */
public class FindFragment extends  BaseFragment<TrendsPresenter> implements TrendingView{
    @Override
    public int getLayoutResource() {
        return R.layout.fragment_find;
    }

    @Override
    public Presenter getPresenter() {
        return mPresenter;
    }


    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    TrendsPresenter mPresenter;

    String mLanguage = "all";
    List<TrendingRepo> repoList;
//    String mQuery;

    public static FindFragment newInstance(String language) {
        FindFragment fragment = new FindFragment();
        fragment.mLanguage = language;
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mQuery = mLanguage+"&language:" + mLanguage;
        mPresenter = new TrendsPresenter(this);
        mPresenter.getTrending(mLanguage, true);
        mRefreshLayout.setOnRefreshListener(() -> {
            mPresenter.getTrending(mLanguage, false);
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, R.color.colorPrimary));

//        mAdapter.setOnItemClickListener((itemView, position) -> {
//            String url = mAdapter.getItem(position).getLink();
//            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//            builder.setToolbarColor(ContextCompat.getColor(getContext(), R.color.primary));
//            CustomTabsIntent customTabsIntent = builder.build();
//            CustomTabActivityHelper.openCustomTab(
//                    getActivity(), customTabsIntent, Uri.parse(url), new WebViewFallback());
//        });
    }

    @Override
    public void onRequestLoading() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onRequestFinished() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRequestError(int code, String message) {
        ToastUtil.show(getContext(), message);
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetTrendingsFinish(List<TrendingRepo> repos) {
        mRecycleView.setAdapter(new BaseQuickAdapter<TrendingRepo>(R.layout.item_trends, repos) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, TrendingRepo repos) {

                ImageView imageView = baseViewHolder.getView(R.id.iv_user_face);
                TextView titleTv = baseViewHolder.getView(R.id.tv_repo_name);
                TextView descTv = baseViewHolder.getView(R.id.tv_repo_desc);
                TextView starCountTv = baseViewHolder.getView(R.id.tv_stars_today);

                titleTv.setText(repos.getOwner()+"/"+repos.getRepo());
                descTv.setText(repos.getDesc());
                starCountTv.setText(String.valueOf(repos.getStars_today())+" stars today");

                ImageLoader.loadImage(imageView,imageView.getContext(), repos.getAvatar());
            }

        });
    }

    @Override
    public void onDestroy() {
//        mPresenter.destroy();
        super.onDestroy();
    }
}

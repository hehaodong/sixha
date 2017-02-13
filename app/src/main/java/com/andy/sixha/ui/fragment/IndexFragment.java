package com.andy.sixha.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andy.sixha.Common.AppConstant;
import com.andy.sixha.MyApplication;
import com.andy.sixha.R;
import com.andy.sixha.bean.MarkSix;
import com.andy.sixha.bean.Phase;
import com.andy.sixha.mvp.presenter.Presenter;
import com.andy.sixha.mvp.view.GetLatestView;
import com.andy.sixha.ui.activity.LoginActivity;
import com.andy.sixha.util.NumberUtil;
import com.andy.sixha.util.ToastUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.ui.fragment
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2016-11-28 11:16
 */
public class IndexFragment extends BaseFragment implements GetLatestView{
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.list_view)
    RecyclerView listView;

    List<Phase> mList = new ArrayList<Phase>();
    LinearLayoutManager linearLayoutManager;
    BaseQuickAdapter<List<Phase>> adapter;
    @Override
    public int getLayoutResource() {
        return R.layout.fragment_index;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }
    @OnClick(R.id.btn_login)
    public void login(View v){
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }
    private void init(){
        mList.add(new Phase("1,2,3,4,5,6",26,500,2,true,"132"));
        mList.add(new Phase("7,12,43,4,5,28",33,300,1,true,"133"));


        mRefreshLayout.setOnRefreshListener(this::refresh);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        listView.setLayoutManager(linearLayoutManager);
        listView.setAdapter(new BaseQuickAdapter<Phase>(R.layout.item_phase, mList) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, Phase phase) {
                baseViewHolder.setText(R.id.tv_phase,phase.getPhase()+"")
                        .setText(R.id.tv_small_code,phase.getSmallCode()+"")
                        .setText(R.id.tv_big_code,phase.getBigCode()+"")
                        .setText(R.id.tv_buy_type,phase.getBuyType()== AppConstant.BUY_ODD?"µ¥":"Ë«"+"")
                        .setText(R.id.tv_buy_amount,phase.getBuyAmount()+"");
                if(phase.isOpen()){
                    baseViewHolder.setText(R.id.tv_earn_amount, NumberUtil.getInstance().earnAmount(phase.getBuyType(),phase.getBigCode(),phase.getBuyAmount())+"");
                }else {
                    baseViewHolder.setText(R.id.tv_earn_amount,"0");
                }
            }
        });
        listView.setItemAnimator(new DefaultItemAnimator());
//        mRefreshLayout.setRefreshing(true);
    }
    void refresh() {
        mList.add(new Phase("7,12,43,4,5,28",24,500,1,true,"131"));
        mList.add(new Phase("7,12,43,4,5,28",24,500,1,true,"131"));
        mRefreshLayout.setRefreshing(false);
        listView.getAdapter().notifyDataSetChanged();
//        mStart = 0;
//        mPresenter.getHistoryList(mStart, LIMIT, mUserId);
    }

    @Override
    public void onGetLatestFinish(MarkSix markSix) {

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
        mRefreshLayout.setRefreshing(false);
        ToastUtil.show(getActivity(),message);
    }
    @Override
    public Presenter getPresenter() {
        return null;
    }
}

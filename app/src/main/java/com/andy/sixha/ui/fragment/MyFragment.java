package com.andy.sixha.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.andy.sixha.R;
import com.andy.sixha.bean.MarkSix;
import com.andy.sixha.mvp.presenter.HistoryPresenter;
import com.andy.sixha.mvp.presenter.Presenter;
import com.andy.sixha.mvp.view.GetHistoryView;
import com.andy.sixha.thirdpart.LoggerUtil;
import com.andy.sixha.util.ToastUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.ui.fragment
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2016-11-29 14:48
 */
public class MyFragment extends BaseFragment implements GetHistoryView{
    @BindView(R.id.ptr)
    PtrClassicFrameLayout mPtrFrame;
    @BindView(R.id.recycle_view)
    RecyclerView listView;


    LinearLayoutManager linearLayoutManager;
    BaseQuickAdapter<MarkSix> adapter;

    HistoryPresenter mPresenter;

    private List<Integer> bigMarkList = new ArrayList<Integer>();
    List<MarkSix> mList = new ArrayList<MarkSix>();
    private boolean isLoadMore = false;
    @Override
    public int getLayoutResource() {
        return R.layout.fragment_my;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new HistoryPresenter(this);
        init();
//        mPresenter.getHistory("2015");
//        refresh();
        mPtrFrame.autoRefresh();
    }

    private void init(){
//        mRefreshLayout.setOnRefreshListener(this::refresh);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(linearLayoutManager);

        final PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(getActivity());
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);
        mPtrFrame.setHeaderView(header);
        // mPtrFrame.setPinContent(true);//刷新时，保持内容不动，仅头部下移,默认,false
        mPtrFrame.addPtrUIHandler(header);
        //mPtrFrame.setKeepHeaderWhenRefresh(true);//刷新时保持头部的显示，默认为true
        //mPtrFrame.disableWhenHorizontalMove(true);//如果是ViewPager，设置为true，会解决ViewPager滑动冲突问题。
// 为布局设置下拉刷新和上拉加载的回调事件

        mPtrFrame.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) { // 上拉加载的回调方法
                loadMore();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) { // 下拉刷新的回调方法
                refresh();
            }
        });

    }

    void refresh(){
//        mRefreshLayout.setRefreshing(true);
        mList.clear();
        mPresenter.getHistory("2017");
    }
    void loadMore(){
        mPresenter.getHistory("2015");
    }
    @Override
    public Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void onGetHistory(List<MarkSix> markSixList) {
        mPtrFrame.refreshComplete();
        if(markSixList == null)return;
        for(int i=0;i<markSixList.size();i++){
            MarkSix markSix = markSixList.get(i);
            bigMarkList.add(Integer.parseInt(markSix.getOne_code().substring(0,2)));
        }
        Log.d("dddddddd",bigMarkList.toString());
        mList.addAll(markSixList);
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }else{
            adapter = new BaseQuickAdapter<MarkSix>(R.layout.item_marksix, mList) {
                @Override
                protected void convert(BaseViewHolder baseViewHolder, MarkSix markSix) {
                    baseViewHolder.setText(R.id.tv_phase,markSix.getStage().substring(0,4)+"\n"+markSix.getStage().substring(4,markSix.getStage().length()))
                            .setText(R.id.tv_t_code,"小码:"+markSix.getSix_code().toString().replace("[","").replace("]","")+"\n特别号码： "+markSix.getOne_code());
                }
            };
            listView.setAdapter(adapter);
            listView.setItemAnimator(new DefaultItemAnimator());
        }

    }

    @Override
    public void onRequestLoading() {
        mPtrFrame.autoRefresh();
    }

    @Override
    public void onRequestFinished() {
        mPtrFrame.refreshComplete();
    }

    @Override
    public void onRequestError(int code, String message) {
        ToastUtil.show(getContext(), message);
        mPtrFrame.refreshComplete();
    }

}

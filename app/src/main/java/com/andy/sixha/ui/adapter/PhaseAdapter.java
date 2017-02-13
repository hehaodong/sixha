package com.andy.sixha.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andy.sixha.Common.AppConstant;
import com.andy.sixha.R;
import com.andy.sixha.bean.Phase;
import com.andy.sixha.util.NumberUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.adapter
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2016-11-18 11:25
 */
public class PhaseAdapter extends RecyclerView.Adapter<PhaseAdapter.ViewHolder> {
    private Context mContext;
    private List<Phase> mList = new ArrayList<Phase>();
    public PhaseAdapter(Context context, List<Phase> list){
        this.mContext = context;
        this.mList = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_phase,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Phase phase = (Phase)mList.get(position);
        holder.tv_phase.setText(phase.getPhase()+"");
        holder.tv_small_code.setText(phase.getSmallCode()+"");
        holder.tv_big_code.setText(phase.getBigCode()+"");

        holder.tv_buy_type.setText(phase.getBuyType()== AppConstant.BUY_ODD?"µ¥":"Ë«"+"");
        holder.tv_buy_amount.setText(phase.getBuyAmount()+"");
        if(phase.isOpen()){
            holder.tv_earn_amount.setText(NumberUtil.getInstance().earnAmount(phase.getBuyType(),phase.getBigCode(),phase.getBuyAmount())+"");
        }else {
            holder.tv_earn_amount.setText("0");
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_phase)
        TextView tv_phase;
        @BindView(R.id.tv_small_code)
        TextView tv_small_code;
        @BindView(R.id.tv_big_code)
        TextView tv_big_code;
        @BindView(R.id.tv_buy_type)
        TextView tv_buy_type;
        @BindView(R.id.tv_buy_amount)
        TextView tv_buy_amount;
        @BindView(R.id.tv_earn_amount)
        TextView tv_earn_amount;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

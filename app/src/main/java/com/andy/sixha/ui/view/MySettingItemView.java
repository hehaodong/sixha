package com.andy.sixha.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andy.sixha.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.ui.view
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2016-12-22 16:57
 */
public class MySettingItemView extends LinearLayout {

    private Context mContext;
    @BindView(R.id.tv_title)
    public TextView tvTitle;
    @BindView(R.id.tv_sub_title)
    public TextView tvSubTitle;
    @BindView(R.id.iv_right_arrow)
    public ImageView ivRightArrow;

    private String title;
    private String sub_title;
    private int right_arrow;

    public MySettingItemView(Context context) {
        super(context);
        mContext = context;
        initView(null, 0);
    }

    public MySettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(attrs, 0);
    }

    public MySettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(attrs, defStyleAttr);
    }

//    public MySettingItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        mContext = context;
//        initView();
//    }
    private void initView(AttributeSet attrs, int defStyleAttr){
        View.inflate(mContext, R.layout.view_my_setting_item, this);
        ButterKnife.bind(this);
        final TypedArray attributes = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.MySettingItemView, defStyleAttr, 0);
        initByAttributes(attributes);
        if(right_arrow == 0){
            right_arrow = R.mipmap.ic_next;
        }
        setValue(title,sub_title,right_arrow);
    }
    protected void initByAttributes(TypedArray attributes) {
        right_arrow = attributes.getResourceId(R.styleable.MySettingItemView_right_arrow, R.mipmap.ic_next);
        title = attributes.getString(R.styleable.MySettingItemView_title);
        sub_title = attributes.getString(R.styleable.MySettingItemView_sub_title);
    }

    /**
     * 手动设置项目的值
     * @param resTitle
     * @param resSubTitle
     * @param resRightArrow
     */
    public void setValue(int resTitle,int resSubTitle,int resRightArrow){
        if(resTitle!=0){
            tvTitle.setText(resTitle);
        }
        if(resSubTitle!=0){
            tvSubTitle.setText(resSubTitle);
        }
        if(resRightArrow!=0){
            ivRightArrow.setImageResource(resRightArrow);
        }
    }
    /**
     * 手动设置项目的值
     * @param resTitle
     * @param resSubTitle
     * @param resRightArrow
     */
    public void setValue(String resTitle,String resSubTitle,int resRightArrow){
        if(!TextUtils.isEmpty(resTitle)){
            tvTitle.setText(resTitle);
        }
        if(!TextUtils.isEmpty(resSubTitle)){
            tvSubTitle.setText(resSubTitle);
        }
        if(resRightArrow!=0){
            ivRightArrow.setImageResource(resRightArrow);
        }
    }
}

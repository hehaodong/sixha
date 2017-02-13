package com.andy.sixha.thirdpart;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.util
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2016-11-24 16:35
 */
public class ImageLoader {
//    public static ImageLoader instance;
//
//    public  static ImageLoader getInstance(){
//        if(instance == null){
//            instance = new ImageLoader();
//        }
//        return instance;
//    }

    /**
     * º”‘ÿÕº∆¨
     * @param imageView
     * @param context
     * @param imgUrl
     */
    public static void loadImage(ImageView imageView, Context context, String imgUrl){
        Picasso.with(context).load(imgUrl).into(imageView);
    }

}

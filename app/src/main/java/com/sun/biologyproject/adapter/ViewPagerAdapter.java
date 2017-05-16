package com.sun.biologyproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 轮播图的适配器
 * Created by SUN on 2017/3/7.
 */
public class ViewPagerAdapter extends PagerAdapter {

    /**
     * 存放ImageView视图列表
     */
    private List<ImageView> imageViewList;

    public ViewPagerAdapter(){
        imageViewList = new ArrayList<>();
    }

    public void setData(List<ImageView> imageViews){
        if(imageViews != null){
            imageViewList = imageViews;
        }
}

    @Override
    public int getCount() {
        return imageViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageViewList.get(position));
        return imageViewList.get(position);
    }
}

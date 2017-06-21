package com.sun.biologyproject.adapter;

import android.content.Context;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sun.biologyproject.R;
import com.sun.biologyproject.bean.BiologyBean;
import com.sun.biologyproject.bean.RecordBean;

import java.util.List;

/**
 * Created by SUN on 2017/6/22.
 */
public class RecordListViewAdapter extends BaseAdapter {

    private List<RecordBean> mData;
    private Context mContext;

    public RecordListViewAdapter(Context context){
        mContext = context;
    }

    public void setmData(List<RecordBean> list){
        mData = list;
    }

    @Override
    public int getCount() {
        if (mData != null){
            return mData.size();
        }
        return 0;
    }

    @Override
    public RecordBean getItem(int i) {
        if (mData != null){
            return mData.get(i);
        }else {
            return new RecordBean();
        }
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.record_listview_item, null);
            holder = new ViewHolder();
            holder.mTvScore = (TextView) view.findViewById(R.id.tv_score);
            holder.mTvTitle = (TextView) view.findViewById(R.id.tv_title);
            holder.mTvLocation = (TextView) view.findViewById(R.id.tv_location);
            holder.mTvTime = (TextView) view.findViewById(R.id.tv_time);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        RecordBean item = getItem(i);
        holder.mTvScore.setText(String.valueOf(item.getScore()));
        holder.mTvTitle.setText(item.getWatersName());
        holder.mTvLocation.setText(item.getLocation());
        holder.mTvTime.setText(item.getTime().getDate());

        return view;
    }

    class ViewHolder{
        TextView mTvScore;
        TextView mTvTitle;
        TextView mTvLocation;
        TextView mTvTime;
    }
}

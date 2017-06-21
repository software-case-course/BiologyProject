package com.sun.biologyproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sun.biologyproject.R;
import com.sun.biologyproject.bean.BiologyBean;
import com.sun.biologyproject.bean.RecordBean;

import java.util.List;

/**
 * Created by SUN on 2017/6/22.
 */
public class ReportListViewAdapter extends BaseAdapter {

    List<BiologyBean> mData;
    Context mContext;

    public ReportListViewAdapter(Context context){
        mContext = context;
    }

    public void setmData(List<BiologyBean> mData) {
        this.mData = mData;
    }

    @Override
    public int getCount() {
        if (mData != null){
            return mData.size();
        }
        return 0;
    }

    @Override
    public BiologyBean getItem(int i) {
        if (mData != null){
            return mData.get(i);
        }else {
            return new BiologyBean();
        }
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.report_listview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.mTvName = (TextView) view.findViewById(R.id.tv_name);
            viewHolder.mIvDelete = (ImageView) view.findViewById(R.id.iv_delete);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.mTvName.setText(getItem(i).getName());
        viewHolder.mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.remove(i);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    class ViewHolder{
        TextView mTvName;
        ImageView mIvDelete;
    }
}

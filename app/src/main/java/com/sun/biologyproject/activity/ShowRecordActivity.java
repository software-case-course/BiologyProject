package com.sun.biologyproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.aitangba.swipeback.SwipeBackActivity;
import com.github.pavlospt.CircleView;
import com.sun.biologyproject.R;
import com.sun.biologyproject.adapter.ReportListViewAdapter;
import com.sun.biologyproject.bean.RecordBean;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ShowRecordActivity extends SwipeBackActivity {

    private CircleView mCircleView;
    private TextView mTvWaterName;
    private TextView mTvLocation;
    private TextView mTvTime;
    private TextView mTvTools;
    private ListView mLvShowCollect;

    private ReportListViewAdapter mAdapter = new ReportListViewAdapter(this);

    private RecordBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_record);
        loadData();
        initView();
    }

    private void loadData(){
        Intent data = getIntent();
        if (data == null){
            return;
        }

        String objId = data.getStringExtra("id");
        BmobQuery<RecordBean> query = new BmobQuery<>();
        query.addWhereEqualTo("objectId", objId);
        query.findObjects(new FindListener<RecordBean>() {
            @Override
            public void done(List<RecordBean> list, BmobException e) {
                if (e == null && list != null){
                    if (list.size() > 0 ){
                        mBean = list.get(0);
                        setViewData();
                    }
                }
            }
        });
    }

    private void initView(){
        mCircleView = (CircleView) findViewById(R.id.cv_score);
        mTvWaterName = (TextView) findViewById(R.id.tv_watersName);
        mTvLocation = (TextView) findViewById(R.id.tv_location);
        mTvTime = (TextView) findViewById(R.id.tv_time);
        mTvTools  = (TextView) findViewById(R.id.tv_tools);
        mLvShowCollect = (ListView) findViewById(R.id.lv_showCollect);
        mLvShowCollect.setAdapter(mAdapter);
    }

    private void setViewData(){
        mCircleView.setTitleText(String.valueOf(mBean.getScore()));
        mTvWaterName.setText(mBean.getWatersName());
        mTvLocation.setText(mBean.getLocation());
        mTvTime.setText(mBean.getTime().getDate());
        StringBuffer stringBuffer = new StringBuffer();
        List<String> stringList = mBean.getTools();
        for (String i: stringList){
            stringBuffer.append(i+ "  ");
        }
        mTvTools.setText(stringBuffer.toString());
        mAdapter.setmData(mBean.getBiologyBeanList());
        mAdapter.notifyDataSetChanged();
    }
}

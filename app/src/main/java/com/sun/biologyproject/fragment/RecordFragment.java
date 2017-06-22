package com.sun.biologyproject.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sun.biologyproject.R;
import com.sun.biologyproject.activity.ShowRecordActivity;
import com.sun.biologyproject.adapter.RecordListViewAdapter;
import com.sun.biologyproject.bean.RecordBean;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by SUN on 2017/6/21.
 */
public class RecordFragment extends Fragment {

    private ListView mLvShowRecord;
    private RecordListViewAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.record_fragment_layout, null);
        mLvShowRecord = (ListView) contentView.findViewById(R.id.lv_showRecord);
        mLvShowRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RecordBean bean = mAdapter.getItem(i);
                Intent intent = new Intent(getActivity(), ShowRecordActivity.class);
                intent.putExtra("id", bean.getObjectId());
                startActivity(intent);
            }
        });
        mAdapter = new RecordListViewAdapter(getActivity());
        mLvShowRecord.setAdapter(mAdapter);
        return contentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("加载中...");
        progressDialog.setTitle(null);
        progressDialog.setCancelable(true);
        progressDialog.show();

        BmobQuery<RecordBean> query = new BmobQuery<>();
        query.addWhereEqualTo("userName", BmobUser.getCurrentUser().getUsername());
        query.findObjects(new FindListener<RecordBean>() {
            @Override
            public void done(List<RecordBean> list, BmobException e) {
                if (e == null){
                    if (list != null && list.size() > 0){
                        mAdapter.setmData(list);
                        mAdapter.notifyDataSetChanged();
                    }
                }
                progressDialog.cancel();
            }
        });
    }
}

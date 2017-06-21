package com.sun.biologyproject.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.pavlospt.CircleView;
import com.sun.biologyproject.MainApplication;
import com.sun.biologyproject.R;
import com.sun.biologyproject.adapter.ReportListViewAdapter;
import com.sun.biologyproject.bean.Adress;
import com.sun.biologyproject.bean.BiologyBean;
import com.sun.biologyproject.bean.RecordBean;
import com.sun.biologyproject.bean.User;
import com.sun.biologyproject.utils.SharedUtils;
import com.sun.biologyproject.utils.ToastUtils;

import   java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.b.I;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static android.R.attr.fastScrollOverlayPosition;
import static android.R.attr.parentActivityName;
import static android.R.attr.positiveButtonText;
import static android.R.attr.resource;
import static android.R.attr.toAlpha;

/**
 * Created by Administrator on 2017/5/17.
 */

public class ReportActivity extends Activity{
//    private TextView time1;
//    private ListView listView;
//    private ArrayAdapter<String>arr_adapter;
//    private SimpleAdapter simp_adapter;
//    private ArrayList<String> arr_data;

    /**
     * View
     */
    private CircleView mCircleView;
    private ListView mLvShowBiology;
    private Button mBtnCollect;
    private Button mBtnUpload;

    private ReportListViewAdapter mAdapter = new ReportListViewAdapter(this);

    Integer score = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        initView();
//        listView=(ListView) findViewById(R.id.listView);
//        String [] tempArr = {"物种1", "物种2", "物种3", "物种4"};
//        arr_data = new ArrayList<String>(Arrays.asList(tempArr));
//        arr_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr_data);
//        //simp_adapter=new SimpleAdapter(this,arr_data,resource,from,to)
//
//        listView.setAdapter(arr_adapter);
//        listView.setOnItemClickListener(this);
//        listView.setOnScrollListener(this);
//
//
//        time1 = new TextView(this);
//        SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
//        Date curDate =  new Date(System.currentTimeMillis());
//        String   str   =   formatter.format(curDate);
//
//        this.time1 = (TextView) findViewById(R.id.timer);
//        time1.setText(str);
//        initView1();
    }
//    private void initView1()
//    {Button button2=(Button)findViewById(R.id.identify_more);
//    button2.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent intent=new Intent(ReportActivity.this,IdentifyCrittersActivity.class);
//            startActivity(intent);
//        }
//    });}
//
//    @Override
//    public void onScrollStateChanged(AbsListView absListView, int i) {
//
//    }
//
//    @Override
//    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//
//    }
//
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
//
////        Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_LONG).show();
//        arr_data.remove(position);
//        arr_adapter.notifyDataSetChanged();
////         arr_adapter.remove(String.valueOf(position));
////        arr_adapter.notifyDataSetChanged();
//    }

    private void initView(){

        mCircleView = (CircleView) findViewById(R.id.cv_score);
        List<BiologyBean> list = MainApplication.collectList;
        for (BiologyBean bean: list){
            score += bean.getScore();
        }
        score = score/list.size();
        mCircleView.setTitleText(String.valueOf(score));

        mLvShowBiology = (ListView) findViewById(R.id.lv_showCollect);
        mAdapter.setmData(MainApplication.collectList);
        mLvShowBiology.setAdapter(mAdapter);

        mBtnCollect = (Button) findViewById(R.id.btn_collect);
        mBtnCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportActivity.this, IdentifyCrittersActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mBtnUpload = (Button) findViewById(R.id.btn_upload);
        mBtnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText = new EditText(ReportActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(ReportActivity.this);
                builder.setView(editText);
                builder.setTitle("请输入本水域的名称");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        uploadData(editText.getText().toString().trim());
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void uploadData(String watersName){
        RecordBean recordBean = new RecordBean();
        recordBean.setWatersName(watersName);
        recordBean.setUserName(BmobUser.getCurrentUser(User.class).getUsername());

        String []ary = SharedUtils.readMyTools(ReportActivity.this).split(",");
        List<String> tools = new ArrayList<String>();
        for (String i: ary){
            tools.add(i);
        }
        recordBean.setTools(tools);

        recordBean.setScore(score);
        recordBean.setBiologyBeanList(MainApplication.collectList);
        recordBean.setLocation(Adress.getAdressInstance().getAdress());
        Date date = new Date(System.currentTimeMillis());
        recordBean.setTime(new BmobDate(date));
        recordBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null){
                    ToastUtils.showShortToast(ReportActivity.this, "上传成功！");
                }else {
                    ToastUtils.showShortToast(ReportActivity.this, s);
                }
                finish();
            }
        });
    }
}


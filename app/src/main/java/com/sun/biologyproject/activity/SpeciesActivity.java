package com.sun.biologyproject.activity;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aitangba.swipeback.SwipeBackActivity;
import com.bumptech.glide.Glide;
import com.sun.biologyproject.MainApplication;
import com.sun.biologyproject.R;
import com.sun.biologyproject.bean.BiologyBean;
import com.sun.biologyproject.bean.BiologyImage;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SpeciesActivity extends AppCompatActivity {

//    private GridView show_one;
//    //private int num;
//    private ImageView imageView;
//    private int firstId,secondId;
    private String TAG = getClass().getSimpleName();

    /**
     * View
     */
    private ImageView mIvBack;
    private TextView mTvTitle;
    private LinearLayout mContentView;//显示生物图片的搭载体
    private TextView mTvEnvironment;//生物生存的环境
    private Button mBtnCollect;//收集生物按钮

    private String mBiologyId;//生物对应的Id
    private BiologyBean mBiologyBean = new BiologyBean();





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species);
       // setContentView(R.layout.test_layout);
//        show_one=(GridView)findViewById(R.id.show_grid);
//        firstId=Integer.parseInt(FirstId);
//        secondId=Integer.parseInt(SecondId);
//
//        GridViewDB gridViewDB=GridViewDB.getInstance();//获取DB对象
        //imageView.setImageResource(gridViewDB.getPictureId(firstId,secondId));//gridViewDB.getPictureId(firstId,secondId)获取图片的id
        //num = Integer.parseInt(str);
//        show_one = (GridView)findViewById(R.id.show_grid);
//        show_one.setNumColumns(1);
//        show_one.setAdapter(new ShowAdapter(this,firstId,secondId));

        /**
         * 获取指定生物的Id
         */
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String FirstId = bundle.getString("firstId");
        String SecondId=bundle.getString("secondId");
        Log.d(getClass().getSimpleName(), "firstId：" + FirstId);
        Log.d(getClass().getSimpleName(), "secondId：" + SecondId);
        mBiologyId = FirstId + "-" + SecondId;

        initView();
        loadImage();

    }

    private void initView()
    {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backAction();
            }
        });

        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mContentView = (LinearLayout) findViewById(R.id.linearContentView);
        mTvEnvironment = (TextView) findViewById(R.id.tv_environment);

        mBtnCollect = (Button) findViewById(R.id.btn_collect);
        mBtnCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<BiologyBean> list = MainApplication.collectList;
                if (!list.contains(mBiologyBean)){
                    list.add(mBiologyBean);
                }
                Intent intent = new Intent(SpeciesActivity.this,ReportActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            backAction();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void backAction(){
        Intent intent = new Intent(SpeciesActivity.this, IdentifyCrittersActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
        finish();
    }

    /**
     * 加载图片
     */
    private void loadImage(){

        BmobQuery<BiologyImage> query = new BmobQuery<>();
        query.addWhereEqualTo("id", mBiologyId);
        query.order("index");
        query.findObjects(new FindListener<BiologyImage>() {
            @Override
            public void done(List<BiologyImage> list, BmobException e) {
                if (e == null){
                    if (list != null && list.size() > 0){
                        ImageView imageView;
                        //加载图片
                        for (BiologyImage item: list){
                            imageView = new ImageView(SpeciesActivity.this);
                            Glide.with(SpeciesActivity.this).load(item.getImgUrl()).into(imageView);
                            mContentView.addView(imageView);
                            Log.d(TAG, item.getIndex() + "--" + item.getName() + "---" + item.getImgUrl());
                        }
                    }
                }
            }
        });

        BmobQuery<BiologyBean> beanBmobQuery = new BmobQuery<>();
        beanBmobQuery.addWhereEqualTo("id", mBiologyId);
        beanBmobQuery.findObjects(new FindListener<BiologyBean>() {
            @Override
            public void done(List<BiologyBean> list, BmobException e) {
                if (e == null && list != null){
                    if (list.size() > 0){
                        mBiologyBean = list.get(0);
                        mTvTitle.setText(mBiologyBean.getName());
                        mTvEnvironment.setText(mBiologyBean.getEnvironment());
                    }
                }
            }
        });

    }
}

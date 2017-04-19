package com.sun.biologyproject;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CollectCrittersActivity extends AppCompatActivity {

    /**
     * View
     */
    private ViewPager viewPager;
    private TextView  tv_title;

    /**
     * Adapter
     */
    private ViewPagerAdapter adapter;

    /**
     * Data
     */
    private List<ImageView> imageIdList = new ArrayList<>();
    private List<String>    titleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_critters);
        initParam();
        intiView();
    }

    /**
     * 初始化一些变量
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initParam(){
        int[] imgId = {R.drawable.img_step_one, R.drawable.img_step_two, R.drawable.img_step_three, R.drawable.img_step_four,
                        R.drawable.img_step_five, R.drawable.img_step_six, R.drawable.img_step_seven, R.drawable.img_step_eight, R.drawable.img_step_nine};
        String[] titleArys = {"找到一个好的位置", "在河流设置一个捕虫网", "挪动溪流里的小石块", "接下来摩擦沙子",
                              "搬到溪边的岸上", "把网中的虫子倒到水桶中", "冲洗干净捕虫网", "继续收集水生生物", "把水生生物收集起来"};
        ImageView imageView;
        Integer integer;
        for(int i = 0; i < imgId.length; ++i){
            imageView = new ImageView(this);
//            imageView.setImageResource(imgId[i]);
//            Log.d("Collect", "id:" + imgId[i]);
//            imageView.setImageDrawable(getDrawable(imgId[i]))
            imageView.setBackgroundResource(imgId[i]);
            titleList.add(titleArys[i]);
//            integer = new Integer(imgId[i]);
            imageIdList.add(imageView);
        }
    }

    /**
     * 初始化View
     */
    private void intiView(){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new ViewPagerAdapter();
        adapter.setData(imageIdList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_title.setText((position+1)+"."+titleList.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(1+"."+titleList.get(0));
    }
}

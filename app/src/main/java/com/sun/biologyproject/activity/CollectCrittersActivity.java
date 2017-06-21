package com.sun.biologyproject.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aitangba.swipeback.SwipeBackActivity;
import com.sun.biologyproject.R;
import com.sun.biologyproject.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CollectCrittersActivity extends SwipeBackActivity {

    /**
     * View
     */
    private ViewPager viewPager;
    private TextView  tv_title;
    private Button    bt_go_to_collect;

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

        bt_go_to_collect = (Button) findViewById(R.id.bt_go_to_collect);
        bt_go_to_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CollectCrittersActivity.this, IdentifyCrittersActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
                if(position == titleList.size()-1){
                    bt_go_to_collect.setVisibility(View.VISIBLE);
                }else{
                    bt_go_to_collect.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(1+"."+titleList.get(0));
    }

}

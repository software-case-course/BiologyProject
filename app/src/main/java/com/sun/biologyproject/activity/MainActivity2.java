package com.sun.biologyproject.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.sun.biologyproject.R;
import com.sun.biologyproject.fragment.FindFragment;
import com.sun.biologyproject.fragment.MineFragment;
import com.sun.biologyproject.fragment.RecordFragment;

public class MainActivity2 extends AppCompatActivity {

    private BottomNavigationBar mBottomNavigationBar;
    private FindFragment mFindFragment;
    private RecordFragment mRecordFragment;
    private MineFragment mMineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mFindFragment = new FindFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mainFrame, mFindFragment);
        fragmentTransaction.commit();

        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottomNavigationBar);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.find_white, "收集").setActiveColorResource(R.color.brown))
                .addItem(new BottomNavigationItem(R.drawable.record_white, "记录").setActiveColorResource(R.color.teal))
                .addItem(new BottomNavigationItem(R.drawable.mine_white, "个人中心").setActiveColorResource(R.color.blue))
                .setFirstSelectedPosition(0)
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
//                Toast.makeText(MainActivity2.this, "pos：" + position, Toast.LENGTH_SHORT).show();
                changeTab(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    private void changeTab(int pos){
        hindAllFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        switch (pos){
            case 0:
                fragmentTransaction.show(mFindFragment);
                break;
            case 1:
                if (mRecordFragment == null){
                    mRecordFragment = new RecordFragment();
                    fragmentTransaction.add(R.id.mainFrame, mRecordFragment);
                }else {
                    fragmentTransaction.show(mRecordFragment);
                }
                break;
            case 2:
                if (mMineFragment == null){
                    mMineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.mainFrame, mMineFragment);
                }else {
                    fragmentTransaction.show(mMineFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    private void hindAllFragment(){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (mFindFragment != null){
            fragmentTransaction.hide(mFindFragment);
        }
        if (mRecordFragment != null){
            fragmentTransaction.hide(mRecordFragment);
        }
        if (mMineFragment != null){
            fragmentTransaction.hide(mMineFragment);
        }
        fragmentTransaction.commit();
    }

}

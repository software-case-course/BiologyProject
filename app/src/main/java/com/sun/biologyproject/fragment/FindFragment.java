package com.sun.biologyproject.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.sun.biologyproject.R;
import com.sun.biologyproject.activity.CollectCrittersActivity;
import com.sun.biologyproject.activity.IdentifyCrittersActivity;
import com.sun.biologyproject.activity.ShowMapActivity;
import com.sun.biologyproject.activity.ToolsActivity;
import com.sun.biologyproject.bean.Adress;
import com.sun.biologyproject.utils.SharedUtils;
import com.sun.biologyproject.utils.Utils;

/**
 * Created by SUN on 2017/6/21.
 */
public class FindFragment extends Fragment implements AMapLocationListener {

    /**
     * View
     */
    private LinearLayout mLinearTopBar;
    private TextView mTvLocation;
    private Button mBtnCourse;
    private Button mBtnCollect;

    //与定位有关的参数
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private String[] strMsg;
    private static String locationString=null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.find_fragment_layout, null);
        initView(contentView);
        return contentView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Location();
    }

    private void initView(View view){
        mLinearTopBar = (LinearLayout) view.findViewById(R.id.linearTopBar);
        mLinearTopBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ShowMapActivity.class);
                startActivity(intent);
            }
        });

        mTvLocation = (TextView) view.findViewById(R.id.tv_location);

        mBtnCourse = (Button) view.findViewById(R.id.btn_course);
        mBtnCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CollectCrittersActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
            }
        });

        mBtnCollect = (Button) view.findViewById(R.id.btn_collect);
        mBtnCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (TextUtils.isEmpty(SharedUtils.readMyTools(getActivity()))){
                    intent = new Intent(getActivity(), ToolsActivity.class);
                }else {
                    intent = new Intent(getActivity(), IdentifyCrittersActivity.class);
                }
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
            }
        });
    }

    @Override
    public void onLocationChanged(AMapLocation loc) {
        if (null != loc) {
            Message msg = mHandler.obtainMessage();
            msg.obj = loc;
            msg.what = Utils.MSG_LOCATION_FINISH;
            mHandler.sendMessage(msg);
        }

    }

    Handler mHandler = new Handler() {
        public void dispatchMessage(android.os.Message msg) {
            switch (msg.what) {
                //定位完成
                case Utils.MSG_LOCATION_FINISH:
                    String result = "";
                    try {
                        AMapLocation loc = (AMapLocation) msg.obj;
                        result = Utils.getLocationStr(loc, 1);
                        strMsg = result.split(",");
                        //Toast.makeText(MainActivity.this, "定位成功", Toast.LENGTH_LONG).show();
                        //textView.setText("地址：" + strMsg[0] + "\n" + "经    度：" + strMsg[1] + "\n" + "纬    度：" + strMsg[1]);
                        locationString = strMsg[0];
                        mTvLocation.setText(locationString);
                        Adress.getAdressInstance().setAdress(locationString);
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "定位失败", Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    break;
            }
        };

    };

    public void Location() {
        // TODO Auto-generated method stub
        try {
            locationClient = new AMapLocationClient(getActivity());
            locationOption = new AMapLocationClientOption();
            // 设置定位模式为低功耗模式
            locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
            // 设置定位监听
            locationClient.setLocationListener(this);
            locationOption.setOnceLocation(true);//设置为单次定位
            locationClient.setLocationOption(locationOption);// 设置定位参数
            // 启动定位
            locationClient.startLocation();
            mHandler.sendEmptyMessage(Utils.MSG_LOCATION_START);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "定位失败", Toast.LENGTH_LONG).show();
        }
    }

}

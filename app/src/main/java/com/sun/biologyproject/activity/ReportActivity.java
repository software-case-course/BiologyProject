package com.sun.biologyproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sun.biologyproject.R;

import   java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/17.
 */

public class ReportActivity extends Activity{
    private TextView time1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        time1 = new TextView(this);
        SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());
        String   str   =   formatter.format(curDate);
        Log.i("Info",str);
        this.time1 = (TextView) findViewById(R.id.timer);
        time1.setText(str);
        initView1();
    }
    private void initView1()
    {Button button2=(Button)findViewById(R.id.identify_more);
    button2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(ReportActivity.this,IdentifyCrittersActivity.class);
            startActivity(intent);
        }
    });}
}


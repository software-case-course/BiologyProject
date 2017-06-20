package com.sun.biologyproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.sun.biologyproject.R;

import   java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static android.R.attr.fastScrollOverlayPosition;
import static android.R.attr.parentActivityName;
import static android.R.attr.positiveButtonText;
import static android.R.attr.resource;
import static android.R.attr.toAlpha;

/**
 * Created by Administrator on 2017/5/17.
 */

public class ReportActivity extends Activity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener{
    private TextView time1;
    private ListView listView;
    private ArrayAdapter<String>arr_adapter;
    private SimpleAdapter simp_adapter;
    private ArrayList<String> arr_data;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        listView=(ListView) findViewById(R.id.listView);
        String [] tempArr = {"物种1", "物种2", "物种3", "物种4"};
        arr_data = new ArrayList<String>(Arrays.asList(tempArr));
        arr_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr_data);
        //simp_adapter=new SimpleAdapter(this,arr_data,resource,from,to)

        listView.setAdapter(arr_adapter);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);


        time1 = new TextView(this);
        SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());
        String   str   =   formatter.format(curDate);

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

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {

//        Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_LONG).show();
        arr_data.remove(position);
        arr_adapter.notifyDataSetChanged();
//         arr_adapter.remove(String.valueOf(position));
//        arr_adapter.notifyDataSetChanged();
    }

}


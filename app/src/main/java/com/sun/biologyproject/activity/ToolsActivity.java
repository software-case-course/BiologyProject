package com.sun.biologyproject.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sun.biologyproject.R;

public class ToolsActivity extends Activity  {

    private CheckBox net;
    private CheckBox bucket;
    private CheckBox cup;
    private CheckBox box;
    private CheckBox gloves ;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

        submit = (Button)findViewById(R.id.submit);

        //取得每一个CheckBox对象
        net = (CheckBox)findViewById(R.id.net);
        bucket = (CheckBox)findViewById(R.id.bucket);
        cup = (CheckBox)findViewById(R.id.cup);
        box = (CheckBox)findViewById(R.id.box);
        gloves = (CheckBox)findViewById(R.id.gloves);

        //设置监听
        net.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView , boolean isChecked){
                if(net.isChecked()){
                    DisplayToast("你选择了网");
                }
            }
        });

        bucket.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView , boolean isChecked){
                if(bucket.isChecked()){
                    DisplayToast("你选择了水桶");
                }
            }
        });

        cup.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView , boolean isChecked){
                if(cup.isChecked()){
                    DisplayToast("你选择了杯子");
                }
            }
        });

        box.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView , boolean isChecked){
                if(box.isChecked()){
                    DisplayToast("你选择了箱子");
                }
            }
        });

        gloves.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView , boolean isChecked){
                if(gloves.isChecked()){
                    DisplayToast("你选择了手套");
                }
            }
        });

        submit.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v){
                int num = 0;
                if(net.isChecked())
                {
                    num++;
                }
                if(bucket.isChecked())
                {
                    num++;
                }
                if(cup.isChecked())
                {
                    num++;
                }
                if(box.isChecked())
                {
                    num++;
                }
                if(gloves.isChecked())
                {
                    num++;
                }
                DisplayToast("您一共选择了"+num+"样工具");
            }
        });

    }
    public void DisplayToast(String str){
        Toast toast = Toast.makeText(this,str,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,220);
        toast.show();
    }


}

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
import com.sun.biologyproject.utils.ShareUtils;

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
                int sign1 = 0, sign2 = 0,sign3 = 0, sign4 = 0, sign5 = 0;
                if(net.isChecked())
                {
                    num++;
                    sign1 = 1;
                }
                if(bucket.isChecked())
                {
                    num++;
                    sign2 = 1;
                }
                if(cup.isChecked())
                {
                    num++;
                    sign3 = 1;
                }
                if(box.isChecked())
                {
                    num++;
                    sign4 = 1;
                }
                if(gloves.isChecked())
                {
                    num++;
                    sign5 = 1;
                }
                DisplayToast("您一共选择了"+num+"样工具");
                StringBuffer stringBuffer = new StringBuffer();
                if( sign1 == 1){
                    stringBuffer.append("网,");
                }
                if( sign2 == 1){
                    stringBuffer.append("水桶,");
                }
                if( sign3 == 1){
                    stringBuffer.append("杯子,");
                }
                if( sign4 == 1){
                    stringBuffer.append("箱子,");
                }
                if( sign5 == 1){
                    stringBuffer.append("手套");
                }
                ShareUtils.writeMyTools(ToolsActivity.this, stringBuffer.toString());
            }
        });

    }
    public void DisplayToast(String str){
        Toast toast = Toast.makeText(this,str,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,220);
        toast.show();
    }


}

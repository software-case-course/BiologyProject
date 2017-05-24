package com.sun.biologyproject.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.sun.biologyproject.R;

public class ToolsActivity extends Activity implements CompoundButton.OnCheckedChangeListener {

    private CheckBox net , bucket , cup , box ,gloves ;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

        tv = (TextView)this.findViewById(R.id.tv);
        net= (CheckBox) this.findViewById(R.id.net);
        bucket= (CheckBox) this.findViewById(R.id.bucket);
        cup= (CheckBox) this.findViewById(R.id.cup);
        box= (CheckBox) this.findViewById(R.id.box);
        gloves= (CheckBox) this.findViewById(R.id.gloves);
        net.setOnCheckedChangeListener(this);
        bucket.setOnCheckedChangeListener(this);
        cup.setOnCheckedChangeListener(this);
        box.setOnCheckedChangeListener(this);
        gloves.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView , boolean isChecked){
        String str = "您选择的工具是：";
        if(net.isChecked()){
            str+=net.getText()+",";
        }
        if(bucket.isChecked()){
            str+=bucket.getText()+",";
        }
        if(cup.isChecked()){
            str+=cup.getText()+",";
        }
        if(box.isChecked()){
            str+=box.getText()+",";
        }
        if(gloves.isChecked()){
            str+=gloves.getText()+",";
        }
        tv.setText(str);
    }
}

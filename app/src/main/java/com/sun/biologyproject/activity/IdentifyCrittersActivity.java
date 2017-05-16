package com.sun.biologyproject.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sun.biologyproject.R;

/**
 * 识别水生生物的activity
 * Created by everlov3 on 2017/4/19.
 */

public class IdentifyCrittersActivity extends Activity{
    private TextView title_text;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indentify_critters);

        title_text=(TextView)findViewById(R.id.titleText);
        title_text.setText("识别水生生物");
    }

    /**
     * 对设置好的控件进行响应
     * @param v
     */
    public void onClick(View v)
    {
        int id=1;
        switch (v.getId()){
            //有壳类
            case R.id.shell:
            case R.id.list1:
                id=1;
                break;
            //蠕虫类
            case R.id.worm:
            case R.id.list2:
                id=2;
                break;
            //六条腿
            case R.id.sixlegs1:
            case R.id.list3:

            //超过六条腿
            case R.id.morelegs1:
            case R.id.list4:
                id=4;
                break;
            default:
                Toast.makeText(this,"system error",Toast.LENGTH_SHORT).show();
                break;
        }

        Intent intent=new Intent(IdentifyCrittersActivity.this,GridViewActivity.class);
        intent.putExtra("id",String.valueOf(id));
        startActivity(intent);
    }

}

package com.sun.biologyproject;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;
import android.widget.Toast;

/**
 * Created by everlov3 on 2017/4/19.
 */

public class actvitysecond extends Activity{
    private ImageView imageView1;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvitysecond);

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

        Intent intent=new Intent(actvitysecond.this,GridViewActivity.class);
        intent.putExtra("id",String.valueOf(id));
        startActivity(intent);
    }

}

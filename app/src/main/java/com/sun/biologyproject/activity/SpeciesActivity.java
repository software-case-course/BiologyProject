package com.sun.biologyproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.sun.biologyproject.R;
import com.sun.biologyproject.adapter.GridAdapter;
import com.sun.biologyproject.adapter.ShowAdapter;
import com.sun.biologyproject.bean.GridViewDB;

public class SpeciesActivity extends AppCompatActivity {

    private GridView show_one;
    //private int num;
    private ImageView imageView;
    private int firstId,secondId;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species);
       // setContentView(R.layout.test_layout);
        show_one=(GridView)findViewById(R.id.show_grid);

        //获取GridViewActivity 中的数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String FirstId = bundle.getString("firstId");
        String SecondId=bundle.getString("secondId");
        firstId=Integer.parseInt(FirstId);
        secondId=Integer.parseInt(SecondId);

        GridViewDB gridViewDB=GridViewDB.getInstance();//获取DB对象
        //imageView.setImageResource(gridViewDB.getPictureId(firstId,secondId));//gridViewDB.getPictureId(firstId,secondId)获取图片的id

        //num = Integer.parseInt(str);
//        show_one = (GridView)findViewById(R.id.show_grid);
        show_one.setNumColumns(1);
        show_one.setAdapter(new ShowAdapter(this,firstId,secondId));

//        initView();

    }
    private void initView()
    {
        Button button1=(Button)findViewById(R.id.toreport);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SpeciesActivity.this,ReportActivity.class);
                startActivity(intent);
            }
        });
    }
}

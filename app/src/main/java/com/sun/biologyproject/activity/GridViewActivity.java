package com.sun.biologyproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.sun.biologyproject.R;
import com.sun.biologyproject.adapter.GridAdapter;

/**
 * 梁雨宜 2017/4/19
 * 启动显示GridView的Activity
 */

public class GridViewActivity extends AppCompatActivity {

    private GridView choose_one;
    private int id;
    private String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        str=bundle.getString("id");
        Log.d("Tag",str);
        id=Integer.parseInt(str);
        choose_one=(GridView)findViewById(R.id.choose_grid);
        choose_one.setNumColumns(2);
        choose_one.setAdapter(new GridAdapter(this,id));
        choose_one.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.d("Tag","position"+String.valueOf(position)+" id:"+String.valueOf(id));
                //Toast.makeText(GridViewActivity.this,"position"+String.valueOf(position)+" id:"+String.valueOf(id),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(GridViewActivity.this,SpeciesActivity.class);
                intent.putExtra("firstId",str);
                intent.putExtra("secondId",String.valueOf(position));
                startActivity(intent);
                finish();
            }
        });
    }
}

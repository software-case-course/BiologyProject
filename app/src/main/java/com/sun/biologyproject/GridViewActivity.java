package com.sun.biologyproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class GridViewActivity extends AppCompatActivity {

    private GridView choose_one;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        choose_one=(GridView)findViewById(R.id.choose_grid);
        choose_one.setNumColumns(2);
        choose_one.setAdapter(new GridAdapter(this));
        //startActivity(new Intent(GridViewActivity.this,SpeciesActivity.class));
        choose_one.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.d("Tag","position"+String.valueOf(position)+" id:"+String.valueOf(id));
                startActivity(new Intent(GridViewActivity.this,SpeciesActivity.class));
            }
        });
    }
}

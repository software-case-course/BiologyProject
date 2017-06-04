package com.sun.biologyproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.sun.biologyproject.R;
import com.sun.biologyproject.adapter.GridAdapter;

public class SpeciesActivity extends AppCompatActivity {

    private GridView show_one;
    private int num;
    @Override
protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String str = bundle.getString("position");
        num = Integer.parseInt(str);
        show_one = (GridView)findViewById(R.id.show_grid);
        show_one.setNumColumns(1);
        show_one.setAdapter(new GridAdapter(this,num));

        initView();

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

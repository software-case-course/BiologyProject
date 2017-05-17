package com.sun.biologyproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sun.biologyproject.R;

public class SpeciesActivity extends AppCompatActivity {
    @Override
protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species);
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

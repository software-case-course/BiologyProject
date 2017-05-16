package com.sun.biologyproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sun.biologyproject.R;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {

    private Button collect;
    private Button identify;
    private TextView title_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "f99ed8e9be1e3e153a970b88d11a1fa6");
        setContentView(R.layout.activity_main);

        title_text=(TextView)findViewById(R.id.titleText);
        title_text.setText("水生生物识别");

        collect=(Button)findViewById(R.id.collect_button);
        identify=(Button)findViewById(R.id.identify_button);

        identify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,IdentifyCrittersActivity.class);
                startActivity(intent);
            }
        });
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CollectCrittersActivity.class);
                startActivity(intent);
            }
        });
    }
}

package com.sun.biologyproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button collect;
    private Button identify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        collect=(Button)findViewById(R.id.collect_button);
        identify=(Button)findViewById(R.id.identify_button);

        /*identify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,GridViewActivity.class);
                startActivity(intent);
            }
        });*/
        identify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,actvitysecond.class);
                startActivity(intent);
            }
        });
    }
}

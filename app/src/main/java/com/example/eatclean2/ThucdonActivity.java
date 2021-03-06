package com.example.eatclean2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eatclean2.DBMonan.queryData;

public class ThucdonActivity extends AppCompatActivity {

    TextView tv1,tv2,tv3;
    static int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thucdon);
        tv1=findViewById(R.id.tv_buoisang);
        tv2=findViewById(R.id.tv_buoitrua);
        tv3=findViewById(R.id.tv_buoitoi);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
      queryData queryData=new queryData(ThucdonActivity.this,1);
      queryData.getAlldataBUA();
//        queryData.xoabang();

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=1;
                Intent intent=new Intent(ThucdonActivity.this,ChitietThudonActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=2;
                Intent intent=new Intent(ThucdonActivity.this,ChitietThudonActivity.class);
                intent.putExtra("id",id); startActivity(intent);
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=3;
                Intent intent=new Intent(ThucdonActivity.this,ChitietThudonActivity.class);
                intent.putExtra("id",id); startActivity(intent);
            }
        });

    }
}
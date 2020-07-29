package com.example.eatclean2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.eatclean2.DBMonan.queryData;
import com.example.eatclean2.Model.Monan;

public class viewThucdonActivity extends AppCompatActivity {
    EditText ed_tenmon,ed_mota;
    ImageView imageView;
    Button but_1,but_2,but_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_thucdon);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        Intent intent=getIntent();

        final Monan monan= (Monan) intent.getSerializableExtra("monan");

        ed_tenmon=findViewById(R.id.ed_tenMon);
        ed_mota=findViewById(R.id.ed_mota);
        imageView=findViewById(R.id.image);
        but_1=findViewById(R.id.but_1);
        but_2=findViewById(R.id.but_2);
        but_3=findViewById(R.id.but_3);


        //Toast.makeText(this,monan.getImgae(),Toast.LENGTH_LONG).show();
        Bitmap bitmap=BitmapFactory.decodeFile(monan.getImgae());
       imageView.setImageBitmap(bitmap);
        ed_tenmon.setText(monan.getTen());
        ed_mota.setText(monan.getMota());

        but_1.setOnClickListener(new View.OnClickListener() {// them vao bang buoi sang
            @Override
            public void onClick(View view) {
                queryData queryData=new queryData(viewThucdonActivity.this,1);
                if (!queryData.insertinto_bua(monan.getId(),"buoisang")){
                    Toast.makeText(viewThucdonActivity.this," Không thể thêm vào bữa sáng",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(viewThucdonActivity.this," Thêm thành công",Toast.LENGTH_LONG).show();
                }

            }
        });
        but_2.setOnClickListener(new View.OnClickListener() {// them vao bang buoi sang
            @Override
            public void onClick(View view) {
                queryData queryData=new queryData(viewThucdonActivity.this,2);
                if (!queryData.insertinto_bua(monan.getId(),"buoitrua")){
                    Toast.makeText(viewThucdonActivity.this," Không thể thêm vào bữa trưa",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(viewThucdonActivity.this," Thêm thành công",Toast.LENGTH_LONG).show();
                }

            }
        });
        but_3.setOnClickListener(new View.OnClickListener() {// them vao bang buoi sang
            @Override
            public void onClick(View view) {
                queryData queryData=new queryData(viewThucdonActivity.this,3);
                if (!queryData.insertinto_bua(monan.getId(),"buoichieu")){
                    Toast.makeText(viewThucdonActivity.this," Không thể thêm vào bữa tối",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(viewThucdonActivity.this," Thêm thành công",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
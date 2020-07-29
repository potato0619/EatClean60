package com.example.eatclean2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eatclean2.DBMonan.queryData;
import com.example.eatclean2.Model.Monan;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

//    Spinner spinner;
    EditText ed_tenmon,ed_mota;
    ImageView imageView;
    Button but_add;
    static String path="";

    static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ed_tenmon=findViewById(R.id.ed_tenMon);
        ed_mota=findViewById(R.id.ed_mota);
        imageView=findViewById(R.id.image);
        but_add=findViewById(R.id.but);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,11);

            }
        });
        but_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ed_tenmon.getText().toString().isEmpty()){
                  thongbao(" tên món ");
                }else  if(ed_mota.getText().toString().isEmpty()){
                  thongbao(" mô tả ");
                }  else{
                    String tenmon=ed_tenmon.getText().toString();
                    String mota=ed_mota.getText().toString();

                    Log.d("iddd", id+" ");

                    queryData queryData=new queryData(AddActivity.this,id);
                    Monan monan=new Monan(SeachActivity.monans.size(),tenmon,mota,path,0);
                    queryData.insertinto(monan);

                    SeachActivity.monans.add(monan);
                    SeachActivity.adapter2.notifyItemChanged(SeachActivity.monans.size());

                    Toast.makeText(AddActivity.this," Thêm món ăn thành công",Toast.LENGTH_LONG).show();
                    finish();


                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode==11&&resultCode==RESULT_OK){
            Uri uri=data.getData();

            path=getRealPathFromURI(uri);
            Log.d("aa", path);

            try {
                InputStream fileInputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(fileInputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }
    public void thongbao(String nd){
        AlertDialog.Builder builder=new AlertDialog.Builder(AddActivity.this);
        builder.setTitle("thông báo");
        builder.setMessage("dữ liệu ở ô "+nd+"còn thiếu!!");
        builder.setNegativeButton("xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();


    }


}
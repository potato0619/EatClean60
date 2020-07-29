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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.eatclean2.DBMonan.queryData;
import com.example.eatclean2.Model.Monan;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Sua_thucdonActivity extends AppCompatActivity {
    EditText ed_tenmon,ed_mota;
    ImageView imageView;
    Button but_1,but_3;
    static String path="/storage/emulated/0/DCIM/Camera/rap2.jpg";
    static Monan monan;
    static  int vitri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_thucdon);
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

       monan = (Monan) intent.getSerializableExtra("monan");
       vitri=intent.getIntExtra("vitri",0);

        ed_tenmon=findViewById(R.id.ed_tenMon);
        ed_mota=findViewById(R.id.ed_mota);
        imageView=findViewById(R.id.image);
        but_1=findViewById(R.id.but_1);
        but_3=findViewById(R.id.but_3);

        //Toast.makeText(this,monan.getImgae(),Toast.LENGTH_LONG).show();
        Bitmap bitmap= BitmapFactory.decodeFile(monan.getImgae());
        imageView.setImageBitmap(bitmap);
        ed_tenmon.setText(monan.getTen());
        ed_mota.setText(monan.getMota());
        path=monan.getImage();

        but_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


        queryData queryData=new queryData(Sua_thucdonActivity.this,4);
                queryData.xoa_thucdon(monan.getId());
                // Toast.makeText(Sua_thucdonActivity.this,"thành công",Toast.LENGTH_LONG).show();
                SeachActivity.monans.remove(vitri);
                SeachActivity.adapter2.notifyItemRemoved(vitri);
                SeachActivity.adapter2.notifyDataSetChanged();
                Toast.makeText(Sua_thucdonActivity.this,"thành công",Toast.LENGTH_LONG).show();
                finish();

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,11);

            }
        });
        but_1.setOnClickListener(new View.OnClickListener() {// sua thuc don
            @Override
            public void onClick(View view) {

                if (ed_tenmon.getText().toString().isEmpty()){
                    AlertDialog.Builder builder=new AlertDialog.Builder(Sua_thucdonActivity.this);
                    builder.setTitle("thông báo");
                    builder.setMessage("Dữ liệu liệu tên món để trống !");
                    builder.setPositiveButton("xác nhận", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();
                }else{
                    String tenmon=ed_tenmon.getText().toString();
                    String mota=ed_mota.getText().toString();
                   // Log.d("iddd", 1+" ");
                    queryData queryData=new queryData(Sua_thucdonActivity.this,4);

                    Monan monan1=new Monan(monan.getId(),tenmon,mota,path,0);
                    queryData.update(monan1);

                    SeachActivity.monans.set(vitri,monan1);
                    SeachActivity.adapter2.notifyItemChanged(vitri);
                    Toast.makeText(Sua_thucdonActivity.this,"thành công",Toast.LENGTH_LONG).show();
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
//

}
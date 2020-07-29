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

public class SuaActivity extends AppCompatActivity {

    Spinner spinner;
    EditText ed_tenmon,ed_mota;
    ImageView imageView;
    Button but_add,but_xoa;
    static String path="/storage/emulated/0/DCIM/Camera/rap2.jpg";

   static int id;

   static int vitri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua);
        Intent intent=getIntent();
        final Monan monan= (Monan) intent.getSerializableExtra("monan");
        id=intent.getIntExtra("i",0);
        vitri=intent.getIntExtra("vitri",0);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        spinner=findViewById(R.id.spinner);
        final ArrayList<String> strings=new ArrayList<>();

        if(id==1){
            strings.add("buổi sáng");
        }else if(id==2){
            strings.add("buổi trưa");
        }else if(id==3){
            strings.add("buổi tối");
        }


// custom spinner
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,strings);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);


        ed_tenmon=findViewById(R.id.ed_tenMon);
        ed_mota=findViewById(R.id.ed_mota);
        imageView=findViewById(R.id.image);
      //  but_add=findViewById(R.id.but1);
        but_xoa=findViewById(R.id.but);

        ed_mota.setText(monan.getMota());
        ed_tenmon.setText(monan.getTen());

        Bitmap bitmap=BitmapFactory.decodeFile(monan.getImgae());
        imageView.setImageBitmap(bitmap);

        but_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(SuaActivity.this);
                builder.setTitle("thông báo");
                builder.setMessage("Bạn có muốn xóa mục này");
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        queryData queryData=new queryData(SuaActivity.this,id);

                        queryData.xoa(monan.getId());

                        ChitietThudonActivity.monans.remove(vitri);
                        ChitietThudonActivity.adapter.notifyItemRemoved(vitri);
                        ChitietThudonActivity.adapter.notifyDataSetChanged();
                        Toast.makeText(SuaActivity.this,"Xóa thành công",Toast.LENGTH_LONG).show();
                        finish();

                    }
                });
                builder.show();
                //finish();
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
}
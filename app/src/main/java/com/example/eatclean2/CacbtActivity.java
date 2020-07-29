package com.example.eatclean2;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CacbtActivity extends AppCompatActivity {


    Toolbar toolbar;
    RecyclerView recyclerView;
   public static VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cacbt);
        recyclerView=findViewById(R.id.recycleview);
        toolbar=findViewById(R.id.toolbar);
        videoView=findViewById(R.id.video);// ánh xạ cho biến video view

        ArrayList<vide> vides=new ArrayList<>();// kha baao mảng
        vides.add(new vide(R.drawable.anh1,"video giảm cân 1",R.raw.video4));
        vides.add(new vide(R.drawable.video3,"video giảm cân 2",R.raw.video2));
        vides.add(new vide(R.drawable.video4,"video giảm cân 3",R.raw.video3));
        vides.add(new vide(R.drawable.anh1,"video giảm cân 4",R.raw.video));

        videoadapter thucdonadapter=new videoadapter(CacbtActivity.this,vides);//gọi videodapter để hiện thị ra recylerview
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(CacbtActivity.this);// dịnh dạng kiêu cho reclerview là linearLayout
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);// kiểu dọc cho linearlayout
        recyclerView.setLayoutManager(linearLayoutManager);// add vao reclervie
        recyclerView.setAdapter(thucdonadapter);// add adaper

        setSupportActionBar(toolbar);// hiện thị toorbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// hiện thi nút quay về
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {// sự kiên nhấn nút quay lại
            @Override
            public void onClick(View view) {
                finish();// kết thúc
            }
        });
        toolbar.setTitle("video");// đặt tiêu dề là video

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video3));// đặt linjk video là android.resource:// ....
        videoView.requestFocus();// đật con trỏ vào videoview để bắt đầu vào là phát luôn video ở biết videoview.
        videoView.start();// videeo bắt đầu phat
    }
}
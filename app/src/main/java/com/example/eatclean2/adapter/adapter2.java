package com.example.eatclean2.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatclean2.AddActivity;
import com.example.eatclean2.ChitietThudonActivity;
import com.example.eatclean2.Model.Monan;
import com.example.eatclean2.R;
import com.example.eatclean2.SuaActivity;
import com.example.eatclean2.Sua_thucdonActivity;
import com.example.eatclean2.viewThucdonActivity;

import java.util.ArrayList;

public class adapter2 extends RecyclerView.Adapter<adapter2.ViewHolder> {

    ArrayList<Monan> arrayList=new ArrayList<>();
    Context context;

    public adapter2(ArrayList<Monan> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.dong_1monan,parent,false);

        return new adapter2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Monan monan=arrayList.get(position);
        Bitmap bitmap= BitmapFactory.decodeFile(monan.getImgae());
        Log.d("image"," dia chi:"+ monan.getImgae());
        holder.image_sp.setImageBitmap(bitmap);

//        Bitmap bitmapImage = BitmapFactory.decodeFile(monan.getImgae());
//        int nh = (int) ( bitmapImage.getHeight() * (512.0 / bitmapImage.getWidth()) );
//        Bitmap bitmap = Bitmap.createScaledBitmap(bitmapImage, 512, nh, true);

        holder.image_sp.setImageBitmap(bitmap);
        holder.tendiadiem.setText(monan.getTen());
        holder.diachi.setText(monan.getMota());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_sp;
        TextView tendiadiem;
        TextView diachi;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            image_sp = itemView.findViewById(R.id.image);
            tendiadiem = itemView.findViewById(R.id.tv_ten);
            diachi = itemView.findViewById(R.id.diadiem);
            itemView.setOnClickListener(new View.OnClickListener() {// nut view
                @Override
                public void onClick(View view) {

                    Intent intent=new Intent(context, viewThucdonActivity.class);
                    intent.putExtra("monan",arrayList.get(getPosition()));

                    context.startActivity(intent);
                    //ontext.startActivity(new Intent(context, viewThucdonActivity.class));

                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Intent intent=new Intent(context, Sua_thucdonActivity.class);
                    intent.putExtra("monan",arrayList.get(getPosition()));
                    intent.putExtra("vitri",getPosition());

                    context.startActivity(intent);
                    return true;
                }
            });
        }

    }
}
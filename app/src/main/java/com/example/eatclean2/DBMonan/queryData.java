package com.example.eatclean2.DBMonan;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;


import com.example.eatclean2.Model.Monan;

import java.util.ArrayList;

public class queryData {
    DBMonan dataMonan;
    String tenbua="thucdon";
    public queryData(Context context, int id) {
        dataMonan=new DBMonan(context,"thucdon.sqlite",null,1);
        // tao bang
        dataMonan.QueryData("CREATE TABLE IF NOT EXISTS thucdon(id INTEGER PRIMARY KEY AUTOINCREMENT, tenmonan VARCHAR(100),mota  VARCHAR(250), image  VARCHAR(200),chot INTEGER)");
        dataMonan.QueryData("CREATE TABLE IF NOT EXISTS bua(id INTEGER PRIMARY KEY AUTOINCREMENT,IDthucdon INTEGER, tenbua varchar(24))");

        switch (id){
            case 1:
                tenbua="buoisang";
                break;
            case 2:
                tenbua="buoitrua";
                break;
            case 3:
                tenbua="buoichieu";
                break;

        }

    }
    public void getAlldataBUA() {// lay ds thưc don
        ArrayList<Monan> arrayList=new ArrayList<>();

        Cursor data=dataMonan.getData("Select * from bua");
        arrayList.clear();
        while (data.moveToNext()) {

            int id1=data.getInt(0);
            int id2=data.getInt(1);
            String a=data.getString(2);
            Log.d("data", "bua : "+id1+"/"+id2+"/"+a);

        }
    }
    public ArrayList<Monan> getAlldata(){// lay ds mon an trong buoi sang
        ArrayList<Monan> arrayList=new ArrayList<>();


        Cursor data=dataMonan.getData("Select bua.id,thucdon.tenmonan,thucdon.mota, thucdon.image from  thucdon inner join bua on thucdon.id=bua.IDthucdon and bua.tenbua='"+tenbua+"'");
        Log.d("data", "bua awn "+tenbua);
        arrayList.clear();
        while (data.moveToNext()) {
            int id1=data.getInt(0);
            String ten=data.getString(1);
            String mota=data.getString(2);
            String image=data.getString(3);
            int chot=0;
            Monan Monan=new Monan(id1,ten,mota,image,chot);
            arrayList.add(Monan);

        }
        return arrayList;
    }
    public ArrayList<Monan> getAlldatathucdon() {// lay ds thưc don
        ArrayList<Monan> arrayList=new ArrayList<>();

        Cursor data=dataMonan.getData("Select * from thucdon");
        arrayList.clear();
        while (data.moveToNext()) {

            int id1=data.getInt(0);
            String ten=data.getString(1);
            String mota=data.getString(2);
            String image=data.getString(3);
            int chot=data.getInt(4);
            Monan Monan=new Monan(id1,ten,mota,image,chot);
            arrayList.add(Monan);
        }
        return arrayList;
    }

    public boolean insertinto_bua(int id,String tenbua){// them vao  3bang

            try {
                Cursor cursor=dataMonan.getData("select * from bua where IDthucdon="+id +" and tenbua ='"+tenbua+"'");
                if(cursor.moveToNext()){
                    return false;
                }
                dataMonan.QueryData("insert into bua values ("+null+","+id+",'"+tenbua+"')");
                Log.d("sql", "insert into bua values ("+null+","+id+",'"+tenbua+"')");
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
    }
//////    public void getid()
    public void insertinto(Monan monan){// them vao  thuc don

        dataMonan.QueryData("insert into thucdon values ("+null+",'"+monan.getTen()+"','"+monan.getMota()+"','"+monan.getImgae()+"',"+monan.getChot()+")");
        Log.d("sql", "insert into thucdon values ("+null+",'"+monan.getTen()+"','"+monan.getMota()+"','"+monan.getImgae()+"',"+monan.getChot()+")");

    }
    public void xoa(int id){// xoa trong 3 bagn

        dataMonan.QueryData("delete from bua where id='"+id+"'");
        Log.d("sql", "delete from bua where id="+id);
    }
    public void xoa_thucdon(int id){// xoa trong 3 bagn

        dataMonan.QueryData("delete from thucdon where id='"+id+"'");
        Log.d("sql", "delete from thucdon where id="+id);
    }
    public void update(Monan monan){
        Log.d("sql", "update thucdon set (tenmonan="+"'"+monan.getTen()+"',"+"mota='"+monan.getMota()+"image='"+monan.getImgae()+"') where id ='"+monan.getId()+"'");
        dataMonan.QueryData("update thucdon set tenmonan="+"'"+monan.getTen()+"',"+"mota='"+monan.getMota()+"',image='"+monan.getImgae()+"' where id ='"+monan.getId()+"'");

    }
    public void xoabang(){
        dataMonan.QueryData("DROP TABLE bua");
    }



}

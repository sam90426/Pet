package com.sam.pet.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/8/19.
 * 用户关键数据存储本地sqlite数据库
 * 该类为自定义的sqlite类
 */
public class PetSQLData {
    private static SQLiteDatabase db;
    private static DBHelper dbHelper;


    //region 初始化数据库
    private static void InitData(Context context) {

        //创建StuDBHelper对象
         dbHelper = new DBHelper(context,"pet.db",null,1);

    }
    //endregion

    //region 向数据库插入记录
    /* 具体使用方法如下:
    * ContentValues cValue = new ContentValues();
    * cValue.put("sname","xiaoming");
    * cValue.put("snumber","01005");
    * */
    public static void InsertData(Context context,ContentValues cValue) {
        InitData(context);
        //获得到可写的数据库
        db=dbHelper.getWritableDatabase();
        db.insert("Pet_User", null, cValue);
    }
    //endregion

    //region 从数据库获取登录名
    /*
    * cursorcount:0=UserID,1=登录名,2=密码
    * */
    public static String GetValue(Context context,int cursorcount) {
        InitData(context);
        //得到一个可读的SQLiteDatabase对象
        db =dbHelper.getReadableDatabase();
        String value="";
        Cursor cursor = db.query ("Pet_User",null,null,null,null,null,null);
        //判断游标是否为空
        if(cursor.moveToFirst()){
            //遍历游标
            cursor.move(cursorcount);
            value = cursor.getString(2);
        }
        return value;
    }
    //endregion
}

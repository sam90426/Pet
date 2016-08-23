package com.sam.pet.Util;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2016/8/19.
 * 用户关键数据存储本地sqlite数据库
 * 该类为自定义的sqlite类
 */
public class PetSQLData {
    private SQLiteDatabase db;

    //region 初始化数据库
    private void InitData() {
        db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.sam.pet/databases/pet.db", null);
        //创建表SQL语句(不存在则插入)
        String pet_table = "create table if not exists usertable(ID integer primary key autoincrement,PName text,PValue text)";
        //执行SQL语句
        db.execSQL(pet_table);
    }
    //endregion

    //region 向数据库插入记录
    /* 具体使用方法如下:
    * ContentValues cValue = new ContentValues();
    * cValue.put("sname","xiaoming");
    * cValue.put("snumber","01005");
    * */
    public void InsertData(ContentValues cValue) {
        db.insert("stu_table", null, cValue);
    }
    //endregion

    //region 从数据库获取登录名
    /*
    * cursorcount:0=登录名,1=密码
    * */
    public String GetLoginName(int cursorcount) {
        String value="";
        Cursor cursor = db.query ("usertable",null,null,null,null,null,null);
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

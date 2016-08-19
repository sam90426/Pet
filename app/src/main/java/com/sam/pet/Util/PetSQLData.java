package com.sam.pet.Util;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2016/8/19.
 * 用户关键数据存储本地sqlite数据库
 * 该类为自定义的sqlite类
 */
public class PetSQLData {
    private SQLiteDatabase db;

    //region 初始化数据库
    private void InitData(){


    }
    //endregion

    //region 向数据库插入记录
    public boolean  InsertData(){

        return true;
    }
    //endregion

    //region 从数据库获取值
    public String  GetValue(){
        String returnstr="";

        return returnstr;
    }
    //endregion
}

package com.sam.pet.Util;

import android.app.Application;

/**
 * Created by Administrator on 2016/8/23.
 */
public class UserData extends Application {
    private static int UserID=0;

    public static int getUserID(){
        return UserID;
    }
    public static void setUserID(int newID){
        UserID= newID;
    }
}

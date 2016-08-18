package com.sam.pet.Util;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/8/9.
 */
public class ReturnParse {
    public static String Parse(Context context,String json){
        String result="";//返回内容
        String msg="";//返回消息
        int state=0;//返回值状态 0=成功
        try {
            JSONObject obj=new JSONObject(json);
            state=obj.getInt("status");
            msg=obj.getString("message");
            result=obj.getString("result");
        }catch (Exception e){
            e.printStackTrace();
        }
        //验证返回结果
        switch (state){//判断状态码
            case 0://未登录

                Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
                break;
            case 1:
                Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        //返回值
        return result;
    }
}

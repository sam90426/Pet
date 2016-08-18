package com.sam.pet.Util;

import android.app.Application;

public class MyInterFaceUrl extends Application{
	//md5加密秘钥
	public static final String md5key="tugou2015rainsky";

	//接口域名
	public static final String interfaceurl="http://192.168.1.24:8085";

	public static final String logininterface=interfaceurl+"/account/loginon";

	public static final String Registerinterface=interfaceurl+"/account/register";

}

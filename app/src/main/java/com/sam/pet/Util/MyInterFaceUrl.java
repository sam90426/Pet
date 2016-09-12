package com.sam.pet.Util;

import android.app.Application;

public class MyInterFaceUrl extends Application{
	//md5加密秘钥
	public static final String md5key="wxxsampet";

	//接口域名
	public static final String interfaceurl="http://localhost:58380";

	public static final String logininterface=interfaceurl+"/account/loginon";

	public static final String Registerinterface=interfaceurl+"/account/register";

}

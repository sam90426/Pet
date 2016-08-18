package com.sam.pet.Util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;
/*
* getMD5() 传入参数后的params 但不包含sign 传入参数后该方法会自动加密并添加sign参数
*
* */
public class TGmd5 {
	public static Map<String, String> getMD5(Map<String, String> params) {
		String md5str="";
		Set<Map.Entry<String, String>> entrySet = params.entrySet();
		int i = 0;
		for (Map.Entry<String, String> entry : entrySet) {
			String value = entry.getValue();
			md5str+=value;
			i++;
		}
		md5str = md5str + MyInterFaceUrl.md5key;
		try {
			MessageDigest m = MessageDigest.getInstance("md5");
			m.update(md5str.getBytes("UTF8"));
			byte s[] = m.digest();
			params.put("sign",hex(s));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return params;
	}

	private static String hex(byte[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; ++i) {
			sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();
	}

}

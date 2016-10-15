package com.sam.pet.AccountView;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sam.pet.R;
import com.sam.pet.Util.HttpManager;
import com.sam.pet.Util.MyInterFaceUrl;
import com.sam.pet.Util.ReturnParse;
import com.sam.pet.Util.TGmd5;

import java.util.HashMap;
import java.util.Map;

public class Login extends Activity {

    private EditText loginname, password;
    private Button loginbtn, registerbtn, findbtn;
    String LoginName = "";
    String PassWord = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        //region 初始化
        loginname = (EditText) findViewById(R.id.loginname);
        password = (EditText) findViewById(R.id.password);

        loginbtn = (Button) findViewById(R.id.loginbtn);
        registerbtn = (Button) findViewById(R.id.registerbtn);
        findbtn = (Button) findViewById(R.id.findbtn);
        //endregion

        //region 登录
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginName = loginname.getText().toString();
                PassWord = password.getText().toString();
                if (LoginName.isEmpty()) {
                    Toast.makeText(Login.this, "请输入手机号", Toast.LENGTH_LONG).show();
                } else {
                    if (PassWord.isEmpty()) {
                        Toast.makeText(Login.this, "请输入密码", Toast.LENGTH_LONG).show();
                    } else if (PassWord.length() < 6 || PassWord.length() > 12) {
                        Toast.makeText(Login.this, "密码为6-12位数字或字母", Toast.LENGTH_LONG).show();
                    } else {

                        //region 参数拼接
                        Map<String, String> params = new HashMap<>();
                        params.put("username", LoginName);
                        params.put("password", PassWord);
                        //endregion

                        //region 数据请求
                        HttpManager httpManager = new HttpManager(Login.this, MyInterFaceUrl.logininterface, TGmd5.getMD5(params), new HttpManager.OnRequestResonseListener() {
                            @Override
                            public void onSucesss(String json) {
                                try {
                                     ContentValues cValue = new ContentValues();
                                     cValue.put("UserID","2");
                                     cValue.put("LoginName",LoginName);
                                     cValue.put("PassWord",PassWord);
                                     System.out.print(ReturnParse.Parse(Login.this, json));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void onFailure(String errorMsg) {
                                System.out.print(errorMsg);
                            }
                        });

                        httpManager.post();
                        //endregion
                    }
                }
            }
        });
        //endregion

        //region 注册
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent().setClass(Login.this,Register.class));
            }
        });
        //endregion

        //region 找回密码
        findbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this, "跳转找回密码……", Toast.LENGTH_LONG).show();
            }
        });
        //endregion
    }
}

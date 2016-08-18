package com.sam.pet.AccountView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sam.pet.R;
import com.sam.pet.Util.CodeUtils;
import com.sam.pet.Util.HttpManager;
import com.sam.pet.Util.MyInterFaceUrl;
import com.sam.pet.Util.ReturnParse;
import com.sam.pet.Util.TGmd5;

import java.util.HashMap;
import java.util.Map;

public class Register extends Activity {
    private ImageView codeimg;
    private Button coderefresh,registersubmit;
    private EditText codeedit,loginname,password,nickname;

    String codenumber="";//验证码
    String LoginName = "";
    String PassWord = "";
    String NickName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //region 初始化
        loginname = (EditText) findViewById(R.id.registerloginname);
        password = (EditText) findViewById(R.id.registerpassword);
        nickname = (EditText) findViewById(R.id.registernickname);

        codeimg=(ImageView)findViewById(R.id.registercodeimg);
        coderefresh=(Button)findViewById(R.id.registercoderefresh);
        registersubmit=(Button)findViewById(R.id.registersubmitbtn);
        codeedit=(EditText) findViewById(R.id.registercode);
        //endregion

        //region 验证码初始化
        refreshcode();
        //endregion

        //region 验证码刷新
        coderefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //生成的验证码
                refreshcode();
            }
        });
        //endregion

        //region 提交注册
        registersubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserCode=codeedit.getText().toString();
                if(UserCode.isEmpty())
                {
                    Toast.makeText(Register.this, "验证码不能为空", Toast.LENGTH_LONG).show();
                    refreshcode();
                }else if(!UserCode.equals(codenumber)){
                    Toast.makeText(Register.this, "验证码错误", Toast.LENGTH_LONG).show();
                    codeedit.setText("");
                    refreshcode();
                }else{
                    //获取表单数据
                    LoginName=loginname.getText().toString();
                    PassWord=password.getText().toString();
                    NickName=nickname.getText().toString();
                    if (LoginName.isEmpty()) {
                        Toast.makeText(Register.this, "请输入手机号", Toast.LENGTH_LONG).show();
                    } else {
                        if (PassWord.isEmpty()) {
                            Toast.makeText(Register.this, "请输入密码", Toast.LENGTH_LONG).show();
                        } else if (PassWord.length() < 6 || PassWord.length() > 12) {
                            Toast.makeText(Register.this, "密码为6-12位数字或字母", Toast.LENGTH_LONG).show();
                        } else if(NickName.isEmpty()){
                            Toast.makeText(Register.this, "请输入论坛昵称", Toast.LENGTH_LONG).show();
                        }
                        else{

                            //region 参数拼接
                            Map<String, String> params = new HashMap<>();
                            params.put("username", LoginName);
                            params.put("password", PassWord);
                            params.put("nickname",NickName);
                            //endregion

                            //region 数据请求
                            HttpManager httpManager = new HttpManager(Register.this, MyInterFaceUrl.Registerinterface, TGmd5.getMD5(params), new HttpManager.OnRequestResonseListener() {
                                @Override
                                public void onSucesss(String json) {
                                    try {
                                        System.out.print(ReturnParse.Parse(Register.this, json));
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
            }
        });
        //endregion
    }

    //region 刷新验证码
    private void refreshcode(){
        codenumber = CodeUtils.createCode();
        codeimg.setImageBitmap(CodeUtils.getInstance().createBitmap(codenumber));
    }
    //endregion
}

package com.sam.pet.Util;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


/*调用方法
*
*Map<String,String> params = new HashMap<>();
*params.put("token", Config.TOKEN);----(添加参数)
*
*HttpManager httpManager = new HttpManager(this, Config.URL, params, new HttpManager.OnRequestResonseListener() {
*    @Override----(成功调用)
*    public void onSucesss(String json) {
*
*        try{
*           JSONObject obj=new JSONObject(json);
*           String msg=obj.getString("msg");----(解析返回的内容)
*           System.out.print(msg);
*       }catch(Exception e){
*           e.printStackTrace();
*       }
*    }
*    @Override----(失败调用)
*    public void onFailure(String errorMsg) {
*        System.out.print("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
*    }
*});
*
*httpManager.get();---(请求类型)
* */

// 网络框架封装
public class HttpManager {

    private String url;
    private Map<String, String> params;
    private HttpUtils http;
    private boolean isShowDialog;
    private Context context;

    public HttpManager(Context context, String url, Map<String, String> params,
                       OnRequestResonseListener listener, boolean isShowDialog) {
        this.context = context;
        this.url = url;
        this.params = params;
        this.onRequestResonseListener = listener;
        this.isShowDialog = isShowDialog;
        http = new HttpUtils();

    }

    public void requestByVolley() {
        StringRequest request = new StringRequest(url, new Listener<String>() {

            @Override
            public void onResponse(String arg0) {
                if (onRequestResonseListener != null) {
                    onRequestResonseListener.onSucesss(arg0);
                }
            }
        }, new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                if (onRequestResonseListener != null) {
                    onRequestResonseListener.onFailure(arg0.getMessage());
                }
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        requestQueue.start();
    }

    public HttpManager(Context context, String url, Map<String, String> params,
                       OnRequestResonseListener listener) {
        this(context, url, params, listener, true);
    }

    public void get() {
        HttpMethod get = HttpMethod.GET;
        request(get);
    }

    public void post() {
        HttpMethod post = HttpMethod.POST;
        request(post);
    }

    private void request(HttpMethod get) {
        RequestParams requestParams = null;
        if (get == HttpMethod.GET){
            url = url+"?";
            Set<Entry<String, String>> entrySet = this.params.entrySet();
            int i = 0;
            for (Entry<String, String> entry : entrySet) {
                String key = entry.getKey();
                String value = entry.getValue();
                url = url+key+"="+value;
                if (i<params.size()-1){
                    url = url+"&";
                }
                i++;
            }
            Log.i("DD", "YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY::::::" + url);
        }else{
            requestParams = loadRequestParams();
        }

        http.send(get, url, requestParams, new RequestCallBack<String>() {

            private ProgressDialog dialog;

            @Override
            public void onStart() {
                super.onStart();
                if (isShowDialog)
                    dialog = ProgressDialog.show(context, null, "正在拼命加载中..");
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                if (dialog != null)
                    dialog.dismiss();
                if (onRequestResonseListener != null)
                    onRequestResonseListener.onSucesss(responseInfo.result);

            }

            @Override
            public void onFailure(HttpException error, String msg) {
                if (dialog != null)
                    dialog.dismiss();
                if (onRequestResonseListener != null)
                    onRequestResonseListener.onFailure(msg);
            }
        });
    }

    private RequestParams loadRequestParams() {
        if (this.params == null) {
            return null;
        }
        RequestParams requestParams = new RequestParams();
        Set<Entry<String, String>> entrySet = this.params.entrySet();
        for (Entry<String, String> entry : entrySet) {
            String key = entry.getKey();
            String value = entry.getValue();
            requestParams.addBodyParameter(key, value);
        }
        return requestParams;
    }

    private OnRequestResonseListener onRequestResonseListener;

    public OnRequestResonseListener getOnRequestResonseListener() {
        return onRequestResonseListener;
    }

    public void setOnRequestResonseListener(
            OnRequestResonseListener onRequestResonseListener) {
        this.onRequestResonseListener = onRequestResonseListener;
    }

    public interface OnRequestResonseListener {
        void onSucesss(String json);

        void onFailure(String errorMsg);
    }
}

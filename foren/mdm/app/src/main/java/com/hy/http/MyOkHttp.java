package com.hy.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.http.HttpClient;
import com.hy.frame.R;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.view.LoadingDialog;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.cache.CacheMode;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class MyOkHttp {
    private static final String[] CERTIFICATES = {"vjkejiBase64.cer"};
    private static final String JSON_Flag = "ret";
    private static final String JSON_Msg = "message";
    private static final String JSON_Obj = "obj";
    private CacheMode cacheMode;
    private Context context;
    private Map<String, String> headerParams;
    private boolean isDestroy;
    private List<IMyHttpListener> listeners;
    private LoadingDialog loadingDialog;
    private int qid;
    protected Request request;
    private boolean showDialog;
    private String signatures;

    public MyOkHttp(Context context, IMyHttpListener listener) {
        if (context == null) {
            MyLog.e("MyHttpClient init error!");
            return;
        }
        this.context = context;
        this.listeners = new ArrayList();
        this.listeners.add(listener);
    }

    public void setListener(IMyHttpListener listener) {
        if (this.listeners == null) {
            this.listeners = new ArrayList();
        } else {
            this.listeners.clear();
        }
        this.listeners.add(listener);
    }

    @Deprecated
    public void addListener(IMyHttpListener listener) {
        if (this.listeners == null) {
            this.listeners = new ArrayList();
        }
        this.listeners.add(listener);
    }

    private void setRequest(Request request) {
        this.request = request;
    }

    public Context getContext() {
        return this.context;
    }

    @Deprecated
    public void setContentType(String contentType) {
        addHeader("Content-Type", contentType);
    }

    @Deprecated
    public void setAccept(String accept) {
        addHeader(Headers.HEAD_KEY_ACCEPT, accept);
    }

    @Deprecated
    public void setUserAgent(String userAgent) {
        addHeader("User-Agent", userAgent);
    }

    public void addHeader(String key, String value) {
        if (this.headerParams == null) {
            this.headerParams = new HashMap();
        }
        this.headerParams.put(key, value);
    }

    public MyOkHttp(Context context, IMyHttpListener listener, boolean verify) {
        this(context, listener);
        PackageInfo pi;
        if (verify && (pi = HyUtil.getAppVersion(context)) != null && pi.signatures != null && pi.signatures.length > 0) {
            this.signatures = pi.signatures[0].toCharsString();
        }
    }

    public void post(int resId, String url) {
        post(resId, url, String.class);
    }

    public void post(int resId, String url, Map<String, String> params) {
        post(resId, url, params, String.class);
    }

    public <T> void post(int resId, String url, Class<T> cls) {
        post(resId, url, null, cls);
    }

    public <T> void post(int resId, String url, Map<String, String> params, Class<T> cls) {
        post(resId, url, params, cls, false);
    }

    public <T> void post(int requestCode, String url, Map<String, String> params, Class<T> cls, boolean list) {
        request(RequestMethod.POST, requestCode, url, params, cls, list);
    }

    public <T> void post(int requestCode, String url, String requestBody, Map<String, String> params, Class<T> cls, boolean list) {
        request(RequestMethod.POST, requestCode, url, params, cls, list);
    }

    public <T> void request(RequestMethod methodType, final int requestCode, String url, Map<String, String> params, final Class<T> cls, final boolean list) {
        if (!this.isDestroy) {
            final ResultInfo result = new ResultInfo();
            result.setRequestCode(requestCode);
            result.setQid(this.qid);
            result.setErrorCode(ResultInfo.CODE_ERROR_DEFAULT);
            this.qid = 0;
            if (!HyUtil.isNetworkConnected(this.context)) {
                result.setErrorCode(ResultInfo.CODE_ERROR_NET);
                result.setMsg(getString(R.string.API_FLAG_NET_FAIL));
                onRequestError(result);
                return;
            }
            if (this.signatures != null) {
                if (params == null) {
                    params = new HashMap<>();
                }
                params.put("signatures", this.signatures);
            }
            MyLog.et("request--", "what= " + requestCode + "----" + url);
            Request.Builder builder = new Request.Builder();
            builder.url(url);
            if (methodType == RequestMethod.GET) {
                builder.get();
            } else if (methodType == RequestMethod.POST) {
                FormBody.Builder formBody = new FormBody.Builder();
                if (params != null) {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        formBody.add(entry.getKey(), entry.getValue());
                    }
                }
                builder.post(formBody.build());
            }
            if (this.headerParams != null) {
                for (Map.Entry<String, String> map : this.headerParams.entrySet()) {
                    builder.addHeader(map.getKey(), map.getValue());
                }
            }
            builder.tag(result);
            this.request = builder.build();
            if (!this.isDestroy) {
                MyLog.d("onStart", "what=" + requestCode);
                showLoading();
                HttpClient.getInstance().getClient().newCall(this.request).enqueue(new Callback() { // from class: com.hy.http.MyOkHttp.1
                    @Override // okhttp3.Callback
                    public void onFailure(Call call, IOException e) {
                        if (!MyOkHttp.this.isDestroy) {
                            MyOkHttp.this.hideLoading();
                            int code = R.string.API_FLAG_EXCEPTION_CON;
                            result.setMsg(MyOkHttp.this.getString(code));
                            String msg = e.getMessage();
                            if (msg != null) {
                                msg.toLowerCase(Locale.CHINA);
                                if (msg.contains("broken pipe")) {
                                    code = R.string.API_FLAG_EXCEPTION_BROKEN;
                                } else if (msg.contains("timed out")) {
                                    code = R.string.API_FLAG_EXCEPTION_TIMEOUT;
                                } else if (msg.contains("unknownhostexception")) {
                                    code = R.string.API_FLAG_EXCEPTION_UNKNOWN_HOST;
                                }
                            }
                            ResultInfo result2 = (ResultInfo) call.request().tag();
                            MyLog.e("onFailed", "what=" + result2.getRequestCode() + ",msg=" + e.getMessage());
                            MyLog.e(requestCode + "----" + MyOkHttp.this.getString(code));
                            MyOkHttp.this.onRequestError(result2);
                        }
                    }

                    @Override // okhttp3.Callback
                    public void onResponse(Call call, Response response) throws IOException {
                        if (!MyOkHttp.this.isDestroy) {
                            MyOkHttp.this.hideLoading();
                            String data = response.body().string();
                            ResultInfo result2 = (ResultInfo) response.request().tag();
                            MyLog.et("onSucceed", "what=" + result2.getRequestCode() + ",data=" + data);
                            if (data == null || data.equals("{}")) {
                                MyLog.e(requestCode + "----" + MyOkHttp.this.getString(R.string.API_FLAG_NO_RESPONSE));
                                MyOkHttp.this.onRequestError(result2);
                                return;
                            }
                            MyOkHttp.this.doSuccess(result2, data, cls, list);
                        }
                    }
                });
            }
        }
    }

    public void get(int resId, String url) {
        get(resId, url, String.class);
    }

    public void get(int resId, String url, AjaxParams params) {
        get(resId, url, params, String.class);
    }

    public <T> void get(int resId, String url, Class<T> cls) {
        get(resId, url, null, cls);
    }

    public <T> void get(int resId, String url, AjaxParams params, Class<T> cls) {
        get(resId, url, params, cls, false);
    }

    public <T> void get(int requestCode, String url, AjaxParams params, Class<T> cls, boolean list) {
        request(RequestMethod.GET, requestCode, url, null, cls, list);
    }

    public <T> void get(int requestCode, String url, String requestBody, AjaxParams params, Class<T> cls, boolean list) {
        request(RequestMethod.GET, requestCode, url, null, cls, list);
    }

    @Deprecated
    protected <T> void doSuccess(ResultInfo result, String json, Class<T> cls, boolean list) {
        result.setObj(json);
        onRequestSuccess(result);
    }

    @Deprecated
    protected <T> void doSuccess(ResultInfo result, JSONArray obj, Class<T> cls, boolean list) {
        result.setObj(obj);
        onRequestSuccess(result);
    }

    protected <T> void doSuccess(ResultInfo result, JSONObject obj, Class<T> cls, boolean list) {
        int flag = 0;
        try {
            result.setJson(obj.toString());
            if (obj.has(JSON_Flag)) {
                flag = obj.getInt(JSON_Flag);
                result.setErrorCode(flag);
            }
            if (obj.has("message")) {
                result.setMsg(obj.getString("message"));
            }
            if (obj.has("code")) {
                result.setCode(obj.getString("code"));
            }
            String data = null;
            if (obj.has(JSON_Obj)) {
                data = obj.getString(JSON_Obj);
                result.setObjStr(data);
            }
            if (flag == 0) {
                doSuccessData(result, data, cls, list);
                return;
            }
            if (data != null) {
                result.setObj(data);
            }
            onRequestError(result);
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrorCode(ResultInfo.CODE_ERROR_DECODE);
            result.setMsg(getString(R.string.API_FLAG_ANALYSIS_ERROR));
            MyLog.e(getString(R.string.API_FLAG_ANALYSIS_ERROR));
            onRequestError(result);
        }
    }

    protected <T> void doSuccessData(ResultInfo result, String data, Class<T> cls, boolean list) {
        try {
            if (!HyUtil.isNoEmpty(data) || TextUtils.equals(data, "[]")) {
                onRequestSuccess(result);
            } else if (list) {
                ArrayList arrayList = null;
                JSONArray rlt = new JSONArray(data);
                if (rlt.length() > 0) {
                    int size = rlt.length();
                    arrayList = new ArrayList();
                    for (int i = 0; i < size; i++) {
                        arrayList.add(doT(rlt.getString(i), cls));
                    }
                }
                result.setObj(arrayList);
                onRequestSuccess(result);
            } else {
                result.setObj(doT(data, cls));
                onRequestSuccess(result);
            }
        } catch (Exception e) {
            result.setErrorCode(ResultInfo.CODE_ERROR_DECODE);
            result.setMsg(1 != 0 ? getString(R.string.API_FLAG_ANALYSIS_ERROR) : getString(R.string.API_FLAG_SUCCESS_ERROR));
            MyLog.e(result.getMsg());
            HyUtil.printException(e);
            onRequestError(result);
        }
    }

    private <T> Object doT(String data, Class<T> cls) {
        if (cls == String.class) {
            return data;
        }
        if (cls == Integer.TYPE || cls == Integer.class) {
            return Integer.valueOf(Integer.parseInt(data));
        }
        if (cls == Float.TYPE || cls == Float.class) {
            return Float.valueOf(Float.parseFloat(data));
        }
        if (cls == Double.TYPE || cls == Double.class) {
            return Double.valueOf(Double.parseDouble(data));
        }
        if (cls == Long.TYPE || cls == Long.class) {
            return Long.valueOf(Long.parseLong(data));
        }
        if (cls == Date.class || cls == java.sql.Date.class) {
            return stringToDateTime(data);
        }
        if (cls == Boolean.TYPE || cls == Boolean.class) {
            return Boolean.valueOf(Boolean.parseBoolean(data));
        }
        return new Gson().fromJson(data, (Class<Object>) cls);
    }

    public static Date stringToDateTime(String strDate) {
        if (strDate != null) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected void onRequestError(ResultInfo result) {
        if (!this.isDestroy && this.listeners != null) {
            for (IMyHttpListener listener : this.listeners) {
                listener.onRequestError(result);
            }
        }
    }

    protected void onRequestSuccess(ResultInfo result) {
        if (!this.isDestroy) {
            result.setErrorCode(0);
            if (this.listeners != null) {
                for (IMyHttpListener listener : this.listeners) {
                    listener.onRequestSuccess(result);
                }
            }
        }
    }

    public void setLoadingDialog(LoadingDialog loadingDialog) {
        this.loadingDialog = loadingDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

    protected void showLoading() {
        if (!this.isDestroy && this.showDialog) {
            if (this.loadingDialog == null) {
                setLoadingDialog(new LoadingDialog(this.context));
            }
            this.loadingDialog.show();
        }
    }

    public void showDialogNow() {
        showDialogNow(R.string.loading);
    }

    public void showDialogNow(int msgId) {
        setShowDialog(getString(msgId));
        if (!this.loadingDialog.isShowing()) {
            showLoading();
        }
    }

    public void setShowDialog(int msgId) {
        setShowDialog(getString(msgId));
    }

    public void dialogDismiss() {
        hideLoading();
    }

    public void setShowDialog(String msg) {
        if (!this.isDestroy) {
            if (msg == null) {
                this.showDialog = false;
                return;
            }
            this.showDialog = true;
            if (this.loadingDialog == null) {
                setLoadingDialog(new LoadingDialog(this.context, msg));
            } else {
                this.loadingDialog.setLoadMsg(msg);
            }
        }
    }

    protected void hideLoading() {
        if (!this.isDestroy && this.loadingDialog != null) {
            this.loadingDialog.dismiss();
        }
    }

    public void onDestroy() {
        MyLog.d(getClass(), "onDestroy");
        this.isDestroy = true;
        this.listeners = null;
        this.loadingDialog = null;
        this.request = null;
    }

    protected String getString(int resId) {
        return this.context.getString(resId);
    }
}

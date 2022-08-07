package com.hy.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.HttpsUtils;
import com.alipay.sdk.cons.b;
import com.google.gson.Gson;
import com.hy.frame.R;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.view.LoadingDialog;
import com.tencent.open.SocialConstants;
import com.yolanda.nohttp.Binary;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.JsonArrayRequest;
import com.yolanda.nohttp.JsonObjectRequest;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.RequestQueue;
import com.yolanda.nohttp.Response;
import com.yolanda.nohttp.StringRequest;
import com.yolanda.nohttp.cache.CacheMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import u.aly.av;

/* loaded from: classes.dex */
public abstract class MyHttpClient {
    private static final String[] CERTIFICATES = {"vjkejiBase64.cer"};
    private static final String JSON_Flag = "ret";
    private static final String JSON_Msg = "message";
    private static final String JSON_Obj = "obj";
    private static final int REQUEST_TYPE_JSON = 0;
    private static final int REQUEST_TYPE_JSONARRAY = 1;
    private static final int REQUEST_TYPE_STRING = 2;
    private static final int TIME_OUT = 60000;
    private CacheMode cacheMode;
    private Context context;
    private long diffTime;
    private Map<String, String> headerParams;
    private boolean isDestroy;
    private List<IMyHttpListener> listeners;
    private LoadingDialog loadingDialog;
    private int qid;
    protected Request request;
    private RequestQueue requestQueue;
    private int requestType;
    private boolean showDialog;
    private String signatures;

    public MyHttpClient(Context context, IMyHttpListener listener, String host) {
        this(context, listener, host, 0);
    }

    public MyHttpClient(Context context, IMyHttpListener listener, String host, int requestType) {
        if (context == null || host == null) {
            MyLog.e("MyHttpClient init error!");
            return;
        }
        this.context = context;
        this.diffTime = AppShare.get(getContext()).getLong("diffTime");
        this.listeners = new ArrayList();
        this.listeners.add(listener);
        this.requestType = requestType;
        this.requestQueue = NoHttp.newRequestQueue();
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

    public Map<String, String> getHeaderParams() {
        return this.headerParams;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public void setCacheMode(CacheMode cacheMode) {
        this.cacheMode = cacheMode;
    }

    public long getDiffTime() {
        return this.diffTime;
    }

    public void setDiffTime(long diffTime) {
        this.diffTime = diffTime;
    }

    public MyHttpClient(Context context, IMyHttpListener listener, String host, boolean verify) {
        this(context, listener, host);
        PackageInfo pi;
        if (verify && (pi = HyUtil.getAppVersion(context)) != null && pi.signatures != null && pi.signatures.length > 0) {
            this.signatures = pi.signatures[0].toCharsString();
        }
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

    public void post(int resId, String url) {
        post(resId, url, String.class);
    }

    public void post(int resId, String url, AjaxParams params) {
        post(resId, url, params, String.class);
    }

    public <T> void post(int resId, String url, Class<T> cls) {
        post(resId, url, null, cls);
    }

    public <T> void post(int resId, String url, AjaxParams params, Class<T> cls) {
        post(resId, url, params, cls, false);
    }

    public <T> void post(int requestCode, String url, AjaxParams params, Class<T> cls, boolean list) {
        this.request = new JsonObjectRequest(url, RequestMethod.POST);
        request(false, requestCode, url, params, cls, list);
    }

    public <T> void post(int requestCode, String url, String requestBody, AjaxParams params, Class<T> cls, boolean list) {
        this.request = new JsonObjectRequest(url, RequestMethod.POST);
        this.request.setRequestBody(requestBody);
        request(false, requestCode, url, params, cls, list);
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
        this.request = new JsonObjectRequest(url, RequestMethod.GET);
        request(true, requestCode, url, params, cls, list);
    }

    public <T> void get(int requestCode, String url, String requestBody, AjaxParams params, Class<T> cls, boolean list) {
        this.request = new JsonObjectRequest(url, RequestMethod.GET);
        this.request.setRequestBody(requestBody);
        request(true, requestCode, url, params, cls, list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> void request(boolean isGet, final int requestCode, String url, AjaxParams params, final Class<T> cls, final boolean list) {
        if (!this.isDestroy) {
            final ResultInfo result = new ResultInfo();
            result.setRequestCode(requestCode);
            result.setQid(this.qid);
            result.setErrorCode(ResultInfo.CODE_ERROR_DEFAULT);
            this.qid = 0;
            if (this.signatures != null) {
                if (params == null) {
                    params = new AjaxParams();
                }
                params.put("signatures", this.signatures);
            }
            MyLog.et(SocialConstants.TYPE_REQUEST, requestCode + "----" + url);
            if (this.request == null) {
                RequestMethod method = isGet ? RequestMethod.GET : RequestMethod.POST;
                switch (this.requestType) {
                    case 1:
                        this.request = NoHttp.createJsonArrayRequest(url, method);
                        break;
                    case 2:
                        this.request = NoHttp.createStringRequest(url, method);
                        break;
                    default:
                        this.request = NoHttp.createJsonObjectRequest(url, method);
                        break;
                }
            }
            if (url != null && url.contains(b.a)) {
                this.request.setSSLSocketFactory(HttpsUtils.getSslSocketFactory(HttpsUtils.getCertificates(this.context, CERTIFICATES), null, null).sSLSocketFactory);
            }
            this.request.setCancelSign(cls);
            this.request.setTag(result);
            if (this.headerParams != null) {
                for (Map.Entry<String, String> map : this.headerParams.entrySet()) {
                    this.request.addHeader(map.getKey(), map.getValue());
                }
            }
            if (params != null) {
                this.request.add(params.getUrlParams());
                if (params.getFileParams() != null) {
                    for (Map.Entry<String, Binary> map2 : params.getFileParams().entrySet()) {
                        this.request.add(map2.getKey(), map2.getValue());
                    }
                }
            }
            if (this.cacheMode != null) {
                this.request.setCacheMode(this.cacheMode);
            } else {
                this.request.setCacheMode(CacheMode.DEFAULT);
            }
            OnResponseListener listener = null;
            if (this.request instanceof JsonObjectRequest) {
                listener = new OnResponseListener<JSONObject>() { // from class: com.hy.http.MyHttpClient.1
                    @Override // com.yolanda.nohttp.OnResponseListener
                    public void onStart(int what) {
                        if (!MyHttpClient.this.isDestroy) {
                            MyLog.d("onStart(JSONObject)", "what=" + what);
                            MyHttpClient.this.showLoading();
                        }
                    }

                    @Override // com.yolanda.nohttp.OnResponseListener
                    public void onSucceed(int what, Response<JSONObject> response) {
                        if (!MyHttpClient.this.isDestroy) {
                            MyHttpClient.this.hideLoading();
                            JSONObject data = response.get();
                            MyLog.et("onSucceed(JSONObject)", "what=" + what + ",data=" + data);
                            ResultInfo result2 = (ResultInfo) response.getTag();
                            if (data == null || data.toString().equals("{}")) {
                                MyLog.e(requestCode + "----" + MyHttpClient.this.getString(R.string.API_FLAG_NO_RESPONSE));
                                MyHttpClient.this.onRequestError(result2);
                                return;
                            }
                            MyHttpClient.this.doSuccess(result2, data, cls, list);
                        }
                    }

                    @Override // com.yolanda.nohttp.OnResponseListener
                    public void onFailed(int what, String url2, Object tag, Exception exception, int responseCode, long networkMillis) {
                        if (!MyHttpClient.this.isDestroy) {
                            MyLog.e("onFailed(JSONObject)", "what=" + what + ",msg=" + exception.getMessage());
                            MyHttpClient.this.hideLoading();
                            int code = R.string.API_FLAG_EXCEPTION_CON;
                            result.setMsg(MyHttpClient.this.getString(code));
                            String msg = exception.getMessage();
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
                            MyLog.e(requestCode + "----" + MyHttpClient.this.getString(code));
                            MyHttpClient.this.onRequestError((ResultInfo) tag);
                        }
                    }

                    @Override // com.yolanda.nohttp.OnResponseListener
                    public void onFinish(int what) {
                        MyLog.d("onFinish(JSONObject)", "what=" + what);
                        MyHttpClient.this.cacheMode = null;
                        MyHttpClient.this.request = null;
                    }
                };
            } else if (this.request instanceof JsonArrayRequest) {
                listener = new OnResponseListener<JSONArray>() { // from class: com.hy.http.MyHttpClient.2
                    @Override // com.yolanda.nohttp.OnResponseListener
                    public void onStart(int what) {
                        if (!MyHttpClient.this.isDestroy) {
                            MyLog.d("onStart(JSONArray)", "what=" + what);
                            MyHttpClient.this.showLoading();
                        }
                    }

                    @Override // com.yolanda.nohttp.OnResponseListener
                    public void onSucceed(int what, Response<JSONArray> response) {
                        if (!MyHttpClient.this.isDestroy) {
                            MyHttpClient.this.hideLoading();
                            JSONArray data = response.get();
                            MyLog.et("onSucceed(JSONArray)", "what=" + what + ",data=" + data);
                            ResultInfo result2 = (ResultInfo) response.getTag();
                            if (data != null) {
                                MyHttpClient.this.doSuccess(result2, data, cls, list);
                                return;
                            }
                            MyLog.e(requestCode + "----" + MyHttpClient.this.getString(R.string.API_FLAG_NO_RESPONSE));
                            MyHttpClient.this.onRequestError(result2);
                        }
                    }

                    @Override // com.yolanda.nohttp.OnResponseListener
                    public void onFailed(int what, String url2, Object tag, Exception exception, int responseCode, long networkMillis) {
                        if (!MyHttpClient.this.isDestroy) {
                            MyLog.e("onFailed(JSONArray)", "what=" + what + ",msg=" + exception.getMessage());
                            MyHttpClient.this.hideLoading();
                            int code = R.string.API_FLAG_EXCEPTION_CON;
                            result.setMsg(MyHttpClient.this.getString(code));
                            String msg = exception.getMessage();
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
                            MyLog.e(requestCode + "----" + code);
                            MyHttpClient.this.onRequestError((ResultInfo) tag);
                        }
                    }

                    @Override // com.yolanda.nohttp.OnResponseListener
                    public void onFinish(int what) {
                        MyLog.d("onFinish(JSONArray)", "what=" + what);
                        MyHttpClient.this.cacheMode = null;
                        MyHttpClient.this.request = null;
                    }
                };
            } else if (this.request instanceof StringRequest) {
                listener = new OnResponseListener<String>() { // from class: com.hy.http.MyHttpClient.3
                    @Override // com.yolanda.nohttp.OnResponseListener
                    public void onStart(int what) {
                        if (!MyHttpClient.this.isDestroy) {
                            MyLog.d("onStart(String)", "what=" + what);
                            MyHttpClient.this.showLoading();
                        }
                    }

                    @Override // com.yolanda.nohttp.OnResponseListener
                    public void onSucceed(int what, Response<String> response) {
                        if (!MyHttpClient.this.isDestroy) {
                            MyHttpClient.this.hideLoading();
                            String data = response.get();
                            MyLog.et("onSucceed(String)", "what=" + what + ",data=" + data);
                            ResultInfo result2 = (ResultInfo) response.getTag();
                            if (data != null) {
                                MyHttpClient.this.doSuccess(result2, data, cls, list);
                                return;
                            }
                            MyLog.e(requestCode + "----" + MyHttpClient.this.getString(R.string.API_FLAG_NO_RESPONSE));
                            MyHttpClient.this.onRequestError(result2);
                        }
                    }

                    @Override // com.yolanda.nohttp.OnResponseListener
                    public void onFailed(int what, String url2, Object tag, Exception exception, int responseCode, long networkMillis) {
                        if (!MyHttpClient.this.isDestroy) {
                            MyLog.e("onFailed(String)", "what=" + what + ",msg=" + exception.getMessage());
                            MyHttpClient.this.hideLoading();
                            int code = R.string.API_FLAG_EXCEPTION_CON;
                            result.setMsg(MyHttpClient.this.getString(code));
                            String msg = exception.getMessage();
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
                            MyLog.e(requestCode + "----" + MyHttpClient.this.getString(code));
                            MyHttpClient.this.onRequestError((ResultInfo) tag);
                        }
                    }

                    @Override // com.yolanda.nohttp.OnResponseListener
                    public void onFinish(int what) {
                        MyLog.d("onFinish(String)", "what=" + what);
                        MyHttpClient.this.cacheMode = null;
                        MyHttpClient.this.request = null;
                    }
                };
            }
            this.requestQueue.add(requestCode, this.request, listener);
        }
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
                flag = obj.optInt(JSON_Flag);
                result.setErrorCode(flag);
            }
            if (obj.has("message")) {
                result.setMsg(obj.optString("message"));
            }
            if (obj.has(av.aG)) {
                result.setError(obj.optString(av.aG));
            }
            if (obj.has("code")) {
                result.setCode(obj.optString("code"));
            }
            String data = null;
            if (obj.has(JSON_Obj)) {
                data = obj.optString(JSON_Obj);
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

    private static Date stringToDateTime(String strDate) {
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
        showDialogNow(getString(msgId));
    }

    public void showDialogNow(String msg) {
        setShowDialog(msg);
        showLoading();
    }

    public void setShowDialog(int msgId) {
        setShowDialog(getString(msgId));
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

    public void dialogDismiss() {
        hideLoading();
    }

    protected void hideLoading() {
        if (!this.isDestroy && this.loadingDialog != null) {
            this.loadingDialog.dismiss();
        }
    }

    public void cancleRequestBySign(Object o) {
        if (this.request != null) {
            this.request.cancelBySign(o);
        }
        if (this.loadingDialog != null) {
            this.loadingDialog.dismiss();
        }
    }

    public void onDestroy() {
        MyLog.d(getClass(), "onDestroy");
        this.isDestroy = true;
        this.listeners = null;
        this.loadingDialog = null;
        if (this.request != null) {
            this.request.cancel(true);
        }
        this.request = null;
        if (this.requestQueue != null) {
            this.requestQueue.stop();
        }
        this.requestQueue = null;
    }

    protected String getString(int resId) {
        return this.context.getString(resId);
    }
}

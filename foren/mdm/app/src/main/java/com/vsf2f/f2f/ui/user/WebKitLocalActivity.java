package com.vsf2f.f2f.ui.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.alibaba.fastjson.util.Base64;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.util.j;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.common.MyApplication;
import com.cdlinglu.utils.CameraUtil;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.PayUtil;
import com.cdlinglu.utils.ShareUtils;
import com.cdlinglu.utils.pay.PayResult;
import com.cdlinglu.utils.pay.WxPayInfo;
import com.cdlinglu.utils.x5WebView.WebViewJavaScriptFunction;
import com.easeui.EaseConstant;
import com.em.DemoHelper;
import com.em.ui.ChatActivity;
import com.em.ui.UserProfileActivity;
import com.em.utils.UserShared;
import com.google.gson.Gson;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.hy.frame.view.recycler.xRefreshView.XRefreshView;
import com.hy.frame.view.recycler.xRefreshView.XWebView;
import com.hy.http.AjaxParams;
import com.hyphenate.chat.MessageEncoder;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ShareThirdBean;
import com.vsf2f.f2f.ui.dialog.BasePopupMenu;
import com.vsf2f.f2f.ui.dialog.MenuWebDialog;
import com.vsf2f.f2f.ui.dialog.PictureDialog;
import com.vsf2f.f2f.ui.dialog.ShareForwardDialog;
import com.vsf2f.f2f.ui.report.ReportMainActivity;
import com.vsf2f.f2f.ui.user.pwd.LoginActivity;
import com.vsf2f.f2f.ui.user.pwd.RegistActivity;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpHost;
import org.json.JSONException;
import org.json.JSONObject;
import u.aly.av;

/* loaded from: classes2.dex */
public class WebKitLocalActivity extends BaseActivity implements XRefreshViewListener, BasePopupMenu.PopupListener, CameraUtil.CameraDealListener, PictureDialog.ConfirmDlgListener, PayUtil.PayListener {
    private static final int ALI_PAY_FLAG = 101;
    private static final int WX_PAY_FLAG = 102;
    private boolean autoTitle;
    private ProgressBar bar;
    private CameraUtil camera;
    private ImageView empty;
    private Handler handler;
    private ImageView imgMenu;
    private boolean isOpenWx;
    private boolean loadError;
    private ValueCallback<Uri[]> mUploadFile;
    private MenuWebDialog menuDialog;
    private PictureDialog pictureDlg;
    private ShareForwardDialog shareDialog;
    private ShareUtils shareUtils;
    private XWebView web;
    private XRefreshView xrv_wv;
    private ViewGroup.LayoutParams webParams = new ViewGroup.LayoutParams(-1, -1);
    @SuppressLint({"HandlerLeak"})
    protected Handler mHandler = new Handler() { // from class: com.vsf2f.f2f.ui.user.WebKitLocalActivity.5
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 101:
                    WebKitLocalActivity.this.vsPayResultFromApp(new PayResult((String) msg.obj).getResultStatus(), "alipay");
                    return;
                default:
                    return;
            }
        }
    };
    private Handler myHandler = new Handler();
    private Runnable runable = new Runnable() { // from class: com.vsf2f.f2f.ui.user.WebKitLocalActivity.11
        @Override // java.lang.Runnable
        public void run() {
            WebKitLocalActivity.this.web.loadUrl("");
        }
    };

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_web_kit_local;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        this.xrv_wv = (XRefreshView) getView(R.id.xrv_wv);
        this.bar = (ProgressBar) getView(R.id.web_pbBar);
        this.web = new XWebView(this);
        this.web.setLayoutParams(this.webParams);
        this.xrv_wv.addView(this.web);
        this.xrv_wv.setXRefreshViewListener(this);
        this.empty = (ImageView) getViewAndClick(R.id.web_imgEmpty);
        setOnClickListener(R.id.top_imgBack);
        setOnClickListener(R.id.top_imgClose);
        this.imgMenu = (ImageView) getViewAndClick(R.id.top_imgMenu);
        this.shareUtils = new ShareUtils(this.context);
        Bundle bundle = getBundle();
        if (bundle == null || !bundle.containsKey(Constant.FLAG)) {
            finish();
            return;
        }
        String url = bundle.getString(Constant.FLAG);
        if (TextUtils.isEmpty(url) || (!url.contains("vjkeji") && !url.contains("vsf2f"))) {
            this.xrv_wv.setCanFunction(false);
        } else {
            this.xrv_wv.setCanFunction(true);
        }
        String title = bundle.getString(Constant.FLAG_TITLE);
        boolean showMenu = bundle.getBoolean(Constant.FLAG2, false);
        bundle.getBoolean(Constant.FLAG3, true);
        this.imgMenu.setVisibility(showMenu ? 0 : 8);
        if (TextUtils.isEmpty(url)) {
            setTitle("空白页");
        } else if (TextUtils.isEmpty(title) || com.vsf2f.f2f.ui.utils.Constant.FLAG_AUTO_TITLE.equals(title)) {
            this.autoTitle = true;
        } else if (com.vsf2f.f2f.ui.utils.Constant.FLAG_NO_TITLE.equals(title)) {
            hideHeader();
        } else {
            setTitle(title);
        }
        WebUtils.loadOtherUrl(this.web, url);
        if (this.handler == null) {
            this.handler = new Handler(new Handler.Callback() { // from class: com.vsf2f.f2f.ui.user.WebKitLocalActivity.1
                @Override // android.os.Handler.Callback
                public boolean handleMessage(Message msg) {
                    if (WebKitLocalActivity.this.bar != null) {
                        if (msg.what == 100) {
                            WebKitLocalActivity.this.xrv_wv.stopRefresh();
                            WebKitLocalActivity.this.bar.setVisibility(8);
                        } else {
                            WebKitLocalActivity.this.bar.setVisibility(0);
                            WebKitLocalActivity.this.bar.setProgress(msg.what);
                        }
                    }
                    return false;
                }
            });
        }
        this.web.setWebViewClient(new WebViewClient() { // from class: com.vsf2f.f2f.ui.user.WebKitLocalActivity.2
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url2) {
                MyLog.w("urlWeb====>", url2);
                if (url2.endsWith("goback2app")) {
                    WebKitLocalActivity.this.finish();
                }
                if (url2.startsWith("weixin://")) {
                    try {
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        intent.setData(Uri.parse(url2));
                        WebKitLocalActivity.this.startActivity(intent);
                        return true;
                    } catch (Exception e) {
                        MyToast.show(WebKitLocalActivity.this.context, (int) R.string.no_install_wx);
                        WebKitLocalActivity.this.goBack();
                        return true;
                    }
                } else if (url2.contains("alipay")) {
                    if (WebKitLocalActivity.this.parseScheme(url2)) {
                        try {
                            Intent intent2 = Intent.parseUri(Uri.parse(url2).toString(), 1);
                            intent2.addCategory("android.intent.category.BROWSABLE");
                            intent2.setComponent(null);
                            WebKitLocalActivity.this.startActivity(intent2);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    return false;
                } else if (url2.startsWith(com.tencent.smtt.sdk.WebView.SCHEME_TEL)) {
                    Intent intent3 = new Intent("android.intent.action.CALL", Uri.parse(url2));
                    if (ActivityCompat.checkSelfPermission(WebKitLocalActivity.this.context, "android.permission.CALL_PHONE") != 0) {
                        return false;
                    }
                    WebKitLocalActivity.this.startActivity(intent3);
                    return true;
                } else if (url2.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                    return false;
                } else {
                    if (!url2.startsWith("vsf2f")) {
                        return true;
                    }
                    String newurl = WebKitLocalActivity.this.vsf2fUrl(url2);
                    if (!TextUtils.isEmpty(newurl)) {
                        return true;
                    }
                    view.loadUrl(newurl);
                    return true;
                }
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url2) {
                WebKitLocalActivity.this.setWebTitle(view.getTitle());
                if (WebKitLocalActivity.this.loadError) {
                    WebKitLocalActivity.this.loadError = false;
                    WebKitLocalActivity.this.empty.setVisibility(0);
                } else {
                    WebKitLocalActivity.this.empty.setVisibility(8);
                }
                WebKitLocalActivity.this.xrv_wv.stopRefresh();
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                WebKitLocalActivity.this.loadError = true;
                WebKitLocalActivity.this.xrv_wv.stopRefresh();
            }
        });
        this.web.setWebChromeClient(new WebChromeClient() { // from class: com.vsf2f.f2f.ui.user.WebKitLocalActivity.3
            @Override // android.webkit.WebChromeClient
            public boolean onJsPrompt(WebView webView, String url2, String message, String defaultValue, JsPromptResult jsPromptResult) {
                if (message.equals("1")) {
                }
                jsPromptResult.confirm(j.c);
                return true;
            }

            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView view, int newProgress) {
                if (WebKitLocalActivity.this.handler != null) {
                    WebKitLocalActivity.this.handler.sendEmptyMessage(newProgress);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onReceivedTitle(WebView view, String title2) {
                if (!TextUtils.isEmpty(title2) && title2.toLowerCase().contains(av.aG)) {
                    WebKitLocalActivity.this.loadError = true;
                }
            }

            @Override // android.webkit.WebChromeClient
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                WebKitLocalActivity.this.mUploadFile = filePathCallback;
                WebKitLocalActivity.this.showPictureDlg();
                return true;
            }
        });
        initSetting();
        setResult(-1);
    }

    public void setWebTitle(String title) {
        if (this.autoTitle) {
            setTitle(title);
        }
    }

    private void initSetting() {
        this.web.removeJavascriptInterface("searchBoxJavaBridge_");
        this.web.removeJavascriptInterface("accessibility");
        this.web.removeJavascriptInterface("accessibilityTraversal");
        WebSettings settings = this.web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setDatabaseEnabled(true);
        settings.setSavePassword(true);
        settings.setCacheMode(-1);
        settings.setAppCacheMaxSize(Long.MAX_VALUE);
        settings.setGeolocationDatabasePath(getApp().getDir("database", 0).getPath());
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        settings.setUserAgentString(settings.getUserAgentString() + " vsf2fcompany/f2f/" + ComUtil.getVersion(this));
        this.web.setInitialScale(10);
        this.web.setDownloadListener(new MyWebViewDownLoadListener());
        addJsInterface();
    }

    private void addJsInterface() {
        this.web.addJavascriptInterface(new WebViewJavaScriptFunction() { // from class: com.vsf2f.f2f.ui.user.WebKitLocalActivity.4
            @Override // com.cdlinglu.utils.x5WebView.WebViewJavaScriptFunction
            @JavascriptInterface
            public void onJsFunctionCalled(String tag) {
            }

            @JavascriptInterface
            public void openChatPanel(String s) {
                if (s != null) {
                    try {
                        JSONObject json = new JSONObject(s);
                        String user = json.optString(com.vsf2f.f2f.ui.utils.Constant.USER_BUCKET);
                        Bundle bundle = new Bundle();
                        if (DemoHelper.getInstance().getCurrentUserName().equals(user)) {
                            MyToast.show(WebKitLocalActivity.this.context, "不能和自己聊天");
                            return;
                        }
                        if (TextUtils.isEmpty(user)) {
                            bundle.putString("username", com.vsf2f.f2f.ui.utils.Constant.COM_SOURCE_USER);
                        } else {
                            bundle.putString("username", user);
                        }
                        bundle.putBoolean(EaseConstant.BACK_TYPE, false);
                        bundle.putString(MessageEncoder.ATTR_EXT, json.optString(MessageEncoder.ATTR_EXT));
                        WebKitLocalActivity.this.startAct(ChatActivity.class, bundle);
                        return;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(WebKitLocalActivity.this.getApplicationContext(), "发起聊天失败", 0).show();
            }

            @JavascriptInterface
            public void putZhimaScore(String zhimaCode) {
                UserShared.getInstance().save(com.vsf2f.f2f.ui.utils.Constant.ZHIMACODE, Integer.parseInt(((Map) new Gson().fromJson(zhimaCode, (Class<Object>) new HashMap().getClass())).get("score")));
                WebKitLocalActivity.this.setResult(-1);
            }

            @JavascriptInterface
            public String setWebViewTitle(String title) {
                MyLog.e("webView", "title==>" + title + "");
                WebKitLocalActivity.this.setTitle(title + "");
                return "android回馈" + title;
            }

            @JavascriptInterface
            public String getLoginUserInfoFromApp() {
                return WebUtils.getTokenJson(WebKitLocalActivity.this);
            }

            @JavascriptInterface
            public String getLoginUserInfoFromApp(String s) {
                return WebUtils.getTokenJson(WebKitLocalActivity.this);
            }

            @JavascriptInterface
            public String getVsAppType(String s) {
                return getVsAppType();
            }

            @JavascriptInterface
            public String getVsAppType() {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put(Constants.PARAM_PLATFORM, f.a);
                    jsonObject.put("type", "F2F");
                } catch (JSONException e) {
                    HyUtil.printException(e);
                }
                return jsonObject.toString();
            }

            @JavascriptInterface
            public String vsPayFromWap(String order) {
                String str;
                try {
                    JSONObject jsonObject = new JSONObject(order);
                    AjaxParams params = new AjaxParams();
                    params.put("orderNo", jsonObject.optString("orderNo"));
                    params.put("orderInfo", jsonObject.optString("orderInfo"));
                    params.put("payType", jsonObject.optString("payType"));
                    params.put("timestamp", jsonObject.optString("timestamp"));
                    params.put("nonceStr", jsonObject.optString("nonceStr"));
                    if (!jsonObject.optString("sign").equals(ComUtil.encryptParam(params))) {
                        JSONObject json = new JSONObject();
                        json.put(j.c, "fail");
                        json.put(av.aG, "sign_error");
                        json.put("msg", "签名失败");
                        str = json.toString();
                    } else {
                        final String orderInfo = new String(Base64.decodeFast(jsonObject.optString("orderInfo")), "utf-8");
                        try {
                            if ("alipay".equals(jsonObject.optString("payType"))) {
                                ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.user.WebKitLocalActivity.4.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        String result = new PayTask(WebKitLocalActivity.this).pay(orderInfo, true);
                                        Message msg = new Message();
                                        msg.what = 101;
                                        msg.obj = result;
                                        WebKitLocalActivity.this.mHandler.sendMessage(msg);
                                    }
                                });
                            } else {
                                WebKitLocalActivity.this.getClient().showDialogNow(R.string.load_wx);
                                try {
                                    new PayUtil(WebKitLocalActivity.this, PayUtil.PayStyle.WxPay).toWxPay(new WxPayInfo(new JSONObject(orderInfo)));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    MyToast.show(WebKitLocalActivity.this.context, "调取微信失败");
                                    WebKitLocalActivity.this.getClient().dialogDismiss();
                                }
                            }
                            JSONObject json2 = new JSONObject();
                            json2.put(j.c, "success");
                            str = json2.toString();
                        } catch (Exception e2) {
                            e = e2;
                            HyUtil.printException(e);
                            return "{\"result\",\"fail\"}";
                        }
                    }
                    return str;
                } catch (Exception e3) {
                    e = e3;
                }
            }
        }, "Android");
    }

    public void vsPayResultFromApp(String resultCode, String payType) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("resultCode", resultCode);
            jsonObject.put(Constants.PARAM_PLATFORM, f.a);
            jsonObject.put("payType", payType);
            this.web.evaluateJavascript("javascript:vsPayResultFromApp(" + jsonObject.toString() + ")", new ValueCallback<String>() { // from class: com.vsf2f.f2f.ui.user.WebKitLocalActivity.6
                public void onReceiveValue(String result) {
                    MyLog.e(result);
                }
            });
        } catch (Exception e) {
            MyToast.show(this.context, "请重试");
            e.printStackTrace();
        }
    }

    private void showMenu() {
        if (this.menuDialog == null) {
            this.menuDialog = new MenuWebDialog(this.context, this);
            this.menuDialog.showAsDropDown(this.imgMenu);
        } else if (this.menuDialog.isShowing()) {
            this.menuDialog.dismiss();
        } else {
            this.menuDialog.showAsDropDown(this.imgMenu);
        }
    }

    public void showForwardDialog() {
        if (this.shareDialog == null) {
            this.shareDialog = new ShareForwardDialog(this);
            this.shareDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.user.WebKitLocalActivity.7
                @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                public void onDlgConfirm(BaseDialog dlg, int flag) {
                    switch (flag) {
                        case 1:
                            WebKitLocalActivity.this.toShare();
                            return;
                        case 2:
                            ComUtil.copySys(WebKitLocalActivity.this.context, WebKitLocalActivity.this.shareUtils.getShare().getUrl());
                            return;
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        default:
                            return;
                        case 11:
                            WebKitLocalActivity.this.shareUtils.shareTask(1);
                            return;
                        case 12:
                            WebKitLocalActivity.this.shareUtils.shareTask(0);
                            return;
                        case 13:
                            WebKitLocalActivity.this.shareUtils.qqShare();
                            return;
                        case 14:
                            WebKitLocalActivity.this.shareUtils.qqZoneShare();
                            return;
                    }
                }
            });
        }
        this.shareDialog.show();
    }

    public void toShare() {
        try {
            if (isNoLogin()) {
                MyToast.show(this.context, (int) R.string.login_hint);
                startAct(LoginActivity.class);
            } else {
                this.web.evaluateJavascript("javascript:getShareInMDMData()", new ValueCallback<String>() { // from class: com.vsf2f.f2f.ui.user.WebKitLocalActivity.8
                    public void onReceiveValue(String value) {
                        if (TextUtils.isEmpty(value) || "{}".equals(value)) {
                            MyToast.show(WebKitLocalActivity.this.context, "店铺未激活。不支持分享");
                        } else if (f.b.equals(value)) {
                            MyToast.show(WebKitLocalActivity.this.context, "不支持分享");
                        } else {
                            String herf = "";
                            try {
                                herf = new JSONObject(value).optString("url");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            WebKitLocalActivity.this.getClient().post(R.string.API_CIRCLES_PUBLIC, ComUtil.getF2FApi(WebKitLocalActivity.this.context, WebKitLocalActivity.this.getString(R.string.API_CIRCLES_PUBLIC)), WebKitLocalActivity.this.shareUtils.shareCircle(value, herf), String.class, false);
                        }
                    }
                });
            }
        } catch (Exception e) {
            MyToast.show(this.context, "请重试");
            e.printStackTrace();
        }
    }

    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4 || event.getAction() != 0) {
            return false;
        }
        goBack();
        return false;
    }

    public void goBack() {
        if (this.web == null || !this.web.canGoBack()) {
            finish();
        } else {
            this.web.goBack();
        }
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onCameraTakeSuccess(String path) {
        MyLog.e("onCameraTakeSuccess: " + path);
        this.camera.cropImageUri(1, 1, 256);
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onCameraPickSuccess(String path) {
        MyLog.e("onCameraPickSuccess: " + path);
        this.camera.cropImageUri(path, 1, 1, 256);
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onCameraCutSuccess(String uri) {
        if (this.mUploadFile == null) {
            Toast.makeText(this, "文件上传失败", 0).show();
        } else {
            this.mUploadFile.onReceiveValue(new Uri[]{Uri.fromFile(new File(uri))});
        }
    }

    @Override // com.cdlinglu.utils.CameraUtil.CameraDealListener
    public void onFunctionCancel(String uri) {
        this.mUploadFile.onReceiveValue(null);
    }

    @Override // com.vsf2f.f2f.ui.dialog.PictureDialog.ConfirmDlgListener
    public void onDlgCameraClick(PictureDialog dlg) {
        if (this.camera != null) {
            this.camera.onDlgCameraClick();
        }
    }

    @Override // com.vsf2f.f2f.ui.dialog.PictureDialog.ConfirmDlgListener
    public void onDlgPhotoClick(PictureDialog dlg) {
        if (this.camera != null) {
            this.camera.onDlgPhotoClick();
        }
    }

    @Override // com.vsf2f.f2f.ui.dialog.PictureDialog.ConfirmDlgListener
    public void onDlgCancelClick(PictureDialog dlg) {
        this.mUploadFile.onReceiveValue(null);
    }

    @Override // com.cdlinglu.utils.PayUtil.PayListener
    public void paySuccess(PayUtil.PayStyle style) {
        this.isOpenWx = true;
    }

    @Override // com.cdlinglu.utils.PayUtil.PayListener
    public void payFail(String msg) {
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu.PopupListener
    public void onClickPopup(View v) {
        switch (v.getId()) {
            case R.id.web_txtRefresh /* 2131756286 */:
                this.web.reload();
                return;
            case R.id.web_txtShare /* 2131756287 */:
                try {
                    this.web.evaluateJavascript("javascript:getShareInfoFromApp()", new ValueCallback<String>() { // from class: com.vsf2f.f2f.ui.user.WebKitLocalActivity.10
                        public void onReceiveValue(String value) {
                            ShareThirdBean shareThirdBean;
                            if (TextUtils.isEmpty(value) || f.b.equals(value)) {
                                shareThirdBean = new ShareThirdBean(WebKitLocalActivity.this.web.getTitle(), WebKitLocalActivity.this.web.getUrl(), "");
                            } else {
                                shareThirdBean = (ShareThirdBean) new Gson().fromJson(value, (Class<Object>) ShareThirdBean.class);
                            }
                            WebKitLocalActivity.this.shareUtils.setShare(shareThirdBean);
                            WebKitLocalActivity.this.showForwardDialog();
                        }
                    });
                    return;
                } catch (Exception e) {
                    MyToast.show(this.context, "请重试");
                    e.printStackTrace();
                    return;
                }
            case R.id.web_txtReport /* 2131756288 */:
                try {
                    this.web.evaluateJavascript("javascript:getReportRecordData()", new ValueCallback<String>() { // from class: com.vsf2f.f2f.ui.user.WebKitLocalActivity.9
                        public void onReceiveValue(String value) {
                            MyLog.e("webReport", value);
                            if (HyUtil.isEmpty(value)) {
                                MyToast.show(WebKitLocalActivity.this.context, "未检测到可举报内容");
                                return;
                            }
                            try {
                                JSONObject json = new JSONObject(value);
                                String objId = json.optString("objId");
                                int repotType = json.optInt("objType");
                                String reportedUser = json.optString("reportedUser");
                                Bundle bundle = new Bundle();
                                bundle.putInt("objType", repotType);
                                bundle.putString("objId", objId);
                                bundle.putString("reportedUser", reportedUser);
                                WebKitLocalActivity.this.startAct(ReportMainActivity.class, bundle);
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                        }
                    });
                    return;
                } catch (Exception e2) {
                    MyToast.show(this.context, "不支持举报");
                    e2.printStackTrace();
                    return;
                }
            case R.id.web_txtCopy /* 2131756289 */:
                try {
                    ComUtil.copySys(this.context, this.web.getUrl());
                    return;
                } catch (Exception e3) {
                    MyToast.show(this.context, (int) R.string.copy_failed);
                    e3.printStackTrace();
                    return;
                }
            case R.id.web_txtBrowser /* 2131756290 */:
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.web.getUrl())));
                    return;
                } catch (Exception e4) {
                    e4.printStackTrace();
                    return;
                }
            default:
                return;
        }
    }

    /* loaded from: classes2.dex */
    public class MyWebViewDownLoadListener implements DownloadListener {
        private MyWebViewDownLoadListener() {
            WebKitLocalActivity.this = r1;
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            MyLog.w("url=" + url);
            WebKitLocalActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
        }
    }

    public boolean parseScheme(String url) {
        if (url.contains("platformapi/startapp")) {
            this.myHandler.removeCallbacks(this.runable);
            return true;
        } else if (!url.contains("web-other")) {
            return false;
        } else {
            this.myHandler.postDelayed(this.runable, 2000L);
            return false;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.top_imgBack /* 2131755207 */:
                goBack();
                return;
            case R.id.web_imgEmpty /* 2131755415 */:
                this.web.reload();
                return;
            case R.id.top_imgMenu /* 2131755851 */:
                showMenu();
                return;
            case R.id.top_imgClose /* 2131755854 */:
                finish();
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRefresh() {
        this.web.reload();
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onLoadMore(boolean isSilence) {
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRelease(float direction) {
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onHeaderMove(double headerMovePercent, int offsetY) {
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_CIRCLES_PUBLIC /* 2131296309 */:
                MyToast.show(this.context, (int) R.string.share_circle_success);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.camera != null) {
            this.camera.onActivityResult(requestCode, resultCode, data);
        }
        if (requestCode == 10103 || requestCode == 10104) {
            Tencent.onActivityResultData(requestCode, resultCode, data, this.shareUtils.getListener());
        }
    }

    public void showPictureDlg() {
        if (this.camera == null) {
            this.camera = new CameraUtil(this, this);
        }
        if (this.pictureDlg == null) {
            this.pictureDlg = new PictureDialog(this.context);
            this.pictureDlg.init(this);
        }
        this.pictureDlg.show();
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.isOpenWx) {
            this.isOpenWx = false;
            getClient().dialogDismiss();
            MyLog.d("PayAct:errCode=" + MyApplication.wxErrCode);
            vsPayResultFromApp(MyApplication.wxErrCode + "", "weixin");
            if (MyApplication.wxErrCode == 0) {
                MyApplication.wxErrCode = 1;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.web != null) {
            if (this.xrv_wv != null) {
                this.xrv_wv.removeView(this.web);
            }
            this.web.stopLoading();
            this.web.getSettings().setJavaScriptEnabled(false);
            this.web.clearHistory();
            this.web.clearView();
            this.web.removeAllViews();
            try {
                this.web.destroy();
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }

    public String vsf2fUrl(String url) {
        if (url.startsWith("vsf2f://login")) {
            if (!isNoLogin()) {
                return "http://vjkeji.com/public/login.mobile";
            }
            startAct(LoginActivity.class);
            return "";
        } else if (url.startsWith("vsf2f://close")) {
            finish();
            return "";
        } else if (url.startsWith("vsf2f://joinus#")) {
            if (isNoLogin()) {
                String guid = url.substring(url.indexOf("#") + 1);
                Bundle bundle = new Bundle();
                bundle.putString(com.vsf2f.f2f.ui.utils.Constant.USER_GUID, guid);
                startAct(RegistActivity.class, bundle);
            } else {
                MyToast.show(this.context, "您已登录，无需注册");
            }
            return "";
        } else if (url.startsWith("vsf2f://addfriend#")) {
            String username = url.substring(url.indexOf("#") + 1);
            Bundle bundle2 = new Bundle();
            bundle2.putString("username", username);
            startAct(UserProfileActivity.class, bundle2);
            return "";
        } else if (!url.startsWith("vsf2f://openauthority")) {
            return url;
        } else {
            startAct(UserVipActivity.class);
            return "";
        }
    }
}

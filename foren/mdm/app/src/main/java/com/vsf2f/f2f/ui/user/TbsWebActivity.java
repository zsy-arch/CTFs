package com.vsf2f.f2f.ui.user;

import android.content.Intent;
import android.media.MediaPlayer;
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
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.common.MyHttpClient;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.ShareUtils;
import com.cdlinglu.utils.x5WebView.MyWebViewClient;
import com.cdlinglu.utils.x5WebView.WebViewJavaScriptFunction;
import com.cdlinglu.utils.x5WebView.X5WebView;
import com.em.DemoHelper;
import com.em.ui.UserProfileActivity;
import com.em.utils.UserShared;
import com.google.gson.Gson;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.IMyHttpListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.MessageEncoder;
import com.tencent.open.SocialConstants;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.tauth.Tencent;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ShareThirdBean;
import com.vsf2f.f2f.bean.WebInitBean;
import com.vsf2f.f2f.ui.dialog.ImgLoadDialog;
import com.vsf2f.f2f.ui.dialog.MenuWeb2Dialog;
import com.vsf2f.f2f.ui.dialog.ShareWebDialog;
import com.vsf2f.f2f.ui.user.pwd.LoginActivity;
import com.vsf2f.f2f.ui.user.pwd.RegistActivity;
import com.vsf2f.f2f.ui.utils.GameUtil;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import com.yolanda.nohttp.Headers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpHost;
import org.json.JSONException;
import org.json.JSONObject;
import u.aly.av;

/* loaded from: classes2.dex */
public class TbsWebActivity extends BaseActivity implements MenuWeb2Dialog.WebMenuListener {
    private RelativeLayout _parent;
    private ProgressBar bar;
    private Handler handler;
    private ImageView imgBack;
    private ImageView imgClose;
    private FrameLayout imgLoad;
    private ImageView imgMenu;
    private String lastApi;
    private int lastRawId;
    private String mainUrl;
    private List<WebInitBean.MoreMenusBean> moreMenus;
    private MediaPlayer player;
    private ShareWebDialog shareDialog;
    private ShareUtils shareUtils;
    private X5WebView x5web;
    private boolean loadMain = true;
    private boolean autoTitle = true;
    private Handler myHandler = new Handler();
    private Runnable runable = new Runnable() { // from class: com.vsf2f.f2f.ui.user.TbsWebActivity.8
        @Override // java.lang.Runnable
        public void run() {
            TbsWebActivity.this.x5web.loadUrl("");
        }
    };

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_web;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(67108864);
        }
        Bundle bundle = getBundle();
        if (bundle == null || !bundle.containsKey(Constant.FLAG)) {
            finish();
            return;
        }
        this.imgLoad = (FrameLayout) getView(R.id.web_imgLoad);
        this.imgMenu = (ImageView) getViewAndClick(R.id.top_imgMenu);
        this.imgBack = (ImageView) getViewAndClick(R.id.top_imgBack);
        this.imgClose = (ImageView) getViewAndClick(R.id.top_imgClose);
        this.player = new MediaPlayer();
        this.shareUtils = new ShareUtils(this.context);
        String title = bundle.getString(Constant.FLAG_TITLE);
        this.mainUrl = getBundle().getString(Constant.FLAG);
        if (!bundle.getBoolean("isLoadGame")) {
            removeLoadView();
        }
        if (bundle.getBoolean("isSign")) {
            this.mainUrl = WebUtils.getTokenUrl(this.context, this.mainUrl);
        }
        if (bundle.getBoolean("isMore")) {
            this.imgMenu.setVisibility(0);
        }
        if (bundle.getBoolean("isClose", true)) {
            this.imgClose.setVisibility(0);
        }
        if (bundle.getBoolean("isBackup")) {
            this.imgBack.setVisibility(0);
        }
        this.x5web = new X5WebView(this.context);
        this.x5web.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this._parent = (RelativeLayout) findViewById(R.id.web_view_container);
        this._parent.removeAllViews();
        this._parent.addView(this.x5web);
        if (TextUtils.isEmpty(this.mainUrl)) {
            setTitle("空白页");
        } else if (TextUtils.isEmpty(title) || com.vsf2f.f2f.ui.utils.Constant.FLAG_AUTO_TITLE.equals(title)) {
            this.autoTitle = true;
        } else if (com.vsf2f.f2f.ui.utils.Constant.FLAG_NO_TITLE.equals(title)) {
            hideHeader();
        } else {
            setTitle(title);
        }
        this.x5web.loadUrl(this.mainUrl);
        MyLog.e("url=" + this.mainUrl);
        this.bar = (ProgressBar) getView(R.id.web_pbBar);
        if (this.handler == null) {
            this.handler = new Handler(new Handler.Callback() { // from class: com.vsf2f.f2f.ui.user.TbsWebActivity.1
                @Override // android.os.Handler.Callback
                public boolean handleMessage(Message msg) {
                    if (TbsWebActivity.this.bar != null) {
                        if (msg.what == 100) {
                            TbsWebActivity.this.bar.setVisibility(8);
                        } else {
                            TbsWebActivity.this.bar.setVisibility(0);
                            TbsWebActivity.this.bar.setProgress(msg.what);
                        }
                    }
                    return false;
                }
            });
        }
    }

    public void removeLoadView() {
        if (this.imgLoad != null) {
            this.imgLoad.setVisibility(8);
            FrameLayout faceParent = (FrameLayout) getView(R.id.web_faceParent);
            if (faceParent != null) {
                faceParent.removeView(this.imgLoad);
            }
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.x5web.setWebViewClient(new MyWebViewClient() { // from class: com.vsf2f.f2f.ui.user.TbsWebActivity.2
            @Override // com.cdlinglu.utils.x5WebView.MyWebViewClient, com.tencent.smtt.sdk.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                super.shouldOverrideUrlLoading(view, url);
                MyLog.d("urlWeb====>", url);
                if (url.endsWith("goback2app")) {
                    TbsWebActivity.this.finish();
                }
                if (url.startsWith("weixin://")) {
                    try {
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        intent.setData(Uri.parse(url));
                        TbsWebActivity.this.startActivity(intent);
                        return true;
                    } catch (Exception e) {
                        MyToast.show(TbsWebActivity.this.context, (int) R.string.no_install_wx);
                        if (TbsWebActivity.this.x5web == null || !TbsWebActivity.this.x5web.canGoBack()) {
                            return true;
                        }
                        TbsWebActivity.this.x5web.goBack();
                        return true;
                    }
                } else if (url.contains("alipay")) {
                    if (TbsWebActivity.this.parseScheme(url)) {
                        try {
                            Intent intent2 = Intent.parseUri(Uri.parse(url).toString(), 1);
                            intent2.addCategory("android.intent.category.BROWSABLE");
                            intent2.setComponent(null);
                            TbsWebActivity.this.startActivity(intent2);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    return false;
                } else if (url.startsWith(WebView.SCHEME_TEL)) {
                    int flag = url.indexOf(":");
                    Intent intent3 = new Intent("android.intent.action.CALL", Uri.parse(WebView.SCHEME_TEL + Long.parseLong(url.substring(flag + 1, flag + 11))));
                    if (ActivityCompat.checkSelfPermission(TbsWebActivity.this.context, "android.permission.CALL_PHONE") != 0) {
                        return false;
                    }
                    TbsWebActivity.this.startActivity(intent3);
                    return true;
                } else if (url.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                    return false;
                } else {
                    if (!url.startsWith("vsf2f")) {
                        return true;
                    }
                    String newurl = TbsWebActivity.this.vsf2fUrl(url);
                    if (!TextUtils.isEmpty(newurl)) {
                        return true;
                    }
                    view.loadUrl(newurl);
                    return true;
                }
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onPageFinished(WebView view, String url) {
                TbsWebActivity.this.setWebTitle(view.getTitle());
                TbsWebActivity.this.imgLoad.postDelayed(new Runnable() { // from class: com.vsf2f.f2f.ui.user.TbsWebActivity.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        TbsWebActivity.this.removeLoadView();
                    }
                }, 2000L);
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                super.onReceivedError(webView, i, s, s1);
                MyLog.e("onReceivedError=" + s + "," + s1);
            }
        });
        this.x5web.setWebChromeClient(new WebChromeClient() { // from class: com.vsf2f.f2f.ui.user.TbsWebActivity.3
            @Override // com.tencent.smtt.sdk.WebChromeClient
            public void onProgressChanged(WebView view, int newProgress) {
                if (TbsWebActivity.this.handler != null) {
                    TbsWebActivity.this.handler.sendEmptyMessage(newProgress);
                }
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                return true;
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public void onReceivedTitle(WebView webView, String s) {
                super.onReceivedTitle(webView, s);
                MyLog.e("onReceivedTitle=" + s);
            }
        });
        this.x5web.addJavascriptInterface(new WebViewJavaScriptFunction() { // from class: com.vsf2f.f2f.ui.user.TbsWebActivity.4
            @Override // com.cdlinglu.utils.x5WebView.WebViewJavaScriptFunction
            public void onJsFunctionCalled(String tag) {
            }

            @JavascriptInterface
            public void vsInitWebview(String s) {
                MyLog.e("init", s);
                final WebInitBean webInit = (WebInitBean) new Gson().fromJson(s, (Class<Object>) WebInitBean.class);
                TbsWebActivity.this.myHandler.post(new Runnable() { // from class: com.vsf2f.f2f.ui.user.TbsWebActivity.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (HyUtil.isNoEmpty(webInit.getMoreMenus())) {
                            TbsWebActivity.this.moreMenus = webInit.getMoreMenus();
                            TbsWebActivity.this.imgMenu.setVisibility(0);
                        } else {
                            TbsWebActivity.this.imgMenu.setVisibility(8);
                        }
                        TbsWebActivity.this.setTitle(webInit.getTitle());
                        if (webInit.isIsBackup()) {
                            TbsWebActivity.this.imgBack.setVisibility(0);
                        }
                        if (webInit.isIsClose()) {
                            TbsWebActivity.this.imgClose.setVisibility(0);
                        }
                    }
                });
            }

            @JavascriptInterface
            public void vsOpenWebview(String s) {
                MyToast.show(TbsWebActivity.this.context, "open=" + s);
            }

            @JavascriptInterface
            public String getLoginUserInfoFromApp() {
                return WebUtils.getTokenJson(TbsWebActivity.this);
            }

            @JavascriptInterface
            public String getLoginUserInfoFromApp(String s) {
                return WebUtils.getTokenJson(TbsWebActivity.this);
            }

            @JavascriptInterface
            public void doPostVSMethod(String jsonStr) {
                MyLog.e("doPost=" + jsonStr);
                if (jsonStr != null) {
                    TbsWebActivity.this.doRequsetApi(jsonStr);
                }
            }

            @JavascriptInterface
            public void doVSAppMethod(String jsonStr) {
                MyLog.e("doVSApp=" + jsonStr);
                if (jsonStr != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(jsonStr);
                        String method = jsonObject.optString("method");
                        JSONObject biz = new JSONObject(ComUtil.getUTF(jsonObject.optString("bizContent")));
                        String js = jsonObject.optString("jscallback");
                        char c = 65535;
                        switch (method.hashCode()) {
                            case -2135216808:
                                if (method.equals("opengolddetails")) {
                                    c = 7;
                                    break;
                                }
                                break;
                            case -1894100143:
                                if (method.equals("playMusic")) {
                                    c = 2;
                                    break;
                                }
                                break;
                            case -503632913:
                                if (method.equals("openview")) {
                                    c = 1;
                                    break;
                                }
                                break;
                            case 94756344:
                                if (method.equals(Headers.HEAD_VALUE_CONNECTION_CLOSE)) {
                                    c = 6;
                                    break;
                                }
                                break;
                            case 258538104:
                                if (method.equals("openQRCode")) {
                                    c = 0;
                                    break;
                                }
                                break;
                            case 294916862:
                                if (method.equals("goback2app")) {
                                    c = 5;
                                    break;
                                }
                                break;
                            case 1085444827:
                                if (method.equals("refresh")) {
                                    c = 4;
                                    break;
                                }
                                break;
                            case 1195341721:
                                if (method.equals("invitation")) {
                                    c = 3;
                                    break;
                                }
                                break;
                        }
                        switch (c) {
                            case 0:
                                TbsWebActivity.this.myHandler.post(new Runnable() { // from class: com.vsf2f.f2f.ui.user.TbsWebActivity.4.2
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        TbsWebActivity.this.openQRCode();
                                    }
                                });
                                return;
                            case 1:
                                final String url = biz.optString("url");
                                if (biz.optBoolean("isSign")) {
                                    url = WebUtils.getTokenUrl(TbsWebActivity.this, url);
                                }
                                TbsWebActivity.this.x5web.post(new Runnable() { // from class: com.vsf2f.f2f.ui.user.TbsWebActivity.4.3
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        TbsWebActivity.this.imgClose.setVisibility(8);
                                        TbsWebActivity.this.imgBack.setVisibility(0);
                                        TbsWebActivity.this.x5web.loadUrl(url);
                                        TbsWebActivity.this.loadMain = false;
                                    }
                                });
                                return;
                            case 2:
                                String type = biz.optString("type");
                                biz.optString("playTimes");
                                int rawId = 0;
                                char c2 = 65535;
                                switch (type.hashCode()) {
                                    case 99644:
                                        if (type.equals("dog")) {
                                            c2 = 0;
                                            break;
                                        }
                                        break;
                                    case 103315:
                                        if (type.equals("hit")) {
                                            c2 = 3;
                                            break;
                                        }
                                        break;
                                    case 3059345:
                                        if (type.equals("coin")) {
                                            c2 = 2;
                                            break;
                                        }
                                        break;
                                    case 547301227:
                                        if (type.equals("watering")) {
                                            c2 = 4;
                                            break;
                                        }
                                        break;
                                    case 1028980589:
                                        if (type.equals("fertilizing")) {
                                            c2 = 6;
                                            break;
                                        }
                                        break;
                                    case 1032299505:
                                        if (type.equals("cricket")) {
                                            c2 = 1;
                                            break;
                                        }
                                        break;
                                    case 1456250255:
                                        if (type.equals("catalyzer")) {
                                            c2 = 5;
                                            break;
                                        }
                                        break;
                                }
                                switch (c2) {
                                    case 0:
                                        rawId = R.raw.wangwang;
                                        break;
                                    case 1:
                                        rawId = R.raw.cricket;
                                        break;
                                    case 2:
                                        rawId = R.raw.coins;
                                        break;
                                    case 3:
                                        rawId = R.raw.hit;
                                        break;
                                    case 4:
                                        rawId = R.raw.watering;
                                        break;
                                    case 5:
                                        rawId = R.raw.catalyzer;
                                        break;
                                    case 6:
                                        rawId = R.raw.fertilizing;
                                        break;
                                }
                                if ((!TbsWebActivity.this.player.isPlaying() || TbsWebActivity.this.lastRawId != rawId) && rawId != 0) {
                                    TbsWebActivity.this.lastRawId = rawId;
                                    TbsWebActivity.this.player = MediaPlayer.create(TbsWebActivity.this, rawId);
                                    TbsWebActivity.this.player.setAudioStreamType(3);
                                    TbsWebActivity.this.player.start();
                                    return;
                                }
                                return;
                            case 3:
                                String friendName = biz.optString("friendName");
                                MyLog.e("邀请好友：" + friendName);
                                EMMessage message = EMMessage.createSendMessage(EMMessage.Type.CMD);
                                Map<String, String> map = new HashMap<>();
                                map.put("userName", DemoHelper.getInstance().getCurrentUserNick());
                                map.put("headURL", DemoHelper.getInstance().getCurrentUserPic().getSpath());
                                map.put("detailUrl", TbsWebActivity.this.getString(R.string.API_GAME_HTML));
                                map.put("title", "种钱乐园,浇浇水施施肥，摇一摇钱收不停");
                                map.put(SocialConstants.PARAM_APP_DESC, "我在面对面发现一个好玩赚钱的游戏！快来和我一起玩吧！根本停不下来！");
                                map.put("type", "game");
                                message.setAttribute(MessageEncoder.ATTR_EXT, new JSONObject(map));
                                message.addBody(new EMTextMessageBody("[游戏信息]"));
                                message.setTo(friendName);
                                EMClient.getInstance().chatManager().sendMessage(message);
                                MyToast.show(TbsWebActivity.this.context, "邀请发送成功");
                                final String str = String.format("javascript:VSF2FCallJS.%s('%s')", js, "{\"method\":\"invitation\",\"ret\":\"0\",\"obj\":\"success\"}");
                                MyLog.e("javascript=" + str);
                                if (Build.VERSION.SDK_INT >= 19) {
                                    TbsWebActivity.this.x5web.post(new Runnable() { // from class: com.vsf2f.f2f.ui.user.TbsWebActivity.4.4
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            TbsWebActivity.this.x5web.evaluateJavascript(str, new ValueCallback<String>() { // from class: com.vsf2f.f2f.ui.user.TbsWebActivity.4.4.1
                                                public void onReceiveValue(String result) {
                                                }
                                            });
                                        }
                                    });
                                    return;
                                }
                                return;
                            case 4:
                                TbsWebActivity.this.x5web.reload();
                                return;
                            case 5:
                            case 6:
                                TbsWebActivity.this.finish();
                                return;
                            case 7:
                                String type2 = biz.optString("type");
                                Bundle bundle = new Bundle();
                                if ("gold".equals(type2)) {
                                    bundle.putInt("type", 0);
                                } else if ("goldbean".equals(type2)) {
                                    bundle.putInt("type", 1);
                                }
                                TbsWebActivity.this.startAct(MyBeansInfoActivity.class, bundle);
                                return;
                            default:
                                return;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Android");
    }

    public void doRequsetApi(final String jsonStr) {
        if (!HyUtil.isNetworkConnected(this.context)) {
            MyToast.show(this.context, "请连接网络！");
        }
        try {
            final String js = new JSONObject(jsonStr).optString("jscallback", "doPostVSMethod");
            new MyHttpClient(this.context, new IMyHttpListener() { // from class: com.vsf2f.f2f.ui.user.TbsWebActivity.5
                @Override // com.hy.http.IMyHttpListener
                public void onRequestSuccess(ResultInfo result) {
                    String str = String.format("javascript:VSF2FCallJS.%s('%s')", js, result.getJson());
                    MyLog.e("javascript=" + str);
                    if (Build.VERSION.SDK_INT >= 19 && TbsWebActivity.this.x5web != null) {
                        TbsWebActivity.this.x5web.evaluateJavascript(str, null);
                    }
                }

                @Override // com.hy.http.IMyHttpListener
                public void onRequestError(ResultInfo result) {
                    if (result == null) {
                        MyToast.show(TbsWebActivity.this.context, (int) R.string.not_connect_need_click);
                        return;
                    }
                    String error = result.getError();
                    if (!TextUtils.isEmpty(error)) {
                        if ("request_timeout_fail".equals(error)) {
                            long serviceTime = Long.parseLong((String) result.getObj());
                            long cusTime = System.currentTimeMillis();
                            long diff = cusTime - serviceTime;
                            MyLog.e("diffTime=" + cusTime + "-" + serviceTime + "=" + diff);
                            AppShare.get(TbsWebActivity.this.context).putLong("diffTime", diff);
                            TbsWebActivity.this.doRequsetApi(jsonStr);
                            return;
                        } else if ("unlogin_error".equals(error)) {
                            DemoHelper.getInstance().onConnectionConflict();
                            return;
                        }
                    }
                    String str = String.format("javascript:VSF2FCallJS.%s('%s')", js, result.getJson());
                    MyLog.e("javascript=" + str);
                    if (Build.VERSION.SDK_INT >= 19 && TbsWebActivity.this.x5web != null) {
                        try {
                            TbsWebActivity.this.x5web.loadUrl(str);
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).post(R.string.API_GAME_POST, ComUtil.getGAMEApi(this.context, getString(R.string.API_GAME_POST)), GameUtil.getVsSign(jsonStr), null, String.class, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setWebTitle(String title) {
        if (this.autoTitle) {
            setTitle(title);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void request() {
        final String str = String.format("javascript:doVSWapMethod('%s')", "{\"method\":\"prevwebview\",\"ret\":\"0\",\"obj\":\"success\"}");
        if (this.x5web != null && Build.VERSION.SDK_INT >= 19) {
            this.x5web.post(new Runnable() { // from class: com.vsf2f.f2f.ui.user.TbsWebActivity.6
                @Override // java.lang.Runnable
                public void run() {
                    if (TbsWebActivity.this.x5web != null) {
                        TbsWebActivity.this.x5web.evaluateJavascript(str, new ValueCallback<String>() { // from class: com.vsf2f.f2f.ui.user.TbsWebActivity.6.1
                            public void onReceiveValue(String result) {
                            }
                        });
                    }
                }
            });
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        request();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.top_imgBack /* 2131755207 */:
                goBack();
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

    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4 || event.getAction() != 0) {
            return false;
        }
        goBack();
        return false;
    }

    public void goBack() {
        if (!this.loadMain) {
            this.loadMain = true;
            this.x5web.clearHistory();
            this.x5web.loadUrl(this.mainUrl);
            this.imgBack.setVisibility(8);
            this.imgClose.setVisibility(0);
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10103 || requestCode == 10104) {
            Tencent.onActivityResultData(requestCode, resultCode, data, this.shareUtils.getListener());
        }
    }

    private void showMenu() {
        MyLog.e(this.moreMenus);
        if (HyUtil.isNoEmpty(this.moreMenus)) {
            new MenuWeb2Dialog(this.context, this.moreMenus, this).showAsDropDown(this.imgMenu);
        }
    }

    private void showForwardDialog() {
        ShareThirdBean shareBean;
        String share = DemoHelper.getInstance().readConfig().getGame_ext_url();
        try {
        } catch (JSONException e) {
            shareBean = new ShareThirdBean("种钱乐园,浇浇水施施肥，摇一摇钱收不停", getString(R.string.url_invite_game, new Object[]{getUserInfo().getGuid()}), "http://apps.vjkeji.com/android/icon_tree.png");
            shareBean.setContext("我在面对面发现一个好玩赚钱的游戏！快来和我一起玩吧！根本停不下来！");
        }
        if (TextUtils.isEmpty(share)) {
            throw new JSONException("");
        }
        JSONObject shareObject = new JSONObject(share);
        shareBean = new ShareThirdBean(shareObject.optString("title"), shareObject.optString("url") + "?guid=" + getUserInfo().getGuid(), shareObject.optString("pic"));
        shareBean.setContext(shareObject.optString(av.aJ));
        this.shareUtils.setShare(shareBean);
        if (this.shareDialog == null) {
            this.shareDialog = new ShareWebDialog(this.context);
            this.shareDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.user.TbsWebActivity.7
                @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                public void onDlgConfirm(BaseDialog dlg, int flag) {
                    switch (flag) {
                        case 11:
                            TbsWebActivity.this.shareUtils.shareTask(1);
                            return;
                        case 12:
                            TbsWebActivity.this.shareUtils.shareTask(0);
                            return;
                        case 13:
                            TbsWebActivity.this.shareUtils.qqShare();
                            return;
                        case 14:
                            TbsWebActivity.this.shareUtils.qqZoneShare();
                            return;
                        default:
                            return;
                    }
                }
            });
        }
        this.shareDialog.show();
    }

    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this._parent.removeView(this.x5web);
        if (this.player != null) {
            this.player.release();
        }
        if (this.x5web != null) {
            this.x5web.clear();
            this.x5web = null;
        }
        super.onDestroy();
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
        } else if (!url.startsWith("vsf2f://addfriend#")) {
            return url;
        } else {
            String username = url.substring(url.indexOf("#") + 1);
            Bundle bundle2 = new Bundle();
            bundle2.putString("username", username);
            startAct(UserProfileActivity.class, bundle2);
            return "";
        }
    }

    public boolean parseScheme(String url) {
        if (url.contains("platformapi/startapp")) {
            this.myHandler.removeCallbacks(this.runable);
            return true;
        }
        if (url.contains("web-other")) {
            this.myHandler.postDelayed(this.runable, 2000L);
        }
        return false;
    }

    @Override // com.vsf2f.f2f.ui.dialog.MenuWeb2Dialog.WebMenuListener
    public void onMenuSelect(String flag, String url) {
        char c = 65535;
        switch (flag.hashCode()) {
            case -1898171474:
                if (flag.equals("QRCode")) {
                    c = 1;
                    break;
                }
                break;
            case 3198785:
                if (flag.equals("help")) {
                    c = 4;
                    break;
                }
                break;
            case 109400031:
                if (flag.equals("share")) {
                    c = 2;
                    break;
                }
                break;
            case 1085444827:
                if (flag.equals("refresh")) {
                    c = 0;
                    break;
                }
                break;
            case 1787798387:
                if (flag.equals("strategy")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.x5web.reload();
                return;
            case 1:
                openQRCode();
                return;
            case 2:
                showForwardDialog();
                return;
            case 3:
            case 4:
                this.x5web.loadUrl(url);
                this.loadMain = false;
                return;
            default:
                if (!TextUtils.isEmpty(url)) {
                    this.x5web.loadUrl(url);
                    this.loadMain = false;
                    return;
                }
                return;
        }
    }

    public void openQRCode() {
        String codeUrl = getGameCode();
        ShareThirdBean shareBean = new ShareThirdBean("种钱乐园,浇浇水施施肥，摇一摇钱收不停", getString(R.string.url_invite_game, new Object[]{UserShared.getInstance().getCurrentGuId()}), codeUrl);
        shareBean.setContext("我在面对面发现一个好玩赚钱的游戏！快来和我一起玩吧！根本停不下来！");
        this.shareUtils.setShare(shareBean);
        ImgLoadDialog imgLoadDialog = new ImgLoadDialog(this.context, codeUrl);
        imgLoadDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.user.TbsWebActivity.9
            @Override // com.hy.frame.common.BaseDialog.IConfirmListener
            public void onDlgConfirm(BaseDialog dlg, int flag) {
                ImgLoadDialog viewDialog = (ImgLoadDialog) dlg;
                switch (flag) {
                    case 11:
                        TbsWebActivity.this.shareUtils.wxSharePic(1, viewDialog.getBitmap());
                        return;
                    case 12:
                        TbsWebActivity.this.shareUtils.wxSharePic(0, viewDialog.getBitmap());
                        return;
                    case 13:
                        TbsWebActivity.this.shareUtils.qqSharePic(viewDialog.getLocalUrl());
                        return;
                    case 14:
                        TbsWebActivity.this.shareUtils.qqZoneSharePic();
                        return;
                    default:
                        return;
                }
            }
        });
        imgLoadDialog.show();
    }

    private String getGameCode() {
        String logo = "";
        if (getUserInfo(true).getUserPic().getSpath() != null) {
            logo = "&logo=true";
        }
        return ComUtil.getZCApi(this.context, getString(R.string.API_GAME_QRCODE, new Object[]{DemoHelper.getInstance().getCurrentUserName() + logo}));
    }
}

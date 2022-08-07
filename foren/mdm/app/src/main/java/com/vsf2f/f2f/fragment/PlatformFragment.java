package com.vsf2f.f2f.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.alipay.sdk.util.j;
import com.cdlinglu.common.BaseFragment;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.x5WebView.WebViewJavaScriptFunction;
import com.easeui.EaseConstant;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.em.ui.ChatActivity;
import com.em.ui.UserProfileActivity;
import com.em.utils.UserShared;
import com.google.gson.Gson;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.recycler.callback.XRefreshViewListener;
import com.hy.frame.view.recycler.xRefreshView.XWebView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ChatUserBean;
import com.vsf2f.f2f.ui.otay.ManorTreeDialog;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.user.pwd.LoginActivity;
import com.vsf2f.f2f.ui.user.pwd.RegistActivity;
import com.vsf2f.f2f.ui.utils.GameUtil;
import com.vsf2f.f2f.ui.utils.web.WebUtils;
import org.apache.http.HttpHost;
import u.aly.av;

/* loaded from: classes2.dex */
public class PlatformFragment extends BaseFragment implements XRefreshViewListener {
    private ProgressBar bar;
    private ImageView empty;
    private Handler handler;
    private boolean loadError;
    private ViewGroup.LayoutParams webParams = new ViewGroup.LayoutParams(-1, -1);
    private XWebView webPublic;
    private LinearLayout xrv_wv;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_main_plat_fragment;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        this.bar = (ProgressBar) getView(R.id.web_pbBar);
        this.webPublic = new XWebView(getContext());
        this.webPublic.setLayoutParams(this.webParams);
        this.xrv_wv = (LinearLayout) getView(R.id.xrv_wv);
        this.xrv_wv.addView(this.webPublic);
        this.empty = (ImageView) getViewAndClick(R.id.web_imgEmpty);
        if (this.handler == null) {
            this.handler = new Handler(new Handler.Callback() { // from class: com.vsf2f.f2f.fragment.PlatformFragment.1
                @Override // android.os.Handler.Callback
                public boolean handleMessage(Message msg) {
                    if (PlatformFragment.this.bar != null) {
                        if (msg.what == 100) {
                            PlatformFragment.this.bar.setVisibility(8);
                        } else {
                            PlatformFragment.this.bar.setVisibility(0);
                            PlatformFragment.this.bar.setProgress(msg.what);
                        }
                    }
                    return false;
                }
            });
        }
        setOnClickListener(R.id.discover_imgTree);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        initWeb(this.webPublic);
        loadWeb();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void loadWeb() {
        if (this.webPublic != null) {
            String url = DemoHelper.getInstance().readConfig().getDzpt_url();
            if (url == null) {
                url = ComUtil.getZCApi(this.context, getString(R.string.URL_PLATFORM));
            }
            this.webPublic.loadUrl(url);
            MyLog.e(url);
        }
    }

    public void initWeb(XWebView webLayout) {
        webLayout.setVerticalScrollBarEnabled(false);
        webLayout.setHorizontalScrollBarEnabled(false);
        webLayout.removeJavascriptInterface("searchBoxJavaBridge_");
        webLayout.removeJavascriptInterface("accessibility");
        webLayout.removeJavascriptInterface("accessibilityTraversal");
        WebSettings settings = webLayout.getSettings();
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
        webLayout.setInitialScale(10);
        webLayout.setWebViewClient(new WebViewClient() { // from class: com.vsf2f.f2f.fragment.PlatformFragment.2
            boolean MDZZ = true;

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                super.shouldOverrideUrlLoading(view, url);
                MyLog.d("webUrl==>", url);
                if (url.substring(0, 4).equals(HttpHost.DEFAULT_SCHEME_NAME)) {
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.FLAG, url);
                    bundle.putBoolean(Constant.FLAG2, true);
                    bundle.putString(Constant.FLAG_TITLE, com.vsf2f.f2f.ui.utils.Constant.FLAG_AUTO_TITLE);
                    PlatformFragment.this.startAct(WebKitLocalActivity.class, bundle);
                    return true;
                } else if (!url.startsWith("vsf2f")) {
                    return false;
                } else {
                    if (PlatformFragment.this.isNoLogin()) {
                        PlatformFragment.this.startActivity(new Intent(PlatformFragment.this.getContext(), LoginActivity.class));
                        return true;
                    }
                    String newurl = PlatformFragment.this.vsf2fUrl(url);
                    if (TextUtils.isEmpty(newurl)) {
                        return true;
                    }
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(Constant.FLAG_TITLE, com.vsf2f.f2f.ui.utils.Constant.FLAG_AUTO_TITLE);
                    bundle2.putString(Constant.FLAG, newurl);
                    PlatformFragment.this.startAct(WebKitLocalActivity.class, bundle2);
                    return true;
                }
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                if (PlatformFragment.this.loadError) {
                    PlatformFragment.this.loadError = false;
                    PlatformFragment.this.empty.setVisibility(0);
                    return;
                }
                PlatformFragment.this.empty.setVisibility(8);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                PlatformFragment.this.loadError = true;
            }
        });
        webLayout.setWebChromeClient(new WebChromeClient() { // from class: com.vsf2f.f2f.fragment.PlatformFragment.3
            @Override // android.webkit.WebChromeClient
            public boolean onJsAlert(WebView webView, String s, String s1, JsResult jsResult) {
                return super.onJsAlert(webView, s, s1, jsResult);
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsPrompt(WebView webView, String url, String message, String defaultValue, JsPromptResult jsPromptResult) {
                if (message.equals("1")) {
                }
                jsPromptResult.confirm(j.c);
                return true;
            }

            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView view, int newProgress) {
                if (PlatformFragment.this.handler != null) {
                    PlatformFragment.this.handler.sendEmptyMessage(newProgress);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onReceivedTitle(WebView view, String title) {
                if (!TextUtils.isEmpty(title) && title.toLowerCase().contains(av.aG)) {
                    PlatformFragment.this.loadError = true;
                }
            }
        });
        webLayout.addJavascriptInterface(new WebViewJavaScriptFunction() { // from class: com.vsf2f.f2f.fragment.PlatformFragment.4
            @Override // com.cdlinglu.utils.x5WebView.WebViewJavaScriptFunction
            @JavascriptInterface
            public void onJsFunctionCalled(String tag) {
            }

            @JavascriptInterface
            public String getLoginUserInfoFromApp(String s) {
                return WebUtils.getTokenJson(PlatformFragment.this.getContext());
            }

            @JavascriptInterface
            public void openChatPanel(String s) {
                if (s != null) {
                    ChatUserBean bean = new ChatUserBean();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean(EaseConstant.BACK_TYPE, false);
                    bundle.putString("username", ((ChatUserBean) new Gson().fromJson(s, (Class<Object>) bean.getClass())).getUser());
                    PlatformFragment.this.startAct(ChatActivity.class, bundle);
                    return;
                }
                Toast.makeText(PlatformFragment.this.getContext(), "发起聊天失败", 0).show();
            }
        }, "Android");
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.discover_imgTree /* 2131755301 */:
                if (isNoLogin()) {
                    startAct(LoginActivity.class);
                    return;
                } else if (UserShared.getInstance().getInt(com.vsf2f.f2f.ui.utils.Constant.CERT_MOBILE) != 1) {
                    new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.fragment.PlatformFragment.5
                        @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                        public void onResult(boolean confirmed, Bundle bundle) {
                            if (confirmed) {
                                PlatformFragment.this.startAct(BindPhoneActivity.class);
                            }
                        }
                    }, true).show();
                    return;
                } else if (UserShared.getInstance().isOpenManor()) {
                    GameUtil.startGame(this.context);
                    return;
                } else {
                    new ManorTreeDialog(this.context).show();
                    return;
                }
            case R.id.web_imgEmpty /* 2131755415 */:
                this.webPublic.reload();
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IFragmentListener
    public void sendMsg(int i, Object o) {
    }

    public String vsf2fUrl(String url) {
        if (url.startsWith("vsf2f://login")) {
            if (!isNoLogin()) {
                return "http://vjkeji.com/public/login.mobile";
            }
            startAct(LoginActivity.class);
            return "";
        } else if (url.startsWith("vsf2f://close")) {
            return "";
        } else {
            if (url.startsWith("vsf2f://joinus#")) {
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
    }

    @Override // com.hy.frame.view.recycler.callback.XRefreshViewListener
    public void onRefresh() {
        this.webPublic.reload();
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
}

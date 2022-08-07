package com.cdlinglu.utils.x5WebView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.cdlinglu.utils.ComUtil;
import com.hy.http.HttpEntity;
import com.hyphenate.util.HanziToPinyin;
import com.tencent.smtt.export.external.interfaces.HttpAuthHandler;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.vsf2f.f2f.R;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class X5WebView extends WebView {
    private static boolean isSmallWebViewDisplayed = false;
    private float beginY;
    private WebChromeClient chromeClient;
    private WebViewClient client;
    private float distanceY;
    private boolean isClampedY;
    private boolean isTop;
    private Map<String, Object> mJsBridges;
    private OnTopListener onTopListener;

    /* loaded from: classes.dex */
    public interface OnTopListener {
        void isTop(boolean z);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public X5WebView(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
        this.isTop = true;
        this.isClampedY = false;
        this.client = new WebViewClient() { // from class: com.cdlinglu.utils.x5WebView.X5WebView.1
            @Override // com.tencent.smtt.sdk.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onReceivedHttpAuthRequest(WebView webview, HttpAuthHandler httpAuthHandlerhost, String host, String realm) {
                httpAuthHandlerhost.useHttpAuthUsernamePassword();
            }
        };
        this.chromeClient = new WebChromeClient() { // from class: com.cdlinglu.utils.x5WebView.X5WebView.2
            IX5WebChromeClient.CustomViewCallback callback;
            View myNormalView;
            View myVideoView;

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public boolean onJsConfirm(WebView arg02, String arg12, String arg2, JsResult arg3) {
                return super.onJsConfirm(arg02, arg12, arg2, arg3);
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
                FrameLayout normalView = (FrameLayout) ((Activity) X5WebView.this.getContext()).findViewById(R.id.web_filechooser);
                ViewGroup viewGroup = (ViewGroup) normalView.getParent();
                viewGroup.removeView(normalView);
                viewGroup.addView(view);
                this.myVideoView = view;
                this.myNormalView = normalView;
                this.callback = customViewCallback;
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public void onHideCustomView() {
                if (this.callback != null) {
                    this.callback.onCustomViewHidden();
                    this.callback = null;
                }
                if (this.myVideoView != null) {
                    ViewGroup viewGroup = (ViewGroup) this.myVideoView.getParent();
                    viewGroup.removeView(this.myVideoView);
                    viewGroup.addView(this.myNormalView);
                }
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public boolean onShowFileChooser(WebView arg02, ValueCallback<Uri[]> arg12, WebChromeClient.FileChooserParams arg2) {
                Log.e("app", "onShowFileChooser");
                return super.onShowFileChooser(arg02, arg12, arg2);
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public void openFileChooser(ValueCallback<Uri> uploadFile, String acceptType, String captureType) {
                Log.e("app", "openFileChooser");
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType(HttpEntity.WILDCARD);
                intent.addCategory("android.intent.category.OPENABLE");
                try {
                    ((Activity) X5WebView.this.getContext()).startActivityForResult(Intent.createChooser(intent, "choose files"), 1);
                } catch (ActivityNotFoundException ex) {
                    ex.printStackTrace();
                }
                super.openFileChooser(uploadFile, acceptType, captureType);
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public boolean onCreateWindow(WebView arg02, boolean arg12, boolean arg2, Message msg) {
                if (!X5WebView.isSmallWebViewDisplayed) {
                    return true;
                }
                WebView webView = new WebView(X5WebView.this.getContext()) { // from class: com.cdlinglu.utils.x5WebView.X5WebView.2.1
                    @Override // android.view.View
                    protected void onDraw(Canvas canvas) {
                        super.onDraw(canvas);
                        Paint paint = new Paint();
                        paint.setColor(-16711936);
                        paint.setTextSize(15.0f);
                        canvas.drawText("新建窗口", 10.0f, 10.0f, paint);
                    }
                };
                webView.setWebViewClient(new WebViewClient() { // from class: com.cdlinglu.utils.x5WebView.X5WebView.2.2
                    @Override // com.tencent.smtt.sdk.WebViewClient
                    public boolean shouldOverrideUrlLoading(WebView arg03, String arg13) {
                        arg03.loadUrl(arg13);
                        return true;
                    }
                });
                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(400, 600);
                lp.gravity = 17;
                X5WebView.this.addView(webView, lp);
                ((WebView.WebViewTransport) msg.obj).setWebView(webView);
                msg.sendToTarget();
                return true;
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public boolean onJsAlert(WebView arg02, String arg12, String arg2, JsResult arg3) {
                Log.i("yuanhaizhou", "setX5webview = null");
                return super.onJsAlert(null, "www.baidu.com", "aa", arg3);
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public boolean onJsPrompt(WebView arg02, String arg12, String arg2, String arg3, JsPromptResult arg4) {
                return X5WebView.this.isMsgPrompt(arg12) ? X5WebView.this.onJsPrompt(arg2, arg3) : super.onJsPrompt(arg02, arg12, arg2, arg3, arg4);
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public void onReceivedTitle(WebView arg02, String arg12) {
                super.onReceivedTitle(arg02, arg12);
                Log.i("yuanhaizhou", "webpage title is " + arg12);
            }
        };
        setWebViewClientExtension(new X5WebViewEventHandler(this));
        setWebViewClient(this.client);
        initWebViewSettings();
        getView().setClickable(true);
        getView().setOnTouchListener(new View.OnTouchListener() { // from class: com.cdlinglu.utils.x5WebView.X5WebView.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    private void initWebViewSettings() {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setSavePassword(true);
        settings.setCacheMode(-1);
        settings.setAppCacheMaxSize(Long.MAX_VALUE);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportMultipleWindows(true);
        settings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        settings.setUserAgentString(settings.getUserAgentString() + " vsf2fcompany/f2f/" + ComUtil.getVersion(getContext()));
        settings.setGeolocationDatabasePath(getContext().getApplicationContext().getDir("database", 0).getPath());
        settings.setSaveFormData(true);
        removeJavascriptInterface("searchBoxJavaBridge_");
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
    }

    public X5WebView(Context context) {
        this(context, null);
    }

    public static void setSmallWebViewEnabled(boolean enabled) {
        isSmallWebViewDisplayed = enabled;
    }

    public void addJavascriptBridge(SecurityJsBridgeBundle jsBridgeBundle) {
        if (this.mJsBridges == null) {
            this.mJsBridges = new HashMap(5);
        }
        if (jsBridgeBundle != null) {
            this.mJsBridges.put(SecurityJsBridgeBundle.BLOCK + jsBridgeBundle.getJsBlockName() + "-method" + jsBridgeBundle.getMethodName(), jsBridgeBundle);
        }
    }

    public boolean onJsPrompt(String methodName, String blockName) {
        String tag = SecurityJsBridgeBundle.BLOCK + blockName + "-method" + methodName;
        if (this.mJsBridges == null || !this.mJsBridges.containsKey(tag)) {
            return false;
        }
        ((SecurityJsBridgeBundle) this.mJsBridges.get(tag)).onCallMethod();
        return true;
    }

    public boolean isMsgPrompt(String msg) {
        return msg != null && msg.startsWith(SecurityJsBridgeBundle.PROMPT_START_OFFSET);
    }

    public boolean tbs_dispatchTouchEvent(MotionEvent ev, View view) {
        boolean r = super.super_dispatchTouchEvent(ev);
        Log.d("Bran", "dispatchTouchEvent " + ev.getAction() + HanziToPinyin.Token.SEPARATOR + r);
        return r;
    }

    public boolean tbs_onInterceptTouchEvent(MotionEvent ev, View view) {
        return super.super_onInterceptTouchEvent(ev);
    }

    public void tbs_onScrollChanged(int l, int t, int oldl, int oldt, View view) {
        this.isTop = t == 0;
        if (this.onTopListener != null) {
            this.onTopListener.isTop(this.isTop);
        }
        super_onScrollChanged(l, t, oldl, oldt);
    }

    public synchronized void tbs_onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY, View view) {
        this.isClampedY = clampedY;
        super_onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    public void tbs_computeScroll(View view) {
        super_computeScroll();
    }

    public synchronized boolean tbs_overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent, View view) {
        return super_overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    public boolean tbs_onTouchEvent(MotionEvent event, View view) {
        switch (event.getAction()) {
            case 0:
                this.beginY = event.getRawY();
                break;
            case 1:
                this.distanceY = ((int) event.getRawY()) - this.beginY;
                break;
        }
        return super_onTouchEvent(event);
    }

    public boolean isTop() {
        return this.isTop;
    }

    public void setOnTopListener(OnTopListener listener) {
        this.onTopListener = listener;
    }

    public void clear() {
        stopLoading();
        clearCache(true);
        clearFormData();
        clearMatches();
        clearSslPreferences();
        clearDisappearingChildren();
        clearView();
        clearAnimation();
        clearHistory();
        loadUrl("about:blank");
        clearView();
        freeMemory();
        removeAllViews();
    }
}

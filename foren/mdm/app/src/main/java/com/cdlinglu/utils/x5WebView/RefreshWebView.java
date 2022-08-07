package com.cdlinglu.utils.x5WebView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.vsf2f.f2f.R;
import u.aly.av;

/* loaded from: classes.dex */
public class RefreshWebView extends RelativeLayout implements View.OnTouchListener {
    public static final int STATUS_PULL_TO_REFRESH = 0;
    public static final int STATUS_REFRESHING = 2;
    public static final int STATUS_REFRESH_FINISHED = 3;
    public static final int STATUS_RELEASE_TO_REFRESH = 1;
    private final String TAG;
    private ProgressBar barUrl;
    private float begin_y;
    private boolean canPull;
    private Context current_context_;
    private int current_status_;
    private String current_url_;
    private Handler handler;
    private RelativeLayout header_;
    private ViewGroup.MarginLayoutParams header_layout_params_;
    private int hide_header_height_;
    private int last_status_;
    private boolean loadError;
    private boolean load_once_;
    private int[] location_array_;
    private float original_location_y_;
    private int touchSlop;
    private View webError;
    private X5WebView web_view_;

    public RefreshWebView(Context aContext, AttributeSet attrs) {
        super(aContext, attrs);
        this.TAG = "WebView";
        this.current_status_ = 3;
        this.canPull = true;
        this.location_array_ = new int[2];
        this.load_once_ = false;
        LayoutInflater.from(aContext).inflate(R.layout.refresh_web_view, this);
        this.current_context_ = aContext;
        initWebView(this.current_context_);
        this.header_ = (RelativeLayout) findViewById(R.id.base_web_view_header_layout);
        this.header_.setVisibility(this.canPull ? 0 : 8);
        this.barUrl = (ProgressBar) findViewById(R.id.base_web_view_progressBar);
        this.webError = findViewById(R.id.base_web_view_imgError);
        this.webError.setOnClickListener(new View.OnClickListener() { // from class: com.cdlinglu.utils.x5WebView.RefreshWebView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                RefreshWebView.this.loadError = false;
                RefreshWebView.this.web_view_.reload();
            }
        });
        if (this.handler == null && Build.VERSION.SDK_INT >= 3) {
            this.handler = new Handler(new Handler.Callback() { // from class: com.cdlinglu.utils.x5WebView.RefreshWebView.2
                @Override // android.os.Handler.Callback
                public boolean handleMessage(Message msg) {
                    if (RefreshWebView.this.barUrl != null) {
                        if (msg.what == 0 || msg.what == 100) {
                            RefreshWebView.this.barUrl.setVisibility(8);
                        } else {
                            RefreshWebView.this.barUrl.setVisibility(0);
                            RefreshWebView.this.barUrl.setProgress(msg.what);
                        }
                    }
                    return false;
                }
            });
        }
        this.touchSlop = ViewConfiguration.get(aContext).getScaledTouchSlop();
    }

    public RefreshWebView(Context aContext, boolean canPull) {
        this(aContext, (AttributeSet) null);
        this.canPull = canPull;
    }

    private void initWebView(Context aContext) {
        this.web_view_ = new X5WebView(aContext);
        this.web_view_.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.web_view_.setOnTouchListener(this);
        ((RelativeLayout) findViewById(R.id.base_web_view_container)).addView(this.web_view_);
        this.web_view_.setWebChromeClient(new WebChromeClient() { // from class: com.cdlinglu.utils.x5WebView.RefreshWebView.3
            @Override // com.tencent.smtt.sdk.WebChromeClient
            public void onProgressChanged(WebView view, int newProgress) {
                if (RefreshWebView.this.handler != null) {
                    RefreshWebView.this.handler.sendEmptyMessage(newProgress);
                }
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public void onReceivedTitle(WebView view, String title) {
                Log.i("WebView", "onReceivedTitle");
                if (!TextUtils.isEmpty(title) && title.toLowerCase().contains(av.aG)) {
                    RefreshWebView.this.loadError = true;
                }
            }
        });
        this.web_view_.setInitialScale(10);
        this.web_view_.setDownloadListener(new MyWebViewDownLoadListener());
    }

    public void loadUrl(String url) {
        if (this.web_view_ == null && this.current_context_ != null) {
            Log.d("WebView", "current web view has not be init, do it now");
            initWebView(this.current_context_);
        }
        if (!TextUtils.isEmpty(url)) {
            this.web_view_.loadUrl(url);
        } else {
            Log.e("WebView", "Url navigating is NULL or empty");
        }
    }

    public void setWebViewClient(final WebViewClient myWebViewClient) {
        this.web_view_.setWebViewClient(new XMyWebViewClient() { // from class: com.cdlinglu.utils.x5WebView.RefreshWebView.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                return myWebViewClient.shouldOverrideUrlLoading(webView, url);
            }
        });
    }

    public void setWebChromeClient(final WebChromeClient webChromeClient) {
        this.web_view_.setWebChromeClient(new XMyWebChromeClient() { // from class: com.cdlinglu.utils.x5WebView.RefreshWebView.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                webChromeClient.onShowFileChooser(webView, filePathCallback, fileChooserParams);
                return true;
            }
        });
    }

    public void addJavascriptInterface(WebViewJavaScriptFunction webViewJavaScriptFunction) {
        this.web_view_.addJavascriptInterface(webViewJavaScriptFunction, "Android");
    }

    public void setCurrent_url_(String current_url_) {
        this.current_url_ = current_url_;
    }

    public X5WebView getWebView() {
        return this.web_view_;
    }

    public void remove_webView() {
        if (this.web_view_ != null) {
            try {
                this.web_view_.stopLoading();
                this.web_view_.getSettings().setJavaScriptEnabled(false);
                this.web_view_.clearHistory();
                this.web_view_.clearView();
                this.web_view_.removeAllViews();
                this.web_view_.destroy();
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean canGoBack() {
        return this.web_view_.canGoBack();
    }

    public void goBack() {
        if (canGoBack()) {
            this.web_view_.goBack();
        } else {
            Log.d("WebView", "Can NOT go back anymore");
        }
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed && !this.load_once_) {
            this.web_view_.getLocationOnScreen(this.location_array_);
            this.original_location_y_ = this.location_array_[1];
            this.header_layout_params_ = (ViewGroup.MarginLayoutParams) this.header_.getLayoutParams();
            this.hide_header_height_ = -this.header_.getHeight();
            Log.d("WebView", "onLayout. WebView's locationY = " + this.original_location_y_ + "; It's height is : " + this.hide_header_height_);
            this.load_once_ = true;
        }
    }

    private void updateHeaderView() {
        if (this.last_status_ == this.current_status_) {
            return;
        }
        if (this.current_status_ == 0) {
            ((TextView) findViewById(R.id.pull_to_refresh_description)).setText(getResources().getString(R.string.pull_to_refresh_pull_label));
            findViewById(R.id.pull_to_refresh_arrow).setVisibility(0);
            rotateArrow();
        } else if (this.current_status_ == 1) {
            ((TextView) findViewById(R.id.pull_to_refresh_description)).setText(getResources().getString(R.string.pull_to_refresh_release_label));
            findViewById(R.id.pull_to_refresh_arrow).setVisibility(0);
            rotateArrow();
        } else if (this.current_status_ == 2) {
            ((TextView) findViewById(R.id.pull_to_refresh_description)).setText(getResources().getString(R.string.pull_to_refresh_footer_refreshing_label));
            findViewById(R.id.pull_to_refresh_arrow).clearAnimation();
            findViewById(R.id.pull_to_refresh_arrow).setVisibility(8);
        }
    }

    private void rotateArrow() {
        ImageView arrow = (ImageView) findViewById(R.id.pull_to_refresh_arrow);
        float pivotX = arrow.getWidth() / 2.0f;
        float pivotY = arrow.getHeight() / 2.0f;
        float fromDegrees = 0.0f;
        float toDegrees = 0.0f;
        if (this.current_status_ == 0) {
            fromDegrees = 180.0f;
            toDegrees = 360.0f;
        } else if (this.current_status_ == 1) {
            fromDegrees = 0.0f;
            toDegrees = 180.0f;
        }
        RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees, pivotX, pivotY);
        animation.setDuration(150L);
        animation.setFillAfter(true);
        arrow.startAnimation(animation);
    }

    private void refreshingAction() {
        this.header_layout_params_.topMargin = 0;
        this.header_.setLayoutParams(this.header_layout_params_);
        this.current_status_ = 2;
        Log.i("WebView", "current url is : " + this.current_url_);
        this.web_view_.reload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideHeader() {
        if (this.header_layout_params_ != null) {
            this.header_layout_params_.topMargin = this.hide_header_height_;
            this.header_.setLayoutParams(this.header_layout_params_);
            this.current_status_ = 3;
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        this.web_view_.getLocationOnScreen(this.location_array_);
        if (!this.canPull) {
            return false;
        }
        if (this.original_location_y_ - 1.0f <= this.location_array_[1] && this.web_view_.isTop()) {
            switch (event.getAction()) {
                case 0:
                    this.begin_y = event.getRawY();
                    break;
                case 1:
                default:
                    if (this.current_status_ != 1) {
                        hideHeader();
                        break;
                    } else {
                        refreshingAction();
                        break;
                    }
                case 2:
                    int distance = (int) (event.getRawY() - this.begin_y);
                    if ((distance > 0 || this.header_layout_params_.topMargin > this.hide_header_height_) && distance >= this.touchSlop) {
                        if (this.current_status_ != 2) {
                            if (this.header_layout_params_.topMargin > 0) {
                                this.current_status_ = 1;
                            } else {
                                this.current_status_ = 0;
                            }
                            this.header_layout_params_.topMargin = ((distance / 5) * 2) + this.hide_header_height_;
                            this.header_.setLayoutParams(this.header_layout_params_);
                            break;
                        }
                    } else {
                        return false;
                    }
                    break;
            }
            if (this.current_status_ == 3) {
                return false;
            }
            updateHeaderView();
            this.last_status_ = this.current_status_;
            return true;
        } else if (this.web_view_.isTop() || this.header_layout_params_.topMargin == this.hide_header_height_) {
            return false;
        } else {
            hideHeader();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class MyWebViewDownLoadListener implements DownloadListener {
        private MyWebViewDownLoadListener() {
        }

        @Override // com.tencent.smtt.sdk.DownloadListener
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            Log.w("WebView", "url=" + url);
            RefreshWebView.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
        }
    }

    /* loaded from: classes.dex */
    public class XMyWebViewClient extends WebViewClient {
        public XMyWebViewClient() {
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.i("WebView", "onPageStarted");
            RefreshWebView.this.current_url_ = url;
            super.onPageStarted(view, url, favicon);
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onPageFinished(WebView view, String url) {
            Log.i("WebView", "onPageFinished");
            if (RefreshWebView.this.current_status_ == 2) {
                RefreshWebView.this.hideHeader();
            }
            if (RefreshWebView.this.loadError) {
                RefreshWebView.this.loadError = false;
                RefreshWebView.this.webError.setVisibility(0);
                Log.i("WebView", "showErrorView");
                return;
            }
            RefreshWebView.this.webError.setVisibility(8);
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(webView, request, error);
            Log.i("WebView", "onReceived:" + error.getErrorCode() + "==error:" + error.getDescription().toString() + "==url:" + request.getUrl().toString());
            RefreshWebView.this.loadError = true;
        }

        @Override // com.tencent.smtt.sdk.WebViewClient
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            Log.i("WebView", "onReceivedError:" + errorCode + "==error:" + description + "==url:" + failingUrl);
            RefreshWebView.this.loadError = true;
        }
    }

    /* loaded from: classes.dex */
    public class XMyWebChromeClient extends WebChromeClient {
        public XMyWebChromeClient() {
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public void onProgressChanged(WebView view, int newProgress) {
            if (RefreshWebView.this.handler != null) {
                RefreshWebView.this.handler.sendEmptyMessage(newProgress);
            }
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public void onReceivedTitle(WebView view, String title) {
            Log.i("WebView", "onReceivedTitle");
            if (!TextUtils.isEmpty(title)) {
                RefreshWebView.this.loadError = title.equals("找不到网页") || title.toLowerCase().contains(av.aG);
            }
        }
    }
}

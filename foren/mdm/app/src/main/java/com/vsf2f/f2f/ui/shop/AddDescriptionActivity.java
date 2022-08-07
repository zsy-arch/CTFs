package com.vsf2f.f2f.ui.shop;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.hy.frame.util.MyLog;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.web.WebUtils;

/* loaded from: classes2.dex */
public class AddDescriptionActivity extends Activity {
    private static final int FILECHOOSER_RESULTCODE = 100;
    private ProgressBar bar;
    private Handler handler;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private ValueCallback<Uri> mUploadMessage;
    private WebView webView;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_description);
        initView();
        initData();
    }

    public void initView() {
        this.webView = (WebView) findViewById(R.id.webView);
        this.bar = (ProgressBar) findViewById(R.id.pb_webView);
        if (this.handler == null) {
            this.handler = new Handler(new Handler.Callback() { // from class: com.vsf2f.f2f.ui.shop.AddDescriptionActivity.1
                @Override // android.os.Handler.Callback
                public boolean handleMessage(Message msg) {
                    if (AddDescriptionActivity.this.bar != null) {
                        if (msg.what == 0 || msg.what == 100) {
                            AddDescriptionActivity.this.bar.setVisibility(8);
                        } else {
                            AddDescriptionActivity.this.bar.setVisibility(0);
                            AddDescriptionActivity.this.bar.setProgress(msg.what);
                        }
                    }
                    return false;
                }
            });
        }
    }

    public void initData() {
        String guid = getIntent().getStringExtra("guid");
        if (guid != null && !TextUtils.isEmpty(guid)) {
            initWeb(guid);
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initWeb(String guid) {
        WebUtils.loadTokenUrl(this, this.webView, "/m/products/modify/details/" + guid + ".mobile");
        this.webView.setWebChromeClient(new WebChromeClient() { // from class: com.vsf2f.f2f.ui.shop.AddDescriptionActivity.2
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView view, int newProgress) {
                MyLog.i(newProgress + "");
                if (AddDescriptionActivity.this.handler != null) {
                    AddDescriptionActivity.this.handler.sendEmptyMessage(newProgress);
                }
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                MyLog.e("openFileChoose(ValueCallback<Uri> uploadMsg)");
                AddDescriptionActivity.this.mUploadMessage = uploadMsg;
                Intent i = new Intent("android.intent.action.GET_CONTENT");
                i.addCategory("android.intent.category.OPENABLE");
                i.setType("image/*");
                AddDescriptionActivity.this.startActivityForResult(Intent.createChooser(i, AddDescriptionActivity.this.getString(R.string.select_image)), 100);
            }

            public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
                MyLog.e("openFileChoose( ValueCallback uploadMsg, String acceptType )");
                AddDescriptionActivity.this.mUploadMessage = uploadMsg;
                Intent i = new Intent("android.intent.action.GET_CONTENT");
                i.addCategory("android.intent.category.OPENABLE");
                i.setType("image/*");
                AddDescriptionActivity.this.startActivityForResult(Intent.createChooser(i, AddDescriptionActivity.this.getString(R.string.select_image)), 100);
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                MyLog.e("4.1 openFileChoose(ValueCallback<Uri> uploadMsg, String acceptType, String capture)");
                AddDescriptionActivity.this.mUploadMessage = uploadMsg;
                Intent i = new Intent("android.intent.action.GET_CONTENT");
                i.addCategory("android.intent.category.OPENABLE");
                i.setType("image/*");
                AddDescriptionActivity.this.startActivityForResult(Intent.createChooser(i, AddDescriptionActivity.this.getString(R.string.select_image)), 100);
            }

            @Override // android.webkit.WebChromeClient
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                MyLog.e("5.0 onShowFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture)");
                AddDescriptionActivity.this.mUploadCallbackAboveL = filePathCallback;
                Intent i = new Intent("android.intent.action.GET_CONTENT");
                i.addCategory("android.intent.category.OPENABLE");
                i.setType("image/*");
                AddDescriptionActivity.this.startActivityForResult(Intent.createChooser(i, AddDescriptionActivity.this.getString(R.string.select_image)), 100);
                return true;
            }
        });
        this.webView.setWebViewClient(new WebViewClient() { // from class: com.vsf2f.f2f.ui.shop.AddDescriptionActivity.3
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.endsWith("goback2app")) {
                    AddDescriptionActivity.this.finish();
                }
                view.loadUrl(url);
                return true;
            }
        });
        this.webView.getSettings().setJavaScriptEnabled(true);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            if (this.webView.canGoBack()) {
                this.webView.goBack();
                return true;
            }
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri result;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 100) {
            return;
        }
        if (this.mUploadMessage != null || this.mUploadCallbackAboveL != null) {
            if (data == null || resultCode != -1) {
                result = null;
            } else {
                result = data.getData();
            }
            if (this.mUploadCallbackAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (this.mUploadMessage != null) {
                this.mUploadMessage.onReceiveValue(result);
                this.mUploadMessage = null;
            }
        }
    }

    @TargetApi(16)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && this.mUploadCallbackAboveL != null) {
            Uri[] results = null;
            if (resultCode == -1 && data != null) {
                String dataString = data.getDataString();
                ClipData clipData = data.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        results[i] = clipData.getItemAt(i).getUri();
                    }
                }
                if (dataString != null) {
                    results = new Uri[]{Uri.parse(dataString)};
                }
            }
            this.mUploadCallbackAboveL.onReceiveValue(results);
            this.mUploadCallbackAboveL = null;
        }
    }
}

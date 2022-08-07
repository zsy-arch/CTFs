package com.cdlinglu.utils.x5WebView;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/* loaded from: classes.dex */
public class JSObject {
    private Context context;

    public JSObject(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void JsCallAndroid(String s) {
        Toast.makeText(this.context, "JsCallAndroid：" + s, 0).show();
    }

    @JavascriptInterface
    public void yeeBoResult(String flag) {
    }
}

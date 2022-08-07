package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import com.tencent.open.a.f;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public abstract class b extends Dialog {
    private static final String TAG = "openSDK_LOG.JsDialog";
    protected a jsBridge;
    @SuppressLint({"NewApi"})
    protected final WebChromeClient mChromeClient = new WebChromeClient() { // from class: com.tencent.open.b.1
        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            if (consoleMessage == null) {
                return false;
            }
            f.c(b.TAG, "WebChromeClient onConsoleMessage" + consoleMessage.message() + " -- From  111 line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
            if (Build.VERSION.SDK_INT > 7) {
                b.this.onConsoleMessage(consoleMessage == null ? "" : consoleMessage.message());
            }
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public void onConsoleMessage(String str, int i, String str2) {
            f.c(b.TAG, "WebChromeClient onConsoleMessage" + str + " -- From 222 line " + i + " of " + str2);
            if (Build.VERSION.SDK_INT == 7) {
                b.this.onConsoleMessage(str);
            }
        }
    };

    protected abstract void onConsoleMessage(String str);

    public b(Context context) {
        super(context);
    }

    public b(Context context, int i) {
        super(context, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.jsBridge = new a();
    }
}

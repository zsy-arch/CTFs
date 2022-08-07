package d.a.a;

import android.net.Uri;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes.dex */
public class a extends WebChromeClient {

    /* renamed from: a  reason: collision with root package name */
    public d.a.a.b.a f1599a;

    public a(d.a.a.b.a aVar) {
        this.f1599a = aVar;
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient
    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        return true;
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient
    public void onReceivedTitle(WebView webView, String str) {
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        this.f1599a.a(webView, valueCallback, fileChooserParams);
        return true;
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient
    public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
        this.f1599a.a(valueCallback, str);
    }
}

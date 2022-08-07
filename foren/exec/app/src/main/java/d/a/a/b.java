package d.a.a;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import c.a.a.C;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.smtt.sdk.g;

/* loaded from: classes.dex */
public class b extends WebViewClient {
    @Override // com.tencent.smtt.sdk.WebViewClient
    public void onPageFinished(WebView webView, String str) {
    }

    @Override // com.tencent.smtt.sdk.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        g gVar = this.f1313a;
        if (gVar != null) {
            gVar.a(webView, str, bitmap);
        }
    }

    @Override // com.tencent.smtt.sdk.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        sslErrorHandler.proceed();
    }

    @Override // com.tencent.smtt.sdk.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (TextUtils.isEmpty(webView.getUrl())) {
            return false;
        }
        Uri parse = Uri.parse(str);
        String scheme = parse.getScheme();
        if (!"weixin".equals(scheme) && !"alipays".equals(scheme)) {
            return false;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addFlags(1);
            intent.addFlags(268435456);
            intent.setData(parse);
            webView.getContext().startActivity(intent);
        } catch (Exception unused) {
            C.b("唤起异常，请检查", 1);
        }
        return true;
    }
}

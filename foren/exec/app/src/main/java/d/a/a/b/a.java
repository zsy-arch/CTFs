package d.a.a.b;

import android.net.Uri;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes.dex */
public interface a {
    void a(ValueCallback<Uri> valueCallback, String str);

    void a(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams);
}

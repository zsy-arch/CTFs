package d.a.a.d;

import android.os.Build;
import bccsejw.sxexrix.zaswnwt.widget.MyWebView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class f implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f1633a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ MyWebView f1634b;

    public f(MyWebView myWebView, String str) {
        this.f1634b = myWebView;
        this.f1633a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        int i = Build.VERSION.SDK_INT;
        if (this.f1633a.startsWith("javascript")) {
            f.super.evaluateJavascript(this.f1633a, null);
        } else {
            f.super.loadUrl(this.f1633a);
        }
    }
}

package cn.jpush.android.api;

import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageButton;
import cn.jpush.android.b.a.e;

/* loaded from: classes.dex */
public final class p {
    private static int b = 400;
    private static int c = 600;
    public static e a = null;

    public static void a(WindowManager windowManager, WebView webView, ImageButton imageButton) {
        windowManager.removeView(webView);
        windowManager.removeView(imageButton);
    }
}

package d.a.a.d;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Process;
import android.view.ViewGroup;
import bccsejw.sxexrix.zaswnwt.widget.MyWebView;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class n implements e {

    /* renamed from: a  reason: collision with root package name */
    public static n f1650a;

    /* renamed from: b  reason: collision with root package name */
    public boolean f1651b = true;

    /* renamed from: c  reason: collision with root package name */
    public HashMap<String, String> f1652c = new HashMap<>();

    public static synchronized n a() {
        n nVar;
        synchronized (n.class) {
            if (f1650a == null) {
                f1650a = new n();
            }
            nVar = f1650a;
        }
        return nVar;
    }

    public void b() {
    }

    public MyWebView a(Context context) {
        NetworkInfo activeNetworkInfo;
        MyWebView myWebView = new MyWebView(context);
        myWebView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        if (this.f1651b) {
            this.f1651b = false;
        }
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                int myPid = Process.myPid();
                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
                String str = null;
                if (runningAppProcesses != null) {
                    Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ActivityManager.RunningAppProcessInfo next = it.next();
                        if (next.pid == myPid) {
                            str = next.processName;
                            break;
                        }
                    }
                }
                if (!this.f1652c.containsKey(str)) {
                    WebView.setDataDirectorySuffix(str);
                    this.f1652c.put(str, str);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        WebSettings settings = myWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        if ((context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null) ? false : activeNetworkInfo.isAvailable()) {
            settings.setCacheMode(-1);
        } else {
            settings.setCacheMode(1);
        }
        settings.setAppCacheEnabled(true);
        String path = myWebView.getContext().getApplicationContext().getDir("database", 0).getPath();
        settings.setAppCachePath(path);
        settings.setDomStorageEnabled(true);
        settings.setDatabasePath(path);
        settings.setAllowFileAccess(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        int i = Build.VERSION.SDK_INT;
        settings.setMixedContentMode(0);
        settings.setTextZoom(100);
        settings.setGeolocationEnabled(true);
        settings.setGeolocationDatabasePath(path);
        int i2 = Build.VERSION.SDK_INT;
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setDatabaseEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        if ((myWebView.getContext().getApplicationContext().getApplicationInfo().flags & 2) != 0) {
            int i3 = Build.VERSION.SDK_INT;
            try {
                WebView.setWebContentsDebuggingEnabled(true);
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
            }
        }
        myWebView.setVerticalScrollBarEnabled(false);
        if (myWebView.getX5WebViewExtension() != null) {
            myWebView.getX5WebViewExtension().setScrollBarFadingEnabled(false);
        }
        int i4 = Build.VERSION.SDK_INT;
        CookieManager.getInstance().setAcceptCookie(true);
        CookieManager.getInstance().setAcceptThirdPartyCookies(myWebView, true);
        return myWebView;
    }
}

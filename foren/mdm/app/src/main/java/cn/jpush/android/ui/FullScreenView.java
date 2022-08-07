package cn.jpush.android.ui;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.jpush.android.b.a.a;
import cn.jpush.android.b.a.f;
import cn.jpush.android.data.c;
import cn.jpush.android.data.m;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.b;

/* loaded from: classes.dex */
public class FullScreenView extends LinearLayout {
    private static final String TAG;
    public static f webViewHelper;
    private static final String[] z;
    private View.OnClickListener btnBackClickListener = new a(this);
    private ImageButton imgBtnBack;
    private final Context mContext;
    private WebView mWebView;
    private ProgressBar pushPrograssBar;
    private RelativeLayout rlTitleBar;
    private TextView tvTitle;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0027, code lost:
        if (r5 != 0) goto L_0x0033;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0029, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002d, code lost:
        cn.jpush.android.ui.FullScreenView.TAG = r1;
        r1 = "2*\u0005:),)^";
        r0 = 65535;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0033, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0035, code lost:
        if (r5 > r6) goto L_0x0013;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0037, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0040, code lost:
        switch(r0) {
            case 0: goto L_0x004b;
            case 1: goto L_0x0053;
            case 2: goto L_0x005b;
            case 3: goto L_0x0063;
            case 4: goto L_0x006b;
            case 5: goto L_0x0073;
            case 6: goto L_0x007b;
            case 7: goto L_0x0084;
            case 8: goto L_0x008f;
            case 9: goto L_0x002d;
            default: goto L_0x0043;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u0014\u0015\u0011-\u0014\t \u0006";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = ",)67\u001f65\u0011-\u0014\n,\u00102\u0019\u001c$\u0016";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "80\b2+;'27\u0019)";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "7(\u0003\f\u0015=-\u0014+\u000f6\u0007\u00100>?&\u000f";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u000e)\u0001?\u000f;e\u0011-\u0019~!\u00018\u001d+)\u0010~\u001f1!\u0001~\u00150e\u000e.\t--;)\u0019<3\r;\u000b\u0001)\u0005'\u0013+1J&\u00112d";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006b, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = ".0\u00176,,*\u0003,\u001d-6&?\u000e";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0073, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "7!";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007b, code lost:
        r3[r2] = r1;
        r2 = '\b';
        r1 = "*367\u001f65\u0011-\u0014\n,\u00102\u0019";
        r0 = 7;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0084, code lost:
        r3[r2] = r1;
        r2 = '\t';
        r1 = "- \u0005,\u001f6\u0007\u000b&6?3\u0005\u001c\u000e7!\u0003;#";
        r0 = '\b';
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x008f, code lost:
        r3[r2] = r1;
        cn.jpush.android.ui.FullScreenView.z = r3;
        cn.jpush.android.ui.FullScreenView.webViewHelper = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0096, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0097, code lost:
        r9 = '^';
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x009a, code lost:
        r9 = 'E';
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x009d, code lost:
        r9 = 'd';
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a0, code lost:
        r9 = '^';
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0011, code lost:
        if (r5 <= 1) goto L_0x0013;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0013, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0018, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001c, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x0097;
            case 1: goto L_0x009a;
            case 2: goto L_0x009d;
            case 3: goto L_0x00a0;
            default: goto L_0x001f;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001f, code lost:
        r9 = '|';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0021, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            Method dump skipped, instructions count: 200
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.ui.FullScreenView.<clinit>():void");
    }

    public FullScreenView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    private void quitFullScreen() {
        try {
            WindowManager.LayoutParams attributes = ((Activity) this.mContext).getWindow().getAttributes();
            attributes.flags &= -1025;
            ((Activity) this.mContext).getWindow().setAttributes(attributes);
            ((Activity) this.mContext).getWindow().clearFlags(512);
        } catch (Exception e) {
            ac.d();
        }
    }

    public void destory() {
        removeAllViews();
        if (this.mWebView != null) {
            this.mWebView.removeAllViews();
            this.mWebView.destroy();
            this.mWebView = null;
        }
    }

    public void initModule(Context context, c cVar) {
        m mVar = (m) cVar;
        String str = mVar.E;
        setFocusable(true);
        this.mWebView = (WebView) findViewById(getResources().getIdentifier(z[3], z[7], context.getPackageName()));
        this.rlTitleBar = (RelativeLayout) findViewById(getResources().getIdentifier(z[2], z[7], context.getPackageName()));
        this.tvTitle = (TextView) findViewById(getResources().getIdentifier(z[8], z[7], context.getPackageName()));
        this.imgBtnBack = (ImageButton) findViewById(getResources().getIdentifier(z[4], z[7], context.getPackageName()));
        this.pushPrograssBar = (ProgressBar) findViewById(getResources().getIdentifier(z[6], z[7], context.getPackageName()));
        if (this.mWebView == null || this.rlTitleBar == null || this.tvTitle == null || this.imgBtnBack == null) {
            ac.e(TAG, z[5]);
            ((Activity) this.mContext).finish();
        }
        if (1 == mVar.G) {
            this.rlTitleBar.setVisibility(8);
            ((Activity) context).getWindow().setFlags(1024, 1024);
        } else {
            this.tvTitle.setText(str);
            this.imgBtnBack.setOnClickListener(this.btnBackClickListener);
        }
        this.mWebView.setScrollbarFadingEnabled(true);
        this.mWebView.setScrollBarStyle(33554432);
        WebSettings settings = this.mWebView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        b.a(settings);
        webViewHelper = new f(context, cVar);
        this.mWebView.removeJavascriptInterface(z[9]);
        this.mWebView.setWebChromeClient(new a(z[1], cn.jpush.android.b.a.b.class, this.pushPrograssBar, this.tvTitle));
        this.mWebView.setWebViewClient(new c(cVar));
        cn.jpush.android.b.a.b.setWebViewHelper(webViewHelper);
    }

    public void loadUrl(String str) {
        if (this.mWebView != null) {
            new StringBuilder(z[0]).append(str);
            ac.b();
            this.mWebView.loadUrl(str);
        }
    }

    public void pause() {
        if (this.mWebView != null) {
            this.mWebView.onPause();
        }
    }

    public void resume() {
        if (this.mWebView != null) {
            this.mWebView.onResume();
            cn.jpush.android.b.a.b.setWebViewHelper(webViewHelper);
        }
    }

    public void showTitleBar() {
        if (this.rlTitleBar != null && this.rlTitleBar.getVisibility() == 8) {
            this.rlTitleBar.setVisibility(0);
            quitFullScreen();
            this.imgBtnBack.setOnClickListener(this.btnBackClickListener);
            if (this.mWebView != null) {
                this.mWebView.postDelayed(new b(this), 1000L);
            }
        }
    }

    public boolean webviewCanGoBack() {
        if (this.mWebView != null) {
            return this.mWebView.canGoBack();
        }
        return false;
    }

    public void webviewGoBack() {
        if (this.mWebView != null) {
            this.mWebView.goBack();
        }
    }
}

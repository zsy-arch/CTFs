package d.a.a;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.content.FileProvider;
import bccsejw.sxexrix.zaswnwt.utils.permission.PermissionActivity;
import bccsejw.sxexrix.zaswnwt.widget.MyWebView;
import c.a.a.C;
import c.a.a.m;
import com.tencent.smtt.sdk.BuildConfig;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import d.a.a.b.a;
import d.a.a.c.b;
import d.a.a.d.g;
import d.a.a.d.j;
import d.a.a.d.n;
import e.b.a.d;
import e.b.a.e;
import e.b.a.f;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class h extends m implements a {
    public FrameLayout p;
    public MyWebView q;
    public LinearLayout r;
    public ValueCallback<Uri> s;
    public ValueCallback<Uri[]> t;
    public File u;
    public j v;
    public Uri w;

    public static /* synthetic */ void c(h hVar) {
        ValueCallback<Uri[]> valueCallback = hVar.t;
        if (valueCallback != null) {
            valueCallback.onReceiveValue(null);
            hVar.t = null;
        }
    }

    public abstract String l();

    public final void m() {
        new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.CAMERA"};
        int[] iArr = {f.permission_ic_storage, f.permission_ic_location, f.permission_ic_camera};
        String[] strArr = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"};
        int[] iArr2 = {f.permission_ic_storage, f.permission_ic_camera};
        getResources().getStringArray(e.b.a.a.normalPermissionNames);
        String[] stringArray = getResources().getStringArray(e.b.a.a.cameraPermissionNames);
        e eVar = new e(this);
        if (Build.VERSION.SDK_INT < 23) {
            eVar.a(true);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < stringArray.length; i++) {
            arrayList2.add(new d.a.a.c.a.f(strArr[i], stringArray[i], iArr2[i]));
        }
        arrayList.addAll(arrayList2);
        ListIterator listIterator = arrayList.listIterator();
        while (listIterator.hasNext()) {
            if (d.a.a.c.a.h.a(this, ((d.a.a.c.a.f) listIterator.next()).f1610b)) {
                listIterator.remove();
            }
        }
        int i2 = PermissionActivity.q;
        if (arrayList.size() > 0) {
            PermissionActivity.r = eVar;
            Intent intent = new Intent(this, PermissionActivity.class);
            intent.putExtra("data_title", (String) null);
            intent.putExtra("data_permission_type", i2);
            intent.putExtra("data_permission_title_name", "相机");
            intent.putExtra("data_msg", (String) null);
            intent.putExtra("data_color_filter", 0);
            intent.putExtra("data_style_id", -1);
            intent.putExtra("data_anim_style", -1);
            intent.putExtra("data_permissions", arrayList);
            intent.addFlags(268435456);
            startActivity(intent);
            return;
        }
        eVar.a(true);
    }

    public final void n() {
        String str;
        StringBuilder sb = new StringBuilder();
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        StringBuilder a2 = e.a.a.a.a.a("IMG_");
        a2.append(simpleDateFormat.format(date));
        sb.append(a2.toString());
        sb.append(".jpg");
        String sb2 = sb.toString();
        String str2 = b.f1616e;
        if (TextUtils.isEmpty(str2)) {
            str2 = BuildConfig.FLAVOR;
        } else {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        this.u = new File(str2, sb2);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (Build.VERSION.SDK_INT > 23) {
            intent.setFlags(2);
            File file2 = this.u;
            FileProvider.b bVar = (FileProvider.b) FileProvider.a(this, l() + ".fileProvider");
            try {
                String canonicalPath = file2.getCanonicalPath();
                Map.Entry<String, File> entry = null;
                for (Map.Entry<String, File> entry2 : bVar.f205b.entrySet()) {
                    String path = entry2.getValue().getPath();
                    if (canonicalPath.startsWith(path) && (entry == null || path.length() > entry.getValue().getPath().length())) {
                        entry = entry2;
                    }
                }
                if (entry != null) {
                    String path2 = entry.getValue().getPath();
                    if (path2.endsWith("/")) {
                        str = canonicalPath.substring(path2.length());
                    } else {
                        str = canonicalPath.substring(path2.length() + 1);
                    }
                    this.w = new Uri.Builder().scheme("content").authority(bVar.f204a).encodedPath(Uri.encode(entry.getKey()) + '/' + Uri.encode(str, "/")).build();
                    intent.putExtra("output", this.w);
                } else {
                    throw new IllegalArgumentException(e.a.a.a.a.a("Failed to find configured root that contains ", canonicalPath));
                }
            } catch (IOException unused) {
                throw new IllegalArgumentException(e.a.a.a.a.a("Failed to resolve canonical path for ", file2));
            }
        } else {
            this.w = Uri.fromFile(this.u);
            intent.putExtra("output", this.w);
        }
        startActivityForResult(Intent.createChooser(intent, "拍照"), 8738);
    }

    public final void o() {
        startActivityForResult(Intent.createChooser(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), "请选择图片"), 4369);
    }

    @Override // c.i.a.ActivityC0065h, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        Uri uri;
        if (i != 4369 && i != 8738) {
            return;
        }
        if (this.s != null || this.t != null) {
            if (i2 == 0) {
                ValueCallback<Uri> valueCallback = this.s;
                if (valueCallback != null) {
                    valueCallback.onReceiveValue(null);
                    this.s = null;
                }
                ValueCallback<Uri[]> valueCallback2 = this.t;
                if (valueCallback2 != null) {
                    valueCallback2.onReceiveValue(null);
                    this.t = null;
                }
            } else if (i2 == -1) {
                Uri data = (intent == null || intent.getData() == null) ? null : intent.getData();
                if (data == null && (uri = this.w) != null) {
                    data = uri;
                }
                Uri[] uriArr = {data};
                ValueCallback<Uri> valueCallback3 = this.s;
                if (valueCallback3 != null) {
                    valueCallback3.onReceiveValue(data);
                    this.s = null;
                }
                ValueCallback<Uri[]> valueCallback4 = this.t;
                if (valueCallback4 != null) {
                    valueCallback4.onReceiveValue(uriArr);
                    this.s = null;
                }
            }
        }
    }

    @Override // c.i.a.ActivityC0065h, android.app.Activity
    public void onBackPressed() {
        j jVar = this.v;
        if (jVar != null && jVar.j) {
            ValueCallback<Uri[]> valueCallback = this.t;
            if (valueCallback != null) {
                valueCallback.onReceiveValue(null);
                this.t = null;
            }
            this.v.a();
        } else if (this.q.canGoBack()) {
            this.q.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override // c.a.a.m, c.i.a.ActivityC0065h, c.e.a.c, android.app.Activity
    public void onCreate(Bundle bundle) {
        ViewGroup viewGroup;
        super.onCreate(bundle);
        if ("开".equals(r())) {
            getWindow().setFlags(1024, 1024);
        }
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (d.a.a.c.a.b() / 1048576 < 200) {
            C.b("您的储存空间不足，可能会影响您正常使用", 1);
        } else if (d.a.a.c.a.a()) {
            C.b("您手机处于root环境，请注意资金安全", 1);
        } else {
            if (d.a.a.c.a.a(this)) {
                C.b("当前设备不受信任，请注意资金安全", 1);
                return;
            }
            setContentView(e.activity_main);
            int i = Build.VERSION.SDK_INT;
            ViewGroup viewGroup2 = (ViewGroup) findViewById(16908290);
            if (viewGroup2.getChildCount() > 0 && (viewGroup = (ViewGroup) viewGroup2.getChildAt(0)) != null) {
                viewGroup.setFitsSystemWindows(false);
            }
            int i2 = Build.VERSION.SDK_INT;
            getWindow().getDecorView().setSystemUiVisibility(1280);
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().setStatusBarColor(0);
            if (Build.VERSION.SDK_INT >= 23) {
                C.a((Activity) this, 3, true);
            } else if (C.a("MIUI")) {
                C.a((Activity) this, 0, true);
            } else if (C.a("FLYME")) {
                C.a((Activity) this, 1, true);
            }
            p();
            if ("开".equals(r())) {
                this.r.setPadding(0, 0, 0, 0);
            }
        }
    }

    public void p() {
        this.p = (FrameLayout) findViewById(d.fl_webPager_webview);
        this.r = (LinearLayout) findViewById(d.ll_main_base);
        this.q = n.a().a(this);
        this.q.setWebViewClient(new b());
        this.q.setWebChromeClient(new a(this));
        this.q.clearCache(true);
        this.q.loadUrl(t());
        this.p.addView(this.q);
    }

    public final void q() {
        new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.CAMERA"};
        int[] iArr = {f.permission_ic_storage, f.permission_ic_location, f.permission_ic_camera};
        new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"};
        int[] iArr2 = {f.permission_ic_storage, f.permission_ic_camera};
        getResources().getStringArray(e.b.a.a.normalPermissionNames);
        getResources().getStringArray(e.b.a.a.cameraPermissionNames);
        g gVar = new g(this);
        if (Build.VERSION.SDK_INT < 23 || d.a.a.c.a.h.a(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            gVar.a("android.permission.WRITE_EXTERNAL_STORAGE", 0, true);
            return;
        }
        int i = PermissionActivity.p;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new d.a.a.c.a.f("android.permission.WRITE_EXTERNAL_STORAGE"));
        PermissionActivity.r = gVar;
        Intent intent = new Intent(this, PermissionActivity.class);
        intent.putExtra("data_title", (String) null);
        intent.putExtra("data_permission_type", i);
        intent.putExtra("data_permission_title_name", "相册");
        intent.putExtra("data_msg", (String) null);
        intent.putExtra("data_color_filter", 0);
        intent.putExtra("data_style_id", -1);
        intent.putExtra("data_anim_style", -1);
        intent.putExtra("data_permissions", arrayList);
        intent.addFlags(268435456);
        startActivity(intent);
    }

    public abstract String r();

    public void s() {
        if (this.v == null) {
            this.v = new j(this);
        }
        c cVar = new c(this);
        g gVar = new g(3, "取消", cVar);
        gVar.f1639e = Color.parseColor("#0076ff");
        this.v.a(BuildConfig.FLAVOR, true, gVar, new g(1, "拍照", cVar), new g(2, "从相册选择", cVar));
    }

    public abstract String t();

    @Override // d.a.a.b.a
    public void a(ValueCallback<Uri> valueCallback, String str) {
        this.s = valueCallback;
        s();
    }

    @Override // d.a.a.b.a
    public void a(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        this.t = valueCallback;
        s();
    }
}

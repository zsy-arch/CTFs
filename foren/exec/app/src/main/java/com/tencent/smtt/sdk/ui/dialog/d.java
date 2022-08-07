package com.tencent.smtt.sdk.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.tencent.smtt.sdk.BuildConfig;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.stat.MttLoader;
import com.tencent.smtt.sdk.ui.dialog.widget.b;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import e.a.a.a.a;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class d extends Dialog {

    /* renamed from: a */
    public static WeakReference<ValueCallback<String>> f1437a;

    /* renamed from: b */
    public List<b> f1438b;
    public ListView g;
    public Button h;
    public Button i;
    public String k;
    public a l;
    public String m;
    public String n;
    public Intent o;
    public SharedPreferences p;
    public FrameLayout s;
    public LinearLayout t;
    public final String j = "TBSActivityPicker";

    /* renamed from: c */
    public final String f1439c = "extraMenu";

    /* renamed from: d */
    public final String f1440d = "name";

    /* renamed from: e */
    public final String f1441e = "resource_id";
    public final String f = "value";
    public int q = 0;
    public int r = 0;

    public d(Context context, String str, Intent intent, Bundle bundle, ValueCallback<String> valueCallback, String str2, String str3) {
        super(context, 16973835);
        List<b> list;
        List<b> list2;
        this.m = "*/*";
        String str4 = null;
        this.p = null;
        this.n = str3;
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
        StringBuilder a2 = a.a("acts.size(): ");
        a2.append(queryIntentActivities.size());
        TbsLog.i("TBSActivityPicker", a2.toString());
        Bundle bundle2 = bundle != null ? bundle.getBundle("extraMenu") : null;
        if (bundle2 != null) {
            this.f1438b = new ArrayList();
            for (String str5 : bundle2.keySet()) {
                Bundle bundle3 = bundle2.getBundle(str5);
                if (bundle3 != null) {
                    String string = bundle3.getString("name", str4);
                    int i = bundle3.getInt("resource_id", -1);
                    String string2 = bundle3.getString("value", str4);
                    if (!(string == null || i == -1 || string2 == null)) {
                        this.f1438b.add(new b(getContext(), i, string, string2));
                    }
                }
                str4 = null;
            }
        } else {
            TbsLog.i("TBSActivityPicker", "no extra menu info in bundle");
        }
        if (queryIntentActivities.size() == 0 && (((list2 = this.f1438b) == null || list2.isEmpty()) && MttLoader.isBrowserInstalled(context))) {
            StringBuilder a3 = a.a("no action has been found with Intent:");
            a3.append(intent.toString());
            TbsLog.i("TBSActivityPicker", a3.toString());
            QbSdk.isDefaultDialog = true;
        }
        if ("com.tencent.rtxlite".equalsIgnoreCase(context.getApplicationContext().getPackageName()) && queryIntentActivities.size() == 0 && ((list = this.f1438b) == null || list.isEmpty())) {
            StringBuilder a4 = a.a("package name equal to `com.tencent.rtxlite` but no action has been found with Intent:");
            a4.append(intent.toString());
            TbsLog.i("TBSActivityPicker", a4.toString());
            QbSdk.isDefaultDialog = true;
        }
        this.k = str;
        this.o = intent;
        f1437a = new WeakReference<>(valueCallback);
        this.p = context.getSharedPreferences(QbSdk.SHARE_PREFERENCES_NAME, 0);
        if (!TextUtils.isEmpty(str2)) {
            this.m = str2;
        }
        StringBuilder a5 = a.a("Intent:");
        a5.append(this.m);
        a5.append(" MineType:");
        a5.append(this.m);
        TbsLog.i("TBSActivityPicker", a5.toString());
    }

    private View a(Context context) {
        this.s = new FrameLayout(context);
        this.t = new LinearLayout(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, Double.valueOf(c.a(context) * 0.5f).intValue());
        layoutParams.gravity = 17;
        this.t.setLayoutParams(layoutParams);
        this.t.setOrientation(1);
        this.r = c.a(context, 72.0f);
        com.tencent.smtt.sdk.ui.dialog.widget.a aVar = new com.tencent.smtt.sdk.ui.dialog.widget.a(context, c.a(context, 12.0f), c.b(context, 35.0f), c.b(context, 15.0f));
        aVar.setLayoutParams(new LinearLayout.LayoutParams(-1, this.r));
        aVar.setOnClickListener(new View.OnClickListener() { // from class: com.tencent.smtt.sdk.ui.dialog.d.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                d.this.dismiss();
            }
        });
        this.t.addView(aVar);
        this.g = new ListView(context);
        this.g.setOverScrollMode(2);
        this.g.setVerticalScrollBarEnabled(false);
        this.g.setBackgroundColor(-1);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
        layoutParams2.weight = 1.0f;
        layoutParams2.gravity = 16;
        this.g.setLayoutParams(layoutParams2);
        this.g.setDividerHeight(0);
        this.t.addView(this.g);
        LinearLayout linearLayout = new LinearLayout(context);
        this.q = c.a(context, 150.0f);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, this.q);
        layoutParams3.weight = 0.0f;
        linearLayout.setLayoutParams(layoutParams3);
        linearLayout.setOrientation(0);
        linearLayout.setBackgroundColor(-1);
        linearLayout.setContentDescription("x5_tbs_activity_picker_btn_container");
        this.h = new b(context, c.a(context, 12.0f), Color.parseColor("#EBEDF5"));
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, c.a(context, 90.0f));
        layoutParams4.weight = 1.0f;
        layoutParams4.topMargin = c.a(context, 30.0f);
        layoutParams4.bottomMargin = c.a(context, 30.0f);
        layoutParams4.leftMargin = c.a(context, 32.0f);
        layoutParams4.rightMargin = c.a(context, 8.0f);
        this.h.setLayoutParams(layoutParams4);
        this.h.setText(e.b("x5_tbs_wechat_activity_picker_label_always"));
        this.h.setTextColor(Color.rgb(29, 29, 29));
        this.h.setTextSize(0, c.a(context, 34.0f));
        linearLayout.addView(this.h);
        this.i = new b(context, c.a(context, 12.0f), Color.parseColor("#00CAFC"));
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, c.a(context, 90.0f));
        layoutParams5.weight = 1.0f;
        layoutParams5.topMargin = c.a(context, 30.0f);
        layoutParams5.bottomMargin = c.a(context, 30.0f);
        layoutParams5.leftMargin = c.a(context, 8.0f);
        layoutParams5.rightMargin = c.a(context, 32.0f);
        this.i.setLayoutParams(layoutParams5);
        this.i.setText(e.b("x5_tbs_wechat_activity_picker_label_once"));
        this.i.setTextColor(Color.rgb((int) WebView.NORMAL_MODE_ALPHA, (int) WebView.NORMAL_MODE_ALPHA, (int) WebView.NORMAL_MODE_ALPHA));
        this.i.setTextSize(0, c.a(context, 34.0f));
        linearLayout.addView(this.i);
        this.t.addView(linearLayout);
        this.s.addView(this.t);
        return this.s;
    }

    public void a(b bVar) {
        if (!bVar.f()) {
            return;
        }
        if (!c() || f1437a.get() == null) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://mdc.html5.qq.com/d/directdown.jsp?channel_id=11041"));
            intent.addFlags(268435456);
            getContext().startActivity(intent);
            return;
        }
        f1437a.get().onReceiveValue("https://mdc.html5.qq.com/d/directdown.jsp?channel_id=11047");
    }

    public void b(String str) {
        String str2;
        ValueCallback<String> valueCallback;
        StringBuilder sb;
        ValueCallback<String> valueCallback2;
        String str3;
        ActivityInfo activityInfo;
        if (this.l != null && c()) {
            b a2 = this.l.a();
            ResolveInfo a3 = this.l.a(a2);
            if (f1437a.get() != null) {
                if (a2 != null && a3 != null && (activityInfo = a3.activityInfo) != null && activityInfo.packageName != null) {
                    valueCallback = f1437a.get();
                    StringBuilder a4 = a.a(str);
                    a4.append(a3.activityInfo.packageName);
                    str2 = a4.toString();
                } else if (a2 != null) {
                    if (a2.e()) {
                        valueCallback2 = f1437a.get();
                        sb = a.a(str);
                        str3 = a2.g();
                    } else if (a2.f()) {
                        valueCallback2 = f1437a.get();
                        sb = a.a(str);
                        str3 = a2.d();
                    } else {
                        return;
                    }
                    sb.append(str3);
                    valueCallback2.onReceiveValue(sb.toString());
                    return;
                } else {
                    valueCallback = f1437a.get();
                    str2 = a.a(str, "other");
                }
                valueCallback.onReceiveValue(str2);
            }
        }
    }

    private Drawable c(String str) {
        Context context = getContext();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(context.getFilesDir(), str);
        if (!FileUtil.c(file)) {
            return null;
        }
        try {
            TbsLog.i("TBSActivityPicker", "load icon from: " + file.getAbsolutePath());
            return new BitmapDrawable(BitmapFactory.decodeFile(file.getAbsolutePath()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean c() {
        return TbsConfig.APP_QQ.equals(getContext().getApplicationContext().getPackageName());
    }

    private void d() {
        String str;
        Drawable drawable;
        a aVar = this.l;
        String str2 = null;
        b a2 = aVar != null ? aVar.a() : null;
        SharedPreferences sharedPreferences = this.p;
        if (sharedPreferences != null) {
            drawable = c(sharedPreferences.getString("key_tbs_recommend_icon_url", null));
            str = this.p.getString("key_tbs_recommend_label", null);
            String string = this.p.getString("key_tbs_recommend_description", null);
            if (TextUtils.isEmpty(str)) {
                str = null;
            }
            if (!TextUtils.isEmpty(string)) {
                str2 = string;
            }
        } else {
            drawable = null;
            str = null;
        }
        if (drawable == null) {
            drawable = e.a("application_icon");
        }
        if (str == null) {
            str = "QQ浏览器";
        }
        if (str2 == null) {
            str2 = e.b("x5_tbs_wechat_activity_picker_label_recommend");
        }
        this.l = new a(getContext(), this.o, new b(getContext(), drawable, str, TbsConfig.APP_QB, str2), this.f1438b, a2, this, this.g);
        this.g.setAdapter((ListAdapter) this.l);
        e();
    }

    private void e() {
        ListAdapter adapter = this.g.getAdapter();
        if (adapter != null) {
            int i = 0;
            for (int i2 = 0; i2 < adapter.getCount(); i2++) {
                View view = adapter.getView(i2, null, this.g);
                view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                i += view.getMeasuredHeight();
            }
            ViewGroup.LayoutParams layoutParams = this.t.getLayoutParams();
            float a2 = c.a(getContext());
            layoutParams.height = Double.valueOf(Math.max(Math.min(this.r + i + this.q, 0.9f * a2), a2 * 0.5f)).intValue();
            this.t.setLayoutParams(layoutParams);
        }
    }

    public String a() {
        if (this.p == null) {
            return null;
        }
        StringBuilder a2 = a.a("getTBSPickedDefaultBrowser: ");
        SharedPreferences sharedPreferences = this.p;
        StringBuilder a3 = a.a("key_tbs_picked_default_browser_");
        a3.append(this.m);
        a2.append(sharedPreferences.getString(a3.toString(), null));
        TbsLog.i("TBSActivityPicker", a2.toString());
        SharedPreferences sharedPreferences2 = this.p;
        StringBuilder a4 = a.a("key_tbs_picked_default_browser_");
        a4.append(this.m);
        return sharedPreferences2.getString(a4.toString(), null);
    }

    public void a(String str) {
        SharedPreferences.Editor editor;
        TbsLog.i("TBSActivityPicker", "setTBSPickedDefaultBrowser:" + str);
        if (this.p != null) {
            if (TextUtils.isEmpty(str)) {
                StringBuilder a2 = a.a("paramString empty, remove: key_tbs_picked_default_browser_");
                a2.append(this.m);
                TbsLog.i("TBSActivityPicker", a2.toString());
                SharedPreferences.Editor edit = this.p.edit();
                StringBuilder a3 = a.a("key_tbs_picked_default_browser_");
                a3.append(this.m);
                editor = edit.remove(a3.toString());
            } else {
                StringBuilder a4 = a.a("paramString not empty, set: key_tbs_picked_default_browser_");
                a4.append(this.m);
                a4.append("=");
                a4.append(str);
                TbsLog.i("TBSActivityPicker", a4.toString());
                SharedPreferences.Editor edit2 = this.p.edit();
                StringBuilder a5 = a.a("key_tbs_picked_default_browser_");
                a5.append(this.m);
                editor = edit2.putString(a5.toString(), str);
            }
            editor.commit();
        }
    }

    public void a(boolean z) {
        Button button = this.i;
        if (button != null) {
            button.setEnabled(z);
        }
        Button button2 = this.h;
        if (button2 != null) {
            button2.setEnabled(z);
        }
        b("userMenuClickEvent:");
    }

    public void b() {
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(80);
            window.setLayout(-1, -2);
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            attributes.horizontalMargin = 0.0f;
            attributes.dimAmount = 0.5f;
            window.setAttributes(attributes);
        }
        setContentView(a(getContext()));
        d();
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.tencent.smtt.sdk.ui.dialog.d.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                b a2 = d.this.l.a();
                ResolveInfo a3 = d.this.l.a(a2);
                d.this.b("userClickAlwaysEvent:");
                if (a2 != null) {
                    if (a2.e()) {
                        String g = a2.g();
                        if (d.f1437a.get() != null) {
                            d.f1437a.get().onReceiveValue("extraMenuEvent:" + g);
                        }
                        d dVar = d.this;
                        dVar.a("extraMenuEvent:" + g);
                    } else if (a3 == null) {
                        d.this.a(a2);
                    } else {
                        Intent intent = d.this.o;
                        Context context = d.this.getContext();
                        String str = a3.activityInfo.packageName;
                        intent.setPackage(str);
                        if (TbsConfig.APP_QB.equals(str)) {
                            intent.putExtra("ChannelID", context.getApplicationContext().getPackageName());
                            intent.putExtra("PosID", "4");
                        }
                        if (context != null && context.getApplicationInfo().targetSdkVersion >= 24 && Build.VERSION.SDK_INT >= 24) {
                            intent.addFlags(1);
                        }
                        if (!TextUtils.isEmpty(d.this.n)) {
                            intent.putExtra("big_brother_source_key", d.this.n);
                        }
                        try {
                            context.startActivity(intent);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        if (d.f1437a.get() != null) {
                            d.f1437a.get().onReceiveValue("always");
                        }
                        d.this.a(str);
                    }
                    d.this.dismiss();
                }
            }
        });
        this.i.setOnClickListener(new View.OnClickListener() { // from class: com.tencent.smtt.sdk.ui.dialog.d.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                b a2 = d.this.l.a();
                ResolveInfo a3 = d.this.l.a(a2);
                d.this.b("userClickOnceEvent:");
                d.this.a(BuildConfig.FLAVOR);
                if (a2 != null) {
                    if (!a2.e()) {
                        if (a3 == null) {
                            d.this.a(a2);
                        } else {
                            Intent intent = d.this.o;
                            Context context = d.this.getContext();
                            String str = a3.activityInfo.packageName;
                            intent.setPackage(str);
                            if (TbsConfig.APP_QB.equals(str)) {
                                intent.putExtra("ChannelID", context.getApplicationContext().getPackageName());
                                intent.putExtra("PosID", "4");
                            }
                            if (context.getApplicationInfo().targetSdkVersion >= 24 && Build.VERSION.SDK_INT >= 24) {
                                intent.addFlags(1);
                            }
                            if (!TextUtils.isEmpty(d.this.n)) {
                                intent.putExtra("big_brother_source_key", d.this.n);
                            }
                            try {
                                context.startActivity(intent);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            if (d.f1437a.get() != null) {
                                d.f1437a.get().onReceiveValue("once");
                            }
                        }
                    } else if (d.this.c() && d.f1437a.get() != null) {
                        StringBuilder a4 = a.a("extraMenuEvent:");
                        a4.append(a2.g());
                        d.f1437a.get().onReceiveValue(a4.toString());
                    }
                    d.this.dismiss();
                }
            }
        });
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        b();
    }
}

package com.tencent.smtt.sdk.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.smtt.sdk.BuildConfig;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.stat.MttLoader;
import com.tencent.smtt.sdk.ui.dialog.widget.RoundImageView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class a extends ArrayAdapter<b> implements View.OnClickListener, ListAdapter {

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<b> f1422a;

    /* renamed from: b  reason: collision with root package name */
    public b f1423b = null;

    /* renamed from: c  reason: collision with root package name */
    public Intent f1424c;

    /* renamed from: d  reason: collision with root package name */
    public WeakReference<ListView> f1425d;

    /* renamed from: e  reason: collision with root package name */
    public WeakReference<d> f1426e;
    public b f;
    public b g;
    public List<b> h;
    public Handler i;
    public String[] j;

    public a(Context context, Intent intent, b bVar, List<b> list, b bVar2, d dVar, ListView listView) {
        super(context, 0);
        a(dVar);
        a(listView);
        this.g = bVar;
        this.f1424c = intent;
        if ("com.tencent.rtxlite".equalsIgnoreCase(context.getApplicationContext().getPackageName()) || MttLoader.isBrowserInstalled(context)) {
            this.f = null;
        } else {
            this.f = this.g;
        }
        this.h = list;
        this.i = new Handler() { // from class: com.tencent.smtt.sdk.ui.dialog.a.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    a.this.b();
                }
            }
        };
        this.j = new String[2];
        this.j[0] = e.b("x5_tbs_activity_picker_recommend_to_trim");
        this.j[1] = e.b("x5_tbs_activity_picker_recommend_with_chinese_brace_to_trim");
        a(context, bVar2);
    }

    private View a(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
        linearLayout.setOrientation(1);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, new ColorDrawable(Color.argb(41, 0, 0, 0)));
        stateListDrawable.addState(new int[]{-16842919}, new ColorDrawable(0));
        linearLayout.setBackgroundDrawable(stateListDrawable);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, c.a(getContext(), 144.0f)));
        RoundImageView roundImageView = new RoundImageView(context, null);
        roundImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(c.a(getContext(), 96.0f), c.a(getContext(), 96.0f));
        layoutParams.addRule(9);
        layoutParams.addRule(15);
        layoutParams.setMargins(c.a(getContext(), 32.0f), c.a(getContext(), 24.0f), c.a(getContext(), 24.0f), c.a(getContext(), 24.0f));
        roundImageView.setLayoutParams(layoutParams);
        roundImageView.setId(101);
        relativeLayout.addView(roundImageView);
        LinearLayout linearLayout2 = new LinearLayout(context);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(15);
        layoutParams2.addRule(1, 101);
        linearLayout2.setLayoutParams(layoutParams2);
        linearLayout2.setOrientation(1);
        TextView textView = new TextView(context);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setMaxLines(1);
        textView.setTextColor(Color.rgb(29, 29, 29));
        textView.setTextSize(1, 17.0f);
        textView.setId(102);
        linearLayout2.addView(textView);
        TextView textView2 = new TextView(context);
        textView2.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView2.setText(e.b("x5_tbs_wechat_activity_picker_label_recommend"));
        textView2.setTextColor(Color.parseColor("#00CAFC"));
        textView2.setTextSize(1, 14.0f);
        textView2.setId(103);
        linearLayout2.addView(textView2);
        relativeLayout.addView(linearLayout2);
        ImageView imageView = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(c.a(getContext(), 48.0f), c.a(getContext(), 48.0f));
        layoutParams3.addRule(11);
        layoutParams3.addRule(15);
        layoutParams3.setMargins(0, 0, c.a(getContext(), 32.0f), 0);
        imageView.setLayoutParams(layoutParams3);
        imageView.setImageDrawable(e.a("x5_tbs_activity_picker_check"));
        imageView.setId(104);
        relativeLayout.addView(imageView);
        relativeLayout.setId(105);
        linearLayout.addView(relativeLayout);
        return linearLayout;
    }

    private void a(b bVar, View view) {
        if (!(view == null || bVar == null)) {
            TextView textView = (TextView) view.findViewById(102);
            TextView textView2 = (TextView) view.findViewById(103);
            ImageView imageView = (ImageView) view.findViewById(104);
            View findViewById = view.findViewById(105);
            View findViewById2 = view.findViewById(106);
            ((ImageView) view.findViewById(101)).setImageDrawable(bVar.a());
            String replaceAll = bVar.b().trim().replaceAll((char) 160 + BuildConfig.FLAVOR, BuildConfig.FLAVOR);
            String[] strArr = this.j;
            for (String str : strArr) {
                if (str != null && str.length() > 0) {
                    replaceAll = replaceAll.replaceAll(str, BuildConfig.FLAVOR);
                }
            }
            textView.setText(replaceAll);
            if (bVar.c() == null) {
                bVar.a(a(bVar));
            }
            findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.tencent.smtt.sdk.ui.dialog.a.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ViewParent parent = view2.getParent();
                    if (parent instanceof View) {
                        View view3 = (View) parent;
                        if (view3.getTag() == a.this.f) {
                            a.this.onClick(view3);
                        }
                    }
                }
            });
            if (TbsConfig.APP_QB.equals(bVar.d())) {
                textView2.setVisibility(0);
                textView2.setText(bVar.h());
            } else {
                textView2.setVisibility(8);
            }
            findViewById.setClickable(false);
            findViewById.setEnabled(false);
            if (bVar == this.f1423b) {
                imageView.setVisibility(0);
                if (findViewById2 != null) {
                    findViewById2.setVisibility(0);
                }
            } else {
                imageView.setVisibility(8);
                if (findViewById2 != null) {
                    findViewById2.setVisibility(8);
                }
            }
            view.setTag(bVar);
            view.setOnClickListener(this);
        }
    }

    private void a(boolean z) {
        d dVar;
        WeakReference<d> weakReference = this.f1426e;
        if (weakReference != null && (dVar = weakReference.get()) != null) {
            dVar.a(z);
        }
    }

    public static boolean a(Context context, String str) {
        if (str != null && !BuildConfig.FLAVOR.equals(str)) {
            try {
                context.getPackageManager().getApplicationInfo(str, 8192);
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return false;
    }

    private void b(Context context, b bVar) {
        this.f1423b = bVar;
        b bVar2 = this.f1423b;
        if (bVar2 != null) {
            a((bVar2.e() || this.f1423b.f()) ? true : a(context, this.f1423b.d()));
        }
    }

    public ResolveInfo a(b bVar) {
        if (bVar != null && !TextUtils.isEmpty(bVar.d())) {
            for (ResolveInfo resolveInfo : getContext().getPackageManager().queryIntentActivities(this.f1424c, 65536)) {
                if (bVar.d().equals(resolveInfo.activityInfo.packageName)) {
                    return resolveInfo;
                }
            }
        }
        return null;
    }

    public b a() {
        return this.f1423b;
    }

    /* renamed from: a */
    public b getItem(int i) {
        if (i < 0 || i >= this.f1422a.size()) {
            return null;
        }
        return this.f1422a.get(i);
    }

    public void a(Context context, b bVar) {
        b bVar2;
        this.f1422a = new ArrayList<>();
        List<b> list = this.h;
        if (!(list == null || list.size() == 0)) {
            this.f1422a.addAll(this.h);
        }
        boolean z = false;
        boolean z2 = false;
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(this.f1424c, 65536)) {
            if (b.a(context, resolveInfo.activityInfo.packageName) != null || resolveInfo.loadIcon(context.getPackageManager()) != null) {
                b bVar3 = new b(context, resolveInfo);
                b bVar4 = this.f;
                if (bVar4 != null && bVar4.d().equals(resolveInfo.activityInfo.packageName)) {
                    bVar3.a(this.f.f());
                    bVar3.a(this.f.h());
                    bVar3.a(this.f.a());
                    this.f1422a.add(0, bVar3);
                    z = true;
                } else if (TbsConfig.APP_QB.equals(resolveInfo.activityInfo.packageName)) {
                    b bVar5 = this.g;
                    if (bVar5 != null) {
                        bVar3.a(bVar5.f());
                        bVar3.a(this.g.h());
                        bVar3.a(this.g.a());
                    }
                    this.f1422a.add(0, bVar3);
                } else {
                    this.f1422a.add(bVar3);
                }
                if (!z2 && bVar != null && bVar3.d().equals(bVar.d())) {
                    b(context, bVar3);
                    z2 = true;
                }
            }
        }
        if (!z && (bVar2 = this.f) != null) {
            this.f1422a.add(0, bVar2);
        }
        if (!z2 && this.f1422a.size() > 0) {
            b(context, this.f1422a.get(0));
        }
    }

    public void a(ListView listView) {
        this.f1425d = new WeakReference<>(listView);
    }

    public void a(d dVar) {
        this.f1426e = new WeakReference<>(dVar);
    }

    public void b() {
        View findViewWithTag;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.i.obtainMessage(1).sendToTarget();
            return;
        }
        ListView listView = this.f1425d.get();
        if (listView != null && (findViewWithTag = listView.findViewWithTag(this.f)) != null) {
            a(this.f, findViewWithTag);
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return this.f1422a.size();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        b a2 = getItem(i);
        if (a2 == null) {
            return null;
        }
        if (view == null) {
            view = a(getContext());
        }
        a(a2, view);
        return view;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        b bVar;
        Object tag = view.getTag();
        if (tag != null && (tag instanceof b) && (bVar = (b) tag) != this.f1423b) {
            ViewParent parent = view.getParent();
            View view2 = null;
            if (parent instanceof View) {
                view2 = (View) parent;
            }
            b bVar2 = this.f1423b;
            b(view.getContext(), bVar);
            a(bVar2, view2.findViewWithTag(bVar2));
            a(this.f1423b, view);
        }
    }
}

package com.alimama.mobile.csdk.umupdate.a;

import android.content.Context;
import android.content.res.Resources;

/* compiled from: MMRes.java */
/* loaded from: classes.dex */
public class h {
    private String a;
    private Resources b;

    public h(Context context) {
        this.b = context.getResources();
        this.a = context.getPackageName();
    }

    public int a() {
        return this.b.getIdentifier("tb_munion_adview", f.bt, this.a);
    }

    public int b() {
        return this.b.getIdentifier("tb_munion_aditem", f.bt, this.a);
    }

    public String c() {
        return this.b.getString(this.b.getIdentifier("tb_munion_tip_download_prefix", "string", this.a));
    }

    public int d() {
        return this.b.getIdentifier("progress_frame", "id", this.a);
    }

    public int e() {
        return this.b.getIdentifier("promoter_frame", "id", this.a);
    }

    public int f() {
        return this.b.getIdentifier("status_msg", "id", this.a);
    }

    public int g() {
        return this.b.getIdentifier("loading", "id", this.a);
    }

    public int h() {
        return this.b.getIdentifier("ad_image", "id", this.a);
    }
}

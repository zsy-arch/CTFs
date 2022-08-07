package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.DexLoader;

/* loaded from: classes.dex */
public class TbsMediaFactory {

    /* renamed from: a  reason: collision with root package name */
    public Context f1245a;

    /* renamed from: b  reason: collision with root package name */
    public s f1246b = null;

    /* renamed from: c  reason: collision with root package name */
    public DexLoader f1247c = null;

    public TbsMediaFactory(Context context) {
        this.f1245a = null;
        this.f1245a = context.getApplicationContext();
        a();
    }

    private void a() {
        if (this.f1245a != null) {
            if (this.f1246b == null) {
                d.a(true).a(this.f1245a, false, false);
                this.f1246b = d.a(true).a();
                s sVar = this.f1246b;
                if (sVar != null) {
                    this.f1247c = sVar.b();
                }
            }
            if (this.f1246b == null || this.f1247c == null) {
                throw new RuntimeException("tbs core dex(s) load failure !!!");
            }
        }
    }

    public TbsMediaPlayer createPlayer() {
        DexLoader dexLoader;
        if (this.f1246b != null && (dexLoader = this.f1247c) != null) {
            return new TbsMediaPlayer(new n(dexLoader, this.f1245a));
        }
        throw new RuntimeException("tbs core dex(s) did not loaded !!!");
    }
}

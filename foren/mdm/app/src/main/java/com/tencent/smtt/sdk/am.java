package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.k;
import java.io.File;

/* loaded from: classes2.dex */
public class am extends Thread {
    final /* synthetic */ Context a;
    final /* synthetic */ Context b;
    final /* synthetic */ aj c;

    public am(aj ajVar, Context context, Context context2) {
        this.c = ajVar;
        this.a = context;
        this.b = context2;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp thread start");
        try {
            File h = this.c.h(this.a);
            File h2 = this.c.h(this.b);
            k.a(h, h2, new an(this));
            k.a(h, h2, new ao(this));
            TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp thread done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.alimama.mobile.csdk.umupdate.b;

import com.alimama.mobile.csdk.umupdate.a.j;
import u.upd.g;

/* compiled from: XpClient.java */
/* loaded from: classes.dex */
public class b extends g {
    public f a(e eVar) {
        f fVar = null;
        for (int i = 0; i < a.c.length; i++) {
            eVar.setBaseUrl(a.c[i]);
            fVar = (f) setHeader(j.a()).execute(eVar, f.class);
            if (!(fVar == null || fVar.b == null)) {
                break;
            }
        }
        return fVar;
    }
}

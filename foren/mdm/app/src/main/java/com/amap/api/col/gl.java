package com.amap.api.col;

import java.util.HashMap;
import java.util.Map;

/* compiled from: AuthRequest.java */
@Deprecated
/* loaded from: classes.dex */
class gl extends ig {
    private String b;
    private Map<String, String> a = new HashMap();
    private Map<String, String> c = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Map<String, String> map) {
        this.a.clear();
        this.a.putAll(map);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str) {
        this.b = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(Map<String, String> map) {
        this.c.clear();
        this.c.putAll(map);
    }

    @Override // com.amap.api.col.ig
    public String c() {
        return this.b;
    }

    @Override // com.amap.api.col.ig
    public Map<String, String> a() {
        return this.a;
    }

    @Override // com.amap.api.col.ig
    public Map<String, String> b() {
        return this.c;
    }
}

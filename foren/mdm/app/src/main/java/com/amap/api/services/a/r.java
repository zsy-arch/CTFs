package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;

/* compiled from: NearbyDeleteHandler.java */
/* loaded from: classes.dex */
public class r extends b<String, Integer> {
    private Context h;
    private String i;

    public r(Context context, String str) {
        super(context, str);
        this.h = context;
        this.i = str;
    }

    @Override // com.amap.api.services.a.b
    protected String e() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(aw.f(this.h));
        stringBuffer.append("&userid=").append(this.i);
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public Integer a(String str) throws AMapException {
        return 0;
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        return h.b() + "/nearby/data/delete";
    }
}

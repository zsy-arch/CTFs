package com.amap.api.col;

import android.content.Context;
import com.amap.api.col.gb;
import com.amap.api.maps.AMapException;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: ProtocalHandler.java */
/* loaded from: classes.dex */
public abstract class bl<T, V> {
    protected T a;
    protected int b = 3;
    protected Context c;

    protected abstract String a();

    protected abstract JSONObject a(gb.a aVar);

    protected abstract V b(JSONObject jSONObject) throws AMapException;

    protected abstract Map<String, String> b();

    public bl(Context context, T t) {
        a(context, t);
    }

    private void a(Context context, T t) {
        this.c = context;
        this.a = t;
    }

    public V c() throws AMapException {
        if (this.a != null) {
            return d();
        }
        return null;
    }

    protected V d() throws AMapException {
        int i;
        String str;
        AMapException aMapException;
        int i2 = 0;
        V v = null;
        gb.a aVar = null;
        while (i2 < this.b) {
            try {
                aVar = gb.a(this.c, dt.e(), a(), b());
                v = b(a(aVar));
                i2 = this.b;
            } finally {
                if (i2 < i) {
                    continue;
                }
            }
        }
        return v;
    }
}

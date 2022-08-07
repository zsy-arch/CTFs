package com.alipay.sdk.data;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.util.i;
import com.alipay.sdk.util.k;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class b implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ a b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(a aVar, Context context) {
        this.b = aVar;
        this.a = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            com.alipay.sdk.packet.impl.b bVar = new com.alipay.sdk.packet.impl.b();
            Context context = this.a;
            com.alipay.sdk.packet.b a = bVar.a(context, "", k.a(context), true);
            if (a != null) {
                a aVar = this.b;
                String str = a.b;
                if (!TextUtils.isEmpty(str)) {
                    try {
                        JSONObject optJSONObject = new JSONObject(str).optJSONObject(a.g);
                        aVar.i = optJSONObject.optInt(a.f, a.a);
                        aVar.j = optJSONObject.optString(a.h, a.b).trim();
                    } catch (Throwable th) {
                    }
                }
                a aVar2 = this.b;
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(a.f, aVar2.a());
                    jSONObject.put(a.h, aVar2.j);
                    i.a(com.alipay.sdk.sys.b.a().a, a.e, jSONObject.toString());
                } catch (Exception e) {
                }
            }
        } catch (Throwable th2) {
        }
    }
}

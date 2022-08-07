package cn.jpush.android.util;

import android.content.Context;
import org.json.JSONArray;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class aj implements Runnable {
    Context a;
    JSONArray b;

    public aj(Context context, JSONArray jSONArray) {
        this.a = context;
        this.b = jSONArray;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ah.b(this.a, this.b);
    }
}

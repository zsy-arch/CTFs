package com.alipay.sdk.authjs;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;
import com.alipay.sdk.authjs.a;
import com.em.ui.EditActivity;
import java.util.Timer;
import org.json.JSONException;
import org.json.JSONObject;
import u.aly.av;

/* loaded from: classes.dex */
public final class c {
    b a;
    Context b;

    private static /* synthetic */ int a(c cVar, a aVar) {
        if (aVar != null && "toast".equals(aVar.k)) {
            JSONObject jSONObject = aVar.m;
            String optString = jSONObject.optString(EditActivity.CONTENT);
            int optInt = jSONObject.optInt("duration");
            int i = 1;
            if (optInt < 2500) {
                i = 0;
            }
            Toast.makeText(cVar.b, optString, i).show();
            new Timer().schedule(new e(cVar, aVar), i);
        }
        return a.EnumC0009a.a;
    }

    public c(Context context, b bVar) {
        this.b = context;
        this.a = bVar;
    }

    private void a(String str) {
        String str2;
        JSONObject jSONObject;
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            String string = jSONObject2.getString(a.e);
            try {
                if (!TextUtils.isEmpty(string)) {
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("param");
                    if (jSONObject3 instanceof JSONObject) {
                        jSONObject = jSONObject3;
                    } else {
                        jSONObject = null;
                    }
                    String string2 = jSONObject2.getString(a.g);
                    String string3 = jSONObject2.getString(a.d);
                    a aVar = new a("call");
                    aVar.j = string3;
                    aVar.k = string2;
                    aVar.m = jSONObject;
                    aVar.i = string;
                    a(aVar);
                }
            } catch (Exception e) {
                str2 = string;
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        a(str2, a.EnumC0009a.d);
                    } catch (JSONException e2) {
                    }
                }
            }
        } catch (Exception e3) {
            str2 = null;
        }
    }

    public final void a(a aVar) throws JSONException {
        if (TextUtils.isEmpty(aVar.k)) {
            a(aVar.i, a.EnumC0009a.c);
            return;
        }
        d dVar = new d(this, aVar);
        if (Looper.getMainLooper() == Looper.myLooper()) {
            dVar.run();
        } else {
            new Handler(Looper.getMainLooper()).post(dVar);
        }
    }

    public final void a(String str, int i) throws JSONException {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(av.aG, i - 1);
            a aVar = new a("callback");
            aVar.m = jSONObject;
            aVar.i = str;
            this.a.a(aVar);
        }
    }

    private static void a(Runnable runnable) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    private int b(a aVar) {
        if (aVar != null && "toast".equals(aVar.k)) {
            JSONObject jSONObject = aVar.m;
            String optString = jSONObject.optString(EditActivity.CONTENT);
            int optInt = jSONObject.optInt("duration");
            int i = 1;
            if (optInt < 2500) {
                i = 0;
            }
            Toast.makeText(this.b, optString, i).show();
            new Timer().schedule(new e(this, aVar), i);
        }
        return a.EnumC0009a.a;
    }

    private void c(a aVar) {
        JSONObject jSONObject = aVar.m;
        String optString = jSONObject.optString(EditActivity.CONTENT);
        int optInt = jSONObject.optInt("duration");
        int i = 1;
        if (optInt < 2500) {
            i = 0;
        }
        Toast.makeText(this.b, optString, i).show();
        new Timer().schedule(new e(this, aVar), i);
    }
}

package com.alipay.b.a.a.e;

import com.alipay.b.a.a.c.b.a;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import org.json.JSONObject;
import u.aly.av;

/* loaded from: classes.dex */
public final class b {
    private File a;
    private a b;

    public b(String str, a aVar) {
        this.a = null;
        this.b = null;
        this.a = new File(str);
        this.b = aVar;
    }

    private static String a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "id");
            jSONObject.put(av.aG, str);
            return jSONObject.toString();
        } catch (Exception e) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b() {
        String str;
        int i;
        synchronized (this) {
            if (this.a != null && this.a.exists() && this.a.isDirectory() && this.a.list().length != 0) {
                ArrayList arrayList = new ArrayList();
                for (String str2 : this.a.list()) {
                    arrayList.add(str2);
                }
                Collections.sort(arrayList);
                String str3 = (String) arrayList.get(arrayList.size() - 1);
                int size = arrayList.size();
                if (!str3.equals(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".log")) {
                    str = str3;
                    i = size;
                } else if (arrayList.size() >= 2) {
                    str = (String) arrayList.get(arrayList.size() - 2);
                    i = size - 1;
                }
                int i2 = !this.b.a(a(com.alipay.b.a.a.a.b.a(this.a.getAbsolutePath(), str))) ? i - 1 : i;
                for (int i3 = 0; i3 < i2; i3++) {
                    new File(this.a, (String) arrayList.get(i3)).delete();
                }
            }
        }
    }

    public final void a() {
        new Thread(new c(this)).start();
    }
}

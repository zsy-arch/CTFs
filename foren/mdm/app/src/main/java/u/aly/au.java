package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.alipay.sdk.util.h;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import u.aly.aw;

/* compiled from: ViewPageTracker.java */
/* loaded from: classes2.dex */
public class au {
    private static final String a = "activities";
    private final Map<String, Long> b = new HashMap();
    private final ArrayList<aw.l> c = new ArrayList<>();

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.b) {
                this.b.put(str, Long.valueOf(System.currentTimeMillis()));
            }
        }
    }

    public void b(String str) {
        Long remove;
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.b) {
                remove = this.b.remove(str);
            }
            if (remove == null) {
                bo.e("please call 'onPageStart(%s)' before onPageEnd", str);
                return;
            }
            long currentTimeMillis = System.currentTimeMillis() - remove.longValue();
            synchronized (this.c) {
                aw.l lVar = new aw.l();
                lVar.a = str;
                lVar.b = currentTimeMillis;
                this.c.add(lVar);
            }
        }
    }

    public void a() {
        String str = null;
        long j = 0;
        synchronized (this.b) {
            for (Map.Entry<String, Long> entry : this.b.entrySet()) {
                if (entry.getValue().longValue() > j) {
                    j = entry.getValue().longValue();
                    str = entry.getKey();
                } else {
                    j = j;
                    str = str;
                }
            }
        }
        if (str != null) {
            b(str);
        }
    }

    public void a(Context context) {
        if (context != null) {
            try {
                SharedPreferences a2 = aq.a(context);
                SharedPreferences.Editor edit = a2.edit();
                if (this.c.size() > 0) {
                    String string = a2.getString(a, "");
                    StringBuilder sb = new StringBuilder();
                    if (!TextUtils.isEmpty(string)) {
                        sb.append(string);
                        sb.append(h.b);
                    }
                    synchronized (this.c) {
                        Iterator<aw.l> it = this.c.iterator();
                        while (it.hasNext()) {
                            aw.l next = it.next();
                            sb.append(String.format("[\"%s\",%d]", next.a, Long.valueOf(next.b)));
                            sb.append(h.b);
                        }
                        this.c.clear();
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    edit.remove(a);
                    edit.putString(a, sb.toString());
                }
                edit.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void a(SharedPreferences sharedPreferences, aw.o oVar) {
        if (sharedPreferences != null) {
            try {
                String string = sharedPreferences.getString(a, "");
                if (!TextUtils.isEmpty(string)) {
                    for (String str : string.split(h.b)) {
                        JSONArray jSONArray = new JSONArray(str);
                        aw.l lVar = new aw.l();
                        lVar.a = jSONArray.getString(0);
                        lVar.b = jSONArray.getInt(1);
                        oVar.g.add(lVar);
                    }
                }
            } catch (Exception e) {
                bo.e(e);
            }
        }
    }
}

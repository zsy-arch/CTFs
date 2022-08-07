package u.aly;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.util.h;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import u.aly.aw;

/* compiled from: AutoViewPageTracker.java */
/* loaded from: classes2.dex */
public class ad {
    public static String a = null;
    private static final String c = "autoact";
    private Application f;
    private final Map<String, Long> d = new HashMap();
    private final ArrayList<aw.l> e = new ArrayList<>();
    Application.ActivityLifecycleCallbacks b = new Application.ActivityLifecycleCallbacks() { // from class: u.aly.ad.1
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            ad.this.b(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            ad.this.c(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }
    };

    public ad(Activity activity) {
        this.f = null;
        if (activity != null) {
            this.f = activity.getApplication();
            a(activity);
        }
    }

    private void a(Activity activity) {
        this.f.registerActivityLifecycleCallbacks(this.b);
        if (a == null) {
            b(activity);
        }
    }

    public void a() {
        if (this.f != null) {
            this.f.unregisterActivityLifecycleCallbacks(this.b);
        }
    }

    public void a(Context context) {
        SharedPreferences a2;
        if (context == null) {
            try {
                context = this.f.getApplicationContext();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (context != null && (a2 = aq.a(context)) != null) {
            SharedPreferences.Editor edit = a2.edit();
            if (this.e.size() > 0) {
                String string = a2.getString(c, "");
                StringBuilder sb = new StringBuilder();
                if (!TextUtils.isEmpty(string)) {
                    sb.append(string);
                    if (!string.endsWith(h.b)) {
                        sb.append(h.b);
                    }
                }
                synchronized (this.e) {
                    Iterator<aw.l> it = this.e.iterator();
                    while (it.hasNext()) {
                        aw.l next = it.next();
                        sb.append(String.format("[\"%s\",%d]", next.a, Long.valueOf(next.b)));
                        sb.append(h.b);
                    }
                    this.e.clear();
                }
                sb.deleteCharAt(sb.length() - 1);
                edit.remove(c);
                edit.putString(c, sb.toString());
            }
            edit.commit();
        }
    }

    public void b(Context context) {
        c(null);
        a();
        a(context);
    }

    public static void a(SharedPreferences sharedPreferences, aw.o oVar) {
        if (sharedPreferences != null) {
            try {
                String string = sharedPreferences.getString(c, "");
                if (!TextUtils.isEmpty(string)) {
                    for (String str : string.split(h.b)) {
                        JSONArray jSONArray = new JSONArray(str);
                        aw.l lVar = new aw.l();
                        lVar.a = jSONArray.getString(0);
                        lVar.b = jSONArray.getInt(1);
                        oVar.h.add(lVar);
                    }
                }
            } catch (Exception e) {
                bo.e(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Activity activity) {
        a = activity.getPackageName() + "." + activity.getLocalClassName();
        synchronized (this.d) {
            this.d.put(a, Long.valueOf(System.currentTimeMillis()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Activity activity) {
        long j = 0;
        synchronized (this.d) {
            if (this.d.containsKey(a)) {
                j = System.currentTimeMillis() - this.d.get(a).longValue();
                this.d.remove(a);
            }
        }
        synchronized (this.e) {
            aw.l lVar = new aw.l();
            lVar.a = a;
            lVar.b = j;
            this.e.add(lVar);
        }
    }
}

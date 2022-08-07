package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import u.aly.aw;

/* compiled from: StoreHelper.java */
/* loaded from: classes2.dex */
public final class bs {
    private static Context b = null;
    private static String c = null;
    private static final String g = "mobclick_agent_user_";
    private static final String h = "mobclick_agent_header_";
    private static final String i = "mobclick_agent_update_";
    private static final String j = "mobclick_agent_state_";
    private static final String k = "mobclick_agent_cached_";
    private a d;
    private static bs a = null;
    private static long e = 1209600000;
    private static long f = 2097152;

    /* compiled from: StoreHelper.java */
    /* loaded from: classes2.dex */
    public interface b {
        void a(File file);

        boolean b(File file);

        void c(File file);
    }

    public bs(Context context) {
        this.d = new a(context);
    }

    public static synchronized bs a(Context context) {
        bs bsVar;
        synchronized (bs.class) {
            b = context.getApplicationContext();
            c = context.getPackageName();
            if (a == null) {
                a = new bs(context);
            }
            bsVar = a;
        }
        return bsVar;
    }

    private static boolean a(File file) {
        long length = file.length();
        if (!file.exists() || length <= f) {
            return false;
        }
        m.a(b).a(length, System.currentTimeMillis(), com.umeng.analytics.a.s);
        return true;
    }

    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            SharedPreferences.Editor edit = o().edit();
            edit.putString("au_p", str);
            edit.putString("au_u", str2);
            edit.commit();
        }
    }

    public String[] a() {
        SharedPreferences o = o();
        String string = o.getString("au_p", null);
        String string2 = o.getString("au_u", null);
        if (string == null || string2 == null) {
            return null;
        }
        return new String[]{string, string2};
    }

    public void b() {
        o().edit().remove("au_p").remove("au_u").commit();
    }

    public String c() {
        SharedPreferences a2 = aq.a(b);
        if (a2 != null) {
            return a2.getString("appkey", null);
        }
        return null;
    }

    public void a(String str) {
        SharedPreferences a2 = aq.a(b);
        if (a2 != null) {
            a2.edit().putString("appkey", str).commit();
        }
    }

    public String d() {
        SharedPreferences a2 = aq.a(b);
        if (a2 != null) {
            return a2.getString("channel", null);
        }
        return null;
    }

    public void b(String str) {
        SharedPreferences a2 = aq.a(b);
        if (a2 != null) {
            a2.edit().putString("channel", str).commit();
        }
    }

    public String e() {
        SharedPreferences a2 = aq.a(b);
        if (a2 != null) {
            return a2.getString("st", null);
        }
        return null;
    }

    public void c(String str) {
        SharedPreferences a2 = aq.a(b);
        if (a2 != null) {
            a2.edit().putString("st", str).commit();
        }
    }

    public void a(int i2) {
        SharedPreferences a2 = aq.a(b);
        if (a2 != null) {
            a2.edit().putInt("vt", i2).commit();
        }
    }

    public int f() {
        SharedPreferences a2 = aq.a(b);
        if (a2 != null) {
            return a2.getInt("vt", 0);
        }
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v22, types: [u.aly.aw] */
    /* JADX WARN: Type inference failed for: r0v3, types: [u.aly.aw] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r3v10, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Unknown variable types count: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public u.aly.aw g() {
        /*
            r5 = this;
            r1 = 0
            java.lang.String r0 = r5.q()     // Catch: Exception -> 0x006e
            java.io.File r2 = new java.io.File     // Catch: Exception -> 0x006e
            android.content.Context r3 = u.aly.bs.b     // Catch: Exception -> 0x006e
            android.content.Context r3 = r3.getApplicationContext()     // Catch: Exception -> 0x006e
            java.io.File r3 = r3.getFilesDir()     // Catch: Exception -> 0x006e
            java.lang.String r3 = r3.getAbsolutePath()     // Catch: Exception -> 0x006e
            r2.<init>(r3, r0)     // Catch: Exception -> 0x006e
            boolean r0 = a(r2)     // Catch: Exception -> 0x006e
            if (r0 == 0) goto L_0x0022
            r2.delete()     // Catch: Exception -> 0x006e
        L_0x0021:
            return r1
        L_0x0022:
            boolean r0 = r2.exists()     // Catch: Exception -> 0x006e
            if (r0 == 0) goto L_0x0021
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: Exception -> 0x0057, all -> 0x0079
            r3.<init>(r2)     // Catch: Exception -> 0x0057, all -> 0x0079
            java.io.ObjectInputStream r2 = new java.io.ObjectInputStream     // Catch: Exception -> 0x0096, all -> 0x0091
            r2.<init>(r3)     // Catch: Exception -> 0x0096, all -> 0x0091
            java.lang.Object r0 = r2.readObject()     // Catch: Exception -> 0x0099, all -> 0x0094
            u.aly.aw r0 = (u.aly.aw) r0     // Catch: Exception -> 0x0099, all -> 0x0094
            if (r2 == 0) goto L_0x003d
            r2.close()     // Catch: IOException -> 0x0044, Exception -> 0x0049
        L_0x003d:
            if (r3 == 0) goto L_0x0042
            r3.close()     // Catch: IOException -> 0x0052, Exception -> 0x0049
        L_0x0042:
            r1 = r0
            goto L_0x0021
        L_0x0044:
            r1 = move-exception
            r1.printStackTrace()     // Catch: Exception -> 0x0049
            goto L_0x003d
        L_0x0049:
            r1 = move-exception
        L_0x004a:
            boolean r2 = u.aly.bo.a
            if (r2 == 0) goto L_0x0042
            u.aly.bo.e(r1)
            goto L_0x0042
        L_0x0052:
            r1 = move-exception
            r1.printStackTrace()     // Catch: Exception -> 0x0049
            goto L_0x0042
        L_0x0057:
            r0 = move-exception
            r2 = r1
            r3 = r1
        L_0x005a:
            r0.printStackTrace()     // Catch: all -> 0x0094
            if (r2 == 0) goto L_0x0062
            r2.close()     // Catch: IOException -> 0x0069, Exception -> 0x006e
        L_0x0062:
            if (r3 == 0) goto L_0x009b
            r3.close()     // Catch: IOException -> 0x0073, Exception -> 0x006e
            r0 = r1
            goto L_0x0042
        L_0x0069:
            r0 = move-exception
            r0.printStackTrace()     // Catch: Exception -> 0x006e
            goto L_0x0062
        L_0x006e:
            r0 = move-exception
            r4 = r0
            r0 = r1
            r1 = r4
            goto L_0x004a
        L_0x0073:
            r0 = move-exception
            r0.printStackTrace()     // Catch: Exception -> 0x006e
            r0 = r1
            goto L_0x0042
        L_0x0079:
            r0 = move-exception
            r2 = r1
            r3 = r1
        L_0x007c:
            if (r2 == 0) goto L_0x0081
            r2.close()     // Catch: IOException -> 0x0087, Exception -> 0x006e
        L_0x0081:
            if (r3 == 0) goto L_0x0086
            r3.close()     // Catch: IOException -> 0x008c, Exception -> 0x006e
        L_0x0086:
            throw r0     // Catch: Exception -> 0x006e
        L_0x0087:
            r2 = move-exception
            r2.printStackTrace()     // Catch: Exception -> 0x006e
            goto L_0x0081
        L_0x008c:
            r2 = move-exception
            r2.printStackTrace()     // Catch: Exception -> 0x006e
            goto L_0x0086
        L_0x0091:
            r0 = move-exception
            r2 = r1
            goto L_0x007c
        L_0x0094:
            r0 = move-exception
            goto L_0x007c
        L_0x0096:
            r0 = move-exception
            r2 = r1
            goto L_0x005a
        L_0x0099:
            r0 = move-exception
            goto L_0x005a
        L_0x009b:
            r0 = r1
            goto L_0x0042
        */
        throw new UnsupportedOperationException("Method not decompiled: u.aly.bs.g():u.aly.aw");
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x005f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0064 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(u.aly.aw r5) {
        /*
            r4 = this;
            r2 = 0
            java.lang.String r0 = r4.q()
            java.io.File r1 = new java.io.File
            android.content.Context r3 = u.aly.bs.b
            android.content.Context r3 = r3.getApplicationContext()
            java.io.File r3 = r3.getFilesDir()
            java.lang.String r3 = r3.getAbsolutePath()
            r1.<init>(r3, r0)
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: Exception -> 0x003d, all -> 0x005a
            r3.<init>(r1)     // Catch: Exception -> 0x003d, all -> 0x005a
            java.io.ObjectOutputStream r1 = new java.io.ObjectOutputStream     // Catch: Exception -> 0x007a, all -> 0x0072
            r1.<init>(r3)     // Catch: Exception -> 0x007a, all -> 0x0072
            r1.writeObject(r5)     // Catch: Exception -> 0x007e, all -> 0x0075
            r1.flush()     // Catch: Exception -> 0x007e, all -> 0x0075
            if (r1 == 0) goto L_0x002d
            r1.close()     // Catch: IOException -> 0x0033
        L_0x002d:
            if (r3 == 0) goto L_0x0032
            r3.close()     // Catch: IOException -> 0x0038
        L_0x0032:
            return
        L_0x0033:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x002d
        L_0x0038:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0032
        L_0x003d:
            r0 = move-exception
            r1 = r2
        L_0x003f:
            u.aly.bo.e(r0)     // Catch: all -> 0x0077
            r0.printStackTrace()     // Catch: all -> 0x0077
            if (r1 == 0) goto L_0x004a
            r1.close()     // Catch: IOException -> 0x0055
        L_0x004a:
            if (r2 == 0) goto L_0x0032
            r2.close()     // Catch: IOException -> 0x0050
            goto L_0x0032
        L_0x0050:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0032
        L_0x0055:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x004a
        L_0x005a:
            r0 = move-exception
            r1 = r2
            r3 = r2
        L_0x005d:
            if (r1 == 0) goto L_0x0062
            r1.close()     // Catch: IOException -> 0x0068
        L_0x0062:
            if (r3 == 0) goto L_0x0067
            r3.close()     // Catch: IOException -> 0x006d
        L_0x0067:
            throw r0
        L_0x0068:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0062
        L_0x006d:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0067
        L_0x0072:
            r0 = move-exception
            r1 = r2
            goto L_0x005d
        L_0x0075:
            r0 = move-exception
            goto L_0x005d
        L_0x0077:
            r0 = move-exception
            r3 = r2
            goto L_0x005d
        L_0x007a:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x003f
        L_0x007e:
            r0 = move-exception
            r2 = r3
            goto L_0x003f
        */
        throw new UnsupportedOperationException("Method not decompiled: u.aly.bs.a(u.aly.aw):void");
    }

    public void h() {
        b.deleteFile(p());
        b.deleteFile(q());
        m.a(b).d(new f() { // from class: u.aly.bs.1
            @Override // u.aly.f, u.aly.g
            public void a(Object obj, boolean z) {
                if (obj.equals("success")) {
                }
            }
        });
    }

    public void a(byte[] bArr) {
        this.d.a(bArr);
    }

    public boolean i() {
        return this.d.a();
    }

    public a j() {
        return this.d;
    }

    private SharedPreferences o() {
        return b.getSharedPreferences(g + c, 0);
    }

    public SharedPreferences k() {
        return b.getSharedPreferences(h + c, 0);
    }

    public SharedPreferences l() {
        return b.getSharedPreferences(i + c, 0);
    }

    public SharedPreferences m() {
        return b.getSharedPreferences(j + c, 0);
    }

    private String p() {
        return h + c;
    }

    private String q() {
        SharedPreferences a2 = aq.a(b);
        if (a2 == null) {
            return k + c + bl.a(b);
        }
        int i2 = a2.getInt(com.umeng.analytics.a.y, 0);
        int parseInt = Integer.parseInt(bl.a(b));
        if (i2 == 0 || parseInt == i2) {
            return k + c + bl.a(b);
        }
        return k + c + i2;
    }

    /* compiled from: StoreHelper.java */
    /* loaded from: classes2.dex */
    public static class a {
        private final int a;
        private File b;
        private FilenameFilter c;

        public a(Context context) {
            this(context, ".um");
        }

        public a(Context context, String str) {
            this.a = 10;
            this.c = new FilenameFilter() { // from class: u.aly.bs.a.2
                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str2) {
                    return str2.startsWith("um");
                }
            };
            this.b = new File(context.getFilesDir(), str);
            if (!this.b.exists() || !this.b.isDirectory()) {
                this.b.mkdir();
            }
        }

        public boolean a() {
            File[] listFiles = this.b.listFiles();
            return listFiles != null && listFiles.length > 0;
        }

        public void a(b bVar) {
            File[] listFiles = this.b.listFiles(this.c);
            if (listFiles != null && listFiles.length >= 10) {
                Arrays.sort(listFiles);
                final int length = listFiles.length - 10;
                bp.b(new Runnable() { // from class: u.aly.bs.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (length > 0) {
                            m.a(bs.b).a(length, System.currentTimeMillis(), com.umeng.analytics.a.t);
                        }
                    }
                });
                for (int i = 0; i < length; i++) {
                    listFiles[i].delete();
                }
            }
            if (listFiles != null && listFiles.length > 0) {
                bVar.a(this.b);
                int length2 = listFiles.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    try {
                        if (bVar.b(listFiles[i2])) {
                            listFiles[i2].delete();
                        }
                    } catch (Throwable th) {
                        listFiles[i2].delete();
                    }
                }
                bVar.c(this.b);
            }
        }

        public void a(byte[] bArr) {
            if (bArr != null && bArr.length != 0) {
                try {
                    bm.a(new File(this.b, String.format(Locale.US, "um_cache_%d.env", Long.valueOf(System.currentTimeMillis()))), bArr);
                } catch (Exception e) {
                }
            }
        }

        public void b() {
            File[] listFiles = this.b.listFiles(this.c);
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    file.delete();
                }
            }
        }

        public int c() {
            File[] listFiles = this.b.listFiles(this.c);
            if (listFiles == null || listFiles.length <= 0) {
                return 0;
            }
            return listFiles.length;
        }
    }

    public byte[] b(final aw awVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            final JSONObject jSONObject2 = new JSONObject();
            jSONObject.put(com.umeng.analytics.a.x, new JSONObject() { // from class: u.aly.bs.2
                {
                    bs.this = this;
                    bs.this.a(awVar, this, jSONObject2);
                }
            });
            JSONObject jSONObject3 = new JSONObject() { // from class: u.aly.bs.3
                {
                    bs.this = this;
                    bs.this.b(awVar, this, jSONObject2);
                }
            };
            if (jSONObject3.length() > 0) {
                jSONObject.put(com.umeng.analytics.a.w, jSONObject3);
            }
            bo.b("serialize entry:" + String.valueOf(jSONObject2));
            return String.valueOf(jSONObject).getBytes();
        } catch (Exception e2) {
            bo.e("Fail to serialize log ...", e2);
            return null;
        }
    }

    public void a(aw awVar, JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        jSONObject.put("appkey", awVar.a.a);
        jSONObject.put("channel", awVar.a.b);
        if (awVar.a.c != null) {
            jSONObject.put("secret", awVar.a.c);
        }
        jSONObject.put(av.d, awVar.a.d);
        jSONObject.put(av.g, awVar.a.g);
        jSONObject.put(av.e, awVar.a.e);
        jSONObject.put(av.f, awVar.a.f);
        jSONObject.put("version_code", awVar.a.h);
        jSONObject.put(av.i, awVar.a.i);
        jSONObject.put(av.j, awVar.a.j);
        jSONObject.put(av.k, awVar.a.k);
        jSONObject.put("sdk_version", awVar.a.l);
        jSONObject.put(av.m, awVar.a.m);
        jSONObject.put("idmd5", awVar.a.s);
        jSONObject.put("cpu", awVar.a.t);
        jSONObject.put("os", awVar.a.f55u);
        jSONObject.put("os_version", awVar.a.v);
        jSONObject.put("resolution", awVar.a.w);
        jSONObject.put("mc", awVar.a.x);
        jSONObject.put("device_id", awVar.a.y);
        jSONObject.put("device_model", awVar.a.z);
        jSONObject.put(av.w, awVar.a.A);
        jSONObject.put(av.x, awVar.a.B);
        jSONObject.put(av.y, awVar.a.C);
        jSONObject.put(av.z, awVar.a.D);
        jSONObject.put(av.A, awVar.a.E);
        jSONObject.put(av.B, awVar.a.F);
        if (awVar.a.G != null) {
            jSONObject.put(av.C, awVar.a.G);
        }
        if (awVar.a.H != null) {
            jSONObject.put(av.D, awVar.a.H);
        }
        jSONObject.put("timezone", awVar.a.I);
        jSONObject.put("language", awVar.a.J);
        jSONObject.put("country", awVar.a.K);
        jSONObject.put("carrier", awVar.a.L);
        jSONObject.put("access", awVar.a.M);
        jSONObject.put("access_subtype", awVar.a.N);
        jSONObject.put(av.t, awVar.a.O == null ? "" : awVar.a.O);
        jSONObject.put(av.K, awVar.a.P);
        jSONObject.put(av.L, awVar.a.Q);
        jSONObject.put(av.M, awVar.a.R);
        jSONObject.put(av.N, awVar.a.S);
        jSONObject.put(av.O, awVar.a.T);
        jSONObject2.put("sdk_version", awVar.a.l).put("device_id", awVar.a.y).put("device_model", awVar.a.z).put("version", awVar.a.v).put("appkey", awVar.a.a).put("channel", awVar.a.b);
    }

    public void b(aw awVar, JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        JSONObject jSONObject3 = new JSONObject();
        if (!(awVar.b.h == null || awVar.b.h.a == null || awVar.b.h.a.size() <= 0)) {
            JSONObject jSONObject4 = new JSONObject();
            for (Map.Entry<String, List<aw.e>> entry : awVar.b.h.a.entrySet()) {
                String key = entry.getKey();
                List<aw.e> value = entry.getValue();
                JSONArray jSONArray = new JSONArray();
                for (int i2 = 0; i2 < value.size(); i2++) {
                    aw.e eVar = value.get(i2);
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put(av.ax, eVar.a);
                    jSONObject5.put(av.ay, eVar.b);
                    jSONObject5.put(av.az, eVar.c);
                    jSONObject5.put("count", eVar.d);
                    jSONObject5.put(av.aB, new JSONArray((Collection) eVar.e));
                    jSONArray.put(jSONObject5);
                }
                jSONObject4.put(key, jSONArray);
            }
            jSONObject3.put(av.aw, jSONObject4);
        }
        if (!(awVar.b.h == null || awVar.b.h.b == null || awVar.b.h.b.size() <= 0)) {
            JSONObject jSONObject6 = new JSONObject();
            for (Map.Entry<String, List<aw.f>> entry2 : awVar.b.h.b.entrySet()) {
                String key2 = entry2.getKey();
                List<aw.f> value2 = entry2.getValue();
                JSONArray jSONArray2 = new JSONArray();
                for (int i3 = 0; i3 < value2.size(); i3++) {
                    aw.f fVar = value2.get(i3);
                    JSONObject jSONObject7 = new JSONObject();
                    jSONObject7.put("value", fVar.a);
                    jSONObject7.put("ts", fVar.b);
                    jSONObject7.put("label", fVar.c);
                    jSONArray2.put(jSONObject7);
                }
                jSONObject6.put(key2, jSONArray2);
            }
            jSONObject3.put(av.aC, jSONObject6);
        }
        if (jSONObject3 != null && jSONObject3.length() > 0) {
            jSONObject.put(av.av, jSONObject3);
            jSONObject2.put("Categorical Counter", jSONObject3);
        }
        if (awVar.b.a != null && awVar.b.a.size() > 0) {
            JSONArray jSONArray3 = new JSONArray();
            for (int i4 = 0; i4 < awVar.b.a.size(); i4++) {
                aw.h hVar = awVar.b.a.get(i4);
                JSONArray jSONArray4 = new JSONArray();
                for (int i5 = 0; i5 < hVar.b.size(); i5++) {
                    JSONObject jSONObject8 = new JSONObject();
                    aw.j jVar = hVar.b.get(i5);
                    jSONObject8.put("id", jVar.c);
                    jSONObject8.put("ts", jVar.d);
                    jSONObject8.put(av.aO, jVar.e);
                    for (Map.Entry<String, Object> entry3 : jVar.g.entrySet()) {
                        Object value3 = entry3.getValue();
                        if (value3 instanceof Map) {
                            JSONObject jSONObject9 = new JSONObject();
                            for (Map.Entry entry4 : ((Map) value3).entrySet()) {
                                jSONObject9.put((String) entry4.getKey(), entry4.getValue());
                            }
                            jSONObject8.put(entry3.getKey(), jSONObject9);
                        } else if (value3 instanceof List) {
                            ArrayList arrayList = (ArrayList) value3;
                            JSONArray jSONArray5 = new JSONArray();
                            for (int i6 = 0; i6 < arrayList.size(); i6++) {
                                jSONArray5.put(arrayList.get(i6));
                            }
                            jSONObject8.put(entry3.getKey(), jSONArray5);
                        } else {
                            jSONObject8.put(entry3.getKey(), entry3.getValue());
                        }
                    }
                    jSONArray4.put(jSONObject8);
                }
                if (!(hVar.a == null || jSONArray4 == null || jSONArray4.length() <= 0)) {
                    JSONObject jSONObject10 = new JSONObject();
                    jSONObject10.put(hVar.a, jSONArray4);
                    jSONArray3.put(jSONObject10);
                }
            }
            if (jSONArray3 != null && jSONArray3.length() > 0) {
                jSONObject.put(av.aK, jSONArray3);
                jSONObject2.put("event", jSONArray3);
            }
        }
        if (awVar.b.b != null && awVar.b.b.size() > 0) {
            JSONArray jSONArray6 = new JSONArray();
            for (int i7 = 0; i7 < awVar.b.b.size(); i7++) {
                aw.h hVar2 = awVar.b.b.get(i7);
                JSONArray jSONArray7 = new JSONArray();
                for (int i8 = 0; i8 < hVar2.b.size(); i8++) {
                    aw.j jVar2 = hVar2.b.get(i8);
                    JSONObject jSONObject11 = new JSONObject();
                    jSONObject11.put("id", jVar2.c);
                    jSONObject11.put("ts", jVar2.d);
                    jSONObject11.put(av.aO, jVar2.e);
                    for (Map.Entry<String, Object> entry5 : jVar2.g.entrySet()) {
                        Object value4 = entry5.getValue();
                        if ((value4 instanceof String) || (value4 instanceof Integer) || (value4 instanceof Long)) {
                            jSONObject11.put(entry5.getKey(), entry5.getValue());
                        }
                    }
                    jSONArray7.put(jSONObject11);
                }
                if (!(hVar2.a == null || jSONArray7 == null || jSONArray7.length() <= 0)) {
                    JSONObject jSONObject12 = new JSONObject();
                    jSONObject12.put(hVar2.a, jSONArray7);
                    jSONArray6.put(jSONObject12);
                }
            }
            if (jSONArray6 != null && jSONArray6.length() > 0) {
                jSONObject.put(av.aL, jSONArray6);
                jSONObject2.put("game event", jSONArray6);
            }
        }
        if (awVar.b.i != null && awVar.b.i.size() > 0) {
            JSONArray jSONArray8 = new JSONArray();
            for (int i9 = 0; i9 < awVar.b.i.size(); i9++) {
                aw.i iVar = awVar.b.i.get(i9);
                JSONObject jSONObject13 = new JSONObject();
                jSONObject13.put("ts", iVar.a);
                jSONObject13.put(av.aI, iVar.b);
                jSONObject13.put(av.aJ, iVar.c);
                jSONArray8.put(jSONObject13);
            }
            jSONObject.put(av.aG, jSONArray8);
        }
        if (awVar.b.c != null && awVar.b.c.size() > 0) {
            JSONArray jSONArray9 = new JSONArray();
            for (int i10 = 0; i10 < awVar.b.c.size(); i10++) {
                aw.o oVar = awVar.b.c.get(i10);
                JSONObject jSONObject14 = new JSONObject();
                jSONObject14.put("id", oVar.b);
                jSONObject14.put("start_time", oVar.c);
                jSONObject14.put("end_time", oVar.d);
                jSONObject14.put("duration", oVar.e);
                if (!(oVar.i.a == 0 && oVar.i.b == 0)) {
                    JSONObject jSONObject15 = new JSONObject();
                    jSONObject15.put(av.aj, oVar.i.a);
                    jSONObject15.put(av.ai, oVar.i.b);
                    jSONObject14.put(av.ah, jSONObject15);
                }
                if (oVar.g.size() > 0) {
                    JSONArray jSONArray10 = new JSONArray();
                    for (aw.l lVar : oVar.g) {
                        JSONObject jSONObject16 = new JSONObject();
                        jSONObject16.put(av.ab, lVar.a);
                        jSONObject16.put("duration", lVar.b);
                        jSONArray10.put(jSONObject16);
                    }
                    jSONObject14.put(av.Z, jSONArray10);
                }
                if (oVar.h.size() > 0) {
                    JSONArray jSONArray11 = new JSONArray();
                    for (aw.l lVar2 : oVar.h) {
                        JSONObject jSONObject17 = new JSONObject();
                        jSONObject17.put(av.ab, lVar2.a);
                        jSONObject17.put("duration", lVar2.b);
                        jSONArray11.put(jSONObject17);
                    }
                    jSONObject14.put(av.aa, jSONArray11);
                }
                if (oVar.j.c != 0) {
                    JSONArray jSONArray12 = new JSONArray();
                    JSONObject jSONObject18 = new JSONObject();
                    jSONObject18.put("lat", oVar.j.a);
                    jSONObject18.put("lng", oVar.j.b);
                    jSONObject18.put("ts", oVar.j.c);
                    jSONArray12.put(jSONObject18);
                    jSONObject14.put(av.ad, jSONArray12);
                }
                jSONArray9.put(jSONObject14);
            }
            if (jSONArray9 != null && jSONArray9.length() > 0) {
                jSONObject.put(av.U, jSONArray9);
                jSONObject2.put("session", jSONArray9);
            }
        }
        if (awVar.b.d.a != 0) {
            JSONObject jSONObject19 = new JSONObject();
            jSONObject19.put("ts", awVar.b.d.a);
            if (jSONObject19.length() > 0) {
                jSONObject.put(av.ak, jSONObject19);
                jSONObject2.put("active_msg", awVar.b.d.a);
            }
        }
        if (awVar.b.e.c) {
            JSONObject jSONObject20 = new JSONObject();
            JSONObject jSONObject21 = new JSONObject();
            jSONObject21.put("interval", awVar.b.e.b);
            jSONObject21.put(av.ao, awVar.b.e.a);
            jSONObject20.put(av.an, jSONObject21);
            if (jSONObject20.length() > 0) {
                jSONObject.put(av.am, jSONObject20);
            }
        }
        if (awVar.b.f.size() > 0) {
            JSONObject jSONObject22 = new JSONObject();
            for (Map.Entry<String, Integer> entry6 : awVar.b.f.entrySet()) {
                jSONObject22.put(entry6.getKey(), entry6.getValue());
            }
            jSONObject.put(av.aq, jSONObject22);
        }
        if (!(awVar.b.g.a == null && awVar.b.g.b == null)) {
            JSONObject jSONObject23 = new JSONObject();
            jSONObject23.put("provider", awVar.b.g.a);
            jSONObject23.put(av.au, awVar.b.g.b);
            if (jSONObject23.length() > 0) {
                jSONObject.put(av.as, jSONObject23);
                jSONObject2.put(av.as, jSONObject23);
            }
        }
        if (awVar.b.j != null) {
            jSONObject.put("userlevel", awVar.b.j);
        }
    }
}

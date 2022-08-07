package u.aly;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import u.aly.aw;

/* compiled from: EventTracker.java */
/* loaded from: classes2.dex */
public class ah {
    private static final String a = "fs_lc_tl";
    private Context e;
    private af f;
    private ae g;
    private final int b = 128;
    private final int c = 256;
    private final int d = 10;
    private JSONObject h = null;

    public ah(Context context) {
        this.f = null;
        this.g = null;
        try {
            if (context == null) {
                throw new RuntimeException("Context is null, can't track event");
            }
            this.e = context;
            this.f = af.b(this.e);
            this.g = this.f.a(this.e);
            if (this.h == null) {
                a(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(String str, Map<String, Object> map, long j) {
        try {
            if (a(str) && b(map)) {
                aw.j jVar = new aw.j();
                jVar.c = str;
                jVar.d = System.currentTimeMillis();
                if (j > 0) {
                    jVar.e = j;
                }
                jVar.a = 1;
                Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
                for (int i = 0; i < 10 && it.hasNext(); i++) {
                    Map.Entry<String, Object> next = it.next();
                    if (!av.aR.equals(next.getKey()) && !av.aP.equals(next.getKey()) && !av.aO.equals(next.getKey()) && !"id".equals(next.getKey()) && !"ts".equals(next.getKey())) {
                        Object value = next.getValue();
                        if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
                            jVar.g.put(next.getKey(), value);
                        }
                    }
                }
                if (jVar.b == null) {
                    jVar.b = as.g(this.e);
                }
                jVar.g.put("_umpname", ad.a);
                this.f.a(jVar);
            }
        } catch (Exception e) {
            bo.e("Exception occurred in Mobclick.onEvent(). ", e);
        }
    }

    public void a(String str, String str2, long j, int i) {
        try {
            if (a(str) && b(str2)) {
                new HashMap().put(str, str2 == null ? "" : str2);
                aw.j jVar = new aw.j();
                jVar.c = str;
                jVar.d = System.currentTimeMillis();
                if (j > 0) {
                    jVar.e = j;
                }
                jVar.a = 1;
                Map<String, Object> map = jVar.g;
                if (str2 == null) {
                    str2 = "";
                }
                map.put(str, str2);
                if (jVar.b == null) {
                    jVar.b = as.g(this.e);
                }
                jVar.g.put("_umpname", ad.a);
                this.f.a(jVar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(Context context, List<String> list) {
        try {
            if (this.e == null && context != null) {
                this.e = context;
            }
        } catch (Exception e) {
        }
    }

    public void a(String str, Map<String, Object> map) {
    }

    private JSONObject a(Map<String, Object> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                try {
                    String key = entry.getKey();
                    if (key != null) {
                        String a2 = bm.a(key, 128);
                        Object value = entry.getValue();
                        if (value != null) {
                            if (value.getClass().isArray()) {
                                if (value instanceof int[]) {
                                    ArrayList arrayList = new ArrayList();
                                    for (int i : (int[]) value) {
                                        arrayList.add(Integer.valueOf(i));
                                    }
                                    jSONObject.put(a2, arrayList);
                                } else if (value instanceof double[]) {
                                    ArrayList arrayList2 = new ArrayList();
                                    for (double d : (double[]) value) {
                                        arrayList2.add(Double.valueOf(d));
                                    }
                                    jSONObject.put(a2, arrayList2);
                                } else if (value instanceof long[]) {
                                    ArrayList arrayList3 = new ArrayList();
                                    for (long j : (long[]) value) {
                                        arrayList3.add(Long.valueOf(j));
                                    }
                                    jSONObject.put(a2, arrayList3);
                                } else if (value instanceof float[]) {
                                    ArrayList arrayList4 = new ArrayList();
                                    for (float f : (float[]) value) {
                                        arrayList4.add(Float.valueOf(f));
                                    }
                                    jSONObject.put(a2, arrayList4);
                                } else if (value instanceof boolean[]) {
                                    ArrayList arrayList5 = new ArrayList();
                                    for (boolean z : (boolean[]) value) {
                                        arrayList5.add(Boolean.valueOf(z));
                                    }
                                    jSONObject.put(a2, arrayList5);
                                } else if (value instanceof byte[]) {
                                    ArrayList arrayList6 = new ArrayList();
                                    for (byte b : (byte[]) value) {
                                        arrayList6.add(Byte.valueOf(b));
                                    }
                                    jSONObject.put(a2, arrayList6);
                                } else if (value instanceof short[]) {
                                    ArrayList arrayList7 = new ArrayList();
                                    for (short s : (short[]) value) {
                                        arrayList7.add(Short.valueOf(s));
                                    }
                                    jSONObject.put(a2, arrayList7);
                                } else if (value instanceof char[]) {
                                    ArrayList arrayList8 = new ArrayList();
                                    for (char c : (char[]) value) {
                                        arrayList8.add(Character.valueOf(c));
                                    }
                                    jSONObject.put(a2, arrayList8);
                                } else {
                                    jSONObject.put(a2, new ArrayList(Arrays.asList((Object[]) value)));
                                }
                            } else if (value instanceof String) {
                                jSONObject.put(a2, bm.a(value.toString(), 256));
                            } else {
                                jSONObject.put(a2, value);
                            }
                        }
                    }
                } catch (Exception e) {
                    bo.e(e);
                }
            }
        } catch (Exception e2) {
        }
        return jSONObject;
    }

    public void b(String str, Map<String, Object> map) {
        try {
            if (a(str)) {
                aw.j jVar = new aw.j();
                jVar.c = str;
                jVar.d = System.currentTimeMillis();
                jVar.e = 0L;
                jVar.a = 2;
                Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
                for (int i = 0; i < 10 && it.hasNext(); i++) {
                    Map.Entry<String, Object> next = it.next();
                    if (!av.aR.equals(next.getKey()) && !av.aP.equals(next.getKey()) && !av.aO.equals(next.getKey()) && !"id".equals(next.getKey()) && !"ts".equals(next.getKey())) {
                        Object value = next.getValue();
                        if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
                            jVar.g.put(next.getKey(), value);
                        }
                    }
                }
                if (jVar.b == null) {
                    jVar.b = as.g(this.e);
                }
                this.f.a(jVar);
            }
        } catch (Exception e) {
        }
    }

    private boolean a(String str) {
        if (str != null) {
            try {
                int length = str.trim().getBytes().length;
                if (length > 0 && length <= 128) {
                    return true;
                }
            } catch (Exception e) {
            }
        }
        bo.e("Event id is empty or too long in tracking Event");
        return false;
    }

    private boolean b(String str) {
        if (str == null) {
            return true;
        }
        if (str.trim().getBytes().length <= 256) {
            return true;
        }
        bo.e("Event label or value is empty or too long in tracking Event");
        return false;
    }

    private boolean b(Map<String, Object> map) {
        if (map != null) {
            if (!map.isEmpty()) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (a(entry.getKey()) && entry.getValue() != null) {
                        if ((entry.getValue() instanceof String) && !b(entry.getValue().toString())) {
                            return false;
                        }
                    }
                    return false;
                }
                return true;
            }
        }
        bo.e("map is null or empty in onEvent");
        return false;
    }

    public boolean a(List<String> list, int i, String str) {
        String str2;
        try {
            n a2 = n.a();
            if (list == null) {
                bo.e("cklist is null!");
            } else if (list.size() <= 0) {
                bo.e("the KeyList is null!");
                return false;
            } else {
                ArrayList arrayList = new ArrayList(list);
                if (!a2.b(arrayList.get(0))) {
                    bo.e("Primary key Invalid!");
                    return false;
                }
                if (arrayList.size() > 8) {
                    arrayList.clear();
                    arrayList.add(arrayList.get(0));
                    arrayList.add("__cc");
                    arrayList.add("illegal");
                } else if (!a2.a(arrayList)) {
                    arrayList.clear();
                    arrayList.add(arrayList.get(0));
                    arrayList.add("__cc");
                    arrayList.add("illegal");
                } else if (!a2.b(arrayList)) {
                    arrayList.clear();
                    arrayList.add(arrayList.get(0));
                    arrayList.add("__cc");
                    arrayList.add("illegal");
                } else {
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        String str3 = arrayList.get(i2);
                        if (str3.length() > 16) {
                            arrayList.remove(i2);
                            arrayList.add(i2, str3.substring(0, 16));
                        }
                    }
                }
                if (!a2.a(str)) {
                    bo.e("label  Invalid!");
                    str2 = "__illegal";
                } else {
                    str2 = str;
                }
                final HashMap hashMap = new HashMap();
                hashMap.put(arrayList, new l(arrayList, i, str2, System.currentTimeMillis()));
                bp.b(new br() { // from class: u.aly.ah.1
                    @Override // u.aly.br
                    public void a() {
                        m.a(ah.this.e).a(new f() { // from class: u.aly.ah.1.1
                            @Override // u.aly.f, u.aly.g
                            public void a(Object obj, boolean z) {
                                if (obj.equals("success")) {
                                }
                            }
                        }, hashMap);
                    }
                });
            }
        } catch (Exception e) {
        }
        return true;
    }

    private void a(Context context) {
        try {
            String string = aq.a(context).getString(a, null);
            if (!TextUtils.isEmpty(string)) {
                this.h = new JSONObject(string);
            }
            a();
        } catch (Exception e) {
        }
    }

    private void a() {
        int i = 0;
        try {
            if (!TextUtils.isEmpty(this.g.a)) {
                String[] split = this.g.a.split("!");
                JSONObject jSONObject = new JSONObject();
                if (this.h != null) {
                    for (String str : split) {
                        String a2 = bm.a(str, 128);
                        if (this.h.has(a2)) {
                            jSONObject.put(a2, this.h.get(a2));
                        }
                    }
                }
                this.h = new JSONObject();
                if (split.length >= 10) {
                    while (i < 10) {
                        a(split[i], jSONObject);
                        i++;
                    }
                } else {
                    while (i < split.length) {
                        a(split[i], jSONObject);
                        i++;
                    }
                }
                b(this.e);
                this.g.a = null;
            }
        } catch (Exception e) {
        }
    }

    private void a(String str, JSONObject jSONObject) throws JSONException {
        String a2 = bm.a(str, 128);
        if (jSONObject.has(a2)) {
            a(a2, ((Boolean) jSONObject.get(a2)).booleanValue());
        } else {
            a(a2, false);
        }
    }

    private void a(String str, boolean z) {
        try {
            if (!av.aR.equals(str) && !av.aP.equals(str) && !av.aO.equals(str) && !"id".equals(str) && !"ts".equals(str) && !this.h.has(str)) {
                this.h.put(str, z);
            }
        } catch (Exception e) {
        }
    }

    private void b(Context context) {
        try {
            if (this.h != null) {
                aq.a(this.e).edit().putString(a, this.h.toString()).commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package u.aly;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* compiled from: FieldMetaData.java */
/* loaded from: classes2.dex */
public class ci implements Serializable {
    private static Map<Class<? extends bw>, Map<? extends cd, ci>> d = new HashMap();
    public final String a;
    public final byte b;
    public final cj c;

    public ci(String str, byte b, cj cjVar) {
        this.a = str;
        this.b = b;
        this.c = cjVar;
    }

    public static void a(Class<? extends bw> cls, Map<? extends cd, ci> map) {
        d.put(cls, map);
    }

    public static Map<? extends cd, ci> a(Class<? extends bw> cls) {
        if (!d.containsKey(cls)) {
            try {
                cls.newInstance();
            } catch (IllegalAccessException e) {
                throw new RuntimeException("IllegalAccessException for TBase class: " + cls.getName() + ", message: " + e.getMessage());
            } catch (InstantiationException e2) {
                throw new RuntimeException("InstantiationException for TBase class: " + cls.getName() + ", message: " + e2.getMessage());
            }
        }
        return d.get(cls);
    }
}

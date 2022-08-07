package u.aly;

import java.lang.reflect.InvocationTargetException;

/* compiled from: TEnumHelper.java */
/* loaded from: classes2.dex */
public class cb {
    public static ca a(Class<? extends ca> cls, int i) {
        try {
            return (ca) cls.getMethod("findByValue", Integer.TYPE).invoke(null, Integer.valueOf(i));
        } catch (IllegalAccessException e) {
            return null;
        } catch (NoSuchMethodException e2) {
            return null;
        } catch (InvocationTargetException e3) {
            return null;
        }
    }
}

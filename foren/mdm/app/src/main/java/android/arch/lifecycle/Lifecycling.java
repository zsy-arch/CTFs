package android.arch.lifecycle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class Lifecycling {
    private static Map<Class, Constructor<? extends GenericLifecycleObserver>> sCallbackCache;
    private static Constructor<? extends GenericLifecycleObserver> sREFLECTIVE;

    Lifecycling() {
    }

    static {
        try {
            sREFLECTIVE = ReflectiveGenericLifecycleObserver.class.getDeclaredConstructor(Object.class);
        } catch (NoSuchMethodException e) {
        }
        sCallbackCache = new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static GenericLifecycleObserver getCallback(Object object) {
        if (object instanceof GenericLifecycleObserver) {
            return (GenericLifecycleObserver) object;
        }
        try {
            Class<?> klass = object.getClass();
            Constructor<? extends GenericLifecycleObserver> cachedConstructor = sCallbackCache.get(klass);
            if (cachedConstructor != null) {
                return (GenericLifecycleObserver) cachedConstructor.newInstance(object);
            }
            Constructor<? extends GenericLifecycleObserver> cachedConstructor2 = getGeneratedAdapterConstructor(klass);
            if (cachedConstructor2 == null) {
                cachedConstructor2 = sREFLECTIVE;
            } else if (!cachedConstructor2.isAccessible()) {
                cachedConstructor2.setAccessible(true);
            }
            sCallbackCache.put(klass, cachedConstructor2);
            return (GenericLifecycleObserver) cachedConstructor2.newInstance(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    @Nullable
    private static Constructor<? extends GenericLifecycleObserver> getGeneratedAdapterConstructor(Class<?> klass) {
        Package aPackage = klass.getPackage();
        String fullPackage = aPackage != null ? aPackage.getName() : "";
        String name = klass.getCanonicalName();
        if (name == null) {
            return null;
        }
        if (!fullPackage.isEmpty()) {
            name = name.substring(fullPackage.length() + 1);
        }
        String adapterName = getAdapterName(name);
        try {
            if (!fullPackage.isEmpty()) {
                adapterName = fullPackage + "." + adapterName;
            }
            return Class.forName(adapterName).getDeclaredConstructor(klass);
        } catch (ClassNotFoundException e) {
            Class<?> superclass = klass.getSuperclass();
            if (superclass != null) {
                return getGeneratedAdapterConstructor(superclass);
            }
            return null;
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException(e2);
        }
    }

    static String getAdapterName(String className) {
        return className.replace(".", "_") + "_LifecycleAdapter";
    }
}

package com.parse;

import android.app.Service;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class PPNSUtil {
    static String CLASS_PPNS_SERVICE = "com.parse.PPNSService";

    PPNSUtil() {
    }

    public static boolean isPPNSAvailable() {
        try {
            Class.forName(CLASS_PPNS_SERVICE);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static ProxyService newPPNSService(Service service) {
        try {
            return (ProxyService) Class.forName(CLASS_PPNS_SERVICE).getDeclaredConstructor(Service.class).newInstance(service);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException(e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException(e4);
        } catch (InvocationTargetException e5) {
            throw new RuntimeException(e5);
        }
    }
}

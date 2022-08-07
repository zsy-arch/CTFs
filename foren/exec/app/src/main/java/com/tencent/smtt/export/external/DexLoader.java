package com.tencent.smtt.export.external;

import android.content.Context;
import android.text.TextUtils;
import dalvik.system.DexClassLoader;
import dalvik.system.VMStack;
import e.a.a.a.a;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class DexLoader {
    public static final String JAVACORE_PACKAGE_PREFIX = "org.chromium";
    public static final String TAF_PACKAGE_PREFIX = "com.taf";
    public static final String TAG = "DexLoader";
    public static final String TBS_FUSION_DEX = "tbs_jars_fusion_dex";
    public static final String TBS_WEBVIEW_DEX = "webview_dex";
    public static final String TENCENT_PACKAGE_PREFIX = "com.tencent";
    public static boolean mCanUseDexLoaderProviderService = true;
    public static boolean mMttClassUseCorePrivate;
    public static boolean mUseSpeedyClassLoader;
    public static boolean mUseTbsCorePrivateClassLoader;
    public DexClassLoader mClassLoader;

    /* loaded from: classes.dex */
    public static class TbsCorePrivateClassLoader extends DexClassLoader {
        public TbsCorePrivateClassLoader(String str, String str2, String str3, ClassLoader classLoader) {
            super(str, str2, str3, classLoader);
        }

        @Override // java.lang.ClassLoader
        public Class<?> loadClass(String str, boolean z) {
            ClassLoader parent;
            if (str == null) {
                return super.loadClass(str, z);
            }
            boolean startsWith = str.startsWith(DexLoader.JAVACORE_PACKAGE_PREFIX);
            if (DexLoader.mMttClassUseCorePrivate) {
                startsWith = startsWith || str.startsWith(DexLoader.TENCENT_PACKAGE_PREFIX) || str.startsWith(DexLoader.TAF_PACKAGE_PREFIX);
            }
            if (!startsWith) {
                return super.loadClass(str, z);
            }
            Class<?> findLoadedClass = findLoadedClass(str);
            if (findLoadedClass != null) {
                return findLoadedClass;
            }
            try {
                String str2 = "WebCoreClassLoader - loadClass(" + str + "," + z + ")...";
                findLoadedClass = findClass(str);
            } catch (ClassNotFoundException unused) {
            }
            return (findLoadedClass != null || (parent = getParent()) == null) ? findLoadedClass : parent.loadClass(str);
        }
    }

    public DexLoader(Context context, String str, String str2) {
        this((String) null, context, new String[]{str}, str2);
    }

    public DexLoader(Context context, String[] strArr, String str) {
        this((String) null, context, strArr, str);
    }

    public DexLoader(Context context, String[] strArr, String str, DexLoader dexLoader) {
        DexClassLoader classLoader = dexLoader.getClassLoader();
        for (String str2 : strArr) {
            classLoader = createDexClassLoader(str2, str, context.getApplicationInfo().nativeLibraryDir, classLoader, context);
            this.mClassLoader = classLoader;
        }
    }

    public DexLoader(Context context, String[] strArr, String str, String str2) {
        ClassLoader classLoader = context.getClassLoader();
        String str3 = context.getApplicationInfo().nativeLibraryDir;
        if (!TextUtils.isEmpty(str2)) {
            StringBuilder a2 = a.a(str3);
            a2.append(File.pathSeparator);
            a2.append(str2);
            str3 = a2.toString();
        }
        int i = 0;
        ClassLoader classLoader2 = classLoader;
        while (i < strArr.length) {
            DexClassLoader createDexClassLoader = createDexClassLoader(strArr[i], str, str3, classLoader2, context);
            this.mClassLoader = createDexClassLoader;
            i++;
            classLoader2 = createDexClassLoader;
        }
    }

    public DexLoader(String str, Context context, String[] strArr, String str2) {
        this(str, context, strArr, str2, null);
    }

    public DexLoader(String str, Context context, String[] strArr, String str2, Map<String, Object> map) {
        initTbsSettings(map);
        ClassLoader callingClassLoader = VMStack.getCallingClassLoader();
        callingClassLoader = callingClassLoader == null ? context.getClassLoader() : callingClassLoader;
        a.b("Set base classLoader for DexClassLoader: ", callingClassLoader);
        DexClassLoader dexClassLoader = callingClassLoader;
        for (String str3 : strArr) {
            dexClassLoader = createDexClassLoader(str3, str2, str, dexClassLoader, context);
            this.mClassLoader = dexClassLoader;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x018f, code lost:
        if (r0 == null) goto L_0x01ab;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01cd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private dalvik.system.DexClassLoader createDexClassLoader(java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.ClassLoader r20, android.content.Context r21) {
        /*
            Method dump skipped, instructions count: 517
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.export.external.DexLoader.createDexClassLoader(java.lang.String, java.lang.String, java.lang.String, java.lang.ClassLoader, android.content.Context):dalvik.system.DexClassLoader");
    }

    public static void delete(File file) {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    delete(file2);
                }
                file.delete();
            }
        }
    }

    public static String getFileNameNoEx(String str) {
        int lastIndexOf;
        return (str == null || str.length() <= 0 || (lastIndexOf = str.lastIndexOf(46)) <= -1 || lastIndexOf >= str.length()) ? str : str.substring(0, lastIndexOf);
    }

    public static void initTbsSettings(Map<String, Object> map) {
        a.b("initTbsSettings - ", map);
        if (map != null) {
            try {
                Object obj = map.get(TbsCoreSettings.TBS_SETTINGS_USE_PRIVATE_CLASSLOADER);
                if (obj instanceof Boolean) {
                    mUseTbsCorePrivateClassLoader = ((Boolean) obj).booleanValue();
                }
                Object obj2 = map.get(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER);
                if (obj2 instanceof Boolean) {
                    mUseSpeedyClassLoader = ((Boolean) obj2).booleanValue();
                }
                Object obj3 = map.get(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE);
                if (obj3 instanceof Boolean) {
                    mCanUseDexLoaderProviderService = ((Boolean) obj3).booleanValue();
                }
                Object obj4 = map.get(TbsCoreSettings.TBS_SETTINGS_PRAVITE_MTT_CLASSES);
                if (obj4 instanceof Boolean) {
                    mMttClassUseCorePrivate = ((Boolean) obj4).booleanValue();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private boolean shouldUseTbsCorePrivateClassLoader(String str) {
        if (!mUseTbsCorePrivateClassLoader) {
            return false;
        }
        return str.contains(TBS_FUSION_DEX) || str.contains(TBS_WEBVIEW_DEX);
    }

    public DexClassLoader getClassLoader() {
        return this.mClassLoader;
    }

    public Object getStaticField(String str, String str2) {
        try {
            Field field = this.mClassLoader.loadClass(str).getField(str2);
            field.setAccessible(true);
            return field.get(null);
        } catch (Throwable unused) {
            DexLoader.class.getSimpleName();
            String str3 = "'" + str + "' get field '" + str2 + "' failed";
            return null;
        }
    }

    public Object invokeMethod(Object obj, String str, String str2, Class<?>[] clsArr, Object... objArr) {
        try {
            Method method = this.mClassLoader.loadClass(str).getMethod(str2, clsArr);
            method.setAccessible(true);
            return method.invoke(obj, objArr);
        } catch (Throwable unused) {
            DexLoader.class.getSimpleName();
            String str3 = "'" + str + "' invoke method '" + str2 + "' failed";
            return null;
        }
    }

    public Object invokeStaticMethod(String str, String str2, Class<?>[] clsArr, Object... objArr) {
        try {
            Method method = this.mClassLoader.loadClass(str).getMethod(str2, clsArr);
            method.setAccessible(true);
            return method.invoke(null, objArr);
        } catch (Throwable th) {
            if (str2 == null || !str2.equalsIgnoreCase("initTesRuntimeEnvironment")) {
                DexLoader.class.getSimpleName();
                String str3 = "'" + str + "' invoke static method '" + str2 + "' failed";
                return null;
            }
            DexLoader.class.getSimpleName();
            String str4 = "'" + str + "' invoke static method '" + str2 + "' failed";
            return th;
        }
    }

    public Class<?> loadClass(String str) {
        try {
            return this.mClassLoader.loadClass(str);
        } catch (Throwable unused) {
            DexLoader.class.getSimpleName();
            String str2 = "loadClass '" + str + "' failed";
            return null;
        }
    }

    public Object newInstance(String str) {
        try {
            return this.mClassLoader.loadClass(str).newInstance();
        } catch (Throwable unused) {
            DexLoader.class.getSimpleName();
            String str2 = "create " + str + " instance failed";
            return null;
        }
    }

    public Object newInstance(String str, Class<?>[] clsArr, Object... objArr) {
        try {
            return this.mClassLoader.loadClass(str).getConstructor(clsArr).newInstance(objArr);
        } catch (Throwable th) {
            if ("com.tencent.smtt.webkit.adapter.X5WebViewAdapter".equalsIgnoreCase(str)) {
                DexLoader.class.getSimpleName();
                String str2 = "'newInstance " + str + " failed";
                return th;
            }
            DexLoader.class.getSimpleName();
            String str3 = "create '" + str + "' instance failed";
            return null;
        }
    }

    public void setStaticField(String str, String str2, Object obj) {
        try {
            Field field = this.mClassLoader.loadClass(str).getField(str2);
            field.setAccessible(true);
            field.set(null, obj);
        } catch (Throwable unused) {
            DexLoader.class.getSimpleName();
            String str3 = "'" + str + "' set field '" + str2 + "' failed";
        }
    }
}

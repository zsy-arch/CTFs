package com.tencent.smtt.export.external;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.tencent.smtt.sdk.BuildConfig;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import e.a.a.a.a;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class DexClassLoaderProvider extends DexClassLoader {
    public static final String IS_FIRST_LOAD_DEX_FLAG_FILE = "is_first_load_dex_flag_file";
    public static final String LAST_DEX_NAME = "tbs_jars_fusion_dex.jar";
    public static final long LOAD_DEX_DELAY = 3000;
    public static final String LOGTAG = "dexloader";
    public static DexClassLoader mClassLoaderOriginal;
    public static Context mContext;
    public static boolean mForceLoadDexFlag;
    public static DexClassLoaderProvider mInstance;
    public static String mRealDexPath;
    public static Service mService;
    public SpeedyDexClassLoader mClassLoader;

    /* loaded from: classes.dex */
    public static class SpeedyDexClassLoader extends BaseDexClassLoader {
        public SpeedyDexClassLoader(String str, File file, String str2, ClassLoader classLoader) {
            super(str, null, str2, classLoader);
        }

        @Override // java.lang.ClassLoader
        public Package definePackage(String str, String str2, String str3, String str4, String str5, String str6, String str7, URL url) {
            return super.definePackage(str, str2, str3, str4, str5, str6, str7, url);
        }

        @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
        public Class<?> findClass(String str) {
            return super.findClass(str);
        }

        @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
        public URL findResource(String str) {
            return super.findResource(str);
        }

        @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
        public Enumeration<URL> findResources(String str) {
            return super.findResources(str);
        }

        @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
        public synchronized Package getPackage(String str) {
            return super.getPackage(str);
        }

        @Override // java.lang.ClassLoader
        public Package[] getPackages() {
            return super.getPackages();
        }

        @Override // java.lang.ClassLoader
        public Class<?> loadClass(String str, boolean z) {
            return super.loadClass(str, z);
        }
    }

    public DexClassLoaderProvider(String str, String str2, String str3, ClassLoader classLoader, boolean z) {
        super(str, str2, str3, classLoader);
        this.mClassLoader = null;
        if (z) {
            StringBuilder a2 = a.a("SpeedyDexClassLoader: ");
            a2.append(mRealDexPath);
            a2.toString();
            this.mClassLoader = new SpeedyDexClassLoader(mRealDexPath, null, str3, classLoader);
            return;
        }
        StringBuilder a3 = a.a("DexClassLoader: ");
        a3.append(mRealDexPath);
        a3.toString();
        this.mClassLoader = null;
    }

    public static DexClassLoader createDexClassLoader(String str, String str2, String str3, ClassLoader classLoader, Context context) {
        String str4 = "new DexClassLoaderDelegate: " + str + ", context: " + context;
        mContext = context.getApplicationContext();
        mRealDexPath = str;
        int lastIndexOf = str.lastIndexOf("/") + 1;
        String a2 = a.a(str.substring(0, lastIndexOf), "fake_dex.jar");
        String substring = str.substring(lastIndexOf);
        if (!supportSpeedyClassLoader() || !is_first_load_tbs_dex(str2, substring)) {
            String str5 = "new DexClassLoaderDelegate -- real: " + str;
            mInstance = new DexClassLoaderProvider(str, str2, str3, classLoader, false);
        } else {
            String str6 = "new DexClassLoaderDelegate -- fake: " + a2;
            set_first_load_tbs_dex(str2, substring);
            mInstance = new DexClassLoaderProvider(a2, str2, str3, classLoader, true);
            doAsyncDexLoad(substring, str, str2, str3, classLoader);
        }
        return mInstance;
    }

    public static void doAsyncDexLoad(final String str, final String str2, final String str3, final String str4, final ClassLoader classLoader) {
        if (shouldUseDexLoaderService()) {
            new Timer().schedule(new TimerTask() { // from class: com.tencent.smtt.export.external.DexClassLoaderProvider.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    try {
                        ArrayList<String> arrayList = new ArrayList<>(4);
                        arrayList.add(0, str);
                        arrayList.add(1, str2);
                        arrayList.add(2, str3);
                        arrayList.add(3, str4);
                        Intent intent = new Intent(DexClassLoaderProvider.mContext, DexClassLoaderProviderService.class);
                        intent.putStringArrayListExtra("dex2oat", arrayList);
                        DexClassLoaderProvider.mContext.startService(intent);
                        String str5 = "shouldUseDexLoaderService(" + str + ", " + intent + ")";
                    } catch (SecurityException unused) {
                    } catch (Throwable th) {
                        a.b("after shouldUseDexLoaderService exception: ", th);
                    }
                }
            }, LOAD_DEX_DELAY);
            return;
        }
        String str5 = "Background real dex loading(" + str + ")";
        new Timer().schedule(new TimerTask() { // from class: com.tencent.smtt.export.external.DexClassLoaderProvider.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                boolean z;
                try {
                    File file = new File(str2.replace(".jar", ".dex"));
                    if (!file.exists() || file.length() == 0) {
                        String str6 = BuildConfig.FLAVOR + file + " does not existed!";
                        z = false;
                    } else {
                        String str7 = BuildConfig.FLAVOR + file + " existed!";
                        z = true;
                    }
                    File file2 = new File(str3);
                    File file3 = new File(str2);
                    boolean exists = file2.exists();
                    boolean isDirectory = file2.isDirectory();
                    boolean exists2 = file3.exists();
                    if (exists && isDirectory && exists2) {
                        long currentTimeMillis = System.currentTimeMillis();
                        new DexClassLoader(str2, str3, str4, classLoader);
                        String str8 = BuildConfig.FLAVOR + String.format("load_dex completed -- cl_cost: %d, existed: %b", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Boolean.valueOf(z));
                        if (DexClassLoaderProvider.mForceLoadDexFlag && DexClassLoaderProvider.LAST_DEX_NAME.equals(str)) {
                            String str9 = "Stop provider service after loading " + str;
                            if (DexClassLoaderProvider.mService != null) {
                                DexClassLoaderProvider.mService.stopSelf();
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    String str10 = "dex loading exception(" + exists + ", " + isDirectory + ", " + exists2 + ")";
                } catch (Throwable th) {
                    a.b("@AsyncDexLoad task exception: ", th);
                }
            }
        }, LOAD_DEX_DELAY);
    }

    public static boolean is_first_load_tbs_dex(String str, String str2) {
        if (mForceLoadDexFlag) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append("_");
        sb.append(IS_FIRST_LOAD_DEX_FLAG_FILE);
        return !new File(str, sb.toString()).exists();
    }

    public static void setForceLoadDexFlag(boolean z, Service service) {
        mForceLoadDexFlag = z;
        mService = service;
    }

    public static void set_first_load_tbs_dex(String str, String str2) {
        File file = new File(str, str2 + "_" + IS_FIRST_LOAD_DEX_FLAG_FILE);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static boolean shouldUseDexLoaderService() {
        return !mForceLoadDexFlag && DexLoader.mCanUseDexLoaderProviderService;
    }

    public static boolean supportSpeedyClassLoader() {
        return Build.VERSION.SDK_INT != 21 || DexLoader.mCanUseDexLoaderProviderService;
    }

    private boolean useSelfClassloader() {
        return this.mClassLoader == null;
    }

    @Override // java.lang.ClassLoader
    public void clearAssertionStatus() {
        if (useSelfClassloader()) {
            super.clearAssertionStatus();
        } else {
            this.mClassLoader.clearAssertionStatus();
        }
    }

    @Override // java.lang.ClassLoader
    public Package definePackage(String str, String str2, String str3, String str4, String str5, String str6, String str7, URL url) {
        return useSelfClassloader() ? super.definePackage(str, str2, str3, str4, str5, str6, str7, url) : this.mClassLoader.definePackage(str, str2, str3, str4, str5, str6, str7, url);
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    public Class<?> findClass(String str) {
        return useSelfClassloader() ? super.findClass(str) : this.mClassLoader.findClass(str);
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    public String findLibrary(String str) {
        return useSelfClassloader() ? super.findLibrary(str) : this.mClassLoader.findLibrary(str);
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    public URL findResource(String str) {
        return useSelfClassloader() ? super.findResource(str) : this.mClassLoader.findResource(str);
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    public Enumeration<URL> findResources(String str) {
        return useSelfClassloader() ? super.findResources(str) : this.mClassLoader.findResources(str);
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    public synchronized Package getPackage(String str) {
        if (useSelfClassloader()) {
            return super.getPackage(str);
        }
        return this.mClassLoader.getPackage(str);
    }

    @Override // java.lang.ClassLoader
    public Package[] getPackages() {
        return useSelfClassloader() ? super.getPackages() : this.mClassLoader.getPackages();
    }

    @Override // java.lang.ClassLoader
    public URL getResource(String str) {
        return useSelfClassloader() ? super.getResource(str) : this.mClassLoader.getResource(str);
    }

    @Override // java.lang.ClassLoader
    public InputStream getResourceAsStream(String str) {
        return useSelfClassloader() ? getResourceAsStream(str) : this.mClassLoader.getResourceAsStream(str);
    }

    @Override // java.lang.ClassLoader
    public Enumeration<URL> getResources(String str) {
        return useSelfClassloader() ? super.getResources(str) : this.mClassLoader.getResources(str);
    }

    @Override // java.lang.ClassLoader
    public Class<?> loadClass(String str) {
        return useSelfClassloader() ? super.loadClass(str) : this.mClassLoader.loadClass(str);
    }

    @Override // java.lang.ClassLoader
    public Class<?> loadClass(String str, boolean z) {
        return useSelfClassloader() ? super.loadClass(str, z) : this.mClassLoader.loadClass(str, z);
    }

    @Override // java.lang.ClassLoader
    public void setClassAssertionStatus(String str, boolean z) {
        if (useSelfClassloader()) {
            super.setClassAssertionStatus(str, z);
        } else {
            this.mClassLoader.setClassAssertionStatus(str, z);
        }
    }

    @Override // java.lang.ClassLoader
    public void setDefaultAssertionStatus(boolean z) {
        if (useSelfClassloader()) {
            super.setDefaultAssertionStatus(z);
        } else {
            this.mClassLoader.setDefaultAssertionStatus(z);
        }
    }

    @Override // java.lang.ClassLoader
    public void setPackageAssertionStatus(String str, boolean z) {
        if (useSelfClassloader()) {
            super.setPackageAssertionStatus(str, z);
        } else {
            this.mClassLoader.setPackageAssertionStatus(str, z);
        }
    }

    @Override // dalvik.system.BaseDexClassLoader
    public String toString() {
        return useSelfClassloader() ? super.toString() : this.mClassLoader.toString();
    }
}

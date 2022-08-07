package com.amap.api.col;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: ResourcesUtil.java */
/* loaded from: classes.dex */
public class fo {
    private static Context e;
    private static AssetManager a = null;
    private static Resources b = null;
    private static Resources c = null;
    private static boolean d = true;
    private static String f = "autonavi_Resource";
    private static String g = "1_1_0";
    private static String h = ".png";
    private static String i = ".jar";
    private static String j = f + g + i;
    private static String k = f + g + h;
    private static String l = "";
    private static String m = l + j;
    private static Resources.Theme n = null;
    private static Resources.Theme o = null;
    private static Field p = null;
    private static Field q = null;
    private static Activity r = null;

    public static boolean a(Context context) {
        try {
            e = context;
            l = fn.a(e).getAbsolutePath() + "/";
            m = l + j;
            if (!d) {
                return true;
            }
            if (!b(context)) {
                return false;
            }
            a = b(m);
            b = a(context, a);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public static Resources a() {
        return b == null ? e.getResources() : b;
    }

    private static Resources a(Context context, AssetManager assetManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        displayMetrics.setToDefaults();
        return new Resources(assetManager, displayMetrics, context.getResources().getConfiguration());
    }

    private static AssetManager b(String str) {
        Throwable th;
        AssetManager assetManager;
        try {
            Class<?> cls = Class.forName("android.content.res.AssetManager");
            assetManager = (AssetManager) cls.getConstructor(null).newInstance(null);
            try {
                cls.getDeclaredMethod("addAssetPath", String.class).invoke(assetManager, str);
            } catch (Throwable th2) {
                th = th2;
                gr.b(th, "ResourcesUtil", "getAssetManager(String apkPath)");
                return assetManager;
            }
        } catch (Throwable th3) {
            assetManager = null;
            th = th3;
        }
        return assetManager;
    }

    private static boolean b(Context context) {
        OutputStream outputStream;
        InputStream inputStream;
        boolean z;
        OutputStream outputStream2;
        Exception e2;
        String str;
        String str2;
        try {
            outputStream = null;
            z = true;
            c(context);
            outputStream2 = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            inputStream = context.getResources().getAssets().open(k);
            try {
                if (b(inputStream)) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e3) {
                            e = e3;
                            e.printStackTrace();
                            str = "ResourcesUtil";
                            str2 = "copyResourceJarToAppFilesDir(Context ctx)";
                            gr.b(e, str, str2);
                            return z;
                        }
                    }
                    if (0 != 0) {
                        outputStream2.close();
                    }
                } else {
                    f();
                    outputStream = a(inputStream);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            e = e4;
                            e.printStackTrace();
                            str = "ResourcesUtil";
                            str2 = "copyResourceJarToAppFilesDir(Context ctx)";
                            gr.b(e, str, str2);
                            return z;
                        }
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
            } catch (Exception e5) {
                e2 = e5;
                e2.printStackTrace();
                gr.b(e2, "ResourcesUtil", "copyResourceJarToAppFilesDir(Context ctx)");
                z = false;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e6) {
                        e = e6;
                        e.printStackTrace();
                        str = "ResourcesUtil";
                        str2 = "copyResourceJarToAppFilesDir(Context ctx)";
                        gr.b(e, str, str2);
                        return z;
                    }
                }
                if (0 != 0) {
                    outputStream2.close();
                }
                return z;
            }
        } catch (Exception e7) {
            e2 = e7;
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                    gr.b(e8, "ResourcesUtil", "copyResourceJarToAppFilesDir(Context ctx)");
                    throw th;
                }
            }
            if (outputStream != null) {
                outputStream2.close();
            }
            throw th;
        }
        return z;
    }

    private static OutputStream a(InputStream inputStream) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(l, j));
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read <= 0) {
                return fileOutputStream;
            }
            fileOutputStream.write(bArr, 0, read);
        }
    }

    private static boolean b(InputStream inputStream) throws IOException {
        File file = new File(m);
        long length = file.length();
        int available = inputStream.available();
        if (!file.exists() || length != available) {
            return false;
        }
        inputStream.close();
        return true;
    }

    private static void f() {
        File[] listFiles = new File(l).listFiles(new a());
        if (listFiles != null && listFiles.length > 0) {
            for (File file : listFiles) {
                if (file.delete()) {
                }
            }
        }
    }

    private static void c(Context context) {
        l = context.getFilesDir().getAbsolutePath();
        m = l + "/" + j;
    }

    public static View a(Activity activity, int i2, ViewGroup viewGroup) {
        Throwable th;
        View view;
        XmlResourceParser xml = a().getXml(i2);
        if (!d) {
            return LayoutInflater.from(activity).inflate(xml, viewGroup);
        }
        try {
            try {
                boolean a2 = a(activity);
                view = LayoutInflater.from(activity).inflate(xml, viewGroup);
                if (a2) {
                    try {
                        b(activity);
                    } catch (Throwable th2) {
                        th = th2;
                        th.printStackTrace();
                        gr.b(th, "ResourcesUtil", "selfInflate(Activity activity, int resource, ViewGroup root)");
                        return view;
                    }
                }
            } catch (Throwable th3) {
                view = null;
                th = th3;
            }
            return view;
        } finally {
            xml.close();
        }
    }

    public static boolean a(Activity activity) {
        if (c != null) {
            return false;
        }
        try {
            if (p == null) {
                p = h();
            }
            if (q == null) {
                q = i();
            }
            if (n == null) {
                n = g();
            }
            Context baseContext = activity.getBaseContext();
            c = (Resources) q.get(baseContext);
            o = (Resources.Theme) p.get(activity);
            q.set(baseContext, b);
            p.set(activity, n);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "ResourcesUtil", "switchToJarResourcesAndTheme(Activity activity)");
            return false;
        }
    }

    private static Resources.Theme g() {
        if (n == null) {
            if (a == null) {
                a = b(m);
            }
            if (b == null) {
                b = a(r, a);
            }
            n = b.newTheme();
            n.applyStyle(a("com.android.internal.R.style.Theme"), true);
        }
        return n;
    }

    private static Field h() {
        try {
            p = Class.forName("android.view.ContextThemeWrapper").getDeclaredField("mTheme");
            p.setAccessible(true);
        } catch (Throwable th) {
            gr.b(th, "ResourcesUtil", "getActivityThemeField()");
        }
        return p;
    }

    private static Field i() {
        try {
            q = Class.forName("android.app.ContextImpl").getDeclaredField("mResources");
            q.setAccessible(true);
        } catch (Throwable th) {
            gr.b(th, "ResourcesUtil", "getContextResourcesField()");
        }
        return q;
    }

    public static int a(String str) {
        try {
            String substring = str.substring(0, str.indexOf(".R.") + 2);
            int lastIndexOf = str.lastIndexOf(".");
            String substring2 = str.substring(lastIndexOf + 1, str.length());
            String substring3 = str.substring(0, lastIndexOf);
            return Class.forName(substring + "$" + substring3.substring(substring3.lastIndexOf(".") + 1, substring3.length())).getDeclaredField(substring2).getInt(null);
        } catch (Throwable th) {
            gr.b(th, "ResourcesUtil", "getInnerRIdValue(String rStrnig)");
            return -1;
        }
    }

    public static void b(Activity activity) {
        if (c != null) {
            try {
                q.set(activity.getBaseContext(), c);
                p.set(activity, o);
            } finally {
                c = null;
            }
        }
    }

    public static Animation a(Context context, int i2) throws Resources.NotFoundException {
        XmlResourceParser xmlResourceParser;
        try {
            Resources.NotFoundException notFoundException = new Resources.NotFoundException();
            xmlResourceParser = null;
            try {
                try {
                    xmlResourceParser = a().getAnimation(i2);
                    return a(context, xmlResourceParser);
                } catch (IOException e2) {
                    gr.b(e2, "ResourcesUtil", "loadAnimation(Context context, int id)");
                    throw notFoundException;
                }
            } catch (XmlPullParserException e3) {
                gr.b(e3, "ResourcesUtil", "loadAnimation(Context context, int id)");
                throw notFoundException;
            }
        } finally {
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
        }
    }

    private static Animation a(Context context, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return a(context, xmlPullParser, null, Xml.asAttributeSet(xmlPullParser));
    }

    private static Animation a(Context context, XmlPullParser xmlPullParser, AnimationSet animationSet, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        int next;
        Animation translateAnimation;
        int depth = xmlPullParser.getDepth();
        do {
            next = xmlPullParser.next();
            if ((next != 3 || xmlPullParser.getDepth() > depth) && next != 1) {
            }
        } while (next != 2);
        String name = xmlPullParser.getName();
        if (name.equals("set")) {
            translateAnimation = new AnimationSet(context, attributeSet);
            a(context, xmlPullParser, (AnimationSet) translateAnimation, attributeSet);
        } else if (name.equals("alpha")) {
            translateAnimation = new AlphaAnimation(context, attributeSet);
        } else if (name.equals("scale")) {
            translateAnimation = new ScaleAnimation(context, attributeSet);
        } else if (name.equals("rotate")) {
            translateAnimation = new RotateAnimation(context, attributeSet);
        } else if (name.equals("translate")) {
            translateAnimation = new TranslateAnimation(context, attributeSet);
        } else {
            throw new RuntimeException("Unknown animation name: " + xmlPullParser.getName());
        }
        if (animationSet != null) {
            animationSet.addAnimation(translateAnimation);
        }
        return translateAnimation;
    }

    public static void b() {
    }

    /* compiled from: ResourcesUtil.java */
    /* loaded from: classes.dex */
    public static class a implements FilenameFilter {
        a() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.startsWith(fo.f) && !str.endsWith(new StringBuilder().append(fo.g).append(fo.i).toString());
        }
    }
}

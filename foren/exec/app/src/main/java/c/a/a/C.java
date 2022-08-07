package c.a.a;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StrictMode;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.LongSparseArray;
import android.util.TypedValue;
import android.util.Xml;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import bccsejw.sxexrix.zaswnwt.MyApplication;
import c.a.f.ua;
import c.a.f.ya;
import c.e.b.a.e;
import c.e.c;
import c.e.c.a.d;
import c.e.c.b;
import c.e.f.a;
import c.m.a.a.f;
import com.tencent.smtt.sdk.BuildConfig;
import com.tencent.smtt.sdk.TbsListener;
import e.a.a.a.a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class C {

    /* renamed from: a */
    public static Field f315a;

    /* renamed from: b */
    public static boolean f316b;

    /* renamed from: c */
    public static Class f317c;

    /* renamed from: d */
    public static boolean f318d;

    /* renamed from: e */
    public static Field f319e;
    public static boolean f;
    public static Field g;
    public static boolean h;
    public static Method i;
    public static boolean j;
    public static Method k;
    public static boolean l;
    public static Field m;
    public static boolean n;
    public static Method o;
    public static boolean p;
    public static Field q;
    public static boolean r;
    public static String s;
    public static String t;
    public static Toast u;

    public static Toast a(CharSequence charSequence, int i2) {
        Toast toast = u;
        if (toast == null) {
            u = Toast.makeText(MyApplication.f301a, charSequence, i2);
        } else {
            toast.setText(charSequence);
            u.setDuration(i2);
        }
        return u;
    }

    public static void b(CharSequence charSequence, int i2) {
        a(charSequence, i2).show();
    }

    public static boolean b(int i2) {
        return i2 >= 28 && i2 <= 31;
    }

    public static Path c(String str) {
        Path path = new Path();
        b[] b2 = b(str);
        if (b2 == null) {
            return null;
        }
        try {
            b.a(b2, path);
            return path;
        } catch (RuntimeException e2) {
            throw new RuntimeException(a.a("Error in parsing ", str), e2);
        }
    }

    public static String d(String str) {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), 1024);
            try {
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                try {
                    bufferedReader.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                return readLine;
            } catch (IOException unused) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException unused2) {
            bufferedReader = null;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x008a, code lost:
        if (r16 == false) goto L_0x008c;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0097 A[Catch: NumberFormatException -> 0x00b8, LOOP:3: B:26:0x006e->B:44:0x0097, LOOP_END, TryCatch #0 {NumberFormatException -> 0x00b8, blocks: (B:23:0x005a, B:26:0x006e, B:28:0x0074, B:32:0x0080, B:44:0x0097, B:46:0x009d, B:49:0x00ad, B:51:0x00b2), top: B:66:0x005a }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x009d A[Catch: NumberFormatException -> 0x00b8, TryCatch #0 {NumberFormatException -> 0x00b8, blocks: (B:23:0x005a, B:26:0x006e, B:28:0x0074, B:32:0x0080, B:44:0x0097, B:46:0x009d, B:49:0x00ad, B:51:0x00b2), top: B:66:0x005a }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00ad A[Catch: NumberFormatException -> 0x00b8, TryCatch #0 {NumberFormatException -> 0x00b8, blocks: (B:23:0x005a, B:26:0x006e, B:28:0x0074, B:32:0x0080, B:44:0x0097, B:46:0x009d, B:49:0x00ad, B:51:0x00b2), top: B:66:0x005a }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00e3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0096 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static c.e.c.b[] b(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.C.b(java.lang.String):c.e.c.b[]");
    }

    public static int c(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, int i3) {
        return !a(xmlPullParser, str) ? i3 : typedArray.getResourceId(i2, i3);
    }

    public static InputConnection a(InputConnection inputConnection, EditorInfo editorInfo, View view) {
        if (inputConnection != null && editorInfo.hintText == null) {
            ViewParent parent = view.getParent();
            while (true) {
                if (!(parent instanceof View)) {
                    break;
                } else if (parent instanceof ya) {
                    editorInfo.hintText = ((ya) parent).a();
                    break;
                } else {
                    parent = parent.getParent();
                }
            }
        }
        return inputConnection;
    }

    public static Shader.TileMode c(int i2) {
        if (i2 == 1) {
            return Shader.TileMode.REPEAT;
        }
        if (i2 != 2) {
            return Shader.TileMode.CLAMP;
        }
        return Shader.TileMode.MIRROR;
    }

    public static void c(TextView textView, int i2) {
        a(i2);
        int fontMetricsInt = textView.getPaint().getFontMetricsInt(null);
        if (i2 != fontMetricsInt) {
            textView.setLineSpacing(i2 - fontMetricsInt, 1.0f);
        }
    }

    public static void a(Object obj, StringBuilder sb) {
        int lastIndexOf;
        if (obj == null) {
            sb.append("null");
            return;
        }
        String simpleName = obj.getClass().getSimpleName();
        if (simpleName.length() <= 0 && (lastIndexOf = (simpleName = obj.getClass().getName()).lastIndexOf(46)) > 0) {
            simpleName = simpleName.substring(lastIndexOf + 1);
        }
        sb.append(simpleName);
        sb.append('{');
        sb.append(Integer.toHexString(System.identityHashCode(obj)));
    }

    public static /* synthetic */ String a() {
        boolean z;
        String str;
        String str2;
        if (Environment.getExternalStorageState().equals("mounted")) {
            str2 = d.a.a.c.b.f1612a;
            File file = new File(str2);
            z = !file.exists() ? file.mkdirs() : true;
        } else {
            z = false;
        }
        if (z) {
            StringBuilder sb = new StringBuilder();
            str = d.a.a.c.b.f1612a;
            sb.append(str);
            sb.append(File.separator);
            return sb.toString();
        }
        return d.a.a.c.b.f1613b + File.separator;
    }

    public static int b(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, int i3) {
        return !a(xmlPullParser, str) ? i3 : typedArray.getInt(i2, i3);
    }

    public static TypedValue b(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2) {
        if (!a(xmlPullParser, str)) {
            return null;
        }
        return typedArray.peekValue(i2);
    }

    public static String b(Context context, ComponentName componentName) {
        String string;
        ActivityInfo activityInfo = context.getPackageManager().getActivityInfo(componentName, TbsListener.ErrorCode.DOWNLOAD_INTERRUPT);
        int i2 = Build.VERSION.SDK_INT;
        String str = activityInfo.parentActivityName;
        if (str != null) {
            return str;
        }
        if (activityInfo.metaData == null || (string = activityInfo.metaData.getString("android.support.PARENT_ACTIVITY")) == null) {
            return null;
        }
        if (string.charAt(0) != '.') {
            return string;
        }
        return context.getPackageName() + string;
    }

    public static void a(View view, CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setTooltipText(charSequence);
            return;
        }
        ua uaVar = ua.f649a;
        if (uaVar != null && uaVar.f651c == view) {
            ua.a((ua) null);
        }
        if (TextUtils.isEmpty(charSequence)) {
            ua uaVar2 = ua.f650b;
            if (uaVar2 != null && uaVar2.f651c == view) {
                uaVar2.b();
            }
            view.setOnLongClickListener(null);
            view.setLongClickable(false);
            view.setOnHoverListener(null);
            return;
        }
        new ua(view, charSequence);
    }

    public static Drawable b(Drawable drawable) {
        return (Build.VERSION.SDK_INT < 23 && !(drawable instanceof c.e.c.a.a)) ? new d(drawable) : drawable;
    }

    public static void b(TextView textView, int i2) {
        int i3;
        a(i2);
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i4 = Build.VERSION.SDK_INT;
        if (textView.getIncludeFontPadding()) {
            i3 = fontMetricsInt.bottom;
        } else {
            i3 = fontMetricsInt.descent;
        }
        if (i2 > Math.abs(i3)) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i2 - i3);
        }
    }

    public static boolean a(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null;
    }

    public static float[] a(float[] fArr, int i2, int i3) {
        if (i2 <= i3) {
            int length = fArr.length;
            if (i2 < 0 || i2 > length) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i4 = i3 - i2;
            int min = Math.min(i4, length - i2);
            float[] fArr2 = new float[i4];
            System.arraycopy(fArr, i2, fArr2, 0, min);
            return fArr2;
        }
        throw new IllegalArgumentException();
    }

    public static File a(Context context) {
        StringBuilder a2 = a.a(".font");
        a2.append(Process.myPid());
        a2.append("-");
        a2.append(Process.myTid());
        a2.append("-");
        String sb = a2.toString();
        for (int i2 = 0; i2 < 100; i2++) {
            File cacheDir = context.getCacheDir();
            File file = new File(cacheDir, sb + i2);
            if (file.createNewFile()) {
                return file;
            }
        }
        return null;
    }

    public static Interpolator a(Context context, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        return AnimationUtils.loadInterpolator(context, i2);
    }

    public static float a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, float f2) {
        return !a(xmlPullParser, str) ? f2 : typedArray.getFloat(i2, f2);
    }

    public static boolean a(String str) {
        String str2 = s;
        if (str2 != null) {
            return str2.equals(str);
        }
        String d2 = d("ro.miui.ui.version.name");
        t = d2;
        if (!TextUtils.isEmpty(d2)) {
            s = "MIUI";
        } else {
            String d3 = d("ro.build.version.emui");
            t = d3;
            if (!TextUtils.isEmpty(d3)) {
                s = "EMUI";
            } else {
                String d4 = d("ro.build.version.opporom");
                t = d4;
                if (!TextUtils.isEmpty(d4)) {
                    s = "OPPO";
                } else {
                    String d5 = d("ro.vivo.os.version");
                    t = d5;
                    if (!TextUtils.isEmpty(d5)) {
                        s = "VIVO";
                    } else {
                        String d6 = d("ro.smartisan.version");
                        t = d6;
                        if (!TextUtils.isEmpty(d6)) {
                            s = "SMARTISAN";
                        } else {
                            t = Build.DISPLAY;
                            if (t.toUpperCase().contains("FLYME")) {
                                s = "FLYME";
                            } else {
                                t = "unknown";
                                s = Build.MANUFACTURER.toUpperCase();
                            }
                        }
                    }
                }
            }
        }
        return s.equals(str);
    }

    public static void a(PopupWindow popupWindow, boolean z) {
        if (Build.VERSION.SDK_INT >= 23) {
            popupWindow.setOverlapAnchor(z);
            return;
        }
        if (!r) {
            try {
                q = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                q.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            r = true;
        }
        Field field = q;
        if (field != null) {
            try {
                field.set(popupWindow, Boolean.valueOf(z));
            } catch (IllegalAccessException unused2) {
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v16, types: [java.lang.Object[], java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.content.res.ColorStateList a(android.content.res.Resources r17, org.xmlpull.v1.XmlPullParser r18, android.util.AttributeSet r19, android.content.res.Resources.Theme r20) {
        /*
            Method dump skipped, instructions count: 294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.C.a(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):android.content.res.ColorStateList");
    }

    public static int a(Object... objArr) {
        int i2 = Build.VERSION.SDK_INT;
        return Objects.hash(objArr);
    }

    public static boolean a(Drawable drawable) {
        int i2 = Build.VERSION.SDK_INT;
        return drawable.isAutoMirrored();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0047 A[Catch: Throwable -> 0x004e, all -> 0x004b, TryCatch #3 {Throwable -> 0x004e, blocks: (B:8:0x0013, B:10:0x002c, B:22:0x0043, B:23:0x0047, B:24:0x004a), top: B:38:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0056 A[Catch: Throwable -> 0x005a, TRY_ENTER, TRY_LEAVE, TryCatch #4 {IOException -> 0x0063, blocks: (B:3:0x0005, B:6:0x000f, B:11:0x002f, B:33:0x005f, B:34:0x0062, B:32:0x0056), top: B:39:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005f A[Catch: IOException -> 0x0063, TryCatch #4 {IOException -> 0x0063, blocks: (B:3:0x0005, B:6:0x000f, B:11:0x002f, B:33:0x005f, B:34:0x0062, B:32:0x0056), top: B:39:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x003e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.nio.ByteBuffer a(android.content.Context r8, android.os.CancellationSignal r9, android.net.Uri r10) {
        /*
            android.content.ContentResolver r8 = r8.getContentResolver()
            r0 = 0
            java.lang.String r1 = "r"
            android.os.ParcelFileDescriptor r8 = r8.openFileDescriptor(r10, r1, r9)     // Catch: IOException -> 0x0063
            if (r8 != 0) goto L_0x0013
            if (r8 == 0) goto L_0x0012
            r8.close()     // Catch: IOException -> 0x0063
        L_0x0012:
            return r0
        L_0x0013:
            java.io.FileInputStream r9 = new java.io.FileInputStream     // Catch: Throwable -> 0x004e, all -> 0x004b
            java.io.FileDescriptor r10 = r8.getFileDescriptor()     // Catch: Throwable -> 0x004e, all -> 0x004b
            r9.<init>(r10)     // Catch: Throwable -> 0x004e, all -> 0x004b
            java.nio.channels.FileChannel r1 = r9.getChannel()     // Catch: Throwable -> 0x0036, all -> 0x0033
            long r5 = r1.size()     // Catch: Throwable -> 0x0036, all -> 0x0033
            java.nio.channels.FileChannel$MapMode r2 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: Throwable -> 0x0036, all -> 0x0033
            r3 = 0
            java.nio.MappedByteBuffer r10 = r1.map(r2, r3, r5)     // Catch: Throwable -> 0x0036, all -> 0x0033
            r9.close()     // Catch: Throwable -> 0x004e, all -> 0x004b
            r8.close()     // Catch: IOException -> 0x0063
            return r10
        L_0x0033:
            r10 = move-exception
            r1 = r0
            goto L_0x003c
        L_0x0036:
            r10 = move-exception
            throw r10     // Catch: all -> 0x0038
        L_0x0038:
            r1 = move-exception
            r7 = r1
            r1 = r10
            r10 = r7
        L_0x003c:
            if (r1 == 0) goto L_0x0047
            r9.close()     // Catch: Throwable -> 0x0042, all -> 0x004b
            goto L_0x004a
        L_0x0042:
            r9 = move-exception
            r1.addSuppressed(r9)     // Catch: Throwable -> 0x004e, all -> 0x004b
            goto L_0x004a
        L_0x0047:
            r9.close()     // Catch: Throwable -> 0x004e, all -> 0x004b
        L_0x004a:
            throw r10     // Catch: Throwable -> 0x004e, all -> 0x004b
        L_0x004b:
            r9 = move-exception
            r10 = r0
            goto L_0x0054
        L_0x004e:
            r9 = move-exception
            throw r9     // Catch: all -> 0x0050
        L_0x0050:
            r10 = move-exception
            r7 = r10
            r10 = r9
            r9 = r7
        L_0x0054:
            if (r10 == 0) goto L_0x005f
            r8.close()     // Catch: Throwable -> 0x005a
            goto L_0x0062
        L_0x005a:
            r8 = move-exception
            r10.addSuppressed(r8)     // Catch: IOException -> 0x0063
            goto L_0x0062
        L_0x005f:
            r8.close()     // Catch: IOException -> 0x0063
        L_0x0062:
            throw r9     // Catch: IOException -> 0x0063
        L_0x0063:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.C.a(android.content.Context, android.os.CancellationSignal, android.net.Uri):java.nio.ByteBuffer");
    }

    public static <T> T a(T t2, Object obj) {
        if (t2 != null) {
            return t2;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static int a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, int i3) {
        return !a(xmlPullParser, str) ? i3 : typedArray.getColor(i2, i3);
    }

    public static Drawable a(CompoundButton compoundButton) {
        if (Build.VERSION.SDK_INT >= 23) {
            return compoundButton.getButtonDrawable();
        }
        if (!n) {
            try {
                m = CompoundButton.class.getDeclaredField("mButtonDrawable");
                m.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            n = true;
        }
        Field field = m;
        if (field != null) {
            try {
                return (Drawable) field.get(compoundButton);
            } catch (IllegalAccessException unused2) {
                m = null;
            }
        }
        return null;
    }

    public static boolean a(Activity activity, int i2, boolean z) {
        View decorView;
        if (i2 == 0) {
            try {
                Window window = activity.getWindow();
                Class<?> cls = activity.getWindow().getClass();
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i3 = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                Method declaredMethod = cls.getDeclaredMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
                declaredMethod.setAccessible(true);
                if (z) {
                    declaredMethod.invoke(window, Integer.valueOf(i3), Integer.valueOf(i3));
                } else {
                    declaredMethod.invoke(window, 0, Integer.valueOf(i3));
                }
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        } else if (i2 == 1) {
            try {
                Window window2 = activity.getWindow();
                WindowManager.LayoutParams attributes = window2.getAttributes();
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i4 = declaredField.getInt(null);
                int i5 = declaredField2.getInt(attributes);
                declaredField2.setInt(attributes, z ? i5 | i4 : (~i4) & i5);
                window2.setAttributes(attributes);
                return true;
            } catch (Exception e3) {
                e3.printStackTrace();
                return false;
            }
        } else if (Build.VERSION.SDK_INT < 23 || (decorView = activity.getWindow().getDecorView()) == null) {
            return false;
        } else {
            int systemUiVisibility = decorView.getSystemUiVisibility();
            int i6 = z ? systemUiVisibility | 8192 : systemUiVisibility & (-8193);
            if (decorView.getSystemUiVisibility() != i6) {
                decorView.setSystemUiVisibility(i6);
            }
            return true;
        }
    }

    public static b[] a(b[] bVarArr) {
        if (bVarArr == null) {
            return null;
        }
        b[] bVarArr2 = new b[bVarArr.length];
        for (int i2 = 0; i2 < bVarArr.length; i2++) {
            bVarArr2[i2] = new b(bVarArr[i2]);
        }
        return bVarArr2;
    }

    public static Intent a(Activity activity) {
        int i2 = Build.VERSION.SDK_INT;
        Intent parentActivityIntent = activity.getParentActivityIntent();
        if (parentActivityIntent != null) {
            return parentActivityIntent;
        }
        try {
            String b2 = b(activity, activity.getComponentName());
            if (b2 == null) {
                return null;
            }
            ComponentName componentName = new ComponentName(activity, b2);
            try {
                if (b(activity, componentName) == null) {
                    return Intent.makeMainActivity(componentName);
                }
                return new Intent().setComponent(componentName);
            } catch (PackageManager.NameNotFoundException unused) {
                String str = "getParentActivityIntent: bad parentActivityName '" + b2 + "' in manifest";
                return null;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static int a(Context context, String str) {
        int myPid = Process.myPid();
        int myUid = Process.myUid();
        String packageName = context.getPackageName();
        if (context.checkPermission(str, myPid, myUid) == -1) {
            return -1;
        }
        String permissionToOp = Build.VERSION.SDK_INT >= 23 ? AppOpsManager.permissionToOp(str) : null;
        if (permissionToOp != null) {
            if (packageName == null) {
                String[] packagesForUid = context.getPackageManager().getPackagesForUid(myUid);
                if (packagesForUid == null || packagesForUid.length <= 0) {
                    return -1;
                }
                packageName = packagesForUid[0];
            }
            if ((Build.VERSION.SDK_INT >= 23 ? ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOpNoThrow(permissionToOp, packageName) : 1) != 0) {
                return -2;
            }
        }
        return 0;
    }

    public static boolean a(File file, InputStream inputStream) {
        Throwable th;
        IOException e2;
        FileOutputStream fileOutputStream;
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file, false);
            } catch (IOException e3) {
                e2 = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            try {
                fileOutputStream.close();
            } catch (IOException unused) {
            }
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
            return true;
        } catch (IOException e4) {
            e2 = e4;
            fileOutputStream2 = fileOutputStream;
            String str = "Error copying resource contents to temp file: " + e2.getMessage();
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException unused2) {
                }
            }
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
            return false;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException unused3) {
                }
            }
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
            throw th;
        }
    }

    public static c.e.b.a.a a(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i2, int i3) {
        c.e.b.a.a aVar;
        if (a(xmlPullParser, str)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i2, typedValue);
            int i4 = typedValue.type;
            if (i4 >= 28 && i4 <= 31) {
                return new c.e.b.a.a(null, null, typedValue.data);
            }
            try {
                aVar = c.e.b.a.a.a(typedArray.getResources(), typedArray.getResourceId(i2, 0), theme);
            } catch (Exception unused) {
                aVar = null;
            }
            if (aVar != null) {
                return aVar;
            }
        }
        return new c.e.b.a.a(null, null, i3);
    }

    public static void a(LayoutInflater layoutInflater, LayoutInflater.Factory2 factory2) {
        layoutInflater.setFactory2(factory2);
        int i2 = Build.VERSION.SDK_INT;
    }

    public static boolean a(b[] bVarArr, b[] bVarArr2) {
        if (bVarArr == null || bVarArr2 == null || bVarArr.length != bVarArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 < bVarArr.length; i2++) {
            if (!(bVarArr[i2].f774a == bVarArr2[i2].f774a && bVarArr[i2].f775b.length == bVarArr2[i2].f775b.length)) {
                return false;
            }
        }
        return true;
    }

    public static int a(int i2, int i3) {
        int i4 = Build.VERSION.SDK_INT;
        return Gravity.getAbsoluteGravity(i2, i3);
    }

    public static void a(PopupWindow popupWindow, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            popupWindow.setWindowLayoutType(i2);
            return;
        }
        if (!p) {
            try {
                o = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", Integer.TYPE);
                o.setAccessible(true);
            } catch (Exception unused) {
            }
            p = true;
        }
        Method method = o;
        if (method != null) {
            try {
                method.invoke(popupWindow, Integer.valueOf(i2));
            } catch (Exception unused2) {
            }
        }
    }

    public static boolean a(File file, Resources resources, int i2) {
        InputStream inputStream;
        Throwable th;
        try {
            inputStream = resources.openRawResource(i2);
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
        try {
            boolean a2 = a(file, inputStream);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused) {
                }
            }
            return a2;
        } catch (Throwable th3) {
            th = th3;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    public static void a(Object obj) {
        if (!f318d) {
            try {
                f317c = Class.forName("android.content.res.ThemedResourceCache");
            } catch (ClassNotFoundException unused) {
            }
            f318d = true;
        }
        Class cls = f317c;
        if (cls != null) {
            if (!f) {
                try {
                    f319e = cls.getDeclaredField("mUnthemedEntries");
                    f319e.setAccessible(true);
                } catch (NoSuchFieldException unused2) {
                }
                f = true;
            }
            Field field = f319e;
            if (field != null) {
                LongSparseArray longSparseArray = null;
                try {
                    longSparseArray = (LongSparseArray) field.get(obj);
                } catch (IllegalAccessException unused3) {
                }
                if (longSparseArray != null) {
                    longSparseArray.clear();
                }
            }
        }
    }

    public static c.e.b.a.b a(XmlPullParser xmlPullParser, Resources resources) {
        int next;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            xmlPullParser.require(2, null, "font-family");
            if (xmlPullParser.getName().equals("font-family")) {
                TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), c.FontFamily);
                String string = obtainAttributes.getString(c.FontFamily_fontProviderAuthority);
                String string2 = obtainAttributes.getString(c.FontFamily_fontProviderPackage);
                String string3 = obtainAttributes.getString(c.FontFamily_fontProviderQuery);
                int resourceId = obtainAttributes.getResourceId(c.FontFamily_fontProviderCerts, 0);
                int integer = obtainAttributes.getInteger(c.FontFamily_fontProviderFetchStrategy, 1);
                int integer2 = obtainAttributes.getInteger(c.FontFamily_fontProviderFetchTimeout, TbsListener.ErrorCode.INFO_CODE_MINIQB);
                obtainAttributes.recycle();
                if (string == null || string2 == null || string3 == null) {
                    ArrayList arrayList = new ArrayList();
                    while (xmlPullParser.next() != 3) {
                        if (xmlPullParser.getEventType() == 2) {
                            if (xmlPullParser.getName().equals("font")) {
                                TypedArray obtainAttributes2 = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), c.FontFamilyFont);
                                int i2 = obtainAttributes2.getInt(obtainAttributes2.hasValue(c.FontFamilyFont_fontWeight) ? c.FontFamilyFont_fontWeight : c.FontFamilyFont_android_fontWeight, TbsListener.ErrorCode.INFO_CODE_BASE);
                                boolean z = 1 == obtainAttributes2.getInt(obtainAttributes2.hasValue(c.FontFamilyFont_fontStyle) ? c.FontFamilyFont_fontStyle : c.FontFamilyFont_android_fontStyle, 0);
                                int i3 = obtainAttributes2.hasValue(c.FontFamilyFont_ttcIndex) ? c.FontFamilyFont_ttcIndex : c.FontFamilyFont_android_ttcIndex;
                                String string4 = obtainAttributes2.getString(obtainAttributes2.hasValue(c.FontFamilyFont_fontVariationSettings) ? c.FontFamilyFont_fontVariationSettings : c.FontFamilyFont_android_fontVariationSettings);
                                int i4 = obtainAttributes2.getInt(i3, 0);
                                int i5 = obtainAttributes2.hasValue(c.FontFamilyFont_font) ? c.FontFamilyFont_font : c.FontFamilyFont_android_font;
                                int resourceId2 = obtainAttributes2.getResourceId(i5, 0);
                                String string5 = obtainAttributes2.getString(i5);
                                obtainAttributes2.recycle();
                                while (xmlPullParser.next() != 3) {
                                    a(xmlPullParser);
                                }
                                arrayList.add(new c.e.b.a.d(string5, i2, z, string4, i4, resourceId2));
                            } else {
                                a(xmlPullParser);
                            }
                        }
                    }
                    if (arrayList.isEmpty()) {
                        return null;
                    }
                    return new c.e.b.a.c((c.e.b.a.d[]) arrayList.toArray(new c.e.b.a.d[arrayList.size()]));
                }
                while (xmlPullParser.next() != 3) {
                    a(xmlPullParser);
                }
                return new e(new c.e.e.a(string, string2, string3, a(resources, resourceId)), integer, integer2);
            }
            a(xmlPullParser);
            return null;
        }
        throw new XmlPullParserException("No start tag found");
    }

    public static String a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2) {
        if (!a(xmlPullParser, str)) {
            return null;
        }
        return typedArray.getString(i2);
    }

    public static int a(int i2) {
        if (i2 >= 0) {
            return i2;
        }
        throw new IllegalArgumentException();
    }

    public static Intent a(Context context, ComponentName componentName) {
        String b2 = b(context, componentName);
        if (b2 == null) {
            return null;
        }
        ComponentName componentName2 = new ComponentName(componentName.getPackageName(), b2);
        if (b(context, componentName2) == null) {
            return Intent.makeMainActivity(componentName2);
        }
        return new Intent().setComponent(componentName2);
    }

    public static PropertyValuesHolder a(TypedArray typedArray, int i2, int i3, int i4, String str) {
        int i5;
        int i6;
        int i7;
        float f2;
        float f3;
        float f4;
        PropertyValuesHolder propertyValuesHolder;
        TypedValue peekValue = typedArray.peekValue(i3);
        boolean z = peekValue != null;
        int i8 = z ? peekValue.type : 0;
        TypedValue peekValue2 = typedArray.peekValue(i4);
        boolean z2 = peekValue2 != null;
        int i9 = z2 ? peekValue2.type : 0;
        if (i2 == 4) {
            i2 = ((!z || !b(i8)) && (!z2 || !b(i9))) ? 0 : 3;
        }
        boolean z3 = i2 == 0;
        PropertyValuesHolder propertyValuesHolder2 = null;
        if (i2 == 2) {
            String string = typedArray.getString(i3);
            String string2 = typedArray.getString(i4);
            b[] b2 = b(string);
            b[] b3 = b(string2);
            if (b2 == null && b3 == null) {
                return null;
            }
            if (b2 != null) {
                c.m.a.a.e eVar = new c.m.a.a.e();
                if (b3 == null) {
                    propertyValuesHolder = PropertyValuesHolder.ofObject(str, eVar, b2);
                } else if (a(b2, b3)) {
                    propertyValuesHolder = PropertyValuesHolder.ofObject(str, eVar, b2, b3);
                } else {
                    throw new InflateException(" Can't morph from " + string + " to " + string2);
                }
                return propertyValuesHolder;
            } else if (b3 != null) {
                return PropertyValuesHolder.ofObject(str, new c.m.a.a.e(), b3);
            } else {
                return null;
            }
        } else {
            f fVar = i2 == 3 ? f.f1056a : null;
            if (z3) {
                if (z) {
                    if (i8 == 5) {
                        f3 = typedArray.getDimension(i3, 0.0f);
                    } else {
                        f3 = typedArray.getFloat(i3, 0.0f);
                    }
                    if (z2) {
                        if (i9 == 5) {
                            f4 = typedArray.getDimension(i4, 0.0f);
                        } else {
                            f4 = typedArray.getFloat(i4, 0.0f);
                        }
                        propertyValuesHolder2 = PropertyValuesHolder.ofFloat(str, f3, f4);
                    } else {
                        propertyValuesHolder2 = PropertyValuesHolder.ofFloat(str, f3);
                    }
                } else {
                    if (i9 == 5) {
                        f2 = typedArray.getDimension(i4, 0.0f);
                    } else {
                        f2 = typedArray.getFloat(i4, 0.0f);
                    }
                    propertyValuesHolder2 = PropertyValuesHolder.ofFloat(str, f2);
                }
            } else if (z) {
                if (i8 == 5) {
                    i6 = (int) typedArray.getDimension(i3, 0.0f);
                } else if (b(i8)) {
                    i6 = typedArray.getColor(i3, 0);
                } else {
                    i6 = typedArray.getInt(i3, 0);
                }
                if (z2) {
                    if (i9 == 5) {
                        i7 = (int) typedArray.getDimension(i4, 0.0f);
                    } else if (b(i9)) {
                        i7 = typedArray.getColor(i4, 0);
                    } else {
                        i7 = typedArray.getInt(i4, 0);
                    }
                    propertyValuesHolder2 = PropertyValuesHolder.ofInt(str, i6, i7);
                } else {
                    propertyValuesHolder2 = PropertyValuesHolder.ofInt(str, i6);
                }
            } else {
                if (z2) {
                    if (i9 == 5) {
                        i5 = (int) typedArray.getDimension(i4, 0.0f);
                    } else if (b(i9)) {
                        i5 = typedArray.getColor(i4, 0);
                    } else {
                        i5 = typedArray.getInt(i4, 0);
                    }
                    propertyValuesHolder2 = PropertyValuesHolder.ofInt(str, i5);
                }
                if (propertyValuesHolder2 == null && fVar != null) {
                    propertyValuesHolder2.setEvaluator(fVar);
                    return propertyValuesHolder2;
                }
            }
            return propertyValuesHolder2 == null ? propertyValuesHolder2 : propertyValuesHolder2;
        }
    }

    public static TypedArray a(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        if (theme == null) {
            return resources.obtainAttributes(attributeSet, iArr);
        }
        return theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    public static List<List<byte[]>> a(Resources resources, int i2) {
        if (i2 == 0) {
            return Collections.emptyList();
        }
        TypedArray obtainTypedArray = resources.obtainTypedArray(i2);
        try {
            if (obtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            int i3 = Build.VERSION.SDK_INT;
            if (obtainTypedArray.getType(0) == 1) {
                for (int i4 = 0; i4 < obtainTypedArray.length(); i4++) {
                    int resourceId = obtainTypedArray.getResourceId(i4, 0);
                    if (resourceId != 0) {
                        arrayList.add(a(resources.getStringArray(resourceId)));
                    }
                }
            } else {
                arrayList.add(a(resources.getStringArray(i2)));
            }
            return arrayList;
        } finally {
            obtainTypedArray.recycle();
        }
    }

    public static List<byte[]> a(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            arrayList.add(Base64.decode(str, 0));
        }
        return arrayList;
    }

    public static void a(XmlPullParser xmlPullParser) {
        int i2 = 1;
        while (i2 > 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i2++;
            } else if (next == 3) {
                i2--;
            }
        }
    }

    public static boolean a(Drawable drawable, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            return drawable.setLayoutDirection(i2);
        }
        if (!j) {
            try {
                i = Drawable.class.getDeclaredMethod("setLayoutDirection", Integer.TYPE);
                i.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            j = true;
        }
        Method method = i;
        if (method != null) {
            try {
                method.invoke(drawable, Integer.valueOf(i2));
                return true;
            } catch (Exception unused2) {
                i = null;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:156:0x0321, code lost:
        if (r27 == null) goto L_0x034a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0323, code lost:
        if (r13 == null) goto L_0x034a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0325, code lost:
        r1 = new android.animation.Animator[r13.size()];
        r2 = r13.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0333, code lost:
        if (r2.hasNext() == false) goto L_0x0341;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x0335, code lost:
        r14 = r14 + 1;
        r1[r14] = (android.animation.Animator) r2.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0341, code lost:
        if (r28 != 0) goto L_0x0347;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x0343, code lost:
        r27.playTogether(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0347, code lost:
        r27.playSequentially(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x034a, code lost:
        return r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:151:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0196  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.animation.Animator a(android.content.Context r22, android.content.res.Resources r23, android.content.res.Resources.Theme r24, org.xmlpull.v1.XmlPullParser r25, android.util.AttributeSet r26, android.animation.AnimatorSet r27, int r28, float r29) {
        /*
            Method dump skipped, instructions count: 843
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.C.a(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.animation.AnimatorSet, int, float):android.animation.Animator");
    }

    public static ActionMode.Callback a(TextView textView, ActionMode.Callback callback) {
        int i2 = Build.VERSION.SDK_INT;
        return (i2 < 26 || i2 > 27 || (callback instanceof c.e.i.e)) ? callback : new c.e.i.e(callback, textView);
    }

    public static void a(TextView textView, int i2) {
        int i3;
        a(i2);
        if (Build.VERSION.SDK_INT >= 28) {
            textView.setFirstBaselineToTopHeight(i2);
            return;
        }
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i4 = Build.VERSION.SDK_INT;
        if (textView.getIncludeFontPadding()) {
            i3 = fontMetricsInt.top;
        } else {
            i3 = fontMetricsInt.ascent;
        }
        if (i2 > Math.abs(i3)) {
            textView.setPadding(textView.getPaddingLeft(), i2 - (-i3), textView.getPaddingRight(), textView.getPaddingBottom());
        }
    }

    public static Keyframe a(Keyframe keyframe, float f2) {
        if (keyframe.getType() == Float.TYPE) {
            return Keyframe.ofFloat(f2);
        }
        if (keyframe.getType() == Integer.TYPE) {
            return Keyframe.ofInt(f2);
        }
        return Keyframe.ofObject(f2);
    }

    public static a.C0009a a(TextView textView) {
        int i2;
        int i3;
        TextDirectionHeuristic textDirectionHeuristic;
        if (Build.VERSION.SDK_INT >= 28) {
            return new a.C0009a(textView.getTextMetricsParams());
        }
        TextPaint textPaint = new TextPaint(textView.getPaint());
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 23) {
            i3 = 1;
            i2 = 1;
        } else {
            i3 = 0;
            i2 = 0;
        }
        int i4 = Build.VERSION.SDK_INT;
        TextDirectionHeuristic textDirectionHeuristic2 = TextDirectionHeuristics.FIRSTSTRONG_LTR;
        if (Build.VERSION.SDK_INT >= 23) {
            i3 = textView.getBreakStrategy();
            i2 = textView.getHyphenationFrequency();
        }
        int i5 = Build.VERSION.SDK_INT;
        if (textView.getTransformationMethod() instanceof PasswordTransformationMethod) {
            textDirectionHeuristic = TextDirectionHeuristics.LTR;
        } else if (Build.VERSION.SDK_INT < 28 || (textView.getInputType() & 15) != 3) {
            if (textView.getLayoutDirection() == 1) {
                z = true;
            }
            switch (textView.getTextDirection()) {
                case 2:
                    textDirectionHeuristic = TextDirectionHeuristics.ANYRTL_LTR;
                    break;
                case 3:
                    textDirectionHeuristic = TextDirectionHeuristics.LTR;
                    break;
                case 4:
                    textDirectionHeuristic = TextDirectionHeuristics.RTL;
                    break;
                case 5:
                    textDirectionHeuristic = TextDirectionHeuristics.LOCALE;
                    break;
                case 6:
                    textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                    break;
                case 7:
                    textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                    break;
                default:
                    if (!z) {
                        textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                        break;
                    } else {
                        textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                        break;
                    }
            }
        } else {
            byte directionality = Character.getDirectionality(DecimalFormatSymbols.getInstance(textView.getTextLocale()).getDigitStrings()[0].codePointAt(0));
            if (directionality == 1 || directionality == 2) {
                textDirectionHeuristic = TextDirectionHeuristics.RTL;
            } else {
                textDirectionHeuristic = TextDirectionHeuristics.LTR;
            }
        }
        return new a.C0009a(textPaint, textDirectionHeuristic, i3, i2);
    }

    public static void a(TextView textView, c.e.f.a aVar) {
        if (a(textView).a(aVar.f830b)) {
            textView.setText(aVar);
            return;
        }
        throw new IllegalArgumentException("Given text can not be applied to TextView.");
    }

    public static ValueAnimator a(Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, ValueAnimator valueAnimator, float f2, XmlPullParser xmlPullParser) {
        ValueAnimator valueAnimator2;
        int i2;
        ValueAnimator valueAnimator3;
        PropertyValuesHolder propertyValuesHolder;
        TypedArray a2 = a(resources, theme, attributeSet, c.m.a.a.a.g);
        TypedArray a3 = a(resources, theme, attributeSet, c.m.a.a.a.k);
        ValueAnimator valueAnimator4 = valueAnimator == null ? new ValueAnimator() : valueAnimator;
        long b2 = b(a2, xmlPullParser, "duration", 1, TbsListener.ErrorCode.ERROR_CODE_LOAD_BASE);
        int i3 = 0;
        long b3 = b(a2, xmlPullParser, "startOffset", 2, 0);
        int b4 = b(a2, xmlPullParser, "valueType", 7, 4);
        if (a(xmlPullParser, "valueFrom") && a(xmlPullParser, "valueTo")) {
            if (b4 == 4) {
                TypedValue peekValue = a2.peekValue(5);
                boolean z = peekValue != null;
                int i4 = z ? peekValue.type : 0;
                TypedValue peekValue2 = a2.peekValue(6);
                boolean z2 = peekValue2 != null;
                b4 = ((!z || !b(i4)) && (!z2 || !b(z2 ? peekValue2.type : 0))) ? 0 : 3;
            }
            PropertyValuesHolder a4 = a(a2, b4, 5, 6, BuildConfig.FLAVOR);
            if (a4 != null) {
                valueAnimator4.setValues(a4);
            }
        }
        valueAnimator4.setDuration(b2);
        valueAnimator4.setStartDelay(b3);
        valueAnimator4.setRepeatCount(b(a2, xmlPullParser, "repeatCount", 3, 0));
        valueAnimator4.setRepeatMode(b(a2, xmlPullParser, "repeatMode", 4, 1));
        if (a3 != null) {
            ObjectAnimator objectAnimator = (ObjectAnimator) valueAnimator4;
            String a5 = a(a3, xmlPullParser, "pathData", 1);
            if (a5 != null) {
                String a6 = a(a3, xmlPullParser, "propertyXName", 2);
                String a7 = a(a3, xmlPullParser, "propertyYName", 3);
                if (a6 == null && a7 == null) {
                    throw new InflateException(a3.getPositionDescription() + " propertyXName or propertyYName is needed for PathData");
                }
                Path c2 = c(a5);
                float f3 = 0.5f * f2;
                PathMeasure pathMeasure = new PathMeasure(c2, false);
                ArrayList arrayList = new ArrayList();
                arrayList.add(Float.valueOf(0.0f));
                float f4 = 0.0f;
                while (true) {
                    f4 += pathMeasure.getLength();
                    arrayList.add(Float.valueOf(f4));
                    if (!pathMeasure.nextContour()) {
                        break;
                    }
                }
                PathMeasure pathMeasure2 = new PathMeasure(c2, false);
                int min = Math.min(100, ((int) (f4 / f3)) + 1);
                float[] fArr = new float[min];
                float[] fArr2 = new float[min];
                float[] fArr3 = new float[2];
                float f5 = f4 / (min - 1);
                valueAnimator2 = valueAnimator4;
                int i5 = 0;
                float f6 = 0.0f;
                while (true) {
                    propertyValuesHolder = null;
                    if (i5 >= min) {
                        break;
                    }
                    pathMeasure2.getPosTan(f6 - ((Float) arrayList.get(i3)).floatValue(), fArr3, null);
                    fArr[i5] = fArr3[0];
                    fArr2[i5] = fArr3[1];
                    f6 += f5;
                    int i6 = i3 + 1;
                    if (i6 < arrayList.size() && f6 > ((Float) arrayList.get(i6)).floatValue()) {
                        pathMeasure2.nextContour();
                        i3 = i6;
                    }
                    i5++;
                    min = min;
                }
                PropertyValuesHolder ofFloat = a6 != null ? PropertyValuesHolder.ofFloat(a6, fArr) : null;
                if (a7 != null) {
                    propertyValuesHolder = PropertyValuesHolder.ofFloat(a7, fArr2);
                }
                if (ofFloat == null) {
                    i2 = 0;
                    objectAnimator.setValues(propertyValuesHolder);
                } else {
                    i2 = 0;
                    if (propertyValuesHolder == null) {
                        objectAnimator.setValues(ofFloat);
                    } else {
                        objectAnimator.setValues(ofFloat, propertyValuesHolder);
                    }
                }
            } else {
                valueAnimator2 = valueAnimator4;
                i2 = 0;
                objectAnimator.setPropertyName(a(a3, xmlPullParser, "propertyName", 0));
            }
        } else {
            valueAnimator2 = valueAnimator4;
            i2 = 0;
        }
        if (a(xmlPullParser, "interpolator")) {
            i2 = a2.getResourceId(i2, i2);
        }
        if (i2 > 0) {
            int i7 = Build.VERSION.SDK_INT;
            valueAnimator3 = valueAnimator2;
            valueAnimator3.setInterpolator(AnimationUtils.loadInterpolator(context, i2));
        } else {
            valueAnimator3 = valueAnimator2;
        }
        a2.recycle();
        if (a3 != null) {
            a3.recycle();
        }
        return valueAnimator3;
    }
}

package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.export.external.libwebp;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.utils.TbsLog;
import e.a.a.a.a;
import java.util.Map;

/* loaded from: classes.dex */
public class s {

    /* renamed from: a */
    public Context f1401a;

    /* renamed from: b */
    public Context f1402b;

    /* renamed from: c */
    public String f1403c;

    /* renamed from: d */
    public String[] f1404d;

    /* renamed from: e */
    public DexLoader f1405e;
    public String f;
    public String g = null;

    public s(Context context, Context context2, String str, String str2, String[] strArr, String str3) {
        boolean z;
        boolean z2;
        this.f1401a = null;
        this.f1402b = null;
        this.f1403c = null;
        this.f1404d = null;
        this.f1405e = null;
        this.f = "TbsDexOpt";
        TbsLog.i("TbsWizard", "construction start...");
        if (context == null || ((context2 == null && TbsShareManager.f1265c == null) || TextUtils.isEmpty(str) || strArr == null || strArr.length == 0)) {
            throw new Exception("TbsWizard paramter error:-1callerContext:" + context + "hostcontext" + context2 + "isEmpty" + TextUtils.isEmpty(str) + "dexfileList" + strArr);
        }
        this.f1401a = context.getApplicationContext();
        if (context2.getApplicationContext() != null) {
            this.f1402b = context2.getApplicationContext();
        } else {
            this.f1402b = context2;
        }
        this.f1403c = str;
        this.f1404d = strArr;
        this.f = str2;
        for (int i = 0; i < this.f1404d.length; i++) {
            TbsLog.i("TbsWizard", "#2 mDexFileList[" + i + "]: " + this.f1404d[i]);
        }
        TbsLog.i("TbsWizard", "new DexLoader #2 libraryPath is " + str3 + " mCallerAppContext is " + this.f1401a + " dexOutPutDir is " + str2);
        this.f1405e = new DexLoader(str3, this.f1401a, this.f1404d, str2, QbSdk.n);
        System.currentTimeMillis();
        a(context);
        libwebp.loadWepLibraryIfNeed(context2, this.f1403c);
        if ("com.nd.android.pandahome2".equals(this.f1401a.getApplicationInfo().packageName)) {
            this.f1405e.invokeStaticMethod("com.tencent.tbs.common.beacon.X5CoreBeaconUploader", "getInstance", new Class[]{Context.class}, this.f1401a);
        }
        if (QbSdk.n != null) {
            try {
                z = TbsPVConfig.getInstance(this.f1401a).getTbsCoreSandboxModeEnable();
            } catch (Throwable unused) {
                z = false;
            }
            try {
                z2 = "true".equals(String.valueOf(QbSdk.n.get(TbsCoreSettings.TBS_SETTINGS_USE_SANDBOX)));
            } catch (Throwable th) {
                th.printStackTrace();
                z2 = false;
            }
            QbSdk.n.put(TbsCoreSettings.TBS_SETTINGS_USE_SANDBOX, Boolean.valueOf(z && z2));
            this.f1405e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTbsSettings", new Class[]{Map.class}, QbSdk.n);
        }
        int b2 = b(context);
        if (b2 >= 0) {
            TbsLog.i("TbsWizard", "construction end...");
            return;
        }
        throw new Exception("TbsWizard init error: " + b2 + "; msg: " + this.g);
    }

    private int b(Context context) {
        Object obj;
        int i;
        String str;
        Object obj2 = this.f1402b;
        if (obj2 != null || TbsShareManager.f1265c == null) {
            TbsLog.i("TbsWizard", "initTesRuntimeEnvironment callerContext is " + context + " mHostContext is " + this.f1402b + " mDexLoader is " + this.f1405e + " mtbsInstallLocation is " + this.f1403c + " mDexOptPath is " + this.f);
            DexLoader dexLoader = this.f1405e;
            obj = dexLoader.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTesRuntimeEnvironment", new Class[]{Context.class, Context.class, DexLoader.class, String.class, String.class, String.class, Integer.TYPE, String.class}, context, this.f1402b, dexLoader, this.f1403c, this.f, TbsConfig.TBS_SDK_VERSIONNAME, 43939, QbSdk.p);
        } else {
            DexLoader dexLoader2 = this.f1405e;
            obj = dexLoader2.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTesRuntimeEnvironment", new Class[]{Context.class, Context.class, DexLoader.class, String.class, String.class, String.class, Integer.TYPE, String.class, String.class}, context, obj2, dexLoader2, this.f1403c, this.f, TbsConfig.TBS_SDK_VERSIONNAME, 43939, QbSdk.p, TbsShareManager.f1265c);
        }
        if (obj == null) {
            c();
            d();
            DexLoader dexLoader3 = this.f1405e;
            obj = dexLoader3.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTesRuntimeEnvironment", new Class[]{Context.class, Context.class, DexLoader.class, String.class, String.class}, context, this.f1402b, dexLoader3, this.f1403c, this.f);
        }
        if (obj == null) {
            i = -3;
        } else if (obj instanceof Integer) {
            i = ((Integer) obj).intValue();
        } else if (obj instanceof Throwable) {
            TbsCoreLoadStat.getInstance().a(this.f1401a, TbsListener.ErrorCode.THROWABLE_INITTESRUNTIMEENVIRONMENT, (Throwable) obj);
            i = -5;
        } else {
            i = -4;
        }
        if (i < 0) {
            Object invokeStaticMethod = this.f1405e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "getLoadFailureDetails", new Class[0], new Object[0]);
            if (invokeStaticMethod instanceof Throwable) {
                Throwable th = (Throwable) invokeStaticMethod;
                StringBuilder a2 = a.a("#");
                a2.append(th.getMessage());
                a2.append("; cause: ");
                a2.append(th.getCause());
                a2.append("; th: ");
                a2.append(th);
                this.g = a2.toString();
            }
            if (invokeStaticMethod instanceof String) {
                str = (String) invokeStaticMethod;
            }
            return i;
        }
        str = null;
        this.g = str;
        return i;
    }

    private void c() {
        this.f1405e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "setTesSdkVersionName", new Class[]{String.class}, TbsConfig.TBS_SDK_VERSIONNAME);
    }

    private void d() {
        this.f1405e.setStaticField("com.tencent.tbs.tbsshell.TBSShell", "VERSION", 43939);
    }

    public String a() {
        String str = null;
        Object invokeStaticMethod = this.f1405e.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "invokeStaticMethod", new Class[]{Boolean.TYPE, String.class, String.class, Class[].class, Object[].class}, true, "com.tencent.smtt.util.CrashTracker", "getCrashExtraInfo", null, new Object[0]);
        if (invokeStaticMethod == null) {
            invokeStaticMethod = this.f1405e.invokeStaticMethod("com.tencent.smtt.util.CrashTracker", "getCrashExtraInfo", null, new Object[0]);
        }
        if (invokeStaticMethod != null) {
            str = String.valueOf(invokeStaticMethod) + " ReaderPackName=" + TbsReaderView.gReaderPackName + " ReaderPackVersion=" + TbsReaderView.gReaderPackVersion;
        }
        return str == null ? "X5 core get nothing..." : str;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(android.content.Context r3) {
        /*
            r2 = this;
            java.util.Map<java.lang.String, java.lang.Object> r0 = com.tencent.smtt.sdk.QbSdk.n
            if (r0 == 0) goto L_0x0015
            java.lang.String r1 = "check_tbs_validity"
            java.lang.Object r0 = r0.get(r1)
            boolean r1 = r0 instanceof java.lang.Boolean
            if (r1 == 0) goto L_0x0015
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            goto L_0x0016
        L_0x0015:
            r0 = 1
        L_0x0016:
            if (r0 == 0) goto L_0x001b
            com.tencent.smtt.utils.l.b(r3)
        L_0x001b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.s.a(android.content.Context):void");
    }

    public void a(Context context, Context context2, String str, String str2, String[] strArr, String str3) {
        this.f1401a = context.getApplicationContext();
        if (this.f1402b.getApplicationContext() != null) {
            this.f1402b = this.f1402b.getApplicationContext();
        }
        this.f1403c = str;
        this.f1404d = strArr;
        this.f = str2;
        libwebp.loadWepLibraryIfNeed(context2, this.f1403c);
        Map<String, Object> map = QbSdk.n;
        if (map != null) {
            this.f1405e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTbsSettings", new Class[]{Map.class}, map);
        }
        int b2 = b(context);
        if (b2 < 0) {
            throw new Exception("continueInit init error: " + b2 + "; msg: " + this.g);
        }
    }

    public boolean a(Context context, String str, String str2, Bundle bundle) {
        Object invokeStaticMethod = this.f1405e.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "installLocalQbApk", new Class[]{Context.class, String.class, String.class, Bundle.class}, context, str, str2, bundle);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    public DexLoader b() {
        return this.f1405e;
    }
}

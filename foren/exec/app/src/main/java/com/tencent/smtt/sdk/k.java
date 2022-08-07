package com.tencent.smtt.sdk;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* loaded from: classes.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    public static k f1368a;

    /* renamed from: b  reason: collision with root package name */
    public static Context f1369b;

    public static k a(Context context) {
        if (f1368a == null) {
            synchronized (k.class) {
                if (f1368a == null) {
                    f1368a = new k();
                }
            }
        }
        f1369b = context.getApplicationContext();
        return f1368a;
    }

    private Properties e() {
        Throwable th;
        Properties properties;
        Exception e2;
        BufferedInputStream bufferedInputStream;
        try {
            BufferedInputStream bufferedInputStream2 = null;
            try {
                File a2 = a();
                properties = new Properties();
                if (a2 != null) {
                    try {
                        bufferedInputStream = new BufferedInputStream(new FileInputStream(a2));
                    } catch (Exception e3) {
                        e2 = e3;
                    }
                    try {
                        properties.load(bufferedInputStream);
                        bufferedInputStream2 = bufferedInputStream;
                    } catch (Exception e4) {
                        e2 = e4;
                        bufferedInputStream2 = bufferedInputStream;
                        e2.printStackTrace();
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        return properties;
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
                if (bufferedInputStream2 != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                }
                return properties;
            } catch (Exception e8) {
                e2 = e8;
                properties = null;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public File a() {
        m.a();
        File file = new File(m.s(f1369b), "tbscoreinstall.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return file;
    }

    public void a(int i) {
        a("dexopt_retry_num", i);
    }

    public void a(int i, int i2) {
        a("copy_core_ver", i);
        a("copy_status", i2);
    }

    public void a(String str) {
        a("install_apk_path", str);
    }

    public void a(String str, int i) {
        a(str, String.valueOf(i));
    }

    /*  JADX ERROR: JadxRuntimeException in pass: SSATransform
        jadx.core.utils.exceptions.JadxRuntimeException: Not initialized variable reg: 0, insn: 0x0041: MOVE  (r2 I:??[OBJECT, ARRAY]) = (r0 I:??[OBJECT, ARRAY]), block:B:18:0x0041
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVarsInBlock(SSATransform.java:171)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:143)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:60)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:41)
        */
    public void a(
    /*  JADX ERROR: JadxRuntimeException in pass: SSATransform
        jadx.core.utils.exceptions.JadxRuntimeException: Not initialized variable reg: 0, insn: 0x0041: MOVE  (r2 I:??[OBJECT, ARRAY]) = (r0 I:??[OBJECT, ARRAY]), block:B:18:0x0041
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVarsInBlock(SSATransform.java:171)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:143)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:60)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r4v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:233)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:209)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:162)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:364)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:304)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:270)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
        */

    public int b() {
        return c("install_core_ver");
    }

    public int b(String str) {
        Properties e2 = e();
        if (e2 == null || e2.getProperty(str) == null) {
            return -1;
        }
        return Integer.parseInt(e2.getProperty(str));
    }

    public void b(int i) {
        a("unzip_retry_num", i);
    }

    public void b(int i, int i2) {
        a("tpatch_ver", i);
        a("tpatch_status", i2);
    }

    public int c() {
        return b("install_status");
    }

    public int c(String str) {
        Properties e2 = e();
        if (e2 == null || e2.getProperty(str) == null) {
            return 0;
        }
        return Integer.parseInt(e2.getProperty(str));
    }

    public void c(int i) {
        a("incrupdate_status", i);
    }

    public void c(int i, int i2) {
        a("install_core_ver", i);
        a("install_status", i2);
    }

    public int d() {
        return b("incrupdate_status");
    }

    public String d(String str) {
        Properties e2 = e();
        if (e2 == null || e2.getProperty(str) == null) {
            return null;
        }
        return e2.getProperty(str);
    }

    public void d(int i) {
        a("unlzma_status", i);
    }
}

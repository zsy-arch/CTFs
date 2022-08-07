package com.alipay.apmobilesecuritysdk.g;

import android.content.Context;
import android.os.Environment;
import com.alipay.b.a.a.a.a.c;
import com.alipay.b.a.a.d.b;
import com.alipay.b.a.a.d.d;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a {
    public static String a(Context context, String str, String str2) {
        if (context == null || com.alipay.b.a.a.a.a.a(str) || com.alipay.b.a.a.a.a.a(str2)) {
            return null;
        }
        try {
            String a = d.a(context, str, str2, "");
            if (!com.alipay.b.a.a.a.a.a(a)) {
                return c.b(c.a(), a);
            }
            return null;
        } catch (Throwable th) {
            return null;
        }
    }

    public static String a(String str, String str2) {
        String str3 = null;
        synchronized (a.class) {
            if (!com.alipay.b.a.a.a.a.a(str) && !com.alipay.b.a.a.a.a.a(str2)) {
                try {
                    String a = b.a(str);
                    if (!com.alipay.b.a.a.a.a.a(a)) {
                        String string = new JSONObject(a).getString(str2);
                        if (!com.alipay.b.a.a.a.a.a(string)) {
                            str3 = c.b(c.a(), string);
                        }
                    }
                } catch (Throwable th) {
                }
            }
        }
        return str3;
    }

    public static void a(Context context, String str, String str2, String str3) {
        if (!com.alipay.b.a.a.a.a.a(str) && !com.alipay.b.a.a.a.a.a(str2) && context != null) {
            try {
                String a = c.a(c.a(), str3);
                HashMap hashMap = new HashMap();
                hashMap.put(str2, a);
                d.a(context, str, hashMap);
            } catch (Throwable th) {
            }
        }
    }

    public static void a(String str, String str2, String str3) {
        Throwable th;
        FileWriter fileWriter;
        synchronized (a.class) {
            if (!com.alipay.b.a.a.a.a.a(str) && !com.alipay.b.a.a.a.a.a(str2)) {
                try {
                    String a = b.a(str);
                    JSONObject jSONObject = new JSONObject();
                    if (com.alipay.b.a.a.a.a.b(a)) {
                        try {
                            jSONObject = new JSONObject(a);
                        } catch (Exception e) {
                            jSONObject = new JSONObject();
                        }
                    }
                    jSONObject.put(str2, c.a(c.a(), str3));
                    String jSONObject2 = jSONObject.toString();
                    try {
                        if (!com.alipay.b.a.a.a.a.a(jSONObject2)) {
                            System.setProperty(str, jSONObject2);
                        }
                    } catch (Throwable th2) {
                    }
                    if (com.alipay.b.a.a.d.c.a()) {
                        String str4 = ".SystemConfig" + File.separator + str;
                        try {
                            if (com.alipay.b.a.a.d.c.a()) {
                                File file = new File(Environment.getExternalStorageDirectory(), str4);
                                if (!file.exists()) {
                                    file.getParentFile().mkdirs();
                                }
                                File file2 = new File(file.getAbsolutePath());
                                FileWriter fileWriter2 = null;
                                try {
                                    fileWriter = new FileWriter(file2, false);
                                } catch (Exception e2) {
                                    fileWriter = null;
                                } catch (Throwable th3) {
                                    th = th3;
                                }
                                try {
                                    fileWriter.write(jSONObject2);
                                    try {
                                        fileWriter.close();
                                    } catch (IOException e3) {
                                    }
                                } catch (Exception e4) {
                                    if (fileWriter != null) {
                                        try {
                                            fileWriter.close();
                                        } catch (IOException e5) {
                                        }
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    fileWriter2 = fileWriter;
                                    if (fileWriter2 != null) {
                                        try {
                                            fileWriter2.close();
                                        } catch (IOException e6) {
                                        }
                                    }
                                    throw th;
                                }
                            }
                        } catch (Exception e7) {
                        }
                    }
                } catch (Throwable th5) {
                }
            }
        }
    }
}

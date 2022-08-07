package com.amap.api.col;

import android.content.Context;
import com.amap.api.col.hw;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;

/* compiled from: Utils.java */
/* loaded from: classes.dex */
public class io {
    static byte[] a(hw hwVar, String str) {
        return a(hwVar, str, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] a(hw hwVar, String str, boolean z) {
        InputStream inputStream;
        InputStream inputStream2;
        byte[] bArr;
        hw.b bVar;
        Throwable th;
        try {
            inputStream = null;
            inputStream2 = null;
            bArr = new byte[0];
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            bVar = hwVar.a(str);
            if (bVar == null) {
                if (0 != 0) {
                    try {
                        inputStream2.close();
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                }
                if (bVar != null) {
                    try {
                        bVar.close();
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                    }
                }
            } else {
                try {
                    InputStream a = bVar.a(0);
                    if (a == null) {
                        if (a != null) {
                            try {
                                a.close();
                            } catch (Throwable th5) {
                                th5.printStackTrace();
                            }
                        }
                        if (bVar != null) {
                            try {
                                bVar.close();
                            } catch (Throwable th6) {
                                th6.printStackTrace();
                            }
                        }
                    } else {
                        bArr = new byte[a.available()];
                        a.read(bArr);
                        if (z) {
                            hwVar.c(str);
                        }
                        if (a != null) {
                            try {
                                a.close();
                            } catch (Throwable th7) {
                                th7.printStackTrace();
                            }
                        }
                        if (bVar != null) {
                            try {
                                bVar.close();
                            } catch (Throwable th8) {
                                th8.printStackTrace();
                            }
                        }
                    }
                } catch (Throwable th9) {
                    th = th9;
                    go.a(th, "Utils", "readSingleLog");
                    if (0 != 0) {
                        try {
                            inputStream.close();
                        } catch (Throwable th10) {
                            th10.printStackTrace();
                        }
                    }
                    if (bVar != null) {
                        try {
                            bVar.close();
                        } catch (Throwable th11) {
                            th11.printStackTrace();
                        }
                    }
                    return bArr;
                }
            }
        } catch (Throwable th12) {
            th = th12;
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (Throwable th13) {
                    th13.printStackTrace();
                }
            }
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (Throwable th14) {
                    th14.printStackTrace();
                }
            }
            throw th;
        }
        return bArr;
    }

    public static String a() {
        return gk.a(new Date().getTime());
    }

    public static String a(Context context, gj gjVar) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"sim\":\"").append(ge.e(context)).append("\",\"sdkversion\":\"").append(gjVar.c()).append("\",\"product\":\"").append(gjVar.a()).append("\",\"ed\":\"").append(gjVar.e()).append("\",\"nt\":\"").append(ge.c(context)).append("\",\"np\":\"").append(ge.a(context)).append("\",\"mnc\":\"").append(ge.b(context)).append("\",\"ant\":\"").append(ge.d(context)).append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }

    public static String a(String str, String str2, String str3, int i, String str4, String str5) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2).append(",").append("\"timestamp\":\"");
        stringBuffer.append(str3);
        stringBuffer.append("\",\"et\":\"");
        stringBuffer.append(i);
        stringBuffer.append("\",\"classname\":\"");
        stringBuffer.append(str4);
        stringBuffer.append("\",");
        stringBuffer.append("\"detail\":\"");
        stringBuffer.append(str5);
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }

    public static byte[] a(Context context, String str, int i) {
        hw hwVar;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[0];
            String a = gp.a(context, str);
            hwVar = null;
            try {
                hwVar = hw.a(new File(a), 1, 1, i);
                File file = new File(a);
                if (file != null && file.exists()) {
                    String[] list = file.list();
                    for (String str2 : list) {
                        if (str2.contains(".0")) {
                            byteArrayOutputStream.write(a(hwVar, str2.split("\\.")[0]));
                        }
                    }
                }
                bArr = byteArrayOutputStream.toByteArray();
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (hwVar != null) {
                    try {
                        hwVar.close();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            } catch (IOException e2) {
                go.a(e2, "Statistics.Utils", "getContent");
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                if (hwVar != null) {
                    try {
                        hwVar.close();
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                go.a(th3, "Statistics.Utils", "getContent");
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                if (hwVar != null) {
                    try {
                        hwVar.close();
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                    }
                }
            }
            return bArr;
        } catch (Throwable th5) {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            if (hwVar != null) {
                try {
                    hwVar.close();
                } catch (Throwable th6) {
                    th6.printStackTrace();
                }
            }
            throw th5;
        }
    }

    public static int a(Context context, String str) {
        try {
            File file = new File(gp.a(context, str));
            if (!file.exists()) {
                return 0;
            }
            return file.list().length;
        } catch (Throwable th) {
            go.a(th, "Statistics.Utils", "getFileNum");
            return 0;
        }
    }

    public static void a(Context context, String str, int i, byte[] bArr) {
        hw hwVar;
        Random random;
        Throwable th;
        OutputStream outputStream = null;
        if (bArr != null && bArr.length != 0) {
            synchronized (io.class) {
                try {
                    random = new Random();
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    hwVar = hw.a(new File(gp.a(context, str)), 1, 1, i);
                    try {
                        hw.a b = hwVar.b(Integer.toString(random.nextInt(100)) + Long.toString(System.nanoTime()));
                        outputStream = b.a(0);
                        outputStream.write(bArr);
                        b.a();
                        hwVar.e();
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (hwVar != null) {
                            hwVar.close();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        go.a(th, "Statistics.Utils", "writeToDiskLruCache");
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (hwVar != null) {
                            hwVar.close();
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    hwVar = null;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (hwVar != null) {
                        hwVar.close();
                    }
                    throw th;
                }
            }
        }
    }

    public static long b(Context context, String str) {
        Throwable th;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        Throwable th2;
        IOException e;
        FileNotFoundException e2;
        long j = 0;
        File file = new File(gp.a(context, str));
        if (file.exists()) {
            try {
                fileInputStream = null;
            } catch (Throwable th3) {
                th = th3;
            }
            try {
                fileInputStream2 = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[fileInputStream2.available()];
                    fileInputStream2.read(bArr);
                    j = Long.parseLong(gk.a(bArr));
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Throwable th4) {
                            th4.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e3) {
                    e2 = e3;
                    go.a(e2, "StatisticsManager", "getUpdateTime");
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Throwable th5) {
                            th5.printStackTrace();
                        }
                    }
                    return j;
                } catch (IOException e4) {
                    e = e4;
                    go.a(e, "StatisticsManager", "getUpdateTime");
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Throwable th6) {
                            th6.printStackTrace();
                        }
                    }
                    return j;
                } catch (Throwable th7) {
                    th2 = th7;
                    go.a(th2, "StatisticsManager", "getUpdateTime");
                    if (file != null && file.exists()) {
                        file.delete();
                    }
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Throwable th8) {
                            th8.printStackTrace();
                        }
                    }
                    return j;
                }
            } catch (FileNotFoundException e5) {
                e2 = e5;
                fileInputStream2 = null;
            } catch (IOException e6) {
                e = e6;
                fileInputStream2 = null;
            } catch (Throwable th9) {
                th = th9;
                if (0 != 0) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th10) {
                        th10.printStackTrace();
                    }
                }
                throw th;
            }
        }
        return j;
    }

    public static void a(Context context, long j, String str) {
        FileOutputStream fileOutputStream;
        IOException e;
        FileNotFoundException e2;
        File file = new File(gp.a(context, str));
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
        } catch (Throwable th) {
            th = th;
        }
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(gk.a(String.valueOf(j)));
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e3) {
                e2 = e3;
                e2.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                }
            } catch (IOException e4) {
                e = e4;
                e.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e5) {
            e2 = e5;
            fileOutputStream = null;
        } catch (IOException e6) {
            e = e6;
            fileOutputStream = null;
        } catch (Throwable th5) {
            th = th5;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th6) {
                    th6.printStackTrace();
                }
            }
            throw th;
        }
    }
}

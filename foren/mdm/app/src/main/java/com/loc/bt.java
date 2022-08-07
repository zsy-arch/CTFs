package com.loc;

import android.content.Context;
import com.loc.be;
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
/* loaded from: classes2.dex */
public class bt {
    public static String a() {
        return t.a(new Date().getTime());
    }

    public static String a(Context context, s sVar) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"sim\":\"").append(n.e(context)).append("\",\"sdkversion\":\"").append(sVar.c()).append("\",\"product\":\"").append(sVar.a()).append("\",\"ed\":\"").append(sVar.d()).append("\",\"nt\":\"").append(n.c(context)).append("\",\"np\":\"").append(n.a(context)).append("\",\"mnc\":\"").append(n.b(context)).append("\",\"ant\":\"").append(n.d(context)).append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }

    public static String a(String str, String str2, int i, String str3, String str4) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str).append(",\"timestamp\":\"");
        stringBuffer.append(str2);
        stringBuffer.append("\",\"et\":\"");
        stringBuffer.append(i);
        stringBuffer.append("\",\"classname\":\"");
        stringBuffer.append(str3);
        stringBuffer.append("\",");
        stringBuffer.append("\"detail\":\"");
        stringBuffer.append(str4);
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }

    public static void a(Context context, long j, String str) {
        FileOutputStream fileOutputStream;
        IOException e;
        FileNotFoundException e2;
        File file = new File(x.a(context, str));
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
                fileOutputStream.write(t.a(String.valueOf(j)));
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th2) {
                        th = th2;
                        th.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e3) {
                e2 = e3;
                e2.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th3) {
                        th = th3;
                        th.printStackTrace();
                    }
                }
            } catch (IOException e4) {
                e = e4;
                e.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th4) {
                        th = th4;
                        th.printStackTrace();
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

    public static void a(Context context, String str, byte[] bArr) {
        be beVar;
        Random random;
        Throwable th;
        OutputStream outputStream = null;
        if (bArr != null && bArr.length != 0) {
            synchronized (bt.class) {
                try {
                    random = new Random();
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    beVar = be.a(new File(x.a(context, str)), 307200L);
                    try {
                        be.a b = beVar.b(Integer.toString(random.nextInt(100)) + Long.toString(System.nanoTime()));
                        outputStream = b.a();
                        outputStream.write(bArr);
                        b.b();
                        beVar.c();
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (beVar != null) {
                            beVar.close();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        w.a(th, "Statistics.Utils", "writeToDiskLruCache");
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (beVar != null) {
                            beVar.close();
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    beVar = null;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (beVar != null) {
                        beVar.close();
                    }
                    throw th;
                }
            }
        }
    }

    public static byte[] a(Context context, String str) {
        be beVar;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[0];
            String a = x.a(context, str);
            beVar = null;
            try {
                beVar = be.a(new File(a), 307200L);
                File file = new File(a);
                if (file.exists()) {
                    String[] list = file.list();
                    for (String str2 : list) {
                        if (str2.contains(".0")) {
                            byteArrayOutputStream.write(a(beVar, str2.split("\\.")[0], true));
                        }
                    }
                }
                bArr = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (beVar != null) {
                    try {
                        beVar.close();
                    } catch (Throwable th) {
                        th = th;
                        return bArr;
                    }
                }
            } catch (IOException e2) {
                w.a(e2, "Statistics.Utils", "getContent");
                if (beVar != null) {
                    try {
                    } catch (Throwable th2) {
                        return bArr;
                    }
                }
            } catch (Throwable th3) {
                w.a(th3, "Statistics.Utils", "getContent");
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                if (beVar != null) {
                    try {
                        beVar.close();
                    } catch (Throwable th4) {
                        th = th4;
                        return bArr;
                    }
                }
            }
            return bArr;
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            if (beVar != null) {
                try {
                    beVar.close();
                } catch (Throwable th5) {
                    th5.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] a(be beVar, String str, boolean z) {
        InputStream inputStream;
        InputStream inputStream2;
        byte[] bArr;
        be.b bVar;
        Throwable th;
        try {
            inputStream = null;
            inputStream2 = null;
            bArr = new byte[0];
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            bVar = beVar.a(str);
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
                        th = th4;
                        th.printStackTrace();
                        return bArr;
                    }
                }
            } else {
                try {
                    InputStream a = bVar.a();
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
                                th = th6;
                                th.printStackTrace();
                                return bArr;
                            }
                        }
                    } else {
                        bArr = new byte[a.available()];
                        a.read(bArr);
                        if (z) {
                            beVar.c(str);
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
                                th = th8;
                                th.printStackTrace();
                                return bArr;
                            }
                        }
                    }
                } catch (Throwable th9) {
                    th = th9;
                    w.a(th, "Utils", "readSingleLog");
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
                            th = th11;
                            th.printStackTrace();
                            return bArr;
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

    public static int b(Context context, String str) {
        try {
            File file = new File(x.a(context, str));
            if (!file.exists()) {
                return 0;
            }
            return file.list().length;
        } catch (Throwable th) {
            w.a(th, "Statistics.Utils", "getFileNum");
            return 0;
        }
    }

    public static long c(Context context, String str) {
        Throwable th;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        Throwable th2;
        IOException e;
        FileNotFoundException e2;
        long j = 0;
        File file = new File(x.a(context, str));
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
                    j = Long.parseLong(t.a(bArr));
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Throwable th4) {
                            th = th4;
                            th.printStackTrace();
                            return j;
                        }
                    }
                } catch (FileNotFoundException e3) {
                    e2 = e3;
                    w.a(e2, "StatisticsManager", "getUpdateTime");
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Throwable th5) {
                            th = th5;
                            th.printStackTrace();
                            return j;
                        }
                    }
                    return j;
                } catch (IOException e4) {
                    e = e4;
                    w.a(e, "StatisticsManager", "getUpdateTime");
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Throwable th6) {
                            th = th6;
                            th.printStackTrace();
                            return j;
                        }
                    }
                    return j;
                } catch (Throwable th7) {
                    th2 = th7;
                    w.a(th2, "StatisticsManager", "getUpdateTime");
                    if (file.exists()) {
                        file.delete();
                    }
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Throwable th8) {
                            th = th8;
                            th.printStackTrace();
                            return j;
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
                th2 = th9;
                fileInputStream2 = null;
            }
        }
        return j;
    }
}

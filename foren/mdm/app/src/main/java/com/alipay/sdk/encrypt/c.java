package com.alipay.sdk.encrypt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes.dex */
public final class c {
    public static byte[] a(byte[] bArr) throws IOException {
        GZIPOutputStream gZIPOutputStream;
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr);
            try {
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream2);
                    try {
                        byte[] bArr2 = new byte[4096];
                        while (true) {
                            int read = byteArrayInputStream2.read(bArr2);
                            if (read == -1) {
                                break;
                            }
                            gZIPOutputStream.write(bArr2, 0, read);
                        }
                        gZIPOutputStream.flush();
                        gZIPOutputStream.finish();
                        byte[] byteArray = byteArrayOutputStream2.toByteArray();
                        try {
                            byteArrayInputStream2.close();
                        } catch (Exception e) {
                        }
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Exception e2) {
                        }
                        try {
                            gZIPOutputStream.close();
                        } catch (Exception e3) {
                        }
                        return byteArray;
                    } catch (Throwable th) {
                        th = th;
                        byteArrayOutputStream = byteArrayOutputStream2;
                        byteArrayInputStream = byteArrayInputStream2;
                        if (byteArrayInputStream != null) {
                            try {
                                byteArrayInputStream.close();
                            } catch (Exception e4) {
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e5) {
                            }
                        }
                        if (gZIPOutputStream != null) {
                            try {
                                gZIPOutputStream.close();
                            } catch (Exception e6) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    gZIPOutputStream = null;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    byteArrayInputStream = byteArrayInputStream2;
                }
            } catch (Throwable th3) {
                th = th3;
                gZIPOutputStream = null;
                byteArrayInputStream = byteArrayInputStream2;
            }
        } catch (Throwable th4) {
            th = th4;
            gZIPOutputStream = null;
            byteArrayInputStream = null;
        }
    }

    public static byte[] b(byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream;
        GZIPInputStream gZIPInputStream;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr);
            try {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream2);
                try {
                    byte[] bArr2 = new byte[4096];
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    while (true) {
                        try {
                            int read = gZIPInputStream.read(bArr2, 0, 4096);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream2.write(bArr2, 0, read);
                        } catch (Throwable th2) {
                            th = th2;
                            byteArrayOutputStream = byteArrayOutputStream2;
                            byteArrayInputStream = byteArrayInputStream2;
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e) {
                            }
                            try {
                                gZIPInputStream.close();
                            } catch (Exception e2) {
                            }
                            try {
                                byteArrayInputStream.close();
                            } catch (Exception e3) {
                            }
                            throw th;
                        }
                    }
                    byteArrayOutputStream2.flush();
                    byte[] byteArray = byteArrayOutputStream2.toByteArray();
                    try {
                        byteArrayOutputStream2.close();
                    } catch (Exception e4) {
                    }
                    try {
                        gZIPInputStream.close();
                    } catch (Exception e5) {
                    }
                    try {
                        byteArrayInputStream2.close();
                    } catch (Exception e6) {
                    }
                    return byteArray;
                } catch (Throwable th3) {
                    th = th3;
                    byteArrayInputStream = byteArrayInputStream2;
                }
            } catch (Throwable th4) {
                th = th4;
                gZIPInputStream = null;
                byteArrayInputStream = byteArrayInputStream2;
            }
        } catch (Throwable th5) {
            th = th5;
            gZIPInputStream = null;
            byteArrayInputStream = null;
        }
    }
}

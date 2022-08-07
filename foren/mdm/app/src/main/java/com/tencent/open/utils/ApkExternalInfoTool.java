package com.tencent.open.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.ProtocolException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Properties;
import java.util.zip.ZipException;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public final class ApkExternalInfoTool {
    public static final String CHANNELID = "channelNo";
    private static final ZipLong a = new ZipLong(101010256);
    private static final ZipShort b = new ZipShort(38651);

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public static class ApkExternalInfo {
        Properties a;
        byte[] b;

        private ApkExternalInfo() {
            this.a = new Properties();
        }

        void a(byte[] bArr) throws IOException {
            if (bArr != null) {
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                int length = ApkExternalInfoTool.b.getBytes().length;
                byte[] bArr2 = new byte[length];
                wrap.get(bArr2);
                if (!ApkExternalInfoTool.b.equals(new ZipShort(bArr2))) {
                    throw new ProtocolException("unknow protocl [" + Arrays.toString(bArr) + "]");
                } else if (bArr.length - length > 2) {
                    byte[] bArr3 = new byte[2];
                    wrap.get(bArr3);
                    int value = new ZipShort(bArr3).getValue();
                    if ((bArr.length - length) - 2 >= value) {
                        byte[] bArr4 = new byte[value];
                        wrap.get(bArr4);
                        this.a.load(new ByteArrayInputStream(bArr4));
                        int length2 = ((bArr.length - length) - value) - 2;
                        if (length2 > 0) {
                            this.b = new byte[length2];
                            wrap.get(this.b);
                        }
                    }
                }
            }
        }

        public String toString() {
            return "ApkExternalInfo [p=" + this.a + ", otherData=" + Arrays.toString(this.b) + "]";
        }
    }

    public static String read(File file, String str) throws IOException {
        RandomAccessFile randomAccessFile;
        Throwable th;
        String str2 = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            try {
                byte[] a2 = a(randomAccessFile);
                if (a2 != null) {
                    ApkExternalInfo apkExternalInfo = new ApkExternalInfo();
                    apkExternalInfo.a(a2);
                    str2 = apkExternalInfo.a.getProperty(str);
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                } else if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                return str2;
            } catch (Throwable th2) {
                th = th2;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            randomAccessFile = null;
            th = th3;
        }
    }

    public static String readChannelId(File file) throws IOException {
        return read(file, CHANNELID);
    }

    private static byte[] a(RandomAccessFile randomAccessFile) throws IOException {
        boolean z = true;
        long length = randomAccessFile.length() - 22;
        randomAccessFile.seek(length);
        byte[] bytes = a.getBytes();
        int read = randomAccessFile.read();
        while (true) {
            if (read != -1) {
                if (read == bytes[0] && randomAccessFile.read() == bytes[1] && randomAccessFile.read() == bytes[2] && randomAccessFile.read() == bytes[3]) {
                    break;
                }
                length--;
                randomAccessFile.seek(length);
                read = randomAccessFile.read();
            } else {
                z = false;
                break;
            }
        }
        if (!z) {
            throw new ZipException("archive is not a ZIP archive");
        }
        randomAccessFile.seek(16 + length + 4);
        byte[] bArr = new byte[2];
        randomAccessFile.readFully(bArr);
        int value = new ZipShort(bArr).getValue();
        if (value == 0) {
            return null;
        }
        byte[] bArr2 = new byte[value];
        randomAccessFile.read(bArr2);
        return bArr2;
    }
}

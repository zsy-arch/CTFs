package u.upd;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.vsf2f.f2f.ui.utils.area.ConvertUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: Helper.java */
/* loaded from: classes2.dex */
public class n {
    public static final String a = System.getProperty("line.separator");
    private static final String b = "helper";

    public static String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bytes);
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                stringBuffer.append(String.format("%02X", Byte.valueOf(digest[i])));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            return str.replaceAll("[^[a-z][A-Z][0-9][.][_]]", "");
        }
    }

    public static String b(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                stringBuffer.append(Integer.toHexString(b2 & 255));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            b.a(b, "getMD5 error", e);
            return "";
        }
    }

    public static String a(File file) {
        byte[] bArr = new byte[1024];
        try {
            if (!file.isFile()) {
                return "";
            }
            MessageDigest instance = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr, 0, 1024);
                if (read == -1) {
                    fileInputStream.close();
                    return String.format("%1$032x", new BigInteger(1, instance.digest()));
                }
                instance.update(bArr, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(Context context, long j) {
        if (j < 1000) {
            return String.valueOf((int) j) + "B";
        }
        if (j < 1000000) {
            return String.valueOf(Math.round(((float) j) / 1000.0d)) + "K";
        }
        if (j < 1000000000) {
            return String.valueOf(new DecimalFormat("#0.0").format(((float) j) / 1000000.0d)) + "M";
        }
        return String.valueOf(new DecimalFormat("#0.00").format(((float) j) / 1.0E9d)) + "G";
    }

    public static String c(String str) {
        try {
            long longValue = Long.valueOf(str).longValue();
            if (longValue < 1024) {
                return String.valueOf((int) longValue) + "B";
            }
            if (longValue < 1048576) {
                return String.valueOf(new DecimalFormat("#0.00").format(((float) longValue) / 1024.0d)) + "K";
            }
            if (longValue < ConvertUtils.GB) {
                return String.valueOf(new DecimalFormat("#0.00").format(((float) longValue) / 1048576.0d)) + "M";
            }
            return String.valueOf(new DecimalFormat("#0.00").format(((float) longValue) / 1.073741824E9d)) + "G";
        } catch (NumberFormatException e) {
            return str;
        }
    }

    public static void a(Context context, String str) {
        context.startActivity(context.getPackageManager().getLaunchIntentForPackage(str));
    }

    public static boolean b(Context context, String str) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean d(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean e(String str) {
        if (d(str)) {
            return false;
        }
        String lowerCase = str.trim().toLowerCase(Locale.US);
        if (lowerCase.startsWith("http://") || lowerCase.startsWith("https://")) {
            return true;
        }
        return false;
    }

    public static String a() {
        return a(new Date());
    }

    public static String a(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
    }

    public static String a(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] cArr = new char[1024];
        StringWriter stringWriter = new StringWriter();
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (-1 == read) {
                return stringWriter.toString();
            }
            stringWriter.write(cArr, 0, read);
        }
    }

    public static byte[] b(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 == read) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public static void a(File file, byte[] bArr) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            fileOutputStream.write(bArr);
            fileOutputStream.flush();
        } finally {
            a(fileOutputStream);
        }
    }

    public static void a(File file, String str) throws IOException {
        a(file, str.getBytes());
    }

    public static void c(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
            }
        }
    }

    public static void a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception e) {
            }
        }
    }
}

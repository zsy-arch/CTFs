package com.tencent.open;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import com.tencent.open.a.f;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class e extends AsyncTask<Bitmap, Void, HashMap<String, Object>> {
    private static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd-HHmmss", Locale.CHINA);
    private a b;

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public interface a {
        void a(String str);

        void b(String str);
    }

    public e(a aVar) {
        this.b = aVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public HashMap<String, Object> doInBackground(Bitmap... bitmapArr) {
        String str;
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            Bitmap bitmap = bitmapArr[0];
            if (bitmap != null) {
                if (bitmap.getWidth() > 320 || bitmap.getHeight() > 320) {
                    Bitmap a2 = a(bitmap);
                    str = b(a2);
                    a2.recycle();
                } else {
                    str = b(bitmap);
                }
                bitmap.recycle();
                hashMap.put("ResultType", 1);
                hashMap.put("ResultValue", str);
            }
        } catch (Exception e) {
            hashMap.put("ResultType", 0);
            hashMap.put("ResultValue", e.getMessage());
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(HashMap<String, Object> hashMap) {
        if (((Integer) hashMap.get("ResultType")).intValue() == 1) {
            this.b.a((String) hashMap.get("ResultValue"));
        } else {
            this.b.b((String) hashMap.get("ResultValue"));
        }
        super.onPostExecute(hashMap);
    }

    private Bitmap a(Bitmap bitmap) {
        int i = 1;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        if (byteArrayOutputStream.toByteArray().length / 1024 > 1024) {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeStream(byteArrayInputStream, null, options);
        } catch (OutOfMemoryError e) {
            f.c("openSDK_LOG.VoiceHelper", "VoiceHelper decodeStream has OutOfMemoryError!");
        }
        options.inJustDecodeBounds = false;
        int a2 = a(options, 320, 320);
        if (a2 > 0) {
            i = a2;
        }
        f.c("openSDK_LOG.VoiceHelper", "comp be=" + i);
        options.inSampleSize = i;
        try {
            return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, options);
        } catch (OutOfMemoryError e2) {
            f.c("openSDK_LOG.VoiceHelper", "VoiceHelper decodeStream has OutOfMemoryError!");
            return null;
        }
    }

    private int a(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i3 <= i2 && i4 <= i) {
            return 1;
        }
        int round = Math.round(i3 / i2);
        int round2 = Math.round(i4 / i);
        return round < round2 ? round : round2;
    }

    public static void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                File file = new File(str);
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception e) {
            }
        }
    }

    private String b(Bitmap bitmap) {
        Throwable th;
        String str;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            String str2 = b() + File.separator + ".AppCenterWebBuffer";
            str = str2 + File.separator + (a(System.currentTimeMillis()) + ".png");
            File file = new File(str2);
            if (file.exists() || !file.mkdirs()) {
            }
            File file2 = new File(str);
            if (file2.exists()) {
                file2.delete();
            }
            file2.createNewFile();
            fileOutputStream = new FileOutputStream(file2);
        } catch (Exception e) {
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e2) {
                }
            }
        } catch (Exception e3) {
            fileOutputStream2 = fileOutputStream;
            str = "";
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e4) {
                }
            }
            return str;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e5) {
                }
            }
            throw th;
        }
        return str;
    }

    private String b() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        if (new File("/mnt/sdcard-ext").isDirectory()) {
            return "/mnt/sdcard-ext";
        }
        return ".";
    }

    private String a(long j) {
        return a.format(new Date(j));
    }

    public static boolean a() {
        if (!Environment.getExternalStorageState().equals("mounted") && !new File("/mnt/sdcard-ext").isDirectory()) {
            return false;
        }
        return true;
    }
}

package com.tencent.open.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import com.tencent.open.a.f;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class AsynLoadImg {
    private static String c;
    private String a;
    private AsynLoadImgBack b;
    private long d;
    private Handler e;
    private Runnable f = new Runnable() { // from class: com.tencent.open.utils.AsynLoadImg.2
        @Override // java.lang.Runnable
        public void run() {
            boolean z;
            f.a("AsynLoadImg", "saveFileRunnable:");
            String str = "share_qq_" + Util.encrypt(AsynLoadImg.this.a) + ".jpg";
            String str2 = AsynLoadImg.c + str;
            File file = new File(str2);
            Message obtainMessage = AsynLoadImg.this.e.obtainMessage();
            if (file.exists()) {
                obtainMessage.arg1 = 0;
                obtainMessage.obj = str2;
                f.a("AsynLoadImg", "file exists: time:" + (System.currentTimeMillis() - AsynLoadImg.this.d));
            } else {
                Bitmap bitmap = AsynLoadImg.getbitmap(AsynLoadImg.this.a);
                if (bitmap != null) {
                    z = AsynLoadImg.this.saveFile(bitmap, str);
                } else {
                    f.a("AsynLoadImg", "saveFileRunnable:get bmp fail---");
                    z = false;
                }
                if (z) {
                    obtainMessage.arg1 = 0;
                    obtainMessage.obj = str2;
                } else {
                    obtainMessage.arg1 = 1;
                }
                f.a("AsynLoadImg", "file not exists: download time:" + (System.currentTimeMillis() - AsynLoadImg.this.d));
            }
            AsynLoadImg.this.e.sendMessage(obtainMessage);
        }
    };

    public AsynLoadImg(Activity activity) {
        this.e = new Handler(activity.getMainLooper()) { // from class: com.tencent.open.utils.AsynLoadImg.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                f.a("AsynLoadImg", "handleMessage:" + message.arg1);
                if (message.arg1 == 0) {
                    AsynLoadImg.this.b.saved(message.arg1, (String) message.obj);
                } else {
                    AsynLoadImg.this.b.saved(message.arg1, null);
                }
            }
        };
    }

    public void save(String str, AsynLoadImgBack asynLoadImgBack) {
        f.a("AsynLoadImg", "--save---");
        if (str == null || str.equals("")) {
            asynLoadImgBack.saved(1, null);
        } else if (!Util.hasSDCard()) {
            asynLoadImgBack.saved(2, null);
        } else {
            c = Environment.getExternalStorageDirectory() + "/tmp/";
            this.d = System.currentTimeMillis();
            this.a = str;
            this.b = asynLoadImgBack;
            new Thread(this.f).start();
        }
    }

    public boolean saveFile(Bitmap bitmap, String str) {
        Throwable th;
        BufferedOutputStream bufferedOutputStream;
        IOException e;
        BufferedOutputStream bufferedOutputStream2;
        try {
            String str2 = c;
            bufferedOutputStream = null;
            try {
                File file = new File(str2);
                if (!file.exists()) {
                    file.mkdir();
                }
                f.a("AsynLoadImg", "saveFile:" + str);
                bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(new File(str2 + str)));
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bufferedOutputStream2);
            bufferedOutputStream2.flush();
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            return true;
        } catch (IOException e4) {
            e = e4;
            bufferedOutputStream = bufferedOutputStream2;
            e.printStackTrace();
            f.b("AsynLoadImg", "saveFile bmp fail---", e);
            if (bufferedOutputStream == null) {
                return false;
            }
            try {
                bufferedOutputStream.close();
                return false;
            } catch (IOException e5) {
                e5.printStackTrace();
                return false;
            }
        } catch (Throwable th3) {
            th = th3;
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static Bitmap getbitmap(String str) {
        f.a("AsynLoadImg", "getbitmap:" + str);
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            f.a("AsynLoadImg", "image download finished." + str);
            return decodeStream;
        } catch (IOException e) {
            e.printStackTrace();
            f.a("AsynLoadImg", "getbitmap bmp fail---");
            return null;
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            f.a("AsynLoadImg", "getbitmap bmp fail---");
            return null;
        }
    }
}

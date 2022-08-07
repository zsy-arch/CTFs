package com.umeng.update.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.animation.Animation;
import android.widget.ImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Stack;
import java.util.WeakHashMap;
import u.upd.f;
import u.upd.n;

/* compiled from: ResUtil.java */
/* loaded from: classes2.dex */
public class j {
    private static final long c = 52428800;
    private static final long d = 10485760;
    private static final long e = 1800000;
    private static Thread g;
    private static final String b = j.class.getName();
    public static boolean a = false;
    private static final Map<ImageView, String> f = Collections.synchronizedMap(new WeakHashMap());

    /* compiled from: ResUtil.java */
    /* loaded from: classes2.dex */
    public interface a {
        void a(b bVar);

        void a(f.a aVar);
    }

    /* compiled from: ResUtil.java */
    /* loaded from: classes2.dex */
    public enum b {
        BIND_FORM_CACHE,
        BIND_FROM_NET
    }

    private static String b(String str) {
        int lastIndexOf = str.lastIndexOf(".");
        String str2 = "";
        if (lastIndexOf >= 0) {
            str2 = str.substring(lastIndexOf);
        }
        return n.a(str) + str2;
    }

    public static String a(Context context, String str) {
        File file;
        String canonicalPath;
        long j;
        if (n.d(str)) {
            return null;
        }
        try {
            String str2 = b(str) + ".tmp";
            if (u.upd.a.b()) {
                canonicalPath = Environment.getExternalStorageDirectory().getCanonicalPath();
                j = c;
            } else {
                canonicalPath = context.getCacheDir().getCanonicalPath();
                j = d;
            }
            File file2 = new File(canonicalPath + com.umeng.update.a.a);
            a(file2, j, (long) e);
            file = new File(file2, str2);
        } catch (Exception e2) {
            e = e2;
            file = null;
        }
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            InputStream inputStream = (InputStream) new URL(str).openConnection().getContent();
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    inputStream.close();
                    fileOutputStream.close();
                    File file3 = new File(file.getParent(), file.getName().replace(".tmp", ""));
                    file.renameTo(file3);
                    u.upd.b.a(b, "download img[" + str + "]  to " + file3.getCanonicalPath());
                    return file3.getCanonicalPath();
                }
            }
        } catch (Exception e3) {
            e = e3;
            u.upd.b.a(b, e.getStackTrace().toString() + "\t url:\t" + n.a + str);
            if (file != null && file.exists()) {
                file.deleteOnExit();
            }
            return null;
        }
    }

    public static File a(String str, Context context, boolean[] zArr) throws IOException {
        if (u.upd.a.b()) {
            File file = new File(Environment.getExternalStorageDirectory().getCanonicalPath() + com.umeng.update.a.a + str);
            file.mkdirs();
            if (file.exists()) {
                zArr[0] = true;
                return file;
            }
        }
        String absolutePath = context.getCacheDir().getAbsolutePath();
        new File(absolutePath).mkdir();
        a(absolutePath, 505, -1, -1);
        String str2 = absolutePath + com.umeng.update.a.b;
        new File(str2).mkdir();
        a(str2, 505, -1, -1);
        File file2 = new File(str2);
        zArr[0] = false;
        return file2;
    }

    public static boolean a(String str, int i, int i2, int i3) {
        try {
            Class.forName("android.os.FileUtils").getMethod("setPermissions", String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE).invoke(null, str, Integer.valueOf(i), -1, -1);
            return true;
        } catch (ClassNotFoundException e2) {
            u.upd.b.b(b, "error when set permissions:", e2);
            return false;
        } catch (IllegalAccessException e3) {
            u.upd.b.b(b, "error when set permissions:", e3);
            return false;
        } catch (IllegalArgumentException e4) {
            u.upd.b.b(b, "error when set permissions:", e4);
            return false;
        } catch (NoSuchMethodException e5) {
            u.upd.b.b(b, "error when set permissions:", e5);
            return false;
        } catch (InvocationTargetException e6) {
            u.upd.b.b(b, "error when set permissions:", e6);
            return false;
        }
    }

    public static boolean a(String str, int i) {
        int i2 = 432;
        if ((i & 1) != 0) {
            i2 = 436;
        }
        if ((i & 2) != 0) {
            i2 |= 2;
        }
        return a(str, i2, -1, -1);
    }

    public static void a(final File file, long j, final long j2) throws IOException {
        if (file.exists()) {
            if (a(file.getCanonicalFile()) > j) {
                if (g == null) {
                    g = new Thread(new Runnable() { // from class: com.umeng.update.net.j.1
                        @Override // java.lang.Runnable
                        public void run() {
                            j.b(file, j2);
                            Thread unused = j.g = null;
                        }
                    });
                }
                synchronized (g) {
                    g.start();
                }
            }
        } else if (!file.mkdirs()) {
            u.upd.b.b(b, "Failed to create directory" + file.getAbsolutePath() + ". Check permission. Make sure WRITE_EXTERNAL_STORAGE is added in your Manifest.xml");
        }
    }

    private static long a(File file) {
        long j = 0;
        if (file == null || !file.exists() || !file.isDirectory()) {
            return 0L;
        }
        Stack stack = new Stack();
        stack.clear();
        stack.push(file);
        while (!stack.isEmpty()) {
            File[] listFiles = ((File) stack.pop()).listFiles();
            j = j;
            for (File file2 : listFiles) {
                if (!file2.isDirectory()) {
                    j += file2.length();
                }
            }
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(File file, long j) {
        if (file != null && file.exists() && file.canWrite() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (File file2 : listFiles) {
                if (!file2.isDirectory() && new Date().getTime() - file2.lastModified() > j) {
                    file2.delete();
                }
            }
        }
    }

    protected static File b(Context context, String str) throws IOException {
        String canonicalPath;
        String b2 = b(str);
        if (u.upd.a.b()) {
            canonicalPath = Environment.getExternalStorageDirectory().getCanonicalPath();
        } else {
            canonicalPath = context.getCacheDir().getCanonicalPath();
        }
        File file = new File(new File(canonicalPath + com.umeng.update.a.a), b2);
        if (file.exists()) {
            return file;
        }
        return null;
    }

    public static void a(Context context, ImageView imageView, String str, boolean z) {
        a(context, imageView, str, z, null, null, false);
    }

    public static void a(Context context, ImageView imageView, String str, boolean z, a aVar) {
        a(context, imageView, str, z, aVar, null, false);
    }

    public static void a(Context context, ImageView imageView, String str, boolean z, a aVar, Animation animation) {
        a(context, imageView, str, z, aVar, null, false);
    }

    public static void a(Context context, ImageView imageView, String str, boolean z, a aVar, Animation animation, boolean z2) {
        if (imageView != null) {
            f.put(imageView, str);
            try {
                File b2 = b(context, str);
                if (b2 == null || !b2.exists() || a) {
                    new c(context, imageView, str, b.BIND_FROM_NET, null, z, aVar, animation, z2).execute(new Object[0]);
                    return;
                }
                if (aVar != null) {
                    aVar.a(b.BIND_FORM_CACHE);
                }
                Drawable c2 = c(b2.getAbsolutePath());
                if (c2 == null) {
                    b2.delete();
                }
                b(context, imageView, c2, z, aVar, animation, z2, str);
            } catch (Exception e2) {
                u.upd.b.b(b, "", e2);
                if (aVar != null) {
                    aVar.a(f.a.FAIL);
                }
            }
        }
    }

    private static boolean a(ImageView imageView, String str) {
        String str2 = f.get(imageView);
        return str2 != null && !str2.equals(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void b(Context context, ImageView imageView, Drawable drawable, boolean z, a aVar, Animation animation, boolean z2, String str) {
        synchronized (j.class) {
            if (z2 && drawable != null) {
                try {
                    drawable = new BitmapDrawable(a(((BitmapDrawable) drawable).getBitmap()));
                } catch (Exception e2) {
                    u.upd.b.b(b, "bind failed", e2);
                    if (aVar != null) {
                        aVar.a(f.a.FAIL);
                    }
                }
            }
            if (drawable == null || imageView == null) {
                if (aVar != null) {
                    aVar.a(f.a.FAIL);
                }
                u.upd.b.e(b, "bind drawable failed. drawable [" + drawable + "]  imageView[+" + imageView + "+]");
            } else if (!a(imageView, str)) {
                if (z) {
                    imageView.setBackgroundDrawable(drawable);
                } else {
                    imageView.setImageDrawable(drawable);
                }
                if (animation != null) {
                    imageView.startAnimation(animation);
                }
                if (aVar != null) {
                    aVar.a(f.a.SUCCESS);
                }
            } else if (aVar != null) {
                aVar.a(f.a.FAIL);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ResUtil.java */
    /* loaded from: classes2.dex */
    public static class c extends AsyncTask<Object, Integer, Drawable> {
        private Context a;
        private String b;
        private ImageView c;
        private b d;
        private boolean e;
        private a f;
        private Animation g;
        private boolean h;
        private File i;

        public c(Context context, ImageView imageView, String str, b bVar, File file, boolean z, a aVar, Animation animation, boolean z2) {
            this.i = file;
            this.a = context;
            this.b = str;
            this.f = aVar;
            this.d = bVar;
            this.e = z;
            this.g = animation;
            this.c = imageView;
            this.h = z2;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            if (this.f != null) {
                this.f.a(this.d);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Drawable drawable) {
            j.b(this.a, this.c, drawable, this.e, this.f, this.g, this.h, this.b);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: a */
        public Drawable doInBackground(Object... objArr) {
            Drawable drawable;
            if (j.a) {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (this.i == null || !this.i.exists()) {
                try {
                    j.a(this.a, this.b);
                    File b = j.b(this.a, this.b);
                    if (b == null || !b.exists()) {
                        drawable = null;
                    } else {
                        drawable = j.c(b.getAbsolutePath());
                    }
                    u.upd.b.c(j.b, "get drawable from net else file.");
                    return drawable;
                } catch (Exception e2) {
                    u.upd.b.e(j.b, e2.toString(), e2);
                    return null;
                }
            } else {
                Drawable c = j.c(this.i.getAbsolutePath());
                if (c == null) {
                    this.i.delete();
                }
                u.upd.b.c(j.b, "get drawable from cacheFile.");
                return c;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Drawable c(String str) {
        try {
            return Drawable.createFromPath(str);
        } catch (OutOfMemoryError e2) {
            u.upd.b.e(b, "Resutil fetchImage OutOfMemoryError:" + e2.toString());
            return null;
        }
    }

    private static Bitmap a(Bitmap bitmap) {
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            RectF rectF = new RectF(rect);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(-12434878);
            canvas.drawRoundRect(rectF, bitmap.getWidth() / 6, bitmap.getHeight() / 6, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            bitmap.recycle();
            return createBitmap;
        } catch (OutOfMemoryError e2) {
            u.upd.b.e(b, "Cant`t create round corner bitmap. [OutOfMemoryError] ");
            return null;
        }
    }
}

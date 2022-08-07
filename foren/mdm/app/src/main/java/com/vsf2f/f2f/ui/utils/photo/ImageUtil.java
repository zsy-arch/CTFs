package com.vsf2f.f2f.ui.utils.photo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import com.hy.frame.util.FolderUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class ImageUtil {
    public static void scaleImage(String srcPath, String finishPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        newOpts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(srcPath, newOpts);
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        int be = 1;
        if (w > h && w > 480) {
            be = w / 480;
        } else if (h > w && h > 800) {
            be = h / 800;
        }
        if (be <= 0) {
            be = 1;
        }
        newOpts.inSampleSize = be;
        newOpts.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        int degree = readPictureDegree(srcPath);
        if (degree != 0) {
            bitmap = toTurn(bitmap, degree);
        }
        compressImage(bitmap, finishPath);
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0025, code lost:
        if (r3 == 20) goto L_0x0027;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void compressImage(android.graphics.Bitmap r6, java.lang.String r7) {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            android.graphics.Bitmap$CompressFormat r4 = android.graphics.Bitmap.CompressFormat.JPEG
            r5 = 60
            r6.compress(r4, r5, r0)
            r3 = 60
        L_0x000e:
            byte[] r4 = r0.toByteArray()
            int r4 = r4.length
            int r4 = r4 / 1024
            r5 = 200(0xc8, float:2.8E-43)
            if (r4 <= r5) goto L_0x0027
            r0.reset()
            int r3 = r3 + (-10)
            android.graphics.Bitmap$CompressFormat r4 = android.graphics.Bitmap.CompressFormat.JPEG
            r6.compress(r4, r3, r0)
            r4 = 20
            if (r3 != r4) goto L_0x000e
        L_0x0027:
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: IOException -> 0x003a
            r2.<init>(r7)     // Catch: IOException -> 0x003a
            byte[] r4 = r0.toByteArray()     // Catch: IOException -> 0x003a
            r2.write(r4)     // Catch: IOException -> 0x003a
            r2.flush()     // Catch: IOException -> 0x003a
            r2.close()     // Catch: IOException -> 0x003a
        L_0x0039:
            return
        L_0x003a:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0039
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vsf2f.f2f.ui.utils.photo.ImageUtil.compressImage(android.graphics.Bitmap, java.lang.String):void");
    }

    public static String compressImage(String srcPath, String fileName) {
        return compressImage(srcPath, fileName, 200);
    }

    public static String compressImage(String srcPath, String fileName, int size) {
        try {
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
            newOpts.inJustDecodeBounds = false;
            InputStream inputStream = new FileInputStream(new File(srcPath));
            Bitmap image = BitmapFactory.decodeStream(inputStream, null, newOpts);
            inputStream.close();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 60, baos);
            int options = 60;
            while (baos.toByteArray().length / 1024 > size) {
                baos.reset();
                options -= 10;
                image.compress(Bitmap.CompressFormat.JPEG, options, baos);
                if (options == 20) {
                    break;
                }
            }
            image.recycle();
            String finishPath = FolderUtil.getCachePathAlbum() + File.separator + fileName + ".jpg";
            FileOutputStream fos = new FileOutputStream(finishPath);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
            return finishPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static int readPictureDegree(String path) {
        try {
            switch (new ExifInterface(path).getAttributeInt("Orientation", 1)) {
                case 3:
                    return 180;
                case 4:
                case 5:
                case 7:
                default:
                    return 0;
                case 6:
                    return 90;
                case 8:
                    return 270;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void toTurn(String path, int degree) {
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            Matrix matrix = new Matrix();
            matrix.postRotate(degree);
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true).compress(Bitmap.CompressFormat.JPEG, 90, new FileOutputStream(path));
        } catch (Exception e) {
        }
    }

    public static Bitmap toTurn(Bitmap bitmap, int degree) {
        try {
            Matrix matrix = new Matrix();
            matrix.postRotate(degree);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (Exception e) {
            return null;
        }
    }

    public Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;
        }
        return BitmapFactory.decodeStream(new ByteArrayInputStream(baos.toByteArray()), null, null);
    }

    public synchronized boolean compressByPath(String path, int maxWidth, int maxHeight) {
        boolean z = true;
        z = false;
        synchronized (this) {
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            newOpts.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, newOpts);
            newOpts.inJustDecodeBounds = false;
            int w = newOpts.outWidth;
            int h = newOpts.outHeight;
            int be = 1;
            if (w > h && w > maxWidth) {
                be = newOpts.outWidth / maxWidth;
            } else if (w < h && h > maxHeight) {
                be = newOpts.outHeight / maxHeight;
            }
            if (be <= 0) {
                be = 1;
            }
            newOpts.inSampleSize = be;
            Bitmap bitmap = BitmapFactory.decodeFile(path, newOpts);
            File f = new File(path);
            try {
                if (!f.exists()) {
                    f.createNewFile();
                }
                FileOutputStream fOut = new FileOutputStream(f);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                fOut.flush();
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return z;
    }

    public synchronized boolean compressByBitmap(Bitmap bitmap, String path, int maxWidth, int maxHeight) {
        boolean z = false;
        synchronized (this) {
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            newOpts.inJustDecodeBounds = false;
            int w = newOpts.outWidth;
            int h = newOpts.outHeight;
            int be = 1;
            if (w > h && w > maxWidth) {
                be = newOpts.outWidth / maxWidth;
            } else if (w < h && h > maxHeight) {
                be = newOpts.outHeight / maxHeight;
            }
            if (be <= 0) {
                be = 1;
            }
            newOpts.inSampleSize = be;
            Bitmap bitmap2 = BitmapFactory.decodeFile(path, newOpts);
            File f = new File(path);
            try {
                if (!f.exists()) {
                    f.createNewFile();
                }
                FileOutputStream fOut = new FileOutputStream(f);
                bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                fOut.flush();
                fOut.close();
                z = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return z;
    }

    public Bitmap comp(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        int be = 1;
        if (w > h && w > 480.0f) {
            be = (int) (newOpts.outWidth / 480.0f);
        } else if (w < h && h > 800.0f) {
            be = (int) (newOpts.outHeight / 800.0f);
        }
        if (be <= 0) {
            be = 1;
        }
        newOpts.inSampleSize = be;
        return compressImage(BitmapFactory.decodeStream(new ByteArrayInputStream(baos.toByteArray()), null, newOpts));
    }
}

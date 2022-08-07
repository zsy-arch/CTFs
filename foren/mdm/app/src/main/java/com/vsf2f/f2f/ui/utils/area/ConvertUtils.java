package com.vsf2f.f2f.ui.utils.area;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.TypedValue;
import com.hy.frame.util.MyLog;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes2.dex */
public class ConvertUtils {
    public static final long GB = 1073741824;
    public static final long KB = 1024;
    public static final long MB = 1048576;

    public static String toColorString(int color) {
        return toColorString(color, false);
    }

    public static String toColorString(int color, boolean includeAlpha) {
        String alpha = Integer.toHexString(Color.alpha(color));
        String red = Integer.toHexString(Color.red(color));
        String green = Integer.toHexString(Color.green(color));
        String blue = Integer.toHexString(Color.blue(color));
        if (alpha.length() == 1) {
            alpha = "0" + alpha;
        }
        if (red.length() == 1) {
            red = "0" + red;
        }
        if (green.length() == 1) {
            green = "0" + green;
        }
        if (blue.length() == 1) {
            blue = "0" + blue;
        }
        if (includeAlpha) {
            String colorString = alpha + red + green + blue;
            MyLog.d(String.format(Locale.CHINA, "%d to color string is %s", Integer.valueOf(color), colorString));
            return colorString;
        }
        String colorString2 = red + green + blue;
        MyLog.d(String.format(Locale.CHINA, "%d to color string is %s%s%s%s, exclude alpha is %s", Integer.valueOf(color), alpha, red, green, blue, colorString2));
        return colorString2;
    }

    public static String toDateString(Date date, String format) {
        return new SimpleDateFormat(format, Locale.CHINA).format(date);
    }

    public static String toDateString(String format) {
        return toDateString(Calendar.getInstance(Locale.CHINA).getTime(), format);
    }

    public static Date toDate(String dateStr) {
        return DateUtils.parseDate(dateStr);
    }

    public static long toTimemillis(String dateStr) {
        return toDate(dateStr).getTime();
    }

    public static String toString(Object[] objects) {
        return Arrays.deepToString(objects);
    }

    public static String toString(Object[] objects, String tag) {
        StringBuilder sb = new StringBuilder();
        for (Object object : objects) {
            sb.append(object);
            sb.append(tag);
        }
        return sb.toString();
    }

    public static byte[] toByteArray(InputStream is) {
        if (is == null) {
            return null;
        }
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            while (true) {
                int rc = is.read(buff, 0, 100);
                if (rc > 0) {
                    os.write(buff, 0, rc);
                } else {
                    byte[] byteArray = os.toByteArray();
                    os.close();
                    return byteArray;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] toByteArray(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
        byte[] byteArray = os.toByteArray();
        try {
            os.close();
            return byteArray;
        } catch (IOException e) {
            e.printStackTrace();
            return byteArray;
        }
    }

    public static Bitmap toBitmap(byte[] bytes, int width, int height) {
        if (bytes.length == 0) {
            return null;
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inDither = false;
            options.inPreferredConfig = null;
            if (width > 0 && height > 0) {
                options.outWidth = width;
                options.outHeight = height;
            }
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        } catch (Exception e) {
            MyLog.e(e);
            return null;
        }
    }

    public static Bitmap toBitmap(byte[] bytes) {
        return toBitmap(bytes, -1, -1);
    }

    @TargetApi(11)
    public static Bitmap toBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (drawable instanceof ColorDrawable) {
            Bitmap bitmap = Bitmap.createBitmap(32, 32, Bitmap.Config.ARGB_8888);
            if (Build.VERSION.SDK_INT < 11) {
                return bitmap;
            }
            new Canvas(bitmap).drawColor(((ColorDrawable) drawable).getColor());
            return bitmap;
        } else if (!(drawable instanceof NinePatchDrawable)) {
            return null;
        } else {
            Bitmap bitmap2 = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap2);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmap2;
        }
    }

    public static Drawable toDrawable(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable((Resources) null, bitmap);
    }

    public static byte[] toByteArray(Drawable drawable) {
        return toByteArray(toBitmap(drawable));
    }

    public static Drawable toDrawable(byte[] bytes) {
        return toDrawable(toBitmap(bytes));
    }

    public static int toPx(Context context, float dpValue) {
        int pxValue = (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5f);
        MyLog.d(dpValue + " dp == " + pxValue + " px");
        return pxValue;
    }

    public static int toPx(float dpValue) {
        return (int) TypedValue.applyDimension(1, dpValue, Resources.getSystem().getDisplayMetrics());
    }

    public static int toDp(Context context, float pxValue) {
        int dpValue = (int) ((pxValue / context.getResources().getDisplayMetrics().density) + 0.5f);
        MyLog.d(pxValue + " px == " + dpValue + " dp");
        return dpValue;
    }

    public static int toSp(Context context, float pxValue) {
        int spValue = (int) ((pxValue / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
        MyLog.d(pxValue + " px == " + spValue + " sp");
        return spValue;
    }

    public static String toGbk(String str) {
        try {
            return new String(str.getBytes("utf-8"), "gbk");
        } catch (UnsupportedEncodingException e) {
            MyLog.w(e.getMessage());
            return str;
        }
    }

    public static String toFileSizeString(long fileSize) {
        DecimalFormat df = new DecimalFormat("0.00");
        if (fileSize < 1024) {
            return fileSize + "B";
        }
        if (fileSize < 1048576) {
            return df.format(fileSize / 1024.0d) + "K";
        }
        if (fileSize < GB) {
            return df.format(fileSize / 1048576.0d) + "M";
        }
        return df.format(fileSize / 1.073741824E9d) + "G";
    }

    public static String toString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }
            is.close();
        } catch (IOException e) {
            MyLog.e(e);
        }
        return sb.toString();
    }

    public static ShapeDrawable toRoundDrawable(int color, int radius) {
        int r = toPx(radius);
        ShapeDrawable drawable = new ShapeDrawable(new RoundRectShape(new float[]{r, r, r, r, r, r, r, r}, null, null));
        drawable.getPaint().setColor(color);
        return drawable;
    }

    public static ColorStateList toColorStateList(int normalColor, int pressedColor, int focusedColor, int unableColor) {
        return new ColorStateList(new int[][]{new int[]{16842919, 16842910}, new int[]{16842910, 16842908}, new int[]{16842910}, new int[]{16842908}, new int[]{16842909}, new int[0]}, new int[]{pressedColor, focusedColor, normalColor, focusedColor, unableColor, normalColor});
    }

    public static ColorStateList toColorStateList(int normalColor, int pressedColor) {
        return toColorStateList(normalColor, pressedColor, pressedColor, normalColor);
    }

    public static StateListDrawable toStateListDrawable(Drawable normal, Drawable pressed, Drawable focused, Drawable unable) {
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{16842919, 16842910}, pressed);
        drawable.addState(new int[]{16842910, 16842908}, focused);
        drawable.addState(new int[]{16842910}, normal);
        drawable.addState(new int[]{16842908}, focused);
        drawable.addState(new int[]{16842909}, unable);
        drawable.addState(new int[0], normal);
        return drawable;
    }

    public static StateListDrawable toStateListDrawable(int normalColor, int pressedColor, int focusedColor, int unableColor) {
        StateListDrawable drawable = new StateListDrawable();
        Drawable normal = new ColorDrawable(normalColor);
        Drawable pressed = new ColorDrawable(pressedColor);
        Drawable focused = new ColorDrawable(focusedColor);
        Drawable unable = new ColorDrawable(unableColor);
        drawable.addState(new int[]{16842919, 16842910}, pressed);
        drawable.addState(new int[]{16842910, 16842908}, focused);
        drawable.addState(new int[]{16842910}, normal);
        drawable.addState(new int[]{16842908}, focused);
        drawable.addState(new int[]{16842909}, unable);
        drawable.addState(new int[0], normal);
        return drawable;
    }

    public static StateListDrawable toStateListDrawable(Drawable normal, Drawable pressed) {
        return toStateListDrawable(normal, pressed, pressed, normal);
    }

    public static StateListDrawable toStateListDrawable(int normalColor, int pressedColor) {
        return toStateListDrawable(normalColor, pressedColor, pressedColor, normalColor);
    }
}

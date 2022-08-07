package com.vsf2f.f2f.ui.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.InputDeviceCompat;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.hy.frame.util.MyLog;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.LaunchActivity;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
public class BadgeUtil {
    private static final String INTENT_ACTION = "android.intent.action.BADGE_COUNT_UPDATE";
    private static final String INTENT_EXTRA_ACTIVITY_NAME = "badge_count_class_name";
    private static final String INTENT_EXTRA_BADGE_COUNT = "badge_count";
    private static final String INTENT_EXTRA_PACKAGENAME = "badge_count_package_name";

    public static void setBadgeCount(Context context, int badgeCount) {
        MyLog.e("系统：" + Build.MANUFACTURER);
        if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
            sendToXiaoMi(context, badgeCount);
        } else if (Build.MANUFACTURER.equalsIgnoreCase("samsung")) {
            sendToSony(context, badgeCount);
        } else if (Build.MANUFACTURER.toLowerCase().contains("sony")) {
            sendToSamsumg(context, badgeCount);
        } else if (Build.MANUFACTURER.equalsIgnoreCase("OPPO")) {
            sendToOPPO(context, badgeCount);
        }
    }

    private static String getLauncherClassName(Context context) {
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        for (ResolveInfo resolveInfo : pm.queryIntentActivities(intent, 0)) {
            if (resolveInfo.activityInfo.applicationInfo.packageName.equalsIgnoreCase(context.getPackageName())) {
                return resolveInfo.activityInfo.name;
            }
        }
        return null;
    }

    private static void sendToXiaoMi(Context context, int number) {
        Notification notification;
        NotificationManager nm;
        try {
            nm = (NotificationManager) context.getSystemService("notification");
            notification = null;
            try {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                builder.setContentTitle("您有" + number + "未读消息");
                builder.setTicker("您有" + number + "未读消息");
                builder.setAutoCancel(true);
                builder.setSmallIcon(R.mipmap.ico_logo);
                builder.setDefaults(4);
                notification = builder.build();
                Object miuiNotification = Class.forName("android.app.MiuiNotification").newInstance();
                Field field = miuiNotification.getClass().getDeclaredField("messageCount");
                field.setAccessible(true);
                field.set(miuiNotification, Integer.valueOf(number));
                Field field2 = notification.getClass().getField("extraNotification");
                field2.setAccessible(true);
                field2.set(notification, miuiNotification);
                MyLog.e("Xiaomi=>isSendOk=>1");
                if (notification != null && 1 != 0) {
                    nm.notify(101010, notification);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Intent localIntent = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
                localIntent.putExtra("android.intent.extra.update_application_component_name", context.getPackageName() + "/" + LaunchActivity.class);
                localIntent.putExtra("android.intent.extra.update_application_message_text", number);
                context.sendBroadcast(localIntent);
                if (notification != null && 0 != 0) {
                    nm.notify(101010, notification);
                }
            }
        } catch (Throwable th) {
            if (!(notification == null || 1 == 0)) {
                nm.notify(101010, notification);
            }
            throw th;
        }
    }

    private static void sendToSony(Context context, int number) {
        boolean isShow = true;
        if ("0".equals(Integer.valueOf(number))) {
            isShow = false;
        }
        Intent localIntent = new Intent();
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", isShow);
        localIntent.setAction("com.sonyericsson.home.action.UPDATE_BADGE");
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", LaunchActivity.class);
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", number);
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", context.getPackageName());
        context.sendBroadcast(localIntent);
        MyLog.e("Sony,isSendOk");
    }

    private static void sendToSamsumg(Context context, int number) {
        Intent localIntent = new Intent(INTENT_ACTION);
        localIntent.putExtra(INTENT_EXTRA_BADGE_COUNT, number);
        localIntent.putExtra(INTENT_EXTRA_PACKAGENAME, context.getPackageName());
        localIntent.putExtra(INTENT_EXTRA_ACTIVITY_NAME, LaunchActivity.class);
        context.sendBroadcast(localIntent);
        MyLog.e("Samsumg,isSendOk");
    }

    private static void sendToOPPO(Context context, int number) {
        try {
            Bundle extras = new Bundle();
            extras.putInt("app_badge_count", number);
            context.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", String.valueOf(number), extras);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void sendToVIVO(Context context, int number) {
        Intent intent = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
        intent.putExtra("packageName", context.getPackageName());
        intent.putExtra("className", LaunchActivity.class);
        intent.putExtra("notificationNum", number);
        context.sendBroadcast(intent);
    }

    public static void installRawShortCut(Context context, boolean isShowNum, int num) {
        MyLog.e("installShortCut....");
        Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        shortcutIntent.putExtra("android.intent.extra.shortcut.NAME", context.getString(R.string.app_name));
        shortcutIntent.putExtra("duplicate", false);
        Intent mainIntent = new Intent("android.intent.action.MAIN");
        mainIntent.addCategory("android.intent.category.LAUNCHER");
        mainIntent.setClass(context, LaunchActivity.class);
        shortcutIntent.putExtra("android.intent.extra.shortcut.INTENT", mainIntent);
        shortcutIntent.putExtra("android.intent.extra.shortcut.ICON", generatorNumIcon2(context, ((BitmapDrawable) context.getResources().getDrawable(R.mipmap.ico_logo)).getBitmap(), isShowNum, String.valueOf(num)));
        context.sendBroadcast(shortcutIntent);
    }

    public static Bitmap generatorNumIcon2(Context context, Bitmap icon, boolean isShowNum, String num) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        float factor = dm.density / 1.5f;
        MyLog.e("density:" + dm.density + "\\dpi:" + dm.densityDpi + "\\factor:" + factor);
        int iconSize = (int) context.getResources().getDimension(17104896);
        Bitmap numIcon = Bitmap.createBitmap(iconSize, iconSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(numIcon);
        Paint iconPaint = new Paint();
        iconPaint.setDither(true);
        iconPaint.setFilterBitmap(true);
        canvas.drawBitmap(icon, new Rect(0, 0, icon.getWidth(), icon.getHeight()), new Rect(0, 0, iconSize, iconSize), iconPaint);
        if (isShowNum) {
            if (TextUtils.isEmpty(num)) {
                num = "0";
            }
            if (!TextUtils.isDigitsOnly(num)) {
                MyLog.e("the num is not digit :" + num);
                num = "0";
            }
            if (Integer.valueOf(num).intValue() > 99) {
                num = "99+";
            }
            Paint numPaint = new Paint((int) InputDeviceCompat.SOURCE_KEYBOARD);
            numPaint.setColor(-1);
            numPaint.setTextSize(20.0f * factor);
            numPaint.setTypeface(Typeface.DEFAULT_BOLD);
            int textWidth = (int) numPaint.measureText(num, 0, num.length());
            MyLog.e("text width:" + textWidth);
            int backgroundHeight = (int) (30.0f * factor);
            int backgroundWidth = textWidth > backgroundHeight ? (int) (textWidth + (10.0f * factor)) : backgroundHeight;
            canvas.save();
            ShapeDrawable drawable = getDefaultBackground(context);
            drawable.setIntrinsicHeight(backgroundHeight);
            drawable.setIntrinsicWidth(backgroundWidth);
            drawable.setBounds(0, 0, backgroundWidth, backgroundHeight);
            canvas.translate(iconSize - backgroundWidth, 0.0f);
            drawable.draw(canvas);
            canvas.restore();
            canvas.drawText(num, iconSize - ((backgroundWidth + textWidth) / 2), 22.0f * factor, numPaint);
        }
        return numIcon;
    }

    private static ShapeDrawable getDefaultBackground(Context context) {
        int r = dipToPixels(context, 8);
        ShapeDrawable drawable = new ShapeDrawable(new RoundRectShape(new float[]{r, r, r, r, r, r, r, r}, null, null));
        drawable.getPaint().setColor(Color.parseColor("#CCFF0000"));
        return drawable;
    }

    public static int dipToPixels(Context context, int dip) {
        return (int) TypedValue.applyDimension(1, dip, context.getResources().getDisplayMetrics());
    }
}

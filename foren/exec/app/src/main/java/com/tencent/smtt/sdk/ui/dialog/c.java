package com.tencent.smtt.sdk.ui.dialog;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public static float f1434a = -1.0f;

    /* renamed from: b  reason: collision with root package name */
    public static int f1435b = -1;

    /* renamed from: c  reason: collision with root package name */
    public static int f1436c = -1;

    public static int a(Context context) {
        if (f1435b == -1) {
            b(context);
        }
        return f1435b;
    }

    public static int a(Context context, float f) {
        return b(context, (f * 160.0f) / 320.0f);
    }

    public static int b(Context context, float f) {
        if (f1434a == -1.0f) {
            b(context);
        }
        return (int) ((f * f1434a) + 0.5f);
    }

    public static void b(Context context) {
        if (f1434a < 0.0f) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            f1434a = displayMetrics.density;
            f1435b = displayMetrics.heightPixels;
        }
    }
}

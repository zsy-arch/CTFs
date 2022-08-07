package com.hy.frame.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.hy.frame.R;

/* loaded from: classes2.dex */
public class MyToast {
    public static Toast mToast;

    public static void init(Context context) {
        mToast = new Toast(context);
    }

    public static void show(Context context, String msg) {
        show(context, msg, 0);
    }

    public static void show(Context context, String msg, int time) {
        if (mToast == null) {
            init(context);
        }
        if (HyUtil.isNoEmpty(msg)) {
            View v = LayoutInflater.from(context).inflate(R.layout.toast, (ViewGroup) null);
            ((TextView) v.findViewById(R.id.txtMsg)).setText(msg);
            mToast.setGravity(17, 0, 0);
            mToast.setDuration(time);
            mToast.setView(v);
            mToast.show();
            return;
        }
        MyLog.e("MyToast", "no msg");
    }

    public static void show(Context context, int resid) {
        show(context, context.getString(resid));
    }

    public static void show(Context context, View v) {
        if (mToast == null) {
            init(context);
        }
        mToast.setView(v);
        mToast.setGravity(17, 0, 0);
        mToast.setDuration(0);
        mToast.setView(v);
        mToast.show();
    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}

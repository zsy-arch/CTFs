package com.cdlinglu.utils.Photo.zoom;

import android.annotation.TargetApi;
import android.view.View;

@TargetApi(16)
/* loaded from: classes.dex */
public class SDK16 {
    public static void postOnAnimation(View view, Runnable r) {
        view.postOnAnimation(r);
    }
}

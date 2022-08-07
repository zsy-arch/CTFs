package com.easeui.widget.photoview;

import android.annotation.TargetApi;
import android.view.View;

@TargetApi(16)
/* loaded from: classes.dex */
class SDK16 {
    SDK16() {
    }

    public static void postOnAnimation(View view, Runnable r) {
        view.postOnAnimation(r);
    }
}

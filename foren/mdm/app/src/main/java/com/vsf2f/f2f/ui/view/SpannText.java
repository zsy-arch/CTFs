package com.vsf2f.f2f.ui.view;

import android.text.method.LinkMovementMethod;
import android.widget.TextView;

/* loaded from: classes2.dex */
public class SpannText {
    public static void getSpanText(TextView tv, String txt) {
        tv.setTextIsSelectable(true);
        tv.setAutoLinkMask(15);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(txt);
    }
}

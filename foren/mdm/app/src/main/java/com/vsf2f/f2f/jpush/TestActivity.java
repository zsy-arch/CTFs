package com.vsf2f.f2f.jpush;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;

/* loaded from: classes2.dex */
public class TestActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("用户自定义打开的Activity");
        if (getIntent() != null) {
            Bundle bundle = getIntent().getExtras();
            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            tv.setText("Title : " + title + "  Content : " + bundle.getString(JPushInterface.EXTRA_ALERT));
        }
        addContentView(tv, new ViewGroup.LayoutParams(-1, -1));
    }
}

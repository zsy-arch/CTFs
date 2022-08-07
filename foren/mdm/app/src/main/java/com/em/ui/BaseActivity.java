package com.em.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import com.easeui.ui.EaseBaseActivity;
import com.umeng.analytics.MobclickAgent;

@SuppressLint({"Registered"})
/* loaded from: classes.dex */
public class BaseActivity extends EaseBaseActivity {
    @Override // com.easeui.ui.EaseBaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    @Override // com.easeui.ui.EaseBaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        MobclickAgent.onPause(this);
    }
}

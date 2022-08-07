package com.easeui.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.easeui.controller.EaseUI;

@SuppressLint({"NewApi", "Registered"})
/* loaded from: classes.dex */
public class EaseBaseActivity extends FragmentActivity {
    protected InputMethodManager inputMethodManager;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        if (!isTaskRoot()) {
            Intent intent = getIntent();
            String action = intent.getAction();
            if (intent.hasCategory("android.intent.category.LAUNCHER") && action.equals("android.intent.action.MAIN")) {
                finish();
                return;
            }
        }
        this.inputMethodManager = (InputMethodManager) getSystemService("input_method");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        EaseUI.getInstance().getNotifier().reset();
    }

    protected void hideSoftKeyboard() {
        if (getWindow().getAttributes().softInputMode != 2 && getCurrentFocus() != null) {
            this.inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        }
    }

    public void back(View view) {
        finish();
    }
}

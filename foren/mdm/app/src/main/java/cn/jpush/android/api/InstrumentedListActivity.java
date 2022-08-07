package cn.jpush.android.api;

import android.app.ListActivity;

/* loaded from: classes.dex */
public class InstrumentedListActivity extends ListActivity {
    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
    }
}

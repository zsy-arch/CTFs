package com.mob.tools;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.mob.tools.utils.ReflectHelper;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class MobUIShell extends Activity {
    private static HashMap<String, FakeActivity> executors = new HashMap<>();
    public static int forceTheme;
    private FakeActivity executor;

    static {
        MobLog.getInstance().d("===============================", new Object[0]);
        MobLog.getInstance().d("MobTools " + "2016-07-07".replace("-0", "-").replace("-", "."), new Object[0]);
        MobLog.getInstance().d("===============================", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String registerExecutor(Object executor) {
        return registerExecutor(String.valueOf(System.currentTimeMillis()), executor);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String registerExecutor(String scheme, Object executor) {
        executors.put(scheme, (FakeActivity) executor);
        return scheme;
    }

    @Override // android.app.Activity
    public void finish() {
        if (this.executor == null || !this.executor.onFinish()) {
            super.finish();
        }
    }

    public FakeActivity getDefault() {
        Object fa;
        try {
            String defaultActivity = getPackageManager().getActivityInfo(getComponentName(), 128).metaData.getString("defaultActivity");
            if (!TextUtils.isEmpty(defaultActivity)) {
                if (defaultActivity.startsWith(".")) {
                    defaultActivity = getPackageName() + defaultActivity;
                }
                String name = ReflectHelper.importClass(defaultActivity);
                if (!TextUtils.isEmpty(name) && (fa = ReflectHelper.newInstance(name, new Object[0])) != null && (fa instanceof FakeActivity)) {
                    return (FakeActivity) fa;
                }
            }
        } catch (Throwable t) {
            MobLog.getInstance().w(t);
        }
        return null;
    }

    public Object getExecutor() {
        return this.executor;
    }

    @Override // android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.executor != null) {
            this.executor.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.executor != null) {
            this.executor.onConfigurationChanged(newConfig);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        Intent mIntent = getIntent();
        String launchTime = mIntent.getStringExtra("launch_time");
        String executorName = mIntent.getStringExtra("executor_name");
        this.executor = executors.remove(launchTime);
        if (this.executor == null) {
            this.executor = executors.remove(mIntent.getScheme());
            if (this.executor == null) {
                this.executor = getDefault();
                if (this.executor == null) {
                    MobLog.getInstance().w(new RuntimeException("Executor lost! launchTime = " + launchTime + ", executorName: " + executorName));
                    super.onCreate(savedInstanceState);
                    finish();
                    return;
                }
            }
        }
        MobLog.getInstance().i("MobUIShell found executor: " + this.executor.getClass(), new Object[0]);
        this.executor.setActivity(this);
        super.onCreate(savedInstanceState);
        MobLog.getInstance().d(this.executor.getClass().getSimpleName() + " onCreate", new Object[0]);
        this.executor.onCreate();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        return this.executor != null ? this.executor.onCreateOptionsMenu(menu) : super.onCreateOptionsMenu(menu);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        if (this.executor != null) {
            this.executor.sendResult();
            MobLog.getInstance().d(this.executor.getClass().getSimpleName() + " onDestroy", new Object[0]);
            this.executor.onDestroy();
        }
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean ret = false;
        if (this.executor != null) {
            ret = this.executor.onKeyEvent(keyCode, event);
        }
        if (ret) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        boolean ret = false;
        if (this.executor != null) {
            ret = this.executor.onKeyEvent(keyCode, event);
        }
        if (ret) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        if (this.executor == null) {
            super.onNewIntent(intent);
        } else {
            this.executor.onNewIntent(intent);
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        return this.executor != null ? this.executor.onOptionsItemSelected(item) : super.onOptionsItemSelected(item);
    }

    @Override // android.app.Activity
    protected void onPause() {
        if (this.executor != null) {
            MobLog.getInstance().d(this.executor.getClass().getSimpleName() + " onPause", new Object[0]);
            this.executor.onPause();
        }
        super.onPause();
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (this.executor != null) {
            this.executor.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        if (this.executor != null) {
            MobLog.getInstance().d(this.executor.getClass().getSimpleName() + " onRestart", new Object[0]);
            this.executor.onRestart();
        }
        super.onRestart();
    }

    @Override // android.app.Activity
    protected void onResume() {
        if (this.executor != null) {
            MobLog.getInstance().d(this.executor.getClass().getSimpleName() + " onResume", new Object[0]);
            this.executor.onResume();
        }
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onStart() {
        if (this.executor != null) {
            MobLog.getInstance().d(this.executor.getClass().getSimpleName() + " onStart", new Object[0]);
            this.executor.onStart();
        }
        super.onStart();
    }

    @Override // android.app.Activity
    protected void onStop() {
        if (this.executor != null) {
            MobLog.getInstance().d(this.executor.getClass().getSimpleName() + " onStop", new Object[0]);
            this.executor.onStop();
        }
        super.onStop();
    }

    @Override // android.app.Activity
    public void setContentView(int layoutResID) {
        setContentView(LayoutInflater.from(this).inflate(layoutResID, (ViewGroup) null));
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        if (view != null) {
            super.setContentView(view);
            if (this.executor != null) {
                this.executor.setContentView(view);
            }
        }
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (view != null) {
            if (params == null) {
                super.setContentView(view);
            } else {
                super.setContentView(view, params);
            }
            if (this.executor != null) {
                this.executor.setContentView(view);
            }
        }
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int resid) {
        if (forceTheme > 0) {
            super.setTheme(forceTheme);
        } else {
            super.setTheme(resid);
        }
    }
}

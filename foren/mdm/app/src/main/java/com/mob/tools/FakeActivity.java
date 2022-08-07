package com.mob.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.mob.tools.utils.R;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.UIHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class FakeActivity {
    private static Class<? extends Activity> shellClass;
    protected Activity activity;
    private View contentView;
    private HashMap<String, Object> result;
    private FakeActivity resultReceiver;

    public static void registerExecutor(String scheme, Object executor) {
        if (shellClass != null) {
            try {
                Method registerExecutor = shellClass.getMethod("registerExecutor", String.class, Object.class);
                registerExecutor.setAccessible(true);
                registerExecutor.invoke(null, scheme, executor);
            } catch (Throwable t) {
                MobLog.getInstance().w(t);
            }
        } else {
            MobUIShell.registerExecutor(scheme, executor);
        }
    }

    public static void setShell(Class<? extends Activity> shellClass2) {
        shellClass = shellClass2;
    }

    public <T extends View> T findViewById(int id) {
        if (this.activity == null) {
            return null;
        }
        return (T) this.activity.findViewById(id);
    }

    public <T extends View> T findViewByResName(View view, String name) {
        int resId;
        if (this.activity != null && (resId = R.getIdRes(this.activity, name)) > 0) {
            return (T) view.findViewById(resId);
        }
        return null;
    }

    public <T extends View> T findViewByResName(String name) {
        int resId;
        if (this.activity != null && (resId = R.getIdRes(this.activity, name)) > 0) {
            return (T) findViewById(resId);
        }
        return null;
    }

    public final void finish() {
        if (this.activity != null) {
            this.activity.finish();
        }
    }

    public View getContentView() {
        return this.contentView;
    }

    public Context getContext() {
        return this.activity;
    }

    public int getOrientation() {
        return this.activity.getResources().getConfiguration().orientation;
    }

    public FakeActivity getParent() {
        return this.resultReceiver;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public void onConfigurationChanged(Configuration newConfig) {
    }

    public void onCreate() {
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void onDestroy() {
    }

    public boolean onFinish() {
        return false;
    }

    public boolean onKeyEvent(int keyCode, KeyEvent event) {
        return false;
    }

    public void onNewIntent(Intent intent) {
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    public void onPause() {
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    }

    public void onRestart() {
    }

    public void onResult(HashMap<String, Object> data) {
    }

    public void onResume() {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void requestFullScreen(boolean fullScreen) {
        if (this.activity != null) {
            if (fullScreen) {
                this.activity.getWindow().addFlags(1024);
                this.activity.getWindow().clearFlags(2048);
            } else {
                this.activity.getWindow().addFlags(2048);
                this.activity.getWindow().clearFlags(1024);
            }
            this.activity.getWindow().getDecorView().requestLayout();
        }
    }

    public void requestLandscapeOrientation() {
        setRequestedOrientation(0);
    }

    public void requestPermissions(String[] permissions, int requestCode) {
        if (this.activity != null && Build.VERSION.SDK_INT >= 23) {
            try {
                ReflectHelper.invokeInstanceMethod(this.activity, "requestPermissions", permissions, Integer.valueOf(requestCode));
            } catch (Throwable t) {
                MobLog.getInstance().d(t);
            }
        }
    }

    public void requestPortraitOrientation() {
        setRequestedOrientation(1);
    }

    public void runOnUIThread(final Runnable r) {
        UIHandler.sendEmptyMessage(0, new Handler.Callback() { // from class: com.mob.tools.FakeActivity.2
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message msg) {
                r.run();
                return false;
            }
        });
    }

    public void runOnUIThread(final Runnable r, long delayMillis) {
        UIHandler.sendEmptyMessageDelayed(0, delayMillis, new Handler.Callback() { // from class: com.mob.tools.FakeActivity.3
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message msg) {
                r.run();
                return false;
            }
        });
    }

    public void sendResult() {
        if (this.resultReceiver != null) {
            this.resultReceiver.onResult(this.result);
        }
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setContentView(View view) {
        this.contentView = view;
    }

    public void setContentViewLayoutResName(String name) {
        int resId;
        if (this.activity != null && (resId = R.getLayoutRes(this.activity, name)) > 0) {
            this.activity.setContentView(resId);
        }
    }

    public void setRequestedOrientation(int requestedOrientation) {
        if (this.activity != null) {
            this.activity.setRequestedOrientation(requestedOrientation);
        }
    }

    public final void setResult(HashMap<String, Object> result) {
        this.result = result;
    }

    public void show(Context context, Intent i) {
        showForResult(context, i, null);
    }

    public void showForResult(Context context, Intent i, FakeActivity resultReceiver) {
        Intent iExec;
        this.resultReceiver = resultReceiver;
        Message msg = new Message();
        String launchTime = null;
        if (shellClass != null) {
            iExec = new Intent(context, shellClass);
            try {
                Method registerExecutor = shellClass.getMethod("registerExecutor", Object.class);
                registerExecutor.setAccessible(true);
                launchTime = (String) registerExecutor.invoke(null, this);
            } catch (Throwable t) {
                MobLog.getInstance().w(t);
            }
        } else {
            iExec = new Intent(context, MobUIShell.class);
            launchTime = MobUIShell.registerExecutor(this);
        }
        iExec.putExtra("launch_time", launchTime);
        iExec.putExtra("executor_name", getClass().getName());
        if (i != null) {
            iExec.putExtras(i);
        }
        msg.obj = new Object[]{context, iExec};
        UIHandler.sendMessage(msg, new Handler.Callback() { // from class: com.mob.tools.FakeActivity.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message msg2) {
                Object[] objs = (Object[]) msg2.obj;
                Context cxt = (Context) objs[0];
                Intent i2 = (Intent) objs[1];
                if (!(cxt instanceof Activity)) {
                    i2.addFlags(268435456);
                }
                cxt.startActivity(i2);
                return false;
            }
        });
    }

    public void startActivity(Intent intent) {
        startActivityForResult(intent, -1);
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        if (this.activity != null) {
            this.activity.startActivityForResult(intent, requestCode);
        }
    }
}

package com.hy.frame.common;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyLog;
import com.tencent.open.SocialConstants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class BaseApplication extends Application {
    private static BaseApplication instance;
    private List<Activity> acts;
    private HashMap<String, Object> hashMap;
    private BroadcastReceiver receiver = new BroadcastReceiver() { // from class: com.hy.frame.common.BaseApplication.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            MyLog.d(getClass(), "action:" + action);
            if (TextUtils.equals(action, "android.net.conn.CONNECTIVITY_CHANGE")) {
                int result = -1;
                NetworkInfo netInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnected()) {
                    result = netInfo.getType();
                }
                MyLog.e(getClass(), "NetState:" + result);
                new AppShare(BaseApplication.this.getApplicationContext()).putInt(Constant.NET_STATUS, result);
                BaseApplication.this.sendBroadcast(new Intent(Constant.ACTION_RECEIVE_NET_CHANGE));
            }
        }
    };
    private Activity topAct;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        MyLog.d(getClass(), "Application start!");
        instance = this;
        this.acts = new CopyOnWriteArrayList();
        this.hashMap = new HashMap<>();
        initNetListener();
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.hy.frame.common.BaseApplication.2
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                MyLog.d(SocialConstants.PARAM_ACT, activity + ".onActivityStarted");
                BaseApplication.this.topAct = activity;
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
            }
        });
    }

    private void initNetListener() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.setPriority(NotificationManagerCompat.IMPORTANCE_UNSPECIFIED);
        registerReceiver(this.receiver, filter);
        this.receiver.onReceive(this, new Intent("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        if (this.receiver != null) {
            unregisterReceiver(this.receiver);
        }
        MyLog.d(getClass(), "Application closed!");
    }

    public void addActivity(Activity activity) {
        remove(activity);
        this.acts.add(activity);
    }

    public Activity getTopActivity() {
        return this.topAct;
    }

    public void exit() {
        clear();
        System.exit(0);
    }

    public void toDesktop() {
        Intent home = new Intent("android.intent.action.MAIN");
        home.setFlags(268435456);
        home.addCategory("android.intent.category.HOME");
        startActivity(home);
    }

    public void remove(Activity activity) {
        if (this.acts != null && !this.acts.isEmpty()) {
            this.acts.remove(activity);
        }
    }

    public void removeFinish(Class cls) {
        if (this.acts != null && !this.acts.isEmpty()) {
            Activity act = null;
            Iterator<Activity> it = this.acts.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Activity item = it.next();
                if (TextUtils.equals(item.getClass().getName(), cls.getName())) {
                    act = item;
                    break;
                }
            }
            if (act != null) {
                act.finish();
                this.acts.remove(act);
            }
        }
    }

    public void clear() {
        if (!(this.acts == null || this.acts.isEmpty())) {
            for (Activity activity : this.acts) {
                activity.finish();
            }
            this.acts.clear();
        }
    }

    public void putValue(String key, Object value) {
        if (this.hashMap == null) {
            this.hashMap = new HashMap<>();
        }
        this.hashMap.put(key, value);
    }

    public Object getValue(String key) {
        if (this.hashMap != null) {
            return this.hashMap.get(key);
        }
        return null;
    }
}

package com.cdlinglu.common;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Looper;
import android.os.Process;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;
import com.hy.frame.util.MyLog;
import com.vsf2f.f2f.ui.LaunchActivity;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import java.lang.Thread;

/* loaded from: classes.dex */
public class UnCeHandler implements Thread.UncaughtExceptionHandler {
    MyApplication application;
    private Thread.UncaughtExceptionHandler mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

    public UnCeHandler(MyApplication application) {
        this.application = application;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable ex) {
        if (handleException(ex) || this.mDefaultHandler == null) {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ((AlarmManager) this.application.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(1, System.currentTimeMillis() + 1000, PendingIntent.getActivity(this.application.getApplicationContext(), 0, new Intent(this.application.getApplicationContext(), LaunchActivity.class), 268435456));
            Process.killProcess(Process.myPid());
            return;
        }
        this.mDefaultHandler.uncaughtException(thread, ex);
    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        for (StackTraceElement a : ex.getStackTrace()) {
            MyLog.e(a);
        }
        ThreadPool.newThreadPool(new Runnable() { // from class: com.cdlinglu.common.UnCeHandler.1
            @Override // java.lang.Runnable
            public void run() {
                Looper.prepare();
                Toast.makeText(UnCeHandler.this.application.getApplicationContext(), "很抱歉,程序出现异常,即将退出.", 0).show();
                Looper.loop();
            }
        });
        return true;
    }
}

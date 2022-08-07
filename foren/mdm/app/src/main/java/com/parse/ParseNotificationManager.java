package com.parse;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
class ParseNotificationManager {
    public static final String TAG = "com.parse.ParseNotificationManager";
    private final AtomicInteger notificationCount = new AtomicInteger(0);
    private volatile boolean shouldShowNotifications = true;

    ParseNotificationManager() {
    }

    /* loaded from: classes2.dex */
    public static class Singleton {
        private static final ParseNotificationManager INSTANCE = new ParseNotificationManager();
    }

    public static ParseNotificationManager getInstance() {
        return Singleton.INSTANCE;
    }

    public void setShouldShowNotifications(boolean show) {
        this.shouldShowNotifications = show;
    }

    public int getNotificationCount() {
        return this.notificationCount.get();
    }

    public void showNotification(Context context, Notification notification) {
        if (context != null && notification != null) {
            this.notificationCount.incrementAndGet();
            if (this.shouldShowNotifications) {
                NotificationManager nm = (NotificationManager) context.getSystemService("notification");
                int notificationId = (int) System.currentTimeMillis();
                try {
                    nm.notify(notificationId, notification);
                } catch (SecurityException e) {
                    notification.defaults = 5;
                    nm.notify(notificationId, notification);
                }
            }
        }
    }
}

package com.umeng.update.util;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.widget.RemoteViews;
import java.lang.reflect.Field;

/* compiled from: NotificationBuilder.java */
/* loaded from: classes2.dex */
public class b {
    protected Context b;
    protected Notification c = new Notification();
    protected Notification.Builder d;

    public b(Context context) {
        this.b = context.getApplicationContext();
        if (Build.VERSION.SDK_INT >= 14) {
            this.d = new Notification.Builder(context);
        }
    }

    public void e() {
        if (Build.VERSION.SDK_INT >= 16) {
            try {
                Field declaredField = Notification.Builder.class.getDeclaredField("mActions");
                declaredField.setAccessible(true);
                declaredField.set(this.d, declaredField.get(this.d).getClass().newInstance());
            } catch (Exception e) {
            }
        }
    }

    public b b(RemoteViews remoteViews) {
        if (Build.VERSION.SDK_INT < 16 && Build.VERSION.SDK_INT >= 14) {
            this.d.setContent(remoteViews);
        }
        this.c.contentView = remoteViews;
        return this;
    }

    public b a(PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= 14) {
            this.d.setContentIntent(pendingIntent);
        }
        this.c.contentIntent = pendingIntent;
        return this;
    }

    public b a(boolean z) {
        if (Build.VERSION.SDK_INT >= 14) {
            this.d.setOngoing(z);
        }
        if (z) {
            this.c.flags |= 2;
        } else {
            this.c.flags &= -3;
        }
        return this;
    }

    public b b(boolean z) {
        if (Build.VERSION.SDK_INT >= 14) {
            this.d.setAutoCancel(z);
        }
        if (z) {
            this.c.flags |= 16;
        } else {
            this.c.flags &= -17;
        }
        return this;
    }

    public b a(int i) {
        if (Build.VERSION.SDK_INT >= 14) {
            this.d.setSmallIcon(i);
        }
        this.c.icon = i;
        return this;
    }

    public b d(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 14) {
            this.d.setTicker(charSequence);
        }
        this.c.tickerText = charSequence;
        return this;
    }

    public b a(long j) {
        if (Build.VERSION.SDK_INT >= 14) {
            this.d.setWhen(j);
        }
        this.c.when = j;
        return this;
    }
}

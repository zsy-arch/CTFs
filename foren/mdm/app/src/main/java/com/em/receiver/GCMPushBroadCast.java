package com.em.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/* loaded from: classes.dex */
public class GCMPushBroadCast extends BroadcastReceiver {
    protected NotificationManager notificationManager = null;
    protected static int notifyID = 341;
    protected static int foregroundNotifyID = 365;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.i("info", "gcmpush onreceive");
        sendNotification(context, intent.getStringExtra("alert"), true);
    }

    public void sendNotification(Context context, String message, boolean isForeground) {
        if (this.notificationManager == null) {
            this.notificationManager = (NotificationManager) context.getSystemService("notification");
        }
        try {
            String packageName = context.getApplicationInfo().packageName;
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context).setSmallIcon(context.getApplicationInfo().icon).setSound(RingtoneManager.getDefaultUri(2)).setWhen(System.currentTimeMillis()).setAutoCancel(true);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, notifyID, context.getPackageManager().getLaunchIntentForPackage(packageName), 134217728);
            mBuilder.setContentTitle((String) context.getPackageManager().getApplicationLabel(context.getApplicationInfo()));
            mBuilder.setTicker(message);
            mBuilder.setContentText(message);
            mBuilder.setContentIntent(pendingIntent);
            this.notificationManager.notify(notifyID, mBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

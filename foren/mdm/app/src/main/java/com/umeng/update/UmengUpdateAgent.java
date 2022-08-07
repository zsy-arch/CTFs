package com.umeng.update;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;
import com.umeng.update.net.j;
import java.io.File;
import java.io.IOException;
import u.upd.b;
import u.upd.l;
import u.upd.n;

/* loaded from: classes2.dex */
public class UmengUpdateAgent {
    private static Context d;
    private static Handler f;
    private static UmengUpdateListener a = null;
    private static UmengDialogButtonListener b = null;
    private static UmengDownloadListener c = null;
    private static c e = new c();
    private static boolean g = false;
    private static String h = UmengUpdateAgent.class.getName();

    public static void setDefault() {
        setDeltaUpdate(true);
        setUpdateAutoPopup(true);
        setUpdateOnlyWifi(true);
        setRichNotification(true);
        setDialogListener(null);
        setDownloadListener(null);
        setUpdateListener(null);
        setAppkey(null);
        setChannel(null);
        setUpdateUIStyle(0);
    }

    private static boolean c() {
        Class<?> cls;
        try {
            cls = Class.forName("com.umeng.message.PushAgent");
        } catch (ClassNotFoundException e2) {
            b.e(h, "isIncludesUmengPushSDK", e2);
            cls = null;
        }
        return cls != null;
    }

    public static void setUpdateFromPushMessage(boolean z) {
        g = z;
    }

    public static boolean getUpdateFromPushMessage() {
        return g;
    }

    public static void setUpdateCheckConfig(boolean z) {
        UpdateConfig.setUpdateCheck(z);
    }

    public static void setUpdateOnlyWifi(boolean z) {
        UpdateConfig.setUpdateOnlyWifi(z);
    }

    public static void setUpdateAutoPopup(boolean z) {
        UpdateConfig.setUpdateAutoPopup(z);
    }

    public static void setUpdateUIStyle(int i) {
        UpdateConfig.setStyle(i);
    }

    public static void setDeltaUpdate(boolean z) {
        UpdateConfig.setDeltaUpdate(z);
    }

    public static void setAppkey(String str) {
        UpdateConfig.setAppkey(str);
    }

    public static void setSlotId(String str) {
        UpdateConfig.setSlotId(str);
    }

    public static void setChannel(String str) {
        UpdateConfig.setChannel(str);
    }

    public static void setRichNotification(boolean z) {
        UpdateConfig.setRichNotification(z);
    }

    public static void setUpdateListener(UmengUpdateListener umengUpdateListener) {
        a = umengUpdateListener;
    }

    public static void setDialogListener(UmengDialogButtonListener umengDialogButtonListener) {
        b = umengDialogButtonListener;
    }

    public static void setDownloadListener(UmengDownloadListener umengDownloadListener) {
        c = umengDownloadListener;
    }

    public static void b(int i, UpdateResponse updateResponse) {
        Message message = new Message();
        message.what = i;
        message.obj = updateResponse;
        f.sendMessage(message);
    }

    public static void silentUpdate(Context context) {
        UpdateConfig.setUpdateForce(false);
        UpdateConfig.setSilentDownload(true);
        b(context.getApplicationContext());
    }

    public static void forceUpdate(Context context) {
        UpdateConfig.setUpdateForce(true);
        UpdateConfig.setSilentDownload(false);
        b(context.getApplicationContext());
    }

    public static void update(Context context) {
        UpdateConfig.setUpdateForce(false);
        UpdateConfig.setSilentDownload(false);
        b(context.getApplicationContext());
    }

    public static void update(Context context, String str, String str2) {
        UpdateConfig.setAppkey(str);
        UpdateConfig.setChannel(str2);
        update(context);
    }

    private static void b(final Context context) {
        try {
            if (context == null) {
                b.b(UpdateConfig.a, "unexpected null context in update");
            } else {
                f = new Handler(context.getMainLooper()) { // from class: com.umeng.update.UmengUpdateAgent.1
                    @Override // android.os.Handler
                    public void handleMessage(Message message) {
                        super.handleMessage(message);
                        if (message.what == 0 && UpdateConfig.isUpdateAutoPopup()) {
                            UmengUpdateAgent.b(UmengUpdateAgent.d, (UpdateResponse) message.obj, UpdateConfig.getStyle());
                        }
                        UmengUpdateAgent.b(context, message);
                        Context unused = UmengUpdateAgent.d = null;
                        if (UmengUpdateAgent.a != null) {
                            UmengUpdateAgent.a.onUpdateReturned(message.what, (UpdateResponse) message.obj);
                        }
                    }
                };
                c(context);
                if (!u.upd.a.k(context)) {
                    if (UpdateConfig.isSilentDownload()) {
                        b(2, (UpdateResponse) null);
                    } else if (UpdateConfig.isUpdateOnlyWifi() && !UpdateConfig.isUpdateForce()) {
                        b(2, (UpdateResponse) null);
                    }
                }
                if (e.a()) {
                    b(4, (UpdateResponse) null);
                    b.a(UpdateConfig.a, "Is updating now.");
                    f.post(new Runnable() { // from class: com.umeng.update.UmengUpdateAgent.2
                        @Override // java.lang.Runnable
                        public void run() {
                            Toast.makeText(context, l.b(context), 0).show();
                        }
                    });
                } else {
                    d = context;
                    new Thread(new a(context.getApplicationContext())).start();
                }
            }
        } catch (Exception e2) {
            b.b(UpdateConfig.a, "Exception occurred in Mobclick.update(). ", e2);
        }
    }

    public static void b(Context context, UpdateResponse updateResponse, int i) {
        try {
            if (!isIgnore(context, updateResponse)) {
                File downloadedFile = downloadedFile(context, updateResponse);
                boolean z = downloadedFile != null;
                if (z || !UpdateConfig.isSilentDownload()) {
                    switch (i) {
                        case 0:
                            e.a(context, updateResponse, z, downloadedFile);
                            break;
                        case 1:
                            ((NotificationManager) context.getSystemService("notification")).notify(0, e.b(context, updateResponse, z, downloadedFile).a());
                            break;
                    }
                } else {
                    startDownload(context, updateResponse);
                }
            }
        } catch (Exception e2) {
            b.b(UpdateConfig.a, "Fail to create update dialog box.", e2);
        }
    }

    public static void showUpdateDialog(Context context, UpdateResponse updateResponse) {
        b(context, updateResponse, 0);
    }

    public static void showUpdateNotification(Context context, UpdateResponse updateResponse) {
        b(context, updateResponse, 1);
    }

    public static File downloadedFile(Context context, UpdateResponse updateResponse) {
        try {
            File file = new File(j.a("/apk", context, new boolean[1]), updateResponse.new_md5 + ".apk");
            if (file.exists()) {
                if (updateResponse.new_md5.equalsIgnoreCase(n.a(file))) {
                    return file;
                }
            }
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean isIgnore(Context context, UpdateResponse updateResponse) {
        return updateResponse.new_md5 != null && updateResponse.new_md5.equalsIgnoreCase(UpdateConfig.getIgnoreMd5(context)) && !UpdateConfig.isUpdateForce();
    }

    public static void ignoreUpdate(Context context, UpdateResponse updateResponse) {
        UpdateConfig.saveIgnoreMd5(context, updateResponse.new_md5);
    }

    public static void a(int i, Context context, UpdateResponse updateResponse, File file) {
        switch (i) {
            case 5:
                a(context, updateResponse, file);
                break;
            case 7:
                ignoreUpdate(context, updateResponse);
                break;
        }
        if (b != null) {
            b.onClick(i);
        }
    }

    private static void a(Context context, UpdateResponse updateResponse, File file) {
        if (file == null) {
            startDownload(context, updateResponse);
        } else {
            startInstall(context, file);
        }
    }

    public static void startInstall(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static void startDownload(Context context, UpdateResponse updateResponse) {
        if (!updateResponse.delta || !UpdateConfig.isDeltaUpdate()) {
            e.a(context, updateResponse.path, updateResponse.new_md5, null, null, c);
            e.c();
            return;
        }
        e.a(context, updateResponse.origin, updateResponse.new_md5, updateResponse.path, updateResponse.patch_md5, c);
        e.b();
    }

    private static boolean c(final Context context) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        if (!UpdateConfig.isUpdateCheck()) {
            return true;
        }
        try {
            if (!Class.forName(context.getPackageName() + ".BuildConfig").getField("DEBUG").getBoolean(null)) {
                return true;
            }
            try {
            } catch (Exception e2) {
                z = false;
            }
            if (UpdateConfig.getAppkey(context) == null) {
                f.post(new Runnable() { // from class: com.umeng.update.UmengUpdateAgent.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please set umeng appkey!", 1).show();
                    }
                });
                return false;
            }
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4101);
            if (packageInfo.activities != null) {
                z = false;
                for (int i = 0; i < packageInfo.activities.length; i++) {
                    try {
                        if (UpdateConfig.e.equals(packageInfo.activities[i].name)) {
                            z = true;
                        }
                    } catch (Exception e3) {
                    }
                }
            } else {
                z = false;
            }
            if (!z) {
                f.post(new Runnable() { // from class: com.umeng.update.UmengUpdateAgent.4
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add Activity in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            if (packageInfo.services != null) {
                z2 = false;
                for (int i2 = 0; i2 < packageInfo.services.length; i2++) {
                    if (UpdateConfig.d.equals(packageInfo.services[i2].name)) {
                        z2 = true;
                    }
                }
            } else {
                z2 = false;
            }
            if (!z2) {
                f.post(new Runnable() { // from class: com.umeng.update.UmengUpdateAgent.5
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add Service in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            if (packageInfo.requestedPermissions != null) {
                z5 = false;
                z4 = false;
                z3 = false;
                for (int i3 = 0; i3 < packageInfo.requestedPermissions.length; i3++) {
                    if (UpdateConfig.f.equals(packageInfo.requestedPermissions[i3])) {
                        z3 = true;
                    } else if (UpdateConfig.g.equals(packageInfo.requestedPermissions[i3])) {
                        z4 = true;
                    } else if (UpdateConfig.h.equals(packageInfo.requestedPermissions[i3])) {
                        z5 = true;
                    }
                }
            } else {
                z5 = false;
                z4 = false;
                z3 = false;
            }
            if (!(z3 && z4 && z5)) {
                f.post(new Runnable() { // from class: com.umeng.update.UmengUpdateAgent.6
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "Please add Permission in AndroidManifest!", 1).show();
                    }
                });
                return false;
            }
            try {
                z = UpdateConfig.b.equals(context.getString(Class.forName(new StringBuilder().append(context.getPackageName()).append(".R$string").toString()).getField(UpdateConfig.i).getInt(null)));
                if (z) {
                    return true;
                }
            } catch (Exception e4) {
                z = false;
            }
            f.post(new Runnable() { // from class: com.umeng.update.UmengUpdateAgent.7
                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(context, "Please copy all resources (res/) from SDK to your project!", 1).show();
                }
            });
            return z;
        } catch (Exception e5) {
            return true;
        }
    }

    public static void b(Context context, Message message) {
        if (c() && getUpdateFromPushMessage()) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt("UpdateStatus", message.what);
            bundle.putSerializable("UpdateResponse", (UpdateResponse) message.obj);
            intent.putExtra("UpdateListener", bundle);
            intent.setAction("com.umeng.message.action.autoupdate");
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            setUpdateFromPushMessage(false);
            b.c("UmengUpdateAgent", "UpdateFromPushMessage");
        }
    }

    /* loaded from: classes2.dex */
    public static class a implements Runnable {
        Context a;

        public a(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                UpdateResponse a = new b(this.a).a();
                if (a == null) {
                    UmengUpdateAgent.b(3, (UpdateResponse) null);
                } else if (!a.hasUpdate) {
                    UmengUpdateAgent.b(1, a);
                } else {
                    UmengUpdateAgent.b(0, a);
                }
            } catch (Error e) {
                b.a(UpdateConfig.a, "request update error" + e.getMessage());
            } catch (Exception e2) {
                UmengUpdateAgent.b(1, (UpdateResponse) null);
                b.a(UpdateConfig.a, "request update error", e2);
            }
        }
    }
}

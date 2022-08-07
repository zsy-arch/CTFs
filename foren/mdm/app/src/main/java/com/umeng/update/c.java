package com.umeng.update;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.umeng.update.net.d;
import com.umeng.update.util.b;
import java.io.File;

/* compiled from: UpdateInternal.java */
/* loaded from: classes2.dex */
public class c implements d {
    private UmengDownloadListener c = null;
    private String d = null;
    private String e = null;
    private String f = null;
    private String g = null;
    private Context h = null;
    private String i = null;
    private com.umeng.update.net.a j = null;
    private boolean k = false;
    private final String a = "delta_update";
    private final String b = "update_normal";

    public void a(Context context, String str, String str2, String str3, String str4, UmengDownloadListener umengDownloadListener) {
        this.h = context;
        this.i = u.upd.a.v(context);
        this.d = str;
        this.e = str2;
        this.f = str3;
        this.g = str4;
        this.c = umengDownloadListener;
    }

    public boolean a() {
        return this.k;
    }

    public void b() {
        this.j = new com.umeng.update.net.a(this.h, this.a, this.i, this.f, this);
        this.j.a(this.g);
        this.j.b(this.e);
        a(this.j);
    }

    public void c() {
        this.j = new com.umeng.update.net.a(this.h, this.b, this.i, this.d, this);
        this.j.a(this.e);
        this.j.b(this.e);
        a(this.j);
    }

    private void a(com.umeng.update.net.a aVar) {
        int i = 0;
        try {
            i = this.h.getPackageManager().getPackageInfo(this.h.getPackageName(), 0).applicationInfo.targetSdkVersion;
        } catch (Exception e) {
        }
        if (Build.VERSION.SDK_INT >= 16 && i >= 14 && UpdateConfig.isRichNotification() && !UpdateConfig.isSilentDownload()) {
            aVar.a(true);
        }
        aVar.b(UpdateConfig.isSilentDownload());
        aVar.c(UpdateConfig.isSilentDownload());
        aVar.a();
    }

    @Override // com.umeng.update.net.d
    public void onStart() {
        this.k = true;
        if (this.c != null) {
            this.c.OnDownloadStart();
        }
    }

    @Override // com.umeng.update.net.d
    public void onProgressUpdate(int i) {
        if (this.c != null) {
            this.c.OnDownloadUpdate(i);
        }
    }

    @Override // com.umeng.update.net.d
    public void onEnd(int i, int i2, String str) {
        switch (i) {
            case 3:
                c();
                break;
        }
        this.k = false;
        if (this.c != null) {
            this.c.OnDownloadEnd(i, str);
        }
    }

    @Override // com.umeng.update.net.d
    public void onStatus(int i) {
        switch (i) {
            case 2:
            case 7:
                this.k = true;
                return;
            case 3:
            case 4:
            case 5:
            default:
                return;
            case 6:
                this.k = false;
                return;
        }
    }

    public void a(Context context, UpdateResponse updateResponse, boolean z, File file) {
        Intent intent = new Intent(context, UpdateDialogActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("response", updateResponse);
        if (z) {
            bundle.putString("file", file.getAbsolutePath());
        } else {
            bundle.putString("file", null);
        }
        bundle.putBoolean("force", UpdateConfig.isUpdateForce());
        intent.putExtras(bundle);
        intent.addFlags(335544320);
        context.startActivity(intent);
    }

    /* compiled from: UpdateInternal.java */
    /* loaded from: classes2.dex */
    class a extends b {
        private String e = "";
        private String f = "";

        public a(Context context) {
            super(context);
        }

        public a a(CharSequence charSequence) {
            if (Build.VERSION.SDK_INT >= 14) {
                this.d.setContentText(charSequence);
            }
            this.f = charSequence.toString();
            return this;
        }

        public a b(CharSequence charSequence) {
            if (Build.VERSION.SDK_INT >= 14) {
                this.d.setContentTitle(charSequence);
            }
            this.e = charSequence.toString();
            return this;
        }

        public a c(CharSequence charSequence) {
            if (Build.VERSION.SDK_INT >= 16) {
                this.d.setStyle(new Notification.BigTextStyle().bigText(charSequence));
            }
            return this;
        }

        public Notification a() {
            if (Build.VERSION.SDK_INT >= 16) {
                return this.d.build();
            }
            if (Build.VERSION.SDK_INT >= 14) {
                return this.d.getNotification();
            }
            this.c.setLatestEventInfo(this.b, this.e, this.f, this.c.contentIntent);
            return this.c;
        }
    }

    public a b(Context context, UpdateResponse updateResponse, boolean z, File file) {
        String string;
        Intent intent;
        String v = u.upd.a.v(context);
        String a2 = updateResponse.a(context, z);
        if (z) {
            string = context.getString(u.upd.c.a(context).f("UMDialog_InstallAPK"));
            intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        } else {
            string = context.getString(u.upd.c.a(context).f("UMUpdateTitle"));
            intent = new Intent(context, UpdateDialogActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("response", updateResponse);
            bundle.putString("file", null);
            bundle.putBoolean("force", UpdateConfig.isUpdateForce());
            intent.putExtras(bundle);
            intent.addFlags(335544320);
        }
        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 134217728);
        a aVar = new a(context);
        aVar.c(a2).b(v).a(string).d(v + string).a(activity).a(17301629).b(true);
        return aVar;
    }
}

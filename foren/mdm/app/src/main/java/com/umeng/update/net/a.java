package com.umeng.update.net;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.hyphenate.chat.MessageEncoder;

/* compiled from: DownloadAgent.java */
/* loaded from: classes2.dex */
public class a {
    private static final String b = a.class.getName();
    private Context c;
    private d d;
    private Messenger e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String[] k;
    private boolean l = false;
    private boolean m = false;
    private boolean n = false;
    final Messenger a = new Messenger(new b());
    private ServiceConnection o = new ServiceConnection() { // from class: com.umeng.update.net.a.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            u.upd.b.c(a.b, "ServiceConnection.onServiceConnected");
            a.this.e = new Messenger(iBinder);
            try {
                Message obtain = Message.obtain((Handler) null, 4);
                C0091a aVar = new C0091a(a.this.f, a.this.g, a.this.h);
                aVar.d = a.this.i;
                aVar.e = a.this.j;
                aVar.f = a.this.k;
                aVar.g = a.this.l;
                aVar.h = a.this.m;
                aVar.i = a.this.n;
                obtain.setData(aVar.a());
                obtain.replyTo = a.this.a;
                a.this.e.send(obtain);
            } catch (RemoteException e) {
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            u.upd.b.c(a.b, "ServiceConnection.onServiceDisconnected");
            a.this.e = null;
        }
    };

    public void a(String str) {
        this.i = str;
    }

    public void b(String str) {
        this.j = str;
    }

    public void a(String[] strArr) {
        this.k = strArr;
    }

    public void a(boolean z) {
        this.l = z;
    }

    public void b(boolean z) {
        this.m = z;
    }

    public void c(boolean z) {
        this.n = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DownloadAgent.java */
    /* loaded from: classes2.dex */
    public class b extends Handler {
        b() {
            a.this = r1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                u.upd.b.c(a.b, "DownloadAgent.handleMessage(" + message.what + "): ");
                switch (message.what) {
                    case 1:
                        if (a.this.d != null) {
                            a.this.d.onStart();
                            break;
                        }
                        break;
                    case 2:
                        a.this.d.onStatus(message.arg1);
                        break;
                    case 3:
                        if (a.this.d != null) {
                            a.this.d.onProgressUpdate(message.arg1);
                            break;
                        }
                        break;
                    case 4:
                    default:
                        super.handleMessage(message);
                        break;
                    case 5:
                        a.this.c.unbindService(a.this.o);
                        if (a.this.d != null) {
                            if (message.arg1 != 1 && message.arg1 != 3 && message.arg1 != 5) {
                                a.this.d.onEnd(0, 0, null);
                                u.upd.b.c(a.b, "DownloadAgent.handleMessage(DownloadingService.DOWNLOAD_COMPLETE_FAIL): ");
                                break;
                            } else {
                                a.this.d.onEnd(message.arg1, message.arg2, message.getData().getString(MessageEncoder.ATTR_FILENAME));
                                break;
                            }
                        }
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                u.upd.b.c(a.b, "DownloadAgent.handleMessage(" + message.what + "): " + e.getMessage());
            }
        }
    }

    public a(Context context, String str, String str2, String str3, d dVar) {
        this.c = context.getApplicationContext();
        this.f = str;
        this.g = str2;
        this.h = str3;
        this.d = dVar;
    }

    public void a() {
        this.c.bindService(new Intent(this.c, DownloadingService.class), this.o, 1);
        this.c.startService(new Intent(this.c, DownloadingService.class));
    }

    /* compiled from: DownloadAgent.java */
    /* renamed from: com.umeng.update.net.a$a */
    /* loaded from: classes2.dex */
    public static class C0091a {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String[] f = null;
        public boolean g = false;
        public boolean h = false;
        public boolean i = false;

        public C0091a(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.c = str3;
        }

        public Bundle a() {
            Bundle bundle = new Bundle();
            bundle.putString("mComponentName", this.a);
            bundle.putString("mTitle", this.b);
            bundle.putString("mUrl", this.c);
            bundle.putString("mMd5", this.d);
            bundle.putString("mTargetMd5", this.e);
            bundle.putStringArray("reporturls", this.f);
            bundle.putBoolean("rich_notification", this.g);
            bundle.putBoolean("mSilent", this.h);
            bundle.putBoolean("mWifiOnly", this.i);
            return bundle;
        }

        public static C0091a a(Bundle bundle) {
            C0091a aVar = new C0091a(bundle.getString("mComponentName"), bundle.getString("mTitle"), bundle.getString("mUrl"));
            aVar.d = bundle.getString("mMd5");
            aVar.e = bundle.getString("mTargetMd5");
            aVar.f = bundle.getStringArray("reporturls");
            aVar.g = bundle.getBoolean("rich_notification");
            aVar.h = bundle.getBoolean("mSilent");
            aVar.i = bundle.getBoolean("mWifiOnly");
            return aVar;
        }
    }
}

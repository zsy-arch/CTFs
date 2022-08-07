package com.hyphenate.chat.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.EasyUtils;
import java.io.File;

/* loaded from: classes2.dex */
public class a {
    private static final String a = "EMAdvanceDebugManager";
    private static String b = null;
    private static a c = null;
    private BroadcastReceiver d = null;
    private b f = null;
    private Context e = EMClient.getInstance().getContext();

    /* renamed from: com.hyphenate.chat.a.a$a */
    /* loaded from: classes2.dex */
    public enum EnumC0042a {
        em_retrieve_dns,
        em_upload_dns,
        em_start_debug,
        em_stop_debug,
        em_upload_log,
        em_print_user,
        em_change_appkey,
        em_change_servers,
        em_dump_db
    }

    private a() {
        b = this.e.getPackageName() + ".debug.ipc.cmd";
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (c == null) {
                c = new a();
            }
            aVar = c;
        }
        return aVar;
    }

    private void h() {
        if (this.d == null) {
            this.d = new BroadcastReceiver() { // from class: com.hyphenate.chat.a.a.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().equals(a.b)) {
                        EnumC0042a aVar = null;
                        try {
                            aVar = EnumC0042a.valueOf(intent.getStringExtra("action"));
                        } catch (Exception e) {
                        }
                        if (aVar == null) {
                            EMLog.e(a.a, "unknow cmd action");
                            return;
                        }
                        EMMessage createReceiveMessage = EMMessage.createReceiveMessage(EMMessage.Type.CMD);
                        if (intent.getStringExtra("appkey") != null) {
                            createReceiveMessage.setAttribute("appkey", intent.getStringExtra("appkey"));
                        }
                        if (intent.getStringExtra("im_server") != null) {
                            createReceiveMessage.setAttribute("im_server", intent.getStringExtra("im_server"));
                        }
                        if (intent.getStringExtra("rest_server") != null) {
                            createReceiveMessage.setAttribute("rest_server", intent.getStringExtra("rest_server"));
                        }
                        if (intent.getBooleanExtra("enable_dns", false)) {
                            createReceiveMessage.setAttribute("enable_dns", true);
                        }
                        a.this.a(createReceiveMessage, aVar);
                    }
                }
            };
            this.e.registerReceiver(this.d, new IntentFilter(b));
        }
    }

    public void a(EMMessage eMMessage, EnumC0042a aVar) {
        switch (aVar) {
            case em_retrieve_dns:
                new Thread(new Runnable() { // from class: com.hyphenate.chat.a.a.2
                    @Override // java.lang.Runnable
                    public void run() {
                        EMLog.d(a.a, "retrieve_dns");
                        a.this.f.h();
                    }
                }).start();
                return;
            case em_upload_dns:
                EMLog.d(a, "upload dns");
                return;
            case em_start_debug:
                a(true);
                EMClient.getInstance().setDebugMode(true);
                EMLog.d(a, "debugmode set to true");
                return;
            case em_stop_debug:
                a(false);
                EMLog.d(a, "debugmode set to false");
                EMClient.getInstance().setDebugMode(false);
                return;
            case em_upload_log:
                this.f.a(new EMCallBack() { // from class: com.hyphenate.chat.a.a.3
                    @Override // com.hyphenate.EMCallBack
                    public void onError(int i, String str) {
                        EMLog.d(a.a, "upload log fail, error: " + str);
                    }

                    @Override // com.hyphenate.EMCallBack
                    public void onProgress(int i, String str) {
                    }

                    @Override // com.hyphenate.EMCallBack
                    public void onSuccess() {
                        EMLog.d(a.a, "upload log success");
                    }
                });
                return;
            case em_print_user:
                boolean z = EMLog.debugMode;
                if (!z) {
                    EMLog.debugMode = true;
                }
                EMLog.d(a, " usename : " + EMClient.getInstance().getCurrentUser() + "\r\n appkey  : " + this.f.l() + "\r\n SDK     : " + this.f.e());
                EMLog.debugMode = z;
                return;
            case em_change_appkey:
                String stringAttribute = eMMessage.getStringAttribute("appkey", null);
                EMLog.d(a, "received change appkey cmd, appkey: " + stringAttribute);
                if (stringAttribute != null) {
                    a(stringAttribute);
                    Intent intent = new Intent(this.e.getPackageName() + ".em_internal_debug");
                    intent.putExtra("debug_action", "change_appkey");
                    this.e.sendBroadcast(intent);
                    return;
                }
                return;
            case em_change_servers:
                String stringAttribute2 = eMMessage.getStringAttribute("im_server", null);
                String stringAttribute3 = eMMessage.getStringAttribute("rest_server", null);
                if (!eMMessage.getBooleanAttribute("enable_dns", false)) {
                    EMLog.d(a, "change servers to " + stringAttribute2 + " and " + stringAttribute3);
                    if (!(stringAttribute2 == null || stringAttribute3 == null)) {
                        this.f.a(false);
                        this.f.c(stringAttribute2);
                        this.f.d(stringAttribute3);
                        a(stringAttribute2, stringAttribute3);
                        if (stringAttribute2.contains(":")) {
                            this.f.c(stringAttribute2.split(":")[0]);
                            this.f.a(Integer.valueOf(stringAttribute2.split(":")[1]).intValue());
                        }
                    }
                } else if (!this.f.i()) {
                    this.f.a(true);
                    a((String) null, (String) null);
                } else {
                    return;
                }
                Intent intent2 = new Intent(this.e.getPackageName() + ".em_internal_debug");
                intent2.putExtra("debug_action", "change_servers");
                this.e.sendBroadcast(intent2);
                return;
            case em_dump_db:
                String absolutePath = this.e.getFilesDir().getAbsolutePath();
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                if (externalStorageDirectory.exists()) {
                    EasyUtils.copyFolder(absolutePath + "/easemobDB", externalStorageDirectory + "/easemobDB");
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void a(b bVar) {
        this.f = bVar;
        h();
    }

    public void a(String str) {
        e.a().c(str);
    }

    public void a(String str, String str2) {
        e.a().a(str, str2);
    }

    public void a(boolean z) {
        e.a().a(z);
    }

    public String b() {
        return e.a().i();
    }

    public String c() {
        return e.a().j();
    }

    public String d() {
        return e.a().k();
    }

    public String e() {
        return e.a().l();
    }

    public void f() {
    }
}

package com.hyphenate.chat.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.chat.adapter.EMACallback;
import com.hyphenate.chat.adapter.EMAChatConfig;
import com.hyphenate.chat.adapter.message.EMAMessage;
import com.hyphenate.util.EMLog;
import java.io.File;
import java.util.List;

/* loaded from: classes2.dex */
public class b {
    static final String b = "uuid";
    static final String c = "share-secret";
    static final String d = "entities";
    private static final String f = "conf";
    private static final String g = "EASEMOB_APPKEY";
    private static final String h = "EASEMOB_CHAT_ADDRESS";
    private static final String i = "EASEMOB_CHAT_DOMAIN";
    private static final String j = "EASEMOB_GROUP_DOMAIN";
    private static final String k = "EASEMOB_CHAT_PORT";
    private static final String l = "EASEMOB_API_URL";
    private String n;
    private String p;
    private String q;
    private String r;
    private EMOptions s;
    private C0043b v;
    private String w;
    private String m = null;
    private int o = -1;
    private String t = null;

    /* renamed from: u */
    private Context f31u = null;
    boolean e = false;
    public EMAChatConfig a = new EMAChatConfig();

    /* loaded from: classes2.dex */
    public enum a {
        EMSandboxMode,
        EMProductMode,
        EMDevMode
    }

    /* renamed from: com.hyphenate.chat.a.b$b */
    /* loaded from: classes2.dex */
    public static class C0043b {
        public String a;
        public String b;

        public C0043b(String str, String str2) {
            this.a = str;
            this.b = str2;
        }
    }

    /* loaded from: classes2.dex */
    public enum c {
        EMChatMode,
        EMHelpDeskMode
    }

    private void E() {
        try {
            String e = a.a().e();
            if (e != null) {
                EMLog.debugMode = Boolean.parseBoolean(e);
            }
            if (a.a().d() != null) {
                this.m = a.a().d();
            }
            String b2 = a.a().b();
            String c2 = a.a().c();
            if (b2 != null && c2 != null) {
                if (b2.contains(":")) {
                    this.o = Integer.valueOf(b2.split(":")[1]).intValue();
                    b2 = b2.split(":")[0];
                }
                this.n = b2;
                this.p = c2;
                this.e = true;
            }
        } catch (Exception e2) {
        }
    }

    private void F() {
        EMLog.d(f, " APPKEY:" + this.m + " CHATSERVER:" + this.a.getChatAddress());
        EMLog.d(f, "STORAGE_URL:" + this.a.getRestServer());
    }

    private void a(EMOptions eMOptions) {
        this.s = eMOptions;
        this.a.setRequireReadAck(eMOptions.getRequireAck());
        this.a.setRequireDeliveryAck(eMOptions.getRequireDeliveryAck());
        this.a.setAutoAccept(eMOptions.getAcceptInvitationAlways());
        this.a.setDeleteMessageAsExitGroup(eMOptions.isDeleteMessagesAsExitGroup());
        this.a.setIsChatroomOwnerLeaveAllowed(eMOptions.isChatroomOwnerLeaveAllowed());
        this.a.setAutoAcceptGroupInvitation(eMOptions.isAutoAcceptGroupInvitation());
        this.a.enableDnsConfig(eMOptions.getEnableDNSConfig());
        this.a.setSortMessageByServerTime(eMOptions.isSortMessageByServerTime());
        this.t = eMOptions.getGCMNumber();
        this.v = eMOptions.getMipushConfig();
        this.w = eMOptions.getHuaweiPushAppId();
        if (eMOptions.getRestServer() != null && eMOptions.getImServer() != null) {
            this.a.enableDnsConfig(false);
            this.p = eMOptions.getRestServer();
            this.n = eMOptions.getImServer();
            if (eMOptions.getImPort() > 0) {
                this.o = eMOptions.getImPort();
            }
        }
    }

    public static boolean a() {
        return false;
    }

    public boolean A() {
        return this.a.getSortMessageByServerTime();
    }

    public String B() {
        return this.a.getGaoDeDiscoverKey();
    }

    public String C() {
        return this.a.getGaoDeLocationKey();
    }

    public String D() {
        return this.a.getDownloadPath();
    }

    public void a(int i2) {
        this.a.setChatPort(i2);
    }

    public void a(EMCallBack eMCallBack) {
        this.a.uploadLog(new EMACallback(eMCallBack));
    }

    public void a(C0043b bVar) {
        this.v = bVar;
    }

    void a(c cVar) {
    }

    public void a(String str) {
        String str2;
        if (this.f31u != null) {
            String absolutePath = this.f31u.getFilesDir().getAbsolutePath();
            String packageName = this.f31u.getPackageName();
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            String str3 = "/Android/data/" + packageName + "/" + str + "/";
            if (!file.exists() || !file.canWrite()) {
                str2 = absolutePath;
            } else {
                str2 = file.getAbsolutePath() + str3 + "core_log";
                absolutePath = file.getAbsolutePath() + str3 + "files";
                new File(str2).mkdirs();
                new File(absolutePath).mkdirs();
            }
            this.a.setLogPath(str2);
            this.a.setDownloadPath(absolutePath);
        }
    }

    public void a(String str, int i2) {
        this.a.updateConversationUnreadCount(str, i2);
    }

    public void a(String str, int i2, String str2) {
        this.a.importConversation(str, i2, str2);
    }

    public void a(String str, int i2, String str2, String str3, String str4, List<String> list, boolean z, int i3) {
        this.a.importGroup(str, i2, str2, str3, str4, list, z, i3);
    }

    public void a(String str, String str2, String str3, String str4, List<String> list, int i2) {
        this.a.importChatRoom(str, str2, str3, str4, list, i2);
    }

    public void a(List<String> list) {
        this.a.importBlackList(list);
    }

    public void a(boolean z) {
        this.a.enableDnsConfig(z);
    }

    public boolean a(Context context, EMOptions eMOptions) {
        ApplicationInfo applicationInfo;
        String str = null;
        String absolutePath = context.getFilesDir().getAbsolutePath();
        this.f31u = context;
        try {
            applicationInfo = this.f31u.getPackageManager().getApplicationInfo(this.f31u.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e) {
            EMLog.e(f, e.getMessage());
            EMLog.e(f, "找不到ApplicationInfo");
            applicationInfo = null;
        }
        if (eMOptions != null && !TextUtils.isEmpty(eMOptions.getAppKey())) {
            str = eMOptions.getAppKey();
        }
        this.m = str;
        if (applicationInfo != null) {
            Bundle bundle = applicationInfo.metaData;
            if (bundle == null) {
                EMLog.w(f, "请确认meta属性写在清单文件里的application节点以内");
            } else {
                String string = bundle.getString(g);
                if (string == null && this.m == null) {
                    Log.w(f, "EASEMOB_APPKEY is not set in AndroidManifest file");
                } else if (TextUtils.isEmpty(this.m)) {
                    this.m = string;
                }
                String string2 = bundle.getString(h);
                if (string2 != null) {
                    this.n = string2;
                }
                int i2 = bundle.getInt(k, -1);
                if (i2 != -1) {
                    this.o = i2;
                }
                String string3 = bundle.getString(l);
                if (string3 != null) {
                    this.p = string3;
                }
                String string4 = bundle.getString("GCM_PROJECT_NUMBER");
                if (string4 != null && this.t == null) {
                    this.t = string4;
                }
                String string5 = bundle.getString(i);
                if (string5 != null) {
                    this.q = string5;
                }
                String string6 = bundle.getString(j);
                if (string6 != null) {
                    this.r = string6;
                }
            }
        }
        this.a.init(absolutePath, absolutePath, this.m);
        a(eMOptions);
        E();
        a(this.m);
        EMLog.i(f, "EASEMOB_APPKEY is set to:" + this.m);
        if (this.n != null && !this.n.equals("")) {
            this.a.setChatServer(this.n);
        }
        if (this.p != null && !this.p.equals("")) {
            this.a.setRestServer(this.p);
        }
        if (this.q != null && !this.q.equals("")) {
            this.a.setChatDomain(this.q);
        }
        if (this.r != null && !this.r.equals("")) {
            this.a.setGroupDomain(this.r);
        }
        if (this.o != -1) {
            this.a.setChatPort(this.o);
        }
        if (this.e) {
            this.a.enableDnsConfig(false);
        }
        this.a.setSDKVersion(EMClient.VERSION);
        F();
        return true;
    }

    public EMOptions b() {
        return this.s;
    }

    public String b(boolean z) {
        return this.a.getAccessToken(z);
    }

    public void b(String str) {
        this.a.openDatabase(str);
    }

    public void b(List<String> list) {
        this.a.importContacts(list);
    }

    c c() {
        return c.EMChatMode;
    }

    public void c(String str) {
        this.a.setChatServer(str);
    }

    public void c(List<EMAMessage> list) {
        this.a.importMessages(list);
    }

    public void c(boolean z) {
        this.a.setDebugMode(z);
    }

    a d() {
        return this.a.getIsSandboxMode() ? a.EMSandboxMode : a.EMProductMode;
    }

    public void d(String str) {
        this.a.setRestServer(str);
    }

    public void d(boolean z) {
        this.a.setRequireDeliveryAck(z);
    }

    public String e() {
        return EMClient.VERSION;
    }

    public void e(String str) {
        this.t = str;
    }

    public void e(boolean z) {
        this.a.setRequireReadAck(z);
    }

    public String f() {
        return this.a.getBaseUrl();
    }

    public void f(String str) {
        this.w = str;
    }

    public void f(boolean z) {
        this.a.setUseEncryption(z);
    }

    public void g(boolean z) {
        this.a.setAutoAccept(z);
    }

    public boolean g() {
        return this.a.useHttps();
    }

    public void h() {
        this.a.retrieveDNSConfig();
    }

    public void h(boolean z) {
        this.a.setDeleteMessageAsExitGroup(z);
    }

    public void i(boolean z) {
        this.a.setAutoAcceptGroupInvitation(z);
    }

    public boolean i() {
        return this.a.isEnableDnsConfig();
    }

    public void j(boolean z) {
        this.a.setIsChatroomOwnerLeaveAllowed(z);
    }

    public boolean j() {
        return this.a.isGcmEnabled();
    }

    public String k() {
        return this.a.getRestServer();
    }

    public void k(boolean z) {
        this.a.setSortMessageByServerTime(z);
    }

    public String l() {
        return this.a.getAppKey();
    }

    public void l(boolean z) {
        this.a.setUseHttps(z);
    }

    public String m() {
        return this.a.getNextAvailableBaseUrl();
    }

    public String n() {
        return this.a.getAccessToken();
    }

    public long o() {
        return this.a.getTokenSaveTime();
    }

    public boolean p() {
        return this.a.getRequireDeliveryAck();
    }

    public boolean q() {
        return this.a.getRequireReadAck();
    }

    public boolean r() {
        return false;
    }

    public boolean s() {
        return this.a.getAutoAccept();
    }

    public boolean t() {
        return this.a.getDeleteMessageAsExitGroup();
    }

    public boolean u() {
        return this.a.getAutoAcceptGroupInvitation();
    }

    public boolean v() {
        return this.a.getIsChatroomOwnerLeaveAllowed();
    }

    public String w() {
        return this.t;
    }

    public C0043b x() {
        return this.v;
    }

    public String y() {
        return this.w;
    }

    public void z() {
        this.a.reloadAll();
    }
}

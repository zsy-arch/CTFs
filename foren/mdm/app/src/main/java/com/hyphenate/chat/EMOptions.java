package com.hyphenate.chat;

import com.hyphenate.chat.a.b;

/* loaded from: classes.dex */
public class EMOptions {
    private int imPort;
    private String imServer;
    private String restServer;
    private boolean acceptInvitationAlways = true;
    private boolean autoAcceptGroupInvitation = true;
    private boolean useEncryption = false;
    private boolean requireReadAck = true;
    private boolean requireDeliveryAck = false;
    private boolean requireServerAck = true;
    private boolean deleteMessagesAsExitGroup = true;
    private boolean isChatroomOwnerLeaveAllowed = true;
    private String appkey = "";
    private b config = null;
    private boolean enableAutoLogin = true;
    private String gcmNumber = null;
    private b.C0043b mipushConfig = null;
    private String huaweiPushAppId = null;
    private boolean enableDNSConfig = true;
    private boolean sortMessageByServerTime = true;
    private boolean useHttps = false;

    private boolean getUseEncryption() {
        return this.config == null ? this.useEncryption : this.config.r();
    }

    private void setUseEncryption(boolean z) {
        this.useEncryption = z;
        if (this.config != null) {
            this.config.f(z);
        }
    }

    public void allowChatroomOwnerLeave(boolean z) {
        if (this.config == null) {
            this.isChatroomOwnerLeaveAllowed = z;
        } else {
            this.config.j(z);
        }
    }

    public void enableDNSConfig(boolean z) {
        this.enableDNSConfig = z;
        if (this.config != null) {
            this.config.a(z);
        }
    }

    public boolean getAcceptInvitationAlways() {
        return this.config == null ? this.acceptInvitationAlways : this.config.s();
    }

    public String getAccessToken() {
        return this.config.n();
    }

    public String getAccessToken(boolean z) {
        return this.config.b(z);
    }

    public String getAppKey() {
        return this.config == null ? this.appkey : this.config.l();
    }

    public boolean getAutoLogin() {
        return this.enableAutoLogin;
    }

    public boolean getEnableDNSConfig() {
        return this.enableDNSConfig;
    }

    public String getGCMNumber() {
        return this.config == null ? this.gcmNumber : this.config.w();
    }

    public String getHuaweiPushAppId() {
        return this.config == null ? this.huaweiPushAppId : this.config.y();
    }

    public int getImPort() {
        return this.imPort;
    }

    public String getImServer() {
        return this.imServer;
    }

    public b.C0043b getMipushConfig() {
        return this.config == null ? this.mipushConfig : this.config.x();
    }

    public boolean getRequireAck() {
        return this.config == null ? this.requireReadAck : this.config.q();
    }

    public boolean getRequireDeliveryAck() {
        return this.config == null ? this.requireDeliveryAck : this.config.p();
    }

    public String getRestServer() {
        return this.restServer;
    }

    public String getVersion() {
        return this.config.e();
    }

    public boolean isAutoAcceptGroupInvitation() {
        return this.config == null ? this.autoAcceptGroupInvitation : this.config.u();
    }

    public boolean isChatroomOwnerLeaveAllowed() {
        return this.config == null ? this.isChatroomOwnerLeaveAllowed : this.config.v();
    }

    public boolean isDeleteMessagesAsExitGroup() {
        return this.config == null ? this.deleteMessagesAsExitGroup : this.config.t();
    }

    public boolean isSortMessageByServerTime() {
        return this.config == null ? this.sortMessageByServerTime : this.config.A();
    }

    public void setAcceptInvitationAlways(boolean z) {
        if (this.config == null) {
            this.acceptInvitationAlways = z;
        } else {
            this.config.g(z);
        }
    }

    public void setAppKey(String str) {
        this.appkey = str;
        updatePath(str);
    }

    public void setAutoAcceptGroupInvitation(boolean z) {
        if (this.config == null) {
            this.autoAcceptGroupInvitation = z;
        } else {
            this.config.i(z);
        }
    }

    public void setAutoLogin(boolean z) {
        this.enableAutoLogin = z;
    }

    public void setConfig(b bVar) {
        this.config = bVar;
    }

    public void setDeleteMessagesAsExitGroup(boolean z) {
        if (this.config == null) {
            this.deleteMessagesAsExitGroup = z;
        } else {
            this.config.h(z);
        }
    }

    public void setGCMNumber(String str) {
        this.gcmNumber = str;
        if (this.config != null) {
            this.config.e(str);
        }
    }

    public void setHuaweiPushAppId(String str) {
        this.huaweiPushAppId = str;
        if (this.config != null) {
            this.config.f(str);
        }
    }

    public void setIMServer(String str) {
        if (str != null) {
            this.imServer = str;
            if (this.config != null) {
                this.config.c(str);
            }
        }
    }

    public void setImPort(int i) {
        this.imPort = i;
        if (this.config != null) {
            this.config.a(i);
        }
    }

    public void setMipushConfig(String str, String str2) {
        if (this.config == null) {
            this.mipushConfig = new b.C0043b(str, str2);
        } else {
            this.config.a(new b.C0043b(str, str2));
        }
    }

    public void setRequireAck(boolean z) {
        this.requireReadAck = z;
        if (this.config != null) {
            this.config.e(z);
        }
    }

    public void setRequireDeliveryAck(boolean z) {
        this.requireDeliveryAck = z;
        if (this.config != null) {
            this.config.d(z);
        }
    }

    public void setRestServer(String str) {
        if (str != null) {
            this.restServer = str;
            if (this.config != null) {
                this.config.d(str);
            }
        }
    }

    public void setSortMessageByServerTime(boolean z) {
        if (this.config == null) {
            this.sortMessageByServerTime = z;
        } else {
            this.config.k(z);
        }
    }

    public void setUseHttps(boolean z) {
        this.useHttps = z;
        if (this.config != null) {
            this.config.l(z);
        }
    }

    public void updatePath(String str) {
        if (this.config != null) {
            this.config.a(str);
        }
    }
}

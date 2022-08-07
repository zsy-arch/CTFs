package com.vsf2f.f2f.bean;

/* loaded from: classes2.dex */
public class ChatImageBean {
    private String chatname;
    private String httpurl;
    private String localpath;
    private String msgid;
    private long savetime;
    private String username;

    public ChatImageBean() {
    }

    public ChatImageBean(String username, String chatname) {
        this.username = username;
        this.chatname = chatname;
    }

    public String getMsgid() {
        return this.msgid;
    }

    public void setMsgid(String username) {
        this.msgid = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChatname() {
        return this.chatname;
    }

    public void setChatname(String chatname) {
        this.chatname = chatname;
    }

    public String getHttpurl() {
        return this.httpurl;
    }

    public void setHttpurl(String httpurl) {
        this.httpurl = httpurl;
    }

    public String getLocalpath() {
        return this.localpath;
    }

    public void setLocalpath(String localpath) {
        this.localpath = localpath;
    }

    public long getSavetime() {
        return this.savetime;
    }

    public void setSavetime(long savetime) {
        this.savetime = savetime;
    }

    public String toString() {
        return "ChatImageBean{username='" + this.username + "', chatname='" + this.chatname + "', httpurl='" + this.httpurl + "', localpath='" + this.localpath + "'}";
    }
}

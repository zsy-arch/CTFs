package com.hyphenate.chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class EMSessionManager {
    private static final String PREF_KEY_LOGIN_PWD = "easemob.chat.loginpwd";
    private static final String PREF_KEY_LOGIN_USER = "easemob.chat.loginuser";
    private static final String TAG = "Session";
    private static EMSessionManager instance = new EMSessionManager();
    private Context appContext = null;
    public EMContact currentUser = null;
    private String lastLoginUser = null;
    private String lastLoginPwd = null;

    EMSessionManager() {
    }

    public static synchronized EMSessionManager getInstance() {
        EMSessionManager eMSessionManager;
        synchronized (EMSessionManager.class) {
            if (instance.appContext == null) {
                instance.appContext = EMClient.getInstance().getContext();
            }
            eMSessionManager = instance;
        }
        return eMSessionManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearLastLoginPwd() {
        try {
            this.lastLoginPwd = "";
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this.appContext).edit();
            edit.putString(PREF_KEY_LOGIN_PWD, this.lastLoginPwd);
            edit.apply();
        } catch (Exception e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearLastLoginUser() {
        try {
            this.lastLoginUser = "";
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this.appContext).edit();
            edit.putString(PREF_KEY_LOGIN_USER, this.lastLoginUser);
            edit.apply();
        } catch (Exception e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getLastLoginPwd() {
        if (this.lastLoginPwd == null) {
            String string = PreferenceManager.getDefaultSharedPreferences(this.appContext).getString(PREF_KEY_LOGIN_PWD, "");
            if (string.equals("")) {
                this.lastLoginPwd = "";
                return this.lastLoginPwd;
            }
            try {
                this.lastLoginPwd = EMClient.getInstance().getCryptoUtils().decryptBase64String(string);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.lastLoginPwd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getLastLoginUser() {
        if (this.lastLoginUser == null) {
            this.lastLoginUser = PreferenceManager.getDefaultSharedPreferences(this.appContext).getString(PREF_KEY_LOGIN_USER, "");
            this.currentUser = new EMContact(this.lastLoginUser);
        }
        return this.lastLoginUser;
    }

    public String getLoginUserName() {
        return this.currentUser.username;
    }

    public void setLastLoginPwd(String str) {
        if (str != null) {
            this.lastLoginPwd = str;
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this.appContext).edit();
            try {
                edit.putString(PREF_KEY_LOGIN_PWD, EMClient.getInstance().getCryptoUtils().encryptBase64String(str));
                edit.apply();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLastLoginUser(String str) {
        if (str != null) {
            this.currentUser = new EMContact(str);
            this.lastLoginUser = str;
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this.appContext).edit();
            edit.putString(PREF_KEY_LOGIN_USER, str);
            edit.apply();
        }
    }
}

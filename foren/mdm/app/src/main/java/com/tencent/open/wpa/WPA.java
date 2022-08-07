package com.tencent.open.wpa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.TDialog;
import com.tencent.open.b.d;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.UnsupportedEncodingException;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class WPA extends BaseApi {
    public static final String CHAT_TYPE_GROUP = "group";
    public static final String CHAT_TYPE_WPA = "wpa";

    public WPA(Context context, QQAuth qQAuth, QQToken qQToken) {
        super(qQAuth, qQToken);
    }

    public WPA(Context context, QQToken qQToken) {
        super(qQToken);
    }

    public WPA(QQToken qQToken) {
        super(qQToken);
    }

    public void getWPAUserOnlineState(String str, IUiListener iUiListener) {
        try {
            if (str == null) {
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_WAP_STATE, "15", "18", "1");
                throw new Exception("uin null");
            } else if (str.length() < 5) {
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_WAP_STATE, "15", "18", "1");
                throw new Exception("uin length < 5");
            } else {
                for (int i = 0; i < str.length(); i++) {
                    if (!Character.isDigit(str.charAt(i))) {
                        d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_WAP_STATE, "15", "18", "1");
                        throw new Exception("uin not digit");
                    }
                }
                HttpUtils.requestAsync(this.mToken, Global.getContext(), "http://webpresence.qq.com/getonline?Type=1&" + str + ":", null, "GET", new BaseApi.TempRequestListener(iUiListener));
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_WAP_STATE, "15", "18", "0");
            }
        } catch (Exception e) {
            if (iUiListener != null) {
                iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, null));
            }
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_WAP_STATE, "15", "18", "1");
        }
    }

    public int startWPAConversation(Activity activity, String str, String str2) {
        return startWPAConversation(activity, CHAT_TYPE_WPA, str, str2);
    }

    public int startWPAConversation(Activity activity, String str, String str2, String str3) {
        int i;
        if (str == null || (!str.equals(CHAT_TYPE_WPA) && !str.equals(CHAT_TYPE_GROUP))) {
            return -5;
        }
        String str4 = "16";
        if (str.equals(CHAT_TYPE_GROUP)) {
            str4 = "17";
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        if (TextUtils.isEmpty(str2)) {
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_START_WAP, str4, "18", "1");
            return -1;
        } else if (str2.length() < 5) {
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_START_WAP, str4, "18", "1");
            return -3;
        } else {
            for (int i2 = 0; i2 < str2.length(); i2++) {
                if (!Character.isDigit(str2.charAt(i2))) {
                    d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_START_WAP, str4, "18", "1");
                    return -4;
                }
            }
            String str5 = "";
            if (!TextUtils.isEmpty(str3)) {
                try {
                    str5 = Base64.encodeToString(str3.getBytes("UTF-8"), 2);
                } catch (UnsupportedEncodingException e) {
                }
            }
            intent.setData(Uri.parse(String.format("mqqwpa://im/chat?chat_type=%1$s&uin=%2$s&version=1&src_type=app&attach_content=%3$s", str, str2, str5)));
            PackageManager packageManager = Global.getContext().getPackageManager();
            if (packageManager.queryIntentActivities(intent, 65536).size() > 0) {
                activity.startActivity(intent);
                i = 0;
            } else {
                intent.setData(Uri.parse("http://www.myapp.com/forward/a/45592?g_f=990935"));
                if (packageManager.queryIntentActivities(intent, 65536).size() > 0) {
                    new TDialog(activity, "", getCommonDownloadQQUrl(""), null, this.mToken).show();
                    i = 0;
                } else {
                    d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_START_WAP, str4, "18", "1");
                    i = -2;
                }
            }
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_START_WAP, str4, "18", "0");
            return i;
        }
    }
}

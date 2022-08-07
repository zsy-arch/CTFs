package com.tencent.open.yyb;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.a.f;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.yyb.a;
import java.util.regex.Pattern;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class AppbarAgent extends BaseApi {
    public static final String TO_APPBAR_DETAIL = "siteIndex";
    public static final String TO_APPBAR_NEWS = "myMessage";
    public static final String TO_APPBAR_SEND_BLOG = "newThread";
    public static final String wx_appid = "wx8e8dc60535c9cd93";
    private Bundle a;
    private String b;

    public AppbarAgent(QQToken qQToken) {
        super(qQToken);
    }

    public void startAppbarLabel(Activity activity, String str) {
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(activity, Constants.MSG_PARAM_ERROR, 0).show();
            return;
        }
        this.a = new Bundle();
        this.a.putString("params", "label/" + str);
        startAppbar(activity, "sId");
    }

    public void startAppbarThread(Activity activity, String str) {
        if (!d(str)) {
            Toast.makeText(activity, Constants.MSG_PARAM_ERROR, 0).show();
            return;
        }
        this.b = str;
        startAppbar(activity, "toThread");
    }

    public void startAppbar(Activity activity, String str) {
        if (!a(str)) {
            Toast.makeText(activity, Constants.MSG_PARAM_ERROR, 0).show();
            return;
        }
        String c = c(str);
        String b = b();
        if (TextUtils.isEmpty(b) || SystemUtils.compareVersion(b, "4.2") < 0) {
            a(activity, c);
            return;
        }
        String str2 = c + a();
        f.a("openSDK_LOG.AppbarAgent", "-->(AppbarAgent)startAppbar : yybUrl = " + str2);
        try {
            Intent intent = new Intent();
            intent.setClassName("com.tencent.android.qqdownloader", "com.tencent.assistant.activity.ExportBrowserActivity");
            intent.putExtra("com.tencent.assistant.BROWSER_URL", str2);
            activity.startActivity(intent);
            activity.overridePendingTransition(17432576, 17432577);
        } catch (Exception e) {
            f.b("openSDK_LOG.AppbarAgent", "-->(AppbarAgent)startAppbar : ExportBrowserActivity not found, start H5", e);
            a(activity, c);
        }
    }

    private boolean a(String str) {
        return TO_APPBAR_DETAIL.equals(str) || TO_APPBAR_NEWS.equals(str) || TO_APPBAR_SEND_BLOG.equals(str) || "sId".equals(str) || "toThread".equals(str);
    }

    private void a(Activity activity, String str) {
        if (this.mToken != null) {
            Intent intent = new Intent(activity, AppbarActivity.class);
            intent.putExtra("appid", this.mToken.getAppId());
            if (!(this.mToken.getAccessToken() == null || this.mToken.getOpenId() == null)) {
                a.C0089a aVar = new a.C0089a();
                aVar.b = this.mToken.getAccessToken();
                aVar.c = Long.parseLong(this.mToken.getAppId());
                aVar.a = this.mToken.getOpenId();
                a.a(activity, str, this.mToken.getOpenId(), this.mToken.getAccessToken(), this.mToken.getAppId());
            }
            intent.putExtra("url", str);
            f.a("openSDK_LOG.AppbarAgent", "-->(AppbarAgent)startAppbar H5 : url = " + str);
            try {
                activity.startActivityForResult(intent, Constants.REQUEST_APPBAR);
            } catch (Exception e) {
                f.b("openSDK_LOG.AppbarAgent", "-->(AppbarAgent)startAppbar : activity not found, start H5", e);
            }
        }
    }

    private Bundle b(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("pkgName", Global.getContext().getPackageName());
        if (!TO_APPBAR_DETAIL.equals(str) && !TO_APPBAR_SEND_BLOG.equals(str)) {
            if (TO_APPBAR_NEWS.equals(str)) {
                bundle.putString(SocialConstants.PARAM_SOURCE, "myapp");
            } else if ("sId".equals(str)) {
                if (this.a != null) {
                    bundle.putAll(this.a);
                }
            } else if ("toThread".equals(str)) {
                str = String.format("sId/t/%s", this.b);
            }
        }
        bundle.putString("route", str);
        return bundle;
    }

    private String c(String str) {
        return "http://m.wsq.qq.com/direct?" + a(b(str));
    }

    private String a() {
        Bundle bundle = new Bundle();
        if (!(this.mToken == null || this.mToken.getAppId() == null || this.mToken.getAccessToken() == null || this.mToken.getOpenId() == null)) {
            bundle.putString("qOpenAppId", this.mToken.getAppId());
            bundle.putString("qOpenId", this.mToken.getOpenId());
            bundle.putString("qAccessToken", this.mToken.getAccessToken());
        }
        bundle.putString("qPackageName", Global.getContext().getPackageName());
        return com.alipay.sdk.sys.a.b + a(bundle);
    }

    private String a(Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : bundle.keySet()) {
            sb.append(str).append("=").append(bundle.get(str)).append(com.alipay.sdk.sys.a.b);
        }
        String sb2 = sb.toString();
        if (sb2.endsWith(com.alipay.sdk.sys.a.b)) {
            sb2 = sb2.substring(0, sb2.length() - 1);
        }
        f.a("openSDK_LOG.AppbarAgent", "-->encodeParams, result: " + sb2);
        return sb2;
    }

    private String b() {
        try {
            PackageInfo packageInfo = Global.getContext().getPackageManager().getPackageInfo("com.tencent.android.qqdownloader", 0);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean d(String str) {
        return !TextUtils.isEmpty(str) && Pattern.matches("^[1-9][0-9]*$", str);
    }
}

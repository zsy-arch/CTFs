package com.cdlinglu.utils.x5WebView;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.hy.frame.util.MyLog;
import com.hyphenate.util.HanziToPinyin;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/* loaded from: classes.dex */
public class MyWebViewClient extends WebViewClient {
    @Override // com.tencent.smtt.sdk.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        String address;
        Context context = webView.getContext();
        MyLog.d("shouldOverrideUrlLoading-----> " + url);
        if (url.startsWith(WebView.SCHEME_TEL)) {
            try {
                Intent intent = new Intent("android.intent.action.DIAL");
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
                return true;
            } catch (ActivityNotFoundException e) {
                MyLog.d("Error dialing " + url + ": " + e.toString());
                return true;
            }
        } else if (url.startsWith("geo:")) {
            try {
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setData(Uri.parse(url));
                context.startActivity(intent2);
                return true;
            } catch (ActivityNotFoundException e2) {
                MyLog.d("Error showing map " + url + ": " + e2.toString());
                return true;
            }
        } else if (url.startsWith(WebView.SCHEME_MAILTO)) {
            try {
                Intent intent3 = new Intent("android.intent.action.VIEW");
                intent3.setData(Uri.parse(url));
                context.startActivity(intent3);
                return true;
            } catch (ActivityNotFoundException e3) {
                MyLog.d("Error sending email " + url + ": " + e3.toString());
                return true;
            }
        } else if (url.startsWith("sms:")) {
            try {
                Intent intent4 = new Intent("android.intent.action.VIEW");
                int parmIndex = url.indexOf(63);
                if (parmIndex == -1) {
                    address = url.substring(4);
                } else {
                    address = url.substring(4, parmIndex);
                    String query = Uri.parse(url).getQuery();
                    if (query != null && query.startsWith("body=")) {
                        intent4.putExtra("sms_body", query.substring(5));
                    }
                }
                intent4.setData(Uri.parse("sms:" + address));
                intent4.putExtra("address", address);
                intent4.setType("vnd.android-dir/mms-sms");
                context.startActivity(intent4);
                return true;
            } catch (ActivityNotFoundException e4) {
                MyLog.d("Error sending sms " + url + ":" + e4.toString());
                return true;
            }
        } else if (url.startsWith("market:")) {
            try {
                Intent intent5 = new Intent("android.intent.action.VIEW");
                intent5.setData(Uri.parse(url));
                context.startActivity(intent5);
                return true;
            } catch (ActivityNotFoundException e5) {
                MyLog.d("Error loading Google Play Store: " + url + HanziToPinyin.Token.SEPARATOR + e5.toString());
                return true;
            }
        } else if (!url.endsWith(".apk")) {
            return true;
        } else {
            try {
                Intent intent6 = new Intent("android.intent.action.VIEW");
                intent6.setData(Uri.parse(url));
                context.startActivity(intent6);
                return true;
            } catch (ActivityNotFoundException e6) {
                MyLog.d("Error dialing " + url + ": " + e6.toString());
                return true;
            }
        }
    }
}

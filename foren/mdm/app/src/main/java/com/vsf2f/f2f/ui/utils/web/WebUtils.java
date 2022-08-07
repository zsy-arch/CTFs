package com.vsf2f.f2f.ui.utils.web;

import android.content.Context;
import android.webkit.WebView;
import com.cdlinglu.utils.ComUtil;
import com.google.gson.Gson;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.UrlTokenJsonBean;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.http.HttpHost;

/* loaded from: classes2.dex */
public class WebUtils {
    public static void loadTokenUrl(Context context, WebView webView, String url) {
        nextLoad(webView, getTokenUrl(context, url));
    }

    public static void loadOtherUrl(WebView webView, String url) {
        nextLoad(webView, url);
    }

    private static void nextLoad(WebView webView, String url) {
        webView.loadUrl(url);
    }

    public static boolean isLogin(Context context) {
        return new AppShare(context).getString(Constant.USER_TOKEN) != null;
    }

    public static String getTokenStr(Context context) {
        String token = ComUtil.getUserToken(context);
        if (!HyUtil.isNoEmpty(token)) {
            return "";
        }
        AjaxParams params1 = new AjaxParams();
        params1.put("vs_access_token", token);
        params1.put("vs_nonce_str", ComUtil.getRandomString(32));
        params1.put("vs_timestamp", System.currentTimeMillis() - AppShare.get(context).getLong("diffTime"));
        return "?vs_access_token=" + token + "&vs_request_token=" + ComUtil.encryptParam(params1) + "&vs_timestamp=" + params1.getUrlParams().get("vs_timestamp") + "&vs_nonce_str=" + params1.getUrlParams().get("vs_nonce_str");
    }

    public static String getTokenJson(Context context) {
        String token = ComUtil.getUserToken(context);
        if (!HyUtil.isNoEmpty(token) || !isLogin(context)) {
            return "";
        }
        AjaxParams params1 = new AjaxParams();
        params1.put("vs_access_token", token);
        params1.put("vs_nonce_str", ComUtil.getRandomString(32));
        params1.put("vs_timestamp", System.currentTimeMillis() - AppShare.get(context).getLong("diffTime"));
        String vs_request_token = ComUtil.encryptParam(params1);
        UrlTokenJsonBean bean = new UrlTokenJsonBean();
        bean.setVs_access_token(token);
        bean.setVs_nonce_str(params1.getUrlParams().get("vs_nonce_str"));
        bean.setVs_request_token(vs_request_token);
        bean.setVs_timestamp(params1.getUrlParams().get("vs_timestamp"));
        return new Gson().toJson(bean);
    }

    public static String getTokenUrl(Context context, String url) {
        String token = ComUtil.getUserToken(context);
        String host = context.getString(R.string.API_HOST);
        if (!HyUtil.isNoEmpty(token)) {
            return url;
        }
        String md5url = null;
        try {
            if (!url.contains(host) && !url.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                url = host + url;
            }
            md5url = URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ComUtil.getCom_Url(context) + getTokenStr(context) + "&redirectURL=" + md5url;
    }
}

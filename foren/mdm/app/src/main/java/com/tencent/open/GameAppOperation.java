package com.tencent.open;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Toast;
import com.alipay.sdk.sys.a;
import com.alipay.sdk.util.h;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.connect.share.QQShare;
import com.tencent.open.a.f;
import com.tencent.open.b.c;
import com.tencent.open.b.d;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.ThreadManager;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class GameAppOperation extends BaseApi {
    public static final String GAME_FRIEND_ADD_MESSAGE = "add_msg";
    public static final String GAME_FRIEND_LABEL = "friend_label";
    public static final String GAME_FRIEND_OPENID = "fopen_id";
    public static final String GAME_SIGNATURE = "signature";
    public static final String GAME_UNION_ID = "unionid";
    public static final String GAME_UNION_NAME = "union_name";
    public static final String GAME_ZONE_ID = "zoneid";
    public static final char PIC_SYMBOLE = 20;
    public static final String QQFAV_DATALINE_APPNAME = "app_name";
    public static final String QQFAV_DATALINE_AUDIOURL = "audioUrl";
    public static final String QQFAV_DATALINE_DESCRIPTION = "description";
    public static final String QQFAV_DATALINE_FILEDATA = "file_data";
    public static final String QQFAV_DATALINE_IMAGEURL = "image_url";
    public static final String QQFAV_DATALINE_OPENID = "open_id";
    public static final String QQFAV_DATALINE_REQTYPE = "req_type";
    public static final String QQFAV_DATALINE_SHAREID = "share_id";
    public static final String QQFAV_DATALINE_SRCTYPE = "src_type";
    public static final String QQFAV_DATALINE_TITLE = "title";
    public static final int QQFAV_DATALINE_TYPE_AUDIO = 2;
    public static final int QQFAV_DATALINE_TYPE_DEFAULT = 1;
    public static final int QQFAV_DATALINE_TYPE_IMAGE_TEXT = 5;
    public static final int QQFAV_DATALINE_TYPE_TEXT = 6;
    public static final String QQFAV_DATALINE_URL = "url";
    public static final String QQFAV_DATALINE_VERSION = "version";
    public static final String SHARE_PRIZE_ACTIVITY_ID = "activityid";
    public static final String SHARE_PRIZE_IMAGE_URL = "imageUrl";
    public static final String SHARE_PRIZE_SHARE_ID = "shareid";
    public static final String SHARE_PRIZE_SHARE_ID_LIST = "shareid_list";
    public static final String SHARE_PRIZE_SUMMARY = "summary";
    public static final int SHARE_PRIZE_SUMMARY_MAX_LENGTH = 60;
    public static final String SHARE_PRIZE_TARGET_URL = "targetUrl";
    public static final String SHARE_PRIZE_TITLE = "title";
    public static final int SHARE_PRIZE_TITLE_MAX_LENGTH = 45;
    public static final String TROOPBAR_ID = "troopbar_id";

    public GameAppOperation(QQToken qQToken) {
        super(qQToken);
    }

    public void makeFriend(Activity activity, Bundle bundle) {
        f.c("openSDK_LOG.GameAppOperation", "-->makeFriend()  -- start");
        if (bundle == null) {
            f.e("openSDK_LOG.GameAppOperation", "-->makeFriend params is null");
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, "14", "18", "1");
            return;
        }
        String string = bundle.getString(GAME_FRIEND_OPENID);
        if (TextUtils.isEmpty(string)) {
            f.e("openSDK_LOG.GameAppOperation", "-->make friend, fOpenid is empty.");
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, "14", "18", "1");
            return;
        }
        String string2 = bundle.getString(GAME_FRIEND_LABEL);
        String string3 = bundle.getString(GAME_FRIEND_ADD_MESSAGE);
        String applicationLable = Util.getApplicationLable(activity);
        String openId = this.mToken.getOpenId();
        String appId = this.mToken.getAppId();
        f.a("openSDK_LOG.GameAppOperation", "-->make friend, fOpenid: " + string + " | label: " + string2 + " | message: " + string3 + " | openid: " + openId + " | appid:" + appId);
        StringBuffer stringBuffer = new StringBuffer("mqqapi://gamesdk/add_friend?src_type=app&version=1");
        stringBuffer.append("&fopen_id=" + Base64.encodeToString(Util.getBytesUTF8(string), 2));
        if (!TextUtils.isEmpty(openId)) {
            stringBuffer.append("&open_id=" + Base64.encodeToString(Util.getBytesUTF8(openId), 2));
        }
        if (!TextUtils.isEmpty(appId)) {
            stringBuffer.append("&app_id=" + appId);
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append("&friend_label=" + Base64.encodeToString(Util.getBytesUTF8(string2), 2));
        }
        if (!TextUtils.isEmpty(string3)) {
            stringBuffer.append("&add_msg=" + Base64.encodeToString(Util.getBytesUTF8(string3), 2));
        }
        if (!TextUtils.isEmpty(applicationLable)) {
            stringBuffer.append("&app_name=" + Base64.encodeToString(Util.getBytesUTF8(applicationLable), 2));
        }
        f.a("openSDK_LOG.GameAppOperation", "-->make friend, url: " + stringBuffer.toString());
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(stringBuffer.toString()));
        if (!hasActivityForIntent(intent) || Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_5_1_0)) {
            f.d("openSDK_LOG.GameAppOperation", "-->make friend, there is no activity.");
            a(activity);
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, "14", "18", "1");
        } else {
            f.c("openSDK_LOG.GameAppOperation", "-->makeFriend target activity found, qqver greater than 5.1.0");
            try {
                activity.startActivity(intent);
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, "14", "18", "0");
            } catch (Exception e) {
                f.b("openSDK_LOG.GameAppOperation", "-->make friend, start activity exception.", e);
                a(activity);
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, "14", "18", "1");
            }
        }
        f.c("openSDK_LOG.GameAppOperation", "-->makeFriend()  -- end");
    }

    public void bindQQGroup(Activity activity, Bundle bundle) {
        f.c("openSDK_LOG.GameAppOperation", "-->bindQQGroup()  -- start");
        if (activity == null) {
            f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, activity is empty.");
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
        } else if (bundle == null) {
            Toast.makeText(activity, "Bundle参数为空", 0).show();
            f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, params is empty.");
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
        } else {
            String applicationLable = Util.getApplicationLable(activity);
            StringBuffer stringBuffer = new StringBuffer("mqqapi://gamesdk/bind_group?src_type=app&version=1");
            if (!TextUtils.isEmpty(applicationLable)) {
                stringBuffer.append("&app_name=" + Base64.encodeToString(Util.getBytesUTF8(applicationLable), 2));
            }
            String string = bundle.getString(GAME_UNION_ID);
            if (TextUtils.isEmpty(string)) {
                Toast.makeText(activity, "游戏公会ID为空", 0).show();
                f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game union id is empty.");
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
                return;
            }
            stringBuffer.append("&unionid=" + Base64.encodeToString(Util.getBytesUTF8(string), 2));
            String string2 = bundle.getString(GAME_UNION_NAME);
            if (TextUtils.isEmpty(string2)) {
                Toast.makeText(activity, "游戏公会名称为空", 0).show();
                f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game union name is empty.");
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
                return;
            }
            stringBuffer.append("&union_name=" + Base64.encodeToString(Util.getBytesUTF8(string2), 2));
            String string3 = bundle.getString(GAME_ZONE_ID);
            if (TextUtils.isEmpty(string3)) {
                Toast.makeText(activity, "游戏区域ID为空", 0).show();
                f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game zone id  is empty.");
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
                return;
            }
            stringBuffer.append("&zoneid=" + Base64.encodeToString(Util.getBytesUTF8(string3), 2));
            String string4 = bundle.getString(GAME_SIGNATURE);
            if (TextUtils.isEmpty(string4)) {
                Toast.makeText(activity, "游戏签名为空", 0).show();
                f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game signature is empty.");
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
                return;
            }
            stringBuffer.append("&signature=" + Base64.encodeToString(Util.getBytesUTF8(string4), 2));
            String openId = this.mToken.getOpenId();
            if (!TextUtils.isEmpty(openId)) {
                stringBuffer.append("&openid=" + Base64.encodeToString(Util.getBytesUTF8(openId), 2));
                Bundle composeActivityParams = composeActivityParams();
                for (String str : composeActivityParams.keySet()) {
                    composeActivityParams.putString(str, Base64.encodeToString(Util.getBytesUTF8(composeActivityParams.getString(str)), 2));
                }
                stringBuffer.append(a.b + HttpUtils.encodeUrl(composeActivityParams));
                f.a("openSDK_LOG.GameAppOperation", "-->bindQQGroup, url: " + stringBuffer.toString());
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(stringBuffer.toString()));
                if (!hasActivityForIntent(intent) || Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_5_1_0)) {
                    f.d("openSDK_LOG.GameAppOperation", "-->bind group, there is no activity, show download page.");
                    d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
                    a(activity);
                } else {
                    f.c("openSDK_LOG.GameAppOperation", "-->bingQQGroup target activity found, qqver > 5.1.0");
                    try {
                        activity.startActivity(intent);
                        d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "0");
                    } catch (Exception e) {
                        f.b("openSDK_LOG.GameAppOperation", "-->bind group, start activity exception.", e);
                        d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
                        a(activity);
                    }
                }
                f.c("openSDK_LOG.GameAppOperation", "-->bindQQGroup()  -- end");
                return;
            }
            Toast.makeText(activity, "Openid为空", 0).show();
            f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, openid is empty.");
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
        }
    }

    public void addToQQFavorites(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c("openSDK_LOG.GameAppOperation", "addToQQFavorites() -- start");
        int i = bundle.getInt("req_type", 1);
        if (!a(activity, bundle, iUiListener)) {
            f.e("openSDK_LOG.GameAppOperation", "-->addToQQFavorites check parames failed");
            a(Constants.VIA_REPORT_TYPE_QQFAVORITES, i, "1");
            return;
        }
        StringBuffer stringBuffer = new StringBuffer("mqqapi://share/to_qqfav?src_type=app&version=1&file_type=news");
        String string = bundle.getString(QQFAV_DATALINE_IMAGEURL);
        String string2 = bundle.getString("title");
        String string3 = bundle.getString("description");
        String string4 = bundle.getString("url");
        String string5 = bundle.getString(QQFAV_DATALINE_AUDIOURL);
        String applicationLable = Util.getApplicationLable(activity);
        String string6 = applicationLable == null ? bundle.getString(QQFAV_DATALINE_APPNAME) : applicationLable;
        ArrayList<String> stringArrayList = bundle.getStringArrayList(QQFAV_DATALINE_FILEDATA);
        String appId = this.mToken.getAppId();
        String openId = this.mToken.getOpenId();
        f.a("openSDK_LOG.GameAppOperation", "addToQQFavorites openId:" + openId);
        if (!TextUtils.isEmpty(string)) {
            stringBuffer.append("&image_url=" + Base64.encodeToString(Util.getBytesUTF8(string), 2));
        }
        if (stringArrayList != null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            int size = stringArrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                try {
                    stringBuffer2.append(URLEncoder.encode(stringArrayList.get(i2).trim(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    f.b("openSDK_LOG.GameAppOperation", "UnsupportedEncodingException", e);
                    stringBuffer2.append(URLEncoder.encode(stringArrayList.get(i2).trim()));
                }
                if (i2 != size - 1) {
                    stringBuffer2.append(h.b);
                }
            }
            stringBuffer.append("&file_data=" + Base64.encodeToString(Util.getBytesUTF8(stringBuffer2.toString()), 2));
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append("&title=" + Base64.encodeToString(Util.getBytesUTF8(string2), 2));
        }
        if (!TextUtils.isEmpty(string3)) {
            stringBuffer.append("&description=" + Base64.encodeToString(Util.getBytesUTF8(string3), 2));
        }
        if (!TextUtils.isEmpty(appId)) {
            stringBuffer.append("&share_id=" + appId);
        }
        if (!TextUtils.isEmpty(string4)) {
            stringBuffer.append("&url=" + Base64.encodeToString(Util.getBytesUTF8(string4), 2));
        }
        if (!TextUtils.isEmpty(string6)) {
            if (string6.length() > 20) {
                string6 = string6.substring(0, 20) + "...";
            }
            stringBuffer.append("&app_name=" + Base64.encodeToString(Util.getBytesUTF8(string6), 2));
        }
        if (!TextUtils.isEmpty(openId)) {
            stringBuffer.append("&open_id=" + Base64.encodeToString(Util.getBytesUTF8(openId), 2));
        }
        if (!TextUtils.isEmpty(string5)) {
            stringBuffer.append("&audioUrl=" + Base64.encodeToString(Util.getBytesUTF8(string5), 2));
        }
        stringBuffer.append("&req_type=" + Base64.encodeToString(Util.getBytesUTF8(String.valueOf(i)), 2));
        f.a("openSDK_LOG.GameAppOperation", "addToQQFavorites url: " + stringBuffer.toString());
        com.tencent.connect.a.a.a(Global.getContext(), this.mToken, "requireApi", SystemUtils.QQFAVORITES_CALLBACK_ACTION);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(stringBuffer.toString()));
        intent.putExtra("pkg_name", activity.getPackageName());
        if (UIListenerManager.getInstance().setListnerWithAction(SystemUtils.QQFAVORITES_CALLBACK_ACTION, iUiListener) != null) {
        }
        if (!hasActivityForIntent(intent) || Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_5_2_0)) {
            f.d("openSDK_LOG.GameAppOperation", "-->addToQQFavorites, there is no activity, show download page.");
            a(Constants.VIA_REPORT_TYPE_QQFAVORITES, i, "1");
            a(activity);
        } else {
            try {
                activity.startActivity(intent);
                a(Constants.VIA_REPORT_TYPE_QQFAVORITES, i, "0");
            } catch (Exception e2) {
                f.b("openSDK_LOG.GameAppOperation", "-->addToQQFavorites, start activity exception.", e2);
                a(Constants.VIA_REPORT_TYPE_QQFAVORITES, i, "1");
                a(activity);
            }
        }
        f.c("openSDK_LOG.GameAppOperation", "addToQQFavorites() --end");
    }

    public void sendToMyComputer(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c("openSDK_LOG.GameAppOperation", "sendToMyComputer() --start");
        int i = bundle.getInt("req_type", 1);
        if (!a(activity, bundle, iUiListener)) {
            f.e("openSDK_LOG.GameAppOperation", "-->sendToMyComputer check parames failed");
            a(Constants.VIA_REPORT_TYPE_DATALINE, i, "1");
            return;
        }
        StringBuffer stringBuffer = new StringBuffer("mqqapi://share/to_qqdataline?src_type=app&version=1&file_type=news");
        String string = bundle.getString(QQFAV_DATALINE_IMAGEURL);
        String string2 = bundle.getString("title");
        String string3 = bundle.getString("description");
        String string4 = bundle.getString("url");
        String string5 = bundle.getString(QQFAV_DATALINE_AUDIOURL);
        String applicationLable = Util.getApplicationLable(activity);
        String string6 = applicationLable == null ? bundle.getString(QQFAV_DATALINE_APPNAME) : applicationLable;
        ArrayList<String> stringArrayList = bundle.getStringArrayList(QQFAV_DATALINE_FILEDATA);
        String appId = this.mToken.getAppId();
        String openId = this.mToken.getOpenId();
        f.a("openSDK_LOG.GameAppOperation", "openId:" + openId);
        if (!TextUtils.isEmpty(string)) {
            stringBuffer.append("&image_url=" + Base64.encodeToString(Util.getBytesUTF8(string), 2));
        }
        if (stringArrayList != null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            int size = stringArrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                try {
                    stringBuffer2.append(URLEncoder.encode(stringArrayList.get(i2).trim(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    f.b("openSDK_LOG.GameAppOperation", "UnsupportedEncodingException", e);
                    stringBuffer2.append(URLEncoder.encode(stringArrayList.get(i2).trim()));
                }
                if (i2 != size - 1) {
                    stringBuffer2.append(h.b);
                }
            }
            stringBuffer.append("&file_data=" + Base64.encodeToString(Util.getBytesUTF8(stringBuffer2.toString()), 2));
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append("&title=" + Base64.encodeToString(Util.getBytesUTF8(string2), 2));
        }
        if (!TextUtils.isEmpty(string3)) {
            stringBuffer.append("&description=" + Base64.encodeToString(Util.getBytesUTF8(string3), 2));
        }
        if (!TextUtils.isEmpty(appId)) {
            stringBuffer.append("&share_id=" + appId);
        }
        if (!TextUtils.isEmpty(string4)) {
            stringBuffer.append("&url=" + Base64.encodeToString(Util.getBytesUTF8(string4), 2));
        }
        if (!TextUtils.isEmpty(string6)) {
            if (string6.length() > 20) {
                string6 = string6.substring(0, 20) + "...";
            }
            stringBuffer.append("&app_name=" + Base64.encodeToString(Util.getBytesUTF8(string6), 2));
        }
        if (!TextUtils.isEmpty(openId)) {
            stringBuffer.append("&open_id=" + Base64.encodeToString(Util.getBytesUTF8(openId), 2));
        }
        if (!TextUtils.isEmpty(string5)) {
            stringBuffer.append("&audioUrl=" + Base64.encodeToString(Util.getBytesUTF8(string5), 2));
        }
        stringBuffer.append("&req_type=" + Base64.encodeToString(Util.getBytesUTF8(String.valueOf(i)), 2));
        f.a("openSDK_LOG.GameAppOperation", "sendToMyComputer url: " + stringBuffer.toString());
        com.tencent.connect.a.a.a(Global.getContext(), this.mToken, "requireApi", SystemUtils.QQDATALINE_CALLBACK_ACTION);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(stringBuffer.toString()));
        intent.putExtra("pkg_name", activity.getPackageName());
        if (UIListenerManager.getInstance().setListnerWithAction(SystemUtils.QQDATALINE_CALLBACK_ACTION, iUiListener) != null) {
        }
        if (!hasActivityForIntent(intent) || Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_5_2_0)) {
            f.d("openSDK_LOG.GameAppOperation", "-->sendToMyComputer, there is no activity, show download page.");
            a(Constants.VIA_REPORT_TYPE_DATALINE, i, "1");
            a(activity);
        } else {
            try {
                startAssistActivity(activity, Constants.REQUEST_SEND_TO_MY_COMPUTER, intent, false);
                a(Constants.VIA_REPORT_TYPE_DATALINE, i, "0");
            } catch (Exception e2) {
                f.b("openSDK_LOG.GameAppOperation", "-->sendToMyComputer, start activity exception.", e2);
                a(Constants.VIA_REPORT_TYPE_DATALINE, i, "1");
                a(activity);
            }
        }
        f.c("openSDK_LOG.GameAppOperation", "sendToMyComputer() --end");
    }

    public void shareToTroopBar(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- start");
        if (iUiListener == null) {
            f.e("openSDK_LOG.GameAppOperation", "listener is null!");
        } else if (activity == null || bundle == null) {
            f.e("openSDK_LOG.GameAppOperation", "activity or params is null!");
            iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "activity or params is null!"));
        } else {
            String string = bundle.getString("title");
            if (TextUtils.isEmpty(string)) {
                iUiListener.onError(new UiError(-5, "传入参数不可以为空: title is null", null));
                f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- title is null");
            } else if (string.length() < 4 || string.length() > 25) {
                iUiListener.onError(new UiError(-5, "传入参数有误!: title size: 4 ~ 25", null));
                f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- title size: 4 ~ 25");
            } else {
                String string2 = bundle.getString("description");
                if (TextUtils.isEmpty(string2)) {
                    iUiListener.onError(new UiError(-5, "传入参数不可以为空: description is null", null));
                    f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- description is null");
                } else if (string2.length() < 10 || string2.length() > 700) {
                    iUiListener.onError(new UiError(-5, "传入参数有误!: description size: 10 ~ 700", null));
                    f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- description size: 10 ~ 700");
                } else {
                    ArrayList<String> stringArrayList = bundle.getStringArrayList(QQFAV_DATALINE_FILEDATA);
                    StringBuffer stringBuffer = new StringBuffer();
                    if (stringArrayList != null && stringArrayList.size() > 0) {
                        int size = stringArrayList.size();
                        if (size > 9) {
                            iUiListener.onError(new UiError(-5, "传入参数有误!: file_data size: 1 ~ 9", null));
                            f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- file_data size: 1 ~ 9");
                            return;
                        }
                        for (int i = 0; i < size; i++) {
                            String trim = stringArrayList.get(i).trim();
                            if (!trim.startsWith("/")) {
                                iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_ERROR, "file_data应该为本地图片"));
                                f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar(): file_data应该为本地图片");
                                return;
                            } else if (trim.startsWith("/") && !new File(trim).exists()) {
                                iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_ERROR, "图片文件不存在"));
                                f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar(): 图片文件不存在");
                                return;
                            }
                        }
                        for (int i2 = 0; i2 < size; i2++) {
                            try {
                                stringBuffer.append(URLEncoder.encode(stringArrayList.get(i2).trim(), "UTF-8"));
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                                f.b("openSDK_LOG.GameAppOperation", "UnsupportedEncodingException: ", e);
                                stringBuffer.append(URLEncoder.encode(stringArrayList.get(i2).trim()));
                            }
                            if (i2 != size - 1) {
                                stringBuffer.append(h.b);
                            }
                        }
                    }
                    String string3 = bundle.getString(TROOPBAR_ID);
                    if (TextUtils.isEmpty(string3) || Util.isNumeric(string3)) {
                        StringBuffer stringBuffer2 = new StringBuffer("mqqapi://share/to_troopbar?src_type=app&version=1&file_type=news");
                        String appId = this.mToken.getAppId();
                        String openId = this.mToken.getOpenId();
                        f.a("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- openId: " + openId);
                        String applicationLable = Util.getApplicationLable(activity);
                        if (!TextUtils.isEmpty(appId)) {
                            stringBuffer2.append("&share_id=" + appId);
                        }
                        if (!TextUtils.isEmpty(openId)) {
                            stringBuffer2.append("&open_id=" + Base64.encodeToString(Util.getBytesUTF8(openId), 2));
                        }
                        if (!TextUtils.isEmpty(applicationLable)) {
                            if (applicationLable.length() > 20) {
                                applicationLable = applicationLable.substring(0, 20) + "...";
                            }
                            stringBuffer2.append("&app_name=" + Base64.encodeToString(Util.getBytesUTF8(applicationLable), 2));
                        }
                        if (!TextUtils.isEmpty(string)) {
                            stringBuffer2.append("&title=" + Base64.encodeToString(Util.getBytesUTF8(string), 2));
                        }
                        if (!TextUtils.isEmpty(string2)) {
                            stringBuffer2.append("&description=" + Base64.encodeToString(Util.getBytesUTF8(string2), 2));
                        }
                        if (!TextUtils.isEmpty(string3)) {
                            stringBuffer2.append("&troopbar_id=" + Base64.encodeToString(Util.getBytesUTF8(string3), 2));
                        }
                        if (!TextUtils.isEmpty(stringBuffer)) {
                            stringBuffer2.append("&file_data=" + Base64.encodeToString(Util.getBytesUTF8(stringBuffer.toString()), 2));
                        }
                        f.a("openSDK_LOG.GameAppOperation", "shareToTroopBar, url: " + stringBuffer2.toString());
                        com.tencent.connect.a.a.a(Global.getContext(), this.mToken, "requireApi", SystemUtils.TROOPBAR_CALLBACK_ACTION);
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse(stringBuffer2.toString()));
                        String packageName = activity.getPackageName();
                        if (!TextUtils.isEmpty(packageName)) {
                            intent.putExtra("pkg_name", packageName);
                        }
                        if (UIListenerManager.getInstance().setListnerWithAction(SystemUtils.TROOPBAR_CALLBACK_ACTION, iUiListener) != null) {
                        }
                        if (!hasActivityForIntent(intent) || Util.isQQVersionBelow(activity, SystemUtils.QQ_VERSION_NAME_5_3_0)) {
                            f.d("openSDK_LOG.GameAppOperation", "-->shareToTroopBar, there is no activity, show download page.");
                            a(activity, SystemUtils.QQ_VERSION_NAME_5_3_0);
                            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_TROOPBAR, Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR, "18", "1");
                        } else {
                            try {
                                startAssistActivity(activity, Constants.REQUEST_SHARE_TO_TROOP_BAR, intent, false);
                                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_TROOPBAR, Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR, "18", "0");
                            } catch (Exception e2) {
                                f.b("openSDK_LOG.GameAppOperation", "-->shareToTroopBar, start activity exception.", e2);
                                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_TROOPBAR, Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR, "18", "1");
                                a(activity, SystemUtils.QQ_VERSION_NAME_5_3_0);
                            }
                        }
                        f.c("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- end");
                        return;
                    }
                    iUiListener.onError(new UiError(-6, "传入参数有误! troopbar_id 必须为数字", null));
                    f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar(): troopbar_id 必须为数字");
                }
            }
        }
    }

    public void sharePrizeToQQ(final Activity activity, Bundle bundle, final IUiListener iUiListener) {
        f.c("openSDK_LOG.GameAppOperation", "sharePrizeToQQ() -- start");
        if (iUiListener == null) {
            f.e("openSDK_LOG.GameAppOperation", "listener is null!");
        } else if (activity == null || bundle == null) {
            f.e("openSDK_LOG.GameAppOperation", "activity or params is null!");
            iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "activity or params is null!"));
        } else {
            String string = bundle.getString("title");
            if (TextUtils.isEmpty(string)) {
                f.e("openSDK_LOG.GameAppOperation", "sharePrizeToQQ failed, title is empty.");
                iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "sharePrizeToQQ failed, title is empty."));
                return;
            }
            String string2 = bundle.getString("summary");
            if (TextUtils.isEmpty(string2)) {
                f.e("openSDK_LOG.GameAppOperation", "sharePrizeToQQ failed, sumary is empty.");
                iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "sharePrizeToQQ failed, sumary is empty."));
                return;
            }
            String string3 = bundle.getString("imageUrl");
            if (TextUtils.isEmpty(string3) || (!string3.startsWith("http://") && !string3.startsWith("https://"))) {
                f.e("openSDK_LOG.GameAppOperation", "sharePrizeToQQ failed, imageUrl is empty or illegal.");
                iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "sharePrizeToQQ failed, imageUrl is empty or illegal."));
                return;
            }
            final String string4 = bundle.getString(SHARE_PRIZE_ACTIVITY_ID);
            if (TextUtils.isEmpty(string4)) {
                f.e("openSDK_LOG.GameAppOperation", "sharePrizeToQQ failed, activityId is empty.");
                iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "sharePrizeToQQ failed, activityId is empty."));
                return;
            }
            final Bundle bundle2 = new Bundle();
            bundle2.putString("title", string);
            bundle2.putString("summary", string2);
            bundle2.putString("imageUrl", string3);
            bundle2.putInt("req_type", 1);
            ThreadManager.executeOnSubThread(new Runnable() { // from class: com.tencent.open.GameAppOperation.1
                @Override // java.lang.Runnable
                public void run() {
                    Bundle a = GameAppOperation.this.a();
                    if (a == null) {
                        f.e("openSDK_LOG.GameAppOperation", "accesstoken or openid or appid is null, please login first!");
                        iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "accesstoken or openid or appid is null, please login first!"));
                        return;
                    }
                    a.putString(GameAppOperation.SHARE_PRIZE_ACTIVITY_ID, string4);
                    try {
                        JSONObject request = HttpUtils.request(GameAppOperation.this.mToken, activity.getApplicationContext(), ServerSetting.URL_PRIZE_MAKE_SHARE_URL, a, "GET");
                        try {
                            int i = request.getInt("ret");
                            int i2 = request.getInt("subCode");
                            if (i == 0 && i2 == 0) {
                                bundle2.putString("targetUrl", request.getString("share_url"));
                                new QQShare(activity.getApplicationContext(), GameAppOperation.this.mToken).shareToQQ(activity, bundle2, iUiListener);
                            } else {
                                String string5 = request.getString("msg");
                                iUiListener.onError(new UiError(i, "make_share_url error.", string5));
                                f.c("openSDK_LOG.GameAppOperation", "code = " + i + ", subcode = errormsg = " + string5);
                            }
                        } catch (JSONException e) {
                            f.e("openSDK_LOG.GameAppOperation", "JSONException occur in make_share_url, errorMsg: " + e.getMessage());
                            iUiListener.onError(new UiError(-4, Constants.MSG_JSON_ERROR, ""));
                        }
                    } catch (Exception e2) {
                        f.b("openSDK_LOG.GameAppOperation", "Exception occur in make_share_url", e2);
                        iUiListener.onError(new UiError(-2, Constants.MSG_IO_ERROR, e2.getMessage()));
                    }
                }
            });
            f.c("openSDK_LOG.GameAppOperation", "sharePrizeToQQ() -- end");
        }
    }

    public void queryUnexchangePrize(final Context context, final Bundle bundle, final IUiListener iUiListener) {
        if (iUiListener == null) {
            f.e("openSDK_LOG.GameAppOperation", "listener is null!");
        } else if (bundle == null) {
            f.e("openSDK_LOG.GameAppOperation", "params is null!");
            iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "params is null!"));
        } else if (this.mToken == null || !this.mToken.isSessionValid()) {
            f.e("openSDK_LOG.GameAppOperation", "queryUnexchangePrize failed, auth token is illegal.");
            iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "queryUnexchangePrize failed, auth token is illegal."));
        } else if (context == null && Global.getContext() == null) {
            f.e("openSDK_LOG.GameAppOperation", "queryUnexchangePrize failed, context is null.");
            iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "queryUnexchangePrize failed, context is null."));
        } else if (TextUtils.isEmpty(bundle.getString(SHARE_PRIZE_ACTIVITY_ID))) {
            f.e("openSDK_LOG.GameAppOperation", "queryUnexchangePrize failed, activityId is empty.");
            iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "queryUnexchangePrize failed, activityId is empty."));
        } else {
            if (context == null) {
                context = Global.getContext();
            }
            ThreadManager.executeOnSubThread(new Runnable() { // from class: com.tencent.open.GameAppOperation.2
                @Override // java.lang.Runnable
                public void run() {
                    Bundle a = GameAppOperation.this.a();
                    if (a == null) {
                        f.e("openSDK_LOG.GameAppOperation", "accesstoken or openid or appid is null, please login first!");
                        iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "accesstoken or openid or appid is null, please login first!"));
                        return;
                    }
                    a.putAll(bundle);
                    try {
                        iUiListener.onComplete(HttpUtils.request(GameAppOperation.this.mToken, context, ServerSetting.URL_PRIZE_QUERY_UNEXCHANGE, a, "GET"));
                    } catch (Exception e) {
                        f.b("openSDK_LOG.GameAppOperation", "Exception occur in queryUnexchangePrize", e);
                        iUiListener.onError(new UiError(-2, Constants.MSG_IO_ERROR, e.getMessage()));
                    }
                }
            });
        }
    }

    public void exchangePrize(final Context context, Bundle bundle, final IUiListener iUiListener) {
        if (iUiListener == null) {
            f.e("openSDK_LOG.GameAppOperation", "exchangePrize listener is null!");
        } else if (bundle == null) {
            f.e("openSDK_LOG.GameAppOperation", "exchangePrize params is null!");
            iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "exchangePrize params is null!"));
        } else if (this.mToken == null || !this.mToken.isSessionValid()) {
            f.e("openSDK_LOG.GameAppOperation", "exchangePrize failed, auth token is illegal.");
            iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "exchangePrize failed, auth token is illegal."));
        } else if (context == null && Global.getContext() == null) {
            f.e("openSDK_LOG.GameAppOperation", "exchangePrize failed, context is null.");
            iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "exchangePrize failed, context is null."));
        } else {
            ArrayList<String> stringArrayList = bundle.getStringArrayList(SHARE_PRIZE_SHARE_ID_LIST);
            if (stringArrayList == null) {
                f.e("openSDK_LOG.GameAppOperation", "exchangePrize failed, shareid_list is empty.");
                iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "exchangePrize failed, shareid_list is empty."));
                return;
            }
            final StringBuffer stringBuffer = new StringBuffer("");
            int size = stringArrayList.size();
            for (int i = 0; i < size; i++) {
                String str = stringArrayList.get(i);
                if (!TextUtils.isEmpty(str)) {
                    stringBuffer.append(str);
                    if (i < size - 1) {
                        stringBuffer.append(",");
                    }
                }
            }
            if (context == null) {
                context = Global.getContext();
            }
            ThreadManager.executeOnSubThread(new Runnable() { // from class: com.tencent.open.GameAppOperation.3
                @Override // java.lang.Runnable
                public void run() {
                    Bundle a = GameAppOperation.this.a();
                    if (a == null) {
                        f.e("openSDK_LOG.GameAppOperation", "accesstoken or openid or appid is null, please login first!");
                        iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "accesstoken or openid or appid is null, please login first!"));
                        return;
                    }
                    a.putString(GameAppOperation.SHARE_PRIZE_SHARE_ID, stringBuffer.toString());
                    a.putString("imei", c.b(Global.getContext()));
                    try {
                        iUiListener.onComplete(HttpUtils.request(GameAppOperation.this.mToken, context, ServerSetting.URL_PRIZE_EXCHANGE, a, "GET"));
                    } catch (Exception e) {
                        f.b("openSDK_LOG.GameAppOperation", "Exception occur in exchangePrize", e);
                        iUiListener.onError(new UiError(-2, Constants.MSG_IO_ERROR, e.getMessage()));
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle a() {
        if (this.mToken == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        String appId = this.mToken.getAppId();
        String openId = this.mToken.getOpenId();
        String accessToken = this.mToken.getAccessToken();
        if (TextUtils.isEmpty(appId) || TextUtils.isEmpty(openId) || TextUtils.isEmpty(accessToken)) {
            f.e("openSDK_LOG.GameAppOperation", "composeLoginStateParams fail, accesstoken or openid or appid is null");
            return null;
        }
        bundle.putString("appid", this.mToken.getAppId());
        bundle.putString("openid", this.mToken.getOpenId());
        bundle.putString("accesstoken", this.mToken.getAccessToken());
        return bundle;
    }

    public void isActivityAvailable(final Activity activity, final String str, final IUiListener iUiListener) {
        if (TextUtils.isEmpty(str)) {
            f.e("openSDK_LOG.GameAppOperation", "isActivityAvailable failed, activityId is null.");
            iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "isActivityAvailable failed, activityId is null."));
        } else if (this.mToken == null || !this.mToken.isSessionValid()) {
            f.e("openSDK_LOG.GameAppOperation", "exchangePrize failed, auth token is illegal.");
            iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "exchangePrize failed, auth token is illegal."));
        } else {
            ThreadManager.executeOnSubThread(new Runnable() { // from class: com.tencent.open.GameAppOperation.4
                @Override // java.lang.Runnable
                public void run() {
                    Bundle a = GameAppOperation.this.a();
                    if (a == null) {
                        f.e("openSDK_LOG.GameAppOperation", "accesstoken or openid or appid is null, please login first!");
                        iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, "accesstoken or openid or appid is null, please login first!"));
                        return;
                    }
                    a.putString(GameAppOperation.SHARE_PRIZE_ACTIVITY_ID, str);
                    try {
                        iUiListener.onComplete(HttpUtils.request(GameAppOperation.this.mToken, activity.getApplicationContext(), ServerSetting.URL_PRIZE_GET_ACTIVITY_STATE, a, "GET"));
                    } catch (Exception e) {
                        f.b("openSDK_LOG.GameAppOperation", "Exception occur in make_share_url", e);
                        iUiListener.onError(new UiError(-6, "Exception occur in make_share_url", e.getMessage()));
                    }
                }
            });
        }
    }

    private boolean a(Activity activity, Bundle bundle, IUiListener iUiListener) {
        if (activity == null || bundle == null || iUiListener == null) {
            f.e("openSDK_LOG.GameAppOperation", "activity or params or listener is null!");
            return false;
        }
        int i = bundle.getInt("req_type", 1);
        if (TextUtils.isEmpty(bundle.getString(QQFAV_DATALINE_APPNAME))) {
            iUiListener.onError(new UiError(-5, "传入参数不可以为空: app_name", null));
            return false;
        }
        String string = bundle.getString("description");
        String string2 = bundle.getString("url");
        String string3 = bundle.getString(QQFAV_DATALINE_AUDIOURL);
        String string4 = bundle.getString(QQFAV_DATALINE_IMAGEURL);
        ArrayList<String> stringArrayList = bundle.getStringArrayList(QQFAV_DATALINE_FILEDATA);
        switch (i) {
            case 1:
                if (TextUtils.isEmpty(string2) || TextUtils.isEmpty(string4)) {
                    iUiListener.onError(new UiError(-5, "传入参数不可以为空: image_url or url is null", null));
                    return false;
                }
                break;
            case 2:
                if (TextUtils.isEmpty(string2) || TextUtils.isEmpty(string4) || TextUtils.isEmpty(string3)) {
                    iUiListener.onError(new UiError(-5, "传入参数不可以为空: image_url or url or audioUrl is null", null));
                    return false;
                }
                break;
            case 3:
            case 4:
            default:
                iUiListener.onError(new UiError(-5, "传入参数有误!: unknow req_type", null));
                return false;
            case 5:
                if (stringArrayList != null && stringArrayList.size() != 0) {
                    int size = stringArrayList.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        String trim = stringArrayList.get(i2).trim();
                        if (trim.startsWith("/") && !new File(trim).exists()) {
                            iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, null));
                            return false;
                        }
                    }
                    break;
                } else {
                    iUiListener.onError(new UiError(-5, "传入参数不可以为空: fill_data is null", null));
                    return false;
                }
                break;
            case 6:
                if (TextUtils.isEmpty(string)) {
                    iUiListener.onError(new UiError(-5, "传入参数不可以为空: description is null", null));
                    return false;
                }
                break;
        }
        return true;
    }

    @Override // com.tencent.connect.common.BaseApi
    public void releaseResource() {
        f.c("openSDK_LOG.GameAppOperation", "releaseResource() -- start");
        f.c("openSDK_LOG.GameAppOperation", "releaseResource() -- end");
    }

    private void a(Activity activity) {
        a(activity, "");
    }

    private void a(Activity activity, String str) {
        new TDialog(activity, "", getCommonDownloadQQUrl(str), null, this.mToken).show();
    }

    private void a(String str, int i, String str2) {
        String str3;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            f.a("openSDK_LOG.GameAppOperation", "reportForVia() error: reportType or result is null");
            return;
        }
        switch (i) {
            case 1:
                str3 = "6";
                break;
            case 2:
                str3 = "3";
                break;
            case 3:
            case 4:
            default:
                f.e("openSDK_LOG.GameAppOperation", "GameAppOperation -- reportForVia() error: unknow type " + String.valueOf(i));
                return;
            case 5:
                str3 = "1";
                break;
            case 6:
                str3 = "5";
                break;
        }
        d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), "2", str, Constants.VIA_ACT_TYPE_TWENTY_EIGHT, str2, str3, "0", "", "");
    }
}

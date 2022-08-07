package com.cdlinglu.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import com.alipay.sdk.sys.a;
import com.em.DemoHelper;
import com.hy.frame.common.BaseApplication;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.Constant;
import com.hy.frame.util.FolderUtil;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.hyphenate.util.HanziToPinyin;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.UserUploadBean;
import com.vsf2f.f2f.ui.dialog.WarnDialog;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpHost;

/* loaded from: classes.dex */
public class ComUtil {
    public static final String KEY = "75cba6dd6d4d75154624dbe9d770d451";
    public static final String PAYKEY = "09ebcc6549b6fad41e0b857459c5b2be";
    public static final String UPLOAND_TOKEN = "TOKEN";
    private static BitmapUtils utils;

    public static String getString(Context context, int resId) {
        return context.getString(resId);
    }

    public static DbUtils getDb(Context context) {
        return DbUtils.create(context, getString(context, R.string.DB_NAME));
    }

    public static boolean checkContent(String target, String regex) {
        if (TextUtils.isEmpty(target)) {
            return true;
        }
        return Pattern.compile(regex).matcher(target).matches();
    }

    public static String addSpaceToUrl(String span) {
        Matcher match = Pattern.compile("(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-Z0-9\\.&amp;%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&amp;%\\$#\\=~_\\-@]*)*").matcher(span);
        while (match.find()) {
            String sss = match.group(0);
            span.replace(sss, sss + HanziToPinyin.Token.SEPARATOR);
        }
        return span;
    }

    public static String getCom_Url(Context context) {
        return context.getString(R.string.URL_REDIRECT, context.getString(R.string.API_HOST));
    }

    public static String getZCApi(Context context, String path) {
        return checkPath(getString(context, R.string.API_HOST), path);
    }

    public static String getF2FApi(Context context, String path) {
        return checkPath(getString(context, R.string.API_HOST_F2F), path);
    }

    public static String getXDDApi(Context context, String path) {
        return checkPath(getString(context, R.string.API_HOST_XDD), path);
    }

    public static String getGAMEApi(Context context, String path) {
        return checkPath(getString(context, R.string.API_HOST_GAME), path);
    }

    private static String checkPath(String host, String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        if (path.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
            return path;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(host);
        if (!host.endsWith("/") && !path.startsWith("/")) {
            sb.append("/");
        }
        sb.append(path);
        return sb.toString();
    }

    public static String getColorText(String str, String color) {
        return "<font color='" + color + "'>" + str + "</font>";
    }

    public static void setNetStatus(Context context, int status) {
        AppShare.get(context).putInt(Constant.NET_STATUS, status);
    }

    public static boolean isNetworkConnected(Context context) {
        return AppShare.get(context).getInt(Constant.NET_STATUS) > -1;
    }

    public static boolean checkUserName(Context context, String phone) {
        if (TextUtils.isEmpty(phone)) {
            MyToast.show(context, "账号不能为空");
        } else if (HyUtil.isMobile(phone) || HyUtil.isEmail(phone)) {
            return true;
        } else {
            MyToast.show(context, "请输入正确的手机号码或邮箱");
        }
        return false;
    }

    public static boolean checkIDcard(Context context, String text) {
        return text.matches("[0-9]{17}x") || text.matches("[0-9]{15}") || text.matches("[0-9]{18}");
    }

    public static boolean checkUserPwd(Context context, String pwd) {
        if (TextUtils.isEmpty(pwd)) {
            MyToast.show(context, "密码不能为空");
        } else if (pwd.length() >= 6 && pwd.length() <= 16) {
            return true;
        } else {
            MyToast.show(context, "密码为6-16位字母或数字");
        }
        return false;
    }

    public static boolean checkChinese(String chinese) {
        return Pattern.matches("^[一-龥]+$", chinese);
    }

    public static void displayImage(Context context, ImageView imageView, String url) {
        displayImage(context, imageView, url, 0, 0);
    }

    public static void displayImage(Context context, ImageView imageView, String url, int width, int height) {
        display(context, imageView, url, R.drawable.img_empty);
    }

    public static void displayHead(Context context, ImageView imageView, String url) {
        displayHead(context, imageView, url, 0, 0);
    }

    public static void displayHead(Context context, ImageView imageView, String url, int width, int height) {
        display(context, imageView, url, R.mipmap.def_head2);
    }

    public static void display(Context context, ImageView imageView, String url, int fail) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(fail);
            return;
        }
        if (utils == null) {
            utils = new BitmapUtils(context, FolderUtil.getCachePathAlbum(), 100, 256);
        }
        utils.configDefaultLoadingImage(fail);
        utils.configDefaultLoadFailedImage(fail);
        String url2 = getZCApi(context, url);
        MyLog.i("display", url2);
        utils.display(imageView, url2);
    }

    public static boolean isNoLogin(Context context) {
        return !isLogin(context);
    }

    public static boolean isLogin(Context context) {
        return HyUtil.isNoEmpty(AppShare.get(context).getString(Constant.USER_TOKEN));
    }

    public static void upLoadToken(BaseApplication app, UserUploadBean upload) {
        if (upload != null) {
            app.putValue(Constant.FLAG, true);
            app.putValue(UPLOAND_TOKEN, upload);
        }
    }

    public static String getUserToken(Context context) {
        return AppShare.get(context.getApplicationContext()).getString(Constant.USER_TOKEN);
    }

    public static void loginOut(Context context) {
        AppShare.get(context).putString(Constant.USER_TOKEN, null);
    }

    public static String searchSign(Context context) {
        if (getUserToken(context) == null) {
            MyToast.show(context, (int) R.string.login_hint);
            return "";
        }
        String vs_access_token = getUserToken(context);
        String vs_nonce_str = getRandomString(32);
        String timestamp = System.currentTimeMillis() + "";
        AjaxParams params1 = new AjaxParams();
        params1.put("vs_access_token", vs_access_token);
        params1.put("vs_nonce_str", vs_nonce_str);
        params1.put("vs_timestamp", System.currentTimeMillis() - AppShare.get(context).getLong("diffTime"));
        return "vs_access_token=" + vs_access_token + "&vs_request_token=" + encryptParam(params1) + "&vs_timestamp=" + timestamp + "&vs_nonce_str=" + vs_nonce_str;
    }

    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt(62)));
        }
        return sb.toString();
    }

    public static int getImageResourceId(String name) {
        return getImageResourceId(name, R.mipmap.def_head);
    }

    public static int getImageResourceId(String name, int defId) {
        try {
            Field field = R.drawable.class.getField(name);
            return field.getInt(field);
        } catch (Exception e) {
            e.printStackTrace();
            return defId;
        }
    }

    public static String encryptParam(AjaxParams params) {
        return encryptMap(params.getUrlParams(), false);
    }

    public static String encryptMap(Map<String, String> urlParams, boolean isPay) {
        if (urlParams == null) {
            return null;
        }
        List<String> paramsList = new ArrayList<>();
        for (Map.Entry<String, String> map : urlParams.entrySet()) {
            if (!TextUtils.isEmpty(map.getValue())) {
                paramsList.add(map.getKey() + "=" + map.getValue());
            }
        }
        String[] strs = new String[paramsList.size()];
        paramsList.toArray(strs);
        Arrays.sort(strs);
        StringBuilder sb = new StringBuilder();
        int length = strs.length;
        for (int i = 0; i < length; i++) {
            sb.append(strs[i] + a.b);
        }
        if (isPay) {
            sb.append("key=09ebcc6549b6fad41e0b857459c5b2be");
        } else {
            sb.append("key=75cba6dd6d4d75154624dbe9d770d451");
        }
        try {
            return MD5.md5Encode(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String dateTime(long now) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now);
        return formatter.format(calendar.getTime());
    }

    public static String UTF(String s) {
        if (TextUtils.isEmpty(s)) {
            return s;
        }
        String utf = "";
        try {
            utf = URLEncoder.encode(s, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return utf;
    }

    public static String getUTF(String s) {
        if (TextUtils.isEmpty(s)) {
            return s;
        }
        String utf = "";
        try {
            utf = URLDecoder.decode(s, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return utf;
    }

    public static SpannableStringBuilder getSpannableTextColor(String text, String keyworld) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        if (text.contains(keyworld)) {
            int spanStartIndex = text.indexOf(keyworld);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(-16776961), spanStartIndex, spanStartIndex + keyworld.length(), 34);
        }
        return spannableStringBuilder;
    }

    public static String getObjectKey(String userName, String path) {
        if (userName == null || path == null || userName.trim().length() <= 0 || path.trim().length() <= 0) {
            return null;
        }
        return userName + "/" + UUID.randomUUID() + path.substring(path.lastIndexOf("."));
    }

    public static String getVersion(Context context) {
        try {
            String version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            MyLog.e(version);
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "版本号未知";
        }
    }

    public static String getPaypwd(Context context) {
        return AppShare.get(context).getString("paypwd_" + DemoHelper.getInstance().getCurrentUserName());
    }

    public static void setPaypwd(Context context, String paypwd) {
        AppShare.get(context).putString("paypwd_" + DemoHelper.getInstance().getCurrentUserName(), paypwd);
    }

    public static boolean isFinger(Context context) {
        return AppShare.get(context).getBoolean("finger_" + DemoHelper.getInstance().getCurrentUserName());
    }

    public static void setFinger(Context context, boolean finger) {
        AppShare.get(context).putBoolean("finger_" + DemoHelper.getInstance().getCurrentUserName(), finger);
    }

    public static void copySys(Context context, String url) {
        ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text", url));
        MyToast.show(context, (int) R.string.copy_success);
    }

    public static boolean checkPermission(@NonNull Context context, @NonNull String permission) {
        return checkPermission(context, permission, "此");
    }

    public static boolean checkPermission(@NonNull Context context, @NonNull String permission, String perName) {
        if (ContextCompat.checkSelfPermission(context, permission) != 0) {
            return true;
        }
        new WarnDialog(context, String.format("获取系统权限失败，在设置-权限管理开启%s权限，以正常使用%s", perName, perName)).show();
        return false;
    }
}

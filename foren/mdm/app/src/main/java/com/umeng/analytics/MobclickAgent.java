package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.smtt.sdk.TbsListener;
import com.umeng.analytics.social.UMPlatformData;
import com.umeng.analytics.social.UMSocialService;
import com.umeng.analytics.social.e;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import u.aly.bo;

/* loaded from: classes2.dex */
public class MobclickAgent {
    private static final String a = "input map is null";
    private static final b b = new b();

    public static void startWithConfigure(UMAnalyticsConfig uMAnalyticsConfig) {
        if (uMAnalyticsConfig != null) {
            b.a(uMAnalyticsConfig);
        }
    }

    public static void setLocation(double d, double d2) {
        b.a(d, d2);
    }

    public static void setLatencyWindow(long j) {
        b.a(j);
    }

    public static void enableEncrypt(boolean z) {
        b.e(z);
    }

    public static void setCatchUncaughtExceptions(boolean z) {
        b.a(z);
    }

    public static void setSecret(Context context, String str) {
        b.b(context, str);
    }

    public static void setScenarioType(Context context, EScenarioType eScenarioType) {
        b.a(context, eScenarioType);
    }

    public static void setSessionContinueMillis(long j) {
        b.b(j);
    }

    public static b getAgent() {
        return b;
    }

    public static void setCheckDevice(boolean z) {
        b.c(z);
    }

    public static void setOpenGLContext(GL10 gl10) {
        b.a(gl10);
    }

    public static void openActivityDurationTrack(boolean z) {
        b.b(z);
    }

    public static void onPageStart(String str) {
        if (!TextUtils.isEmpty(str)) {
            b.a(str);
        } else {
            bo.e("pageName is null or empty");
        }
    }

    public static void onPageEnd(String str) {
        if (!TextUtils.isEmpty(str)) {
            b.b(str);
        } else {
            bo.e("pageName is null or empty");
        }
    }

    public static void setDebugMode(boolean z) {
        b.d(z);
    }

    public static void onPause(Context context) {
        b.b(context);
    }

    public static void onResume(Context context) {
        if (context == null) {
            bo.e("unexpected null context in onResume");
        } else {
            b.a(context);
        }
    }

    public static void reportError(Context context, String str) {
        b.a(context, str);
    }

    public static void reportError(Context context, Throwable th) {
        b.a(context, th);
    }

    public static void onEvent(Context context, List<String> list, int i, String str) {
        b.a(context, list, i, str);
    }

    public static void onEvent(Context context, String str) {
        b.a(context, str, null, -1L, 1);
    }

    public static void onEvent(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            bo.c("label is null or empty");
        } else {
            b.a(context, str, str2, -1L, 1);
        }
    }

    public static void onEvent(Context context, String str, Map<String, String> map) {
        if (map == null) {
            bo.e(a);
        } else {
            b.a(context, str, new HashMap(map), -1L);
        }
    }

    public static void onEventValue(Context context, String str, Map<String, String> map, int i) {
        HashMap hashMap;
        if (map == null) {
            hashMap = new HashMap();
        } else {
            hashMap = new HashMap(map);
        }
        hashMap.put("__ct__", Integer.valueOf(i));
        b.a(context, str, hashMap, -1L);
    }

    public static void onSocialEvent(Context context, String str, UMPlatformData... uMPlatformDataArr) {
        if (context == null) {
            bo.e("context is null in onShareEvent");
            return;
        }
        e.e = "3";
        UMSocialService.share(context, str, uMPlatformDataArr);
    }

    public static void onSocialEvent(Context context, UMPlatformData... uMPlatformDataArr) {
        if (context == null) {
            bo.e("context is null in onShareEvent");
            return;
        }
        e.e = "3";
        UMSocialService.share(context, uMPlatformDataArr);
    }

    public static void onKillProcess(Context context) {
        b.d(context);
    }

    public static void onProfileSignIn(String str) {
        onProfileSignIn("_adhoc", str);
    }

    public static void onProfileSignIn(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            bo.d("uid is null");
        } else if (str2.length() > 64) {
            bo.d("uid is Illegal(length bigger then  legitimate length).");
        } else if (TextUtils.isEmpty(str)) {
            b.b("_adhoc", str2);
        } else if (str.length() > 32) {
            bo.d("provider is Illegal(length bigger then  legitimate length).");
        } else {
            b.b(str, str2);
        }
    }

    public static void onProfileSignOff() {
        b.b();
    }

    /* loaded from: classes2.dex */
    public enum EScenarioType {
        E_UM_NORMAL(0),
        E_UM_GAME(1),
        E_UM_ANALYTICS_OEM(TbsListener.ErrorCode.EXCEED_INCR_UPDATE),
        E_UM_GAME_OEM(TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR);
        
        private int a;

        EScenarioType(int i) {
            this.a = i;
        }

        public int toValue() {
            return this.a;
        }
    }

    /* loaded from: classes2.dex */
    public static class UMAnalyticsConfig {
        public String mAppkey;
        public String mChannelId;
        public Context mContext;
        public boolean mIsCrashEnable;
        public EScenarioType mType;

        private UMAnalyticsConfig() {
            this.mAppkey = null;
            this.mChannelId = null;
            this.mIsCrashEnable = true;
            this.mType = EScenarioType.E_UM_NORMAL;
            this.mContext = null;
        }

        public UMAnalyticsConfig(Context context, String str, String str2) {
            this(context, str, str2, null, true);
        }

        public UMAnalyticsConfig(Context context, String str, String str2, EScenarioType eScenarioType) {
            this(context, str, str2, eScenarioType, true);
        }

        public UMAnalyticsConfig(Context context, String str, String str2, EScenarioType eScenarioType, boolean z) {
            this.mAppkey = null;
            this.mChannelId = null;
            this.mIsCrashEnable = true;
            this.mType = EScenarioType.E_UM_NORMAL;
            this.mContext = null;
            this.mContext = context;
            this.mAppkey = str;
            this.mChannelId = str2;
            this.mIsCrashEnable = z;
            if (eScenarioType != null) {
                this.mType = eScenarioType;
                return;
            }
            switch (AnalyticsConfig.getVerticalType(context)) {
                case 0:
                    this.mType = EScenarioType.E_UM_NORMAL;
                    return;
                case 1:
                    this.mType = EScenarioType.E_UM_GAME;
                    return;
                case TbsListener.ErrorCode.EXCEED_INCR_UPDATE /* 224 */:
                    this.mType = EScenarioType.E_UM_ANALYTICS_OEM;
                    return;
                case TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR /* 225 */:
                    this.mType = EScenarioType.E_UM_GAME_OEM;
                    return;
                default:
                    return;
            }
        }
    }
}

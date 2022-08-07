package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import com.hy.http.HttpEntity;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.a.f;
import com.tencent.open.e;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.OpenConfig;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class SocialApiIml extends BaseApi {
    private Activity a;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public static class b {
        Intent a;
        String b;
        Bundle c;
        String d;
        IUiListener e;
    }

    public SocialApiIml(QQToken qQToken) {
        super(qQToken);
    }

    public SocialApiIml(QQAuth qQAuth, QQToken qQToken) {
        super(qQAuth, qQToken);
    }

    public void gift(Activity activity, Bundle bundle, IUiListener iUiListener) {
        a(activity, SocialConstants.ACTION_GIFT, bundle, iUiListener);
    }

    public void ask(Activity activity, Bundle bundle, IUiListener iUiListener) {
        a(activity, SocialConstants.ACTION_ASK, bundle, iUiListener);
    }

    private void a(Activity activity, String str, Bundle bundle, IUiListener iUiListener) {
        this.a = activity;
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_FRIEND_CHOOSER);
        if (agentIntentWithTarget == null) {
            f.c("openSDK_LOG.SocialApiIml", "--askgift--friend chooser not found");
            agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_ASK_GIFT);
        }
        bundle.putAll(composeActivityParams());
        if (SocialConstants.ACTION_ASK.equals(str)) {
            bundle.putString("type", SocialConstants.TYPE_REQUEST);
        } else if (SocialConstants.ACTION_GIFT.equals(str)) {
            bundle.putString("type", SocialConstants.TYPE_FREEGIFT);
        }
        a(activity, agentIntentWithTarget, str, bundle, ServerSetting.getInstance().getEnvUrl(Global.getContext(), "http://qzs.qq.com/open/mobile/request/sdk_request.html?"), iUiListener, false);
    }

    public void invite(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.a = activity;
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_FRIEND_CHOOSER);
        if (agentIntentWithTarget == null) {
            f.c("openSDK_LOG.SocialApiIml", "--invite--friend chooser not found");
            agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_INVITE);
        }
        bundle.putAll(composeActivityParams());
        a(activity, agentIntentWithTarget, SocialConstants.ACTION_INVITE, bundle, ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_INVITE), iUiListener, false);
    }

    public void story(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.a = activity;
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_STORY);
        bundle.putAll(composeActivityParams());
        a(activity, agentIntentWithTarget, SocialConstants.ACTION_STORY, bundle, ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_SEND_STORY), iUiListener, false);
    }

    private void a(Activity activity, Intent intent, String str, Bundle bundle, String str2, IUiListener iUiListener, boolean z) {
        boolean z2 = false;
        f.c("openSDK_LOG.SocialApiIml", "-->handleIntent action = " + str + ", activityIntent = null ? " + (intent == null));
        if (intent != null) {
            a(activity, intent, str, bundle, iUiListener);
            return;
        }
        OpenConfig instance = OpenConfig.getInstance(Global.getContext(), this.mToken.getAppId());
        if (z || instance.getBoolean("C_LoginH5")) {
            z2 = true;
        }
        if (z2) {
            a(activity, str, bundle, str2, iUiListener);
        } else {
            handleDownloadLastestQQ(activity, bundle, iUiListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity, Intent intent, String str, Bundle bundle, IUiListener iUiListener) {
        f.c("openSDK_LOG.SocialApiIml", "-->handleIntentWithAgent action = " + str);
        intent.putExtra(Constants.KEY_ACTION, str);
        intent.putExtra(Constants.KEY_PARAMS, bundle);
        UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_SOCIAL_API, iUiListener);
        startAssitActivity(activity, intent, Constants.REQUEST_SOCIAL_API);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity, String str, Bundle bundle, String str2, IUiListener iUiListener) {
        f.c("openSDK_LOG.SocialApiIml", "-->handleIntentWithH5 action = " + str);
        Intent targetActivityIntent = getTargetActivityIntent("com.tencent.open.agent.AgentActivity");
        IUiListener cVar = new c(activity, iUiListener, str, str2, bundle);
        Intent targetActivityIntent2 = getTargetActivityIntent("com.tencent.open.agent.EncryTokenActivity");
        if (targetActivityIntent2 == null || targetActivityIntent == null || targetActivityIntent.getComponent() == null || targetActivityIntent2.getComponent() == null || !targetActivityIntent.getComponent().getPackageName().equals(targetActivityIntent2.getComponent().getPackageName())) {
            f.c("openSDK_LOG.SocialApiIml", "-->handleIntentWithH5--token activity not found");
            String encrypt = Util.encrypt("tencent&sdk&qazxc***14969%%" + this.mToken.getAccessToken() + this.mToken.getAppId() + this.mToken.getOpenId() + "qzone3.4");
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(SocialConstants.PARAM_ENCRY_EOKEN, encrypt);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            cVar.onComplete(jSONObject);
            return;
        }
        targetActivityIntent2.putExtra("oauth_consumer_key", this.mToken.getAppId());
        targetActivityIntent2.putExtra("openid", this.mToken.getOpenId());
        targetActivityIntent2.putExtra(Constants.PARAM_ACCESS_TOKEN, this.mToken.getAccessToken());
        targetActivityIntent2.putExtra(Constants.KEY_ACTION, SocialConstants.ACTION_CHECK_TOKEN);
        if (hasActivityForIntent(targetActivityIntent2)) {
            f.c("openSDK_LOG.SocialApiIml", "-->handleIntentWithH5--found token activity");
            UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_SOCIAL_H5, cVar);
            startAssitActivity(activity, targetActivityIntent2, Constants.REQUEST_SOCIAL_H5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, String str, Bundle bundle, String str2, IUiListener iUiListener) {
        f.a("openSDK_LOG.SocialApiIml", "OpenUi, showDialog --start");
        CookieSyncManager.createInstance(context);
        bundle.putString("oauth_consumer_key", this.mToken.getAppId());
        if (this.mToken.isSessionValid()) {
            bundle.putString(Constants.PARAM_ACCESS_TOKEN, this.mToken.getAccessToken());
        }
        String openId = this.mToken.getOpenId();
        if (openId != null) {
            bundle.putString("openid", openId);
        }
        try {
            bundle.putString(Constants.PARAM_PLATFORM_ID, Global.getContext().getSharedPreferences(Constants.PREFERENCE_PF, 0).getString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF));
        } catch (Exception e) {
            e.printStackTrace();
            bundle.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
        }
        String str3 = str2 + HttpUtils.encodeUrl(bundle);
        f.b("openSDK_LOG.SocialApiIml", "OpenUi, showDialog TDialog");
        if (SocialConstants.ACTION_CHALLENGE.equals(str) || SocialConstants.ACTION_BRAG.equals(str)) {
            f.b("openSDK_LOG.SocialApiIml", "OpenUi, showDialog PKDialog");
            new PKDialog(this.a, str, str3, iUiListener, this.mToken).show();
            return;
        }
        new TDialog(this.a, str, str3, iUiListener, this.mToken).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class c implements IUiListener {
        private IUiListener b;
        private String c;
        private String d;
        private Bundle e;
        private Activity f;

        c(Activity activity, IUiListener iUiListener, String str, String str2, Bundle bundle) {
            this.b = iUiListener;
            this.c = str;
            this.d = str2;
            this.e = bundle;
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            String str;
            try {
                str = ((JSONObject) obj).getString(SocialConstants.PARAM_ENCRY_EOKEN);
            } catch (JSONException e) {
                e.printStackTrace();
                f.b("openSDK_LOG.SocialApiIml", "OpenApi, EncrytokenListener() onComplete error", e);
                str = null;
            }
            this.e.putString("encrytoken", str);
            SocialApiIml.this.a((Context) SocialApiIml.this.a, this.c, this.e, this.d, this.b);
            if (TextUtils.isEmpty(str)) {
                f.b("openSDK_LOG.SocialApiIml", "The token get from qq or qzone is empty. Write temp token to localstorage.");
                SocialApiIml.this.writeEncryToken(this.f);
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            f.b("openSDK_LOG.SocialApiIml", "OpenApi, EncryptTokenListener() onError" + uiError.errorMessage);
            this.b.onError(uiError);
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            this.b.onCancel();
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void writeEncryToken(Context context) {
        String str;
        String accessToken = this.mToken.getAccessToken();
        String appId = this.mToken.getAppId();
        String openId = this.mToken.getOpenId();
        if (accessToken == null || accessToken.length() <= 0 || appId == null || appId.length() <= 0 || openId == null || openId.length() <= 0) {
            str = null;
        } else {
            str = Util.encrypt("tencent&sdk&qazxc***14969%%" + accessToken + appId + openId + "qzone3.4");
        }
        com.tencent.open.c.b bVar = new com.tencent.open.c.b(context);
        WebSettings settings = bVar.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        String str2 = "<!DOCTYPE HTML><html lang=\"en-US\"><head><meta charset=\"UTF-8\"><title>localStorage Test</title><script type=\"text/javascript\">document.domain = 'qq.com';localStorage[\"" + this.mToken.getOpenId() + "_" + this.mToken.getAppId() + "\"]=\"" + str + "\";</script></head><body></body></html>";
        String envUrl = ServerSetting.getInstance().getEnvUrl(context, ServerSetting.DEFAULT_LOCAL_STORAGE_URI);
        bVar.loadDataWithBaseURL(envUrl, str2, HttpEntity.TEXT_HTML, "utf-8", envUrl);
    }

    @Override // com.tencent.connect.common.BaseApi
    protected Intent getTargetActivityIntent(String str) {
        Intent intent = new Intent();
        intent.setClassName("com.qzone", str);
        Intent intent2 = new Intent();
        intent2.setClassName("com.tencent.mobileqq", str);
        Intent intent3 = new Intent();
        intent3.setClassName(Constants.PACKAGE_QQ_PAD, str);
        if (Util.isTablet(Global.getContext()) && SystemUtils.isActivityExist(Global.getContext(), intent3)) {
            return intent3;
        }
        if (SystemUtils.isActivityExist(Global.getContext(), intent2) && SystemUtils.compareQQVersion(Global.getContext(), "4.7") >= 0) {
            return intent2;
        }
        if (!SystemUtils.isActivityExist(Global.getContext(), intent) || SystemUtils.compareVersion(SystemUtils.getAppVersionName(Global.getContext(), "com.qzone"), "4.2") < 0) {
            return null;
        }
        if (!SystemUtils.isAppSignatureValid(Global.getContext(), intent.getComponent().getPackageName(), Constants.SIGNATRUE_QZONE)) {
            return null;
        }
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (!this.a.isFinishing() && this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
            this.mProgressDialog.dismiss();
            this.mProgressDialog = null;
        }
    }

    protected boolean a() {
        Intent intent = new Intent();
        intent.setClassName("com.qzone", SocialConstants.ACTIVITY_CHECK_FUNCTION);
        return SystemUtils.isActivityExist(Global.getContext(), intent);
    }

    protected void a(Activity activity, String str, IUiListener iUiListener) {
        Intent intent = new Intent();
        intent.setClassName("com.qzone", "com.tencent.open.agent.AgentActivity");
        intent.putExtra(Constants.KEY_ACTION, "action_check");
        Bundle bundle = new Bundle();
        bundle.putString(com.alipay.sdk.cons.c.n, str);
        intent.putExtra(Constants.KEY_PARAMS, bundle);
        UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_SOCIAL_API, iUiListener);
        startAssitActivity(activity, intent, Constants.REQUEST_SOCIAL_API);
    }

    private b a(Bundle bundle, String str, String str2, IUiListener iUiListener) {
        Intent intent = new Intent();
        intent.setClassName("com.qzone", "com.tencent.open.agent.AgentActivity");
        b bVar = new b();
        bVar.a = intent;
        bVar.c = bundle;
        bVar.d = str2;
        bVar.e = iUiListener;
        bVar.b = str;
        return bVar;
    }

    public void reactive(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.a = activity;
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_FRIEND_CHOOSER);
        if (agentIntentWithTarget == null) {
            agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_REACTIVE);
        }
        bundle.putAll(composeActivityParams());
        String envUrl = ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_REACTIVE);
        if (agentIntentWithTarget != null || !a()) {
            bundle.putString(SocialConstants.PARAM_SEND_IMG, bundle.getString("img"));
            bundle.putString("type", SocialConstants.TYPE_REACTIVE);
            bundle.remove("img");
            a(activity, agentIntentWithTarget, SocialConstants.ACTION_REACTIVE, bundle, envUrl, iUiListener, false);
            return;
        }
        this.mProgressDialog = new ProgressDialog(activity);
        this.mProgressDialog.setMessage("请稍候...");
        this.mProgressDialog.show();
        bundle.putString("type", SocialConstants.TYPE_REACTIVE);
        a(activity, SocialConstants.ACTION_REACTIVE, new a(a(bundle, SocialConstants.ACTION_REACTIVE, envUrl, iUiListener)));
    }

    public void brag(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.a = activity;
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_BRAG);
        bundle.putAll(composeActivityParams());
        a(activity, agentIntentWithTarget, SocialConstants.ACTION_BRAG, bundle, ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_BRAG), iUiListener, false);
    }

    public void challenge(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.a = activity;
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_CHALLENGE);
        bundle.putAll(composeActivityParams());
        a(activity, agentIntentWithTarget, SocialConstants.ACTION_CHALLENGE, bundle, ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_BRAG), iUiListener, false);
    }

    public void grade(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.a = activity;
        bundle.putAll(composeActivityParams());
        bundle.putString("version", Util.getAppVersion(activity));
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_GRADE);
        if (agentIntentWithTarget != null || !a()) {
            a(activity, agentIntentWithTarget, SocialConstants.ACTION_GRADE, bundle, "http://qzs.qq.com/open/mobile/rate/sdk_rate.html?", iUiListener, true);
            return;
        }
        this.mProgressDialog = new ProgressDialog(activity);
        this.mProgressDialog.setMessage("请稍候...");
        this.mProgressDialog.show();
        a(activity, SocialConstants.ACTION_GRADE, new a(a(bundle, SocialConstants.ACTION_GRADE, "http://qzs.qq.com/open/mobile/rate/sdk_rate.html?", iUiListener)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class a implements IUiListener {
        b a;

        public a(b bVar) {
            this.a = bVar;
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            boolean z = false;
            if (obj != null) {
                try {
                    z = ((JSONObject) obj).getBoolean("check_result");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            SocialApiIml.this.b();
            if (z) {
                SocialApiIml.this.a(SocialApiIml.this.a, this.a.a, this.a.b, this.a.c, this.a.e);
                return;
            }
            e.a(this.a.c.getString(SocialConstants.PARAM_IMG_DATA));
            SocialApiIml.this.a(SocialApiIml.this.a, this.a.b, this.a.c, this.a.d, this.a.e);
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            SocialApiIml.this.b();
            e.a(this.a.c.getString(SocialConstants.PARAM_IMG_DATA));
            SocialApiIml.this.a(SocialApiIml.this.a, this.a.b, this.a.c, this.a.d, this.a.e);
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            SocialApiIml.this.b();
            e.a(this.a.c.getString(SocialConstants.PARAM_IMG_DATA));
        }
    }

    public void voice(final Activity activity, final Bundle bundle, final IUiListener iUiListener) {
        Bitmap bitmap;
        this.a = activity;
        bundle.putAll(composeActivityParams());
        bundle.putString("version", Util.getAppVersion(activity));
        if (!e.a()) {
            f.c("openSDK_LOG.SocialApiIml", "voice no SDCard");
            iUiListener.onError(new UiError(-12, Constants.MSG_NO_SDCARD, Constants.MSG_NO_SDCARD));
        } else if (!bundle.containsKey(SocialConstants.PARAM_IMG_DATA) || (bitmap = (Bitmap) bundle.getParcelable(SocialConstants.PARAM_IMG_DATA)) == null) {
            a(activity, bundle, iUiListener);
        } else {
            this.mProgressDialog = new ProgressDialog(activity);
            this.mProgressDialog.setMessage("请稍候...");
            this.mProgressDialog.show();
            new e(new e.a() { // from class: com.tencent.open.SocialApiIml.1
                @Override // com.tencent.open.e.a
                public void a(String str) {
                    bundle.remove(SocialConstants.PARAM_IMG_DATA);
                    if (!TextUtils.isEmpty(str)) {
                        bundle.putString(SocialConstants.PARAM_IMG_DATA, str);
                    }
                    SocialApiIml.this.a(activity, bundle, iUiListener);
                }

                @Override // com.tencent.open.e.a
                public void b(String str) {
                    bundle.remove(SocialConstants.PARAM_IMG_DATA);
                    iUiListener.onError(new UiError(-5, Constants.MSG_IMAGE_ERROR, Constants.MSG_IMAGE_ERROR));
                    SocialApiIml.this.b();
                }
            }).execute(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity, Bundle bundle, IUiListener iUiListener) {
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_VOICE);
        String envUrl = ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_VOICE);
        if (agentIntentWithTarget != null || !a()) {
            a(activity, agentIntentWithTarget, SocialConstants.ACTION_VOICE, bundle, envUrl, iUiListener, true);
            return;
        }
        if (this.mProgressDialog == null || !this.mProgressDialog.isShowing()) {
            this.mProgressDialog = new ProgressDialog(activity);
            this.mProgressDialog.setTitle("请稍候");
            this.mProgressDialog.show();
        }
        a(activity, SocialConstants.ACTION_VOICE, new a(a(bundle, SocialConstants.ACTION_VOICE, envUrl, iUiListener)));
    }
}

package com.tencent.connect.auth;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.parse.ParseException;
import com.tencent.connect.a.a;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.OpenConfig;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.ThreadManager;
import com.tencent.open.utils.Util;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class AuthAgent extends BaseApi {
    public static final String SECURE_LIB_ARM64_FILE_NAME = "libwbsafeedit_64";
    public static final String SECURE_LIB_ARM_FILE_NAME = "libwbsafeedit";
    public static String SECURE_LIB_FILE_NAME = null;
    public static String SECURE_LIB_NAME = null;
    public static final String SECURE_LIB_X86_64_FILE_NAME = "libwbsafeedit_x86_64";
    public static final String SECURE_LIB_X86_FILE_NAME = "libwbsafeedit_x86";
    private IUiListener a;
    private String b;
    private WeakReference<Activity> c;

    static {
        SECURE_LIB_FILE_NAME = SECURE_LIB_ARM_FILE_NAME;
        SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
        String str = Build.CPU_ABI;
        if (str == null || str.equals("")) {
            SECURE_LIB_FILE_NAME = SECURE_LIB_ARM_FILE_NAME;
            SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
            f.c("openSDK_LOG.AuthAgent", "is arm(default) architecture");
        } else if (str.equalsIgnoreCase("arm64-v8a")) {
            SECURE_LIB_FILE_NAME = SECURE_LIB_ARM64_FILE_NAME;
            SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
            f.c("openSDK_LOG.AuthAgent", "is arm64-v8a architecture");
        } else if (str.equalsIgnoreCase("x86")) {
            SECURE_LIB_FILE_NAME = SECURE_LIB_X86_FILE_NAME;
            SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
            f.c("openSDK_LOG.AuthAgent", "is x86 architecture");
        } else if (str.equalsIgnoreCase("x86_64")) {
            SECURE_LIB_FILE_NAME = SECURE_LIB_X86_64_FILE_NAME;
            SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
            f.c("openSDK_LOG.AuthAgent", "is x86_64 architecture");
        } else {
            SECURE_LIB_FILE_NAME = SECURE_LIB_ARM_FILE_NAME;
            SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
            f.c("openSDK_LOG.AuthAgent", "is arm(default) architecture");
        }
    }

    public AuthAgent(QQToken qQToken) {
        super(qQToken);
    }

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class TokenListener implements IUiListener {
        private final IUiListener b;
        private final boolean c;
        private final Context d;

        public TokenListener(Context context, IUiListener iUiListener, boolean z, boolean z2) {
            AuthAgent.this = r3;
            this.d = context;
            this.b = iUiListener;
            this.c = z;
            f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener()");
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete");
            JSONObject jSONObject = (JSONObject) obj;
            try {
                String string = jSONObject.getString(Constants.PARAM_ACCESS_TOKEN);
                String string2 = jSONObject.getString(Constants.PARAM_EXPIRES_IN);
                String string3 = jSONObject.getString("openid");
                if (!(string == null || AuthAgent.this.mToken == null || string3 == null)) {
                    AuthAgent.this.mToken.setAccessToken(string, string2);
                    AuthAgent.this.mToken.setOpenId(string3);
                    a.d(this.d, AuthAgent.this.mToken);
                }
                String string4 = jSONObject.getString(Constants.PARAM_PLATFORM_ID);
                if (string4 != null) {
                    try {
                        this.d.getSharedPreferences(Constants.PREFERENCE_PF, 0).edit().putString(Constants.PARAM_PLATFORM_ID, string4).commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                        f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete error", e);
                    }
                }
                if (this.c) {
                    CookieSyncManager.getInstance().sync();
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete error", e2);
            }
            this.b.onComplete(jSONObject);
            AuthAgent.this.releaseResource();
            f.b();
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onError");
            this.b.onError(uiError);
            f.b();
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onCancel");
            this.b.onCancel();
            f.b();
        }
    }

    public int doLogin(Activity activity, String str, IUiListener iUiListener) {
        return doLogin(activity, str, iUiListener, false, null);
    }

    public int doLogin(Activity activity, String str, IUiListener iUiListener, boolean z, Fragment fragment) {
        this.b = str;
        this.c = new WeakReference<>(activity);
        this.a = iUiListener;
        if (OpenConfig.getInstance(activity, this.mToken.getAppId()).getBoolean(OpenConfig.Key_WebLogin) || !a(activity, fragment, z)) {
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), "2", "1", "5", "1", "0", "0");
            f.d("openSDK_LOG.AuthAgent", "doLogin startActivity fail show dialog.");
            this.a = new FeedConfirmListener(this.a);
            return a(z, this.a);
        }
        f.c("openSDK_LOG.AuthAgent", "OpenUi, showUi, return Constants.UI_ACTIVITY");
        d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), "2", "1", "5", "0", "0", "0");
        return 1;
    }

    @Override // com.tencent.connect.common.BaseApi
    public void releaseResource() {
        this.a = null;
    }

    private int a(boolean z, IUiListener iUiListener) {
        f.c("openSDK_LOG.AuthAgent", "OpenUi, showDialog -- start");
        CookieSyncManager.createInstance(Global.getContext());
        Bundle composeCGIParams = composeCGIParams();
        if (z) {
            composeCGIParams.putString("isadd", "1");
        }
        composeCGIParams.putString(Constants.PARAM_SCOPE, this.b);
        composeCGIParams.putString(Constants.PARAM_CLIENT_ID, this.mToken.getAppId());
        if (isOEM) {
            composeCGIParams.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + installChannel + "-" + com.alimama.mobile.csdk.umupdate.a.f.a + "-" + registerChannel + "-" + businessId);
        } else {
            composeCGIParams.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
        }
        String str = (System.currentTimeMillis() / 1000) + "";
        composeCGIParams.putString("sign", SystemUtils.getAppSignatureMD5(Global.getContext(), str));
        composeCGIParams.putString(com.alimama.mobile.csdk.umupdate.a.f.az, str);
        composeCGIParams.putString("display", "mobile");
        composeCGIParams.putString("response_type", "token");
        composeCGIParams.putString("redirect_uri", ServerSetting.DEFAULT_REDIRECT_URI);
        composeCGIParams.putString("cancel_display", "1");
        composeCGIParams.putString("switch", "1");
        composeCGIParams.putString("status_userip", Util.getUserIp());
        final String str2 = ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_CGI_AUTHORIZE) + HttpUtils.encodeUrl(composeCGIParams);
        final TokenListener tokenListener = new TokenListener(Global.getContext(), iUiListener, true, false);
        f.b("openSDK_LOG.AuthAgent", "OpenUi, showDialog TDialog");
        ThreadManager.executeOnSubThread(new Runnable() { // from class: com.tencent.connect.auth.AuthAgent.1
            @Override // java.lang.Runnable
            public void run() {
                final Activity activity;
                SystemUtils.extractSecureLib(AuthAgent.SECURE_LIB_FILE_NAME, AuthAgent.SECURE_LIB_NAME, 3);
                if (AuthAgent.this.c != null && (activity = (Activity) AuthAgent.this.c.get()) != null) {
                    activity.runOnUiThread(new Runnable() { // from class: com.tencent.connect.auth.AuthAgent.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AuthDialog authDialog = new AuthDialog(activity, SystemUtils.ACTION_LOGIN, str2, tokenListener, AuthAgent.this.mToken);
                            if (activity != null && !activity.isFinishing()) {
                                authDialog.show();
                            }
                        }
                    });
                }
            }
        });
        f.c("openSDK_LOG.AuthAgent", "OpenUi, showDialog -- end");
        return 2;
    }

    private boolean a(Activity activity, Fragment fragment, boolean z) {
        f.c("openSDK_LOG.AuthAgent", "startActionActivity() -- start");
        Intent targetActivityIntent = getTargetActivityIntent("com.tencent.open.agent.AgentActivity");
        if (targetActivityIntent != null) {
            Bundle composeCGIParams = composeCGIParams();
            if (z) {
                composeCGIParams.putString("isadd", "1");
            }
            composeCGIParams.putString(Constants.PARAM_SCOPE, this.b);
            composeCGIParams.putString(Constants.PARAM_CLIENT_ID, this.mToken.getAppId());
            if (isOEM) {
                composeCGIParams.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + installChannel + "-" + com.alimama.mobile.csdk.umupdate.a.f.a + "-" + registerChannel + "-" + businessId);
            } else {
                composeCGIParams.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
            }
            composeCGIParams.putString("need_pay", "1");
            composeCGIParams.putString(Constants.KEY_APP_NAME, SystemUtils.getAppName(Global.getContext()));
            targetActivityIntent.putExtra(Constants.KEY_ACTION, SystemUtils.ACTION_LOGIN);
            targetActivityIntent.putExtra(Constants.KEY_PARAMS, composeCGIParams);
            targetActivityIntent.putExtra("appid", this.mToken.getAppId());
            if (hasActivityForIntent(targetActivityIntent)) {
                this.a = new FeedConfirmListener(this.a);
                UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_LOGIN, this.a);
                if (fragment != null) {
                    f.b("openSDK_LOG.AuthAgent", "startAssitActivity fragment");
                    startAssitActivity(fragment, targetActivityIntent, Constants.REQUEST_LOGIN);
                } else {
                    f.b("openSDK_LOG.AuthAgent", "startAssitActivity activity");
                    startAssitActivity(activity, targetActivityIntent, Constants.REQUEST_LOGIN);
                }
                f.c("openSDK_LOG.AuthAgent", "startActionActivity() -- end, found activity for loginIntent");
                d.a().a(0, "LOGIN_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), "", Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "");
                return true;
            }
        }
        d.a().a(1, "LOGIN_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), "", Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "startActionActivity fail");
        f.c("openSDK_LOG.AuthAgent", "startActionActivity() -- end, no target activity for loginIntent");
        return false;
    }

    public void a(IUiListener iUiListener) {
        f.c("openSDK_LOG.AuthAgent", "reportDAU() -- start");
        String accessToken = this.mToken.getAccessToken();
        String openId = this.mToken.getOpenId();
        String appId = this.mToken.getAppId();
        String str = "";
        if (!TextUtils.isEmpty(accessToken) && !TextUtils.isEmpty(openId) && !TextUtils.isEmpty(appId)) {
            str = Util.encrypt("tencent&sdk&qazxc***14969%%" + accessToken + appId + openId + "qzone3.4");
        }
        if (TextUtils.isEmpty(str)) {
            f.e("openSDK_LOG.AuthAgent", "reportDAU -- encrytoken is null");
            return;
        }
        Bundle composeCGIParams = composeCGIParams();
        composeCGIParams.putString("encrytoken", str);
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "https://openmobile.qq.com/user/user_login_statis", composeCGIParams, "POST", null);
        f.c("openSDK_LOG.AuthAgent", "reportDAU() -- end");
    }

    public void b(IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        composeCGIParams.putString("reqType", "checkLogin");
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "https://openmobile.qq.com/v3/user/get_info", composeCGIParams, "GET", new BaseApi.TempRequestListener(new CheckLoginListener(iUiListener)));
    }

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class CheckLoginListener implements IUiListener {
        IUiListener a;

        public CheckLoginListener(IUiListener iUiListener) {
            AuthAgent.this = r1;
            this.a = iUiListener;
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            if (obj == null) {
                f.e("openSDK_LOG.AuthAgent", "CheckLoginListener response data is null");
                return;
            }
            JSONObject jSONObject = (JSONObject) obj;
            try {
                int i = jSONObject.getInt("ret");
                String string = i == 0 ? "success" : jSONObject.getString("msg");
                if (this.a != null) {
                    this.a.onComplete(new JSONObject().put("ret", i).put("msg", string));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                f.e("openSDK_LOG.AuthAgent", "CheckLoginListener response data format error");
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            if (this.a != null) {
                this.a.onError(uiError);
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            if (this.a != null) {
                this.a.onCancel();
            }
        }
    }

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class FeedConfirmListener implements IUiListener {
        IUiListener a;
        private final String c = "sendinstall";
        private final String d = "installwording";
        private final String e = "http://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi";

        public FeedConfirmListener(IUiListener iUiListener) {
            AuthAgent.this = r2;
            this.a = iUiListener;
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            JSONObject jSONObject;
            String str;
            boolean z = false;
            if (obj != null && (jSONObject = (JSONObject) obj) != null) {
                try {
                    if (jSONObject.getInt("sendinstall") == 1) {
                        z = true;
                    }
                    str = jSONObject.getString("installwording");
                    z = z;
                } catch (JSONException e) {
                    f.d("openSDK_LOG.AuthAgent", "FeedConfirmListener onComplete There is no value for sendinstall.");
                    str = "";
                }
                String decode = URLDecoder.decode(str);
                f.a("openSDK_LOG.AuthAgent", " WORDING = " + decode + "xx");
                if (z && !TextUtils.isEmpty(decode)) {
                    a(decode, this.a, obj);
                } else if (this.a != null) {
                    this.a.onComplete(obj);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* compiled from: ProGuard */
        /* loaded from: classes2.dex */
        public abstract class ButtonListener implements View.OnClickListener {
            Dialog d;

            ButtonListener(Dialog dialog) {
                FeedConfirmListener.this = r1;
                this.d = dialog;
            }
        }

        private void a(String str, final IUiListener iUiListener, final Object obj) {
            Activity activity;
            PackageInfo packageInfo;
            Drawable drawable = null;
            if (AuthAgent.this.c != null && (activity = (Activity) AuthAgent.this.c.get()) != null) {
                Dialog dialog = new Dialog(activity);
                dialog.requestWindowFeature(1);
                PackageManager packageManager = activity.getPackageManager();
                try {
                    packageInfo = packageManager.getPackageInfo(activity.getPackageName(), 0);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                    packageInfo = null;
                }
                if (packageInfo != null) {
                    drawable = packageInfo.applicationInfo.loadIcon(packageManager);
                }
                View.OnClickListener onClickListener = new ButtonListener(dialog) { // from class: com.tencent.connect.auth.AuthAgent.FeedConfirmListener.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        FeedConfirmListener.this.a();
                        if (this.d != null && this.d.isShowing()) {
                            this.d.dismiss();
                        }
                        if (iUiListener != null) {
                            iUiListener.onComplete(obj);
                        }
                    }
                };
                View.OnClickListener onClickListener2 = new ButtonListener(dialog) { // from class: com.tencent.connect.auth.AuthAgent.FeedConfirmListener.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (this.d != null && this.d.isShowing()) {
                            this.d.dismiss();
                        }
                        if (iUiListener != null) {
                            iUiListener.onComplete(obj);
                        }
                    }
                };
                ColorDrawable colorDrawable = new ColorDrawable();
                colorDrawable.setAlpha(0);
                dialog.getWindow().setBackgroundDrawable(colorDrawable);
                dialog.setContentView(a(activity, drawable, str, onClickListener, onClickListener2));
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.tencent.connect.auth.AuthAgent.FeedConfirmListener.3
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        if (iUiListener != null) {
                            iUiListener.onComplete(obj);
                        }
                    }
                });
                if (activity != null && !activity.isFinishing()) {
                    dialog.show();
                }
            }
        }

        private Drawable a(String str, Context context) {
            IOException e;
            Drawable drawable;
            Bitmap bitmap;
            try {
                InputStream open = context.getApplicationContext().getAssets().open(str);
                if (open == null) {
                    return null;
                }
                if (str.endsWith(".9.png")) {
                    try {
                        bitmap = BitmapFactory.decodeStream(open);
                    } catch (OutOfMemoryError e2) {
                        e2.printStackTrace();
                        bitmap = null;
                    }
                    if (bitmap == null) {
                        return null;
                    }
                    byte[] ninePatchChunk = bitmap.getNinePatchChunk();
                    NinePatch.isNinePatchChunk(ninePatchChunk);
                    return new NinePatchDrawable(bitmap, ninePatchChunk, new Rect(), null);
                }
                drawable = Drawable.createFromStream(open, str);
                try {
                    open.close();
                    return drawable;
                } catch (IOException e3) {
                    e = e3;
                    e.printStackTrace();
                    return drawable;
                }
            } catch (IOException e4) {
                drawable = null;
                e = e4;
            }
        }

        private View a(Context context, Drawable drawable, String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.density;
            RelativeLayout relativeLayout = new RelativeLayout(context);
            ImageView imageView = new ImageView(context);
            imageView.setImageDrawable(drawable);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setId(1);
            int i = (int) (14.0f * f);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (60.0f * f), (int) (60.0f * f));
            layoutParams.addRule(9);
            layoutParams.setMargins(0, (int) (18.0f * f), (int) (6.0f * f), (int) (18.0f * f));
            relativeLayout.addView(imageView, layoutParams);
            TextView textView = new TextView(context);
            textView.setText(str);
            textView.setTextSize(14.0f);
            textView.setGravity(3);
            textView.setIncludeFontPadding(false);
            textView.setPadding(0, 0, 0, 0);
            textView.setLines(2);
            textView.setId(5);
            textView.setMinWidth((int) (185.0f * f));
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(1, 1);
            layoutParams2.addRule(6, 1);
            int i2 = (int) (10.0f * f);
            layoutParams2.setMargins(0, 0, (int) (5.0f * f), 0);
            relativeLayout.addView(textView, layoutParams2);
            View view = new View(context);
            view.setBackgroundColor(Color.rgb((int) TbsListener.ErrorCode.COPY_TMPDIR_ERROR, (int) TbsListener.ErrorCode.COPY_TMPDIR_ERROR, (int) TbsListener.ErrorCode.COPY_TMPDIR_ERROR));
            view.setId(3);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, 2);
            layoutParams3.addRule(3, 1);
            layoutParams3.addRule(5, 1);
            layoutParams3.addRule(7, 5);
            layoutParams3.setMargins(0, 0, 0, (int) (12.0f * f));
            relativeLayout.addView(view, layoutParams3);
            LinearLayout linearLayout = new LinearLayout(context);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams4.addRule(5, 1);
            layoutParams4.addRule(7, 5);
            layoutParams4.addRule(3, 3);
            Button button = new Button(context);
            button.setText("跳过");
            button.setBackgroundDrawable(a("buttonNegt.png", context));
            button.setTextColor(Color.rgb(36, 97, 131));
            button.setTextSize(20.0f);
            button.setOnClickListener(onClickListener2);
            button.setId(4);
            LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(0, (int) (45.0f * f));
            layoutParams5.rightMargin = (int) (14.0f * f);
            layoutParams5.leftMargin = (int) (4.0f * f);
            layoutParams5.weight = 1.0f;
            linearLayout.addView(button, layoutParams5);
            Button button2 = new Button(context);
            button2.setText("确定");
            button2.setTextSize(20.0f);
            button2.setTextColor(Color.rgb(255, 255, 255));
            button2.setBackgroundDrawable(a("buttonPost.png", context));
            button2.setOnClickListener(onClickListener);
            LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(0, (int) (45.0f * f));
            layoutParams6.weight = 1.0f;
            layoutParams6.rightMargin = (int) (4.0f * f);
            linearLayout.addView(button2, layoutParams6);
            relativeLayout.addView(linearLayout, layoutParams4);
            ViewGroup.LayoutParams layoutParams7 = new FrameLayout.LayoutParams((int) (279.0f * f), (int) (163.0f * f));
            relativeLayout.setPadding((int) (14.0f * f), 0, (int) (12.0f * f), (int) (12.0f * f));
            relativeLayout.setLayoutParams(layoutParams7);
            relativeLayout.setBackgroundColor(Color.rgb(247, (int) ParseException.INVALID_LINKED_SESSION, 247));
            PaintDrawable paintDrawable = new PaintDrawable(Color.rgb(247, (int) ParseException.INVALID_LINKED_SESSION, 247));
            paintDrawable.setCornerRadius(f * 5.0f);
            relativeLayout.setBackgroundDrawable(paintDrawable);
            return relativeLayout;
        }

        protected void a() {
            Activity activity;
            Bundle composeActivityParams = AuthAgent.this.composeActivityParams();
            if (AuthAgent.this.c != null && (activity = (Activity) AuthAgent.this.c.get()) != null) {
                HttpUtils.requestAsync(AuthAgent.this.mToken, activity, "http://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi", composeActivityParams, "POST", null);
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            if (this.a != null) {
                this.a.onError(uiError);
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            if (this.a != null) {
                this.a.onCancel();
            }
        }
    }
}

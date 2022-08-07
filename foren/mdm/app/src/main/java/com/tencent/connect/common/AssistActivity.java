package com.tencent.connect.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONObject;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class AssistActivity extends Activity {
    public static final String EXTRA_INTENT = "openSDK_LOG.AssistActivity.ExtraIntent";
    protected static final int FINISH_BY_TIMEOUT = 0;
    private static final String RESTART_FLAG = "RESTART_FLAG";
    private static final String RESUME_FLAG = "RESUME_FLAG";
    private static final String TAG = "openSDK_LOG.AssistActivity";
    private String mAppId;
    private boolean isRestart = false;
    protected boolean mOnResumeIsInited = false;
    protected Handler handler = new Handler() { // from class: com.tencent.connect.common.AssistActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (!AssistActivity.this.isFinishing()) {
                        f.d(AssistActivity.TAG, "-->finish by timeout");
                        AssistActivity.this.finish();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    public static Intent getAssistActivityIntent(Context context) {
        return new Intent(context, AssistActivity.class);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setRequestedOrientation(3);
        f.b(TAG, "--onCreate--");
        if (getIntent() == null) {
            f.e(TAG, "-->onCreate--getIntent() returns null");
            finish();
        }
        Intent intent = (Intent) getIntent().getParcelableExtra(EXTRA_INTENT);
        int intExtra = intent == null ? 0 : intent.getIntExtra(Constants.KEY_REQUEST_CODE, 0);
        this.mAppId = intent == null ? "" : intent.getStringExtra("appid");
        Bundle bundleExtra = getIntent().getBundleExtra(SystemUtils.H5_SHARE_DATA);
        if (bundle != null) {
            this.isRestart = bundle.getBoolean(RESTART_FLAG);
            this.mOnResumeIsInited = bundle.getBoolean(RESUME_FLAG, false);
        }
        if (this.isRestart) {
            f.b(TAG, "is restart");
        } else if (bundleExtra != null) {
            f.d(TAG, "--onCreate--h5 bundle not null, will open browser");
            openBrowser(bundleExtra);
        } else if (intent != null) {
            f.c(TAG, "--onCreate--activityIntent not null, will start activity, reqcode = " + intExtra);
            startActivityForResult(intent, intExtra);
        } else {
            f.e(TAG, "--onCreate--activityIntent is null");
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        f.b(TAG, "-->onStart");
        super.onStart();
    }

    @Override // android.app.Activity
    protected void onResume() {
        f.b(TAG, "-->onResume");
        super.onResume();
        Intent intent = getIntent();
        if (!intent.getBooleanExtra(SystemUtils.IS_LOGIN, false)) {
            if (!intent.getBooleanExtra(SystemUtils.IS_QQ_MOBILE_SHARE, false) && this.isRestart && !isFinishing()) {
                finish();
            }
            if (this.mOnResumeIsInited) {
                this.handler.sendMessage(this.handler.obtainMessage(0));
                return;
            }
            this.mOnResumeIsInited = true;
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        f.b(TAG, "-->onPause");
        this.handler.removeMessages(0);
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onStop() {
        f.b(TAG, "-->onStop");
        super.onStop();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        f.b(TAG, "-->onDestroy");
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        f.c(TAG, "--onNewIntent");
        super.onNewIntent(intent);
        intent.putExtra(Constants.KEY_ACTION, SystemUtils.ACTION_SHARE);
        setResult(-1, intent);
        if (!isFinishing()) {
            f.c(TAG, "--onNewIntent--activity not finished, finish now");
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        f.b(TAG, "--onSaveInstanceState--");
        bundle.putBoolean(RESTART_FLAG, true);
        bundle.putBoolean(RESUME_FLAG, this.mOnResumeIsInited);
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        f.c(TAG, "--onActivityResult--requestCode: " + i + " | resultCode: " + i2 + "data = null ? " + (intent == null));
        super.onActivityResult(i, i2, intent);
        if (i != 0) {
            if (intent != null) {
                intent.putExtra(Constants.KEY_ACTION, SystemUtils.ACTION_LOGIN);
            }
            setResultData(i, intent);
            finish();
        }
    }

    public void setResultData(int i, Intent intent) {
        if (intent == null) {
            f.d(TAG, "--setResultData--intent is null, setResult ACTIVITY_CANCEL");
            setResult(0);
            if (i == 11101) {
                d.a().a("", this.mAppId, "2", "1", "7", "2");
                return;
            }
            return;
        }
        try {
            String stringExtra = intent.getStringExtra(Constants.KEY_RESPONSE);
            f.b(TAG, "--setResultDataForLogin-- " + stringExtra);
            if (!TextUtils.isEmpty(stringExtra)) {
                JSONObject jSONObject = new JSONObject(stringExtra);
                String optString = jSONObject.optString("openid");
                String optString2 = jSONObject.optString(Constants.PARAM_ACCESS_TOKEN);
                if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                    f.d(TAG, "--setResultData--openid or token is empty, setResult ACTIVITY_CANCEL");
                    setResult(0, intent);
                    d.a().a("", this.mAppId, "2", "1", "7", "1");
                } else {
                    f.c(TAG, "--setResultData--openid and token not empty, setResult ACTIVITY_OK");
                    setResult(-1, intent);
                    d.a().a(optString, this.mAppId, "2", "1", "7", "0");
                }
            } else {
                f.d(TAG, "--setResultData--response is empty, setResult ACTIVITY_OK");
                setResult(-1, intent);
            }
        } catch (Exception e) {
            f.e(TAG, "--setResultData--parse response failed");
            e.printStackTrace();
        }
    }

    private void openBrowser(Bundle bundle) {
        String string = bundle.getString("viaShareType");
        String string2 = bundle.getString("callbackAction");
        String string3 = bundle.getString("url");
        String string4 = bundle.getString("openId");
        String string5 = bundle.getString("appId");
        String str = "";
        String str2 = "";
        if (SystemUtils.QQ_SHARE_CALLBACK_ACTION.equals(string2)) {
            str = Constants.VIA_SHARE_TO_QQ;
            str2 = "10";
        } else if (SystemUtils.QZONE_SHARE_CALLBACK_ACTION.equals(string2)) {
            str = Constants.VIA_SHARE_TO_QZONE;
            str2 = "11";
        }
        if (!Util.openBrowser(this, string3)) {
            IUiListener listnerWithAction = UIListenerManager.getInstance().getListnerWithAction(string2);
            if (listnerWithAction != null) {
                listnerWithAction.onError(new UiError(-6, Constants.MSG_OPEN_BROWSER_ERROR, null));
            }
            d.a().a(string4, string5, str, str2, "3", "1", string, "0", "2", "0");
            finish();
        } else {
            d.a().a(string4, string5, str, str2, "3", "0", string, "0", "2", "0");
        }
        getIntent().removeExtra("shareH5");
    }
}

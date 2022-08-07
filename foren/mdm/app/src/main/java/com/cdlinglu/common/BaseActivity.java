package com.cdlinglu.common;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import com.baidu.mobstat.StatService;
import com.easeui.controller.EaseUI;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.IMyHttpListener;
import com.hy.http.MyHttpClient;
import com.umeng.analytics.MobclickAgent;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.UserInfo;
import org.json.JSONException;
import org.json.JSONObject;
import u.aly.av;

/* loaded from: classes.dex */
public abstract class BaseActivity extends com.hy.frame.common.BaseActivity implements IMyHttpListener {
    private InputMethodManager inputMethodManager;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(1);
    }

    protected void initHeader(@StringRes int title, @StringRes int right) {
        setHeaderLeftTxt(R.string.back);
        setHeaderRightTxt(right);
        setTitle(title);
    }

    protected void initHeaderBack(@StringRes int title, @DrawableRes int right) {
        setHeaderLeft(R.drawable.icon_back);
        setHeaderRight(right);
        setTitle(title);
    }

    protected void initHeaderBackTxt(@StringRes int title, @StringRes int right) {
        setHeaderLeft(R.drawable.icon_back);
        setHeaderRightTxt(right);
        setTitle(title);
    }

    protected UserInfo getUserInfo() {
        return getUserInfo(false, false);
    }

    protected UserInfo getUserInfo(boolean userPic) {
        return getUserInfo(userPic, false);
    }

    protected UserInfo getUserInfo(boolean userPic, boolean thirdParty) {
        return DemoHelper.getInstance().getCurrentUserInfo(userPic, thirdParty);
    }

    public boolean isNoLogin() {
        return !isLogin();
    }

    public boolean isLogin() {
        return new AppShare(this.context).getString(Constant.USER_TOKEN) != null;
    }

    protected void hideSoftKeyboard() {
        if (getWindow().getAttributes().softInputMode != 2 && getCurrentFocus() != null) {
            if (this.inputMethodManager == null) {
                this.inputMethodManager = (InputMethodManager) getSystemService("input_method");
            }
            this.inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.hy.frame.common.BaseActivity
    public MyHttpClient getClient() {
        if (super.getClient() == null) {
            setClient(new MyHttpClient(this.context, this));
        }
        return super.getClient();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        StatService.onResume((Context) this);
        MobclickAgent.onResume(this.context);
        EaseUI.getInstance().getNotifier().reset();
        DemoHelper.getInstance().checkConflict();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        StatService.onPause((Context) this);
        MobclickAgent.onPause(this.context);
    }

    @Override // com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
    }

    @Override // com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        if (result == null) {
            MyToast.show(this.context, (int) R.string.not_connect_need_click);
            return;
        }
        MyToast.show(this.context, result.getMsg());
        try {
            String str = result.getObjStr();
            MyLog.e(str);
            if (!TextUtils.isEmpty(str) && "user_unlogin_error".equals(new JSONObject(str).optString(av.aG))) {
                DemoHelper.getInstance().onConnectionConflict();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initFontScale() {
        Configuration configuration = getResources().getConfiguration();
        configuration.fontScale = 1.0f;
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        getBaseContext().getResources().updateConfiguration(configuration, metrics);
    }
}

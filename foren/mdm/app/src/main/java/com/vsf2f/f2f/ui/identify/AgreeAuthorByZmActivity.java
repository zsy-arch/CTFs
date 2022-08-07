package com.vsf2f.f2f.ui.identify;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.IdentifyBean;
import java.net.URLEncoder;
import java.util.List;

/* loaded from: classes2.dex */
public class AgreeAuthorByZmActivity extends BaseActivity {
    private int onstartCount = 0;
    private boolean isNewIntent = false;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_agree_author_by_zm;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.title_zhima_author, 0);
        setOnClickListener(R.id.btn_author);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.isNewIntent = true;
        Uri uri = intent.getData();
        if (uri != null) {
            uri.toString();
            checkResult();
        }
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.onstartCount++;
        if (this.onstartCount > 1 && !this.isNewIntent) {
            checkResult();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_author /* 2131755874 */:
                commitIdentify();
                return;
            default:
                return;
        }
    }

    private void commitIdentify() {
        AjaxParams params = new AjaxParams();
        getClient().setShowDialog(true);
        params.put("userName", DemoHelper.getInstance().getCurrentUserName());
        getClient().post(R.string.ZHIMA_CERTIFY, ComUtil.getXDDApi(this.context, getString(R.string.ZHIMA_CERTIFY)), params, IdentifyBean.class);
    }

    private void checkResult() {
        AjaxParams params = new AjaxParams();
        getClient().setShowDialog(true);
        params.put("userName", DemoHelper.getInstance().getCurrentUserName());
        getClient().post(R.string.ZHIMA_CALLBACK, ComUtil.getXDDApi(this.context, getString(R.string.ZHIMA_CALLBACK)), params, String.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        if (result.getErrorCode() == 0) {
            switch (result.getRequestCode()) {
                case R.string.ZHIMA_CALLBACK /* 2131296543 */:
                    MyToast.show(getApplicationContext(), "认证成功");
                    getApp().removeFinish(BindZhiMaActivity.class);
                    finish();
                    return;
                case R.string.ZHIMA_CERTIFY /* 2131296544 */:
                    IdentifyBean bean = (IdentifyBean) result.getObj();
                    if (bean != null) {
                        doVerify(bean.getReturnUrl());
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        if (this.isNewIntent) {
            super.onRequestError(result);
        }
    }

    private void doVerify(String url) {
        if (hasApplication()) {
            Intent action = new Intent("android.intent.action.VIEW");
            action.setData(Uri.parse("alipays://platformapi/startapp?appId=20000067&url=" + URLEncoder.encode(url)));
            startActivity(action);
            return;
        }
        new EaseAlertDialog(this.context, R.string.toast_install_ali, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.identify.AgreeAuthorByZmActivity.1
            @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
            public void onResult(boolean confirmed, Bundle bundle) {
                if (confirmed) {
                    Intent action2 = new Intent("android.intent.action.VIEW");
                    action2.setData(Uri.parse("https://m.alipay.com"));
                    AgreeAuthorByZmActivity.this.startActivity(action2);
                }
            }
        }, true).setOkBtn("好的").setCancelBtn("算了").show();
    }

    private boolean hasApplication() {
        PackageManager manager = getPackageManager();
        Intent action = new Intent("android.intent.action.VIEW");
        action.setData(Uri.parse("alipays://"));
        List<ResolveInfo> list = manager.queryIntentActivities(action, 64);
        return list != null && list.size() > 0;
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        onBackPressed();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        getApp().removeFinish(BindZhiMaActivity.class);
        super.onBackPressed();
    }
}

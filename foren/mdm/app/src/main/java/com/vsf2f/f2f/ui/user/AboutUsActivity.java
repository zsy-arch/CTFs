package com.vsf2f.f2f.ui.user;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.PermissionUtil;
import com.em.DemoHelper;
import com.tencent.smtt.sdk.WebView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ConfigBean;

/* loaded from: classes2.dex */
public class AboutUsActivity extends BaseActivity {
    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.setting_about_face, 0);
        setOnClickListener(R.id.about_llyCall);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        ConfigBean configBean = DemoHelper.getInstance().readConfig();
        if (!TextUtils.isEmpty(configBean.getWeixin())) {
            ((TextView) getView(R.id.about_txtWx)).setText(configBean.getWeixin());
        }
        if (!TextUtils.isEmpty(configBean.getWx_gzh())) {
            ((TextView) getView(R.id.about_txtGzh)).setText(configBean.getWx_gzh());
        }
        if (!TextUtils.isEmpty(configBean.getZc_origin())) {
            ((TextView) getView(R.id.about_txtWeb)).setText(configBean.getZc_origin());
        }
        if (!TextUtils.isEmpty(configBean.getCs_tel())) {
            ((TextView) getView(R.id.about_txtTel)).setText(configBean.getCs_tel());
        }
        if (!TextUtils.isEmpty(configBean.getCp_email())) {
            ((TextView) getView(R.id.about_txtEmail)).setText(configBean.getCp_email());
        }
        ((TextView) getView(R.id.about_txtVersion)).setText(String.format("V%s 版本", DemoHelper.getInstance().getVersion()));
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        if (v.getId() == R.id.about_llyCall) {
            final String tel = ((TextView) getView(R.id.about_txtTel)).getText().toString();
            new AlertDialog.Builder(this).setTitle("确认拨打客服电话？").setIcon(R.drawable.ease_chat_voice_call_receive).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() { // from class: com.vsf2f.f2f.ui.user.AboutUsActivity.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, int which) {
                    if (PermissionUtil.getCallPhonePermissions(AboutUsActivity.this, 111)) {
                        Intent intent = new Intent("android.intent.action.CALL");
                        intent.setData(Uri.parse(WebView.SCHEME_TEL + tel));
                        if (ActivityCompat.checkSelfPermission(AboutUsActivity.this, "android.permission.CALL_PHONE") == 0) {
                            AboutUsActivity.this.startActivity(intent);
                        }
                    }
                }
            }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
        }
    }
}

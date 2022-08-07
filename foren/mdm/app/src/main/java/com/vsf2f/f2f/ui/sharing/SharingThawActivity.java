package com.vsf2f.f2f.ui.sharing;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.SharingBean;
import com.vsf2f.f2f.ui.dialog.PayPwdDialog;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;

/* loaded from: classes2.dex */
public class SharingThawActivity extends BaseActivity {
    private EditText etNum;
    private EditText etReason;
    private int sharingEnableThaw;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_sharing_thaw;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.sharing_title_thaw, R.drawable.icon_help_white);
        this.etNum = (EditText) findViewById(R.id.sharing_thaw_etNum);
        this.etReason = (EditText) findViewById(R.id.sharing_thaw_etReason);
        setOnClickListener(R.id.sharing_thaw_btnCommit);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        SharingBean extInfo = UserShared.getInstance().readShareMoney();
        ((TextView) findViewById(R.id.sharing_thaw_tvEnable)).setText(extInfo.getShareMoneyEnable() + "");
        this.sharingEnableThaw = extInfo.getShareMoneyUnFrozen();
        ((TextView) findViewById(R.id.sharing_thaw_tvFrozen)).setText(this.sharingEnableThaw + "");
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        requestData();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void requestThaw(String num, String reason, String pwdMd5) {
        AjaxParams params = new AjaxParams();
        params.put("num", num);
        params.put("reason", reason);
        params.put("userName", DemoHelper.getInstance().getCurrentUserName());
        params.put("payPwd", pwdMd5);
        params.put("timestamp", System.currentTimeMillis() + "");
        params.put("nonceStr", ComUtil.getRandomString(32));
        params.put("sign", ComUtil.encryptParam(params));
        getClient().setShowDialog(true);
        getClient().post(R.string.API_SHARING_THAW, ComUtil.getZCApi(this.context, getString(R.string.API_SHARING_THAW)), params, String.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_SHARING_THAW /* 2131296431 */:
                MyToast.show(this.context, "提交申请成功，请等待审核");
                finish();
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.FLAG_TITLE, getString(R.string.sharing_title_thaw));
        bundle.putString(Constant.FLAG, ComUtil.getZCApi(this.context, getString(R.string.URL_HELP_COMMON_CODE, new Object[]{"gxb_apply_unfrozen"})));
        bundle.putBoolean(Constant.FLAG2, false);
        startAct(WebKitLocalActivity.class, bundle);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.sharing_thaw_btnCommit /* 2131755650 */:
                final String num = this.etNum.getText().toString();
                if (TextUtils.isEmpty(num)) {
                    MyToast.show(this.context, "请输入数量");
                    return;
                }
                try {
                    if (this.sharingEnableThaw < Integer.parseInt(num)) {
                        MyToast.show(this.context, "可解冻的共享宝数量不足");
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                final String reason = this.etReason.getText().toString();
                if (TextUtils.isEmpty(reason)) {
                    MyToast.show(this.context, "请输入描述");
                    return;
                } else {
                    new PayPwdDialog(this.context, new PayPwdDialog.OutPwdListener() { // from class: com.vsf2f.f2f.ui.sharing.SharingThawActivity.1
                        @Override // com.vsf2f.f2f.ui.dialog.PayPwdDialog.OutPwdListener
                        public void outPwd(String password) {
                            SharingThawActivity.this.requestThaw(num, reason, password);
                        }
                    }).show();
                    return;
                }
            default:
                return;
        }
    }
}

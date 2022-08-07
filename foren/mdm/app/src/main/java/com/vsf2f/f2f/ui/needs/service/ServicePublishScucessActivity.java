package com.vsf2f.f2f.ui.needs.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.ShareUtils;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.ui.dialog.MoreOperateDialog;

/* loaded from: classes2.dex */
public class ServicePublishScucessActivity extends BaseActivity {
    private int moId;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_service_scucess;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.publish_scucess, 0);
        setOnClickListener(R.id.ServicePublishScucess_llyscan);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.moId = getBundle().getInt("moId");
        if (getBundle().getBoolean("share")) {
            setHeaderRight(R.drawable.icon_shop_ransmit);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        AjaxParams params = new AjaxParams();
        params.put("moId", this.moId);
        params.put("payType", 1);
        getClient().post(R.string.API_SERVICE_PUBLISH, ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_PUBLISH)), params, DemandDetailBean.class);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.ServicePublishScucess_llyscan /* 2131756153 */:
                Bundle bundle2 = new Bundle();
                bundle2.putInt("id", this.moId);
                startAct(ServiceInfoActivity.class, bundle2);
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_CIRCLES_PUBLIC /* 2131296309 */:
                MyToast.show(this.context, "分享圈子成功");
                return;
            case R.string.API_SERVICE_DETAIL /* 2131296412 */:
                shareCircle((DemandDetailBean) result.getObj());
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        MoreOperateDialog operateDialog = new MoreOperateDialog(this.context);
        operateDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.needs.service.ServicePublishScucessActivity.1
            @Override // com.hy.frame.common.BaseDialog.IConfirmListener
            public void onDlgConfirm(BaseDialog dlg, int flag) {
                if (flag == R.id.more_btnShare2) {
                    ServicePublishScucessActivity.this.getDetailInfo();
                }
            }
        });
        operateDialog.showBtnShare2();
        operateDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDetailInfo() {
        getClient().setShowDialog(true);
        getClient().get(R.string.API_SERVICE_DETAIL, ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_DETAIL)) + "?shareServiceId=" + this.moId, DemandDetailBean.class);
    }

    public void shareCircle(DemandDetailBean detailBean) {
        getClient().setShowDialog(true);
        getClient().post(R.string.API_CIRCLES_PUBLIC, ComUtil.getF2FApi(this.context, getString(R.string.API_CIRCLES_PUBLIC)), new ShareUtils(this.context).shareNeeds(detailBean, NotificationCompat.CATEGORY_SERVICE), String.class, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            Bundle bundle2 = new Bundle();
            bundle2.putInt("id", this.moId);
            startAct(ServiceInfoActivity.class, bundle2);
            finish();
        }
    }
}

package com.vsf2f.f2f.ui.needs.demand;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.ShareUtils;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.ui.dialog.MoreOperateDialog;

/* loaded from: classes2.dex */
public class DemandPublishScucessActivity extends BaseActivity {
    private LinearLayout _llyToFace;
    private int moId;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_demand_scucess;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.publish_scucess, 0);
        this._llyToFace = (LinearLayout) getViewAndClick(R.id.DemandPublishScucess_llyToFace);
        setOnClickListener(R.id.DemandPublishScucess_llyPay);
        setOnClickListener(R.id.DemandPublishScucess_btnScan);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.moId = getBundle().getInt("moId");
        if (!getBundle().getBoolean("isAbroad")) {
            this._llyToFace.setVisibility(8);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        AjaxParams params = new AjaxParams();
        params.put("moId", this.moId);
        params.put("payType", 1);
        getClient().post(R.string.API_DEMAND_PUBLISH, ComUtil.getXDDApi(this.context, getString(R.string.API_DEMAND_PUBLISH)), params, DemandDetailBean.class);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.DemandPublishScucess_llyPay /* 2131755979 */:
                    Bundle bundle = getBundle();
                    bundle.putInt(Constant.FLAG_TITLE, R.string.bail_demand);
                    startActForResult(EntrustActivity.class, bundle, 0);
                    break;
                case R.id.DemandPublishScucess_btnScan /* 2131755980 */:
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("id", this.moId);
                    startAct(DemandDetailActivity.class, bundle2);
                    break;
                case R.id.DemandPublishScucess_llyToFace /* 2131755981 */:
                    requestData();
                    break;
            }
        } catch (Exception e) {
            MyLog.e(e.toString());
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_CIRCLES_PUBLIC /* 2131296309 */:
                MyToast.show(this.context, "分享圈子成功");
                return;
            case R.string.API_DEMAND_DETAIL /* 2131296324 */:
                shareCircle((DemandDetailBean) result.getObj());
                return;
            case R.string.API_DEMAND_PUBLISH /* 2131296330 */:
                MyToast.show(getApp(), "成功修改为线下支付");
                finish();
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        MoreOperateDialog operateDialog = new MoreOperateDialog(this.context);
        operateDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandPublishScucessActivity.1
            @Override // com.hy.frame.common.BaseDialog.IConfirmListener
            public void onDlgConfirm(BaseDialog dlg, int flag) {
                if (flag == R.id.more_btnShare2) {
                    DemandPublishScucessActivity.this.getDetailInfo();
                }
            }
        });
        operateDialog.showBtnShare2();
        operateDialog.show();
    }

    public void getDetailInfo() {
        getClient().setShowDialog(true);
        getClient().get(R.string.API_DEMAND_DETAIL, ComUtil.getXDDApi(this.context, getString(R.string.API_DEMAND_DETAIL)) + "?moId=" + this.moId, DemandDetailBean.class);
    }

    public void shareCircle(DemandDetailBean detailBean) {
        getClient().setShowDialog(true);
        getClient().post(R.string.API_CIRCLES_PUBLIC, ComUtil.getF2FApi(this.context, getString(R.string.API_CIRCLES_PUBLIC)), new ShareUtils(this.context).shareNeeds(detailBean, "demand"), String.class, false);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            Bundle bundle2 = new Bundle();
            bundle2.putInt("id", this.moId);
            startAct(DemandDetailActivity.class, bundle2);
            finish();
        }
    }
}

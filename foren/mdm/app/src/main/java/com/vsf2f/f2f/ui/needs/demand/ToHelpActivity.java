package com.vsf2f.f2f.ui.needs.demand;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseAlertDialog;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ApplyPayInfoBean;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.bean.DemandUserInfo;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.view.IdentyStateView;

/* loaded from: classes2.dex */
public class ToHelpActivity extends BaseActivity {
    private Button btn_sure;
    private DemandDetailBean datasBean;
    private TextView et_msg;
    private String price;
    private TextView tv_tel;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_to_help;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.do_help, 0);
        ImageView iv_header = (ImageView) getView(R.id.iv_header);
        this.tv_tel = (TextView) getView(R.id.tv_tel);
        TextView tv_price = (TextView) getView(R.id.tv_price);
        TextView tv_nickname = (TextView) getView(R.id.tv_nickname);
        TextView tv_zhima = (TextView) getView(R.id.tv_zhima);
        this.et_msg = (TextView) getView(R.id.et_msg);
        this.btn_sure = (Button) getViewAndClick(R.id.btn_sure);
        this.datasBean = (DemandDetailBean) getBundle().getSerializable("data");
        if (this.datasBean != null) {
            DemandUserInfo userInfo = this.datasBean.getPublishUserObj();
            Glide.with((FragmentActivity) this).load(userInfo.getUserPic().getPath()).error((int) R.mipmap.def_head).into(iv_header);
            this.price = this.datasBean.getReward();
            tv_price.setText("￥" + this.price);
            tv_nickname.setText(userInfo.getNickName());
            tv_zhima.setText(userInfo.getZmScore() + "分");
            ((IdentyStateView) getView(R.id.identyStateView)).setStatus(userInfo.getCertMobile(), userInfo.getCertRealname(), userInfo.getCertZhima(), userInfo.getCertAlipay(), userInfo.getCertWechat(), userInfo.getCertQq());
            String tel = getUserInfo().getPhone();
            if (TextUtils.isEmpty(tel)) {
                new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.needs.demand.ToHelpActivity.1
                    @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                    public void onResult(boolean confirmed, Bundle bundle) {
                        if (confirmed) {
                            ToHelpActivity.this.startActForResult(BindPhoneActivity.class, 123);
                        } else {
                            ToHelpActivity.this.finish();
                        }
                    }
                }, true).show();
                this.btn_sure.setEnabled(false);
            }
            this.tv_tel.setText(tel);
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
            case R.id.btn_sure /* 2131756169 */:
                demandOffer();
                return;
            default:
                return;
        }
    }

    private void demandOffer() {
        if (this.datasBean != null) {
            getClient().setShowDialog(false);
            AjaxParams params = new AjaxParams();
            params.put("shareId", this.datasBean.getMoId() + "");
            params.put("shareSnapshotId", this.datasBean.getDemandSnapShotId() + "");
            params.put("description", this.et_msg.getText().toString());
            getClient().post(R.string.API_DEMAND_OFFER, ComUtil.getXDDApi(this.context, getString(R.string.API_DEMAND_OFFER)), params, ApplyPayInfoBean.class);
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        if (result.getErrorCode() == 0) {
            ApplyPayInfoBean bean = (ApplyPayInfoBean) result.getObj();
            bean.setBizId(bean.getMoId() + "");
            bean.setPayTitle("服务商抢单");
            Bundle bundle = new Bundle();
            bundle.putInt(Constant.FLAG_TITLE, R.string.bail_service);
            bundle.putSerializable("payinfo", bean);
            startAct(HelpSuccessActivity.class, bundle);
            finish();
            return;
        }
        super.onRequestError(result);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 123) {
            return;
        }
        if (resultCode == -1) {
            this.tv_tel.setText(getUserInfo().getPhone());
            this.btn_sure.setEnabled(true);
            return;
        }
        finish();
    }
}

package com.vsf2f.f2f.ui.needs.demand;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.server.audio.MusicPlayer;
import com.cdlinglu.server.audio.VoicePlayer;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseAlertDialog;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.KeyValueView;
import com.hy.frame.view.MyGridView;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.adapter.DemandPicAdapter;
import com.vsf2f.f2f.bean.ApplyPayInfoBean;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.ui.dialog.BasePopupMenu;
import com.vsf2f.f2f.ui.dialog.DemandCloseDialog;
import com.vsf2f.f2f.ui.dialog.DemandOprateDialog;
import com.vsf2f.f2f.ui.dialog.DemandRepriceDialog;
import com.vsf2f.f2f.ui.utils.photo.PhotoPreviewIntent;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class DemandDetailActivity extends BaseActivity implements BasePopupMenu.PopupListener {
    private static final int REQUEST_PREVIEW_CODE = 1001;
    private KeyValueView _kvMode;
    private KeyValueView _kvPrice;
    private KeyValueView _kvTime;
    private KeyValueView _kvType;
    private MusicPlayer _voice;
    private String closeReason;
    private DemandCloseDialog demandCloseDialog;
    private DemandDetailBean detailBean;
    private MyGridView mgv_pic;
    private WindowManager.LayoutParams paramsAlpha;
    private String price;
    private DemandRepriceDialog repriceDialog;
    private TextView tv_address;
    private TextView tv_content;
    private TextView tv_demand_title;
    private int cuOrhistory = 0;
    private int demandId = 0;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_demand_detail;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.title_demand_detail, R.drawable.icon_right_menu);
        this.cuOrhistory = getBundle().getInt("isHistory");
        this.mgv_pic = (MyGridView) getView(R.id.mgv_pic);
        this.tv_demand_title = (TextView) getView(R.id.tv_demand_title);
        this._kvType = (KeyValueView) getView(R.id.DemandDetail_kvType);
        this._kvMode = (KeyValueView) getView(R.id.DemandDetail_kvMode);
        this._kvPrice = (KeyValueView) getView(R.id.DemandDetail_kvPrice);
        this._kvTime = (KeyValueView) getView(R.id.DemandDetail_kvTime);
        this._voice = (MusicPlayer) getViewAndClick(R.id.voicePlayer);
        this.tv_content = (TextView) getView(R.id.tv_content);
        this.tv_address = (TextView) getView(R.id.tv_address);
        this.paramsAlpha = getWindow().getAttributes();
        this.mgv_pic.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandDetailActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (DemandDetailActivity.this.detailBean != null && DemandDetailActivity.this.detailBean.getImgUrlList().size() != 0) {
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(DemandDetailActivity.this.context);
                    intent.setCurrentItem(i);
                    intent.setDeleteMode(false);
                    intent.setPhotoPaths((ArrayList) DemandDetailActivity.this.detailBean.getImgUrlList());
                    DemandDetailActivity.this.startActivityForResult(intent, 1001);
                }
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.demandId = getBundle().getInt("id");
        getDetailInfo();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (this.detailBean != null) {
            this.tv_demand_title.setText(this.detailBean.getTitle());
            if (!TextUtils.isEmpty(this.detailBean.getDescription())) {
                this.tv_content.setText(this.detailBean.getDescription());
            }
            this.tv_address.setText(this.detailBean.getAddress());
            this._kvType.setValue(this.detailBean.getTypeName());
            this._kvMode.setValue(this.detailBean.getServiceModeStr());
            this._kvTime.setValue(this.detailBean.getPublishTime());
            this._kvPrice.setValue("￥" + this.detailBean.getReward() + "/" + this.detailBean.getUnit());
            this.mgv_pic.setAdapter((ListAdapter) new DemandPicAdapter(this, this.detailBean.getImgUrlList()));
            if (TextUtils.isEmpty(this.detailBean.getVoiceDuration()) || TextUtils.isEmpty(this.detailBean.getVoiceFullUrl())) {
                this._voice.setVisibility(4);
            } else {
                this._voice.setDuration(this.detailBean.getVoiceDuration());
                this._voice.setVisibility(0);
            }
            if (this.detailBean.getStatus() == 10 && this.detailBean.getBizType() == 50) {
                showPayment();
            }
        }
    }

    private void showPayment() {
        new EaseAlertDialog(this.context, R.string.payment_spread_prompt, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandDetailActivity.2
            @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
            public void onResult(boolean confirmed, Bundle bundle) {
                if (confirmed) {
                    DemandDetailActivity.this.toPay();
                }
            }
        }, true).show();
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        new DemandOprateDialog(this, this).showAsDropDown(getView(R.id.head_vRight));
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        onLeftClick();
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        VoicePlayer.stopVoice();
    }

    @Override // com.hy.frame.common.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        VoicePlayer.stopVoice();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        if (this.cuOrhistory != 1 || v.getId() == R.id.voicePlayer) {
            switch (v.getId()) {
                case R.id.voicePlayer /* 2131755923 */:
                    new VoicePlayer(this, this.detailBean.getVoiceFullUrl(), this._voice);
                    return;
                case R.id.btn_cancel_price /* 2131756265 */:
                    this.repriceDialog.dismiss();
                    return;
                case R.id.btn_sure_price /* 2131756266 */:
                    this.repriceDialog.dismiss();
                    resetPrice();
                    return;
                default:
                    return;
            }
        } else {
            MyToast.show(getApp(), "操作无效，当前为历史需求");
        }
    }

    private void resetPrice() {
        this.price = this.repriceDialog.getPrice();
        if (TextUtils.isEmpty(this.price)) {
            MyToast.show(this.context, (int) R.string.error_no_price);
        } else if (10.0d > Double.valueOf(this.price).doubleValue()) {
            MyToast.show(this.context, (int) R.string.toast_input_ten_money);
        } else {
            AjaxParams params = new AjaxParams();
            params.put("shareId", this.demandId);
            params.put("reward", HyUtil.formatToMoney(this.price));
            getClient().setShowDialog(true);
            getClient().post(R.string.API_RESET_REWARD, ComUtil.getXDDApi(this.context, getString(R.string.API_RESET_REWARD)), params, ApplyPayInfoBean.class, false);
        }
    }

    public void closeDemand() {
        AjaxParams params = new AjaxParams();
        params.put("shareId", this.demandId);
        params.put("closeReason", this.closeReason);
        getClient().setShowDialog(true);
        getClient().post(R.string.API_CLOSE_REWARD, ComUtil.getXDDApi(this.context, getString(R.string.API_CLOSE_REWARD)), params, String.class);
    }

    public void toPay() {
        if (this.detailBean.getBizType() == 5) {
            this.detailBean.setBizId(this.detailBean.getMoId());
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("needinfo", this.detailBean);
        bundle.putInt(Constant.FLAG_TITLE, R.string.closing_price);
        startActForResult(EntrustActivity.class, bundle, 111);
    }

    private void getDetailInfo() {
        getClient().setShowDialog(true);
        getClient().get(R.string.API_DEMAND_DETAIL, ComUtil.getXDDApi(this.context, getString(R.string.API_DEMAND_DETAIL)) + "?moId=" + this.demandId, DemandDetailBean.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        if (result.getErrorCode() == 0) {
            switch (result.getRequestCode()) {
                case R.string.API_CLOSE_REWARD /* 2131296313 */:
                    MyToast.show(getApplicationContext(), "关闭需求成功");
                    getApp().removeFinish(DemandPublishScucessActivity.class);
                    getApp().removeFinish(DemandInfoActivity.class);
                    finish();
                    return;
                case R.string.API_DEMAND_DETAIL /* 2131296324 */:
                    this.detailBean = (DemandDetailBean) result.getObj();
                    updateUI();
                    return;
                case R.string.API_RESET_REWARD /* 2131296398 */:
                    ApplyPayInfoBean payInfo = (ApplyPayInfoBean) result.getObj();
                    this._kvPrice.setValue("￥" + this.price + "/" + this.detailBean.getUnit());
                    if (payInfo.getNeedPay() != 0) {
                        this.detailBean.setBizId(payInfo.getMoId());
                        this.detailBean.setBizType(payInfo.getBizType());
                        this.detailBean.setPayAmount(payInfo.getPayAmount());
                        this.detailBean.setBatchNo(payInfo.getBatchNo());
                        getApp().removeFinish(DemandPublishScucessActivity.class);
                        getApp().removeFinish(DemandInfoActivity.class);
                        showPayment();
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
        int requestCode = result.getRequestCode();
        MyToast.show(getApplicationContext(), result.getMsg());
        switch (requestCode) {
            case R.string.API_CLOSE_REWARD /* 2131296313 */:
            case R.string.API_RESET_REWARD /* 2131296398 */:
            default:
                return;
            case R.string.API_DEMAND_DETAIL /* 2131296324 */:
                finish();
                return;
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            getApp().removeFinish(DemandPublishScucessActivity.class);
            getApp().removeFinish(DemandInfoActivity.class);
            getDetailInfo();
        }
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu.PopupListener
    public void onClickPopup(View v) {
        int status = this.detailBean.getStatus();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.tv_entrust /* 2131756227 */:
                if (this.detailBean.getPayType() == 1) {
                    MyToast.show(getApplicationContext(), "当前需求为线下支付，无需支付");
                    return;
                } else if (status > 10) {
                    MyToast.show(getApplicationContext(), "当前需求已支付");
                    return;
                } else {
                    toPay();
                    return;
                }
            case R.id.tv_reprice /* 2131756228 */:
                if (status > 11) {
                    MyToast.show(getApplicationContext(), "当前需求正在服务中，不支持重定价");
                    return;
                }
                this.repriceDialog = new DemandRepriceDialog(this, this);
                this.repriceDialog.show();
                return;
            case R.id.tv_complete /* 2131756229 */:
                if (status > 11) {
                    MyToast.show(getApplicationContext(), "当前需求正在服务中，不能修改");
                    return;
                }
                bundle.putInt("moId", this.detailBean.getMoId());
                startActForResult(DemandModifyActivity.class, bundle, 0);
                return;
            case R.id.tv_close /* 2131756230 */:
                if (status > 11) {
                    MyToast.show(getApplicationContext(), "当前需求正在服务中，不能关闭");
                    return;
                }
                if (this.demandCloseDialog == null) {
                    this.demandCloseDialog = new DemandCloseDialog(this, new DemandCloseDialog.CloseListener() { // from class: com.vsf2f.f2f.ui.needs.demand.DemandDetailActivity.3
                        @Override // com.vsf2f.f2f.ui.dialog.DemandCloseDialog.CloseListener
                        public void cancelDemand(String checkReason) {
                            DemandDetailActivity.this.closeReason = checkReason;
                            DemandDetailActivity.this.closeDemand();
                        }
                    });
                }
                this.demandCloseDialog.show();
                return;
            default:
                return;
        }
    }
}

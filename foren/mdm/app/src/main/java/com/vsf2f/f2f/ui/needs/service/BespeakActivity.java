package com.vsf2f.f2f.ui.needs.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.common.MyApplication;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseAlertDialog;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.KeyValueView;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ApplyPayInfoBean;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.bean.DemandUserInfo;
import com.vsf2f.f2f.bean.MyLocation;
import com.vsf2f.f2f.ui.needs.demand.ChoiceLocationActivity;
import com.vsf2f.f2f.ui.needs.demand.EntrustActivity;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.view.IdentyStateView;

/* loaded from: classes2.dex */
public class BespeakActivity extends BaseActivity {
    private static final int REQUEST_BIND_PHONE = 1011;
    private static final int REQUEST_CODE_ADDRESS = 1010;
    private Button btn_sure;
    private String contactPhone;
    private String contactUser;
    private DemandDetailBean datasBean;
    private EditText ed_tel;
    private EditText ed_telor;
    private TextView et_msg;
    private KeyValueView kv_address;
    private MyLocation myLocation;
    private String price;
    private String serviceAddress;
    private TextView tv_buynum;
    private TextView tv_cusStock;
    private TextView tv_title;
    private int buyNum = 1;
    private String area = "";

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_to_bespeak;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.do_bespeak, 0);
        ImageView iv_header = (ImageView) getView(R.id.iv_header);
        this.ed_tel = (EditText) getView(R.id.ed_tel);
        this.ed_telor = (EditText) getView(R.id.ed_telor);
        this.tv_title = (TextView) getView(R.id.service_tv_title);
        this.tv_cusStock = (TextView) getView(R.id.tv_cusStock);
        this.kv_address = (KeyValueView) getViewAndClick(R.id.kv_address);
        this.tv_buynum = (TextView) getView(R.id.tv_buynum);
        this.et_msg = (TextView) getView(R.id.et_msg);
        this.btn_sure = (Button) getViewAndClick(R.id.btn_sure);
        TextView tv_price = (TextView) getView(R.id.tv_price);
        TextView tv_nickname = (TextView) getView(R.id.tv_nickname);
        TextView tv_zhima = (TextView) getView(R.id.tv_zhima);
        this.datasBean = (DemandDetailBean) getBundle().getSerializable("data");
        this.myLocation = MyApplication.getMyLocation();
        if (this.myLocation != null) {
            this.serviceAddress = this.myLocation.getAddress();
            this.kv_address.setValue(this.serviceAddress);
        }
        if (this.datasBean != null) {
            DemandUserInfo userInfo = this.datasBean.getPublishUserObj();
            Glide.with((FragmentActivity) this).load(userInfo.getUserPic().getPath()).error((int) R.mipmap.def_head).into(iv_header);
            this.price = this.datasBean.getReward();
            tv_price.setText("￥" + this.price);
            tv_nickname.setText(userInfo.getNickName());
            tv_zhima.setText(userInfo.getZmScore() + "分");
            ((IdentyStateView) getView(R.id.identyStateView)).setStatus(userInfo.getCertMobile(), userInfo.getCertRealname(), userInfo.getCertZhima(), userInfo.getCertAlipay(), userInfo.getCertWechat(), userInfo.getCertQq());
        }
        this.contactPhone = getUserInfo().getPhone();
        this.contactUser = getUserInfo().getName();
        if (TextUtils.isEmpty(this.contactPhone)) {
            showNoPhone();
        }
        this.ed_tel.setText(this.contactPhone);
        this.ed_telor.setText(this.contactUser);
        this.tv_title.setText(this.datasBean.getTitle());
        this.tv_buynum.setText(this.buyNum + "");
        this.tv_cusStock.setText("当前库存：" + this.datasBean.getInventory());
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
            case R.id.kv_address /* 2131756167 */:
                startActForResult(ChoiceLocationActivity.class, 1010);
                return;
            case R.id.et_msg /* 2131756168 */:
            default:
                return;
            case R.id.btn_sure /* 2131756169 */:
                demandOffer();
                return;
        }
    }

    protected void showNoPhone() {
        this.btn_sure.setEnabled(false);
        new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.needs.service.BespeakActivity.1
            @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
            public void onResult(boolean confirmed, Bundle bundle) {
                if (confirmed) {
                    BespeakActivity.this.startActForResult(BindPhoneActivity.class, 1011);
                } else {
                    BespeakActivity.this.finish();
                }
            }
        }, true).show();
    }

    private void demandOffer() {
        if (this.datasBean != null) {
            String msg = this.et_msg.getText().toString();
            String user = this.ed_telor.getText().toString();
            this.contactPhone = this.ed_tel.getText().toString();
            if (TextUtils.isEmpty(this.serviceAddress)) {
                MyToast.show(this.context, (int) R.string.please_select_address);
            } else if (TextUtils.isEmpty(this.contactPhone)) {
                MyToast.show(this.context, (int) R.string.phone_num_cant_empty);
            } else if (TextUtils.isEmpty(user)) {
                MyToast.show(this.context, (int) R.string.please_input_contact);
            } else {
                getClient().setShowDialog(true);
                AjaxParams params = new AjaxParams();
                params.put("buyNum", this.buyNum);
                params.put("bookMessage", msg);
                params.put("contactUser", user);
                params.put("contactPhone", this.contactPhone);
                params.put("serviceAddress", this.serviceAddress);
                params.put("shareServiceSnapshotId", this.datasBean.getShareServiceSnapshotId() + "");
                getClient().post(R.string.API_SERVICE_BOOKSERVICE, ComUtil.getXDDApi(this.context, getString(R.string.API_SERVICE_BOOKSERVICE)), params, ApplyPayInfoBean.class);
            }
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        ApplyPayInfoBean payInfoBean = (ApplyPayInfoBean) result.getObj();
        payInfoBean.setBizId(payInfoBean.getMoId() + "");
        payInfoBean.setPayTitle(getString(R.string.bespeak_service));
        Bundle bundle = new Bundle();
        bundle.putSerializable("payinfo", payInfoBean);
        bundle.putInt(Constant.FLAG_TITLE, R.string.bail_demand);
        startAct(EntrustActivity.class, bundle);
        finish();
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        PoiItem poiItem;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1011) {
            if (resultCode == -1) {
                this.contactPhone = getUserInfo().getPhone();
                this.ed_tel.setText(this.contactPhone);
                this.btn_sure.setEnabled(true);
                return;
            }
            finish();
        } else if (requestCode == 1010 && resultCode == -1) {
            if (!(data.getBundleExtra("bund") == null || (poiItem = (PoiItem) data.getBundleExtra("bund").getParcelable("key")) == null)) {
                LatLonPoint latLng = poiItem.getLatLonPoint();
                this.area = poiItem.getCityName() + poiItem.getAdName() + poiItem.getBusinessArea();
                this.serviceAddress = this.area + poiItem.getSnippet();
                if (this.myLocation == null) {
                    this.myLocation = new MyLocation();
                }
                this.myLocation.setLatitude(latLng.getLatitude() + "");
                this.myLocation.setLongitude(latLng.getLongitude() + "");
                this.myLocation.setProvince(poiItem.getProvinceName());
                this.myLocation.setCityCode(poiItem.getCityCode());
                this.myLocation.setAdCode(poiItem.getAdCode());
                this.myLocation.setAddress(this.serviceAddress + "");
            }
            this.kv_address.setValue(this.serviceAddress);
        }
    }

    public void add(View view) {
        if (this.buyNum >= this.datasBean.getInventory()) {
            MyToast.show(getApplicationContext(), "少拿几个吧，没有库存了");
            return;
        }
        this.buyNum++;
        this.tv_buynum.setText(this.buyNum + "");
    }

    public void reduce(View view) {
        if (this.buyNum <= 1) {
            MyToast.show(getApplicationContext(), "再减就没啦");
            return;
        }
        this.buyNum--;
        this.tv_buynum.setText(this.buyNum + "");
    }
}

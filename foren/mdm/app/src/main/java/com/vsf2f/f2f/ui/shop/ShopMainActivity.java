package com.vsf2f.f2f.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.ShareUtils;
import com.easeui.widget.EaseAlertDialog;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.MyToast;
import com.tencent.tauth.Tencent;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ShareThirdBean;
import com.vsf2f.f2f.bean.ShopInfoBean;
import com.vsf2f.f2f.ui.dialog.ShareForwardDialog;
import com.vsf2f.f2f.ui.dialog.WarnDialog;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.web.WebUtils;

/* loaded from: classes2.dex */
public class ShopMainActivity extends BaseActivity {
    private ShopInfoBean detailBean;
    private ImageView imgAvatar;
    private ImageView imgVip;
    private ShareForwardDialog shareDialog;
    private ShareUtils shareUtils;
    private TextView txtNickName;
    private String username;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_shop_main;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.shop_manager, R.drawable.icon_shop_ransmit);
        this.imgVip = (ImageView) getView(R.id.shop_main_imgVipLogo);
        this.imgAvatar = (ImageView) getView(R.id.shop_main_imgShopLogo);
        this.txtNickName = (TextView) getView(R.id.shop_main_txtShopName);
        setOnClickListener(R.id.shop_main_navFinanceCenter);
        setOnClickListener(R.id.shop_main_navShopdata);
        setOnClickListener(R.id.shop_main_navShopOrder);
        setOnClickListener(R.id.shop_main_navGoodsManager);
        setOnClickListener(R.id.shop_main_navShopManager);
        setOnClickListener(R.id.shop_main_navShopPreview);
        setOnClickListener(R.id.shop_main_navShopPublish);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.username = DemoHelper.getInstance().getCurrentUserName();
        ((TextView) getView(R.id.shop_main_txtUserName)).setText(this.username);
        initDialog();
        requestData();
    }

    private void initDialog() {
        if (UserShared.getInstance().getIsVerifyState(this.context)) {
            if (UserShared.getInstance().getInt(Constant.CERT_MOBILE) != 1) {
                new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.shop.ShopMainActivity.1
                    @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                    public void onResult(boolean confirmed, Bundle bundle) {
                        if (confirmed) {
                            ShopMainActivity.this.startAct(BindPhoneActivity.class);
                        }
                        ShopMainActivity.this.finish();
                    }
                }, true).show();
            } else if (DemoHelper.getInstance().isOpenStore() != 1) {
                WarnDialog warnDialog = new WarnDialog(this.context, getString(R.string.not_open_shop_prompt), getString(R.string.open_shop_now), true, false, false);
                warnDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.shop.ShopMainActivity.2
                    @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                    public void onDlgConfirm(BaseDialog dlg, int flag) {
                        switch (flag) {
                            case 0:
                                if (UserShared.getInstance().getInt(Constant.CERT_MOBILE) == 1) {
                                    ShopMainActivity.this.startAct(ShopManagerActivity.class);
                                    break;
                                } else {
                                    new EaseAlertDialog(ShopMainActivity.this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.shop.ShopMainActivity.2.1
                                        @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                                        public void onResult(boolean confirmed, Bundle bundle) {
                                            if (confirmed) {
                                                ShopMainActivity.this.startAct(BindPhoneActivity.class);
                                            }
                                        }
                                    }, true).show();
                                    return;
                                }
                        }
                        ShopMainActivity.this.finish();
                    }
                });
                warnDialog.show();
            }
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
        getClient().get(R.string.API_LOAD_SHOP_INFORMATION, ComUtil.getZCApi(this.context, getString(R.string.API_LOAD_SHOP_INFORMATION)), ShopInfoBean.class);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        switch (result.getRequestCode()) {
            case R.string.API_CIRCLES_PUBLIC /* 2131296309 */:
                MyToast.show(this.context, "分享圈子成功");
                return;
            case R.string.API_LOAD_SHOP_INFORMATION /* 2131296370 */:
                this.detailBean = (ShopInfoBean) result.getObj();
                if (this.detailBean != null) {
                    if (!(this.detailBean.getLogo() == null || this.detailBean.getLogo().getSpath() == null)) {
                        ComUtil.displayImage(this.context, this.imgAvatar, this.detailBean.getLogo().getSpath());
                    }
                    if (this.detailBean.getStoreName() != null) {
                        this.txtNickName.setText(this.detailBean.getStoreName());
                    }
                    if (getUserInfo().getLv() >= 3) {
                        this.imgVip.setVisibility(0);
                    }
                    setOnClickListener(R.id.shop_main_llyShopInfo);
                    String shopUrl = "/m/shop/" + this.username + ".mobile";
                    this.shareUtils = new ShareUtils(this.context);
                    ShareThirdBean shareThirdBean = new ShareThirdBean(this.detailBean.getStoreName(), WebUtils.getTokenUrl(this.context, shopUrl), this.detailBean.getLogo() != null ? this.detailBean.getLogo().getPath() : "");
                    shareThirdBean.setContext("我在《面对面》开了属于我的店铺，快来瞧瞧吧！");
                    this.shareUtils.setShare(shareThirdBean);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        showForwardDialog();
    }

    private void showForwardDialog() {
        if (this.shareUtils == null || this.detailBean == null) {
            MyToast.show(this.context, "网络异常，无法获取到分享信息");
        } else if (!"1".equals(this.detailBean.getIsVerify())) {
            MyToast.show(this.context, "店铺未激活，无法分享");
        } else {
            if (this.shareDialog == null) {
                this.shareDialog = new ShareForwardDialog(this);
                this.shareDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.shop.ShopMainActivity.3
                    @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                    public void onDlgConfirm(BaseDialog dlg, int flag) {
                        switch (flag) {
                            case 1:
                                ShopMainActivity.this.getClient().setShowDialog(true);
                                ShopMainActivity.this.getClient().post(R.string.API_CIRCLES_PUBLIC, ComUtil.getF2FApi(ShopMainActivity.this.context, ShopMainActivity.this.getString(R.string.API_CIRCLES_PUBLIC)), ShopMainActivity.this.shareUtils.shareShop(ShopMainActivity.this.detailBean), String.class, false);
                                return;
                            case 2:
                                ComUtil.copySys(ShopMainActivity.this.context, WebUtils.getTokenUrl(ShopMainActivity.this.context, "/m/shop/" + ShopMainActivity.this.username + ".mobile"));
                                return;
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            default:
                                return;
                            case 11:
                                ShopMainActivity.this.shareUtils.shareTask(1);
                                return;
                            case 12:
                                ShopMainActivity.this.shareUtils.shareTask(0);
                                return;
                            case 13:
                                ShopMainActivity.this.shareUtils.qqShare();
                                return;
                            case 14:
                                ShopMainActivity.this.shareUtils.qqZoneShare();
                                return;
                        }
                    }
                });
            }
            this.shareDialog.show();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.shop_main_llyShopInfo /* 2131755670 */:
            case R.id.shop_main_navShopManager /* 2131755679 */:
                bundle.putBoolean(com.hy.frame.util.Constant.FLAG, true);
                startActForResult(ShopManagerActivity.class, bundle, 444);
                return;
            case R.id.shop_main_imgShopLogo /* 2131755671 */:
            case R.id.shop_main_imgVipLogo /* 2131755672 */:
            case R.id.shop_main_txtShopName /* 2131755673 */:
            case R.id.shop_main_txtUserName /* 2131755674 */:
            default:
                return;
            case R.id.shop_main_navFinanceCenter /* 2131755675 */:
                startAct(ShopFinanceActivity.class);
                return;
            case R.id.shop_main_navShopdata /* 2131755676 */:
                startAct(DataStatisticsActivity.class);
                return;
            case R.id.shop_main_navShopOrder /* 2131755677 */:
                String orderUrl = getString(R.string.SHOP_ORDER_URL);
                bundle.putString(com.hy.frame.util.Constant.FLAG_TITLE, "我的订单");
                bundle.putString(com.hy.frame.util.Constant.FLAG, WebUtils.getTokenUrl(this.context, orderUrl));
                startAct(WebKitLocalActivity.class, bundle);
                return;
            case R.id.shop_main_navGoodsManager /* 2131755678 */:
                startActForResult(GoodsListActivity.class, bundle, 444);
                return;
            case R.id.shop_main_navShopPublish /* 2131755680 */:
                startActForResult(GoodsPublishActivity.class, bundle, 444);
                return;
            case R.id.shop_main_navShopPreview /* 2131755681 */:
                bundle.putString(com.hy.frame.util.Constant.FLAG, WebUtils.getTokenUrl(this.context, "/m/shop/" + this.username + ".mobile"));
                bundle.putBoolean(com.hy.frame.util.Constant.FLAG2, true);
                startAct(WebKitLocalActivity.class, bundle);
                return;
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 444 || this.detailBean == null) {
            if (requestCode == 10103 || requestCode == 10104) {
                Tencent.onActivityResultData(requestCode, resultCode, data, this.shareUtils.getListener());
            }
        } else if (UserShared.getInstance().getStoreGoodNum() >= 3) {
            this.detailBean.setIsVerify("1");
        } else {
            this.detailBean.setIsVerify("0");
        }
    }
}

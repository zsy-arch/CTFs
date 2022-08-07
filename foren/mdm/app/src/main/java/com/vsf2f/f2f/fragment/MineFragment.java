package com.vsf2f.f2f.fragment;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.cdlinglu.common.BaseFragment;
import com.cdlinglu.common.MyApplication;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.GuideUtil;
import com.cdlinglu.utils.LocationUtils;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.HyUtil;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.MyLocation;
import com.vsf2f.f2f.bean.UserExtendInfo;
import com.vsf2f.f2f.bean.UserInfo;
import com.vsf2f.f2f.ui.circle.MyCircleActivity;
import com.vsf2f.f2f.ui.needs.NeedsManagerActivity;
import com.vsf2f.f2f.ui.sharing.SharingMainActivity;
import com.vsf2f.f2f.ui.user.MyCodeActivity;
import com.vsf2f.f2f.ui.user.MySaveActivity;
import com.vsf2f.f2f.ui.user.SettingsActivity;
import com.vsf2f.f2f.ui.user.UserDataActivity;
import com.vsf2f.f2f.ui.user.UserVipActivity;
import com.vsf2f.f2f.ui.user.UserWalletActivity;
import com.vsf2f.f2f.ui.utils.AmapUtils;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.view.IdentyStateView;

/* loaded from: classes2.dex */
public class MineFragment extends BaseFragment implements AMapLocationListener {
    private UserInfo detail;
    private int guidIndex;
    private ImageView imgAvatar;
    private ImageView img_guide_discover;
    private ImageView ivCom;
    private ImageView ivVip;
    private ViewGroup.MarginLayoutParams layoutParams;
    private TextView txtComment;
    private TextView txtNickName;
    private TextView txtUserName;
    private TextView txtXddCode;
    private TextView txtcusAddress;
    @DrawableRes
    private int[] guidImg = {R.drawable.img_guide_mine1, R.drawable.img_guide_mine2};
    private int[] marginValue = {125, 350};
    private boolean isRefresh = false;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_main_mine_fragment;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        this.txtNickName = (TextView) getView(R.id.mine_txtNickName);
        this.txtUserName = (TextView) getView(R.id.mine_txtUsername);
        this.txtXddCode = (TextView) getView(R.id.mine_txtXddCode);
        this.txtcusAddress = (TextView) getViewAndClick(R.id.mine_txtcusAddress);
        this.imgAvatar = (ImageView) getView(R.id.mine_imgAvatar);
        this.ivCom = (ImageView) getView(R.id.mine_ivCom);
        this.ivVip = (ImageView) getView(R.id.mine_imgVip);
        setOnClickListener(R.id.mine_llyVip);
        setOnClickListener(R.id.mine_llyInfo);
        setOnClickListener(R.id.mine_llySharing);
        setOnClickListener(R.id.mine_imgQrCode);
        setOnClickListener(R.id.mine_navWallet);
        setOnClickListener(R.id.mine_navCollect);
        setOnClickListener(R.id.mine_navPhotos);
        setOnClickListener(R.id.mine_navManager);
        setOnClickListener(R.id.mine_navSetting);
    }

    @Override // com.hy.frame.common.BaseFragment, android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        ((IdentyStateView) getView(R.id.identyStateView)).setStatus(UserShared.getInstance().getInt(Constant.CERT_MOBILE), UserShared.getInstance().getInt(Constant.CERT_REALNAME), UserShared.getInstance().getInt(Constant.CERT_ZHIMA), UserShared.getInstance().getInt(Constant.CERT_ALIPAY), UserShared.getInstance().getInt(Constant.CERT_WECHAT), UserShared.getInstance().getInt(Constant.CERT_QQ));
        MyLocation myLocation = MyApplication.getMyLocation();
        if (myLocation != null) {
            String address = myLocation.getAddress();
            if (!TextUtils.isEmpty(address)) {
                this.txtcusAddress.setText(address);
            }
        } else {
            AmapUtils.getLocation(this.context, this);
        }
        UserExtendInfo extInfo = UserShared.getInstance().readExtInfo();
        this.txtXddCode.setText(extInfo.getShareMoney() + "个");
        if (extInfo.getCertCompany() == 1) {
            this.ivCom.setVisibility(0);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        updateUI();
        if (GuideUtil.getMineFragment(this.context)) {
            getViewAndClick(R.id.ll_guid).setVisibility(0);
            this.img_guide_discover = (ImageView) getViewAndClick(R.id.img_guide_discover);
            this.img_guide_discover.setVisibility(0);
            this.layoutParams = (ViewGroup.MarginLayoutParams) this.img_guide_discover.getLayoutParams();
            this.img_guide_discover.setImageResource(this.guidImg[0]);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void setRefreshInfo() {
        this.isRefresh = true;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        this.isRefresh = false;
        this.detail = getUserInfo(true);
        if (HyUtil.isEmpty(this.detail.getUserPic().getSpath())) {
            this.imgAvatar.setImageResource(R.mipmap.def_head);
        } else {
            ComUtil.displayHead(this.context, this.imgAvatar, this.detail.getUserPic().getSpath());
        }
        if (this.detail.getLv() > 0) {
            this.ivVip.setVisibility(0);
            ((TextView) getView(R.id.mine_tvVip)).setText(R.string.vip_crown_member);
        } else {
            this.ivVip.setVisibility(8);
        }
        if (this.detail.getName() != null) {
            this.txtNickName.setText(this.detail.getName());
        } else if (this.detail.getNickName() != null) {
            this.txtNickName.setText(this.detail.getNickName());
        } else {
            this.txtNickName.setText(getString(R.string.no_set));
        }
        this.txtUserName.setText(DemoHelper.getInstance().getCurrentUserName());
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.img_guide_discover /* 2131755390 */:
                this.guidIndex++;
                if (this.guidIndex >= this.guidImg.length) {
                    getView(R.id.ll_guid).setVisibility(8);
                    this.img_guide_discover.setVisibility(8);
                    GuideUtil.setMineFragment(this.context);
                    return;
                }
                this.layoutParams.setMargins(0, HyUtil.dip2px(getContext(), this.marginValue[this.guidIndex]), 0, 0);
                this.img_guide_discover.setLayoutParams(this.layoutParams);
                this.img_guide_discover.setImageResource(this.guidImg[this.guidIndex]);
                return;
            case R.id.rl_parent /* 2131755391 */:
            case R.id.top_imgAddFriend /* 2131755392 */:
            case R.id.fragment_contacts_container /* 2131755393 */:
            case R.id.fragment_contacts_list /* 2131755394 */:
            case R.id.mine_llyMyInfo /* 2131755395 */:
            case R.id.mine_imgVip /* 2131755397 */:
            case R.id.mine_txtNickName /* 2131755398 */:
            case R.id.mine_txtUsername /* 2131755399 */:
            case R.id.mine_ivCom /* 2131755400 */:
            case R.id.identyStateView /* 2131755401 */:
            case R.id.mine_txtcusAddress /* 2131755402 */:
            case R.id.mine_tvVip /* 2131755405 */:
            case R.id.mine_txtXddCode /* 2131755407 */:
            default:
                return;
            case R.id.mine_llyInfo /* 2131755396 */:
                startAct(UserDataActivity.class);
                return;
            case R.id.mine_imgQrCode /* 2131755403 */:
                bundle.putInt(com.hy.frame.util.Constant.FLAG_ID, 3);
                bundle.putString(com.hy.frame.util.Constant.FLAG, this.detail.getUserName());
                bundle.putString(com.hy.frame.util.Constant.FLAG2, this.detail.getGuid());
                startAct(MyCodeActivity.class, bundle);
                return;
            case R.id.mine_llyVip /* 2131755404 */:
                startAct(UserVipActivity.class);
                return;
            case R.id.mine_llySharing /* 2131755406 */:
                startAct(SharingMainActivity.class);
                return;
            case R.id.mine_navWallet /* 2131755408 */:
                startAct(UserWalletActivity.class);
                return;
            case R.id.mine_navCollect /* 2131755409 */:
                startAct(MySaveActivity.class);
                return;
            case R.id.mine_navPhotos /* 2131755410 */:
                startAct(MyCircleActivity.class);
                return;
            case R.id.mine_navManager /* 2131755411 */:
                startAct(NeedsManagerActivity.class);
                return;
            case R.id.mine_navSetting /* 2131755412 */:
                startAct(SettingsActivity.class);
                return;
        }
    }

    private void updateLocation(String lat, String lng) {
        AjaxParams params = new AjaxParams();
        params.put("lat", lat);
        params.put("lng", lng);
        getClient().post(R.string.API_USER_CHANGE_DATA, ComUtil.getZCApi(this.context, getString(R.string.API_USER_CHANGE_DATA)), params, Boolean.class);
    }

    @Override // com.cdlinglu.common.BaseFragment, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
    }

    @Override // com.cdlinglu.common.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.isRefresh) {
            updateUI();
        }
    }

    @Override // com.hy.frame.common.BaseFragment, android.view.View.OnClickListener
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override // com.hy.frame.common.IFragmentListener
    public void sendMsg(int i, Object o) {
    }

    @Override // com.amap.api.location.AMapLocationListener
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            MyLocation myLocation = MyApplication.getMyLocation();
            if (myLocation == null || !myLocation.getAddress().equals(aMapLocation.getAddress())) {
                MyApplication.setMyLocation(LocationUtils.locationToAmap(aMapLocation.toString()));
                this.txtcusAddress.setText(aMapLocation.getAddress());
                String str = aMapLocation.getLatitude() + "";
                String str2 = aMapLocation.getLongitude() + "";
            }
        }
    }
}

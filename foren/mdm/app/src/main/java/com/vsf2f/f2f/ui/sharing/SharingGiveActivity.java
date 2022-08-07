package com.vsf2f.f2f.ui.sharing;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.em.ui.PickContactNoCheckboxActivity;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.UserSimpleBean;
import com.vsf2f.f2f.ui.dialog.PayPwdDialog;
import com.vsf2f.f2f.ui.dialog.WarnDialog;
import com.vsf2f.f2f.ui.user.UserVipActivity;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.utils.FingerUtil;

/* loaded from: classes2.dex */
public class SharingGiveActivity extends BaseActivity {
    private int banlance;
    private EditText etNum;
    private EditText etPhone;
    private boolean isNextPage;
    private ImageView ivAvatar;
    private LinearLayout lly_page1;
    private LinearLayout lly_page2;
    private String phone;
    private PayPwdDialog pwdDialog;
    private TextView tvNickname;
    private TextView tvUsername;
    private UserSimpleBean userInfo;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_sharing_give;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.sharing_to_phone, R.drawable.icon_help_white);
        this.lly_page1 = (LinearLayout) findViewById(R.id.sharing_give_page1);
        this.lly_page2 = (LinearLayout) findViewById(R.id.sharing_give_page2);
        this.etPhone = (EditText) getView(R.id.dona_etPhone);
        setOnClickListener(R.id.dona_ivPhone);
        final TextView tvCommit = (TextView) getViewAndClick(R.id.dona_tvCommit);
        this.etPhone.addTextChangedListener(new TextWatcher() { // from class: com.vsf2f.f2f.ui.sharing.SharingGiveActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvCommit.setEnabled(!TextUtils.isEmpty(charSequence.toString()));
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
        this.ivAvatar = (ImageView) getViewAndClick(R.id.dona_ivAvatar);
        this.tvNickname = (TextView) getView(R.id.dona_tvNickname);
        this.tvUsername = (TextView) getView(R.id.dona_tvUsername);
        this.etNum = (EditText) getView(R.id.dona_etNum);
        this.etNum.requestFocus();
        setOnClickListener(R.id.dona_btnCommit);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.banlance = UserShared.getInstance().readShareMoney().getShareMoneyEnable();
        this.etNum.setHint(String.format("请输入转赠数量,最多可转赠%s个", Integer.valueOf(this.banlance)));
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    private void getFriendInfo2(String friendName) {
        getClient().setShowDialog(true);
        getClient().get(R.string.API_GET_USERINFO, ComUtil.getZCApi(this.context, getString(R.string.API_GET_USERINFO, new Object[]{friendName})), UserSimpleBean.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestDona(String pwdMd5, String money) {
        AjaxParams params = new AjaxParams();
        params.put("num", money);
        params.put("userName", DemoHelper.getInstance().getCurrentUserName());
        params.put("receiveUserName", this.phone);
        params.put("payPwd", pwdMd5);
        params.put("timestamp", System.currentTimeMillis() + "");
        params.put("nonceStr", ComUtil.getRandomString(32));
        params.put("sign", ComUtil.encryptParam(params));
        getClient().setShowDialog(true);
        getClient().post(R.string.API_SHARING_DONA, ComUtil.getZCApi(this.context, getString(R.string.API_SHARING_DONA)), params, String.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_GET_USERINFO /* 2131296360 */:
                if (result.getObj() != null) {
                    this.userInfo = (UserSimpleBean) result.getObj();
                    changeNextPage(true);
                    updateUI();
                    return;
                }
                return;
            case R.string.API_SHARING_DONA /* 2131296425 */:
                this.pwdDialog.saveDismiss();
                Toast.makeText(this, "转赠成功", 0).show();
                setResult(-1);
                FingerUtil.showOpenFinger(this.context, new FingerUtil.overListener() { // from class: com.vsf2f.f2f.ui.sharing.SharingGiveActivity.2
                    @Override // com.vsf2f.f2f.ui.utils.FingerUtil.overListener
                    public void over() {
                        SharingGiveActivity.this.finish();
                    }
                });
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
        if (this.userInfo != null) {
            this.tvNickname.setText(this.userInfo.getNickName());
            this.tvUsername.setText(this.userInfo.getUserName());
            Glide.with(this.context).load(this.userInfo.getUserPic().getSpath()).error((int) R.mipmap.def_head2).into(this.ivAvatar);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.dona_ivPhone /* 2131755614 */:
                startActForResult(PickContactNoCheckboxActivity.class, 111);
                return;
            case R.id.dona_tvCommit /* 2131755615 */:
                this.phone = this.etPhone.getText().toString();
                getFriendInfo2(this.phone);
                return;
            case R.id.dona_btnCommit /* 2131755621 */:
                if (getUserInfo().getLv() == 0) {
                    showWarnDlg();
                    return;
                }
                String numStr = this.etNum.getText().toString().trim();
                if (TextUtils.isEmpty(numStr)) {
                    MyToast.show(this.context, (int) R.string.toast_input_num);
                    return;
                } else if (Integer.parseInt(numStr) < 1) {
                    MyToast.show(this.context, (int) R.string.toast_input_zero_num);
                    return;
                } else if (this.banlance < Integer.parseInt(numStr)) {
                    MyToast.show(this.context, "可用的共享宝数量不足");
                    return;
                } else {
                    donation(numStr);
                    return;
                }
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.FLAG_TITLE, getString(R.string.sharing_title_give));
        bundle.putString(Constant.FLAG, ComUtil.getZCApi(this.context, getString(R.string.URL_HELP_COMMON_CODE, new Object[]{"gxb_transfer"})));
        bundle.putBoolean(Constant.FLAG2, false);
        startAct(WebKitLocalActivity.class, bundle);
    }

    public void donation(final String money) {
        this.pwdDialog = new PayPwdDialog(this.context, new PayPwdDialog.OutPwdListener() { // from class: com.vsf2f.f2f.ui.sharing.SharingGiveActivity.3
            @Override // com.vsf2f.f2f.ui.dialog.PayPwdDialog.OutPwdListener
            public void outPwd(String password) {
                SharingGiveActivity.this.requestDona(password, money);
            }
        });
        this.pwdDialog.show();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.isNextPage) {
            changeNextPage(false);
        } else {
            finish();
        }
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        onBackPressed();
    }

    public void changeNextPage(boolean toNext) {
        if (toNext) {
            setTitle(R.string.sharing_title_give);
            this.lly_page1.setVisibility(8);
            this.lly_page2.setVisibility(0);
            this.lly_page1.setAnimation(AnimationUtils.makeOutAnimation(this, false));
            this.lly_page2.setAnimation(AnimationUtils.makeInAnimation(this, false));
        } else {
            setTitle(R.string.sharing_to_phone);
            this.lly_page1.setVisibility(0);
            this.lly_page2.setVisibility(8);
            this.lly_page1.setAnimation(AnimationUtils.makeInAnimation(this, true));
            this.lly_page2.setAnimation(AnimationUtils.makeOutAnimation(this, true));
            this.etNum.setText("");
        }
        this.isNextPage = toNext;
    }

    public void showWarnDlg() {
        WarnDialog warnDialog = new WarnDialog(this.context, getString(R.string.not_open_pms_prompt3), getString(R.string.open_vip_now), true, true, false);
        warnDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.sharing.SharingGiveActivity.4
            @Override // com.hy.frame.common.BaseDialog.IConfirmListener
            public void onDlgConfirm(BaseDialog dlg, int flag) {
                switch (flag) {
                    case 0:
                        SharingGiveActivity.this.startAct(UserVipActivity.class);
                        return;
                    default:
                        return;
                }
            }
        });
        warnDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == -1) {
            this.etPhone.setText(data.getStringExtra("username"));
        }
    }
}

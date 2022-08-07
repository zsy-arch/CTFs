package com.vsf2f.f2f.ui.pay;

import android.content.Intent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.MD5;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.view.NumKeyView;
import com.vsf2f.f2f.ui.view.NumPwdView;

/* loaded from: classes2.dex */
public class PayPwdEditActivity extends BaseActivity {
    private boolean isNextPage;
    private LinearLayout lly_page1;
    private LinearLayout lly_page2;
    private NumPwdView mPwdView;
    private NumPwdView mPwdView2;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_pay_pwd_edit;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.user_cash_pwd, 0);
        this.lly_page1 = (LinearLayout) findViewById(R.id.find_paypwd_page1);
        this.lly_page2 = (LinearLayout) findViewById(R.id.find_paypwd_page2);
        this.mPwdView = (NumPwdView) findViewById(R.id.pwdeditview);
        this.mPwdView2 = (NumPwdView) findViewById(R.id.pwdeditview2);
        ((NumKeyView) findViewById(R.id.keyboardview)).setOnKeyPressListener(new NumKeyView.OnKeyPressListener() { // from class: com.vsf2f.f2f.ui.pay.PayPwdEditActivity.1
            @Override // com.vsf2f.f2f.ui.view.NumKeyView.OnKeyPressListener
            public void onInertKey(String text) {
                if (PayPwdEditActivity.this.isNextPage) {
                    PayPwdEditActivity.this.mPwdView2.onInertKey(text);
                    return;
                }
                PayPwdEditActivity.this.mPwdView.onInertKey(text);
                if (PayPwdEditActivity.this.mPwdView.isFull()) {
                    PayPwdEditActivity.this.changeNextPage(true);
                }
            }

            @Override // com.vsf2f.f2f.ui.view.NumKeyView.OnKeyPressListener
            public void onDeleteKey() {
                if (PayPwdEditActivity.this.isNextPage) {
                    PayPwdEditActivity.this.mPwdView2.onDeleteKey();
                } else {
                    PayPwdEditActivity.this.mPwdView.onDeleteKey();
                }
            }
        });
        setOnClickListener(R.id.find_tvCommit);
        setOnClickListener(R.id.pay_tvFindpwd);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void requestPwd(String oldPwd, String newPwd) {
        if (oldPwd.length() != 6) {
            MyToast.show(this, "请输入6位旧密码");
        } else if (newPwd.length() != 6) {
            MyToast.show(this, "请输入6位新密码");
        } else if (oldPwd.equals(newPwd)) {
            MyToast.show(this.context, (int) R.string.pwd_the_same);
        } else {
            AjaxParams params = new AjaxParams();
            params.put("id", DemoHelper.getInstance().getCurrentUserId());
            params.put("pwdType", "Pay");
            params.put("paypwdVersion", "2");
            try {
                params.put(Constant.PASSWORD, MD5.md5Encode(MD5.md5Encode(oldPwd)));
                params.put("newPassword", MD5.md5Encode(MD5.md5Encode(newPwd)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            getClient().setShowDialog(true);
            getClient().post(R.string.API_USER_CHANGE_PWD, ComUtil.getZCApi(this.context, getString(R.string.API_USER_CHANGE_PWD)), params, String.class);
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_USER_CHANGE_PWD /* 2131296463 */:
                MyToast.show(this.context, "修改密码成功");
                try {
                    UserShared.getInstance().savePaypwdVersion();
                    ComUtil.setPaypwd(this.context, MD5.md5Encode(MD5.md5Encode(this.mPwdView.getPwd())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setResult(-1);
                finish();
                return;
            default:
                return;
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
        result.getRequestCode();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.isNextPage) {
            changeNextPage(false);
            this.mPwdView2.clearPwd();
            return;
        }
        finish();
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        onBackPressed();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.pay_tvFindpwd /* 2131755494 */:
                this.mPwdView.clearPwd();
                startActForResult(PayPwdFindActivity.class, 111);
                return;
            case R.id.find_paypwd_page2 /* 2131755495 */:
            case R.id.pwdeditview2 /* 2131755496 */:
            default:
                return;
            case R.id.find_tvCommit /* 2131755497 */:
                requestPwd(this.mPwdView.getPwd(), this.mPwdView2.getPwd());
                return;
        }
    }

    public void changeNextPage(boolean toNext) {
        if (toNext) {
            this.lly_page1.setVisibility(8);
            this.lly_page2.setVisibility(0);
            this.lly_page1.setAnimation(AnimationUtils.makeOutAnimation(this, false));
            this.lly_page2.setAnimation(AnimationUtils.makeInAnimation(this, false));
        } else {
            this.lly_page1.setVisibility(0);
            this.lly_page2.setVisibility(8);
            this.lly_page1.setAnimation(AnimationUtils.makeInAnimation(this, true));
            this.lly_page2.setAnimation(AnimationUtils.makeOutAnimation(this, true));
        }
        this.isNextPage = toNext;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == -1) {
            finish();
        }
    }
}

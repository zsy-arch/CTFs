package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.MD5;
import com.em.utils.UserShared;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.Constant;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.pay.PayPwdFindActivity;
import com.vsf2f.f2f.ui.pay.PayPwdResetActivity;
import com.vsf2f.f2f.ui.user.pwd.ChangePwdActivity;
import com.vsf2f.f2f.ui.utils.FingerUtil;
import com.vsf2f.f2f.ui.view.NumKeyView;
import com.vsf2f.f2f.ui.view.NumPwdView;

/* loaded from: classes2.dex */
public class PayPwdDialog extends BaseDialog {
    private boolean canFinger;
    private OutPwdListener listener;
    private FingerprintManagerCompat.AuthenticationCallback mFingerListen;
    private FingerUtil mFingerUtil;
    private NumPwdView mPwdView;
    private String md5pwd;
    private TextView txtToPwd;

    /* loaded from: classes2.dex */
    public interface OutPwdListener {
        void outPwd(String str);
    }

    public PayPwdDialog(Context context, OutPwdListener outPwdListener) {
        super(context);
        this.listener = outPwdListener;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_pay_pwd;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-1.0f, -2.0f, 80, R.style.AnimBottom);
        setCanceledOnTouchOutside(false);
        setCancelable(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        this.mPwdView = (NumPwdView) findViewById(R.id.pwdeditview);
        setOnClickListener(R.id.pay_tvFindpwd);
        setOnClickListener(R.id.pay_cancel);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
        if (ComUtil.isFinger(getContext()) && FingerUtil.hasFingerPrints(getContext())) {
            findViewById(R.id.pay_llFinger).setVisibility(0);
            findViewById(R.id.pay_llpwd).setVisibility(4);
            this.txtToPwd = (TextView) getViewAndClick(R.id.pay_toPwd);
            this.txtToPwd.setVisibility(0);
            initFinger();
            onReady();
        }
        ((NumKeyView) findViewById(R.id.keyboardview)).setOnKeyPressListener(new NumKeyView.OnKeyPressListener() { // from class: com.vsf2f.f2f.ui.dialog.PayPwdDialog.1
            @Override // com.vsf2f.f2f.ui.view.NumKeyView.OnKeyPressListener
            public void onInertKey(String text) {
                PayPwdDialog.this.mPwdView.onInertKey(text);
                if (PayPwdDialog.this.mPwdView.isFull()) {
                    try {
                        PayPwdDialog.this.md5pwd = MD5.md5Encode(MD5.md5Encode(PayPwdDialog.this.mPwdView.getPwd()));
                        PayPwdDialog.this.listener.outPwd(PayPwdDialog.this.md5pwd);
                        PayPwdDialog.this.clearPwd();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override // com.vsf2f.f2f.ui.view.NumKeyView.OnKeyPressListener
            public void onDeleteKey() {
                PayPwdDialog.this.mPwdView.onDeleteKey();
            }
        });
    }

    public void clearPwd() {
        this.mPwdView.clearPwd();
    }

    public void saveDismiss() {
        if (!TextUtils.isEmpty(this.md5pwd)) {
            ComUtil.setPaypwd(getContext(), this.md5pwd);
        }
        super.dismiss();
    }

    @Override // android.app.Dialog
    public void show() {
        int pwdType = UserShared.getInstance().readExtInfo().getPaypwdVersion();
        if (pwdType == 0) {
            new WarnDialog(getContext(), getContext().getString(R.string.not_pay_pwd_prompt), "立即修改", true, true, false).setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.dialog.PayPwdDialog.2
                @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                public void onDlgConfirm(BaseDialog dlg, int flag) {
                    if (flag == 0) {
                        Bundle bundle = new Bundle();
                        bundle.putString(Constant.FLAG, com.vsf2f.f2f.ui.utils.Constant.CHANGE_PAY_TYPE);
                        Intent intent = new Intent(PayPwdDialog.this.getContext(), ChangePwdActivity.class);
                        intent.putExtra(Constant.BUNDLE, bundle);
                        PayPwdDialog.this.getContext().startActivity(intent);
                    }
                }
            }).show();
        } else if (pwdType == 1) {
            new WarnDialog(getContext(), getContext().getString(R.string.not_pay_pwd_prompt2), "立即设置", true, true, false).setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.dialog.PayPwdDialog.3
                @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                public void onDlgConfirm(BaseDialog dlg, int flag) {
                    if (flag == 0) {
                        PayPwdDialog.this.getContext().startActivity(new Intent(PayPwdDialog.this.getContext(), PayPwdResetActivity.class));
                    }
                }
            }).show();
        } else {
            super.show();
        }
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.pay_tvFindpwd /* 2131755494 */:
                clearPwd();
                getContext().startActivity(new Intent(getContext(), PayPwdFindActivity.class));
                return;
            case R.id.pay_cancel /* 2131756307 */:
                dismiss();
                return;
            case R.id.pay_toPwd /* 2131756308 */:
                payToPwd();
                return;
            default:
                return;
        }
    }

    protected void payToPwd() {
        findViewById(R.id.pay_llFinger).setVisibility(4);
        findViewById(R.id.pay_llpwd).setVisibility(0);
        this.txtToPwd.setVisibility(4);
        if (this.mFingerUtil != null) {
            this.mFingerUtil.stopsFingerListen();
        }
        this.mFingerUtil = null;
    }

    protected void onReady() {
        if (this.mFingerUtil != null && this.mFingerListen != null) {
            this.mFingerUtil.startFingerListen(this.mFingerListen);
        }
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
        if (this.mFingerUtil != null) {
            this.mFingerUtil.stopsFingerListen();
        }
    }

    protected void initFinger() {
        this.mFingerUtil = new FingerUtil(getContext());
        this.mFingerListen = new FingerprintManagerCompat.AuthenticationCallback() { // from class: com.vsf2f.f2f.ui.dialog.PayPwdDialog.4
            @Override // android.support.v4.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                Toast.makeText(PayPwdDialog.this.getContext(), "指纹识别成功", 0).show();
                PayPwdDialog.this.listener.outPwd(ComUtil.getPaypwd(PayPwdDialog.this.getContext()));
                PayPwdDialog.this.dismiss();
            }

            @Override // android.support.v4.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
            public void onAuthenticationFailed() {
                Toast.makeText(PayPwdDialog.this.getContext(), "指纹识别失败,请重试", 0).show();
            }

            @Override // android.support.v4.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                if (PayPwdDialog.this.canFinger) {
                    switch (helpMsgId) {
                        case 1001:
                            Toast.makeText(PayPwdDialog.this.getContext(), "请按下手指", 0).show();
                            return;
                        case 1002:
                            Toast.makeText(PayPwdDialog.this.getContext(), "正在识别…", 0).show();
                            return;
                        case 1003:
                            Toast.makeText(PayPwdDialog.this.getContext(), "手指抬起，请重新按下", 0).show();
                            return;
                        default:
                            return;
                    }
                }
            }

            @Override // android.support.v4.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                switch (errMsgId) {
                    case 5:
                        PayPwdDialog.this.canFinger = true;
                        return;
                    case 6:
                    default:
                        return;
                    case 7:
                        Toast.makeText(PayPwdDialog.this.getContext(), "失败次数过多！请输入支付密码", 0).show();
                        PayPwdDialog.this.canFinger = false;
                        PayPwdDialog.this.payToPwd();
                        return;
                }
            }
        };
        this.mFingerUtil.startFingerListen(this.mFingerListen);
    }
}

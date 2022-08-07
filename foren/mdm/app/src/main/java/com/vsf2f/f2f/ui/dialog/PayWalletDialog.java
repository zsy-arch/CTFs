package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.MD5;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.pay.PayPwdFindActivity;
import com.vsf2f.f2f.ui.utils.FingerUtil;
import com.vsf2f.f2f.ui.view.NumKeyView;
import com.vsf2f.f2f.ui.view.NumPwdView;

/* loaded from: classes2.dex */
public class PayWalletDialog extends BaseDialog {
    private boolean canFinger;
    private OutPwdListener listener;
    private FingerprintManagerCompat.AuthenticationCallback mFingerListen;
    private FingerUtil mFingerUtil;
    private NumPwdView mPwdView;
    private String money;
    private TextView txtError;
    private TextView txtMoney;
    private TextView txtToPwd;

    /* loaded from: classes2.dex */
    public interface OutPwdListener {
        void outPwd(String str);
    }

    public PayWalletDialog(Context context, String money, OutPwdListener listener) {
        super(context);
        this.money = money;
        this.listener = listener;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_pay_wallet;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-1.0f, -2.0f, 80);
        setCanceledOnTouchOutside(false);
        setCancelable(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        setOnClickListener(R.id.pay_tvFindpwd);
        setOnClickListener(R.id.pay_cancel);
        this.mPwdView = (NumPwdView) findViewById(R.id.pwdeditview);
        this.txtMoney = (TextView) getView(R.id.pay_txtMoney);
        this.txtError = (TextView) getView(R.id.pay_txtError);
    }

    public void setError(String error) {
        this.txtError.setText(error);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
        this.txtMoney.setText(this.money);
        if (ComUtil.isFinger(getContext())) {
            findViewById(R.id.pay_llFinger).setVisibility(0);
            findViewById(R.id.pay_llpwd).setVisibility(4);
            this.txtToPwd = (TextView) getViewAndClick(R.id.pay_toPwd);
            this.txtToPwd.setVisibility(0);
            initFinger();
            onReady();
        }
        ((NumKeyView) findViewById(R.id.keyboardview)).setOnKeyPressListener(new NumKeyView.OnKeyPressListener() { // from class: com.vsf2f.f2f.ui.dialog.PayWalletDialog.1
            @Override // com.vsf2f.f2f.ui.view.NumKeyView.OnKeyPressListener
            public void onInertKey(String text) {
                PayWalletDialog.this.mPwdView.onInertKey(text);
                if (PayWalletDialog.this.mPwdView.isFull()) {
                    try {
                        PayWalletDialog.this.listener.outPwd(MD5.md5Encode(MD5.md5Encode(PayWalletDialog.this.mPwdView.getPwd())));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                PayWalletDialog.this.txtError.setText("");
            }

            @Override // com.vsf2f.f2f.ui.view.NumKeyView.OnKeyPressListener
            public void onDeleteKey() {
                PayWalletDialog.this.mPwdView.onDeleteKey();
                PayWalletDialog.this.txtError.setText("");
            }
        });
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.pay_tvFindpwd /* 2131755494 */:
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

    /* JADX INFO: Access modifiers changed from: private */
    public void payToPwd() {
        findViewById(R.id.pay_llFinger).setVisibility(4);
        findViewById(R.id.pay_llpwd).setVisibility(0);
        this.txtToPwd.setVisibility(4);
    }

    private void onReady() {
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

    private void initFinger() {
        this.mFingerUtil = new FingerUtil(getContext());
        this.mFingerListen = new FingerprintManagerCompat.AuthenticationCallback() { // from class: com.vsf2f.f2f.ui.dialog.PayWalletDialog.2
            @Override // android.support.v4.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                Toast.makeText(PayWalletDialog.this.getContext(), "指纹识别成功", 0).show();
                PayWalletDialog.this.listener.outPwd(ComUtil.getPaypwd(PayWalletDialog.this.getContext()));
            }

            @Override // android.support.v4.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
            public void onAuthenticationFailed() {
                Toast.makeText(PayWalletDialog.this.getContext(), "指纹识别失败,请重试", 0).show();
            }

            @Override // android.support.v4.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                if (PayWalletDialog.this.canFinger) {
                    switch (helpMsgId) {
                        case 1001:
                            Toast.makeText(PayWalletDialog.this.getContext(), "请按下手指", 0).show();
                            return;
                        case 1002:
                            Toast.makeText(PayWalletDialog.this.getContext(), "正在识别…", 0).show();
                            return;
                        case 1003:
                            Toast.makeText(PayWalletDialog.this.getContext(), "手指抬起，请重新按下", 0).show();
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
                        PayWalletDialog.this.canFinger = true;
                        return;
                    case 6:
                    default:
                        return;
                    case 7:
                        Toast.makeText(PayWalletDialog.this.getContext(), "失败次数过多！请输入支付密码", 0).show();
                        PayWalletDialog.this.canFinger = false;
                        PayWalletDialog.this.payToPwd();
                        return;
                }
            }
        };
        this.mFingerUtil.startFingerListen(this.mFingerListen);
    }
}

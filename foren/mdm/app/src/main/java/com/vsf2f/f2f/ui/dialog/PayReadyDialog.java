package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.view.KeyValueView;
import com.hy.frame.view.NavView;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class PayReadyDialog extends BaseDialog {
    private String money;
    private NavView nvType;
    private int type;

    public PayReadyDialog(Context context, String money) {
        super(context);
        this.money = money;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_pay_ready;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-1.0f, -2.0f, 80, R.style.AnimBottom);
        setCanceledOnTouchOutside(false);
        setCancelable(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        this.nvType = (NavView) getView(R.id.pay_nvType);
        ((KeyValueView) getView(R.id.pay_price)).setValue("￥" + this.money);
        ((TextView) getViewAndClick(R.id.pay_money)).setText(this.money);
        setOnClickListener(R.id.pay_llType);
        setOnClickListener(R.id.txt_ensure);
        setOnClickListener(R.id.pay_cancel);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.pay_cancel /* 2131756307 */:
                dismiss();
                return;
            case R.id.pay_llType /* 2131756312 */:
                PayTypeDialog payTypeDialog = new PayTypeDialog(getContext());
                payTypeDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.dialog.PayReadyDialog.1
                    @Override // com.hy.frame.common.BaseDialog.IConfirmListener
                    public void onDlgConfirm(BaseDialog dlg, int flag) {
                        PayReadyDialog.this.type = flag;
                        if (PayReadyDialog.this.type == 0) {
                            PayReadyDialog.this.nvType.setText("支付宝");
                            PayReadyDialog.this.nvType.setDraw(R.drawable.icon_pay_ali);
                        } else if (PayReadyDialog.this.type == 1) {
                            PayReadyDialog.this.nvType.setText("微信");
                            PayReadyDialog.this.nvType.setDraw(R.drawable.icon_pay_wx);
                        }
                    }
                });
                payTypeDialog.show();
                return;
            case R.id.txt_ensure /* 2131756315 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 1);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

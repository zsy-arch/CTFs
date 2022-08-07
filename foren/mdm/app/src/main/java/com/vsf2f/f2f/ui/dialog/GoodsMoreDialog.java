package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class GoodsMoreDialog extends BaseDialog {
    private boolean isRecom;
    private boolean isSoldTab;

    public GoodsMoreDialog(Context context, boolean isSoldTab, boolean isRecom) {
        super(context);
        this.isSoldTab = isSoldTab;
        this.isRecom = isRecom;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_goods_more;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-1.0f, -2.0f, 80, R.style.AnimBottom);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        setOnClickListener(R.id.txt_cancle);
        setOnClickListener(R.id.dlg_imgShareCircle);
        setOnClickListener(R.id.dlg_imgShareWechat);
        setOnClickListener(R.id.dlg_imgShareQQfd);
        setOnClickListener(R.id.dlg_imgShareQzone);
        setOnClickListener(R.id.dlg_llyLook);
        setOnClickListener(R.id.dlg_llyChange);
        setOnClickListener(R.id.dlg_llyEdit);
        setOnClickListener(R.id.dlg_llyDel);
        TextView txtRecom = (TextView) getViewAndClick(R.id.dlg_txtRecom);
        TextView txtChange = (TextView) getView(R.id.dlg_txtChange);
        LinearLayout llyShare = (LinearLayout) getViewAndClick(R.id.dlg_llyShare);
        LinearLayout llyRecommend = (LinearLayout) getViewAndClick(R.id.dlg_llyRecommend);
        if (!this.isSoldTab) {
            llyShare.setVisibility(8);
            llyRecommend.setVisibility(8);
            txtChange.setText("恢复上架");
        }
        if (this.isRecom) {
            txtRecom.setText("取消推荐");
        }
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.txt_cancle /* 2131756250 */:
                dismiss();
                return;
            default:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, v.getId());
                    return;
                }
                return;
        }
    }
}

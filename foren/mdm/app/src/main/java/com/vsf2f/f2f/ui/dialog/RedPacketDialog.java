package com.vsf2f.f2f.ui.dialog;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.packet.RedPacketInfoBean;

/* loaded from: classes2.dex */
public class RedPacketDialog extends BaseDialog {
    private RedPacketInfoBean info;
    private ImageView iv_open;
    private boolean single;
    private TextView tv_detail;
    private TextView tv_greeting;

    public RedPacketDialog(Context context, boolean single, RedPacketInfoBean info) {
        super(context);
        this.info = info;
        this.single = single;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_redpacket;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-2.0f, -2.0f, 17);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        this.tv_greeting = (TextView) getView(R.id.tv_greeting);
        this.iv_open = (ImageView) getViewAndClick(R.id.iv_open);
        this.tv_detail = (TextView) getViewAndClick(R.id.tv_detail);
        setOnClickListener(R.id.v_close);
        Glide.with(getContext()).load(this.info.headURL).into((ImageView) getView(R.id.iv_avatar));
        ((TextView) getView(R.id.tv_nickname)).setText(this.info.userName);
        this.tv_greeting.setText(this.info.RedpacketKeyRedpacketGreeting);
        if (this.single) {
            findViewById(R.id.tv_singe).setVisibility(8);
        }
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    public void setSingle(boolean b) {
        this.single = b;
    }

    public void setError(String greeting) {
        if (this.tv_greeting != null) {
            this.tv_greeting.setText(greeting);
            this.iv_open.setVisibility(8);
            this.tv_detail.setVisibility(0);
        }
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.v_close /* 2131756328 */:
                dismiss();
                return;
            case R.id.iv_avatar /* 2131756329 */:
            case R.id.tv_singe /* 2131756330 */:
            case R.id.tv_greeting /* 2131756331 */:
            default:
                return;
            case R.id.iv_open /* 2131756332 */:
                ObjectAnimator animator = ObjectAnimator.ofFloat(v, "rotationY", 0.0f, 1000.0f, 0.0f);
                animator.setDuration(2000L);
                animator.start();
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 0);
                    return;
                }
                return;
            case R.id.tv_detail /* 2131756333 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 1);
                    return;
                }
                return;
        }
    }
}

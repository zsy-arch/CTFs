package com.vsf2f.f2f.ui.otay;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.em.DemoModel;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class OtayDialog extends BaseDialog {
    private FlakeView flakeView;
    private int num;
    private MediaPlayer player;
    private PopupWindow pop;

    public OtayDialog(Context context) {
        super(context);
        this.num = 50;
    }

    public OtayDialog(Context context, int num) {
        super(context);
        this.num = 50;
        this.num = num;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_otayonii;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-1.0f, -1.0f, 17);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        this.flakeView = new FlakeView(getContext());
        this.flakeView.setLayerType(0, null);
        this.flakeView.addFlakes(this.num);
        LinearLayout container = (LinearLayout) getView(R.id.container);
        container.addView(this.flakeView);
        this.pop = new PopupWindow(-1, -1);
        this.pop.showAtLocation(container, 17, 0, 0);
        this.pop.setOutsideTouchable(true);
        this.pop.setFocusable(true);
        DemoModel demoModel = new DemoModel(getContext());
        if (demoModel.getSettingMsgNotification() && demoModel.getSettingMsgSound()) {
            this.player = MediaPlayer.create(getContext(), (int) R.raw.beans);
            this.player.setAudioStreamType(3);
            this.player.start();
        }
        new Handler().postDelayed(new Runnable() { // from class: com.vsf2f.f2f.ui.otay.OtayDialog.1
            @Override // java.lang.Runnable
            public void run() {
                OtayDialog.this.flakeView.setStop(true);
                OtayDialog.this.pop.dismiss();
            }
        }, 5000L);
        ((TextView) getView(R.id.txt_num)).setText("" + this.num);
        ((TextView) getView(R.id.txt_prompt)).setText(this.num + " 金豆奖励领取成功");
        ((View) getView(R.id.btn_stop)).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.otay.OtayDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OtayDialog.this.flakeView.removeFlakes();
                OtayDialog.this.pop.dismiss();
                OtayDialog.this.player.release();
                OtayDialog.this.dismiss();
            }
        });
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        this.flakeView.removeFlakes();
        this.player.release();
        this.pop.dismiss();
        super.dismiss();
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        v.getId();
    }
}

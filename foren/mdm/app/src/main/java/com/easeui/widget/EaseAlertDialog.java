package com.easeui.widget;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class EaseAlertDialog extends BaseDialog {
    private Bundle bundle;
    private String cancelStr;
    private AlertDialogListener listener;
    private String msg;
    private String okStr;
    private boolean showCancel;
    private String title;

    /* loaded from: classes.dex */
    public interface AlertDialogListener {
        void onResult(boolean z, Bundle bundle);
    }

    public EaseAlertDialog(Context context, int msgId) {
        super(context);
        this.showCancel = false;
        this.title = context.getResources().getString(R.string.prompt);
        this.msg = context.getResources().getString(msgId);
        setCanceledOnTouchOutside(false);
    }

    public EaseAlertDialog(Context context, String msg) {
        super(context);
        this.showCancel = false;
        this.title = context.getResources().getString(R.string.prompt);
        this.msg = msg;
        setCanceledOnTouchOutside(false);
    }

    public EaseAlertDialog(Context context, int titleId, int msgId) {
        super(context);
        this.showCancel = false;
        this.title = context.getResources().getString(titleId);
        this.msg = context.getResources().getString(msgId);
        setCanceledOnTouchOutside(false);
    }

    public EaseAlertDialog(Context context, String title, String msg) {
        super(context);
        this.showCancel = false;
        this.title = title;
        this.msg = msg;
        setCanceledOnTouchOutside(false);
    }

    public EaseAlertDialog(Context context, int msgId, AlertDialogListener listener, boolean showCancel) {
        super(context);
        this.showCancel = false;
        this.msg = context.getResources().getString(msgId);
        this.listener = listener;
        this.showCancel = showCancel;
        setCanceledOnTouchOutside(showCancel);
    }

    public EaseAlertDialog(Context context, int titleId, int msgId, Bundle bundle, AlertDialogListener listener, boolean showCancel) {
        super(context);
        this.showCancel = false;
        this.title = context.getResources().getString(titleId == 0 ? R.string.prompt : titleId);
        this.msg = context.getResources().getString(msgId);
        this.listener = listener;
        this.bundle = bundle;
        this.showCancel = showCancel;
        setCanceledOnTouchOutside(showCancel);
    }

    public EaseAlertDialog(Context context, String title, String msg, Bundle bundle, AlertDialogListener listener, boolean showCancel) {
        super(context);
        this.showCancel = false;
        this.title = title;
        this.msg = msg;
        this.listener = listener;
        this.bundle = bundle;
        this.showCancel = showCancel;
        setCanceledOnTouchOutside(showCancel);
    }

    public EaseAlertDialog setOnTouch(boolean Outside) {
        setCanceledOnTouchOutside(Outside);
        setCancelable(Outside);
        return this;
    }

    public EaseAlertDialog setOkBtn(String str) {
        this.okStr = str;
        return this;
    }

    public EaseAlertDialog setCancelBtn(String str) {
        this.cancelStr = str;
        return this;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.ease_alert_dialog;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(0.7f, -2.0f, 17);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        Button ok = (Button) findViewById(R.id.btn_ok);
        Button cancel = (Button) findViewById(R.id.btn_cancel);
        if (!TextUtils.isEmpty(this.okStr)) {
            ok.setText(this.okStr);
        }
        if (!TextUtils.isEmpty(this.cancelStr)) {
            cancel.setText(this.cancelStr);
        }
        TextView titleView = (TextView) findViewById(R.id.title);
        setTitle(this.title);
        View.OnClickListener listener = new View.OnClickListener() { // from class: com.easeui.widget.EaseAlertDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view.getId() == R.id.btn_ok) {
                    EaseAlertDialog.this.onOk(view);
                } else if (view.getId() == R.id.btn_cancel) {
                    EaseAlertDialog.this.onCancel(view);
                }
            }
        };
        cancel.setOnClickListener(listener);
        ok.setOnClickListener(listener);
        if (this.title != null) {
            titleView.setText(this.title);
        }
        if (this.msg != null) {
            ((TextView) findViewById(R.id.alert_message)).setText(this.msg);
        }
        cancel.setVisibility(this.showCancel ? 0 : 8);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onOk(View view) {
        dismiss();
        if (this.listener != null) {
            this.listener.onResult(true, this.bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCancel(View view) {
        dismiss();
        if (this.listener != null) {
            this.listener.onResult(false, this.bundle);
        }
    }
}

package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.hy.frame.common.BaseDialog;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class PictureDialog extends BaseDialog {
    private ConfirmDlgListener listener;

    /* loaded from: classes2.dex */
    public interface ConfirmDlgListener {
        void onDlgCameraClick(PictureDialog pictureDialog);

        void onDlgCancelClick(PictureDialog pictureDialog);

        void onDlgPhotoClick(PictureDialog pictureDialog);
    }

    public PictureDialog(Context context) {
        super(context);
    }

    public void init(ConfirmDlgListener listener) {
        this.listener = listener;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_pic_select;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-1.0f, -2.0f, 80, R.style.AnimBottom);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        setOnClickListener(R.id.pictrue_btnCamera);
        setOnClickListener(R.id.pictrue_btnPhoto);
        setOnClickListener(R.id.pictrue_btnCancel);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.pictrue_btnCamera /* 2131756324 */:
                if (this.listener != null) {
                    this.listener.onDlgCameraClick(this);
                    return;
                }
                return;
            case R.id.pictrue_btnPhoto /* 2131756325 */:
                if (this.listener != null) {
                    this.listener.onDlgPhotoClick(this);
                    return;
                }
                return;
            case R.id.pictrue_btnCancel /* 2131756326 */:
                if (this.listener != null) {
                    this.listener.onDlgCancelClick(this);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // android.app.Dialog
    public boolean onTouchEvent(MotionEvent event) {
        if (this.listener != null) {
            this.listener.onDlgCancelClick(this);
        }
        return super.onTouchEvent(event);
    }
}

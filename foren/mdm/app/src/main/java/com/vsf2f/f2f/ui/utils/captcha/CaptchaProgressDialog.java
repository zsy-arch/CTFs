package com.vsf2f.f2f.ui.utils.captcha;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class CaptchaProgressDialog extends ProgressDialog {
    private View bgView;
    private float dScale;
    private int dWidth;
    private Context mContext;
    private ImageView mErrorIcon;
    private ProgressBar mProgressBar;
    private TextView mStatusTip;
    private int mPositionX = -1;
    private int mPositionY = -1;
    private int mPositionW = -1;
    private int mPositionH = -1;
    public boolean isCanClickDisappear = false;
    public boolean isCancelLoading = false;

    public CaptchaProgressDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override // android.app.ProgressDialog, android.app.Dialog
    public void onStart() {
        super.onStart();
        this.isCanClickDisappear = false;
        this.mProgressBar.setVisibility(0);
        this.mErrorIcon.setVisibility(4);
        this.mStatusTip.setText(R.string.tip_loading);
    }

    @Override // android.app.ProgressDialog, android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setDialogPositionAndParmars();
        setContentView(R.layout.captcha_progress_dialog_layout);
        this.mStatusTip = (TextView) findViewById(R.id.status_tip);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        this.mErrorIcon = (ImageView) findViewById(R.id.error_pic);
        this.bgView = findViewById(R.id.bg);
        this.bgView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.utils.captcha.CaptchaProgressDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (CaptchaProgressDialog.this.isCanClickDisappear) {
                    try {
                        CaptchaProgressDialog.this.dismiss();
                    } catch (Exception e) {
                        Log.e(Captcha.TAG, "Captcha Progress Dialog dismiss Error:" + e.toString());
                    }
                }
            }
        });
    }

    public void setPosition(int left, int top, int w, int h) {
        this.mPositionX = left;
        this.mPositionY = top;
        this.mPositionW = w;
        this.mPositionH = h;
    }

    private void setDialogPositionAndParmars() {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = 17;
        if (this.mPositionX != -1) {
            layoutParams.gravity = 3;
            layoutParams.x = this.mPositionX;
        }
        if (this.mPositionY != -1) {
            layoutParams.gravity |= 48;
            layoutParams.y = this.mPositionY;
        }
        if (this.mPositionW > 0) {
            layoutParams.width = this.mPositionW;
        } else {
            getDialogWidth();
            layoutParams.width = this.dWidth + 60;
        }
        if (this.mPositionH > 0) {
            layoutParams.height = this.mPositionH;
        } else {
            layoutParams.height = getDialogHeight(this.dWidth) + 60;
        }
        getWindow().setAttributes(layoutParams);
    }

    private void getDialogWidth() {
        try {
            DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
            int width = metrics.widthPixels;
            int height = metrics.heightPixels;
            float scale = metrics.density;
            this.dScale = scale;
            if (this.mPositionW > 270) {
                this.dWidth = this.mPositionW;
                return;
            }
            if (height < width) {
                width = (height * 3) / 4;
            }
            int width2 = (width * 4) / 5;
            if (((int) (width2 / scale)) < 270) {
                width2 = (int) (270.0f * scale);
            }
            this.dWidth = width2;
        } catch (Exception e) {
            Log.e(Captcha.TAG, "getDialogWidth failed");
        }
    }

    private int getDialogHeight(float width) {
        return (int) (((float) (width / 2.0d)) + (52.0f * this.dScale) + 15.0f);
    }

    public void setProgressTips(String mProgressTips) {
        this.mProgressBar.setVisibility(4);
        this.mErrorIcon.setVisibility(0);
        this.mStatusTip.setText(mProgressTips);
    }

    public void setProgressTips(int mProgressTips) {
        this.mProgressBar.setVisibility(4);
        this.mErrorIcon.setVisibility(0);
        this.mStatusTip.setText(mProgressTips);
    }

    @Override // android.app.Dialog
    public void show() {
        try {
            if (this.mContext != null && !((Activity) this.mContext).isFinishing()) {
                super.show();
            }
        } catch (Exception e) {
            Log.e(Captcha.TAG, "Captcha Progress Dialog show Error:" + e.toString());
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
            Log.e(Captcha.TAG, "Captcha Progress Dialog dismiss Error:" + e.toString());
        }
    }
}

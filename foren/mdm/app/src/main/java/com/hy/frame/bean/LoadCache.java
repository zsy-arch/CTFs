package com.hy.frame.bean;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.hy.frame.R;

/* loaded from: classes2.dex */
public class LoadCache {
    public ImageView imgMessage;
    public LinearLayout llyLoad;
    public ProgressBar proLoading;
    public TextView txtMessage;

    public void showLoading(String msg) {
        this.llyLoad.setVisibility(0);
        this.proLoading.setVisibility(0);
        this.imgMessage.setVisibility(8);
        this.txtMessage.setVisibility(0);
        this.txtMessage.setText(msg);
    }

    public void showNoData(String msg, int drawId) {
        this.llyLoad.setVisibility(0);
        this.proLoading.setVisibility(8);
        this.imgMessage.setVisibility(0);
        this.txtMessage.setVisibility(0);
        if (msg == null) {
            this.txtMessage.setText(R.string.hint_no_data);
        } else {
            this.txtMessage.setText(msg);
        }
        this.imgMessage.setImageResource(drawId);
    }
}

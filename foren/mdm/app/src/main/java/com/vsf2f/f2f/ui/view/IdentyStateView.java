package com.vsf2f.f2f.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class IdentyStateView extends LinearLayout {
    public IdentyStateView(Context context) {
        this(context, null);
    }

    public IdentyStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_identy_state, this);
    }

    public void setStatus(int phone, int real, int zhima, int ali, int wx, int qq) {
        ImageView iv_phone = (ImageView) findViewById(R.id.iv_phone);
        ImageView iv_idcard = (ImageView) findViewById(R.id.iv_idcard);
        ImageView iv_zhima = (ImageView) findViewById(R.id.iv_zhima);
        ImageView iv_alipay = (ImageView) findViewById(R.id.iv_alipay);
        ImageView iv_wechat = (ImageView) findViewById(R.id.iv_wechat);
        ImageView iv_qq = (ImageView) findViewById(R.id.iv_qq);
        if (real == 1) {
            iv_idcard.setVisibility(0);
        }
        if (phone == 1) {
            iv_phone.setVisibility(0);
        }
        if (zhima == 1) {
            iv_zhima.setVisibility(0);
        }
        if (ali == 1) {
            iv_alipay.setVisibility(0);
        }
        if (wx == 1) {
            iv_wechat.setVisibility(0);
        }
        if (qq == 1) {
            iv_qq.setVisibility(0);
        }
    }
}

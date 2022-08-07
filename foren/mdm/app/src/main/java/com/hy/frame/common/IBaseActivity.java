package com.hy.frame.common;

import android.support.annotation.LayoutRes;
import android.view.View;

/* loaded from: classes2.dex */
public interface IBaseActivity {
    void initData();

    @LayoutRes
    int initLayoutId();

    void initView();

    void onLeftClick();

    void onRightClick();

    void onViewClick(View view);

    void requestData();

    void updateUI();
}

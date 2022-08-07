package com.amap.api.navi.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.amap.api.col.fo;

/* loaded from: classes.dex */
public class ZoomButtonView extends LinearLayout {
    private ImageButton zoomInBtn;
    private ImageButton zoomOutBtn;

    public ZoomButtonView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public ZoomButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ZoomButtonView(Context context) {
        super(context);
        init();
    }

    public ImageButton getZoomOutBtn() {
        return this.zoomOutBtn;
    }

    public ImageButton getZoomInBtn() {
        return this.zoomInBtn;
    }

    private void init() {
        View a = fo.a((Activity) getContext(), 1191378948, null);
        addView(a);
        this.zoomOutBtn = (ImageButton) a.findViewById(1191772205);
        this.zoomInBtn = (ImageButton) a.findViewById(1191772204);
    }
}

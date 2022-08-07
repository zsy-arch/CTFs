package com.tencent.smtt.sdk.ui.dialog.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.widget.Button;

/* loaded from: classes.dex */
public class b extends Button {

    /* renamed from: a  reason: collision with root package name */
    public int f1458a;

    /* renamed from: b  reason: collision with root package name */
    public int f1459b;

    /* renamed from: c  reason: collision with root package name */
    public float f1460c;

    /* renamed from: d  reason: collision with root package name */
    public float f1461d;

    /* renamed from: e  reason: collision with root package name */
    public float f1462e;
    public float f;
    public c g;
    public c h;
    public c i;

    public b(Context context, float f, float f2, float f3, float f4, int i) {
        super(context);
        this.g = null;
        this.h = null;
        this.i = null;
        this.f1460c = f;
        this.f1461d = f2;
        this.f1462e = f3;
        this.f = f4;
        this.f1458a = i;
        this.f1459b = Color.parseColor("#D0D0D0");
        a();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public b(android.content.Context r8, int r9, int r10) {
        /*
            r7 = this;
            float r5 = (float) r9
            r0 = r7
            r1 = r8
            r2 = r5
            r3 = r5
            r4 = r5
            r6 = r10
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ui.dialog.widget.b.<init>(android.content.Context, int, int):void");
    }

    public void a() {
        this.g = new c(this.f1458a, this.f1460c, this.f1461d, this.f1462e, this.f);
        this.g.a(getWidth(), getHeight());
        this.h = new c(1342177280 | (this.f1458a & 16777215), this.f1460c, this.f1461d, this.f1462e, this.f);
        this.h.a(getWidth(), getHeight());
        this.i = new c(this.f1459b, this.f1460c, this.f1461d, this.f1462e, this.f);
        this.i.a(getWidth(), getHeight());
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842910, -16842919}, this.g);
        stateListDrawable.addState(new int[]{16842910, 16842919}, this.h);
        stateListDrawable.addState(new int[]{-16842910}, this.i);
        setBackgroundDrawable(stateListDrawable);
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        c cVar = this.g;
        if (cVar != null) {
            cVar.a(i3 - i, i4 - i2);
        }
        c cVar2 = this.h;
        if (cVar2 != null) {
            cVar2.a(i3 - i, i4 - i2);
        }
        c cVar3 = this.i;
        if (cVar3 != null) {
            cVar3.a(i3 - i, i4 - i2);
        }
    }
}

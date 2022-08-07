package androidx.appcompat.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ActionMode;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import android.widget.TextView;
import c.a.a;
import c.a.a.C;
import c.a.f.E;
import c.a.f.ea;
import c.a.f.ka;
import c.a.f.xa;
import c.a.j;
import c.e.h.n;

/* loaded from: classes.dex */
public class SwitchCompat extends CompoundButton {

    /* renamed from: a */
    public static final Property<SwitchCompat, Float> f167a = new ea(Float.class, "thumbPos");

    /* renamed from: b */
    public static final int[] f168b = {16842912};
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public int G;
    public final TextPaint H;
    public ColorStateList I;
    public Layout J;
    public Layout K;
    public TransformationMethod L;
    public ObjectAnimator M;
    public final Rect N;

    /* renamed from: c */
    public Drawable f169c;

    /* renamed from: d */
    public ColorStateList f170d;

    /* renamed from: e */
    public PorterDuff.Mode f171e;
    public boolean f;
    public boolean g;
    public Drawable h;
    public ColorStateList i;
    public PorterDuff.Mode j;
    public boolean k;
    public boolean l;
    public int m;
    public int n;
    public int o;
    public boolean p;
    public CharSequence q;
    public CharSequence r;
    public boolean s;
    public int t;
    public int u;
    public float v;
    public float w;
    public VelocityTracker x;
    public int y;
    public float z;

    public SwitchCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.switchStyle);
    }

    private boolean getTargetCheckedState() {
        return this.z > 0.5f;
    }

    private int getThumbOffset() {
        float f;
        if (xa.a(this)) {
            f = 1.0f - this.z;
        } else {
            f = this.z;
        }
        return (int) ((f * getThumbScrollRange()) + 0.5f);
    }

    private int getThumbScrollRange() {
        Rect rect;
        Drawable drawable = this.h;
        if (drawable == null) {
            return 0;
        }
        Rect rect2 = this.N;
        drawable.getPadding(rect2);
        Drawable drawable2 = this.f169c;
        if (drawable2 != null) {
            rect = E.c(drawable2);
        } else {
            rect = E.f526a;
        }
        return ((((this.A - this.C) - rect2.left) - rect2.right) - rect.left) - rect.right;
    }

    public void a(Context context, int i) {
        Typeface typeface;
        ka a2 = ka.a(context, i, j.TextAppearance);
        ColorStateList a3 = a2.a(j.TextAppearance_android_textColor);
        if (a3 != null) {
            this.I = a3;
        } else {
            this.I = getTextColors();
        }
        int c2 = a2.c(j.TextAppearance_android_textSize, 0);
        if (c2 != 0) {
            float f = c2;
            if (f != this.H.getTextSize()) {
                this.H.setTextSize(f);
                requestLayout();
            }
        }
        int d2 = a2.d(j.TextAppearance_android_typeface, -1);
        int d3 = a2.d(j.TextAppearance_android_textStyle, -1);
        if (d2 == 1) {
            typeface = Typeface.SANS_SERIF;
        } else if (d2 != 2) {
            typeface = d2 != 3 ? null : Typeface.MONOSPACE;
        } else {
            typeface = Typeface.SERIF;
        }
        a(typeface, d3);
        if (a2.a(j.TextAppearance_textAllCaps, false)) {
            this.L = new c.a.d.a(getContext());
        } else {
            this.L = null;
        }
        a2.f605b.recycle();
    }

    public final void b() {
        if (this.h == null) {
            return;
        }
        if (this.k || this.l) {
            this.h = this.h.mutate();
            if (this.k) {
                Drawable drawable = this.h;
                ColorStateList colorStateList = this.i;
                int i = Build.VERSION.SDK_INT;
                drawable.setTintList(colorStateList);
            }
            if (this.l) {
                Drawable drawable2 = this.h;
                PorterDuff.Mode mode = this.j;
                int i2 = Build.VERSION.SDK_INT;
                drawable2.setTintMode(mode);
            }
            if (this.h.isStateful()) {
                this.h.setState(getDrawableState());
            }
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        Rect rect;
        int i;
        int i2;
        Rect rect2 = this.N;
        int i3 = this.D;
        int i4 = this.E;
        int i5 = this.F;
        int i6 = this.G;
        int thumbOffset = getThumbOffset() + i3;
        Drawable drawable = this.f169c;
        if (drawable != null) {
            rect = E.c(drawable);
        } else {
            rect = E.f526a;
        }
        Drawable drawable2 = this.h;
        if (drawable2 != null) {
            drawable2.getPadding(rect2);
            int i7 = rect2.left;
            thumbOffset += i7;
            if (rect != null) {
                int i8 = rect.left;
                if (i8 > i7) {
                    i3 += i8 - i7;
                }
                int i9 = rect.top;
                int i10 = rect2.top;
                i = i9 > i10 ? (i9 - i10) + i4 : i4;
                int i11 = rect.right;
                int i12 = rect2.right;
                if (i11 > i12) {
                    i5 -= i11 - i12;
                }
                int i13 = rect.bottom;
                int i14 = rect2.bottom;
                if (i13 > i14) {
                    i2 = i6 - (i13 - i14);
                    this.h.setBounds(i3, i, i5, i2);
                }
            } else {
                i = i4;
            }
            i2 = i6;
            this.h.setBounds(i3, i, i5, i2);
        }
        Drawable drawable3 = this.f169c;
        if (drawable3 != null) {
            drawable3.getPadding(rect2);
            int i15 = thumbOffset - rect2.left;
            int i16 = thumbOffset + this.C + rect2.right;
            this.f169c.setBounds(i15, i4, i16, i6);
            Drawable background = getBackground();
            if (background != null) {
                int i17 = Build.VERSION.SDK_INT;
                background.setHotspotBounds(i15, i4, i16, i6);
            }
        }
        super.draw(canvas);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableHotspotChanged(float f, float f2) {
        int i = Build.VERSION.SDK_INT;
        super.drawableHotspotChanged(f, f2);
        Drawable drawable = this.f169c;
        if (drawable != null) {
            int i2 = Build.VERSION.SDK_INT;
            drawable.setHotspot(f, f2);
        }
        Drawable drawable2 = this.h;
        if (drawable2 != null) {
            int i3 = Build.VERSION.SDK_INT;
            drawable2.setHotspot(f, f2);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f169c;
        boolean z = false;
        if (drawable != null && drawable.isStateful()) {
            z = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.h;
        if (drawable2 != null && drawable2.isStateful()) {
            z |= drawable2.setState(drawableState);
        }
        if (z) {
            invalidate();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        if (!xa.a(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.A;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingLeft + this.o : compoundPaddingLeft;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingRight() {
        if (xa.a(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.A;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingRight + this.o : compoundPaddingRight;
    }

    public boolean getShowText() {
        return this.s;
    }

    public boolean getSplitTrack() {
        return this.p;
    }

    public int getSwitchMinWidth() {
        return this.n;
    }

    public int getSwitchPadding() {
        return this.o;
    }

    public CharSequence getTextOff() {
        return this.r;
    }

    public CharSequence getTextOn() {
        return this.q;
    }

    public Drawable getThumbDrawable() {
        return this.f169c;
    }

    public int getThumbTextPadding() {
        return this.m;
    }

    public ColorStateList getThumbTintList() {
        return this.f170d;
    }

    public PorterDuff.Mode getThumbTintMode() {
        return this.f171e;
    }

    public Drawable getTrackDrawable() {
        return this.h;
    }

    public ColorStateList getTrackTintList() {
        return this.i;
    }

    public PorterDuff.Mode getTrackTintMode() {
        return this.j;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f169c;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.h;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        ObjectAnimator objectAnimator = this.M;
        if (objectAnimator != null && objectAnimator.isStarted()) {
            this.M.end();
            this.M = null;
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            CompoundButton.mergeDrawableStates(onCreateDrawableState, f168b);
        }
        return onCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        Rect rect = this.N;
        Drawable drawable = this.h;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i2 = this.E;
        int i3 = this.G;
        int i4 = i2 + rect.top;
        int i5 = i3 - rect.bottom;
        Drawable drawable2 = this.f169c;
        if (drawable != null) {
            if (!this.p || drawable2 == null) {
                drawable.draw(canvas);
            } else {
                Rect c2 = E.c(drawable2);
                drawable2.copyBounds(rect);
                rect.left += c2.left;
                rect.right -= c2.right;
                int save = canvas.save();
                canvas.clipRect(rect, Region.Op.DIFFERENCE);
                drawable.draw(canvas);
                canvas.restoreToCount(save);
            }
        }
        int save2 = canvas.save();
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        Layout layout = getTargetCheckedState() ? this.J : this.K;
        if (layout != null) {
            int[] drawableState = getDrawableState();
            ColorStateList colorStateList = this.I;
            if (colorStateList != null) {
                this.H.setColor(colorStateList.getColorForState(drawableState, 0));
            }
            this.H.drawableState = drawableState;
            if (drawable2 != null) {
                Rect bounds = drawable2.getBounds();
                i = bounds.left + bounds.right;
            } else {
                i = getWidth();
            }
            canvas.translate((i / 2) - (layout.getWidth() / 2), ((i4 + i5) / 2) - (layout.getHeight() / 2));
            layout.draw(canvas);
        }
        canvas.restoreToCount(save2);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("android.widget.Switch");
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("android.widget.Switch");
        CharSequence charSequence = isChecked() ? this.q : this.r;
        if (!TextUtils.isEmpty(charSequence)) {
            CharSequence text = accessibilityNodeInfo.getText();
            if (TextUtils.isEmpty(text)) {
                accessibilityNodeInfo.setText(charSequence);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(text);
            sb.append(' ');
            sb.append(charSequence);
            accessibilityNodeInfo.setText(sb);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        super.onLayout(z, i, i2, i3, i4);
        int i10 = 0;
        if (this.f169c != null) {
            Rect rect = this.N;
            Drawable drawable = this.h;
            if (drawable != null) {
                drawable.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Rect c2 = E.c(this.f169c);
            i5 = Math.max(0, c2.left - rect.left);
            i10 = Math.max(0, c2.right - rect.right);
        } else {
            i5 = 0;
        }
        if (xa.a(this)) {
            i7 = getPaddingLeft() + i5;
            i6 = ((this.A + i7) - i5) - i10;
        } else {
            i6 = (getWidth() - getPaddingRight()) - i10;
            i7 = (i6 - this.A) + i5 + i10;
        }
        int gravity = getGravity() & 112;
        if (gravity == 16) {
            int paddingTop = getPaddingTop();
            int i11 = this.B;
            int height = (((getHeight() + paddingTop) - getPaddingBottom()) / 2) - (i11 / 2);
            i8 = i11 + height;
            i9 = height;
        } else if (gravity != 80) {
            i9 = getPaddingTop();
            i8 = this.B + i9;
        } else {
            i8 = getHeight() - getPaddingBottom();
            i9 = i8 - this.B;
        }
        this.D = i7;
        this.E = i9;
        this.G = i8;
        this.F = i6;
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        if (this.s) {
            if (this.J == null) {
                this.J = a(this.q);
            }
            if (this.K == null) {
                this.K = a(this.r);
            }
        }
        Rect rect = this.N;
        Drawable drawable = this.f169c;
        int i5 = 0;
        if (drawable != null) {
            drawable.getPadding(rect);
            i4 = (this.f169c.getIntrinsicWidth() - rect.left) - rect.right;
            i3 = this.f169c.getIntrinsicHeight();
        } else {
            i4 = 0;
            i3 = 0;
        }
        this.C = Math.max(this.s ? Math.max(this.J.getWidth(), this.K.getWidth()) + (this.m * 2) : 0, i4);
        Drawable drawable2 = this.h;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            i5 = this.h.getIntrinsicHeight();
        } else {
            rect.setEmpty();
        }
        int i6 = rect.left;
        int i7 = rect.right;
        Drawable drawable3 = this.f169c;
        if (drawable3 != null) {
            Rect c2 = E.c(drawable3);
            i6 = Math.max(i6, c2.left);
            i7 = Math.max(i7, c2.right);
        }
        int max = Math.max(this.n, (this.C * 2) + i6 + i7);
        int max2 = Math.max(i5, i3);
        this.A = max;
        this.B = max2;
        super.onMeasure(i, i2);
        if (getMeasuredHeight() < max2) {
            setMeasuredDimension(getMeasuredWidthAndState(), max2);
        }
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        CharSequence charSequence = isChecked() ? this.q : this.r;
        if (charSequence != null) {
            accessibilityEvent.getText().add(charSequence);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0014, code lost:
        if (r0 != 3) goto L_0x0156;
     */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r11) {
        /*
            Method dump skipped, instructions count: 347
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SwitchCompat.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        super.setChecked(z);
        boolean isChecked = isChecked();
        float f = 1.0f;
        if (getWindowToken() == null || !n.p(this)) {
            ObjectAnimator objectAnimator = this.M;
            if (objectAnimator != null) {
                objectAnimator.cancel();
            }
            if (!isChecked) {
                f = 0.0f;
            }
            setThumbPosition(f);
            return;
        }
        if (!isChecked) {
            f = 0.0f;
        }
        this.M = ObjectAnimator.ofFloat(this, f167a, f);
        this.M.setDuration(250L);
        int i = Build.VERSION.SDK_INT;
        this.M.setAutoCancel(true);
        this.M.start();
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(C.a((TextView) this, callback));
    }

    public void setShowText(boolean z) {
        if (this.s != z) {
            this.s = z;
            requestLayout();
        }
    }

    public void setSplitTrack(boolean z) {
        this.p = z;
        invalidate();
    }

    public void setSwitchMinWidth(int i) {
        this.n = i;
        requestLayout();
    }

    public void setSwitchPadding(int i) {
        this.o = i;
        requestLayout();
    }

    public void setSwitchTypeface(Typeface typeface) {
        if ((this.H.getTypeface() != null && !this.H.getTypeface().equals(typeface)) || (this.H.getTypeface() == null && typeface != null)) {
            this.H.setTypeface(typeface);
            requestLayout();
            invalidate();
        }
    }

    public void setTextOff(CharSequence charSequence) {
        this.r = charSequence;
        requestLayout();
    }

    public void setTextOn(CharSequence charSequence) {
        this.q = charSequence;
        requestLayout();
    }

    public void setThumbDrawable(Drawable drawable) {
        Drawable drawable2 = this.f169c;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f169c = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setThumbPosition(float f) {
        this.z = f;
        invalidate();
    }

    public void setThumbResource(int i) {
        setThumbDrawable(c.a.b.a.a.c(getContext(), i));
    }

    public void setThumbTextPadding(int i) {
        this.m = i;
        requestLayout();
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.f170d = colorStateList;
        this.f = true;
        a();
    }

    public void setThumbTintMode(PorterDuff.Mode mode) {
        this.f171e = mode;
        this.g = true;
        a();
    }

    public void setTrackDrawable(Drawable drawable) {
        Drawable drawable2 = this.h;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.h = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int i) {
        setTrackDrawable(c.a.b.a.a.c(getContext(), i));
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        this.i = colorStateList;
        this.k = true;
        b();
    }

    public void setTrackTintMode(PorterDuff.Mode mode) {
        this.j = mode;
        this.l = true;
        b();
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
        setChecked(!isChecked());
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f169c || drawable == this.h;
    }

    public SwitchCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f170d = null;
        this.f171e = null;
        this.f = false;
        this.g = false;
        this.i = null;
        this.j = null;
        this.k = false;
        this.l = false;
        this.x = VelocityTracker.obtain();
        this.N = new Rect();
        this.H = new TextPaint(1);
        Resources resources = getResources();
        this.H.density = resources.getDisplayMetrics().density;
        ka a2 = ka.a(context, attributeSet, j.SwitchCompat, i, 0);
        this.f169c = a2.b(j.SwitchCompat_android_thumb);
        Drawable drawable = this.f169c;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        this.h = a2.b(j.SwitchCompat_track);
        Drawable drawable2 = this.h;
        if (drawable2 != null) {
            drawable2.setCallback(this);
        }
        this.q = a2.e(j.SwitchCompat_android_textOn);
        this.r = a2.e(j.SwitchCompat_android_textOff);
        this.s = a2.a(j.SwitchCompat_showText, true);
        this.m = a2.c(j.SwitchCompat_thumbTextPadding, 0);
        this.n = a2.c(j.SwitchCompat_switchMinWidth, 0);
        this.o = a2.c(j.SwitchCompat_switchPadding, 0);
        this.p = a2.a(j.SwitchCompat_splitTrack, false);
        ColorStateList a3 = a2.a(j.SwitchCompat_thumbTint);
        if (a3 != null) {
            this.f170d = a3;
            this.f = true;
        }
        PorterDuff.Mode a4 = E.a(a2.d(j.SwitchCompat_thumbTintMode, -1), null);
        if (this.f171e != a4) {
            this.f171e = a4;
            this.g = true;
        }
        if (this.f || this.g) {
            a();
        }
        ColorStateList a5 = a2.a(j.SwitchCompat_trackTint);
        if (a5 != null) {
            this.i = a5;
            this.k = true;
        }
        PorterDuff.Mode a6 = E.a(a2.d(j.SwitchCompat_trackTintMode, -1), null);
        if (this.j != a6) {
            this.j = a6;
            this.l = true;
        }
        if (this.k || this.l) {
            b();
        }
        int f = a2.f(j.SwitchCompat_switchTextAppearance, 0);
        if (f != 0) {
            a(context, f);
        }
        a2.f605b.recycle();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.u = viewConfiguration.getScaledTouchSlop();
        this.y = viewConfiguration.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
    }

    public void a(Typeface typeface, int i) {
        Typeface typeface2;
        float f = 0.0f;
        boolean z = false;
        if (i > 0) {
            if (typeface == null) {
                typeface2 = Typeface.defaultFromStyle(i);
            } else {
                typeface2 = Typeface.create(typeface, i);
            }
            setSwitchTypeface(typeface2);
            int i2 = (~(typeface2 != null ? typeface2.getStyle() : 0)) & i;
            TextPaint textPaint = this.H;
            if ((i2 & 1) != 0) {
                z = true;
            }
            textPaint.setFakeBoldText(z);
            TextPaint textPaint2 = this.H;
            if ((i2 & 2) != 0) {
                f = -0.25f;
            }
            textPaint2.setTextSkewX(f);
            return;
        }
        this.H.setFakeBoldText(false);
        this.H.setTextSkewX(0.0f);
        setSwitchTypeface(typeface);
    }

    public final void a() {
        if (this.f169c == null) {
            return;
        }
        if (this.f || this.g) {
            this.f169c = this.f169c.mutate();
            if (this.f) {
                Drawable drawable = this.f169c;
                ColorStateList colorStateList = this.f170d;
                int i = Build.VERSION.SDK_INT;
                drawable.setTintList(colorStateList);
            }
            if (this.g) {
                Drawable drawable2 = this.f169c;
                PorterDuff.Mode mode = this.f171e;
                int i2 = Build.VERSION.SDK_INT;
                drawable2.setTintMode(mode);
            }
            if (this.f169c.isStateful()) {
                this.f169c.setState(getDrawableState());
            }
        }
    }

    public final Layout a(CharSequence charSequence) {
        TransformationMethod transformationMethod = this.L;
        if (transformationMethod != null) {
            charSequence = transformationMethod.getTransformation(charSequence, this);
        }
        TextPaint textPaint = this.H;
        return new StaticLayout(charSequence, textPaint, charSequence != null ? (int) Math.ceil(Layout.getDesiredWidth(charSequence, textPaint)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }
}

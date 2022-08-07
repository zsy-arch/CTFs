package c.a.f;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import c.a.d;
import c.e.h.n;
import c.e.h.o;
import com.tencent.smtt.export.external.DexClassLoaderProvider;

/* loaded from: classes.dex */
public class ua implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {

    /* renamed from: a */
    public static ua f649a;

    /* renamed from: b */
    public static ua f650b;

    /* renamed from: c */
    public final View f651c;

    /* renamed from: d */
    public final CharSequence f652d;

    /* renamed from: e */
    public final int f653e;
    public final Runnable f = new sa(this);
    public final Runnable g = new ta(this);
    public int h;
    public int i;
    public va j;
    public boolean k;

    public ua(View view, CharSequence charSequence) {
        this.f651c = view;
        this.f652d = charSequence;
        this.f653e = o.a(ViewConfiguration.get(this.f651c.getContext()));
        a();
        this.f651c.setOnLongClickListener(this);
        this.f651c.setOnHoverListener(this);
    }

    public void a(boolean z) {
        int i;
        int i2;
        long j;
        int i3;
        long j2;
        if (n.o(this.f651c)) {
            a((ua) null);
            ua uaVar = f650b;
            if (uaVar != null) {
                uaVar.b();
            }
            f650b = this;
            this.k = z;
            this.j = new va(this.f651c.getContext());
            va vaVar = this.j;
            View view = this.f651c;
            int i4 = this.h;
            int i5 = this.i;
            boolean z2 = this.k;
            CharSequence charSequence = this.f652d;
            if (vaVar.b()) {
                vaVar.a();
            }
            vaVar.f656c.setText(charSequence);
            WindowManager.LayoutParams layoutParams = vaVar.f657d;
            layoutParams.token = view.getApplicationWindowToken();
            int dimensionPixelOffset = vaVar.f654a.getResources().getDimensionPixelOffset(d.tooltip_precise_anchor_threshold);
            if (view.getWidth() < dimensionPixelOffset) {
                i4 = view.getWidth() / 2;
            }
            if (view.getHeight() >= dimensionPixelOffset) {
                int dimensionPixelOffset2 = vaVar.f654a.getResources().getDimensionPixelOffset(d.tooltip_precise_anchor_extra_offset);
                i = i5 + dimensionPixelOffset2;
                i2 = i5 - dimensionPixelOffset2;
            } else {
                i = view.getHeight();
                i2 = 0;
            }
            layoutParams.gravity = 49;
            int dimensionPixelOffset3 = vaVar.f654a.getResources().getDimensionPixelOffset(z2 ? d.tooltip_y_offset_touch : d.tooltip_y_offset_non_touch);
            View rootView = view.getRootView();
            ViewGroup.LayoutParams layoutParams2 = rootView.getLayoutParams();
            if (!(layoutParams2 instanceof WindowManager.LayoutParams) || ((WindowManager.LayoutParams) layoutParams2).type != 2) {
                Context context = view.getContext();
                while (true) {
                    if (!(context instanceof ContextWrapper)) {
                        break;
                    } else if (context instanceof Activity) {
                        rootView = ((Activity) context).getWindow().getDecorView();
                        break;
                    } else {
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                }
            }
            if (rootView != null) {
                rootView.getWindowVisibleDisplayFrame(vaVar.f658e);
                Rect rect = vaVar.f658e;
                if (rect.left < 0 && rect.top < 0) {
                    Resources resources = vaVar.f654a.getResources();
                    int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
                    int dimensionPixelSize = identifier != 0 ? resources.getDimensionPixelSize(identifier) : 0;
                    DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                    vaVar.f658e.set(0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels);
                }
                rootView.getLocationOnScreen(vaVar.g);
                view.getLocationOnScreen(vaVar.f);
                int[] iArr = vaVar.f;
                int i6 = iArr[0];
                int[] iArr2 = vaVar.g;
                iArr[0] = i6 - iArr2[0];
                iArr[1] = iArr[1] - iArr2[1];
                layoutParams.x = (iArr[0] + i4) - (rootView.getWidth() / 2);
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                vaVar.f655b.measure(makeMeasureSpec, makeMeasureSpec);
                int measuredHeight = vaVar.f655b.getMeasuredHeight();
                int[] iArr3 = vaVar.f;
                int i7 = ((iArr3[1] + i2) - dimensionPixelOffset3) - measuredHeight;
                int i8 = iArr3[1] + i + dimensionPixelOffset3;
                if (z2) {
                    if (i7 >= 0) {
                        layoutParams.y = i7;
                    } else {
                        layoutParams.y = i8;
                    }
                } else if (measuredHeight + i8 <= vaVar.f658e.height()) {
                    layoutParams.y = i8;
                } else {
                    layoutParams.y = i7;
                }
            }
            ((WindowManager) vaVar.f654a.getSystemService("window")).addView(vaVar.f655b, vaVar.f657d);
            this.f651c.addOnAttachStateChangeListener(this);
            if (this.k) {
                j = 2500;
            } else {
                if ((n.k(this.f651c) & 1) == 1) {
                    j2 = DexClassLoaderProvider.LOAD_DEX_DELAY;
                    i3 = ViewConfiguration.getLongPressTimeout();
                } else {
                    j2 = 15000;
                    i3 = ViewConfiguration.getLongPressTimeout();
                }
                j = j2 - i3;
            }
            this.f651c.removeCallbacks(this.g);
            this.f651c.postDelayed(this.g, j);
        }
    }

    public void b() {
        if (f650b == this) {
            f650b = null;
            va vaVar = this.j;
            if (vaVar != null) {
                vaVar.a();
                this.j = null;
                a();
                this.f651c.removeOnAttachStateChangeListener(this);
            }
        }
        if (f649a == this) {
            a((ua) null);
        }
        this.f651c.removeCallbacks(this.g);
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        boolean z;
        if (this.j != null && this.k) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f651c.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                a();
                b();
            }
        } else if (this.f651c.isEnabled() && this.j == null) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (Math.abs(x - this.h) > this.f653e || Math.abs(y - this.i) > this.f653e) {
                this.h = x;
                this.i = y;
                z = true;
            } else {
                z = false;
            }
            if (z) {
                a(this);
            }
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.h = view.getWidth() / 2;
        this.i = view.getHeight() / 2;
        a(true);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        b();
    }

    public static void a(ua uaVar) {
        ua uaVar2 = f649a;
        if (uaVar2 != null) {
            uaVar2.f651c.removeCallbacks(uaVar2.f);
        }
        f649a = uaVar;
        ua uaVar3 = f649a;
        if (uaVar3 != null) {
            uaVar3.f651c.postDelayed(uaVar3.f, ViewConfiguration.getLongPressTimeout());
        }
    }

    public final void a() {
        this.h = Integer.MAX_VALUE;
        this.i = Integer.MAX_VALUE;
    }
}

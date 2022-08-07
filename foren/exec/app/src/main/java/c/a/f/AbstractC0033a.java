package c.a.f;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActionMenuPresenter;
import androidx.appcompat.widget.ActionMenuView;
import c.a.a;
import c.a.e.a.l;
import c.a.j;
import c.e.h.n;
import c.e.h.r;
import c.e.h.s;

/* renamed from: c.a.f.a  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0033a extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    public final C0006a f572a;

    /* renamed from: b  reason: collision with root package name */
    public final Context f573b;

    /* renamed from: c  reason: collision with root package name */
    public ActionMenuView f574c;

    /* renamed from: d  reason: collision with root package name */
    public ActionMenuPresenter f575d;

    /* renamed from: e  reason: collision with root package name */
    public int f576e;
    public r f;
    public boolean g;
    public boolean h;

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c.a.f.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0006a implements s {

        /* renamed from: a  reason: collision with root package name */
        public boolean f577a = false;

        /* renamed from: b  reason: collision with root package name */
        public int f578b;

        public C0006a() {
        }

        @Override // c.e.h.s
        public void a(View view) {
            this.f577a = true;
        }

        @Override // c.e.h.s
        public void b(View view) {
            if (!this.f577a) {
                AbstractC0033a aVar = AbstractC0033a.this;
                aVar.f = null;
                AbstractC0033a.super.setVisibility(this.f578b);
            }
        }

        @Override // c.e.h.s
        public void c(View view) {
            AbstractC0033a.super.setVisibility(0);
            this.f577a = false;
        }
    }

    public AbstractC0033a(Context context) {
        this(context, null, 0);
    }

    public static int a(int i, int i2, boolean z) {
        return z ? i - i2 : i + i2;
    }

    public int getAnimatedVisibility() {
        if (this.f != null) {
            return this.f572a.f578b;
        }
        return getVisibility();
    }

    public abstract int getContentHeight();

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, j.ActionBar, a.actionBarStyle, 0);
        setContentHeight(obtainStyledAttributes.getLayoutDimension(j.ActionBar_height, 0));
        obtainStyledAttributes.recycle();
        ActionMenuPresenter actionMenuPresenter = this.f575d;
        if (actionMenuPresenter != null) {
            if (!actionMenuPresenter.q) {
                Configuration configuration2 = actionMenuPresenter.f416b.getResources().getConfiguration();
                int i = configuration2.screenWidthDp;
                int i2 = configuration2.screenHeightDp;
                actionMenuPresenter.p = (configuration2.smallestScreenWidthDp > 600 || i > 600 || (i > 960 && i2 > 720) || (i > 720 && i2 > 960)) ? 5 : (i >= 500 || (i > 640 && i2 > 480) || (i > 480 && i2 > 640)) ? 4 : i >= 360 ? 3 : 2;
            }
            l lVar = actionMenuPresenter.f417c;
            if (lVar != null) {
                lVar.b(true);
            }
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.h = false;
        }
        if (!this.h) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.h = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.h = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.g = false;
        }
        if (!this.g) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.g = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.g = false;
        }
        return true;
    }

    public abstract void setContentHeight(int i);

    @Override // android.view.View
    public void setVisibility(int i) {
        if (i != getVisibility()) {
            r rVar = this.f;
            if (rVar != null) {
                rVar.a();
            }
            super.setVisibility(i);
        }
    }

    public AbstractC0033a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public r a(int i, long j) {
        r rVar = this.f;
        if (rVar != null) {
            rVar.a();
        }
        if (i == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            r a2 = n.a(this);
            a2.a(1.0f);
            a2.a(j);
            C0006a aVar = this.f572a;
            AbstractC0033a.this.f = a2;
            aVar.f578b = i;
            a2.a(aVar);
            return a2;
        }
        r a3 = n.a(this);
        a3.a(0.0f);
        a3.a(j);
        C0006a aVar2 = this.f572a;
        AbstractC0033a.this.f = a3;
        aVar2.f578b = i;
        a3.a(aVar2);
        return a3;
    }

    public AbstractC0033a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int i2;
        this.f572a = new C0006a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(a.actionBarPopupTheme, typedValue, true) || (i2 = typedValue.resourceId) == 0) {
            this.f573b = context;
        } else {
            this.f573b = new ContextThemeWrapper(context, i2);
        }
    }

    public int a(View view, int i, int i2, int i3) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - i3);
    }

    public int a(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = ((i3 - measuredHeight) / 2) + i2;
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
        } else {
            view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        }
        return z ? -measuredWidth : measuredWidth;
    }
}

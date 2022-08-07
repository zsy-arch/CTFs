package c.a.f;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.transition.Transition;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.ListMenuItemView;
import androidx.appcompat.widget.ListPopupWindow;
import c.a.e.a.k;
import c.a.e.a.l;
import c.a.e.a.p;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class L extends ListPopupWindow implements K {
    public static Method I;
    public K J;

    /* loaded from: classes.dex */
    public static class a extends F {
        public final int o;
        public final int p;
        public K q;
        public MenuItem r;

        public a(Context context, boolean z) {
            super(context, z);
            Configuration configuration = context.getResources().getConfiguration();
            int i = Build.VERSION.SDK_INT;
            if (1 == configuration.getLayoutDirection()) {
                this.o = 21;
                this.p = 22;
                return;
            }
            this.o = 22;
            this.p = 21;
        }

        @Override // c.a.f.F, android.view.View
        public boolean onHoverEvent(MotionEvent motionEvent) {
            int i;
            k kVar;
            int pointToPosition;
            int i2;
            if (this.q != null) {
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    i = headerViewListAdapter.getHeadersCount();
                    kVar = (k) headerViewListAdapter.getWrappedAdapter();
                } else {
                    i = 0;
                    kVar = (k) adapter;
                }
                p pVar = null;
                if (motionEvent.getAction() != 10 && (pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1 && (i2 = pointToPosition - i) >= 0 && i2 < kVar.getCount()) {
                    pVar = kVar.getItem(i2);
                }
                MenuItem menuItem = this.r;
                if (menuItem != pVar) {
                    l lVar = kVar.f445a;
                    if (menuItem != null) {
                        this.q.b(lVar, menuItem);
                    }
                    this.r = pVar;
                    if (pVar != null) {
                        this.q.a(lVar, pVar);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i == this.o) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            } else if (listMenuItemView == null || i != this.p) {
                return super.onKeyDown(i, keyEvent);
            } else {
                setSelection(-1);
                ((k) getAdapter()).f445a.a(false);
                return true;
            }
        }

        public void setHoverListener(K k) {
            this.q = k;
        }

        @Override // c.a.f.F, android.widget.AbsListView
        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }
    }

    static {
        try {
            I = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
        } catch (NoSuchMethodException unused) {
        }
    }

    public L(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // androidx.appcompat.widget.ListPopupWindow
    public F a(Context context, boolean z) {
        a aVar = new a(context, z);
        aVar.setHoverListener(this);
        return aVar;
    }

    @Override // c.a.f.K
    public void b(l lVar, MenuItem menuItem) {
        K k = this.J;
        if (k != null) {
            k.b(lVar, menuItem);
        }
    }

    public void a(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.H.setExitTransition((Transition) obj);
        }
    }

    @Override // c.a.f.K
    public void a(l lVar, MenuItem menuItem) {
        K k = this.J;
        if (k != null) {
            k.a(lVar, menuItem);
        }
    }
}

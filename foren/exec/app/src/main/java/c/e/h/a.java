package c.e.h;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import c.e.h.a.b;

/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final View.AccessibilityDelegate f841a = new View.AccessibilityDelegate();

    /* renamed from: b  reason: collision with root package name */
    public final View.AccessibilityDelegate f842b = new C0010a(this);

    /* renamed from: c.e.h.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static final class C0010a extends View.AccessibilityDelegate {

        /* renamed from: a  reason: collision with root package name */
        public final a f843a;

        public C0010a(a aVar) {
            this.f843a = aVar;
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.f843a.a(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            b a2 = this.f843a.a(view);
            if (a2 != null) {
                return (AccessibilityNodeProvider) a2.f848a;
            }
            return null;
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f843a.b(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            this.f843a.a(view, new c.e.h.a.a(accessibilityNodeInfo));
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f843a.c(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.f843a.a(viewGroup, view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            return this.f843a.a(view, i, bundle);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEvent(View view, int i) {
            this.f843a.a(view, i);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.f843a.d(view, accessibilityEvent);
        }
    }

    public void a(View view, int i) {
        f841a.sendAccessibilityEvent(view, i);
    }

    public void b(View view, AccessibilityEvent accessibilityEvent) {
        f841a.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void c(View view, AccessibilityEvent accessibilityEvent) {
        f841a.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public void d(View view, AccessibilityEvent accessibilityEvent) {
        f841a.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }

    public boolean a(View view, AccessibilityEvent accessibilityEvent) {
        return f841a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public void a(View view, c.e.h.a.a aVar) {
        f841a.onInitializeAccessibilityNodeInfo(view, aVar.f844a);
    }

    public boolean a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return f841a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public b a(View view) {
        int i = Build.VERSION.SDK_INT;
        AccessibilityNodeProvider accessibilityNodeProvider = f841a.getAccessibilityNodeProvider(view);
        if (accessibilityNodeProvider != null) {
            return new b(accessibilityNodeProvider);
        }
        return null;
    }

    public boolean a(View view, int i, Bundle bundle) {
        int i2 = Build.VERSION.SDK_INT;
        return f841a.performAccessibilityAction(view, i, bundle);
    }
}

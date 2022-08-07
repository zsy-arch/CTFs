package c.e.h.a;

import android.graphics.Rect;
import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;
import com.tencent.smtt.sdk.TbsListener;

/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public final AccessibilityNodeInfo f844a;

    public a(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.f844a = accessibilityNodeInfo;
    }

    public int a() {
        return this.f844a.getActions();
    }

    public CharSequence b() {
        return this.f844a.getClassName();
    }

    public CharSequence c() {
        return this.f844a.getContentDescription();
    }

    public CharSequence d() {
        return this.f844a.getPackageName();
    }

    public boolean e() {
        return this.f844a.isClickable();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        AccessibilityNodeInfo accessibilityNodeInfo = this.f844a;
        if (accessibilityNodeInfo == null) {
            if (aVar.f844a != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(aVar.f844a)) {
            return false;
        }
        return true;
    }

    public boolean f() {
        return this.f844a.isEnabled();
    }

    public boolean g() {
        return this.f844a.isFocusable();
    }

    public boolean h() {
        return this.f844a.isFocused();
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f844a;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public boolean i() {
        return this.f844a.isLongClickable();
    }

    public boolean j() {
        return this.f844a.isSelected();
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        this.f844a.getBoundsInParent(rect);
        sb.append("; boundsInParent: " + rect);
        this.f844a.getBoundsInScreen(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(d());
        sb.append("; className: ");
        sb.append(b());
        sb.append("; text: ");
        sb.append(this.f844a.getText());
        sb.append("; contentDescription: ");
        sb.append(c());
        sb.append("; viewId: ");
        int i = Build.VERSION.SDK_INT;
        sb.append(this.f844a.getViewIdResourceName());
        sb.append("; checkable: ");
        sb.append(this.f844a.isCheckable());
        sb.append("; checked: ");
        sb.append(this.f844a.isChecked());
        sb.append("; focusable: ");
        sb.append(g());
        sb.append("; focused: ");
        sb.append(h());
        sb.append("; selected: ");
        sb.append(j());
        sb.append("; clickable: ");
        sb.append(e());
        sb.append("; longClickable: ");
        sb.append(i());
        sb.append("; enabled: ");
        sb.append(f());
        sb.append("; password: ");
        sb.append(this.f844a.isPassword());
        sb.append("; scrollable: " + this.f844a.isScrollable());
        sb.append("; [");
        int a2 = a();
        while (a2 != 0) {
            int numberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(a2);
            a2 &= ~numberOfTrailingZeros;
            if (numberOfTrailingZeros == 1) {
                str = "ACTION_FOCUS";
            } else if (numberOfTrailingZeros != 2) {
                switch (numberOfTrailingZeros) {
                    case 4:
                        str = "ACTION_SELECT";
                        break;
                    case 8:
                        str = "ACTION_CLEAR_SELECTION";
                        break;
                    case 16:
                        str = "ACTION_CLICK";
                        break;
                    case 32:
                        str = "ACTION_LONG_CLICK";
                        break;
                    case 64:
                        str = "ACTION_ACCESSIBILITY_FOCUS";
                        break;
                    case TbsListener.ErrorCode.DOWNLOAD_INTERRUPT /* 128 */:
                        str = "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
                        break;
                    case 256:
                        str = "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
                        break;
                    case 512:
                        str = "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
                        break;
                    case 1024:
                        str = "ACTION_NEXT_HTML_ELEMENT";
                        break;
                    case 2048:
                        str = "ACTION_PREVIOUS_HTML_ELEMENT";
                        break;
                    case 4096:
                        str = "ACTION_SCROLL_FORWARD";
                        break;
                    case 8192:
                        str = "ACTION_SCROLL_BACKWARD";
                        break;
                    case 16384:
                        str = "ACTION_COPY";
                        break;
                    case 32768:
                        str = "ACTION_PASTE";
                        break;
                    case 65536:
                        str = "ACTION_CUT";
                        break;
                    case 131072:
                        str = "ACTION_SET_SELECTION";
                        break;
                    default:
                        str = "ACTION_UNKNOWN";
                        break;
                }
            } else {
                str = "ACTION_CLEAR_FOCUS";
            }
            sb.append(str);
            if (a2 != 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /* renamed from: c.e.h.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0011a {

        /* renamed from: a  reason: collision with root package name */
        public static final C0011a f845a = new C0011a(1, null);

        /* renamed from: b  reason: collision with root package name */
        public static final C0011a f846b = new C0011a(2, null);

        /* renamed from: c  reason: collision with root package name */
        public final Object f847c;

        static {
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction = null;
            new C0011a(4, null);
            new C0011a(8, null);
            new C0011a(16, null);
            new C0011a(32, null);
            new C0011a(64, null);
            new C0011a(TbsListener.ErrorCode.DOWNLOAD_INTERRUPT, null);
            new C0011a(256, null);
            new C0011a(512, null);
            new C0011a(1024, null);
            new C0011a(2048, null);
            new C0011a(4096, null);
            new C0011a(8192, null);
            new C0011a(16384, null);
            new C0011a(32768, null);
            new C0011a(65536, null);
            new C0011a(131072, null);
            new C0011a(262144, null);
            new C0011a(524288, null);
            new C0011a(1048576, null);
            new C0011a(2097152, null);
            new C0011a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN : null);
            new C0011a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION : null);
            new C0011a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP : null);
            new C0011a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT : null);
            new C0011a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN : null);
            new C0011a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT : null);
            new C0011a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK : null);
            new C0011a(Build.VERSION.SDK_INT >= 24 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS : null);
            new C0011a(Build.VERSION.SDK_INT >= 26 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW : null);
            new C0011a(Build.VERSION.SDK_INT >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null);
            if (Build.VERSION.SDK_INT >= 28) {
                accessibilityAction = AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP;
            }
            new C0011a(accessibilityAction);
        }

        public C0011a(int i, CharSequence charSequence) {
            int i2 = Build.VERSION.SDK_INT;
            this.f847c = new AccessibilityNodeInfo.AccessibilityAction(i, charSequence);
        }

        public C0011a(Object obj) {
            this.f847c = obj;
        }
    }
}

package c.a.f;

import android.os.Build;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.appcompat.widget.ActivityChooserView;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: c.a.f.l  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0044l extends View.AccessibilityDelegate {
    public C0044l(ActivityChooserView activityChooserView) {
    }

    @Override // android.view.View.AccessibilityDelegate
    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        int i = Build.VERSION.SDK_INT;
        accessibilityNodeInfo.setCanOpenPopup(true);
    }
}

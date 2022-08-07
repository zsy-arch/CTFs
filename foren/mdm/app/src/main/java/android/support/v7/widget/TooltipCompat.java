package android.support.v7.widget;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/* loaded from: classes.dex */
public class TooltipCompat {
    private static final ViewCompatImpl IMPL;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface ViewCompatImpl {
        void setTooltipText(@NonNull View view, @Nullable CharSequence charSequence);
    }

    /* loaded from: classes.dex */
    private static class BaseViewCompatImpl implements ViewCompatImpl {
        private BaseViewCompatImpl() {
        }

        @Override // android.support.v7.widget.TooltipCompat.ViewCompatImpl
        public void setTooltipText(@NonNull View view, @Nullable CharSequence tooltipText) {
            TooltipCompatHandler.setTooltipText(view, tooltipText);
        }
    }

    @TargetApi(26)
    /* loaded from: classes.dex */
    private static class Api26ViewCompatImpl implements ViewCompatImpl {
        private Api26ViewCompatImpl() {
        }

        @Override // android.support.v7.widget.TooltipCompat.ViewCompatImpl
        public void setTooltipText(@NonNull View view, @Nullable CharSequence tooltipText) {
            view.setTooltipText(tooltipText);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 26) {
            IMPL = new Api26ViewCompatImpl();
        } else {
            IMPL = new BaseViewCompatImpl();
        }
    }

    public static void setTooltipText(@NonNull View view, @Nullable CharSequence tooltipText) {
        IMPL.setTooltipText(view, tooltipText);
    }

    private TooltipCompat() {
    }
}

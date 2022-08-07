package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.support.annotation.NonNull;

/* loaded from: classes.dex */
class AnimatorUtils {
    private static final AnimatorUtilsImpl IMPL;

    AnimatorUtils() {
    }

    static {
        if (Build.VERSION.SDK_INT >= 19) {
            IMPL = new AnimatorUtilsApi19();
        } else {
            IMPL = new AnimatorUtilsApi14();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addPauseListener(@NonNull Animator animator, @NonNull AnimatorListenerAdapter listener) {
        IMPL.addPauseListener(animator, listener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void pause(@NonNull Animator animator) {
        IMPL.pause(animator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void resume(@NonNull Animator animator) {
        IMPL.resume(animator);
    }
}

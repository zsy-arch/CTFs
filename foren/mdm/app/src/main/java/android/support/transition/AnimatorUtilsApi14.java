package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;

@RequiresApi(14)
/* loaded from: classes.dex */
class AnimatorUtilsApi14 implements AnimatorUtilsImpl {

    /* loaded from: classes.dex */
    interface AnimatorPauseListenerCompat {
        void onAnimationPause(Animator animator);

        void onAnimationResume(Animator animator);
    }

    @Override // android.support.transition.AnimatorUtilsImpl
    public void addPauseListener(@NonNull Animator animator, @NonNull AnimatorListenerAdapter listener) {
    }

    @Override // android.support.transition.AnimatorUtilsImpl
    public void pause(@NonNull Animator animator) {
        ArrayList<Animator.AnimatorListener> listeners = animator.getListeners();
        if (listeners != null) {
            int size = listeners.size();
            for (int i = 0; i < size; i++) {
                Animator.AnimatorListener listener = listeners.get(i);
                if (listener instanceof AnimatorPauseListenerCompat) {
                    ((AnimatorPauseListenerCompat) listener).onAnimationPause(animator);
                }
            }
        }
    }

    @Override // android.support.transition.AnimatorUtilsImpl
    public void resume(@NonNull Animator animator) {
        ArrayList<Animator.AnimatorListener> listeners = animator.getListeners();
        if (listeners != null) {
            int size = listeners.size();
            for (int i = 0; i < size; i++) {
                Animator.AnimatorListener listener = listeners.get(i);
                if (listener instanceof AnimatorPauseListenerCompat) {
                    ((AnimatorPauseListenerCompat) listener).onAnimationResume(animator);
                }
            }
        }
    }
}

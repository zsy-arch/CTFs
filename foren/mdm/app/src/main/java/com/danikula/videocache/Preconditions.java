package com.danikula.videocache;

/* loaded from: classes.dex */
public final class Preconditions {
    public static <T> T checkNotNull(T reference) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException();
    }

    public static void checkAllNotNull(Object... references) {
        for (Object reference : references) {
            if (reference == null) {
                throw new NullPointerException();
            }
        }
    }

    public static <T> T checkNotNull(T reference, String errorMessage) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(errorMessage);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkArgument(boolean expression, String errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}

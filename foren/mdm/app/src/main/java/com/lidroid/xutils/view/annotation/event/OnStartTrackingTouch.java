package com.lidroid.xutils.view.annotation.event;

import android.widget.SeekBar;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@EventBase(listenerSetter = "setOnSeekBarChangeListener", listenerType = SeekBar.OnSeekBarChangeListener.class, methodName = "onStartTrackingTouch")
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface OnStartTrackingTouch {
    int[] parentId() default {0};

    int[] value();
}

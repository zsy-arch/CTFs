package com.lidroid.xutils.view.annotation.event;

import android.widget.RadioGroup;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@EventBase(listenerSetter = "setOnCheckedChangeListener", listenerType = RadioGroup.OnCheckedChangeListener.class, methodName = "onCheckedChanged")
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface OnRadioGroupCheckedChange {
    int[] parentId() default {0};

    int[] value();
}

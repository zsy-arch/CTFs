package com.lidroid.xutils.view.annotation.event;

import android.view.View;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@EventBase(listenerSetter = "setOnClickListener", listenerType = View.OnClickListener.class, methodName = "onClick")
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface OnClick {
    int[] parentId() default {0};

    int[] value();
}

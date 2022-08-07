package com.lidroid.xutils.view.annotation.event;

import android.widget.ExpandableListView;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@EventBase(listenerSetter = "setOnGroupClickListener", listenerType = ExpandableListView.OnGroupClickListener.class, methodName = "onGroupClick")
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface OnGroupClick {
    int[] parentId() default {0};

    int[] value();
}

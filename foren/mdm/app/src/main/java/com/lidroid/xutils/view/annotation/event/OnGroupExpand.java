package com.lidroid.xutils.view.annotation.event;

import android.widget.ExpandableListView;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@EventBase(listenerSetter = "setOnGroupExpandListener", listenerType = ExpandableListView.OnGroupExpandListener.class, methodName = "onGroupExpand")
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface OnGroupExpand {
    int[] parentId() default {0};

    int[] value();
}

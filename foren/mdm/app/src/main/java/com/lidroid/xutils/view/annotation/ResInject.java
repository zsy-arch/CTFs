package com.lidroid.xutils.view.annotation;

import com.lidroid.xutils.view.ResType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface ResInject {
    int id();

    ResType type();
}

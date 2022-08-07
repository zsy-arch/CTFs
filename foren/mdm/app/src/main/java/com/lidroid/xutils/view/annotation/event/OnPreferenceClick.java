package com.lidroid.xutils.view.annotation.event;

import android.preference.Preference;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@EventBase(listenerSetter = "setOnPreferenceClickListener", listenerType = Preference.OnPreferenceClickListener.class, methodName = "onPreferenceClick")
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface OnPreferenceClick {
    String[] value();
}

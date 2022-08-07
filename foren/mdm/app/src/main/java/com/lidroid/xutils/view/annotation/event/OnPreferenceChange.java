package com.lidroid.xutils.view.annotation.event;

import android.preference.Preference;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@EventBase(listenerSetter = "setOnPreferenceChangeListener", listenerType = Preference.OnPreferenceChangeListener.class, methodName = "onPreferenceChange")
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface OnPreferenceChange {
    String[] value();
}

package com.lidroid.xutils;

import android.app.Activity;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.view.View;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.EventListenerManager;
import com.lidroid.xutils.view.ResLoader;
import com.lidroid.xutils.view.ViewFinder;
import com.lidroid.xutils.view.ViewInjectInfo;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.PreferenceInject;
import com.lidroid.xutils.view.annotation.ResInject;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.EventBase;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class ViewUtils {
    private ViewUtils() {
    }

    public static void inject(View view) {
        injectObject(view, new ViewFinder(view));
    }

    public static void inject(Activity activity) {
        injectObject(activity, new ViewFinder(activity));
    }

    public static void inject(PreferenceActivity preferenceActivity) {
        injectObject(preferenceActivity, new ViewFinder(preferenceActivity));
    }

    public static void inject(Object handler, View view) {
        injectObject(handler, new ViewFinder(view));
    }

    public static void inject(Object handler, Activity activity) {
        injectObject(handler, new ViewFinder(activity));
    }

    public static void inject(Object handler, PreferenceGroup preferenceGroup) {
        injectObject(handler, new ViewFinder(preferenceGroup));
    }

    public static void inject(Object handler, PreferenceActivity preferenceActivity) {
        injectObject(handler, new ViewFinder(preferenceActivity));
    }

    private static void injectObject(Object handler, ViewFinder finder) {
        Class<?> handlerType = handler.getClass();
        ContentView contentView = (ContentView) handlerType.getAnnotation(ContentView.class);
        if (contentView != null) {
            try {
                handlerType.getMethod("setContentView", Integer.TYPE).invoke(handler, Integer.valueOf(contentView.value()));
            } catch (Throwable e) {
                LogUtils.e(e.getMessage(), e);
            }
        }
        Field[] fields = handlerType.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                ViewInject viewInject = (ViewInject) field.getAnnotation(ViewInject.class);
                if (viewInject != null) {
                    try {
                        View view = finder.findViewById(viewInject.value(), viewInject.parentId());
                        if (view != null) {
                            field.setAccessible(true);
                            field.set(handler, view);
                        }
                    } catch (Throwable e2) {
                        LogUtils.e(e2.getMessage(), e2);
                    }
                } else {
                    ResInject resInject = (ResInject) field.getAnnotation(ResInject.class);
                    if (resInject != null) {
                        try {
                            Object res = ResLoader.loadRes(resInject.type(), finder.getContext(), resInject.id());
                            if (res != null) {
                                field.setAccessible(true);
                                field.set(handler, res);
                            }
                        } catch (Throwable e3) {
                            LogUtils.e(e3.getMessage(), e3);
                        }
                    } else {
                        PreferenceInject preferenceInject = (PreferenceInject) field.getAnnotation(PreferenceInject.class);
                        if (preferenceInject != null) {
                            try {
                                Preference preference = finder.findPreference(preferenceInject.value());
                                if (preference != null) {
                                    field.setAccessible(true);
                                    field.set(handler, preference);
                                }
                            } catch (Throwable e4) {
                                LogUtils.e(e4.getMessage(), e4);
                            }
                        }
                    }
                }
            }
        }
        Method[] methods = handlerType.getDeclaredMethods();
        if (methods != null && methods.length > 0) {
            for (Method method : methods) {
                Annotation[] annotations = method.getDeclaredAnnotations();
                if (annotations != null && annotations.length > 0) {
                    for (Annotation annotation : annotations) {
                        Class<?> annType = annotation.annotationType();
                        if (annType.getAnnotation(EventBase.class) != null) {
                            method.setAccessible(true);
                            try {
                                Method valueMethod = annType.getDeclaredMethod("value", new Class[0]);
                                Method parentIdMethod = null;
                                try {
                                    parentIdMethod = annType.getDeclaredMethod("parentId", new Class[0]);
                                } catch (Throwable th) {
                                }
                                Object values = valueMethod.invoke(annotation, new Object[0]);
                                Object parentIds = parentIdMethod == null ? null : parentIdMethod.invoke(annotation, new Object[0]);
                                int parentIdsLen = parentIds == null ? 0 : Array.getLength(parentIds);
                                int len = Array.getLength(values);
                                int i = 0;
                                while (i < len) {
                                    ViewInjectInfo info = new ViewInjectInfo();
                                    info.value = Array.get(values, i);
                                    info.parentId = parentIdsLen > i ? ((Integer) Array.get(parentIds, i)).intValue() : 0;
                                    EventListenerManager.addEventMethod(finder, info, annotation, handler, method);
                                    i++;
                                }
                            } catch (Throwable e5) {
                                LogUtils.e(e5.getMessage(), e5);
                            }
                        }
                    }
                }
            }
        }
    }
}

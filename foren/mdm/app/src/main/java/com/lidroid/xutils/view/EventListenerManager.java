package com.lidroid.xutils.view;

import android.view.View;
import com.lidroid.xutils.util.DoubleKeyValueMap;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.event.EventBase;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class EventListenerManager {
    private static final DoubleKeyValueMap<ViewInjectInfo, Class<?>, Object> listenerCache = new DoubleKeyValueMap<>();

    private EventListenerManager() {
    }

    public static void addEventMethod(ViewFinder finder, ViewInjectInfo info, Annotation eventAnnotation, Object handler, Method method) {
        try {
            View view = finder.findViewByInfo(info);
            if (view != null) {
                EventBase eventBase = (EventBase) eventAnnotation.annotationType().getAnnotation(EventBase.class);
                Class<?> listenerType = eventBase.listenerType();
                String listenerSetter = eventBase.listenerSetter();
                String methodName = eventBase.methodName();
                boolean addNewMethod = false;
                Object listener = listenerCache.get(info, listenerType);
                if (listener != null) {
                    DynamicHandler dynamicHandler = (DynamicHandler) Proxy.getInvocationHandler(listener);
                    addNewMethod = handler.equals(dynamicHandler.getHandler());
                    if (addNewMethod) {
                        dynamicHandler.addMethod(methodName, method);
                    }
                }
                if (!addNewMethod) {
                    DynamicHandler dynamicHandler2 = new DynamicHandler(handler);
                    dynamicHandler2.addMethod(methodName, method);
                    listener = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, dynamicHandler2);
                    listenerCache.put(info, listenerType, listener);
                }
                view.getClass().getMethod(listenerSetter, listenerType).invoke(view, listener);
            }
        } catch (Throwable e) {
            LogUtils.e(e.getMessage(), e);
        }
    }

    /* loaded from: classes2.dex */
    public static class DynamicHandler implements InvocationHandler {
        private WeakReference<Object> handlerRef;
        private final HashMap<String, Method> methodMap = new HashMap<>(1);

        public DynamicHandler(Object handler) {
            this.handlerRef = new WeakReference<>(handler);
        }

        public void addMethod(String name, Method method) {
            this.methodMap.put(name, method);
        }

        public Object getHandler() {
            return this.handlerRef.get();
        }

        public void setHandler(Object handler) {
            this.handlerRef = new WeakReference<>(handler);
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method method2;
            Object handler = this.handlerRef.get();
            if (handler == null || (method2 = this.methodMap.get(method.getName())) == null) {
                return null;
            }
            return method2.invoke(handler, args);
        }
    }
}

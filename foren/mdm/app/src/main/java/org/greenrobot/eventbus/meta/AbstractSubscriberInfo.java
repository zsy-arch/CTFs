package org.greenrobot.eventbus.meta;

import org.greenrobot.eventbus.EventBusException;
import org.greenrobot.eventbus.SubscriberMethod;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes2.dex */
public abstract class AbstractSubscriberInfo implements SubscriberInfo {
    private final boolean shouldCheckSuperclass;
    private final Class subscriberClass;
    private final Class<? extends SubscriberInfo> superSubscriberInfoClass;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractSubscriberInfo(Class subscriberClass, Class<? extends SubscriberInfo> superSubscriberInfoClass, boolean shouldCheckSuperclass) {
        this.subscriberClass = subscriberClass;
        this.superSubscriberInfoClass = superSubscriberInfoClass;
        this.shouldCheckSuperclass = shouldCheckSuperclass;
    }

    @Override // org.greenrobot.eventbus.meta.SubscriberInfo
    public Class getSubscriberClass() {
        return this.subscriberClass;
    }

    @Override // org.greenrobot.eventbus.meta.SubscriberInfo
    public SubscriberInfo getSuperSubscriberInfo() {
        if (this.superSubscriberInfoClass == null) {
            return null;
        }
        try {
            return (SubscriberInfo) this.superSubscriberInfoClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // org.greenrobot.eventbus.meta.SubscriberInfo
    public boolean shouldCheckSuperclass() {
        return this.shouldCheckSuperclass;
    }

    protected SubscriberMethod createSubscriberMethod(String methodName, Class<?> eventType) {
        return createSubscriberMethod(methodName, eventType, ThreadMode.POSTING, 0, false);
    }

    protected SubscriberMethod createSubscriberMethod(String methodName, Class<?> eventType, ThreadMode threadMode) {
        return createSubscriberMethod(methodName, eventType, threadMode, 0, false);
    }

    protected SubscriberMethod createSubscriberMethod(String methodName, Class<?> eventType, ThreadMode threadMode, int priority, boolean sticky) {
        try {
            return new SubscriberMethod(this.subscriberClass.getDeclaredMethod(methodName, eventType), eventType, threadMode, priority, sticky);
        } catch (NoSuchMethodException e) {
            throw new EventBusException("Could not find subscriber method in " + this.subscriberClass + ". Maybe a missing ProGuard rule?", e);
        }
    }
}

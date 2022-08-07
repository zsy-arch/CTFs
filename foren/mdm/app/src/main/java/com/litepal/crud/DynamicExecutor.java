package com.litepal.crud;

import com.litepal.exceptions.DataSupportException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class DynamicExecutor {
    private DynamicExecutor() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object send(Object object, String methodName, Object[] parameters, Class<?> objectClass, Class<?>[] parameterTypes) throws SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (parameters == null) {
            try {
                parameters = new Object[0];
            } catch (NoSuchMethodException e) {
                throw new DataSupportException(DataSupportException.noSuchMethodException(objectClass.getSimpleName(), methodName), e);
            }
        }
        if (parameterTypes == null) {
            parameterTypes = new Class[0];
        }
        Method method = objectClass.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(object, parameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void set(Object object, String fieldName, Object value, Class<?> objectClass) throws SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        Field objectField = objectClass.getDeclaredField(fieldName);
        objectField.setAccessible(true);
        objectField.set(object, value);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setField(Object object, String fieldName, Object value, Class<?> objectClass) throws SecurityException, IllegalArgumentException, IllegalAccessException {
        if (objectClass == DataSupport.class || objectClass == Object.class) {
            throw new DataSupportException(DataSupportException.noSuchFieldExceptioin(objectClass.getSimpleName(), fieldName));
        }
        try {
            set(object, fieldName, value, objectClass);
        } catch (NoSuchFieldException e) {
            setField(object, fieldName, value, objectClass.getSuperclass());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object getField(Object object, String fieldName, Class<?> objectClass) throws IllegalArgumentException, IllegalAccessException {
        if (objectClass == DataSupport.class || objectClass == Object.class) {
            throw new DataSupportException(DataSupportException.noSuchFieldExceptioin(objectClass.getSimpleName(), fieldName));
        }
        try {
            Field objectField = objectClass.getDeclaredField(fieldName);
            objectField.setAccessible(true);
            return objectField.get(object);
        } catch (NoSuchFieldException e) {
            return getField(object, fieldName, objectClass.getSuperclass());
        }
    }
}

package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class DeserializeBeanInfo {
    private Constructor<?> creatorConstructor;
    private Constructor<?> defaultConstructor;
    private Method factoryMethod;
    private int parserFeatures;
    private final List<FieldInfo> fieldList = new ArrayList();
    private final List<FieldInfo> sortedFieldList = new ArrayList();

    public DeserializeBeanInfo(Class<?> clazz) {
        this.parserFeatures = 0;
        this.parserFeatures = TypeUtils.getParserFeatures(clazz);
    }

    public Constructor<?> getDefaultConstructor() {
        return this.defaultConstructor;
    }

    public void setDefaultConstructor(Constructor<?> defaultConstructor) {
        this.defaultConstructor = defaultConstructor;
    }

    public Constructor<?> getCreatorConstructor() {
        return this.creatorConstructor;
    }

    public void setCreatorConstructor(Constructor<?> createConstructor) {
        this.creatorConstructor = createConstructor;
    }

    public Method getFactoryMethod() {
        return this.factoryMethod;
    }

    public void setFactoryMethod(Method factoryMethod) {
        this.factoryMethod = factoryMethod;
    }

    public List<FieldInfo> getFieldList() {
        return this.fieldList;
    }

    public List<FieldInfo> getSortedFieldList() {
        return this.sortedFieldList;
    }

    public boolean add(FieldInfo field) {
        for (FieldInfo item : this.fieldList) {
            if (item.getName().equals(field.getName()) && (!item.isGetOnly() || field.isGetOnly())) {
                return false;
            }
        }
        this.fieldList.add(field);
        this.sortedFieldList.add(field);
        Collections.sort(this.sortedFieldList);
        return true;
    }

    /* JADX INFO: Multiple debug info for r22v3 java.lang.reflect.Field[]: [D('arr$' java.lang.reflect.Method[]), D('arr$' java.lang.reflect.Field[])] */
    /* JADX INFO: Multiple debug info for r22v4 java.lang.reflect.Method[]: [D('arr$' java.lang.reflect.Method[]), D('arr$' java.lang.reflect.Field[])] */
    public static DeserializeBeanInfo computeSetters(Class<?> clazz, Type type) {
        String propertyName;
        String propertyName2;
        JSONField fieldAnnotation;
        DeserializeBeanInfo beanInfo = new DeserializeBeanInfo(clazz);
        Constructor<?> defaultConstructor = getDefaultConstructor(clazz);
        if (defaultConstructor != null) {
            TypeUtils.setAccessible(defaultConstructor);
            beanInfo.setDefaultConstructor(defaultConstructor);
        } else if (defaultConstructor == null && !clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers())) {
            Constructor<?> creatorConstructor = getCreatorConstructor(clazz);
            if (creatorConstructor != null) {
                TypeUtils.setAccessible(creatorConstructor);
                beanInfo.setCreatorConstructor(creatorConstructor);
                for (int i = 0; i < creatorConstructor.getParameterTypes().length; i++) {
                    Annotation[] paramAnnotations = creatorConstructor.getParameterAnnotations()[i];
                    JSONField fieldAnnotation2 = null;
                    int len$ = paramAnnotations.length;
                    int i$ = 0;
                    while (true) {
                        if (i$ >= len$) {
                            break;
                        }
                        Annotation paramAnnotation = paramAnnotations[i$];
                        if (paramAnnotation instanceof JSONField) {
                            fieldAnnotation2 = (JSONField) paramAnnotation;
                            break;
                        }
                        i$++;
                    }
                    if (fieldAnnotation2 == null) {
                        throw new JSONException("illegal json creator");
                    }
                    beanInfo.add(new FieldInfo(fieldAnnotation2.name(), clazz, creatorConstructor.getParameterTypes()[i], creatorConstructor.getGenericParameterTypes()[i], TypeUtils.getField(clazz, fieldAnnotation2.name()), fieldAnnotation2.ordinal(), SerializerFeature.of(fieldAnnotation2.serialzeFeatures())));
                }
            } else {
                Method factoryMethod = getFactoryMethod(clazz);
                if (factoryMethod != null) {
                    TypeUtils.setAccessible(factoryMethod);
                    beanInfo.setFactoryMethod(factoryMethod);
                    for (int i2 = 0; i2 < factoryMethod.getParameterTypes().length; i2++) {
                        Annotation[] paramAnnotations2 = factoryMethod.getParameterAnnotations()[i2];
                        JSONField fieldAnnotation3 = null;
                        int len$2 = paramAnnotations2.length;
                        int i$2 = 0;
                        while (true) {
                            if (i$2 >= len$2) {
                                break;
                            }
                            Annotation paramAnnotation2 = paramAnnotations2[i$2];
                            if (paramAnnotation2 instanceof JSONField) {
                                fieldAnnotation3 = (JSONField) paramAnnotation2;
                                break;
                            }
                            i$2++;
                        }
                        if (fieldAnnotation3 == null) {
                            throw new JSONException("illegal json creator");
                        }
                        beanInfo.add(new FieldInfo(fieldAnnotation3.name(), clazz, factoryMethod.getParameterTypes()[i2], factoryMethod.getGenericParameterTypes()[i2], TypeUtils.getField(clazz, fieldAnnotation3.name()), fieldAnnotation3.ordinal(), SerializerFeature.of(fieldAnnotation3.serialzeFeatures())));
                    }
                } else {
                    throw new JSONException("default constructor not found. " + clazz);
                }
            }
            return beanInfo;
        }
        Method[] arr$ = clazz.getMethods();
        for (Method method : arr$) {
            int ordinal = 0;
            int serialzeFeatures = 0;
            String methodName = method.getName();
            if (methodName.length() >= 4 && !Modifier.isStatic(method.getModifiers()) && ((method.getReturnType().equals(Void.TYPE) || method.getReturnType().equals(clazz)) && method.getParameterTypes().length == 1)) {
                JSONField annotation = (JSONField) method.getAnnotation(JSONField.class);
                if (annotation == null) {
                    annotation = TypeUtils.getSupperMethodAnnotation(clazz, method);
                }
                if (annotation != null) {
                    if (annotation.deserialize()) {
                        ordinal = annotation.ordinal();
                        serialzeFeatures = SerializerFeature.of(annotation.serialzeFeatures());
                        if (annotation.name().length() != 0) {
                            beanInfo.add(new FieldInfo(annotation.name(), method, (Field) null, clazz, type, ordinal, serialzeFeatures));
                            TypeUtils.setAccessible(method);
                        }
                    }
                }
                if (methodName.startsWith("set")) {
                    char c3 = methodName.charAt(3);
                    if (Character.isUpperCase(c3)) {
                        if (TypeUtils.compatibleWithJavaBean) {
                            propertyName2 = TypeUtils.decapitalize(methodName.substring(3));
                        } else {
                            propertyName2 = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
                        }
                    } else if (c3 == '_') {
                        propertyName2 = methodName.substring(4);
                    } else if (c3 == 'f') {
                        propertyName2 = methodName.substring(3);
                    } else if (methodName.length() >= 5 && Character.isUpperCase(methodName.charAt(4))) {
                        propertyName2 = TypeUtils.decapitalize(methodName.substring(3));
                    }
                    Field field = TypeUtils.getField(clazz, propertyName2);
                    if (field == null && method.getParameterTypes()[0] == Boolean.TYPE) {
                        field = TypeUtils.getField(clazz, "is" + Character.toUpperCase(propertyName2.charAt(0)) + propertyName2.substring(1));
                    }
                    if (!(field == null || (fieldAnnotation = (JSONField) field.getAnnotation(JSONField.class)) == null)) {
                        ordinal = fieldAnnotation.ordinal();
                        serialzeFeatures = SerializerFeature.of(fieldAnnotation.serialzeFeatures());
                        if (fieldAnnotation.name().length() != 0) {
                            beanInfo.add(new FieldInfo(fieldAnnotation.name(), method, field, clazz, type, ordinal, serialzeFeatures));
                        }
                    }
                    beanInfo.add(new FieldInfo(propertyName2, method, (Field) null, clazz, type, ordinal, serialzeFeatures));
                    TypeUtils.setAccessible(method);
                }
            }
        }
        Field[] arr$2 = clazz.getFields();
        for (Field field2 : arr$2) {
            if (!Modifier.isStatic(field2.getModifiers())) {
                boolean contains = false;
                for (FieldInfo item : beanInfo.getFieldList()) {
                    if (item.getName().equals(field2.getName())) {
                        contains = true;
                    }
                }
                if (!contains) {
                    int ordinal2 = 0;
                    int serialzeFeatures2 = 0;
                    String propertyName3 = field2.getName();
                    JSONField fieldAnnotation4 = (JSONField) field2.getAnnotation(JSONField.class);
                    if (fieldAnnotation4 != null) {
                        ordinal2 = fieldAnnotation4.ordinal();
                        serialzeFeatures2 = SerializerFeature.of(fieldAnnotation4.serialzeFeatures());
                        if (fieldAnnotation4.name().length() != 0) {
                            propertyName3 = fieldAnnotation4.name();
                        }
                    }
                    beanInfo.add(new FieldInfo(propertyName3, (Method) null, field2, clazz, type, ordinal2, serialzeFeatures2));
                }
            }
        }
        Method[] arr$3 = clazz.getMethods();
        for (Method method2 : arr$3) {
            String methodName2 = method2.getName();
            if (methodName2.length() >= 4 && !Modifier.isStatic(method2.getModifiers()) && methodName2.startsWith("get") && Character.isUpperCase(methodName2.charAt(3)) && method2.getParameterTypes().length == 0 && (Collection.class.isAssignableFrom(method2.getReturnType()) || Map.class.isAssignableFrom(method2.getReturnType()))) {
                JSONField annotation2 = (JSONField) method2.getAnnotation(JSONField.class);
                if (annotation2 == null || annotation2.name().length() <= 0) {
                    propertyName = Character.toLowerCase(methodName2.charAt(3)) + methodName2.substring(4);
                } else {
                    propertyName = annotation2.name();
                }
                beanInfo.add(new FieldInfo(propertyName, method2, (Field) null, clazz, type));
                TypeUtils.setAccessible(method2);
            }
        }
        return beanInfo;
    }

    public static Constructor<?> getDefaultConstructor(Class<?> clazz) {
        if (Modifier.isAbstract(clazz.getModifiers())) {
            return null;
        }
        Constructor<?> defaultConstructor = null;
        Constructor<?>[] arr$ = clazz.getDeclaredConstructors();
        int len$ = arr$.length;
        int i$ = 0;
        while (true) {
            if (i$ >= len$) {
                break;
            }
            Constructor<?> constructor = arr$[i$];
            if (constructor.getParameterTypes().length == 0) {
                defaultConstructor = constructor;
                break;
            }
            i$++;
        }
        if (defaultConstructor != null || !clazz.isMemberClass() || Modifier.isStatic(clazz.getModifiers())) {
            return defaultConstructor;
        }
        Constructor<?>[] arr$2 = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor2 : arr$2) {
            if (constructor2.getParameterTypes().length == 1 && constructor2.getParameterTypes()[0].equals(clazz.getDeclaringClass())) {
                return constructor2;
            }
        }
        return defaultConstructor;
    }

    public static Constructor<?> getCreatorConstructor(Class<?> clazz) {
        Constructor<?>[] arr$ = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : arr$) {
            if (((JSONCreator) constructor.getAnnotation(JSONCreator.class)) != null) {
                if (0 == 0) {
                    return constructor;
                } else {
                    throw new JSONException("multi-json creator");
                }
            }
        }
        return null;
    }

    public static Method getFactoryMethod(Class<?> clazz) {
        Method[] arr$ = clazz.getDeclaredMethods();
        for (Method method : arr$) {
            if (Modifier.isStatic(method.getModifiers()) && clazz.isAssignableFrom(method.getReturnType()) && ((JSONCreator) method.getAnnotation(JSONCreator.class)) != null) {
                if (0 == 0) {
                    return method;
                } else {
                    throw new JSONException("multi-json creator");
                }
            }
        }
        return null;
    }

    public int getParserFeatures() {
        return this.parserFeatures;
    }
}

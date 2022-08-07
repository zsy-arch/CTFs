package com.mob.tools.utils;

import com.mob.tools.gui.CachePool;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class ReflectHelper {
    private static HashSet<String> packageSet = new HashSet<>();
    private static HashMap<String, Class<?>> classMap = new HashMap<>();
    private static HashMap<Class<?>, String> nameMap = new HashMap<>();
    private static CachePool<String, Method> cachedMethod = new CachePool<>(25);
    private static CachePool<String, Constructor<?>> cachedConstr = new CachePool<>(5);

    /* loaded from: classes2.dex */
    public interface ReflectRunnable {
        Object run(Object obj);
    }

    static {
        packageSet.add("java.lang");
        packageSet.add("java.io");
        packageSet.add("java.nio");
        packageSet.add("java.net");
        packageSet.add("java.util");
        packageSet.add("com.mob.tools");
        packageSet.add("com.mob.tools.gui");
        packageSet.add("com.mob.tools.log");
        packageSet.add("com.mob.tools.network");
        packageSet.add("com.mob.tools.utils");
    }

    public static Object createProxy(final HashMap<String, ReflectRunnable> proxyHandler, Class<?>... proxyIntefaces) throws Throwable {
        return Proxy.newProxyInstance(proxyHandler.getClass().getClassLoader(), proxyIntefaces, new InvocationHandler() { // from class: com.mob.tools.utils.ReflectHelper.1
            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                ReflectRunnable function = (ReflectRunnable) proxyHandler.get(method.getName());
                if (function != null) {
                    return function.run(args);
                }
                throw new NoSuchMethodException();
            }
        });
    }

    public static Class<?> getClass(String name) throws Throwable {
        Class<?> clz = getImportedClass(name);
        if (clz == null) {
            try {
                clz = Class.forName(name);
                if (clz != null) {
                    classMap.put(name, clz);
                }
            } catch (Throwable th) {
            }
        }
        return clz;
    }

    private static synchronized Class<?> getImportedClass(String className) {
        Class<?> clz;
        synchronized (ReflectHelper.class) {
            clz = classMap.get(className);
            if (clz == null) {
                Iterator<String> it = packageSet.iterator();
                while (it.hasNext()) {
                    try {
                        importClass(it.next() + "." + className);
                    } catch (Throwable th) {
                    }
                    clz = classMap.get(className);
                    if (clz != null) {
                        break;
                    }
                }
            }
        }
        return clz;
    }

    public static <T> T getInstanceField(Object receiver, String fieldName) throws Throwable {
        try {
            return (T) onGetInstanceField(receiver, fieldName);
        } catch (Throwable t) {
            if (t instanceof NoSuchFieldException) {
                throw t;
            }
            throw new Throwable("className: " + receiver.getClass() + ", fieldName: " + fieldName, t);
        }
    }

    public static String getName(Class<?> clz) throws Throwable {
        String name = nameMap.get(clz);
        if (name != null) {
            return name;
        }
        String name2 = clz.getSimpleName();
        if (classMap.containsKey(name2)) {
            return null;
        }
        classMap.put(name2, clz);
        nameMap.put(clz, name2);
        return name2;
    }

    public static <T> T getStaticField(String className, String fieldName) throws Throwable {
        try {
            return (T) onGetStaticField(className, fieldName);
        } catch (Throwable t) {
            if (t instanceof NoSuchFieldException) {
                throw t;
            }
            throw new Throwable("className: " + className + ", fieldName: " + fieldName, t);
        }
    }

    private static Class<?>[] getTypes(Object[] args) {
        Class<?>[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i] == null ? null : args[i].getClass();
        }
        return types;
    }

    public static String importClass(String className) throws Throwable {
        return importClass(null, className);
    }

    public static synchronized String importClass(String name, String className) throws Throwable {
        synchronized (ReflectHelper.class) {
            if (className.endsWith(".*")) {
                packageSet.add(className.substring(0, className.length() - 2));
                name = "*";
            } else {
                Class<?> clz = Class.forName(className);
                if (name == null) {
                    name = clz.getSimpleName();
                }
                classMap.put(name, clz);
                nameMap.put(clz, name);
            }
        }
        return name;
    }

    public static <T> T invokeInstanceMethod(Object receiver, String methodName, Object... args) throws Throwable {
        try {
            return (T) onInvokeInstanceMethod(receiver, methodName, args);
        } catch (Throwable t) {
            if (t instanceof NoSuchMethodException) {
                throw t;
            }
            throw new Throwable("className: " + receiver.getClass() + ", methodName: " + methodName, t);
        }
    }

    public static <T> T invokeStaticMethod(String className, String methodName, Object... args) throws Throwable {
        try {
            return (T) onInvokeStaticMethod(className, methodName, args);
        } catch (Throwable t) {
            if (t instanceof NoSuchMethodException) {
                throw t;
            }
            throw new Throwable("className: " + className + ", methodName: " + methodName, t);
        }
    }

    private static boolean matchParams(Class<?>[] mTypes, Class<?>[] types) {
        if (mTypes.length != types.length) {
            return false;
        }
        for (int i = 0; i < mTypes.length; i++) {
            if (!(types[i] == null || primitiveEquals(mTypes[i], types[i]) || mTypes[i].isAssignableFrom(types[i]))) {
                return false;
            }
        }
        return true;
    }

    private static Object newArray(String className, Object... args) throws Throwable {
        String tmp = className;
        int dimension = 0;
        while (tmp.startsWith("[")) {
            dimension++;
            tmp = tmp.substring(1);
        }
        int[] lens = null;
        if (dimension == args.length) {
            lens = new int[dimension];
            for (int i = 0; i < dimension; i++) {
                try {
                    lens[i] = Integer.parseInt(String.valueOf(args[i]));
                } catch (Throwable th) {
                    lens = null;
                }
            }
        }
        if (lens != null) {
            Class<?> eleClz = "B".equals(tmp) ? Byte.TYPE : "S".equals(tmp) ? Short.TYPE : "I".equals(tmp) ? Integer.TYPE : "J".equals(tmp) ? Long.TYPE : "F".equals(tmp) ? Float.TYPE : "D".equals(tmp) ? Double.TYPE : "Z".equals(tmp) ? Boolean.TYPE : "C".equals(tmp) ? Character.TYPE : getImportedClass(tmp);
            if (eleClz != null) {
                return Array.newInstance(eleClz, lens);
            }
        }
        throw new NoSuchMethodException("className: [" + className + ", methodName: <init>");
    }

    public static Object newInstance(String className, Object... args) throws Throwable {
        try {
            return onNewInstance(className, args);
        } catch (Throwable t) {
            if (t instanceof NoSuchMethodException) {
                throw t;
            }
            throw new Throwable("className: " + className + ", methodName: <init>", t);
        }
    }

    private static Object onGetElement(Object receiver, String fieldName) throws Throwable {
        if ("length".equals(fieldName)) {
            return Integer.valueOf(Array.getLength(receiver));
        }
        if (fieldName.startsWith("[") && fieldName.endsWith("]")) {
            int index = -1;
            try {
                index = Integer.parseInt(fieldName.substring(1, fieldName.length() - 1));
            } catch (Throwable th) {
            }
            if (index != -1) {
                return Array.get(receiver, index);
            }
        }
        throw new NoSuchFieldException("className: " + receiver.getClass() + ", fieldName: " + fieldName);
    }

    private static <T> T onGetInstanceField(Object receiver, String fieldName) throws Throwable {
        if (receiver.getClass().isArray()) {
            return (T) onGetElement(receiver, fieldName);
        }
        ArrayList<Class<?>> clzs = new ArrayList<>();
        for (Class<?> clz = receiver.getClass(); clz != null; clz = clz.getSuperclass()) {
            clzs.add(clz);
        }
        Iterator<Class<?>> it = clzs.iterator();
        while (it.hasNext()) {
            Field fld = null;
            try {
                fld = it.next().getDeclaredField(fieldName);
            } catch (Throwable th) {
            }
            if (!(fld == null || Modifier.isStatic(fld.getModifiers()))) {
                fld.setAccessible(true);
                return (T) fld.get(receiver);
            }
        }
        throw new NoSuchFieldException("className: " + receiver.getClass() + ", fieldName: " + fieldName);
    }

    private static <T> T onGetStaticField(String className, String fieldName) throws Throwable {
        ArrayList<Class<?>> clzs = new ArrayList<>();
        for (Class<?> clz = getImportedClass(className); clz != null; clz = clz.getSuperclass()) {
            clzs.add(clz);
        }
        Iterator<Class<?>> it = clzs.iterator();
        while (it.hasNext()) {
            Field fld = null;
            try {
                fld = it.next().getDeclaredField(fieldName);
            } catch (Throwable th) {
            }
            if (fld != null && Modifier.isStatic(fld.getModifiers())) {
                fld.setAccessible(true);
                return (T) fld.get(null);
            }
        }
        throw new NoSuchFieldException("className: " + className + ", fieldName: " + fieldName);
    }

    private static <T> T onInvokeInstanceMethod(Object receiver, String methodName, Object... args) throws Throwable {
        Class<?> clz = receiver.getClass();
        String mthSign = clz.getName() + "#" + methodName + "#" + args.length;
        Method mth = cachedMethod.get(mthSign);
        Class<?>[] types = getTypes(args);
        if (mth == null || Modifier.isStatic(mth.getModifiers()) || !matchParams(mth.getParameterTypes(), types)) {
            ArrayList<Class<?>> clzs = new ArrayList<>();
            while (clz != null) {
                clzs.add(clz);
                clz = clz.getSuperclass();
            }
            Iterator<Class<?>> it = clzs.iterator();
            while (it.hasNext()) {
                Method[] mths = it.next().getDeclaredMethods();
                for (Method m : mths) {
                    if (m.getName().equals(methodName) && !Modifier.isStatic(m.getModifiers()) && matchParams(m.getParameterTypes(), types)) {
                        cachedMethod.put(mthSign, m);
                        m.setAccessible(true);
                        if (m.getReturnType() != Void.TYPE) {
                            return (T) m.invoke(receiver, args);
                        }
                        m.invoke(receiver, args);
                        return null;
                    }
                }
            }
            throw new NoSuchMethodException("className: " + receiver.getClass() + ", methodName: " + methodName);
        }
        mth.setAccessible(true);
        if (mth.getReturnType() != Void.TYPE) {
            return (T) mth.invoke(receiver, args);
        }
        mth.invoke(receiver, args);
        return null;
    }

    private static <T> T onInvokeStaticMethod(String className, String methodName, Object... args) throws Throwable {
        String mthSign = className + "#" + methodName + "#" + args.length;
        Method mth = cachedMethod.get(mthSign);
        Class<?>[] types = getTypes(args);
        if (mth == null || !Modifier.isStatic(mth.getModifiers()) || !matchParams(mth.getParameterTypes(), types)) {
            ArrayList<Class<?>> clzs = new ArrayList<>();
            for (Class<?> clz = getImportedClass(className); clz != null; clz = clz.getSuperclass()) {
                clzs.add(clz);
            }
            Iterator<Class<?>> it = clzs.iterator();
            while (it.hasNext()) {
                Method[] mths = it.next().getDeclaredMethods();
                for (Method m : mths) {
                    if (m.getName().equals(methodName) && Modifier.isStatic(m.getModifiers()) && matchParams(m.getParameterTypes(), types)) {
                        cachedMethod.put(mthSign, m);
                        m.setAccessible(true);
                        if (m.getReturnType() != Void.TYPE) {
                            return (T) m.invoke(null, args);
                        }
                        m.invoke(null, args);
                        return null;
                    }
                }
            }
            throw new NoSuchMethodException("className: " + className + ", methodName: " + methodName);
        }
        mth.setAccessible(true);
        if (mth.getReturnType() != Void.TYPE) {
            return (T) mth.invoke(null, args);
        }
        mth.invoke(null, args);
        return null;
    }

    private static Object onNewInstance(String className, Object... args) throws Throwable {
        if (className.startsWith("[")) {
            return newArray(className, args);
        }
        String mthSign = className + "#" + args.length;
        Constructor<?> con = cachedConstr.get(mthSign);
        Class<?>[] types = getTypes(args);
        if (con == null || !matchParams(con.getParameterTypes(), types)) {
            Constructor<?>[] cons = getImportedClass(className).getDeclaredConstructors();
            for (Constructor<?> c : cons) {
                if (matchParams(c.getParameterTypes(), types)) {
                    cachedConstr.put(mthSign, c);
                    c.setAccessible(true);
                    return c.newInstance(args);
                }
            }
            throw new NoSuchMethodException("className: " + className + ", methodName: <init>");
        }
        con.setAccessible(true);
        return con.newInstance(args);
    }

    private static void onSetElement(Object receiver, String fieldName, Object value) throws Throwable {
        if (fieldName.startsWith("[") && fieldName.endsWith("]")) {
            int index = -1;
            try {
                index = Integer.parseInt(fieldName.substring(1, fieldName.length() - 1));
            } catch (Throwable th) {
            }
            if (index != -1) {
                String recClzName = receiver.getClass().getName();
                while (recClzName.startsWith("[")) {
                    recClzName = recClzName.substring(1);
                }
                Class<?> vClass = value.getClass();
                if ("B".equals(recClzName)) {
                    if (vClass == Byte.class) {
                        Array.set(receiver, index, value);
                        return;
                    }
                } else if ("S".equals(recClzName)) {
                    Object sValue = null;
                    if (vClass == Short.class) {
                        sValue = value;
                    } else if (vClass == Byte.class) {
                        sValue = Short.valueOf(((Byte) value).byteValue());
                    }
                    if (sValue != null) {
                        Array.set(receiver, index, sValue);
                        return;
                    }
                } else if ("I".equals(recClzName)) {
                    Object iValue = null;
                    if (vClass == Integer.class) {
                        iValue = value;
                    } else if (vClass == Short.class) {
                        iValue = Integer.valueOf(((Short) value).shortValue());
                    } else if (vClass == Byte.class) {
                        iValue = Integer.valueOf(((Byte) value).byteValue());
                    }
                    if (iValue != null) {
                        Array.set(receiver, index, iValue);
                        return;
                    }
                } else if ("J".equals(recClzName)) {
                    Object jValue = null;
                    if (vClass == Long.class) {
                        jValue = value;
                    } else if (vClass == Integer.class) {
                        jValue = Long.valueOf(((Integer) value).intValue());
                    } else if (vClass == Short.class) {
                        jValue = Long.valueOf(((Short) value).shortValue());
                    } else if (vClass == Byte.class) {
                        jValue = Long.valueOf(((Byte) value).byteValue());
                    }
                    if (jValue != null) {
                        Array.set(receiver, index, jValue);
                        return;
                    }
                } else if ("F".equals(recClzName)) {
                    Object fValue = null;
                    if (vClass == Float.class) {
                        fValue = value;
                    } else if (vClass == Long.class) {
                        fValue = Float.valueOf((float) ((Long) value).longValue());
                    } else if (vClass == Integer.class) {
                        fValue = Float.valueOf(((Integer) value).intValue());
                    } else if (vClass == Short.class) {
                        fValue = Float.valueOf(((Short) value).shortValue());
                    } else if (vClass == Byte.class) {
                        fValue = Float.valueOf(((Byte) value).byteValue());
                    }
                    if (fValue != null) {
                        Array.set(receiver, index, fValue);
                        return;
                    }
                } else if ("D".equals(recClzName)) {
                    Object dValue = null;
                    if (vClass == Double.class) {
                        dValue = value;
                    } else if (vClass == Float.class) {
                        dValue = Double.valueOf(((Float) value).floatValue());
                    } else if (vClass == Long.class) {
                        dValue = Double.valueOf(((Long) value).longValue());
                    } else if (vClass == Integer.class) {
                        dValue = Double.valueOf(((Integer) value).intValue());
                    } else if (vClass == Short.class) {
                        dValue = Double.valueOf(((Short) value).shortValue());
                    } else if (vClass == Byte.class) {
                        dValue = Double.valueOf(((Byte) value).byteValue());
                    }
                    if (dValue != null) {
                        Array.set(receiver, index, dValue);
                        return;
                    }
                } else if ("Z".equals(recClzName)) {
                    if (vClass == Boolean.class) {
                        Array.set(receiver, index, value);
                        return;
                    }
                } else if ("C".equals(recClzName)) {
                    if (vClass == Character.class) {
                        Array.set(receiver, index, value);
                        return;
                    }
                } else if (recClzName.equals(vClass.getName())) {
                    Array.set(receiver, index, value);
                    return;
                }
            }
        }
        throw new NoSuchFieldException("className: " + receiver.getClass() + ", fieldName: " + fieldName + ", value: " + String.valueOf(value));
    }

    private static void onSetInstanceField(Object receiver, String fieldName, Object value) throws Throwable {
        if (receiver.getClass().isArray()) {
            onSetElement(receiver, fieldName, value);
            return;
        }
        ArrayList<Class<?>> clzs = new ArrayList<>();
        for (Class<?> clz = receiver.getClass(); clz != null; clz = clz.getSuperclass()) {
            clzs.add(clz);
        }
        Iterator<Class<?>> it = clzs.iterator();
        while (it.hasNext()) {
            Field fld = null;
            try {
                fld = it.next().getDeclaredField(fieldName);
            } catch (Throwable th) {
            }
            if (!(fld == null || Modifier.isStatic(fld.getModifiers()))) {
                fld.setAccessible(true);
                fld.set(receiver, value);
                return;
            }
        }
        throw new NoSuchFieldException("className: " + receiver.getClass() + ", fieldName: " + fieldName + ", value: " + String.valueOf(value));
    }

    private static void onSetStaticField(String className, String fieldName, Object value) throws Throwable {
        ArrayList<Class<?>> clzs = new ArrayList<>();
        for (Class<?> clz = getImportedClass(className); clz != null; clz = clz.getSuperclass()) {
            clzs.add(clz);
        }
        Iterator<Class<?>> it = clzs.iterator();
        while (it.hasNext()) {
            Field fld = null;
            try {
                fld = it.next().getDeclaredField(fieldName);
            } catch (Throwable th) {
            }
            if (fld != null && Modifier.isStatic(fld.getModifiers())) {
                fld.setAccessible(true);
                fld.set(null, value);
                return;
            }
        }
        throw new NoSuchFieldException("className: " + className + ", fieldName: " + fieldName + ", value: " + String.valueOf(value));
    }

    private static boolean primitiveEquals(Class<?> primitive, Class<?> target) {
        return (primitive == Byte.TYPE && target == Byte.class) || (primitive == Short.TYPE && (target == Short.class || target == Byte.class || target == Character.class)) || ((primitive == Character.TYPE && (target == Character.class || target == Short.class || target == Byte.class)) || ((primitive == Integer.TYPE && (target == Integer.class || target == Short.class || target == Byte.class || target == Character.class)) || ((primitive == Long.TYPE && (target == Long.class || target == Integer.class || target == Short.class || target == Byte.class || target == Character.class)) || ((primitive == Float.TYPE && (target == Float.class || target == Long.class || target == Integer.class || target == Short.class || target == Byte.class || target == Character.class)) || ((primitive == Double.TYPE && (target == Double.class || target == Float.class || target == Long.class || target == Integer.class || target == Short.class || target == Byte.class || target == Character.class)) || (primitive == Boolean.TYPE && target == Boolean.class))))));
    }

    public static void setInstanceField(Object receiver, String fieldName, Object value) throws Throwable {
        try {
            onSetInstanceField(receiver, fieldName, value);
        } catch (Throwable t) {
            if (t instanceof NoSuchFieldException) {
                throw t;
            }
            throw new Throwable("className: " + receiver.getClass() + ", fieldName: " + fieldName + ", value: " + String.valueOf(value), t);
        }
    }

    public static void setStaticField(String className, String fieldName, Object value) throws Throwable {
        try {
            onSetStaticField(className, fieldName, value);
        } catch (Throwable t) {
            if (t instanceof NoSuchFieldException) {
                throw t;
            }
            throw new Throwable("className: " + className + ", fieldName: " + fieldName + ", value: " + String.valueOf(value), t);
        }
    }
}

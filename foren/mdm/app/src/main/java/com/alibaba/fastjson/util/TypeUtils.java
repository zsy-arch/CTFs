package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.alipay.sdk.util.h;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.AccessControlException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes.dex */
public class TypeUtils {
    public static boolean compatibleWithJavaBean = false;
    private static boolean setAccessibleEnable = true;
    private static ConcurrentMap<String, Class<?>> mappings = new ConcurrentHashMap();

    static {
        addBaseClassMappings();
    }

    public static final String castToString(Object value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    public static final Byte castToByte(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Byte.valueOf(((Number) value).byteValue());
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0 || f.b.equals(strVal)) {
                return null;
            }
            return Byte.valueOf(Byte.parseByte(strVal));
        }
        throw new JSONException("can not cast to byte, value : " + value);
    }

    public static final Character castToChar(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Character) {
            return (Character) value;
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0) {
                return null;
            }
            if (strVal.length() == 1) {
                return Character.valueOf(strVal.charAt(0));
            }
            throw new JSONException("can not cast to byte, value : " + value);
        }
        throw new JSONException("can not cast to byte, value : " + value);
    }

    public static final Short castToShort(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Short.valueOf(((Number) value).shortValue());
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0 || f.b.equals(strVal)) {
                return null;
            }
            return Short.valueOf(Short.parseShort(strVal));
        }
        throw new JSONException("can not cast to short, value : " + value);
    }

    public static final BigDecimal castToBigDecimal(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        if (value instanceof BigInteger) {
            return new BigDecimal((BigInteger) value);
        }
        String strVal = value.toString();
        if (strVal.length() == 0) {
            return null;
        }
        return new BigDecimal(strVal);
    }

    public static final BigInteger castToBigInteger(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigInteger) {
            return (BigInteger) value;
        }
        if ((value instanceof Float) || (value instanceof Double)) {
            return BigInteger.valueOf(((Number) value).longValue());
        }
        String strVal = value.toString();
        if (strVal.length() == 0) {
            return null;
        }
        return new BigInteger(strVal);
    }

    public static final Float castToFloat(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Float.valueOf(((Number) value).floatValue());
        }
        if (value instanceof String) {
            String strVal = value.toString();
            if (strVal.length() == 0 || f.b.equals(strVal)) {
                return null;
            }
            return Float.valueOf(Float.parseFloat(strVal));
        }
        throw new JSONException("can not cast to float, value : " + value);
    }

    public static final Double castToDouble(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Double.valueOf(((Number) value).doubleValue());
        }
        if (value instanceof String) {
            String strVal = value.toString();
            if (strVal.length() == 0 || f.b.equals(strVal)) {
                return null;
            }
            return Double.valueOf(Double.parseDouble(strVal));
        }
        throw new JSONException("can not cast to double, value : " + value);
    }

    public static final Date castToDate(Object value) {
        String format;
        if (value == null) {
            return null;
        }
        if (value instanceof Calendar) {
            return ((Calendar) value).getTime();
        }
        if (value instanceof Date) {
            return (Date) value;
        }
        long longValue = -1;
        if (value instanceof Number) {
            longValue = ((Number) value).longValue();
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.indexOf(45) != -1) {
                if (strVal.length() == JSON.DEFFAULT_DATE_FORMAT.length()) {
                    format = JSON.DEFFAULT_DATE_FORMAT;
                } else if (strVal.length() == 10) {
                    format = "yyyy-MM-dd";
                } else if (strVal.length() == "yyyy-MM-dd HH:mm:ss".length()) {
                    format = "yyyy-MM-dd HH:mm:ss";
                } else {
                    format = "yyyy-MM-dd HH:mm:ss.SSS";
                }
                try {
                    return new SimpleDateFormat(format).parse(strVal);
                } catch (ParseException e) {
                    throw new JSONException("can not cast to Date, value : " + strVal);
                }
            } else if (strVal.length() == 0) {
                return null;
            } else {
                longValue = Long.parseLong(strVal);
            }
        }
        if (longValue >= 0) {
            return new Date(longValue);
        }
        throw new JSONException("can not cast to Date, value : " + value);
    }

    public static final java.sql.Date castToSqlDate(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Calendar) {
            return new java.sql.Date(((Calendar) value).getTimeInMillis());
        }
        if (value instanceof java.sql.Date) {
            return (java.sql.Date) value;
        }
        if (value instanceof Date) {
            return new java.sql.Date(((Date) value).getTime());
        }
        long longValue = 0;
        if (value instanceof Number) {
            longValue = ((Number) value).longValue();
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0) {
                return null;
            }
            longValue = Long.parseLong(strVal);
        }
        if (longValue > 0) {
            return new java.sql.Date(longValue);
        }
        throw new JSONException("can not cast to Date, value : " + value);
    }

    public static final Timestamp castToTimestamp(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Calendar) {
            return new Timestamp(((Calendar) value).getTimeInMillis());
        }
        if (value instanceof Timestamp) {
            return (Timestamp) value;
        }
        if (value instanceof Date) {
            return new Timestamp(((Date) value).getTime());
        }
        long longValue = 0;
        if (value instanceof Number) {
            longValue = ((Number) value).longValue();
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0) {
                return null;
            }
            longValue = Long.parseLong(strVal);
        }
        if (longValue > 0) {
            return new Timestamp(longValue);
        }
        throw new JSONException("can not cast to Date, value : " + value);
    }

    public static final Long castToLong(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Long.valueOf(((Number) value).longValue());
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0 || f.b.equals(strVal)) {
                return null;
            }
            try {
                return Long.valueOf(Long.parseLong(strVal));
            } catch (NumberFormatException e) {
                JSONScanner dateParser = new JSONScanner(strVal);
                Calendar calendar = null;
                if (dateParser.scanISO8601DateIfMatch(false)) {
                    calendar = dateParser.getCalendar();
                }
                dateParser.close();
                if (calendar != null) {
                    return Long.valueOf(calendar.getTimeInMillis());
                }
            }
        }
        throw new JSONException("can not cast to long, value : " + value);
    }

    public static final Integer castToInt(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Integer) {
            return (Integer) value;
        }
        if (value instanceof Number) {
            return Integer.valueOf(((Number) value).intValue());
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() != 0 && !f.b.equals(strVal)) {
                return Integer.valueOf(Integer.parseInt(strVal));
            }
            return null;
        }
        throw new JSONException("can not cast to int, value : " + value);
    }

    public static final byte[] castToBytes(Object value) {
        if (value instanceof byte[]) {
            return (byte[]) value;
        }
        if (value instanceof String) {
            return Base64.decodeFast((String) value);
        }
        throw new JSONException("can not cast to int, value : " + value);
    }

    public static final Boolean castToBoolean(Object value) {
        boolean z = true;
        if (value == null) {
            return null;
        }
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        if (value instanceof Number) {
            if (((Number) value).intValue() != 1) {
                z = false;
            }
            return Boolean.valueOf(z);
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0) {
                return null;
            }
            if ("true".equalsIgnoreCase(strVal)) {
                return Boolean.TRUE;
            }
            if ("false".equalsIgnoreCase(strVal)) {
                return Boolean.FALSE;
            }
            if ("1".equals(strVal)) {
                return Boolean.TRUE;
            }
            if ("0".equals(strVal)) {
                return Boolean.FALSE;
            }
            if (f.b.equals(strVal)) {
                return null;
            }
        }
        throw new JSONException("can not cast to int, value : " + value);
    }

    public static final <T> T castToJavaBean(Object obj, Class<T> clazz) {
        return (T) cast(obj, (Class<Object>) clazz, ParserConfig.getGlobalInstance());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> T cast(Object obj, Class<T> clazz, ParserConfig mapping) {
        T t;
        if (obj == 0) {
            return null;
        }
        if (clazz == null) {
            throw new IllegalArgumentException("clazz is null");
        } else if (clazz == obj.getClass()) {
            return obj;
        } else {
            if (!(obj instanceof Map)) {
                if (clazz.isArray()) {
                    if (obj instanceof Collection) {
                        Collection<Object> collection = (Collection) obj;
                        int index = 0;
                        T t2 = (T) Array.newInstance(clazz.getComponentType(), collection.size());
                        for (Object item : collection) {
                            Array.set(t2, index, cast(item, (Class<Object>) clazz.getComponentType(), mapping));
                            index++;
                        }
                        return t2;
                    } else if (clazz == byte[].class) {
                        return (T) castToBytes(obj);
                    }
                }
                if (clazz.isAssignableFrom(obj.getClass())) {
                    return obj;
                }
                if (clazz == Boolean.TYPE || clazz == Boolean.class) {
                    return (T) castToBoolean(obj);
                }
                if (clazz == Byte.TYPE || clazz == Byte.class) {
                    return (T) castToByte(obj);
                }
                if (clazz == Short.TYPE || clazz == Short.class) {
                    return (T) castToShort(obj);
                }
                if (clazz == Integer.TYPE || clazz == Integer.class) {
                    return (T) castToInt(obj);
                }
                if (clazz == Long.TYPE || clazz == Long.class) {
                    return (T) castToLong(obj);
                }
                if (clazz == Float.TYPE || clazz == Float.class) {
                    return (T) castToFloat(obj);
                }
                if (clazz == Double.TYPE || clazz == Double.class) {
                    return (T) castToDouble(obj);
                }
                if (clazz == String.class) {
                    return (T) castToString(obj);
                }
                if (clazz == BigDecimal.class) {
                    return (T) castToBigDecimal(obj);
                }
                if (clazz == BigInteger.class) {
                    return (T) castToBigInteger(obj);
                }
                if (clazz == Date.class) {
                    return (T) castToDate(obj);
                }
                if (clazz == java.sql.Date.class) {
                    return (T) castToSqlDate(obj);
                }
                if (clazz == Timestamp.class) {
                    return (T) castToTimestamp(obj);
                }
                if (clazz.isEnum()) {
                    return (T) castToEnum(obj, clazz, mapping);
                }
                if (Calendar.class.isAssignableFrom(clazz)) {
                    Date date = castToDate(obj);
                    if (clazz == Calendar.class) {
                        t = (T) Calendar.getInstance();
                    } else {
                        try {
                            t = (T) ((Calendar) clazz.newInstance());
                        } catch (Exception e) {
                            throw new JSONException("can not cast to : " + clazz.getName(), e);
                        }
                    }
                    ((Calendar) t).setTime(date);
                    return t;
                } else if ((obj instanceof String) && ((String) obj).length() == 0) {
                    return null;
                } else {
                    throw new JSONException("can not cast to : " + clazz.getName());
                }
            } else if (clazz == Map.class) {
                return obj;
            } else {
                Map map = (Map) obj;
                if (clazz != Object.class || map.containsKey(JSON.DEFAULT_TYPE_KEY)) {
                    return (T) castToJavaBean((Map) obj, clazz, mapping);
                }
                return obj;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Enum, T] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> T castToEnum(java.lang.Object r14, java.lang.Class<T> r15, com.alibaba.fastjson.parser.ParserConfig r16) {
        /*
            r2 = 0
            boolean r11 = r14 instanceof java.lang.String     // Catch: Exception -> 0x004a
            if (r11 == 0) goto L_0x0015
            r0 = r14
            java.lang.String r0 = (java.lang.String) r0     // Catch: Exception -> 0x004a
            r7 = r0
            int r11 = r7.length()     // Catch: Exception -> 0x004a
            if (r11 != 0) goto L_0x0010
        L_0x000f:
            return r2
        L_0x0010:
            java.lang.Enum r2 = java.lang.Enum.valueOf(r15, r7)     // Catch: Exception -> 0x004a
            goto L_0x000f
        L_0x0015:
            boolean r11 = r14 instanceof java.lang.Number     // Catch: Exception -> 0x004a
            if (r11 == 0) goto L_0x0068
            java.lang.Number r14 = (java.lang.Number) r14     // Catch: Exception -> 0x004a
            int r8 = r14.intValue()     // Catch: Exception -> 0x004a
            java.lang.String r11 = "values"
            r12 = 0
            java.lang.Class[] r12 = new java.lang.Class[r12]     // Catch: Exception -> 0x004a
            java.lang.reflect.Method r6 = r15.getMethod(r11, r12)     // Catch: Exception -> 0x004a
            r11 = 0
            r12 = 0
            java.lang.Object[] r12 = new java.lang.Object[r12]     // Catch: Exception -> 0x004a
            java.lang.Object r11 = r6.invoke(r11, r12)     // Catch: Exception -> 0x004a
            java.lang.Object[] r11 = (java.lang.Object[]) r11     // Catch: Exception -> 0x004a
            r0 = r11
            java.lang.Object[] r0 = (java.lang.Object[]) r0     // Catch: Exception -> 0x004a
            r10 = r0
            r1 = r10
            int r5 = r1.length     // Catch: Exception -> 0x004a
            r4 = 0
        L_0x0039:
            if (r4 >= r5) goto L_0x0068
            r9 = r1[r4]     // Catch: Exception -> 0x004a
            r0 = r9
            java.lang.Enum r0 = (java.lang.Enum) r0     // Catch: Exception -> 0x004a
            r2 = r0
            int r11 = r2.ordinal()     // Catch: Exception -> 0x004a
            if (r11 == r8) goto L_0x000f
            int r4 = r4 + 1
            goto L_0x0039
        L_0x004a:
            r3 = move-exception
            com.alibaba.fastjson.JSONException r11 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "can not cast to : "
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r13 = r15.getName()
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r12 = r12.toString()
            r11.<init>(r12, r3)
            throw r11
        L_0x0068:
            com.alibaba.fastjson.JSONException r11 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "can not cast to : "
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r13 = r15.getName()
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.TypeUtils.castToEnum(java.lang.Object, java.lang.Class, com.alibaba.fastjson.parser.ParserConfig):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> T cast(Object obj, Type type, ParserConfig mapping) {
        if (obj == 0) {
            return null;
        }
        if (type instanceof Class) {
            return (T) cast(obj, (Class<Object>) type, mapping);
        }
        if (type instanceof ParameterizedType) {
            return (T) cast(obj, (ParameterizedType) type, mapping);
        }
        if ((obj instanceof String) && ((String) obj).length() == 0) {
            return null;
        }
        if (type instanceof TypeVariable) {
            return obj;
        }
        throw new JSONException("can not cast to : " + type);
    }

    /* JADX WARN: Type inference failed for: r11v0, types: [T, java.util.Map, java.util.HashMap] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> T cast(java.lang.Object r19, java.lang.reflect.ParameterizedType r20, com.alibaba.fastjson.parser.ParserConfig r21) {
        /*
            Method dump skipped, instructions count: 295
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.TypeUtils.cast(java.lang.Object, java.lang.reflect.ParameterizedType, com.alibaba.fastjson.parser.ParserConfig):java.lang.Object");
    }

    public static final <T> T castToJavaBean(Map<String, Object> map, Class<T> clazz, ParserConfig mapping) {
        JSONObject object;
        int lineNumber;
        try {
            if (clazz == StackTraceElement.class) {
                String declaringClass = (String) map.get("className");
                String methodName = (String) map.get("methodName");
                String fileName = (String) map.get("fileName");
                Number value = (Number) map.get("lineNumber");
                if (value == null) {
                    lineNumber = 0;
                } else {
                    lineNumber = value.intValue();
                }
                return (T) new StackTraceElement(declaringClass, methodName, fileName, lineNumber);
            }
            Object iClassObject = map.get(JSON.DEFAULT_TYPE_KEY);
            if (iClassObject instanceof String) {
                String className = (String) iClassObject;
                Class<?> loadClazz = loadClass(className);
                if (loadClazz == null) {
                    throw new ClassNotFoundException(className + " not found");
                } else if (!loadClazz.equals(clazz)) {
                    return (T) castToJavaBean(map, loadClazz, mapping);
                }
            }
            if (clazz.isInterface()) {
                if (map instanceof JSONObject) {
                    object = (JSONObject) map;
                } else {
                    object = new JSONObject(map);
                }
                return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{clazz}, object);
            }
            if (mapping == null) {
                mapping = ParserConfig.getGlobalInstance();
            }
            Map<String, FieldDeserializer> setters = mapping.getFieldDeserializers(clazz);
            Constructor<T> constructor = clazz.getDeclaredConstructor(new Class[0]);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            T object2 = constructor.newInstance(new Object[0]);
            for (Map.Entry<String, FieldDeserializer> entry : setters.entrySet()) {
                String key = entry.getKey();
                FieldDeserializer fieldDeser = entry.getValue();
                if (map.containsKey(key)) {
                    Object value2 = map.get(key);
                    Method method = fieldDeser.getMethod();
                    if (method != null) {
                        method.invoke(object2, cast(value2, method.getGenericParameterTypes()[0], mapping));
                    } else {
                        Field field = fieldDeser.getField();
                        field.set(object2, cast(value2, field.getGenericType(), mapping));
                    }
                }
            }
            return object2;
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public static void addClassMapping(String className, Class<?> clazz) {
        if (className == null) {
            className = clazz.getName();
        }
        mappings.put(className, clazz);
    }

    public static void addBaseClassMappings() {
        mappings.put("byte", Byte.TYPE);
        mappings.put("short", Short.TYPE);
        mappings.put("int", Integer.TYPE);
        mappings.put("long", Long.TYPE);
        mappings.put("float", Float.TYPE);
        mappings.put("double", Double.TYPE);
        mappings.put("boolean", Boolean.TYPE);
        mappings.put("char", Character.TYPE);
        mappings.put("[byte", byte[].class);
        mappings.put("[short", short[].class);
        mappings.put("[int", int[].class);
        mappings.put("[long", long[].class);
        mappings.put("[float", float[].class);
        mappings.put("[double", double[].class);
        mappings.put("[boolean", boolean[].class);
        mappings.put("[char", char[].class);
        mappings.put(HashMap.class.getName(), HashMap.class);
    }

    public static void clearClassMapping() {
        mappings.clear();
        addBaseClassMappings();
    }

    public static Class<?> loadClass(String className) {
        if (className == null || className.length() == 0) {
            return null;
        }
        Class<?> clazz = mappings.get(className);
        if (clazz != null) {
            return clazz;
        }
        if (className.charAt(0) == '[') {
            return Array.newInstance(loadClass(className.substring(1)), 0).getClass();
        }
        if (className.startsWith("L") && className.endsWith(h.b)) {
            return loadClass(className.substring(1, className.length() - 1));
        }
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            if (classLoader != null) {
                clazz = classLoader.loadClass(className);
                addClassMapping(className, clazz);
                return clazz;
            }
        } catch (Throwable th) {
        }
        try {
            clazz = Class.forName(className);
            addClassMapping(className, clazz);
            return clazz;
        } catch (Throwable th2) {
            return clazz;
        }
    }

    public static List<FieldInfo> computeGetters(Class<?> clazz, Map<String, String> aliasMap) {
        return computeGetters(clazz, aliasMap, true);
    }

    /* JADX INFO: Multiple debug info for r15v1 java.lang.reflect.Field[]: [D('arr$' java.lang.reflect.Method[]), D('arr$' java.lang.reflect.Field[])] */
    /* JADX INFO: Multiple debug info for r23v4 java.util.Iterator<com.alibaba.fastjson.util.FieldInfo>: [D('i$' int), D('i$' java.util.Iterator)] */
    public static List<FieldInfo> computeGetters(Class<?> clazz, Map<String, String> aliasMap, boolean sorted) {
        String propertyName;
        JSONField fieldAnnotation;
        String propertyName2;
        JSONField fieldAnnotation2;
        Map<String, FieldInfo> fieldInfoMap = new LinkedHashMap<>();
        Method[] arr$ = clazz.getMethods();
        for (Method method : arr$) {
            String methodName = method.getName();
            int ordinal = 0;
            int serialzeFeatures = 0;
            if (!Modifier.isStatic(method.getModifiers()) && !method.getReturnType().equals(Void.TYPE) && method.getParameterTypes().length == 0 && method.getReturnType() != ClassLoader.class && (!method.getName().equals("getMetaClass") || !method.getReturnType().getName().equals("groovy.lang.MetaClass"))) {
                JSONField annotation = (JSONField) method.getAnnotation(JSONField.class);
                if (annotation == null) {
                    annotation = getSupperMethodAnnotation(clazz, method);
                }
                if (annotation != null) {
                    if (annotation.serialize()) {
                        ordinal = annotation.ordinal();
                        serialzeFeatures = SerializerFeature.of(annotation.serialzeFeatures());
                        if (annotation.name().length() != 0) {
                            String propertyName3 = annotation.name();
                            if (aliasMap == null || (propertyName3 = aliasMap.get(propertyName3)) != null) {
                                fieldInfoMap.put(propertyName3, new FieldInfo(propertyName3, method, (Field) null, ordinal, serialzeFeatures));
                            }
                        }
                    }
                }
                if (methodName.startsWith("get")) {
                    if (methodName.length() >= 4 && !methodName.equals("getClass")) {
                        char c3 = methodName.charAt(3);
                        if (Character.isUpperCase(c3)) {
                            if (compatibleWithJavaBean) {
                                propertyName2 = decapitalize(methodName.substring(3));
                            } else {
                                propertyName2 = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
                            }
                        } else if (c3 == '_') {
                            propertyName2 = methodName.substring(4);
                        } else if (c3 == 'f') {
                            propertyName2 = methodName.substring(3);
                        } else if (methodName.length() >= 5 && Character.isUpperCase(methodName.charAt(4))) {
                            propertyName2 = decapitalize(methodName.substring(3));
                        }
                        if (!isJSONTypeIgnore(clazz, propertyName2)) {
                            Field field = ParserConfig.getField(clazz, propertyName2);
                            if (!(field == null || (fieldAnnotation2 = (JSONField) field.getAnnotation(JSONField.class)) == null)) {
                                if (fieldAnnotation2.serialize()) {
                                    ordinal = fieldAnnotation2.ordinal();
                                    serialzeFeatures = SerializerFeature.of(fieldAnnotation2.serialzeFeatures());
                                    if (fieldAnnotation2.name().length() != 0) {
                                        propertyName2 = fieldAnnotation2.name();
                                        if (aliasMap != null && (propertyName2 = aliasMap.get(propertyName2)) == null) {
                                        }
                                    }
                                }
                            }
                            if (aliasMap == null || (propertyName2 = aliasMap.get(propertyName2)) != null) {
                                fieldInfoMap.put(propertyName2, new FieldInfo(propertyName2, method, field, ordinal, serialzeFeatures));
                            }
                        }
                    }
                }
                if (methodName.startsWith("is") && methodName.length() >= 3) {
                    char c2 = methodName.charAt(2);
                    if (Character.isUpperCase(c2)) {
                        if (compatibleWithJavaBean) {
                            propertyName = decapitalize(methodName.substring(2));
                        } else {
                            propertyName = Character.toLowerCase(methodName.charAt(2)) + methodName.substring(3);
                        }
                    } else if (c2 == '_') {
                        propertyName = methodName.substring(3);
                    } else if (c2 == 'f') {
                        propertyName = methodName.substring(2);
                    }
                    Field field2 = ParserConfig.getField(clazz, propertyName);
                    if (field2 == null) {
                        field2 = ParserConfig.getField(clazz, methodName);
                    }
                    if (!(field2 == null || (fieldAnnotation = (JSONField) field2.getAnnotation(JSONField.class)) == null)) {
                        if (fieldAnnotation.serialize()) {
                            ordinal = fieldAnnotation.ordinal();
                            serialzeFeatures = SerializerFeature.of(fieldAnnotation.serialzeFeatures());
                            if (fieldAnnotation.name().length() != 0) {
                                propertyName = fieldAnnotation.name();
                                if (aliasMap != null && (propertyName = aliasMap.get(propertyName)) == null) {
                                }
                            }
                        }
                    }
                    if (aliasMap == null || (propertyName = aliasMap.get(propertyName)) != null) {
                        fieldInfoMap.put(propertyName, new FieldInfo(propertyName, method, field2, ordinal, serialzeFeatures));
                    }
                }
            }
        }
        Field[] arr$2 = clazz.getFields();
        for (Field field3 : arr$2) {
            if (!Modifier.isStatic(field3.getModifiers())) {
                JSONField fieldAnnotation3 = (JSONField) field3.getAnnotation(JSONField.class);
                int ordinal2 = 0;
                int serialzeFeatures2 = 0;
                String propertyName4 = field3.getName();
                if (fieldAnnotation3 != null) {
                    if (fieldAnnotation3.serialize()) {
                        ordinal2 = fieldAnnotation3.ordinal();
                        serialzeFeatures2 = SerializerFeature.of(fieldAnnotation3.serialzeFeatures());
                        if (fieldAnnotation3.name().length() != 0) {
                            propertyName4 = fieldAnnotation3.name();
                        }
                    }
                }
                if ((aliasMap == null || (propertyName4 = aliasMap.get(propertyName4)) != null) && !fieldInfoMap.containsKey(propertyName4)) {
                    fieldInfoMap.put(propertyName4, new FieldInfo(propertyName4, (Method) null, field3, ordinal2, serialzeFeatures2));
                }
            }
        }
        List<FieldInfo> fieldInfoList = new ArrayList<>();
        boolean containsAll = false;
        String[] orders = null;
        JSONType annotation2 = (JSONType) clazz.getAnnotation(JSONType.class);
        if (annotation2 != null) {
            orders = annotation2.orders();
            if (orders == null || orders.length != fieldInfoMap.size()) {
                containsAll = false;
            } else {
                containsAll = true;
                int len$ = orders.length;
                int i$ = 0;
                while (true) {
                    if (i$ >= len$) {
                        break;
                    } else if (!fieldInfoMap.containsKey(orders[i$])) {
                        containsAll = false;
                        break;
                    } else {
                        i$++;
                    }
                }
            }
        }
        if (containsAll) {
            for (String item : orders) {
                fieldInfoList.add(fieldInfoMap.get(item));
            }
        } else {
            for (FieldInfo fieldInfo : fieldInfoMap.values()) {
                fieldInfoList.add(fieldInfo);
            }
            if (sorted) {
                Collections.sort(fieldInfoList);
            }
        }
        return fieldInfoList;
    }

    public static JSONField getSupperMethodAnnotation(Class<?> clazz, Method method) {
        JSONField annotation;
        for (Class<?> interfaceClass : clazz.getInterfaces()) {
            Method[] arr$ = interfaceClass.getMethods();
            for (Method interfaceMethod : arr$) {
                if (interfaceMethod.getName().equals(method.getName()) && interfaceMethod.getParameterTypes().length == method.getParameterTypes().length) {
                    boolean match = true;
                    int i = 0;
                    while (true) {
                        if (i >= interfaceMethod.getParameterTypes().length) {
                            break;
                        } else if (!interfaceMethod.getParameterTypes()[i].equals(method.getParameterTypes()[i])) {
                            match = false;
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (match && (annotation = (JSONField) interfaceMethod.getAnnotation(JSONField.class)) != null) {
                        return annotation;
                    }
                }
            }
        }
        return null;
    }

    private static boolean isJSONTypeIgnore(Class<?> clazz, String propertyName) {
        JSONType jsonType = (JSONType) clazz.getAnnotation(JSONType.class);
        if (!(jsonType == null || jsonType.ignores() == null)) {
            for (String item : jsonType.ignores()) {
                if (propertyName.equalsIgnoreCase(item)) {
                    return true;
                }
            }
        }
        if (clazz.getSuperclass() == Object.class || clazz.getSuperclass() == null || !isJSONTypeIgnore(clazz.getSuperclass(), propertyName)) {
            return false;
        }
        return true;
    }

    public static boolean isGenericParamType(Type type) {
        if (type instanceof ParameterizedType) {
            return true;
        }
        if (type instanceof Class) {
            return isGenericParamType(((Class) type).getGenericSuperclass());
        }
        return false;
    }

    public static Type getGenericParamType(Type type) {
        if (!(type instanceof ParameterizedType) && (type instanceof Class)) {
            return getGenericParamType(((Class) type).getGenericSuperclass());
        }
        return type;
    }

    public static Type unwrap(Type type) {
        if (!(type instanceof GenericArrayType)) {
            return type;
        }
        Type componentType = ((GenericArrayType) type).getGenericComponentType();
        if (componentType == Byte.TYPE) {
            return byte[].class;
        }
        if (componentType == Character.TYPE) {
            return char[].class;
        }
        return type;
    }

    public static Class<?> getClass(Type type) {
        if (type.getClass() == Class.class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return getClass(((ParameterizedType) type).getRawType());
        }
        return Object.class;
    }

    public static Field getField(Class<?> clazz, String fieldName) {
        Field[] arr$ = clazz.getDeclaredFields();
        for (Field field : arr$) {
            if (fieldName.equals(field.getName())) {
                return field;
            }
        }
        Class<?> superClass = clazz.getSuperclass();
        if (superClass == null || superClass == Object.class) {
            return null;
        }
        return getField(superClass, fieldName);
    }

    public static int getSerializeFeatures(Class<?> clazz) {
        JSONType annotation = (JSONType) clazz.getAnnotation(JSONType.class);
        if (annotation == null) {
            return 0;
        }
        return SerializerFeature.of(annotation.serialzeFeatures());
    }

    public static int getParserFeatures(Class<?> clazz) {
        JSONType annotation = (JSONType) clazz.getAnnotation(JSONType.class);
        if (annotation == null) {
            return 0;
        }
        return Feature.of(annotation.parseFeatures());
    }

    public static String decapitalize(String name) {
        if (name == null || name.length() == 0) {
            return name;
        }
        if (name.length() > 1 && Character.isUpperCase(name.charAt(1)) && Character.isUpperCase(name.charAt(0))) {
            return name;
        }
        char[] chars = name.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }

    public static void setAccessible(AccessibleObject obj) {
        if (setAccessibleEnable && !obj.isAccessible()) {
            try {
                obj.setAccessible(true);
            } catch (AccessControlException e) {
                setAccessibleEnable = false;
            }
        }
    }
}

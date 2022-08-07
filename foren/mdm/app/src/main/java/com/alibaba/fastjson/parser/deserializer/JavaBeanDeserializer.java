package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.FilterUtils;
import com.alibaba.fastjson.util.DeserializeBeanInfo;
import com.alibaba.fastjson.util.FieldInfo;
import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import u.aly.av;

/* loaded from: classes.dex */
public class JavaBeanDeserializer implements ObjectDeserializer {
    private DeserializeBeanInfo beanInfo;
    private final Class<?> clazz;
    private final Map<String, FieldDeserializer> feildDeserializerMap;
    private final List<FieldDeserializer> fieldDeserializers;
    private final List<FieldDeserializer> sortedFieldDeserializers;

    public JavaBeanDeserializer(ParserConfig config, Class<?> clazz) {
        this(config, clazz, clazz);
    }

    public JavaBeanDeserializer(ParserConfig config, Class<?> clazz, Type type) {
        this.feildDeserializerMap = new IdentityHashMap();
        this.fieldDeserializers = new ArrayList();
        this.sortedFieldDeserializers = new ArrayList();
        this.clazz = clazz;
        this.beanInfo = DeserializeBeanInfo.computeSetters(clazz, type);
        for (FieldInfo fieldInfo : this.beanInfo.getFieldList()) {
            addFieldDeserializer(config, clazz, fieldInfo);
        }
        for (FieldInfo fieldInfo2 : this.beanInfo.getSortedFieldList()) {
            this.sortedFieldDeserializers.add(this.feildDeserializerMap.get(fieldInfo2.getName().intern()));
        }
    }

    public Map<String, FieldDeserializer> getFieldDeserializerMap() {
        return this.feildDeserializerMap;
    }

    private void addFieldDeserializer(ParserConfig mapping, Class<?> clazz, FieldInfo fieldInfo) {
        String interName = fieldInfo.getName().intern();
        FieldDeserializer fieldDeserializer = createFieldDeserializer(mapping, clazz, fieldInfo);
        this.feildDeserializerMap.put(interName, fieldDeserializer);
        this.fieldDeserializers.add(fieldDeserializer);
    }

    public FieldDeserializer createFieldDeserializer(ParserConfig mapping, Class<?> clazz, FieldInfo fieldInfo) {
        return mapping.createFieldDeserializer(mapping, clazz, fieldInfo);
    }

    public Object createInstance(DefaultJSONParser parser, Type type) {
        if ((type instanceof Class) && this.clazz.isInterface()) {
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{(Class) type}, new JSONObject());
        }
        if (this.beanInfo.getDefaultConstructor() == null) {
            return null;
        }
        try {
            Constructor<?> constructor = this.beanInfo.getDefaultConstructor();
            Object object = constructor.getParameterTypes().length == 0 ? constructor.newInstance(new Object[0]) : constructor.newInstance(parser.getContext().getObject());
            if (parser.isEnabled(Feature.InitStringFieldAsEmpty)) {
                for (FieldInfo fieldInfo : this.beanInfo.getFieldList()) {
                    if (fieldInfo.getFieldClass() == String.class) {
                        try {
                            fieldInfo.set(object, "");
                        } catch (Exception e) {
                            throw new JSONException("create instance error, class " + this.clazz.getName(), e);
                        }
                    }
                }
            }
            return object;
        } catch (Exception e2) {
            throw new JSONException("create instance error, class " + this.clazz.getName(), e2);
        }
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        return (T) deserialze(parser, type, fieldName, null);
    }

    public <T> T deserialzeArrayMapping(DefaultJSONParser parser, Type type, Object fieldName, Object object) {
        JSONLexer lexer = parser.getLexer();
        if (lexer.token() != 14) {
            throw new JSONException(av.aG);
        }
        T t = (T) createInstance(parser, type);
        int size = this.sortedFieldDeserializers.size();
        int i = 0;
        while (i < size) {
            char seperator = i == size + (-1) ? ']' : ',';
            FieldDeserializer fieldDeser = this.sortedFieldDeserializers.get(i);
            Class<?> fieldClass = fieldDeser.getFieldClass();
            if (fieldClass == Integer.TYPE) {
                fieldDeser.setValue((Object) t, lexer.scanInt(seperator));
            } else if (fieldClass == String.class) {
                fieldDeser.setValue((Object) t, lexer.scanString(seperator));
            } else if (fieldClass == Long.TYPE) {
                fieldDeser.setValue(t, lexer.scanLong(seperator));
            } else if (fieldClass.isEnum()) {
                fieldDeser.setValue(t, lexer.scanEnum(fieldClass, parser.getSymbolTable(), seperator));
            } else {
                lexer.nextToken(14);
                fieldDeser.setValue(t, parser.parseObject(fieldDeser.getFieldType()));
                if (seperator == ']') {
                    if (lexer.token() != 15) {
                        throw new JSONException("syntax error");
                    }
                    lexer.nextToken(16);
                } else if (seperator == ',' && lexer.token() != 16) {
                    throw new JSONException("syntax error");
                }
            }
            i++;
        }
        lexer.nextToken(16);
        return t;
    }

    /* JADX WARN: Code restructure failed: missing block: B:115:0x0272, code lost:
        r26 = com.alibaba.fastjson.util.TypeUtils.loadClass(r25);
        r3 = (T) r28.getConfig().getDeserializer(r26).deserialze(r28, r26, r30);
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x028a, code lost:
        if (r10 == null) goto L_0x0291;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x028c, code lost:
        r10.setObject(r31);
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0291, code lost:
        r28.setContext(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x02d7, code lost:
        r18.nextToken();
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x032d, code lost:
        r15 = r27.beanInfo.getFieldList();
        r24 = r15.size();
        r0 = new java.lang.Object[r24];
        r17 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x0345, code lost:
        if (r17 >= r24) goto L_0x035c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0347, code lost:
        r0[r17] = r8.get(r15.get(r17).getName());
        r17 = r17 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0364, code lost:
        if (r27.beanInfo.getCreatorConstructor() == null) goto L_0x03aa;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x0366, code lost:
        r31 = (T) r27.beanInfo.getCreatorConstructor().newInstance(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x0374, code lost:
        if (r10 == null) goto L_0x037b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x0376, code lost:
        r10.setObject(r31);
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x037b, code lost:
        r28.setContext(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0384, code lost:
        r13 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x03a9, code lost:
        throw new com.alibaba.fastjson.JSONException("create instance error, " + r27.beanInfo.getCreatorConstructor().toGenericString(), r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x03b2, code lost:
        if (r27.beanInfo.getFactoryMethod() == null) goto L_0x0374;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x03b4, code lost:
        r31 = (T) r27.beanInfo.getFactoryMethod().invoke(null, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x03c4, code lost:
        r13 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x03e9, code lost:
        throw new com.alibaba.fastjson.JSONException("create factory method error, " + r27.beanInfo.getFactoryMethod().toString(), r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:?, code lost:
        return r31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:?, code lost:
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:?, code lost:
        return (T) r31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0101, code lost:
        if (r31 != null) goto L_0x0374;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0103, code lost:
        if (r8 != null) goto L_0x032d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0105, code lost:
        r31 = (T) createInstance(r28, r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0109, code lost:
        if (r10 != null) goto L_0x0115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x010b, code lost:
        r10 = r28.setContext(r11, r31, r30);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0115, code lost:
        if (r10 == null) goto L_0x011c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0117, code lost:
        r10.setObject(r31);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x011c, code lost:
        r28.setContext(r11);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r28, java.lang.reflect.Type r29, java.lang.Object r30, java.lang.Object r31) {
        /*
            Method dump skipped, instructions count: 1010
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public boolean parseField(DefaultJSONParser parser, String key, Object object, Type objectType, Map<String, Object> fieldValues) {
        JSONLexer lexer = parser.getLexer();
        FieldDeserializer fieldDeserializer = this.feildDeserializerMap.get(key);
        if (fieldDeserializer == null) {
            Iterator i$ = this.feildDeserializerMap.entrySet().iterator();
            while (true) {
                if (!i$.hasNext()) {
                    break;
                }
                Map.Entry<String, FieldDeserializer> entry = i$.next();
                if (entry.getKey().equalsIgnoreCase(key)) {
                    fieldDeserializer = entry.getValue();
                    break;
                }
            }
        }
        if (fieldDeserializer == null) {
            parseExtra(parser, object, key);
            return false;
        }
        lexer.nextTokenWithColon(fieldDeserializer.getFastMatchToken());
        fieldDeserializer.parseField(parser, object, objectType, fieldValues);
        return true;
    }

    void parseExtra(DefaultJSONParser parser, Object object, String key) {
        Object value;
        JSONLexer lexer = parser.getLexer();
        if (!lexer.isEnabled(Feature.IgnoreNotMatch)) {
            throw new JSONException("setter not found, class " + this.clazz.getName() + ", property " + key);
        }
        lexer.nextTokenWithColon();
        Type type = FilterUtils.getExtratype(parser, object, key);
        if (type == null) {
            value = parser.parse();
        } else {
            value = parser.parseObject(type);
        }
        FilterUtils.processExtra(parser, object, key, value);
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 12;
    }

    public final boolean isSupportArrayToBean(JSONLexer lexer) {
        return Feature.isEnabled(this.beanInfo.getParserFeatures(), Feature.SupportArrayToBean) || lexer.isEnabled(Feature.SupportArrayToBean);
    }
}

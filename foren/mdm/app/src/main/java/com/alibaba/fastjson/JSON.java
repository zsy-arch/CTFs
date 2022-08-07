package com.alibaba.fastjson;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.ParseProcess;
import com.alibaba.fastjson.serializer.AfterFilter;
import com.alibaba.fastjson.serializer.BeforeFilter;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class JSON implements JSONStreamAware, JSONAware {
    public static final String VERSION = "1.1.46";
    public static String DEFAULT_TYPE_KEY = "@type";
    public static String DUMP_CLASS = null;
    public static int DEFAULT_PARSER_FEATURE = (((((((0 | Feature.AutoCloseSource.getMask()) | Feature.InternFieldNames.getMask()) | Feature.UseBigDecimal.getMask()) | Feature.AllowUnQuotedFieldNames.getMask()) | Feature.AllowSingleQuotes.getMask()) | Feature.AllowArbitraryCommas.getMask()) | Feature.SortFeidFastMatch.getMask()) | Feature.IgnoreNotMatch.getMask();
    public static String DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static int DEFAULT_GENERATE_FEATURE = (((0 | SerializerFeature.QuoteFieldNames.getMask()) | SerializerFeature.SkipTransientField.getMask()) | SerializerFeature.WriteEnumUsingToString.getMask()) | SerializerFeature.SortField.getMask();

    public static final Object parse(String text) {
        return parse(text, DEFAULT_PARSER_FEATURE);
    }

    public static final Object parse(String text, int features) {
        if (text == null) {
            return null;
        }
        DefaultJSONParser parser = new DefaultJSONParser(text, ParserConfig.getGlobalInstance(), features);
        Object value = parser.parse();
        parser.handleResovleTask(value);
        parser.close();
        return value;
    }

    public static final Object parse(byte[] input, Feature... features) {
        try {
            return parseObject(new String(input, "UTF-8"), features);
        } catch (UnsupportedEncodingException e) {
            throw new JSONException("parseObject error", e);
        }
    }

    public static final Object parse(String text, Feature... features) {
        int featureValues = DEFAULT_PARSER_FEATURE;
        for (Feature featrue : features) {
            featureValues = Feature.config(featureValues, featrue, true);
        }
        return parse(text, featureValues);
    }

    public static final JSONObject parseObject(String text, Feature... features) {
        return (JSONObject) parse(text, features);
    }

    public static final JSONObject parseObject(String text) {
        Object obj = parse(text);
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        return (JSONObject) toJSON(obj);
    }

    public static final <T> T parseObject(String text, TypeReference<T> type, Feature... features) {
        return (T) parseObject(text, type.getType(), ParserConfig.getGlobalInstance(), DEFAULT_PARSER_FEATURE, features);
    }

    public static final <T> T parseObject(String text, Class<T> clazz, Feature... features) {
        return (T) parseObject(text, clazz, ParserConfig.getGlobalInstance(), DEFAULT_PARSER_FEATURE, features);
    }

    public static final <T> T parseObject(String text, Class<T> clazz, ParseProcess processor, Feature... features) {
        return (T) parseObject(text, clazz, ParserConfig.getGlobalInstance(), processor, DEFAULT_PARSER_FEATURE, features);
    }

    public static final <T> T parseObject(String input, Type clazz, Feature... features) {
        return (T) parseObject(input, clazz, ParserConfig.getGlobalInstance(), DEFAULT_PARSER_FEATURE, features);
    }

    public static final <T> T parseObject(String input, Type clazz, ParseProcess processor, Feature... features) {
        return (T) parseObject(input, clazz, ParserConfig.getGlobalInstance(), DEFAULT_PARSER_FEATURE, features);
    }

    public static final <T> T parseObject(String input, Type clazz, int featureValues, Feature... features) {
        if (input == null) {
            return null;
        }
        for (Feature featrue : features) {
            featureValues = Feature.config(featureValues, featrue, true);
        }
        DefaultJSONParser parser = new DefaultJSONParser(input, ParserConfig.getGlobalInstance(), featureValues);
        T value = (T) parser.parseObject(clazz);
        parser.handleResovleTask(value);
        parser.close();
        return value;
    }

    public static final <T> T parseObject(String input, Type clazz, ParserConfig config, int featureValues, Feature... features) {
        return (T) parseObject(input, clazz, config, null, featureValues, features);
    }

    public static final <T> T parseObject(String input, Type clazz, ParserConfig config, ParseProcess processor, int featureValues, Feature... features) {
        if (input == null) {
            return null;
        }
        for (Feature featrue : features) {
            featureValues = Feature.config(featureValues, featrue, true);
        }
        DefaultJSONParser parser = new DefaultJSONParser(input, config, featureValues);
        if (processor instanceof ExtraTypeProvider) {
            parser.getExtraTypeProviders().add((ExtraTypeProvider) processor);
        }
        if (processor instanceof ExtraProcessor) {
            parser.getExtraProcessors().add((ExtraProcessor) processor);
        }
        T value = (T) parser.parseObject(clazz);
        parser.handleResovleTask(value);
        parser.close();
        return value;
    }

    public static final <T> T parseObject(byte[] input, Type clazz, Feature... features) {
        try {
            return (T) parseObject(new String(input, "UTF-8"), clazz, features);
        } catch (UnsupportedEncodingException e) {
            throw new JSONException("parseObject error", e);
        }
    }

    public static final <T> T parseObject(char[] input, int length, Type clazz, Feature... features) {
        if (input == null || input.length == 0) {
            return null;
        }
        int featureValues = DEFAULT_PARSER_FEATURE;
        for (Feature featrue : features) {
            featureValues = Feature.config(featureValues, featrue, true);
        }
        DefaultJSONParser parser = new DefaultJSONParser(input, length, ParserConfig.getGlobalInstance(), featureValues);
        T value = (T) parser.parseObject(clazz);
        parser.handleResovleTask(value);
        parser.close();
        return value;
    }

    public static final <T> T parseObject(String text, Class<T> clazz) {
        return (T) parseObject(text, (Class<Object>) clazz, new Feature[0]);
    }

    public static final JSONArray parseArray(String text) {
        JSONArray array;
        if (text == null) {
            return null;
        }
        DefaultJSONParser parser = new DefaultJSONParser(text, ParserConfig.getGlobalInstance());
        JSONLexer lexer = parser.getLexer();
        if (lexer.token() == 8) {
            lexer.nextToken();
            array = null;
        } else if (lexer.token() == 20) {
            array = null;
        } else {
            array = new JSONArray();
            parser.parseArray(array);
            parser.handleResovleTask(array);
        }
        parser.close();
        return array;
    }

    public static final <T> List<T> parseArray(String text, Class<T> clazz) {
        List<T> list;
        if (text == null) {
            return null;
        }
        DefaultJSONParser parser = new DefaultJSONParser(text, ParserConfig.getGlobalInstance());
        JSONLexer lexer = parser.getLexer();
        if (lexer.token() == 8) {
            lexer.nextToken();
            list = null;
        } else {
            list = new ArrayList<>();
            parser.parseArray((Class<?>) clazz, (Collection) list);
            parser.handleResovleTask(list);
        }
        parser.close();
        return list;
    }

    public static final List<Object> parseArray(String text, Type[] types) {
        List<Object> list;
        if (text == null) {
            return null;
        }
        DefaultJSONParser parser = new DefaultJSONParser(text, ParserConfig.getGlobalInstance());
        Object[] objectArray = parser.parseArray(types);
        if (objectArray == null) {
            list = null;
        } else {
            list = Arrays.asList(objectArray);
        }
        parser.handleResovleTask(list);
        parser.close();
        return list;
    }

    public static final String toJSONString(Object object) {
        return toJSONString(object, new SerializerFeature[0]);
    }

    public static final String toJSONString(Object object, SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter();
        try {
            JSONSerializer serializer = new JSONSerializer(out);
            for (SerializerFeature feature : features) {
                serializer.config(feature, true);
            }
            serializer.write(object);
            return out.toString();
        } finally {
            out.close();
        }
    }

    public static final String toJSONStringWithDateFormat(Object object, String dateFormat, SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter();
        try {
            JSONSerializer serializer = new JSONSerializer(out);
            for (SerializerFeature feature : features) {
                serializer.config(feature, true);
            }
            serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
            if (dateFormat != null) {
                serializer.setDateFormat(dateFormat);
            }
            serializer.write(object);
            return out.toString();
        } finally {
            out.close();
        }
    }

    public static final String toJSONString(Object object, SerializeFilter filter, SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter();
        try {
            JSONSerializer serializer = new JSONSerializer(out);
            for (SerializerFeature feature : features) {
                serializer.config(feature, true);
            }
            serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
            setFilter(serializer, filter);
            serializer.write(object);
            return out.toString();
        } finally {
            out.close();
        }
    }

    public static final String toJSONString(Object object, SerializeFilter[] filters, SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter();
        try {
            JSONSerializer serializer = new JSONSerializer(out);
            for (SerializerFeature feature : features) {
                serializer.config(feature, true);
            }
            serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
            setFilter(serializer, filters);
            serializer.write(object);
            return out.toString();
        } finally {
            out.close();
        }
    }

    public static final byte[] toJSONBytes(Object object, SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter();
        try {
            JSONSerializer serializer = new JSONSerializer(out);
            for (SerializerFeature feature : features) {
                serializer.config(feature, true);
            }
            serializer.write(object);
            return out.toBytes("UTF-8");
        } finally {
            out.close();
        }
    }

    public static final String toJSONString(Object object, SerializeConfig config, SerializerFeature... features) {
        return toJSONString(object, config, (SerializeFilter) null, features);
    }

    public static final String toJSONString(Object object, SerializeConfig config, SerializeFilter filter, SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter();
        try {
            JSONSerializer serializer = new JSONSerializer(out, config);
            for (SerializerFeature feature : features) {
                serializer.config(feature, true);
            }
            setFilter(serializer, filter);
            serializer.write(object);
            return out.toString();
        } finally {
            out.close();
        }
    }

    public static final String toJSONString(Object object, SerializeConfig config, SerializeFilter[] filters, SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter();
        try {
            JSONSerializer serializer = new JSONSerializer(out, config);
            for (SerializerFeature feature : features) {
                serializer.config(feature, true);
            }
            setFilter(serializer, filters);
            serializer.write(object);
            return out.toString();
        } finally {
            out.close();
        }
    }

    public static final String toJSONStringZ(Object object, SerializeConfig mapping, SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter(features);
        try {
            new JSONSerializer(out, mapping).write(object);
            return out.toString();
        } finally {
            out.close();
        }
    }

    public static final byte[] toJSONBytes(Object object, SerializeConfig config, SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter();
        try {
            JSONSerializer serializer = new JSONSerializer(out, config);
            for (SerializerFeature feature : features) {
                serializer.config(feature, true);
            }
            serializer.write(object);
            return out.toBytes("UTF-8");
        } finally {
            out.close();
        }
    }

    public static final String toJSONString(Object object, boolean prettyFormat) {
        return !prettyFormat ? toJSONString(object) : toJSONString(object, SerializerFeature.PrettyFormat);
    }

    public static final void writeJSONStringTo(Object object, Writer writer, SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter(writer);
        try {
            JSONSerializer serializer = new JSONSerializer(out);
            for (SerializerFeature feature : features) {
                serializer.config(feature, true);
            }
            serializer.write(object);
        } finally {
            out.close();
        }
    }

    public String toString() {
        return toJSONString();
    }

    @Override // com.alibaba.fastjson.JSONAware
    public String toJSONString() {
        SerializeWriter out = new SerializeWriter();
        try {
            new JSONSerializer(out).write(this);
            return out.toString();
        } finally {
            out.close();
        }
    }

    @Override // com.alibaba.fastjson.JSONStreamAware
    public void writeJSONString(Appendable appendable) {
        SerializeWriter out;
        try {
            out = new SerializeWriter();
            try {
                new JSONSerializer(out).write(this);
                appendable.append(out.toString());
            } catch (IOException e) {
                throw new JSONException(e.getMessage(), e);
            }
        } finally {
            out.close();
        }
    }

    public static final Object toJSON(Object javaObject) {
        return toJSON(javaObject, ParserConfig.getGlobalInstance());
    }

    public static final Object toJSON(Object javaObject, ParserConfig mapping) {
        if (javaObject == null) {
            return null;
        }
        if (javaObject instanceof JSON) {
            return (JSON) javaObject;
        }
        if (javaObject instanceof Map) {
            Map<Object, Object> map = (Map) javaObject;
            JSONObject json = new JSONObject(map.size());
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                json.put(TypeUtils.castToString(entry.getKey()), toJSON(entry.getValue()));
            }
            return json;
        } else if (javaObject instanceof Collection) {
            Collection<Object> collection = (Collection) javaObject;
            JSONArray array = new JSONArray(collection.size());
            for (Object item : collection) {
                array.add(toJSON(item));
            }
            return array;
        } else {
            Class<?> clazz = javaObject.getClass();
            if (clazz.isEnum()) {
                return ((Enum) javaObject).name();
            }
            if (clazz.isArray()) {
                int len = Array.getLength(javaObject);
                JSONArray array2 = new JSONArray(len);
                for (int i = 0; i < len; i++) {
                    array2.add(toJSON(Array.get(javaObject, i)));
                }
                return array2;
            } else if (mapping.isPrimitive(clazz)) {
                return javaObject;
            } else {
                try {
                    List<FieldInfo> getters = TypeUtils.computeGetters(clazz, null);
                    JSONObject json2 = new JSONObject(getters.size());
                    for (FieldInfo field : getters) {
                        json2.put(field.getName(), toJSON(field.get(javaObject)));
                    }
                    return json2;
                } catch (IllegalAccessException e) {
                    throw new JSONException("toJSON error", e);
                } catch (InvocationTargetException e2) {
                    throw new JSONException("toJSON error", e2);
                }
            }
        }
    }

    public static final <T> T toJavaObject(JSON json, Class<T> clazz) {
        return (T) TypeUtils.cast((Object) json, (Class<Object>) clazz, ParserConfig.getGlobalInstance());
    }

    private static void setFilter(JSONSerializer serializer, SerializeFilter... filters) {
        for (SerializeFilter filter : filters) {
            setFilter(serializer, filter);
        }
    }

    private static void setFilter(JSONSerializer serializer, SerializeFilter filter) {
        if (filter != null) {
            if (filter instanceof PropertyPreFilter) {
                serializer.getPropertyPreFilters().add((PropertyPreFilter) filter);
            }
            if (filter instanceof NameFilter) {
                serializer.getNameFilters().add((NameFilter) filter);
            }
            if (filter instanceof ValueFilter) {
                serializer.getValueFilters().add((ValueFilter) filter);
            }
            if (filter instanceof PropertyFilter) {
                serializer.getPropertyFilters().add((PropertyFilter) filter);
            }
            if (filter instanceof BeforeFilter) {
                serializer.getBeforeFilters().add((BeforeFilter) filter);
            }
            if (filter instanceof AfterFilter) {
                serializer.getAfterFilters().add((AfterFilter) filter);
            }
        }
    }
}

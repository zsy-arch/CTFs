package com.google.gson;

import com.alipay.sdk.util.h;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.Streams;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import com.google.gson.internal.bind.TimeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* loaded from: classes.dex */
public final class Gson {
    static final boolean DEFAULT_COMPLEX_MAP_KEYS = false;
    static final boolean DEFAULT_ESCAPE_HTML = true;
    static final boolean DEFAULT_JSON_NON_EXECUTABLE = false;
    static final boolean DEFAULT_LENIENT = false;
    static final boolean DEFAULT_PRETTY_PRINT = false;
    static final boolean DEFAULT_SERIALIZE_NULLS = false;
    static final boolean DEFAULT_SPECIALIZE_FLOAT_VALUES = false;
    private static final String JSON_NON_EXECUTABLE_PREFIX = ")]}'\n";
    private static final TypeToken<?> NULL_KEY_SURROGATE = new TypeToken<Object>() { // from class: com.google.gson.Gson.1
    };
    private final ThreadLocal<Map<TypeToken<?>, FutureTypeAdapter<?>>> calls;
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final List<TypeAdapterFactory> factories;
    private final FieldNamingStrategy fieldNamingStrategy;
    private final boolean generateNonExecutableJson;
    private final boolean htmlSafe;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
    private final boolean lenient;
    private final boolean prettyPrinting;
    private final boolean serializeNulls;
    private final Map<TypeToken<?>, TypeAdapter<?>> typeTokenCache;

    public Gson() {
        this(Excluder.DEFAULT, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, LongSerializationPolicy.DEFAULT, Collections.emptyList());
    }

    public Gson(Excluder excluder, FieldNamingStrategy fieldNamingStrategy, Map<Type, InstanceCreator<?>> instanceCreators, boolean serializeNulls, boolean complexMapKeySerialization, boolean generateNonExecutableGson, boolean htmlSafe, boolean prettyPrinting, boolean lenient, boolean serializeSpecialFloatingPointValues, LongSerializationPolicy longSerializationPolicy, List<TypeAdapterFactory> typeAdapterFactories) {
        this.calls = new ThreadLocal<>();
        this.typeTokenCache = new ConcurrentHashMap();
        this.constructorConstructor = new ConstructorConstructor(instanceCreators);
        this.excluder = excluder;
        this.fieldNamingStrategy = fieldNamingStrategy;
        this.serializeNulls = serializeNulls;
        this.generateNonExecutableJson = generateNonExecutableGson;
        this.htmlSafe = htmlSafe;
        this.prettyPrinting = prettyPrinting;
        this.lenient = lenient;
        List<TypeAdapterFactory> factories = new ArrayList<>();
        factories.add(TypeAdapters.JSON_ELEMENT_FACTORY);
        factories.add(ObjectTypeAdapter.FACTORY);
        factories.add(excluder);
        factories.addAll(typeAdapterFactories);
        factories.add(TypeAdapters.STRING_FACTORY);
        factories.add(TypeAdapters.INTEGER_FACTORY);
        factories.add(TypeAdapters.BOOLEAN_FACTORY);
        factories.add(TypeAdapters.BYTE_FACTORY);
        factories.add(TypeAdapters.SHORT_FACTORY);
        TypeAdapter<Number> longAdapter = longAdapter(longSerializationPolicy);
        factories.add(TypeAdapters.newFactory(Long.TYPE, Long.class, longAdapter));
        factories.add(TypeAdapters.newFactory(Double.TYPE, Double.class, doubleAdapter(serializeSpecialFloatingPointValues)));
        factories.add(TypeAdapters.newFactory(Float.TYPE, Float.class, floatAdapter(serializeSpecialFloatingPointValues)));
        factories.add(TypeAdapters.NUMBER_FACTORY);
        factories.add(TypeAdapters.ATOMIC_INTEGER_FACTORY);
        factories.add(TypeAdapters.ATOMIC_BOOLEAN_FACTORY);
        factories.add(TypeAdapters.newFactory(AtomicLong.class, atomicLongAdapter(longAdapter)));
        factories.add(TypeAdapters.newFactory(AtomicLongArray.class, atomicLongArrayAdapter(longAdapter)));
        factories.add(TypeAdapters.ATOMIC_INTEGER_ARRAY_FACTORY);
        factories.add(TypeAdapters.CHARACTER_FACTORY);
        factories.add(TypeAdapters.STRING_BUILDER_FACTORY);
        factories.add(TypeAdapters.STRING_BUFFER_FACTORY);
        factories.add(TypeAdapters.newFactory(BigDecimal.class, TypeAdapters.BIG_DECIMAL));
        factories.add(TypeAdapters.newFactory(BigInteger.class, TypeAdapters.BIG_INTEGER));
        factories.add(TypeAdapters.URL_FACTORY);
        factories.add(TypeAdapters.URI_FACTORY);
        factories.add(TypeAdapters.UUID_FACTORY);
        factories.add(TypeAdapters.CURRENCY_FACTORY);
        factories.add(TypeAdapters.LOCALE_FACTORY);
        factories.add(TypeAdapters.INET_ADDRESS_FACTORY);
        factories.add(TypeAdapters.BIT_SET_FACTORY);
        factories.add(DateTypeAdapter.FACTORY);
        factories.add(TypeAdapters.CALENDAR_FACTORY);
        factories.add(TimeTypeAdapter.FACTORY);
        factories.add(SqlDateTypeAdapter.FACTORY);
        factories.add(TypeAdapters.TIMESTAMP_FACTORY);
        factories.add(ArrayTypeAdapter.FACTORY);
        factories.add(TypeAdapters.CLASS_FACTORY);
        factories.add(new CollectionTypeAdapterFactory(this.constructorConstructor));
        factories.add(new MapTypeAdapterFactory(this.constructorConstructor, complexMapKeySerialization));
        this.jsonAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(this.constructorConstructor);
        factories.add(this.jsonAdapterFactory);
        factories.add(TypeAdapters.ENUM_FACTORY);
        factories.add(new ReflectiveTypeAdapterFactory(this.constructorConstructor, fieldNamingStrategy, excluder, this.jsonAdapterFactory));
        this.factories = Collections.unmodifiableList(factories);
    }

    public Excluder excluder() {
        return this.excluder;
    }

    public FieldNamingStrategy fieldNamingStrategy() {
        return this.fieldNamingStrategy;
    }

    public boolean serializeNulls() {
        return this.serializeNulls;
    }

    public boolean htmlSafe() {
        return this.htmlSafe;
    }

    private TypeAdapter<Number> doubleAdapter(boolean serializeSpecialFloatingPointValues) {
        return serializeSpecialFloatingPointValues ? TypeAdapters.DOUBLE : new TypeAdapter<Number>() { // from class: com.google.gson.Gson.2
            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public Number read2(JsonReader in) throws IOException {
                if (in.peek() != JsonToken.NULL) {
                    return Double.valueOf(in.nextDouble());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, Number value) throws IOException {
                if (value == null) {
                    out.nullValue();
                    return;
                }
                Gson.checkValidFloatingPoint(value.doubleValue());
                out.value(value);
            }
        };
    }

    private TypeAdapter<Number> floatAdapter(boolean serializeSpecialFloatingPointValues) {
        return serializeSpecialFloatingPointValues ? TypeAdapters.FLOAT : new TypeAdapter<Number>() { // from class: com.google.gson.Gson.3
            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public Number read2(JsonReader in) throws IOException {
                if (in.peek() != JsonToken.NULL) {
                    return Float.valueOf((float) in.nextDouble());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, Number value) throws IOException {
                if (value == null) {
                    out.nullValue();
                    return;
                }
                Gson.checkValidFloatingPoint(value.floatValue());
                out.value(value);
            }
        };
    }

    static void checkValidFloatingPoint(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException(value + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private static TypeAdapter<Number> longAdapter(LongSerializationPolicy longSerializationPolicy) {
        return longSerializationPolicy == LongSerializationPolicy.DEFAULT ? TypeAdapters.LONG : new TypeAdapter<Number>() { // from class: com.google.gson.Gson.4
            @Override // com.google.gson.TypeAdapter
            public Number read(JsonReader in) throws IOException {
                if (in.peek() != JsonToken.NULL) {
                    return Long.valueOf(in.nextLong());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, Number value) throws IOException {
                if (value == null) {
                    out.nullValue();
                } else {
                    out.value(value.toString());
                }
            }
        };
    }

    private static TypeAdapter<AtomicLong> atomicLongAdapter(final TypeAdapter<Number> longAdapter) {
        return new TypeAdapter<AtomicLong>() { // from class: com.google.gson.Gson.5
            public void write(JsonWriter out, AtomicLong value) throws IOException {
                longAdapter.write(out, Long.valueOf(value.get()));
            }

            @Override // com.google.gson.TypeAdapter
            public AtomicLong read(JsonReader in) throws IOException {
                return new AtomicLong(((Number) longAdapter.read(in)).longValue());
            }
        }.nullSafe();
    }

    private static TypeAdapter<AtomicLongArray> atomicLongArrayAdapter(final TypeAdapter<Number> longAdapter) {
        return new TypeAdapter<AtomicLongArray>() { // from class: com.google.gson.Gson.6
            public void write(JsonWriter out, AtomicLongArray value) throws IOException {
                out.beginArray();
                int length = value.length();
                for (int i = 0; i < length; i++) {
                    longAdapter.write(out, Long.valueOf(value.get(i)));
                }
                out.endArray();
            }

            @Override // com.google.gson.TypeAdapter
            public AtomicLongArray read(JsonReader in) throws IOException {
                List<Long> list = new ArrayList<>();
                in.beginArray();
                while (in.hasNext()) {
                    list.add(Long.valueOf(((Number) longAdapter.read(in)).longValue()));
                }
                in.endArray();
                int length = list.size();
                AtomicLongArray array = new AtomicLongArray(length);
                for (int i = 0; i < length; i++) {
                    array.set(i, list.get(i).longValue());
                }
                return array;
            }
        }.nullSafe();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> TypeAdapter<T> getAdapter(TypeToken<T> type) {
        TypeAdapter<T> typeAdapter = (TypeAdapter<T>) this.typeTokenCache.get(type == null ? NULL_KEY_SURROGATE : type);
        if (typeAdapter != null) {
            return typeAdapter;
        }
        Map<TypeToken<?>, FutureTypeAdapter<?>> threadCalls = this.calls.get();
        boolean requiresThreadLocalCleanup = false;
        if (threadCalls == null) {
            threadCalls = new HashMap<>();
            this.calls.set(threadCalls);
            requiresThreadLocalCleanup = true;
        }
        FutureTypeAdapter<?> futureTypeAdapter = threadCalls.get(type);
        if (futureTypeAdapter != null) {
            return futureTypeAdapter;
        }
        try {
            FutureTypeAdapter<?> futureTypeAdapter2 = new FutureTypeAdapter<>();
            threadCalls.put(type, futureTypeAdapter2);
            for (TypeAdapterFactory factory : this.factories) {
                TypeAdapter typeAdapter2 = (TypeAdapter<T>) factory.create(this, type);
                if (typeAdapter2 != null) {
                    futureTypeAdapter2.setDelegate(typeAdapter2);
                    this.typeTokenCache.put(type, typeAdapter2);
                    return typeAdapter2;
                }
            }
            throw new IllegalArgumentException("GSON cannot handle " + type);
        } finally {
            threadCalls.remove(type);
            if (requiresThreadLocalCleanup) {
                this.calls.remove();
            }
        }
    }

    public <T> TypeAdapter<T> getDelegateAdapter(TypeAdapterFactory skipPast, TypeToken<T> type) {
        if (!this.factories.contains(skipPast)) {
            skipPast = this.jsonAdapterFactory;
        }
        boolean skipPastFound = false;
        for (TypeAdapterFactory factory : this.factories) {
            if (skipPastFound) {
                TypeAdapter<T> candidate = factory.create(this, type);
                if (candidate != null) {
                    return candidate;
                }
            } else if (factory == skipPast) {
                skipPastFound = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + type);
    }

    public <T> TypeAdapter<T> getAdapter(Class<T> type) {
        return getAdapter(TypeToken.get((Class) type));
    }

    public JsonElement toJsonTree(Object src) {
        return src == null ? JsonNull.INSTANCE : toJsonTree(src, src.getClass());
    }

    public JsonElement toJsonTree(Object src, Type typeOfSrc) {
        JsonTreeWriter writer = new JsonTreeWriter();
        toJson(src, typeOfSrc, writer);
        return writer.get();
    }

    public String toJson(Object src) {
        return src == null ? toJson((JsonElement) JsonNull.INSTANCE) : toJson(src, src.getClass());
    }

    public String toJson(Object src, Type typeOfSrc) {
        StringWriter writer = new StringWriter();
        toJson(src, typeOfSrc, writer);
        return writer.toString();
    }

    public void toJson(Object src, Appendable writer) throws JsonIOException {
        if (src != null) {
            toJson(src, src.getClass(), writer);
        } else {
            toJson((JsonElement) JsonNull.INSTANCE, writer);
        }
    }

    public void toJson(Object src, Type typeOfSrc, Appendable writer) throws JsonIOException {
        try {
            toJson(src, typeOfSrc, newJsonWriter(Streams.writerForAppendable(writer)));
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }

    public void toJson(Object src, Type typeOfSrc, JsonWriter writer) throws JsonIOException {
        boolean oldLenient;
        boolean oldHtmlSafe;
        boolean oldSerializeNulls;
        try {
            TypeAdapter<?> adapter = getAdapter(TypeToken.get(typeOfSrc));
            oldLenient = writer.isLenient();
            writer.setLenient(true);
            oldHtmlSafe = writer.isHtmlSafe();
            writer.setHtmlSafe(this.htmlSafe);
            oldSerializeNulls = writer.getSerializeNulls();
            writer.setSerializeNulls(this.serializeNulls);
            try {
                adapter.write(writer, src);
            } catch (IOException e) {
                throw new JsonIOException(e);
            }
        } finally {
            writer.setLenient(oldLenient);
            writer.setHtmlSafe(oldHtmlSafe);
            writer.setSerializeNulls(oldSerializeNulls);
        }
    }

    public String toJson(JsonElement jsonElement) {
        StringWriter writer = new StringWriter();
        toJson(jsonElement, (Appendable) writer);
        return writer.toString();
    }

    public void toJson(JsonElement jsonElement, Appendable writer) throws JsonIOException {
        try {
            toJson(jsonElement, newJsonWriter(Streams.writerForAppendable(writer)));
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }

    public JsonWriter newJsonWriter(Writer writer) throws IOException {
        if (this.generateNonExecutableJson) {
            writer.write(JSON_NON_EXECUTABLE_PREFIX);
        }
        JsonWriter jsonWriter = new JsonWriter(writer);
        if (this.prettyPrinting) {
            jsonWriter.setIndent("  ");
        }
        jsonWriter.setSerializeNulls(this.serializeNulls);
        return jsonWriter;
    }

    public JsonReader newJsonReader(Reader reader) {
        JsonReader jsonReader = new JsonReader(reader);
        jsonReader.setLenient(this.lenient);
        return jsonReader;
    }

    public void toJson(JsonElement jsonElement, JsonWriter writer) throws JsonIOException {
        boolean oldLenient = writer.isLenient();
        writer.setLenient(true);
        boolean oldHtmlSafe = writer.isHtmlSafe();
        writer.setHtmlSafe(this.htmlSafe);
        boolean oldSerializeNulls = writer.getSerializeNulls();
        writer.setSerializeNulls(this.serializeNulls);
        try {
            try {
                Streams.write(jsonElement, writer);
            } catch (IOException e) {
                throw new JsonIOException(e);
            }
        } finally {
            writer.setLenient(oldLenient);
            writer.setHtmlSafe(oldHtmlSafe);
            writer.setSerializeNulls(oldSerializeNulls);
        }
    }

    public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return (T) Primitives.wrap(classOfT).cast(fromJson(json, (Type) classOfT));
    }

    public <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException {
        if (json == null) {
            return null;
        }
        return (T) fromJson(new StringReader(json), typeOfT);
    }

    public <T> T fromJson(Reader json, Class<T> classOfT) throws JsonSyntaxException, JsonIOException {
        JsonReader jsonReader = newJsonReader(json);
        Object object = fromJson(jsonReader, classOfT);
        assertFullConsumption(object, jsonReader);
        return (T) Primitives.wrap(classOfT).cast(object);
    }

    public <T> T fromJson(Reader json, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        JsonReader jsonReader = newJsonReader(json);
        T object = (T) fromJson(jsonReader, typeOfT);
        assertFullConsumption(object, jsonReader);
        return object;
    }

    private static void assertFullConsumption(Object obj, JsonReader reader) {
        if (obj != null) {
            try {
                if (reader.peek() != JsonToken.END_DOCUMENT) {
                    throw new JsonIOException("JSON document was not fully consumed.");
                }
            } catch (MalformedJsonException e) {
                throw new JsonSyntaxException(e);
            } catch (IOException e2) {
                throw new JsonIOException(e2);
            }
        }
    }

    public <T> T fromJson(JsonReader reader, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        boolean oldLenient;
        try {
            boolean isEmpty = true;
            oldLenient = reader.isLenient();
            reader.setLenient(true);
            try {
                reader.peek();
                isEmpty = false;
                T object = getAdapter(TypeToken.get(typeOfT)).read(reader);
                reader.setLenient(oldLenient);
                return object;
            } catch (EOFException e) {
                if (isEmpty) {
                    reader.setLenient(oldLenient);
                    return null;
                }
                throw new JsonSyntaxException(e);
            } catch (IOException e2) {
                throw new JsonSyntaxException(e2);
            } catch (IllegalStateException e3) {
                throw new JsonSyntaxException(e3);
            }
        } catch (Throwable th) {
            reader.setLenient(oldLenient);
            throw th;
        }
    }

    public <T> T fromJson(JsonElement json, Class<T> classOfT) throws JsonSyntaxException {
        return (T) Primitives.wrap(classOfT).cast(fromJson(json, (Type) classOfT));
    }

    public <T> T fromJson(JsonElement json, Type typeOfT) throws JsonSyntaxException {
        if (json == null) {
            return null;
        }
        return (T) fromJson(new JsonTreeReader(json), typeOfT);
    }

    /* loaded from: classes.dex */
    public static class FutureTypeAdapter<T> extends TypeAdapter<T> {
        private TypeAdapter<T> delegate;

        FutureTypeAdapter() {
        }

        public void setDelegate(TypeAdapter<T> typeAdapter) {
            if (this.delegate != null) {
                throw new AssertionError();
            }
            this.delegate = typeAdapter;
        }

        @Override // com.google.gson.TypeAdapter
        public T read(JsonReader in) throws IOException {
            if (this.delegate != null) {
                return this.delegate.read(in);
            }
            throw new IllegalStateException();
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter out, T value) throws IOException {
            if (this.delegate == null) {
                throw new IllegalStateException();
            }
            this.delegate.write(out, value);
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.serializeNulls + "factories:" + this.factories + ",instanceCreators:" + this.constructorConstructor + h.d;
    }
}

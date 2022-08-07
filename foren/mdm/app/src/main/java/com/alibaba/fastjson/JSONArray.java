package com.alibaba.fastjson;

import com.alibaba.fastjson.util.TypeUtils;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* loaded from: classes.dex */
public class JSONArray extends JSON implements List<Object>, Cloneable, RandomAccess, Serializable {
    private static final long serialVersionUID = 1;
    protected transient Type componentType;
    private final List<Object> list;
    protected transient Object relatedArray;

    public JSONArray() {
        this.list = new ArrayList(10);
    }

    public JSONArray(List<Object> list) {
        this.list = list;
    }

    public JSONArray(int initialCapacity) {
        this.list = new ArrayList(initialCapacity);
    }

    public Object getRelatedArray() {
        return this.relatedArray;
    }

    public void setRelatedArray(Object relatedArray) {
        this.relatedArray = relatedArray;
    }

    public Type getComponentType() {
        return this.componentType;
    }

    public void setComponentType(Type componentType) {
        this.componentType = componentType;
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return this.list.size();
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object o) {
        return this.list.contains(o);
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<Object> iterator() {
        return this.list.iterator();
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return this.list.toArray();
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] a) {
        return (T[]) this.list.toArray(a);
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(Object e) {
        return this.list.add(e);
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object o) {
        return this.list.remove(o);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> c) {
        return this.list.containsAll(c);
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends Object> c) {
        return this.list.addAll(c);
    }

    @Override // java.util.List
    public boolean addAll(int index, Collection<? extends Object> c) {
        return this.list.addAll(index, c);
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> c) {
        return this.list.removeAll(c);
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> c) {
        return this.list.retainAll(c);
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        this.list.clear();
    }

    @Override // java.util.List
    public Object set(int index, Object element) {
        return this.list.set(index, element);
    }

    @Override // java.util.List
    public void add(int index, Object element) {
        this.list.add(index, element);
    }

    @Override // java.util.List
    public Object remove(int index) {
        return this.list.remove(index);
    }

    @Override // java.util.List
    public int indexOf(Object o) {
        return this.list.indexOf(o);
    }

    @Override // java.util.List
    public int lastIndexOf(Object o) {
        return this.list.lastIndexOf(o);
    }

    @Override // java.util.List
    public ListIterator<Object> listIterator() {
        return this.list.listIterator();
    }

    @Override // java.util.List
    public ListIterator<Object> listIterator(int index) {
        return this.list.listIterator(index);
    }

    @Override // java.util.List
    public List<Object> subList(int fromIndex, int toIndex) {
        return this.list.subList(fromIndex, toIndex);
    }

    @Override // java.util.List
    public Object get(int index) {
        return this.list.get(index);
    }

    public JSONObject getJSONObject(int index) {
        Object value = this.list.get(index);
        if (value instanceof JSONObject) {
            return (JSONObject) value;
        }
        return (JSONObject) toJSON(value);
    }

    public JSONArray getJSONArray(int index) {
        Object value = this.list.get(index);
        if (value instanceof JSONArray) {
            return (JSONArray) value;
        }
        return (JSONArray) toJSON(value);
    }

    public <T> T getObject(int index, Class<T> clazz) {
        return (T) TypeUtils.castToJavaBean(this.list.get(index), clazz);
    }

    public Boolean getBoolean(int index) {
        Object value = get(index);
        if (value == null) {
            return null;
        }
        return TypeUtils.castToBoolean(value);
    }

    public boolean getBooleanValue(int index) {
        Object value = get(index);
        if (value == null) {
            return false;
        }
        return TypeUtils.castToBoolean(value).booleanValue();
    }

    public Byte getByte(int index) {
        return TypeUtils.castToByte(get(index));
    }

    public byte getByteValue(int index) {
        Object value = get(index);
        if (value == null) {
            return (byte) 0;
        }
        return TypeUtils.castToByte(value).byteValue();
    }

    public Short getShort(int index) {
        return TypeUtils.castToShort(get(index));
    }

    public short getShortValue(int index) {
        Object value = get(index);
        if (value == null) {
            return (short) 0;
        }
        return TypeUtils.castToShort(value).shortValue();
    }

    public Integer getInteger(int index) {
        return TypeUtils.castToInt(get(index));
    }

    public int getIntValue(int index) {
        Object value = get(index);
        if (value == null) {
            return 0;
        }
        return TypeUtils.castToInt(value).intValue();
    }

    public Long getLong(int index) {
        return TypeUtils.castToLong(get(index));
    }

    public long getLongValue(int index) {
        Object value = get(index);
        if (value == null) {
            return 0L;
        }
        return TypeUtils.castToLong(value).longValue();
    }

    public Float getFloat(int index) {
        return TypeUtils.castToFloat(get(index));
    }

    public float getFloatValue(int index) {
        Object value = get(index);
        if (value == null) {
            return 0.0f;
        }
        return TypeUtils.castToFloat(value).floatValue();
    }

    public Double getDouble(int index) {
        return TypeUtils.castToDouble(get(index));
    }

    public double getDoubleValue(int index) {
        Object value = get(index);
        if (value == null) {
            return 0.0d;
        }
        return TypeUtils.castToDouble(value).doubleValue();
    }

    public BigDecimal getBigDecimal(int index) {
        return TypeUtils.castToBigDecimal(get(index));
    }

    public BigInteger getBigInteger(int index) {
        return TypeUtils.castToBigInteger(get(index));
    }

    public String getString(int index) {
        return TypeUtils.castToString(get(index));
    }

    public Date getDate(int index) {
        return TypeUtils.castToDate(get(index));
    }

    public java.sql.Date getSqlDate(int index) {
        return TypeUtils.castToSqlDate(get(index));
    }

    public Timestamp getTimestamp(int index) {
        return TypeUtils.castToTimestamp(get(index));
    }

    public Object clone() {
        return new JSONArray(new ArrayList(this.list));
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        return this.list.equals(obj);
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        return this.list.hashCode();
    }
}

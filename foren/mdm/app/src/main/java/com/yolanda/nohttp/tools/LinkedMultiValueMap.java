package com.yolanda.nohttp.tools;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public class LinkedMultiValueMap<K, V> implements MultiValueMap<K, V> {
    protected Map<K, List<V>> mSource = new LinkedHashMap();

    @Override // com.yolanda.nohttp.tools.MultiValueMap
    public void add(K key, V value) {
        if (key != null) {
            if (!this.mSource.containsKey(key)) {
                this.mSource.put(key, new ArrayList(2));
            }
            this.mSource.get(key).add(value);
        }
    }

    @Override // com.yolanda.nohttp.tools.MultiValueMap
    public void add(K key, List<V> values) {
        for (V value : values) {
            add((LinkedMultiValueMap<K, V>) key, (K) value);
        }
    }

    @Override // com.yolanda.nohttp.tools.MultiValueMap
    public void set(K key, V value) {
        this.mSource.remove(key);
        add((LinkedMultiValueMap<K, V>) key, (K) value);
    }

    @Override // com.yolanda.nohttp.tools.MultiValueMap
    public void set(K key, List<V> values) {
        this.mSource.remove(key);
        add((LinkedMultiValueMap<K, V>) key, (List) values);
    }

    @Override // com.yolanda.nohttp.tools.MultiValueMap
    public void set(Map<K, List<V>> map) {
        this.mSource.clear();
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            add((LinkedMultiValueMap<K, V>) entry.getKey(), (List) entry.getValue());
        }
    }

    @Override // com.yolanda.nohttp.tools.MultiValueMap
    public void remove(K key) {
        this.mSource.remove(key);
    }

    @Override // com.yolanda.nohttp.tools.MultiValueMap
    public void clear() {
        this.mSource.clear();
    }

    @Override // com.yolanda.nohttp.tools.MultiValueMap
    public Set<K> keySet() {
        return this.mSource.keySet();
    }

    @Override // com.yolanda.nohttp.tools.MultiValueMap
    public List<V> values() {
        List<V> allValues = new ArrayList<>();
        for (K key : this.mSource.keySet()) {
            allValues.addAll(this.mSource.get(key));
        }
        return allValues;
    }

    @Override // com.yolanda.nohttp.tools.MultiValueMap
    public List<V> getValues(K key) {
        return this.mSource.get(key);
    }

    @Override // com.yolanda.nohttp.tools.MultiValueMap
    public V getValue(K key, int index) {
        List<V> values = this.mSource.get(key);
        if (values == null || index >= values.size()) {
            return null;
        }
        return values.get(index);
    }

    @Override // com.yolanda.nohttp.tools.MultiValueMap
    public int size() {
        return this.mSource.size();
    }

    @Override // com.yolanda.nohttp.tools.MultiValueMap
    public boolean isEmpty() {
        return this.mSource.isEmpty();
    }

    @Override // com.yolanda.nohttp.tools.MultiValueMap
    public boolean containsKey(K key) {
        return this.mSource.containsKey(key);
    }
}

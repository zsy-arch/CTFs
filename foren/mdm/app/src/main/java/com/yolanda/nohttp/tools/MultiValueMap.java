package com.yolanda.nohttp.tools;

import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public interface MultiValueMap<K, V> {
    void add(K k, V v);

    void add(K k, List<V> list);

    void clear();

    boolean containsKey(K k);

    V getValue(K k, int i);

    List<V> getValues(K k);

    boolean isEmpty();

    Set<K> keySet();

    void remove(K k);

    void set(K k, V v);

    void set(K k, List<V> list);

    void set(Map<K, List<V>> map);

    int size();

    List<V> values();
}

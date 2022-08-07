package com.parse;

import java.util.AbstractList;
import java.util.List;

/* loaded from: classes2.dex */
class Lists {
    Lists() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> List<List<T>> partition(List<T> list, int size) {
        return new Partition(list, size);
    }

    /* loaded from: classes2.dex */
    private static class Partition<T> extends AbstractList<List<T>> {
        private final List<T> list;
        private final int size;

        public Partition(List<T> list, int size) {
            this.list = list;
            this.size = size;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<T> get(int location) {
            int start = location * this.size;
            return this.list.subList(start, Math.min(this.size + start, this.list.size()));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return (int) Math.ceil(this.list.size() / this.size);
        }
    }
}

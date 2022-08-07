package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;

/* loaded from: classes.dex */
public class SymbolTable {
    public static final int DEFAULT_TABLE_SIZE = 512;
    public static final int MAX_BUCKET_LENTH = 8;
    public static final int MAX_SIZE = 4096;
    private final Entry[] buckets;
    private final int indexMask;
    private int size;
    private final String[] symbols;
    private final char[][] symbols_char;

    public SymbolTable() {
        this(512);
        addSymbol("$ref", 0, 4, "$ref".hashCode());
        addSymbol(JSON.DEFAULT_TYPE_KEY, 0, 5, JSON.DEFAULT_TYPE_KEY.hashCode());
    }

    public SymbolTable(int tableSize) {
        this.size = 0;
        this.indexMask = tableSize - 1;
        this.buckets = new Entry[tableSize];
        this.symbols = new String[tableSize];
        this.symbols_char = new char[tableSize];
    }

    public String addSymbol(char[] buffer, int offset, int len) {
        return addSymbol(buffer, offset, len, hash(buffer, offset, len));
    }

    public String addSymbol(char[] buffer, int offset, int len, int hash) {
        int bucket = hash & this.indexMask;
        String sym = this.symbols[bucket];
        boolean match = true;
        if (sym != null) {
            if (sym.length() == len) {
                char[] characters = this.symbols_char[bucket];
                int i = 0;
                while (true) {
                    if (i >= len) {
                        break;
                    } else if (buffer[offset + i] != characters[i]) {
                        match = false;
                        break;
                    } else {
                        i++;
                    }
                }
                if (match) {
                    return sym;
                }
            } else {
                match = false;
            }
        }
        int entryIndex = 0;
        for (Entry entry = this.buckets[bucket]; entry != null; entry = entry.next) {
            char[] characters2 = entry.characters;
            if (len == characters2.length && hash == entry.hashCode) {
                boolean eq = true;
                int i2 = 0;
                while (true) {
                    if (i2 >= len) {
                        break;
                    } else if (buffer[offset + i2] != characters2[i2]) {
                        eq = false;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (eq) {
                    return entry.symbol;
                }
                entryIndex++;
            }
        }
        if (entryIndex >= 8) {
            return new String(buffer, offset, len);
        }
        if (this.size >= 4096) {
            return new String(buffer, offset, len);
        }
        Entry entry2 = new Entry(buffer, offset, len, hash, this.buckets[bucket]);
        this.buckets[bucket] = entry2;
        if (match) {
            this.symbols[bucket] = entry2.symbol;
            this.symbols_char[bucket] = entry2.characters;
        }
        this.size++;
        return entry2.symbol;
    }

    public String addSymbol(String buffer, int offset, int len, int hash) {
        int bucket = hash & this.indexMask;
        String sym = this.symbols[bucket];
        boolean match = true;
        if (sym != null) {
            if (sym.length() == len) {
                char[] characters = this.symbols_char[bucket];
                int i = 0;
                while (true) {
                    if (i >= len) {
                        break;
                    } else if (buffer.charAt(offset + i) != characters[i]) {
                        match = false;
                        break;
                    } else {
                        i++;
                    }
                }
                if (match) {
                    return sym;
                }
            } else {
                match = false;
            }
        }
        int entryIndex = 0;
        for (Entry entry = this.buckets[bucket]; entry != null; entry = entry.next) {
            char[] characters2 = entry.characters;
            if (len == characters2.length && hash == entry.hashCode) {
                boolean eq = true;
                int i2 = 0;
                while (true) {
                    if (i2 >= len) {
                        break;
                    } else if (buffer.charAt(offset + i2) != characters2[i2]) {
                        eq = false;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (eq) {
                    return entry.symbol;
                }
                entryIndex++;
            }
        }
        if (entryIndex >= 8) {
            return subString(buffer, offset, len);
        }
        if (this.size >= 4096) {
            return subString(buffer, offset, len);
        }
        Entry entry2 = new Entry(buffer, offset, len, hash, this.buckets[bucket]);
        this.buckets[bucket] = entry2;
        if (match) {
            this.symbols[bucket] = entry2.symbol;
            this.symbols_char[bucket] = entry2.characters;
        }
        this.size++;
        return entry2.symbol;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String subString(String src, int offset, int len) {
        char[] chars = new char[len];
        for (int i = offset; i < offset + len; i++) {
            chars[i - offset] = src.charAt(i);
        }
        return new String(chars);
    }

    public int size() {
        return this.size;
    }

    public static final int hash(char[] buffer, int offset, int len) {
        int h = 0;
        int i = 0;
        int off = offset;
        while (i < len) {
            h = (h * 31) + buffer[off];
            i++;
            off++;
        }
        return h;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static final class Entry {
        public final byte[] bytes = null;
        public final char[] characters;
        public final int hashCode;
        public Entry next;
        public final String symbol;

        public Entry(char[] ch, int offset, int length, int hash, Entry next) {
            this.characters = new char[length];
            System.arraycopy(ch, offset, this.characters, 0, length);
            this.symbol = new String(this.characters).intern();
            this.next = next;
            this.hashCode = hash;
        }

        public Entry(String text, int offset, int length, int hash, Entry next) {
            this.symbol = SymbolTable.subString(text, offset, length).intern();
            this.characters = this.symbol.toCharArray();
            this.next = next;
            this.hashCode = hash;
        }
    }
}

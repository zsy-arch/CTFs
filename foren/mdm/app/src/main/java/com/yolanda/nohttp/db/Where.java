package com.yolanda.nohttp.db;

import java.util.List;

/* loaded from: classes2.dex */
public class Where {
    private StringBuilder builder = new StringBuilder();

    /* loaded from: classes2.dex */
    public enum Options {
        IN("IN"),
        EQUAL("="),
        NO_EQUAL("!="),
        ThAN_LARGE(">"),
        THAN_SMALL("<");
        
        private String value;

        Options(String value) {
            this.value = value;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return this.value;
        }
    }

    public Where() {
    }

    public Where(CharSequence columnName, Options op, Object value) {
        add(columnName, op, value);
    }

    public final Where clear() {
        this.builder.delete(0, this.builder.length());
        return this;
    }

    public final Where append(Object row) {
        this.builder.append(row);
        return this;
    }

    public final Where set(String row) {
        clear().append(row);
        return this;
    }

    public final Where isNull(CharSequence columnName) {
        this.builder.append("\"").append(columnName).append("\" ").append("IS ").append("NULL");
        return this;
    }

    private Where addColumnName(CharSequence columnName, Options op) {
        this.builder.append("\"").append(columnName).append("\" ").append(op.toString()).append(' ');
        return this;
    }

    public final Where add(CharSequence columnName, Options op, Object value) {
        if (Options.EQUAL.equals(op) || Options.ThAN_LARGE.equals(op) || Options.THAN_SMALL.equals(op) || Options.NO_EQUAL.equals(op)) {
            addColumnName(columnName, op);
            if (isNumber(value)) {
                this.builder.append(value);
            } else {
                this.builder.append("'").append(value).append("'");
            }
        } else if (!Options.IN.equals(op) || !(value instanceof List)) {
            throw new IllegalArgumentException("Value is not supported by the data type");
        } else {
            addColumnName(columnName, op).append(value).in((List) value);
        }
        return this;
    }

    private <T> Where in(List<T> values) {
        this.builder.append(Options.IN).append(" (");
        for (T value : values) {
            if (value instanceof CharSequence) {
                this.builder.append("'").append(value).append("'");
            } else if ((value instanceof Integer) || (value instanceof Long) || (value instanceof Short)) {
                this.builder.append(value);
            }
            this.builder.append(", ");
        }
        if (this.builder.lastIndexOf(", ") > 0) {
            this.builder.delete(this.builder.length() - 2, this.builder.length());
        }
        this.builder.append(")");
        return this;
    }

    private Where and() {
        if (this.builder.length() > 0) {
            this.builder.append(" AND ");
        }
        return this;
    }

    public final Where and(CharSequence columnName, Options op, Object value) {
        return and().add(columnName, op, value);
    }

    public final Where andNull(CharSequence columnName) {
        return and().isNull(columnName);
    }

    public final Where and(Where where) {
        return and().append(where);
    }

    private Where or() {
        if (this.builder.length() > 0) {
            this.builder.append(" OR ");
        }
        return this;
    }

    public final Where or(CharSequence columnName, Options op, Object value) {
        return or().add(columnName, op, value);
    }

    public final Where orNull(CharSequence columnName) {
        return or().isNull(columnName);
    }

    public final Where or(Where where) {
        return or().append(where);
    }

    public final Where bracket() {
        return insert(0, "(").append(')');
    }

    public final Where insert(int index, CharSequence s) {
        this.builder.insert(index, s);
        return this;
    }

    public final String get() {
        return this.builder.toString();
    }

    public String toString() {
        return this.builder.toString();
    }

    public static boolean isNumber(Object value) {
        return value != null && ((value instanceof Character) || (value instanceof Integer) || (value instanceof Long) || (value instanceof Short) || (value instanceof Double) || (value instanceof Float));
    }
}

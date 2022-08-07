package com.lidroid.xutils.db.sqlite;

import android.text.TextUtils;
import com.hyphenate.util.HanziToPinyin;
import com.lidroid.xutils.db.converter.ColumnConverterFactory;
import com.lidroid.xutils.db.table.ColumnUtils;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class WhereBuilder {
    private final List<String> whereItems = new ArrayList();

    private WhereBuilder() {
    }

    public static WhereBuilder b() {
        return new WhereBuilder();
    }

    public static WhereBuilder b(String columnName, String op, Object value) {
        WhereBuilder result = new WhereBuilder();
        result.appendCondition(null, columnName, op, value);
        return result;
    }

    public WhereBuilder and(String columnName, String op, Object value) {
        appendCondition(this.whereItems.size() == 0 ? null : "AND", columnName, op, value);
        return this;
    }

    public WhereBuilder or(String columnName, String op, Object value) {
        appendCondition(this.whereItems.size() == 0 ? null : "OR", columnName, op, value);
        return this;
    }

    public WhereBuilder expr(String expr) {
        this.whereItems.add(HanziToPinyin.Token.SEPARATOR + expr);
        return this;
    }

    public WhereBuilder expr(String columnName, String op, Object value) {
        appendCondition(null, columnName, op, value);
        return this;
    }

    public int getWhereItemSize() {
        return this.whereItems.size();
    }

    public String toString() {
        if (this.whereItems.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String item : this.whereItems) {
            sb.append(item);
        }
        return sb.toString();
    }

    private void appendCondition(String conj, String columnName, String op, Object value) {
        StringBuilder sqlSb = new StringBuilder();
        if (this.whereItems.size() > 0) {
            sqlSb.append(HanziToPinyin.Token.SEPARATOR);
        }
        if (!TextUtils.isEmpty(conj)) {
            sqlSb.append(conj + HanziToPinyin.Token.SEPARATOR);
        }
        sqlSb.append(columnName);
        if ("!=".equals(op)) {
            op = "<>";
        } else if ("==".equals(op)) {
            op = "=";
        }
        if (value != null) {
            sqlSb.append(HanziToPinyin.Token.SEPARATOR + op + HanziToPinyin.Token.SEPARATOR);
            if ("IN".equalsIgnoreCase(op)) {
                Iterable<?> items = null;
                if (value instanceof Iterable) {
                    items = (Iterable) value;
                } else if (value.getClass().isArray()) {
                    ArrayList<Object> arrayList = new ArrayList<>();
                    int len = Array.getLength(value);
                    for (int i = 0; i < len; i++) {
                        arrayList.add(Array.get(value, i));
                    }
                    items = arrayList;
                }
                if (items != null) {
                    StringBuffer stringBuffer = new StringBuffer("(");
                    Iterator<?> it = items.iterator();
                    while (it.hasNext()) {
                        Object itemColValue = ColumnUtils.convert2DbColumnValueIfNeeded(it.next());
                        if (ColumnDbType.TEXT.equals(ColumnConverterFactory.getDbColumnType(itemColValue.getClass()))) {
                            String valueStr = itemColValue.toString();
                            if (valueStr.indexOf(39) != -1) {
                                valueStr = valueStr.replace("'", "''");
                            }
                            stringBuffer.append("'" + valueStr + "'");
                        } else {
                            stringBuffer.append(itemColValue);
                        }
                        stringBuffer.append(",");
                    }
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                    stringBuffer.append(")");
                    sqlSb.append(stringBuffer.toString());
                } else {
                    throw new IllegalArgumentException("value must be an Array or an Iterable.");
                }
            } else if ("BETWEEN".equalsIgnoreCase(op)) {
                Iterable<?> items2 = null;
                if (value instanceof Iterable) {
                    items2 = (Iterable) value;
                } else if (value.getClass().isArray()) {
                    ArrayList<Object> arrayList2 = new ArrayList<>();
                    int len2 = Array.getLength(value);
                    for (int i2 = 0; i2 < len2; i2++) {
                        arrayList2.add(Array.get(value, i2));
                    }
                    items2 = arrayList2;
                }
                if (items2 != null) {
                    Iterator<?> iterator = items2.iterator();
                    if (!iterator.hasNext()) {
                        throw new IllegalArgumentException("value must have tow items.");
                    }
                    Object start = iterator.next();
                    if (!iterator.hasNext()) {
                        throw new IllegalArgumentException("value must have tow items.");
                    }
                    Object end = iterator.next();
                    Object startColValue = ColumnUtils.convert2DbColumnValueIfNeeded(start);
                    Object endColValue = ColumnUtils.convert2DbColumnValueIfNeeded(end);
                    if (ColumnDbType.TEXT.equals(ColumnConverterFactory.getDbColumnType(startColValue.getClass()))) {
                        String startStr = startColValue.toString();
                        if (startStr.indexOf(39) != -1) {
                            startStr = startStr.replace("'", "''");
                        }
                        String endStr = endColValue.toString();
                        if (endStr.indexOf(39) != -1) {
                            endStr = endStr.replace("'", "''");
                        }
                        sqlSb.append("'" + startStr + "'");
                        sqlSb.append(" AND ");
                        sqlSb.append("'" + endStr + "'");
                    } else {
                        sqlSb.append(startColValue);
                        sqlSb.append(" AND ");
                        sqlSb.append(endColValue);
                    }
                } else {
                    throw new IllegalArgumentException("value must be an Array or an Iterable.");
                }
            } else {
                Object value2 = ColumnUtils.convert2DbColumnValueIfNeeded(value);
                if (ColumnDbType.TEXT.equals(ColumnConverterFactory.getDbColumnType(value2.getClass()))) {
                    String valueStr2 = value2.toString();
                    if (valueStr2.indexOf(39) != -1) {
                        valueStr2 = valueStr2.replace("'", "''");
                    }
                    sqlSb.append("'" + valueStr2 + "'");
                } else {
                    sqlSb.append(value2);
                }
            }
        } else if ("=".equals(op)) {
            sqlSb.append(" IS NULL");
        } else if ("<>".equals(op)) {
            sqlSb.append(" IS NOT NULL");
        } else {
            sqlSb.append(HanziToPinyin.Token.SEPARATOR + op + " NULL");
        }
        this.whereItems.add(sqlSb.toString());
    }
}

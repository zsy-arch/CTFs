package com.parse;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import bolts.Task;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ParseSQLiteCursor implements Cursor {
    private Cursor cursor;
    private Executor executor;

    public static Cursor create(Cursor cursor, Executor executor) {
        return Build.VERSION.SDK_INT >= 14 ? cursor : new ParseSQLiteCursor(cursor, executor);
    }

    private ParseSQLiteCursor(Cursor cursor, Executor executor) {
        this.cursor = cursor;
        this.executor = executor;
    }

    @Override // android.database.Cursor
    public int getCount() {
        return this.cursor.getCount();
    }

    @Override // android.database.Cursor
    public int getPosition() {
        return this.cursor.getPosition();
    }

    @Override // android.database.Cursor
    public boolean move(int offset) {
        return this.cursor.move(offset);
    }

    @Override // android.database.Cursor
    public boolean moveToPosition(int position) {
        return this.cursor.moveToPosition(position);
    }

    @Override // android.database.Cursor
    public boolean moveToFirst() {
        return this.cursor.moveToFirst();
    }

    @Override // android.database.Cursor
    public boolean moveToLast() {
        return this.cursor.moveToLast();
    }

    @Override // android.database.Cursor
    public boolean moveToNext() {
        return this.cursor.moveToNext();
    }

    @Override // android.database.Cursor
    public boolean moveToPrevious() {
        return this.cursor.moveToPrevious();
    }

    @Override // android.database.Cursor
    public boolean isFirst() {
        return this.cursor.isFirst();
    }

    @Override // android.database.Cursor
    public boolean isLast() {
        return this.cursor.isLast();
    }

    @Override // android.database.Cursor
    public boolean isBeforeFirst() {
        return this.cursor.isBeforeFirst();
    }

    @Override // android.database.Cursor
    public boolean isAfterLast() {
        return this.cursor.isAfterLast();
    }

    @Override // android.database.Cursor
    public int getColumnIndex(String columnName) {
        return this.cursor.getColumnIndex(columnName);
    }

    @Override // android.database.Cursor
    public int getColumnIndexOrThrow(String columnName) throws IllegalArgumentException {
        return this.cursor.getColumnIndexOrThrow(columnName);
    }

    @Override // android.database.Cursor
    public String getColumnName(int columnIndex) {
        return this.cursor.getColumnName(columnIndex);
    }

    @Override // android.database.Cursor
    public String[] getColumnNames() {
        return this.cursor.getColumnNames();
    }

    @Override // android.database.Cursor
    public int getColumnCount() {
        return this.cursor.getColumnCount();
    }

    @Override // android.database.Cursor
    public byte[] getBlob(int columnIndex) {
        return this.cursor.getBlob(columnIndex);
    }

    @Override // android.database.Cursor
    public String getString(int columnIndex) {
        return this.cursor.getString(columnIndex);
    }

    @Override // android.database.Cursor
    public void copyStringToBuffer(int columnIndex, CharArrayBuffer buffer) {
        this.cursor.copyStringToBuffer(columnIndex, buffer);
    }

    @Override // android.database.Cursor
    public short getShort(int columnIndex) {
        return this.cursor.getShort(columnIndex);
    }

    @Override // android.database.Cursor
    public int getInt(int columnIndex) {
        return this.cursor.getInt(columnIndex);
    }

    @Override // android.database.Cursor
    public long getLong(int columnIndex) {
        return this.cursor.getLong(columnIndex);
    }

    @Override // android.database.Cursor
    public float getFloat(int columnIndex) {
        return this.cursor.getFloat(columnIndex);
    }

    @Override // android.database.Cursor
    public double getDouble(int columnIndex) {
        return this.cursor.getDouble(columnIndex);
    }

    @Override // android.database.Cursor
    @TargetApi(11)
    public int getType(int columnIndex) {
        return this.cursor.getType(columnIndex);
    }

    @Override // android.database.Cursor
    public boolean isNull(int columnIndex) {
        return this.cursor.isNull(columnIndex);
    }

    @Override // android.database.Cursor
    @Deprecated
    public void deactivate() {
        this.cursor.deactivate();
    }

    @Override // android.database.Cursor
    @Deprecated
    public boolean requery() {
        return this.cursor.requery();
    }

    @Override // android.database.Cursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Task.call(new Callable<Void>() { // from class: com.parse.ParseSQLiteCursor.1
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                ParseSQLiteCursor.this.cursor.close();
                return null;
            }
        }, this.executor);
    }

    @Override // android.database.Cursor
    public boolean isClosed() {
        return this.cursor.isClosed();
    }

    @Override // android.database.Cursor
    public void registerContentObserver(ContentObserver observer) {
        this.cursor.registerContentObserver(observer);
    }

    @Override // android.database.Cursor
    public void unregisterContentObserver(ContentObserver observer) {
        this.cursor.unregisterContentObserver(observer);
    }

    @Override // android.database.Cursor
    public void registerDataSetObserver(DataSetObserver observer) {
        this.cursor.registerDataSetObserver(observer);
    }

    @Override // android.database.Cursor
    public void unregisterDataSetObserver(DataSetObserver observer) {
        this.cursor.unregisterDataSetObserver(observer);
    }

    @Override // android.database.Cursor
    public void setNotificationUri(ContentResolver cr, Uri uri) {
        this.cursor.setNotificationUri(cr, uri);
    }

    @Override // android.database.Cursor
    @TargetApi(19)
    public Uri getNotificationUri() {
        return this.cursor.getNotificationUri();
    }

    @Override // android.database.Cursor
    public boolean getWantsAllOnMoveCalls() {
        return this.cursor.getWantsAllOnMoveCalls();
    }

    @Override // android.database.Cursor
    public Bundle getExtras() {
        return this.cursor.getExtras();
    }

    @Override // android.database.Cursor
    public Bundle respond(Bundle extras) {
        return this.cursor.respond(extras);
    }
}

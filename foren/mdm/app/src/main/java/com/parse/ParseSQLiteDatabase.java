package com.parse;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class ParseSQLiteDatabase {
    private static final ExecutorService dbExecutor = Executors.newSingleThreadExecutor();
    private static final TaskQueue taskQueue = new TaskQueue();
    private SQLiteDatabase db;
    private int openFlags;
    private Task<Void> current = null;
    private final Object currentLock = new Object();
    private final TaskCompletionSource<Void> tcs = new TaskCompletionSource<>();

    public static Task<ParseSQLiteDatabase> openDatabaseAsync(SQLiteOpenHelper helper, int flags) {
        ParseSQLiteDatabase db = new ParseSQLiteDatabase(flags);
        return db.open(helper).continueWithTask(new Continuation<Void, Task<ParseSQLiteDatabase>>() { // from class: com.parse.ParseSQLiteDatabase.1
            @Override // bolts.Continuation
            public Task<ParseSQLiteDatabase> then(Task<Void> task) throws Exception {
                return Task.forResult(ParseSQLiteDatabase.this);
            }
        });
    }

    private ParseSQLiteDatabase(int flags) {
        this.openFlags = flags;
        taskQueue.enqueue(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseSQLiteDatabase.2
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> toAwait) throws Exception {
                synchronized (ParseSQLiteDatabase.this.currentLock) {
                    ParseSQLiteDatabase.this.current = toAwait;
                }
                return ParseSQLiteDatabase.this.tcs.getTask();
            }
        });
    }

    public Task<Boolean> isReadOnlyAsync() {
        Task continueWith;
        synchronized (this.currentLock) {
            continueWith = this.current.continueWith(new Continuation<Void, Boolean>() { // from class: com.parse.ParseSQLiteDatabase.3
                @Override // bolts.Continuation
                public Boolean then(Task<Void> task) throws Exception {
                    return Boolean.valueOf(ParseSQLiteDatabase.this.db.isReadOnly());
                }
            });
            this.current = continueWith.makeVoid();
        }
        return continueWith;
    }

    public Task<Boolean> isOpenAsync() {
        Task continueWith;
        synchronized (this.currentLock) {
            continueWith = this.current.continueWith(new Continuation<Void, Boolean>() { // from class: com.parse.ParseSQLiteDatabase.4
                @Override // bolts.Continuation
                public Boolean then(Task<Void> task) throws Exception {
                    return Boolean.valueOf(ParseSQLiteDatabase.this.db.isOpen());
                }
            });
            this.current = continueWith.makeVoid();
        }
        return continueWith;
    }

    public boolean inTransaction() {
        return this.db.inTransaction();
    }

    Task<Void> open(final SQLiteOpenHelper helper) {
        Task<Void> task;
        synchronized (this.currentLock) {
            this.current = this.current.continueWith(new Continuation<Void, SQLiteDatabase>() { // from class: com.parse.ParseSQLiteDatabase.6
                @Override // bolts.Continuation
                public SQLiteDatabase then(Task<Void> task2) throws Exception {
                    return (ParseSQLiteDatabase.this.openFlags & 1) == 1 ? helper.getReadableDatabase() : helper.getWritableDatabase();
                }
            }, dbExecutor).continueWithTask((Continuation<TContinuationResult, Task<TContinuationResult>>) new Continuation<SQLiteDatabase, Task<Void>>() { // from class: com.parse.ParseSQLiteDatabase.5
                @Override // bolts.Continuation
                public Task<Void> then(Task<SQLiteDatabase> task2) throws Exception {
                    ParseSQLiteDatabase.this.db = task2.getResult();
                    return task2.makeVoid();
                }
            }, (Executor) Task.BACKGROUND_EXECUTOR);
            task = this.current;
        }
        return task;
    }

    public Task<Void> beginTransactionAsync() {
        Task continueWithTask;
        synchronized (this.currentLock) {
            this.current = this.current.continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseSQLiteDatabase.7
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task) throws Exception {
                    ParseSQLiteDatabase.this.db.beginTransaction();
                    return task;
                }
            }, dbExecutor);
            continueWithTask = this.current.continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseSQLiteDatabase.8
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task) throws Exception {
                    return task;
                }
            }, Task.BACKGROUND_EXECUTOR);
        }
        return continueWithTask;
    }

    public Task<Void> setTransactionSuccessfulAsync() {
        Task continueWithTask;
        synchronized (this.currentLock) {
            this.current = this.current.onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseSQLiteDatabase.9
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task) throws Exception {
                    ParseSQLiteDatabase.this.db.setTransactionSuccessful();
                    return task;
                }
            }, dbExecutor);
            continueWithTask = this.current.continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseSQLiteDatabase.10
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task) throws Exception {
                    return task;
                }
            }, Task.BACKGROUND_EXECUTOR);
        }
        return continueWithTask;
    }

    public Task<Void> endTransactionAsync() {
        Task continueWithTask;
        synchronized (this.currentLock) {
            this.current = this.current.continueWith(new Continuation<Void, Void>() { // from class: com.parse.ParseSQLiteDatabase.11
                @Override // bolts.Continuation
                public Void then(Task<Void> task) throws Exception {
                    ParseSQLiteDatabase.this.db.endTransaction();
                    return null;
                }
            }, dbExecutor);
            continueWithTask = this.current.continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseSQLiteDatabase.12
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task) throws Exception {
                    return task;
                }
            }, Task.BACKGROUND_EXECUTOR);
        }
        return continueWithTask;
    }

    public Task<Void> closeAsync() {
        Task continueWithTask;
        synchronized (this.currentLock) {
            this.current = this.current.continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseSQLiteDatabase.13
                /* JADX WARN: Finally extract failed */
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task) throws Exception {
                    try {
                        ParseSQLiteDatabase.this.db.close();
                        ParseSQLiteDatabase.this.tcs.setResult(null);
                        return ParseSQLiteDatabase.this.tcs.getTask();
                    } catch (Throwable th) {
                        ParseSQLiteDatabase.this.tcs.setResult(null);
                        throw th;
                    }
                }
            }, dbExecutor);
            continueWithTask = this.current.continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseSQLiteDatabase.14
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task) throws Exception {
                    return task;
                }
            }, Task.BACKGROUND_EXECUTOR);
        }
        return continueWithTask;
    }

    public Task<Cursor> queryAsync(final String table, final String[] select, final String where, final String[] args) {
        Task<Cursor> continueWithTask;
        synchronized (this.currentLock) {
            Task onSuccess = this.current.onSuccess(new Continuation<Void, Cursor>() { // from class: com.parse.ParseSQLiteDatabase.16
                @Override // bolts.Continuation
                public Cursor then(Task<Void> task) throws Exception {
                    return ParseSQLiteDatabase.this.db.query(table, select, where, args, null, null, null);
                }
            }, dbExecutor).onSuccess((Continuation<TContinuationResult, TContinuationResult>) new Continuation<Cursor, Cursor>() { // from class: com.parse.ParseSQLiteDatabase.15
                @Override // bolts.Continuation
                public Cursor then(Task<Cursor> task) throws Exception {
                    Cursor cursor = ParseSQLiteCursor.create(task.getResult(), ParseSQLiteDatabase.dbExecutor);
                    cursor.getCount();
                    return cursor;
                }
            }, (Executor) dbExecutor);
            this.current = onSuccess.makeVoid();
            continueWithTask = onSuccess.continueWithTask(new Continuation<Cursor, Task<Cursor>>() { // from class: com.parse.ParseSQLiteDatabase.17
                @Override // bolts.Continuation
                public Task<Cursor> then(Task<Cursor> task) throws Exception {
                    return task;
                }
            }, Task.BACKGROUND_EXECUTOR);
        }
        return continueWithTask;
    }

    public Task<Void> insertWithOnConflict(final String table, final ContentValues values, final int conflictAlgorithm) {
        Task<Void> makeVoid;
        synchronized (this.currentLock) {
            Task<TContinuationResult> onSuccess = this.current.onSuccess(new Continuation<Void, Long>() { // from class: com.parse.ParseSQLiteDatabase.18
                @Override // bolts.Continuation
                public Long then(Task<Void> task) throws Exception {
                    return Long.valueOf(ParseSQLiteDatabase.this.db.insertWithOnConflict(table, null, values, conflictAlgorithm));
                }
            }, dbExecutor);
            this.current = onSuccess.makeVoid();
            makeVoid = onSuccess.continueWithTask((Continuation<TContinuationResult, Task<TContinuationResult>>) new Continuation<Long, Task<Long>>() { // from class: com.parse.ParseSQLiteDatabase.19
                @Override // bolts.Continuation
                public Task<Long> then(Task<Long> task) throws Exception {
                    return task;
                }
            }, (Executor) Task.BACKGROUND_EXECUTOR).makeVoid();
        }
        return makeVoid;
    }

    public Task<Void> insertOrThrowAsync(final String table, final ContentValues values) {
        Task<Void> makeVoid;
        synchronized (this.currentLock) {
            Task<TContinuationResult> onSuccess = this.current.onSuccess(new Continuation<Void, Long>() { // from class: com.parse.ParseSQLiteDatabase.20
                @Override // bolts.Continuation
                public Long then(Task<Void> task) throws Exception {
                    return Long.valueOf(ParseSQLiteDatabase.this.db.insertOrThrow(table, null, values));
                }
            }, dbExecutor);
            this.current = onSuccess.makeVoid();
            makeVoid = onSuccess.continueWithTask((Continuation<TContinuationResult, Task<TContinuationResult>>) new Continuation<Long, Task<Long>>() { // from class: com.parse.ParseSQLiteDatabase.21
                @Override // bolts.Continuation
                public Task<Long> then(Task<Long> task) throws Exception {
                    return task;
                }
            }, (Executor) Task.BACKGROUND_EXECUTOR).makeVoid();
        }
        return makeVoid;
    }

    public Task<Integer> updateAsync(final String table, final ContentValues values, final String where, final String[] args) {
        Task<Integer> continueWithTask;
        synchronized (this.currentLock) {
            Task<TContinuationResult> onSuccess = this.current.onSuccess(new Continuation<Void, Integer>() { // from class: com.parse.ParseSQLiteDatabase.22
                @Override // bolts.Continuation
                public Integer then(Task<Void> task) throws Exception {
                    return Integer.valueOf(ParseSQLiteDatabase.this.db.update(table, values, where, args));
                }
            }, dbExecutor);
            this.current = onSuccess.makeVoid();
            continueWithTask = onSuccess.continueWithTask((Continuation<TContinuationResult, Task<TContinuationResult>>) new Continuation<Integer, Task<Integer>>() { // from class: com.parse.ParseSQLiteDatabase.23
                @Override // bolts.Continuation
                public Task<Integer> then(Task<Integer> task) throws Exception {
                    return task;
                }
            }, (Executor) Task.BACKGROUND_EXECUTOR);
        }
        return continueWithTask;
    }

    public Task<Void> deleteAsync(final String table, final String where, final String[] args) {
        Task<Void> makeVoid;
        synchronized (this.currentLock) {
            Task<TContinuationResult> onSuccess = this.current.onSuccess(new Continuation<Void, Integer>() { // from class: com.parse.ParseSQLiteDatabase.24
                @Override // bolts.Continuation
                public Integer then(Task<Void> task) throws Exception {
                    return Integer.valueOf(ParseSQLiteDatabase.this.db.delete(table, where, args));
                }
            }, dbExecutor);
            this.current = onSuccess.makeVoid();
            makeVoid = onSuccess.continueWithTask((Continuation<TContinuationResult, Task<TContinuationResult>>) new Continuation<Integer, Task<Integer>>() { // from class: com.parse.ParseSQLiteDatabase.25
                @Override // bolts.Continuation
                public Task<Integer> then(Task<Integer> task) throws Exception {
                    return task;
                }
            }, (Executor) Task.BACKGROUND_EXECUTOR).makeVoid();
        }
        return makeVoid;
    }

    public Task<Cursor> rawQueryAsync(final String sql, final String[] args) {
        Task<Cursor> continueWithTask;
        synchronized (this.currentLock) {
            Task onSuccess = this.current.onSuccess(new Continuation<Void, Cursor>() { // from class: com.parse.ParseSQLiteDatabase.27
                @Override // bolts.Continuation
                public Cursor then(Task<Void> task) throws Exception {
                    return ParseSQLiteDatabase.this.db.rawQuery(sql, args);
                }
            }, dbExecutor).onSuccess((Continuation<TContinuationResult, TContinuationResult>) new Continuation<Cursor, Cursor>() { // from class: com.parse.ParseSQLiteDatabase.26
                @Override // bolts.Continuation
                public Cursor then(Task<Cursor> task) throws Exception {
                    Cursor cursor = ParseSQLiteCursor.create(task.getResult(), ParseSQLiteDatabase.dbExecutor);
                    cursor.getCount();
                    return cursor;
                }
            }, (Executor) dbExecutor);
            this.current = onSuccess.makeVoid();
            continueWithTask = onSuccess.continueWithTask(new Continuation<Cursor, Task<Cursor>>() { // from class: com.parse.ParseSQLiteDatabase.28
                @Override // bolts.Continuation
                public Task<Cursor> then(Task<Cursor> task) throws Exception {
                    return task;
                }
            }, Task.BACKGROUND_EXECUTOR);
        }
        return continueWithTask;
    }
}

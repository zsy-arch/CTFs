package com.parse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Pair;
import bolts.Capture;
import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import com.parse.OfflineQueryLogic;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class OfflineStore {
    private static final int MAX_SQL_VARIABLES = 999;
    private final WeakValueHashMap<Pair<String, String>, ParseObject> classNameAndObjectIdToObjectMap;
    private final WeakHashMap<ParseObject, Task<ParseObject>> fetchedObjects;
    private final OfflineSQLiteOpenHelper helper;
    private final Object lock;
    private final WeakHashMap<ParseObject, Task<String>> objectToUuidMap;
    private final WeakValueHashMap<String, ParseObject> uuidToObjectMap;

    /* loaded from: classes2.dex */
    public interface SQLiteDatabaseCallable<T> {
        T call(ParseSQLiteDatabase parseSQLiteDatabase);
    }

    /* loaded from: classes2.dex */
    public class OfflineDecoder extends ParseDecoder {
        private Map<String, Task<ParseObject>> offlineObjects;

        private OfflineDecoder(Map<String, Task<ParseObject>> offlineObjects) {
            OfflineStore.this = r1;
            this.offlineObjects = offlineObjects;
        }

        @Override // com.parse.ParseDecoder
        public Object decode(Object object) {
            if (!(object instanceof JSONObject) || !((JSONObject) object).optString("__type").equals("OfflineObject")) {
                return super.decode(object);
            }
            return this.offlineObjects.get(((JSONObject) object).optString("uuid")).getResult();
        }
    }

    /* loaded from: classes2.dex */
    public class OfflineEncoder extends ParseEncoder {
        private ParseSQLiteDatabase db;
        private ArrayList<Task<Void>> tasks = new ArrayList<>();
        private final Object tasksLock = new Object();

        public OfflineEncoder(ParseSQLiteDatabase db) {
            OfflineStore.this = r2;
            this.db = db;
        }

        public Task<Void> whenFinished() {
            return Task.whenAll(this.tasks).continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineStore.OfflineEncoder.1
                /* JADX WARN: Removed duplicated region for block: B:7:0x0017 A[Catch: all -> 0x003d, TryCatch #0 {, blocks: (B:4:0x0007, B:5:0x0011, B:7:0x0017, B:9:0x0023, B:11:0x0029, B:13:0x002b, B:14:0x003b), top: B:19:0x0007 }] */
                @Override // bolts.Continuation
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public bolts.Task<java.lang.Void> then(bolts.Task<java.lang.Void> r5) throws java.lang.Exception {
                    /*
                        r4 = this;
                        com.parse.OfflineStore$OfflineEncoder r2 = com.parse.OfflineStore.OfflineEncoder.this
                        java.lang.Object r3 = com.parse.OfflineStore.OfflineEncoder.access$000(r2)
                        monitor-enter(r3)
                        com.parse.OfflineStore$OfflineEncoder r2 = com.parse.OfflineStore.OfflineEncoder.this     // Catch: all -> 0x003d
                        java.util.ArrayList r2 = com.parse.OfflineStore.OfflineEncoder.access$100(r2)     // Catch: all -> 0x003d
                        java.util.Iterator r0 = r2.iterator()     // Catch: all -> 0x003d
                    L_0x0011:
                        boolean r2 = r0.hasNext()     // Catch: all -> 0x003d
                        if (r2 == 0) goto L_0x002b
                        java.lang.Object r1 = r0.next()     // Catch: all -> 0x003d
                        bolts.Task r1 = (bolts.Task) r1     // Catch: all -> 0x003d
                        boolean r2 = r1.isFaulted()     // Catch: all -> 0x003d
                        if (r2 != 0) goto L_0x0029
                        boolean r2 = r1.isCancelled()     // Catch: all -> 0x003d
                        if (r2 == 0) goto L_0x0011
                    L_0x0029:
                        monitor-exit(r3)     // Catch: all -> 0x003d
                    L_0x002a:
                        return r1
                    L_0x002b:
                        com.parse.OfflineStore$OfflineEncoder r2 = com.parse.OfflineStore.OfflineEncoder.this     // Catch: all -> 0x003d
                        java.util.ArrayList r2 = com.parse.OfflineStore.OfflineEncoder.access$100(r2)     // Catch: all -> 0x003d
                        r2.clear()     // Catch: all -> 0x003d
                        r2 = 0
                        java.lang.Void r2 = (java.lang.Void) r2     // Catch: all -> 0x003d
                        bolts.Task r1 = bolts.Task.forResult(r2)     // Catch: all -> 0x003d
                        monitor-exit(r3)     // Catch: all -> 0x003d
                        goto L_0x002a
                    L_0x003d:
                        r2 = move-exception
                        monitor-exit(r3)     // Catch: all -> 0x003d
                        throw r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.parse.OfflineStore.OfflineEncoder.AnonymousClass1.then(bolts.Task):bolts.Task");
                }
            });
        }

        @Override // com.parse.ParseEncoder
        public JSONObject encodeRelatedObject(ParseObject object) {
            try {
                if (object.getObjectId() != null) {
                    JSONObject result = new JSONObject();
                    result.put("__type", "Pointer");
                    result.put("objectId", object.getObjectId());
                    result.put("className", object.getClassName());
                    return result;
                }
                final JSONObject result2 = new JSONObject();
                result2.put("__type", "OfflineObject");
                synchronized (this.tasksLock) {
                    this.tasks.add(OfflineStore.this.getOrCreateUUIDAsync(object, this.db).onSuccess(new Continuation<String, Void>() { // from class: com.parse.OfflineStore.OfflineEncoder.2
                        @Override // bolts.Continuation
                        public Void then(Task<String> task) throws Exception {
                            result2.put("uuid", task.getResult());
                            return null;
                        }
                    }));
                }
                return result2;
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public OfflineStore(Context context) {
        this(new OfflineSQLiteOpenHelper(context));
    }

    OfflineStore(OfflineSQLiteOpenHelper helper) {
        this.lock = new Object();
        this.uuidToObjectMap = new WeakValueHashMap<>();
        this.objectToUuidMap = new WeakHashMap<>();
        this.fetchedObjects = new WeakHashMap<>();
        this.classNameAndObjectIdToObjectMap = new WeakValueHashMap<>();
        this.helper = helper;
    }

    public Task<String> getOrCreateUUIDAsync(final ParseObject object, ParseSQLiteDatabase db) {
        final String newUUID = UUID.randomUUID().toString();
        final TaskCompletionSource<String> tcs = new TaskCompletionSource<>();
        synchronized (this.lock) {
            Task<String> uuidTask = this.objectToUuidMap.get(object);
            if (uuidTask != null) {
                return uuidTask;
            }
            this.objectToUuidMap.put(object, tcs.getTask());
            this.uuidToObjectMap.put(newUUID, object);
            this.fetchedObjects.put(object, tcs.getTask().onSuccess(new Continuation<String, ParseObject>() { // from class: com.parse.OfflineStore.1
                @Override // bolts.Continuation
                public ParseObject then(Task<String> task) throws Exception {
                    return object;
                }
            }));
            ContentValues values = new ContentValues();
            values.put("uuid", newUUID);
            values.put("className", object.getClassName());
            db.insertOrThrowAsync("ParseObjects", values).continueWith(new Continuation<Void, Void>() { // from class: com.parse.OfflineStore.2
                @Override // bolts.Continuation
                public Void then(Task<Void> task) throws Exception {
                    tcs.setResult(newUUID);
                    return null;
                }
            });
            return tcs.getTask();
        }
    }

    public <T extends ParseObject> Task<T> getPointerAsync(final String uuid, ParseSQLiteDatabase db) {
        synchronized (this.lock) {
            ParseObject parseObject = this.uuidToObjectMap.get(uuid);
            if (parseObject == null) {
                return (Task<T>) db.queryAsync("ParseObjects", new String[]{"className", "objectId"}, "uuid = ?", new String[]{uuid}).onSuccess(new Continuation<Cursor, T>() { // from class: com.parse.OfflineStore.3
                    @Override // bolts.Continuation
                    /* renamed from: then */
                    public ParseObject then2(Task<Cursor> task) throws Exception {
                        Cursor cursor = task.getResult();
                        cursor.moveToFirst();
                        if (cursor.isAfterLast()) {
                            cursor.close();
                            throw new IllegalStateException("Attempted to find non-existent uuid " + uuid);
                        }
                        synchronized (OfflineStore.this.lock) {
                            ParseObject parseObject2 = (ParseObject) OfflineStore.this.uuidToObjectMap.get(uuid);
                            if (parseObject2 != null) {
                                return parseObject2;
                            }
                            String className = cursor.getString(0);
                            String objectId = cursor.getString(1);
                            cursor.close();
                            ParseObject createWithoutData = ParseObject.createWithoutData(className, objectId);
                            if (objectId == null) {
                                OfflineStore.this.uuidToObjectMap.put(uuid, createWithoutData);
                                OfflineStore.this.objectToUuidMap.put(createWithoutData, Task.forResult(uuid));
                            }
                            return createWithoutData;
                        }
                    }
                });
            }
            return Task.forResult(parseObject);
        }
    }

    public <T extends ParseObject> Task<List<T>> findAsync(ParseQuery.State<T> query, ParseUser user, ParsePin pin, ParseSQLiteDatabase db) {
        return findAsync(query, user, pin, false, db);
    }

    public <T extends ParseObject> Task<List<T>> findAsync(final ParseQuery.State<T> query, final ParseUser user, ParsePin pin, final boolean isCount, final ParseSQLiteDatabase db) {
        Task<Cursor> queryTask;
        final OfflineQueryLogic queryLogic = new OfflineQueryLogic(this);
        final List<T> results = new ArrayList<>();
        if (pin == null) {
            queryTask = db.queryAsync("ParseObjects", new String[]{"uuid"}, "className=? AND isDeletingEventually=0", new String[]{query.className()});
        } else {
            Task<String> uuidTask = this.objectToUuidMap.get(pin);
            if (uuidTask == null) {
                return Task.forResult(results);
            }
            queryTask = uuidTask.onSuccessTask(new Continuation<String, Task<Cursor>>() { // from class: com.parse.OfflineStore.4
                @Override // bolts.Continuation
                public Task<Cursor> then(Task<String> task) throws Exception {
                    return db.queryAsync("ParseObjects A  INNER JOIN Dependencies B  ON A.uuid=B.uuid", new String[]{"A.uuid"}, "className=? AND key=? AND isDeletingEventually=0", new String[]{query.className(), task.getResult()});
                }
            });
        }
        return queryTask.onSuccessTask(new Continuation<Cursor, Task<Void>>() { // from class: com.parse.OfflineStore.6
            @Override // bolts.Continuation
            public Task<Void> then(Task<Cursor> task) throws Exception {
                Cursor cursor = task.getResult();
                List<String> uuids = new ArrayList<>();
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    uuids.add(cursor.getString(0));
                    cursor.moveToNext();
                }
                cursor.close();
                final OfflineQueryLogic.ConstraintMatcher createMatcher = queryLogic.createMatcher(query, user);
                Task<Void> checkedAllObjects = Task.forResult(null);
                for (final String uuid : uuids) {
                    final Capture capture = new Capture();
                    checkedAllObjects = checkedAllObjects.onSuccessTask(new Continuation<Void, Task<T>>() { // from class: com.parse.OfflineStore.6.4
                        @Override // bolts.Continuation
                        public Task<T> then(Task<Void> task2) throws Exception {
                            return OfflineStore.this.getPointerAsync(uuid, db);
                        }
                    }).onSuccessTask(new Continuation<T, Task<T>>() { // from class: com.parse.OfflineStore.6.3
                        @Override // bolts.Continuation
                        public Task<T> then(Task<T> task2) throws Exception {
                            capture.set(task2.getResult());
                            return OfflineStore.this.fetchLocallyAsync((ParseObject) capture.get(), db);
                        }
                    }).onSuccessTask(new Continuation<T, Task<Boolean>>() { // from class: com.parse.OfflineStore.6.2
                        @Override // bolts.Continuation
                        public Task<Boolean> then(Task<T> task2) throws Exception {
                            return !((ParseObject) capture.get()).isDataAvailable() ? Task.forResult(false) : createMatcher.matchesAsync((ParseObject) capture.get(), db);
                        }
                    }).onSuccess(new Continuation<Boolean, Void>() { // from class: com.parse.OfflineStore.6.1
                        @Override // bolts.Continuation
                        public Void then(Task<Boolean> task2) {
                            if (!task2.getResult().booleanValue()) {
                                return null;
                            }
                            results.add(capture.get());
                            return null;
                        }
                    });
                }
                return checkedAllObjects;
            }
        }).onSuccessTask(new Continuation<Void, Task<List<T>>>() { // from class: com.parse.OfflineStore.5
            @Override // bolts.Continuation
            public Task<List<T>> then(Task<Void> task) throws Exception {
                OfflineQueryLogic.sort(results, query);
                final List<ParseObject> list = results;
                int skip = query.skip();
                if (!isCount && skip >= 0) {
                    list = list.subList(Math.min(query.skip(), list.size()), list.size());
                }
                int limit = query.limit();
                if (!isCount && limit >= 0 && list.size() > limit) {
                    list = list.subList(0, limit);
                }
                Task<Void> fetchedIncludesTask = Task.forResult(null);
                for (final ParseObject parseObject : list) {
                    fetchedIncludesTask = fetchedIncludesTask.onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineStore.5.1
                        @Override // bolts.Continuation
                        public Task<Void> then(Task<Void> task2) throws Exception {
                            return OfflineQueryLogic.fetchIncludesAsync(OfflineStore.this, parseObject, query, db);
                        }
                    });
                }
                return fetchedIncludesTask.onSuccess(new Continuation<Void, List<T>>() { // from class: com.parse.OfflineStore.5.2
                    @Override // bolts.Continuation
                    public List<T> then(Task<Void> task2) throws Exception {
                        return list;
                    }
                });
            }
        });
    }

    public <T extends ParseObject> Task<T> fetchLocallyAsync(final T object, final ParseSQLiteDatabase db) {
        final TaskCompletionSource<T> tcs = new TaskCompletionSource<>();
        synchronized (this.lock) {
            if (this.fetchedObjects.containsKey(object)) {
                return (Task<T>) this.fetchedObjects.get(object);
            }
            this.fetchedObjects.put(object, tcs.getTask());
            Task<String> uuidTask = this.objectToUuidMap.get(object);
            String className = object.getClassName();
            String objectId = object.getObjectId();
            Task forResult = Task.forResult(null);
            if (objectId == null) {
                if (uuidTask != null) {
                    final String[] select = {"json"};
                    final Capture<String> uuid = new Capture<>();
                    forResult = uuidTask.onSuccessTask(new Continuation<String, Task<Cursor>>() { // from class: com.parse.OfflineStore.8
                        @Override // bolts.Continuation
                        public Task<Cursor> then(Task<String> task) throws Exception {
                            uuid.set(task.getResult());
                            return db.queryAsync("ParseObjects", select, "uuid = ?", new String[]{(String) uuid.get()});
                        }
                    }).onSuccess(new Continuation<Cursor, String>() { // from class: com.parse.OfflineStore.7
                        @Override // bolts.Continuation
                        public String then(Task<Cursor> task) throws Exception {
                            Cursor cursor = task.getResult();
                            cursor.moveToFirst();
                            if (cursor.isAfterLast()) {
                                cursor.close();
                                throw new IllegalStateException("Attempted to find non-existent uuid " + ((String) uuid.get()));
                            }
                            String json = cursor.getString(0);
                            cursor.close();
                            return json;
                        }
                    });
                }
            } else if (uuidTask != null) {
                tcs.setError(new IllegalStateException("This object must have already been fetched from the local datastore, but isn't marked as fetched."));
                synchronized (this.lock) {
                    this.fetchedObjects.remove(object);
                }
                return tcs.getTask();
            } else {
                forResult = db.queryAsync("ParseObjects", new String[]{"json", "uuid"}, String.format("%s = ? AND %s = ?", "className", "objectId"), new String[]{className, objectId}).onSuccess(new Continuation<Cursor, String>() { // from class: com.parse.OfflineStore.9
                    @Override // bolts.Continuation
                    public String then(Task<Cursor> task) throws Exception {
                        Cursor cursor = task.getResult();
                        cursor.moveToFirst();
                        if (cursor.isAfterLast()) {
                            cursor.close();
                            throw new ParseException(120, "This object is not available in the offline cache.");
                        }
                        String jsonString = cursor.getString(0);
                        String newUUID = cursor.getString(1);
                        cursor.close();
                        synchronized (OfflineStore.this.lock) {
                            OfflineStore.this.objectToUuidMap.put(object, Task.forResult(newUUID));
                            OfflineStore.this.uuidToObjectMap.put(newUUID, object);
                        }
                        return jsonString;
                    }
                });
            }
            return forResult.onSuccessTask(new Continuation<String, Task<Void>>() { // from class: com.parse.OfflineStore.11
                @Override // bolts.Continuation
                public Task<Void> then(Task<String> task) throws Exception {
                    String jsonString = task.getResult();
                    if (jsonString == null) {
                        return Task.forError(new ParseException(120, "Attempted to fetch an object offline which was never saved to the offline cache."));
                    }
                    try {
                        final JSONObject json = new JSONObject(jsonString);
                        final Map<String, Task<ParseObject>> offlineObjects = new HashMap<>();
                        new ParseTraverser() { // from class: com.parse.OfflineStore.11.1
                            @Override // com.parse.ParseTraverser
                            protected boolean visit(Object object2) {
                                if (!(object2 instanceof JSONObject) || !((JSONObject) object2).optString("__type").equals("OfflineObject")) {
                                    return true;
                                }
                                String uuid2 = ((JSONObject) object2).optString("uuid");
                                offlineObjects.put(uuid2, OfflineStore.this.getPointerAsync(uuid2, db));
                                return true;
                            }
                        }.setTraverseParseObjects(false).setYieldRoot(false).traverse(json);
                        return Task.whenAll(offlineObjects.values()).onSuccess(new Continuation<Void, Void>() { // from class: com.parse.OfflineStore.11.2
                            @Override // bolts.Continuation
                            public Void then(Task<Void> task2) throws Exception {
                                object.mergeREST(object.getState(), json, new OfflineDecoder(offlineObjects));
                                return null;
                            }
                        });
                    } catch (JSONException e) {
                        return Task.forError(e);
                    }
                }
            }).continueWithTask(new Continuation<Void, Task<T>>() { // from class: com.parse.OfflineStore.10
                @Override // bolts.Continuation
                public Task<T> then(Task<Void> task) throws Exception {
                    if (task.isCancelled()) {
                        tcs.setCancelled();
                    } else if (task.isFaulted()) {
                        tcs.setError(task.getError());
                    } else {
                        tcs.setResult(object);
                    }
                    return tcs.getTask();
                }
            });
        }
    }

    public <T extends ParseObject> Task<T> fetchLocallyAsync(final T object) {
        return runWithManagedConnection(new SQLiteDatabaseCallable<Task<T>>() { // from class: com.parse.OfflineStore.12
            @Override // com.parse.OfflineStore.SQLiteDatabaseCallable
            public Task<T> call(ParseSQLiteDatabase db) {
                return OfflineStore.this.fetchLocallyAsync(object, db);
            }
        });
    }

    public Task<Void> saveLocallyAsync(final String key, final ParseObject object, final ParseSQLiteDatabase db) {
        if (object.getObjectId() != null && !object.isDataAvailable() && !object.hasChanges() && !object.hasOutstandingOperations()) {
            return Task.forResult(null);
        }
        final Capture<String> uuidCapture = new Capture<>();
        return getOrCreateUUIDAsync(object, db).onSuccessTask(new Continuation<String, Task<Void>>() { // from class: com.parse.OfflineStore.14
            @Override // bolts.Continuation
            public Task<Void> then(Task<String> task) throws Exception {
                String uuid = task.getResult();
                uuidCapture.set(uuid);
                return OfflineStore.this.updateDataForObjectAsync(uuid, object, db);
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineStore.13
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task) throws Exception {
                ContentValues values = new ContentValues();
                values.put("key", key);
                values.put("uuid", (String) uuidCapture.get());
                return db.insertWithOnConflict("Dependencies", values, 4);
            }
        });
    }

    public Task<Void> saveLocallyAsync(ParseObject object, boolean includeAllChildren, ParseSQLiteDatabase db) {
        final ArrayList<ParseObject> objectsInTree = new ArrayList<>();
        if (!includeAllChildren) {
            objectsInTree.add(object);
        } else {
            new ParseTraverser() { // from class: com.parse.OfflineStore.15
                @Override // com.parse.ParseTraverser
                protected boolean visit(Object object2) {
                    if (!(object2 instanceof ParseObject)) {
                        return true;
                    }
                    objectsInTree.add((ParseObject) object2);
                    return true;
                }
            }.setYieldRoot(true).setTraverseParseObjects(true).traverse(object);
        }
        return saveLocallyAsync(object, objectsInTree, db);
    }

    public Task<Void> saveLocallyAsync(final ParseObject object, List<ParseObject> children, final ParseSQLiteDatabase db) {
        final List<ParseObject> objects = children != null ? new ArrayList<>(children) : new ArrayList<>();
        if (!objects.contains(object)) {
            objects.add(object);
        }
        List<Task<Void>> tasks = new ArrayList<>();
        for (ParseObject obj : objects) {
            tasks.add(fetchLocallyAsync(obj, db).makeVoid());
        }
        return Task.whenAll(tasks).continueWithTask(new Continuation<Void, Task<String>>() { // from class: com.parse.OfflineStore.19
            @Override // bolts.Continuation
            public Task<String> then(Task<Void> task) throws Exception {
                return (Task) OfflineStore.this.objectToUuidMap.get(object);
            }
        }).onSuccessTask(new Continuation<String, Task<Void>>() { // from class: com.parse.OfflineStore.18
            @Override // bolts.Continuation
            public Task<Void> then(Task<String> task) throws Exception {
                String uuid = task.getResult();
                if (uuid == null) {
                    return null;
                }
                return OfflineStore.this.unpinAsync(uuid, db);
            }
        }).onSuccessTask(new Continuation<Void, Task<String>>() { // from class: com.parse.OfflineStore.17
            @Override // bolts.Continuation
            public Task<String> then(Task<Void> task) throws Exception {
                return OfflineStore.this.getOrCreateUUIDAsync(object, db);
            }
        }).onSuccessTask(new Continuation<String, Task<Void>>() { // from class: com.parse.OfflineStore.16
            @Override // bolts.Continuation
            public Task<Void> then(Task<String> task) throws Exception {
                String uuid = task.getResult();
                List<Task<Void>> tasks2 = new ArrayList<>();
                for (ParseObject obj2 : objects) {
                    tasks2.add(OfflineStore.this.saveLocallyAsync(uuid, obj2, db));
                }
                return Task.whenAll(tasks2);
            }
        });
    }

    public Task<Void> unpinAsync(ParseObject object, final ParseSQLiteDatabase db) {
        Task<String> uuidTask = this.objectToUuidMap.get(object);
        return uuidTask == null ? Task.forResult(null) : uuidTask.continueWithTask(new Continuation<String, Task<Void>>() { // from class: com.parse.OfflineStore.20
            @Override // bolts.Continuation
            public Task<Void> then(Task<String> task) throws Exception {
                String uuid = task.getResult();
                if (uuid == null) {
                    return Task.forResult(null);
                }
                return OfflineStore.this.unpinAsync(uuid, db);
            }
        });
    }

    public Task<Void> unpinAsync(final String key, final ParseSQLiteDatabase db) {
        final List<String> uuidsToDelete = new LinkedList<>();
        return Task.forResult(null).continueWithTask(new Continuation<Void, Task<Cursor>>() { // from class: com.parse.OfflineStore.24
            @Override // bolts.Continuation
            public Task<Cursor> then(Task<Void> task) throws Exception {
                return db.rawQueryAsync("SELECT uuid FROM Dependencies WHERE key=? AND uuid IN ( SELECT uuid FROM Dependencies GROUP BY uuid HAVING COUNT(uuid)=1)", new String[]{key});
            }
        }).onSuccessTask(new Continuation<Cursor, Task<Void>>() { // from class: com.parse.OfflineStore.23
            @Override // bolts.Continuation
            public Task<Void> then(Task<Cursor> task) throws Exception {
                Cursor cursor = task.getResult();
                while (cursor.moveToNext()) {
                    uuidsToDelete.add(cursor.getString(0));
                }
                cursor.close();
                return OfflineStore.this.deleteObjects(uuidsToDelete, db);
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineStore.22
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task) throws Exception {
                return db.deleteAsync("Dependencies", "key=?", new String[]{key});
            }
        }).onSuccess(new Continuation<Void, Void>() { // from class: com.parse.OfflineStore.21
            @Override // bolts.Continuation
            public Void then(Task<Void> task) throws Exception {
                synchronized (OfflineStore.this.lock) {
                    for (String uuid : uuidsToDelete) {
                        ParseObject object = (ParseObject) OfflineStore.this.uuidToObjectMap.get(uuid);
                        if (object != null) {
                            OfflineStore.this.objectToUuidMap.remove(object);
                            OfflineStore.this.uuidToObjectMap.remove(uuid);
                        }
                    }
                }
                return null;
            }
        });
    }

    public Task<Void> deleteObjects(final List<String> uuids, final ParseSQLiteDatabase db) {
        if (uuids.size() <= 0) {
            return Task.forResult(null);
        }
        if (uuids.size() > 999) {
            return deleteObjects(uuids.subList(0, 999), db).onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineStore.25
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task) throws Exception {
                    return OfflineStore.this.deleteObjects(uuids.subList(999, uuids.size()), db);
                }
            });
        }
        String[] placeholders = new String[uuids.size()];
        for (int i = 0; i < placeholders.length; i++) {
            placeholders[i] = "?";
        }
        return db.deleteAsync("ParseObjects", "uuid IN (" + TextUtils.join(",", placeholders) + ")", (String[]) uuids.toArray(new String[uuids.size()]));
    }

    public Task<Void> updateDataForObjectAsync(ParseObject object) {
        synchronized (this.lock) {
            Task<ParseObject> fetched = this.fetchedObjects.get(object);
            if (fetched != null) {
                return fetched.continueWithTask(new AnonymousClass26(object));
            }
            return Task.forError(new IllegalStateException("An object cannot be updated if it wasn't fetched."));
        }
    }

    /* renamed from: com.parse.OfflineStore$26 */
    /* loaded from: classes2.dex */
    public class AnonymousClass26 implements Continuation<ParseObject, Task<Void>> {
        final /* synthetic */ ParseObject val$object;

        AnonymousClass26(ParseObject parseObject) {
            OfflineStore.this = r1;
            this.val$object = parseObject;
        }

        @Override // bolts.Continuation
        public Task<Void> then(Task<ParseObject> task) throws Exception {
            if (!task.isFaulted()) {
                return OfflineStore.this.helper.getWritableDatabaseAsync().continueWithTask(new Continuation<ParseSQLiteDatabase, Task<Void>>() { // from class: com.parse.OfflineStore.26.1
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<ParseSQLiteDatabase> task2) throws Exception {
                        final ParseSQLiteDatabase db = task2.getResult();
                        return db.beginTransactionAsync().onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineStore.26.1.1
                            @Override // bolts.Continuation
                            public Task<Void> then(Task<Void> task3) throws Exception {
                                return OfflineStore.this.updateDataForObjectAsync(AnonymousClass26.this.val$object, db).onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineStore.26.1.1.2
                                    @Override // bolts.Continuation
                                    public Task<Void> then(Task<Void> task4) throws Exception {
                                        return db.setTransactionSuccessfulAsync();
                                    }
                                }).continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineStore.26.1.1.1
                                    @Override // bolts.Continuation
                                    public Task<Void> then(Task<Void> task4) throws Exception {
                                        db.endTransactionAsync();
                                        db.closeAsync();
                                        return task4;
                                    }
                                });
                            }
                        });
                    }
                });
            }
            if (!(task.getError() instanceof ParseException) || ((ParseException) task.getError()).getCode() != 120) {
                return task.makeVoid();
            }
            return Task.forResult(null);
        }
    }

    public Task<Void> updateDataForObjectAsync(final ParseObject object, final ParseSQLiteDatabase db) {
        synchronized (this.lock) {
            Task<String> uuidTask = this.objectToUuidMap.get(object);
            if (uuidTask != null) {
                return uuidTask.onSuccessTask(new Continuation<String, Task<Void>>() { // from class: com.parse.OfflineStore.27
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<String> task) throws Exception {
                        return OfflineStore.this.updateDataForObjectAsync(task.getResult(), object, db);
                    }
                });
            }
            return Task.forResult(null);
        }
    }

    public Task<Void> updateDataForObjectAsync(final String uuid, final ParseObject object, final ParseSQLiteDatabase db) {
        OfflineEncoder encoder = new OfflineEncoder(db);
        final JSONObject json = object.toRest(encoder);
        return encoder.whenFinished().onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineStore.28
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task) throws Exception {
                String className = object.getClassName();
                String objectId = object.getObjectId();
                int isDeletingEventually = json.getInt("__isDeletingEventually");
                ContentValues values = new ContentValues();
                values.put("className", className);
                values.put("json", json.toString());
                if (objectId != null) {
                    values.put("objectId", objectId);
                }
                values.put("isDeletingEventually", Integer.valueOf(isDeletingEventually));
                return db.updateAsync("ParseObjects", values, "uuid = ?", new String[]{uuid}).makeVoid();
            }
        });
    }

    public Task<Void> deleteDataForObjectAsync(final ParseObject object) {
        return this.helper.getWritableDatabaseAsync().continueWithTask(new Continuation<ParseSQLiteDatabase, Task<Void>>() { // from class: com.parse.OfflineStore.29
            @Override // bolts.Continuation
            public Task<Void> then(Task<ParseSQLiteDatabase> task) throws Exception {
                final ParseSQLiteDatabase db = task.getResult();
                return db.beginTransactionAsync().onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineStore.29.1
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<Void> task2) throws Exception {
                        return OfflineStore.this.deleteDataForObjectAsync(object, db).onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineStore.29.1.2
                            @Override // bolts.Continuation
                            public Task<Void> then(Task<Void> task3) throws Exception {
                                return db.setTransactionSuccessfulAsync();
                            }
                        }).continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineStore.29.1.1
                            @Override // bolts.Continuation
                            public Task<Void> then(Task<Void> task3) throws Exception {
                                db.endTransactionAsync();
                                db.closeAsync();
                                return task3;
                            }
                        });
                    }
                });
            }
        });
    }

    public Task<Void> deleteDataForObjectAsync(final ParseObject object, final ParseSQLiteDatabase db) {
        final Capture<String> uuid = new Capture<>();
        synchronized (this.lock) {
            Task<String> uuidTask = this.objectToUuidMap.get(object);
            if (uuidTask != null) {
                return uuidTask.onSuccessTask(new Continuation<String, Task<String>>() { // from class: com.parse.OfflineStore.30
                    @Override // bolts.Continuation
                    public Task<String> then(Task<String> task) throws Exception {
                        uuid.set(task.getResult());
                        return task;
                    }
                }).onSuccessTask(new Continuation<String, Task<Cursor>>() { // from class: com.parse.OfflineStore.32
                    @Override // bolts.Continuation
                    public Task<Cursor> then(Task<String> task) throws Exception {
                        return db.queryAsync("Dependencies", new String[]{"key"}, "uuid=?", new String[]{(String) uuid.get()});
                    }
                }).onSuccessTask(new Continuation<Cursor, Task<Void>>() { // from class: com.parse.OfflineStore.31
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<Cursor> task) throws Exception {
                        Cursor cursor = task.getResult();
                        List<String> uuids = new ArrayList<>();
                        cursor.moveToFirst();
                        while (!cursor.isAfterLast()) {
                            uuids.add(cursor.getString(0));
                            cursor.moveToNext();
                        }
                        cursor.close();
                        List<Task<Void>> tasks = new ArrayList<>();
                        for (final String uuid2 : uuids) {
                            tasks.add(OfflineStore.this.getPointerAsync(uuid2, db).onSuccessTask(new Continuation<ParseObject, Task<ParsePin>>() { // from class: com.parse.OfflineStore.31.2
                                @Override // bolts.Continuation
                                public Task<ParsePin> then(Task<ParseObject> task2) throws Exception {
                                    return OfflineStore.this.fetchLocallyAsync((ParsePin) task2.getResult(), db);
                                }
                            }).continueWithTask(new Continuation<ParsePin, Task<Void>>() { // from class: com.parse.OfflineStore.31.1
                                @Override // bolts.Continuation
                                public Task<Void> then(Task<ParsePin> task2) throws Exception {
                                    ParsePin pin = task2.getResult();
                                    List<ParseObject> modified = pin.getObjects();
                                    if (modified == null || !modified.contains(object)) {
                                        return task2.makeVoid();
                                    }
                                    modified.remove(object);
                                    if (modified.size() == 0) {
                                        return OfflineStore.this.unpinAsync(uuid2, db);
                                    }
                                    pin.setObjects(modified);
                                    return OfflineStore.this.saveLocallyAsync((ParseObject) pin, true, db);
                                }
                            }));
                        }
                        return Task.whenAll(tasks);
                    }
                }).onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineStore.35
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<Void> task) throws Exception {
                        return db.deleteAsync("Dependencies", "uuid=?", new String[]{(String) uuid.get()});
                    }
                }).onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineStore.34
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<Void> task) throws Exception {
                        return db.deleteAsync("ParseObjects", "uuid=?", new String[]{(String) uuid.get()});
                    }
                }).onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineStore.33
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<Void> task) throws Exception {
                        synchronized (OfflineStore.this.lock) {
                            OfflineStore.this.fetchedObjects.remove(object);
                        }
                        return task;
                    }
                });
            }
            return Task.forResult(null);
        }
    }

    private Task<ParsePin> getParsePin(final String name, ParseSQLiteDatabase db) {
        return findAsync(new ParseQuery.State.Builder(ParsePin.class).whereEqualTo("_name", name).build(), null, null, db).onSuccess(new Continuation<List<ParsePin>, ParsePin>() { // from class: com.parse.OfflineStore.36
            @Override // bolts.Continuation
            public ParsePin then(Task<List<ParsePin>> task) throws Exception {
                ParsePin pin = null;
                if (task.getResult() != null && task.getResult().size() > 0) {
                    pin = task.getResult().get(0);
                }
                if (pin != null) {
                    return pin;
                }
                ParsePin pin2 = (ParsePin) ParseObject.create(ParsePin.class);
                pin2.setName(name);
                return pin2;
            }
        });
    }

    public <T extends ParseObject> Task<Void> pinAllObjectsAsync(final String name, final List<T> objects, final boolean includeChildren) {
        return runWithManagedTransaction(new SQLiteDatabaseCallable<Task<Void>>() { // from class: com.parse.OfflineStore.37
            @Override // com.parse.OfflineStore.SQLiteDatabaseCallable
            public Task<Void> call(ParseSQLiteDatabase db) {
                return OfflineStore.this.pinAllObjectsAsync(name, objects, includeChildren, db);
            }
        });
    }

    public <T extends ParseObject> Task<Void> pinAllObjectsAsync(String name, final List<T> objects, final boolean includeChildren, final ParseSQLiteDatabase db) {
        return (objects == null || objects.size() == 0) ? Task.forResult(null) : getParsePin(name, db).onSuccessTask(new Continuation<ParsePin, Task<Void>>() { // from class: com.parse.OfflineStore.38
            @Override // bolts.Continuation
            public Task<Void> then(Task<ParsePin> task) throws Exception {
                ParsePin pin = task.getResult();
                List<ParseObject> modified = pin.getObjects();
                if (modified == null) {
                    modified = new ArrayList<>(objects);
                } else {
                    for (ParseObject object : objects) {
                        if (!modified.contains(object)) {
                            modified.add(object);
                        }
                    }
                }
                pin.setObjects(modified);
                return includeChildren ? OfflineStore.this.saveLocallyAsync((ParseObject) pin, true, db) : OfflineStore.this.saveLocallyAsync(pin, pin.getObjects(), db);
            }
        });
    }

    public <T extends ParseObject> Task<Void> unpinAllObjectsAsync(final String name, final List<T> objects) {
        return runWithManagedTransaction(new SQLiteDatabaseCallable<Task<Void>>() { // from class: com.parse.OfflineStore.39
            @Override // com.parse.OfflineStore.SQLiteDatabaseCallable
            public Task<Void> call(ParseSQLiteDatabase db) {
                return OfflineStore.this.unpinAllObjectsAsync(name, objects, db);
            }
        });
    }

    public <T extends ParseObject> Task<Void> unpinAllObjectsAsync(String name, final List<T> objects, final ParseSQLiteDatabase db) {
        return (objects == null || objects.size() == 0) ? Task.forResult(null) : getParsePin(name, db).onSuccessTask(new Continuation<ParsePin, Task<Void>>() { // from class: com.parse.OfflineStore.40
            @Override // bolts.Continuation
            public Task<Void> then(Task<ParsePin> task) throws Exception {
                ParsePin pin = task.getResult();
                List<ParseObject> modified = pin.getObjects();
                if (modified == null) {
                    return Task.forResult(null);
                }
                modified.removeAll(objects);
                if (modified.size() == 0) {
                    return OfflineStore.this.unpinAsync(pin, db);
                }
                pin.setObjects(modified);
                return OfflineStore.this.saveLocallyAsync((ParseObject) pin, true, db);
            }
        });
    }

    public Task<Void> unpinAllObjectsAsync(final String name) {
        return runWithManagedTransaction(new SQLiteDatabaseCallable<Task<Void>>() { // from class: com.parse.OfflineStore.41
            @Override // com.parse.OfflineStore.SQLiteDatabaseCallable
            public Task<Void> call(ParseSQLiteDatabase db) {
                return OfflineStore.this.unpinAllObjectsAsync(name, db);
            }
        });
    }

    public Task<Void> unpinAllObjectsAsync(String name, final ParseSQLiteDatabase db) {
        return getParsePin(name, db).continueWithTask(new Continuation<ParsePin, Task<Void>>() { // from class: com.parse.OfflineStore.42
            @Override // bolts.Continuation
            public Task<Void> then(Task<ParsePin> task) throws Exception {
                if (task.isFaulted()) {
                    return task.makeVoid();
                }
                return OfflineStore.this.unpinAsync(task.getResult(), db);
            }
        });
    }

    public <T extends ParseObject> Task<List<T>> findFromPinAsync(final String name, final ParseQuery.State<T> state, final ParseUser user) {
        return runWithManagedConnection(new SQLiteDatabaseCallable<Task<List<T>>>() { // from class: com.parse.OfflineStore.43
            @Override // com.parse.OfflineStore.SQLiteDatabaseCallable
            public Task<List<T>> call(ParseSQLiteDatabase db) {
                return OfflineStore.this.findFromPinAsync(name, state, user, db);
            }
        });
    }

    public <T extends ParseObject> Task<List<T>> findFromPinAsync(String name, final ParseQuery.State<T> state, final ParseUser user, final ParseSQLiteDatabase db) {
        Task<ParsePin> task;
        if (name != null) {
            task = getParsePin(name, db);
        } else {
            task = Task.forResult(null);
        }
        return (Task<List<T>>) task.onSuccessTask(new Continuation<ParsePin, Task<List<T>>>() { // from class: com.parse.OfflineStore.44
            @Override // bolts.Continuation
            public Task<List<T>> then(Task<ParsePin> task2) throws Exception {
                return OfflineStore.this.findAsync(state, user, task2.getResult(), false, db);
            }
        });
    }

    public <T extends ParseObject> Task<Integer> countFromPinAsync(final String name, final ParseQuery.State<T> state, final ParseUser user) {
        return runWithManagedConnection(new SQLiteDatabaseCallable<Task<Integer>>() { // from class: com.parse.OfflineStore.45
            @Override // com.parse.OfflineStore.SQLiteDatabaseCallable
            public Task<Integer> call(ParseSQLiteDatabase db) {
                return OfflineStore.this.countFromPinAsync(name, state, user, db);
            }
        });
    }

    public <T extends ParseObject> Task<Integer> countFromPinAsync(String name, final ParseQuery.State<T> state, final ParseUser user, final ParseSQLiteDatabase db) {
        Task<ParsePin> task;
        if (name != null) {
            task = getParsePin(name, db);
        } else {
            task = Task.forResult(null);
        }
        return task.onSuccessTask(new Continuation<ParsePin, Task<Integer>>() { // from class: com.parse.OfflineStore.46
            @Override // bolts.Continuation
            public Task<Integer> then(Task<ParsePin> task2) throws Exception {
                return OfflineStore.this.findAsync(state, user, task2.getResult(), true, db).onSuccess(new Continuation<List<T>, Integer>() { // from class: com.parse.OfflineStore.46.1
                    @Override // bolts.Continuation
                    public Integer then(Task<List<T>> task3) throws Exception {
                        return Integer.valueOf(((List) task3.getResult()).size());
                    }
                });
            }
        });
    }

    public void registerNewObject(ParseObject object) {
        synchronized (this.lock) {
            String objectId = object.getObjectId();
            if (objectId != null) {
                this.classNameAndObjectIdToObjectMap.put(Pair.create(object.getClassName(), objectId), object);
            }
        }
    }

    public void unregisterObject(ParseObject object) {
        synchronized (this.lock) {
            String objectId = object.getObjectId();
            if (objectId != null) {
                this.classNameAndObjectIdToObjectMap.remove(Pair.create(object.getClassName(), objectId));
            }
        }
    }

    public ParseObject getObject(String className, String objectId) {
        ParseObject parseObject;
        if (objectId == null) {
            throw new IllegalStateException("objectId cannot be null.");
        }
        Pair<String, String> classNameAndObjectId = Pair.create(className, objectId);
        synchronized (this.lock) {
            parseObject = this.classNameAndObjectIdToObjectMap.get(classNameAndObjectId);
        }
        return parseObject;
    }

    public void updateObjectId(ParseObject object, String oldObjectId, String newObjectId) {
        if (oldObjectId == null) {
            Pair<String, String> classNameAndNewObjectId = Pair.create(object.getClassName(), newObjectId);
            synchronized (this.lock) {
                ParseObject existing = this.classNameAndObjectIdToObjectMap.get(classNameAndNewObjectId);
                if (existing == null || existing == object) {
                    this.classNameAndObjectIdToObjectMap.put(classNameAndNewObjectId, object);
                } else {
                    throw new RuntimeException("Attempted to change an objectId to one that's already known to the Offline Store.");
                }
            }
        } else if (!oldObjectId.equals(newObjectId)) {
            throw new RuntimeException("objectIds cannot be changed in offline mode.");
        }
    }

    private <T> Task<T> runWithManagedConnection(final SQLiteDatabaseCallable<Task<T>> callable) {
        return (Task<T>) this.helper.getWritableDatabaseAsync().onSuccessTask((Continuation<ParseSQLiteDatabase, Task<T>>) new Continuation<ParseSQLiteDatabase, Task<T>>() { // from class: com.parse.OfflineStore.47
            @Override // bolts.Continuation
            public Task<T> then(Task<ParseSQLiteDatabase> task) throws Exception {
                final ParseSQLiteDatabase db = task.getResult();
                return ((Task) callable.call(db)).continueWithTask(new Continuation<T, Task<T>>() { // from class: com.parse.OfflineStore.47.1
                    @Override // bolts.Continuation
                    public Task<T> then(Task<T> task2) throws Exception {
                        db.closeAsync();
                        return task2;
                    }
                });
            }
        });
    }

    private Task<Void> runWithManagedTransaction(final SQLiteDatabaseCallable<Task<Void>> callable) {
        return this.helper.getWritableDatabaseAsync().onSuccessTask(new Continuation<ParseSQLiteDatabase, Task<Void>>() { // from class: com.parse.OfflineStore.48
            @Override // bolts.Continuation
            public Task<Void> then(Task<ParseSQLiteDatabase> task) throws Exception {
                final ParseSQLiteDatabase db = task.getResult();
                return db.beginTransactionAsync().onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineStore.48.1
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<Void> task2) throws Exception {
                        return ((Task) callable.call(db)).onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineStore.48.1.2
                            @Override // bolts.Continuation
                            public Task<Void> then(Task<Void> task3) throws Exception {
                                return db.setTransactionSuccessfulAsync();
                            }
                        }).continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineStore.48.1.1
                            @Override // bolts.Continuation
                            public Task<Void> then(Task<Void> task3) throws Exception {
                                db.endTransactionAsync();
                                db.closeAsync();
                                return task3;
                            }
                        });
                    }
                });
            }
        });
    }

    void simulateReboot() {
        synchronized (this.lock) {
            this.uuidToObjectMap.clear();
            this.objectToUuidMap.clear();
            this.classNameAndObjectIdToObjectMap.clear();
            this.fetchedObjects.clear();
        }
    }

    void clearDatabase(Context context) {
        this.helper.clearDatabase(context);
    }
}

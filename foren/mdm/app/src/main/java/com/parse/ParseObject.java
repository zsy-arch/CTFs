package com.parse;

import bolts.Capture;
import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import com.parse.ParseUser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ParseObject {
    private static final String AUTO_CLASS_NAME = "_Automatic";
    public static final String DEFAULT_PIN = "_default";
    private static final String KEY_ACL = "ACL";
    private static final String KEY_CLASS_NAME = "className";
    private static final String KEY_COMPLETE = "__complete";
    private static final String KEY_CREATED_AT = "createdAt";
    static final String KEY_IS_DELETING_EVENTUALLY = "__isDeletingEventually";
    private static final String KEY_IS_DELETING_EVENTUALLY_OLD = "isDeletingEventually";
    private static final String KEY_OBJECT_ID = "objectId";
    private static final String KEY_OPERATIONS = "__operations";
    private static final String KEY_UPDATED_AT = "updatedAt";
    private static final String NEW_OFFLINE_OBJECT_ID_PLACEHOLDER = "*** Offline Object ***";
    static final String VERSION_NAME = "1.13.0";
    private static final ThreadLocal<String> isCreatingPointerForObjectId = new ThreadLocal<String>() { // from class: com.parse.ParseObject.1
        @Override // java.lang.ThreadLocal
        public String initialValue() {
            return null;
        }
    };
    private final Map<String, Object> estimatedData;
    boolean isDeleted;
    int isDeletingEventually;
    private String localId;
    final Object mutex;
    final LinkedList<ParseOperationSet> operationSetQueue;
    private final ParseMulticastDelegate<ParseObject> saveEvent;
    private State state;
    final TaskQueue taskQueue;

    public static ParseObjectController getObjectController() {
        return ParseCorePlugins.getInstance().getObjectController();
    }

    private static LocalIdManager getLocalIdManager() {
        return ParseCorePlugins.getInstance().getLocalIdManager();
    }

    private static ParseObjectSubclassingController getSubclassingController() {
        return ParseCorePlugins.getInstance().getSubclassingController();
    }

    /* loaded from: classes2.dex */
    public static class State {
        private final String className;
        private final long createdAt;
        private final boolean isComplete;
        private final String objectId;
        private final Map<String, Object> serverData;
        private final long updatedAt;

        public static Init<?> newBuilder(String className) {
            return "_User".equals(className) ? new ParseUser.State.Builder() : new Builder(className);
        }

        /* loaded from: classes2.dex */
        public static abstract class Init<T extends Init> {
            private final String className;
            private long createdAt;
            private boolean isComplete;
            private String objectId;
            Map<String, Object> serverData;
            private long updatedAt;

            /* JADX INFO: Access modifiers changed from: package-private */
            public abstract <S extends State> S build();

            abstract T self();

            public Init(String className) {
                this.createdAt = -1L;
                this.updatedAt = -1L;
                this.serverData = new HashMap();
                this.className = className;
            }

            public Init(State state) {
                this.createdAt = -1L;
                this.updatedAt = -1L;
                this.serverData = new HashMap();
                this.className = state.className();
                this.objectId = state.objectId();
                this.createdAt = state.createdAt();
                this.updatedAt = state.updatedAt();
                for (String key : state.keySet()) {
                    this.serverData.put(key, state.get(key));
                }
                this.isComplete = state.isComplete();
            }

            public T objectId(String objectId) {
                this.objectId = objectId;
                return self();
            }

            public T createdAt(Date createdAt) {
                this.createdAt = createdAt.getTime();
                return self();
            }

            public T createdAt(long createdAt) {
                this.createdAt = createdAt;
                return self();
            }

            public T updatedAt(Date updatedAt) {
                this.updatedAt = updatedAt.getTime();
                return self();
            }

            public T updatedAt(long updatedAt) {
                this.updatedAt = updatedAt;
                return self();
            }

            public T isComplete(boolean complete) {
                this.isComplete = complete;
                return self();
            }

            public T put(String key, Object value) {
                this.serverData.put(key, value);
                return self();
            }

            public T remove(String key) {
                this.serverData.remove(key);
                return self();
            }

            public T clear() {
                this.objectId = null;
                this.createdAt = -1L;
                this.updatedAt = -1L;
                this.isComplete = false;
                this.serverData.clear();
                return self();
            }

            public T apply(State other) {
                if (other.objectId() != null) {
                    objectId(other.objectId());
                }
                if (other.createdAt() > 0) {
                    createdAt(other.createdAt());
                }
                if (other.updatedAt() > 0) {
                    updatedAt(other.updatedAt());
                }
                isComplete(this.isComplete || other.isComplete());
                for (String key : other.keySet()) {
                    put(key, other.get(key));
                }
                return self();
            }

            public T apply(ParseOperationSet operations) {
                for (String key : operations.keySet()) {
                    Object newValue = ((ParseFieldOperation) operations.get(key)).apply(this.serverData.get(key), key);
                    if (newValue != null) {
                        put(key, newValue);
                    } else {
                        remove(key);
                    }
                }
                return self();
            }
        }

        /* loaded from: classes2.dex */
        public static class Builder extends Init<Builder> {
            public Builder(String className) {
                super(className);
            }

            public Builder(State state) {
                super(state);
            }

            @Override // com.parse.ParseObject.State.Init
            public Builder self() {
                return this;
            }

            @Override // com.parse.ParseObject.State.Init
            public State build() {
                return new State(this);
            }
        }

        public State(Init<?> builder) {
            long j;
            this.className = ((Init) builder).className;
            this.objectId = ((Init) builder).objectId;
            this.createdAt = ((Init) builder).createdAt;
            if (((Init) builder).updatedAt > 0) {
                j = ((Init) builder).updatedAt;
            } else {
                j = this.createdAt;
            }
            this.updatedAt = j;
            this.serverData = Collections.unmodifiableMap(new HashMap(builder.serverData));
            this.isComplete = ((Init) builder).isComplete;
        }

        public <T extends Init<?>> T newBuilder() {
            return new Builder(this);
        }

        public String className() {
            return this.className;
        }

        public String objectId() {
            return this.objectId;
        }

        public long createdAt() {
            return this.createdAt;
        }

        public long updatedAt() {
            return this.updatedAt;
        }

        public boolean isComplete() {
            return this.isComplete;
        }

        public Object get(String key) {
            return this.serverData.get(key);
        }

        public Set<String> keySet() {
            return this.serverData.keySet();
        }

        public String toString() {
            return String.format(Locale.US, "%s@%s[className=%s, objectId=%s, createdAt=%d, updatedAt=%d, isComplete=%s, serverData=%s]", getClass().getName(), Integer.toHexString(hashCode()), this.className, this.objectId, Long.valueOf(this.createdAt), Long.valueOf(this.updatedAt), Boolean.valueOf(this.isComplete), this.serverData);
        }
    }

    public ParseObject() {
        this(AUTO_CLASS_NAME);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ParseObject(String theClassName) {
        this.mutex = new Object();
        this.taskQueue = new TaskQueue();
        this.saveEvent = new ParseMulticastDelegate<>();
        String objectIdForPointer = isCreatingPointerForObjectId.get();
        if (theClassName == null) {
            throw new IllegalArgumentException("You must specify a Parse class name when creating a new ParseObject.");
        }
        theClassName = AUTO_CLASS_NAME.equals(theClassName) ? getSubclassingController().getClassName(getClass()) : theClassName;
        if (!getSubclassingController().isSubclassValid(theClassName, getClass())) {
            throw new IllegalArgumentException("You must create this type of ParseObject using ParseObject.create() or the proper subclass.");
        }
        this.operationSetQueue = new LinkedList<>();
        this.operationSetQueue.add(new ParseOperationSet());
        this.estimatedData = new HashMap();
        State.Init<?> builder = newStateBuilder(theClassName);
        if (objectIdForPointer == null) {
            setDefaultValues();
            builder.isComplete(true);
        } else {
            if (!objectIdForPointer.equals(NEW_OFFLINE_OBJECT_ID_PLACEHOLDER)) {
                builder.objectId(objectIdForPointer);
            }
            builder.isComplete(false);
        }
        this.state = builder.build();
        OfflineStore store = Parse.getLocalDatastore();
        if (store != null) {
            store.registerNewObject(this);
        }
    }

    public static ParseObject create(String className) {
        return getSubclassingController().newInstance(className);
    }

    public static <T extends ParseObject> T create(Class<T> subclass) {
        return (T) create(getSubclassingController().getClassName(subclass));
    }

    public static ParseObject createWithoutData(String className, String objectId) {
        OfflineStore store = Parse.getLocalDatastore();
        try {
            try {
                if (objectId == null) {
                    isCreatingPointerForObjectId.set(NEW_OFFLINE_OBJECT_ID_PLACEHOLDER);
                } else {
                    isCreatingPointerForObjectId.set(objectId);
                }
                ParseObject object = null;
                if (!(store == null || objectId == null)) {
                    object = store.getObject(className, objectId);
                }
                if (object == null) {
                    object = create(className);
                    if (object.hasChanges()) {
                        throw new IllegalStateException("A ParseObject subclass default constructor must not make changes to the object that cause it to be dirty.");
                    }
                }
                return object;
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new RuntimeException("Failed to create instance of subclass.", e2);
            }
        } finally {
            isCreatingPointerForObjectId.set(null);
        }
    }

    public static <T extends ParseObject> T createWithoutData(Class<T> subclass, String objectId) {
        return (T) createWithoutData(getSubclassingController().getClassName(subclass), objectId);
    }

    public static void registerSubclass(Class<? extends ParseObject> subclass) {
        getSubclassingController().registerSubclass(subclass);
    }

    static void unregisterSubclass(Class<? extends ParseObject> subclass) {
        getSubclassingController().unregisterSubclass(subclass);
    }

    static <T> Task<T> enqueueForAll(List<? extends ParseObject> objects, Continuation<Void, Task<T>> taskStart) {
        LockSet lock;
        final TaskCompletionSource<Void> readyToStart = new TaskCompletionSource<>();
        List<Lock> locks = new ArrayList<>(objects.size());
        for (ParseObject obj : objects) {
            locks.add(obj.taskQueue.getLock());
        }
        try {
            lock = new LockSet(locks);
            lock.lock();
            try {
                try {
                    final Task<T> fullTask = taskStart.then(readyToStart.getTask());
                    final List<Task<Void>> childTasks = new ArrayList<>();
                    for (ParseObject obj2 : objects) {
                        obj2.taskQueue.enqueue(new Continuation<Void, Task<T>>() { // from class: com.parse.ParseObject.2
                            @Override // bolts.Continuation
                            public Task<T> then(Task<Void> task) throws Exception {
                                childTasks.add(task);
                                return fullTask;
                            }
                        });
                    }
                    Task.whenAll(childTasks).continueWith(new Continuation<Void, Void>() { // from class: com.parse.ParseObject.3
                        @Override // bolts.Continuation
                        public Void then(Task<Void> task) throws Exception {
                            readyToStart.setResult(null);
                            return null;
                        }
                    });
                    return fullTask;
                } catch (RuntimeException e) {
                    throw e;
                }
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        } finally {
            lock.unlock();
        }
    }

    public static <T extends ParseObject> T from(State state) {
        State newState;
        T object = (T) createWithoutData(state.className(), state.objectId());
        synchronized (object.mutex) {
            if (state.isComplete()) {
                newState = state;
            } else {
                newState = object.getState().newBuilder().apply(state).build();
            }
            object.setState(newState);
        }
        return object;
    }

    public static <T extends ParseObject> T fromJSON(JSONObject json, String defaultClassName, boolean isComplete) {
        return (T) fromJSON(json, defaultClassName, isComplete, ParseDecoder.get());
    }

    public static <T extends ParseObject> T fromJSON(JSONObject json, String defaultClassName, boolean isComplete, ParseDecoder decoder) {
        String className = json.optString(KEY_CLASS_NAME, defaultClassName);
        if (className == null) {
            return null;
        }
        T object = (T) createWithoutData(className, json.optString(KEY_OBJECT_ID, null));
        object.setState(object.mergeFromServer(object.getState(), json, decoder, isComplete));
        return object;
    }

    static <T extends ParseObject> T fromJSONPayload(JSONObject json, ParseDecoder decoder) {
        String className = json.optString(KEY_CLASS_NAME);
        if (className == null || ParseTextUtils.isEmpty(className)) {
            return null;
        }
        T object = (T) createWithoutData(className, json.optString(KEY_OBJECT_ID, null));
        object.build(json, decoder);
        return object;
    }

    State.Init<?> newStateBuilder(String className) {
        return new State.Builder(className);
    }

    public State getState() {
        State state;
        synchronized (this.mutex) {
            state = this.state;
        }
        return state;
    }

    public void setState(State newState) {
        synchronized (this.mutex) {
            setState(newState, true);
        }
    }

    private void setState(State newState, boolean notifyIfObjectIdChanges) {
        synchronized (this.mutex) {
            String oldObjectId = this.state.objectId();
            String newObjectId = newState.objectId();
            this.state = newState;
            if (notifyIfObjectIdChanges && !ParseTextUtils.equals(oldObjectId, newObjectId)) {
                notifyObjectIdChanged(oldObjectId, newObjectId);
            }
            rebuildEstimatedData();
        }
    }

    public String getClassName() {
        String className;
        synchronized (this.mutex) {
            className = this.state.className();
        }
        return className;
    }

    public Date getUpdatedAt() {
        long updatedAt = getState().updatedAt();
        if (updatedAt > 0) {
            return new Date(updatedAt);
        }
        return null;
    }

    public Date getCreatedAt() {
        long createdAt = getState().createdAt();
        if (createdAt > 0) {
            return new Date(createdAt);
        }
        return null;
    }

    public Set<String> keySet() {
        Set<String> unmodifiableSet;
        synchronized (this.mutex) {
            unmodifiableSet = Collections.unmodifiableSet(this.estimatedData.keySet());
        }
        return unmodifiableSet;
    }

    void copyChangesFrom(ParseObject other) {
        synchronized (this.mutex) {
            ParseOperationSet operations = other.operationSetQueue.getFirst();
            for (String key : operations.keySet()) {
                performOperation(key, operations.get(key));
            }
        }
    }

    void mergeFromObject(ParseObject other) {
        synchronized (this.mutex) {
            if (this != other) {
                setState(other.getState().newBuilder().build(), false);
            }
        }
    }

    public void revert(String key) {
        synchronized (this.mutex) {
            if (isDirty(key)) {
                currentOperations().remove(key);
                rebuildEstimatedData();
            }
        }
    }

    public void revert() {
        synchronized (this.mutex) {
            if (isDirty()) {
                currentOperations().clear();
                rebuildEstimatedData();
            }
        }
    }

    public Map<String, ParseObject> collectFetchedObjects() {
        final Map<String, ParseObject> fetchedObjects = new HashMap<>();
        new ParseTraverser() { // from class: com.parse.ParseObject.4
            @Override // com.parse.ParseTraverser
            protected boolean visit(Object object) {
                if (!(object instanceof ParseObject)) {
                    return true;
                }
                ParseObject parseObj = (ParseObject) object;
                State state = parseObj.getState();
                if (state.objectId() == null || !state.isComplete()) {
                    return true;
                }
                fetchedObjects.put(state.objectId(), parseObj);
                return true;
            }
        }.traverse(this.estimatedData);
        return fetchedObjects;
    }

    void build(JSONObject json, ParseDecoder decoder) {
        try {
            State.Builder builder = new State.Builder(this.state).isComplete(true);
            builder.clear();
            Iterator<?> keys = json.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                if (!key.equals(KEY_CLASS_NAME)) {
                    if (key.equals(KEY_OBJECT_ID)) {
                        builder.objectId(json.getString(key));
                    } else if (key.equals(KEY_CREATED_AT)) {
                        builder.createdAt(ParseDateFormat.getInstance().parse(json.getString(key)));
                    } else if (key.equals(KEY_UPDATED_AT)) {
                        builder.updatedAt(ParseDateFormat.getInstance().parse(json.getString(key)));
                    } else {
                        Object decodedObject = decoder.decode(json.get(key));
                        if (decodedObject instanceof ParseFieldOperation) {
                            performOperation(key, (ParseFieldOperation) decodedObject);
                        } else {
                            put(key, decodedObject);
                        }
                    }
                }
            }
            setState(builder.build());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    State mergeFromServer(State state, JSONObject json, ParseDecoder decoder, boolean completeData) {
        try {
            State.Init<?> builder = state.newBuilder();
            if (completeData) {
                builder.clear();
            }
            builder.isComplete(state.isComplete() || completeData);
            Iterator<?> keys = json.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                if (!key.equals("__type") && !key.equals(KEY_CLASS_NAME)) {
                    if (key.equals(KEY_OBJECT_ID)) {
                        builder.objectId(json.getString(key));
                    } else if (key.equals(KEY_CREATED_AT)) {
                        builder.createdAt(ParseDateFormat.getInstance().parse(json.getString(key)));
                    } else if (key.equals(KEY_UPDATED_AT)) {
                        builder.updatedAt(ParseDateFormat.getInstance().parse(json.getString(key)));
                    } else if (key.equals(KEY_ACL)) {
                        builder.put(KEY_ACL, ParseACL.createACLFromJSONObject(json.getJSONObject(key), decoder));
                    } else {
                        builder.put(key, decoder.decode(json.get(key)));
                    }
                }
            }
            return builder.build();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject toRest(ParseEncoder encoder) {
        State state;
        List<ParseOperationSet> operationSetQueueCopy;
        synchronized (this.mutex) {
            state = getState();
            int operationSetQueueSize = this.operationSetQueue.size();
            operationSetQueueCopy = new ArrayList<>(operationSetQueueSize);
            for (int i = 0; i < operationSetQueueSize; i++) {
                operationSetQueueCopy.add(new ParseOperationSet(this.operationSetQueue.get(i)));
            }
        }
        return toRest(state, operationSetQueueCopy, encoder);
    }

    public JSONObject toRest(State state, List<ParseOperationSet> operationSetQueue, ParseEncoder objectEncoder) {
        JSONObject json = new JSONObject();
        try {
            json.put(KEY_CLASS_NAME, state.className());
            if (state.objectId() != null) {
                json.put(KEY_OBJECT_ID, state.objectId());
            }
            if (state.createdAt() > 0) {
                json.put(KEY_CREATED_AT, ParseDateFormat.getInstance().format(new Date(state.createdAt())));
            }
            if (state.updatedAt() > 0) {
                json.put(KEY_UPDATED_AT, ParseDateFormat.getInstance().format(new Date(state.updatedAt())));
            }
            for (String key : state.keySet()) {
                json.put(key, objectEncoder.encode(state.get(key)));
            }
            json.put(KEY_COMPLETE, state.isComplete());
            json.put(KEY_IS_DELETING_EVENTUALLY, this.isDeletingEventually);
            JSONArray operations = new JSONArray();
            for (ParseOperationSet operationSet : operationSetQueue) {
                operations.put(operationSet.toRest(objectEncoder));
            }
            json.put(KEY_OPERATIONS, operations);
            return json;
        } catch (JSONException e) {
            throw new RuntimeException("could not serialize object to JSON");
        }
    }

    public void mergeREST(State state, JSONObject json, ParseDecoder decoder) {
        ArrayList<ParseOperationSet> saveEventuallyOperationSets = new ArrayList<>();
        synchronized (this.mutex) {
            try {
                boolean isComplete = json.getBoolean(KEY_COMPLETE);
                this.isDeletingEventually = ParseJSONUtils.getInt(json, Arrays.asList(KEY_IS_DELETING_EVENTUALLY, KEY_IS_DELETING_EVENTUALLY_OLD));
                JSONArray operations = json.getJSONArray(KEY_OPERATIONS);
                ParseOperationSet newerOperations = currentOperations();
                this.operationSetQueue.clear();
                ParseOperationSet current = null;
                for (int i = 0; i < operations.length(); i++) {
                    ParseOperationSet operationSet = ParseOperationSet.fromRest(operations.getJSONObject(i), decoder);
                    if (operationSet.isSaveEventually()) {
                        if (current != null) {
                            this.operationSetQueue.add(current);
                            current = null;
                        }
                        saveEventuallyOperationSets.add(operationSet);
                        this.operationSetQueue.add(operationSet);
                    } else {
                        if (current != null) {
                            operationSet.mergeFrom(current);
                        }
                        current = operationSet;
                    }
                }
                if (current != null) {
                    this.operationSetQueue.add(current);
                }
                currentOperations().mergeFrom(newerOperations);
                boolean mergeServerData = false;
                if (state.updatedAt() < 0) {
                    mergeServerData = true;
                } else if (json.has(KEY_UPDATED_AT) && new Date(state.updatedAt()).compareTo(ParseDateFormat.getInstance().parse(json.getString(KEY_UPDATED_AT))) < 0) {
                    mergeServerData = true;
                }
                if (mergeServerData) {
                    setState(mergeFromServer(state, ParseJSONUtils.create(json, Arrays.asList(KEY_COMPLETE, KEY_IS_DELETING_EVENTUALLY, KEY_IS_DELETING_EVENTUALLY_OLD, KEY_OPERATIONS)), decoder, isComplete));
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        Iterator i$ = saveEventuallyOperationSets.iterator();
        while (i$.hasNext()) {
            enqueueSaveEventuallyOperationAsync(i$.next());
        }
    }

    private boolean hasDirtyChildren() {
        boolean z;
        synchronized (this.mutex) {
            List<ParseObject> unsavedChildren = new ArrayList<>();
            collectDirtyChildren(this.estimatedData, unsavedChildren, null);
            z = unsavedChildren.size() > 0;
        }
        return z;
    }

    public boolean isDirty() {
        return isDirty(true);
    }

    boolean isDirty(boolean considerChildren) {
        boolean z;
        synchronized (this.mutex) {
            z = this.isDeleted || getObjectId() == null || hasChanges() || (considerChildren && hasDirtyChildren());
        }
        return z;
    }

    public boolean hasChanges() {
        boolean z;
        synchronized (this.mutex) {
            z = currentOperations().size() > 0;
        }
        return z;
    }

    public boolean hasOutstandingOperations() {
        boolean z = true;
        synchronized (this.mutex) {
            if (this.operationSetQueue.size() <= 1) {
                z = false;
            }
        }
        return z;
    }

    public boolean isDirty(String key) {
        boolean containsKey;
        synchronized (this.mutex) {
            containsKey = currentOperations().containsKey(key);
        }
        return containsKey;
    }

    public String getObjectId() {
        String objectId;
        synchronized (this.mutex) {
            objectId = this.state.objectId();
        }
        return objectId;
    }

    public void setObjectId(String newObjectId) {
        synchronized (this.mutex) {
            String oldObjectId = this.state.objectId();
            if (!ParseTextUtils.equals(oldObjectId, newObjectId)) {
                this.state = this.state.newBuilder().objectId(newObjectId).build();
                notifyObjectIdChanged(oldObjectId, newObjectId);
            }
        }
    }

    public String getOrCreateLocalId() {
        String str;
        synchronized (this.mutex) {
            if (this.localId == null) {
                if (this.state.objectId() != null) {
                    throw new IllegalStateException("Attempted to get a localId for an object with an objectId.");
                }
                this.localId = getLocalIdManager().createLocalId();
            }
            str = this.localId;
        }
        return str;
    }

    private void notifyObjectIdChanged(String oldObjectId, String newObjectId) {
        synchronized (this.mutex) {
            OfflineStore store = Parse.getLocalDatastore();
            if (store != null) {
                store.updateObjectId(this, oldObjectId, newObjectId);
            }
            if (this.localId != null) {
                getLocalIdManager().setObjectId(this.localId, newObjectId);
                this.localId = null;
            }
        }
    }

    private ParseRESTObjectCommand currentSaveEventuallyCommand(ParseOperationSet operations, ParseEncoder objectEncoder, String sessionToken) throws ParseException {
        State state = getState();
        ParseRESTObjectCommand command = ParseRESTObjectCommand.saveObjectCommand(state, toJSONObjectForSaving(state, operations, objectEncoder), sessionToken);
        command.enableRetrying();
        return command;
    }

    <T extends State> JSONObject toJSONObjectForSaving(T state, ParseOperationSet operations, ParseEncoder objectEncoder) {
        JSONObject objectJSON = new JSONObject();
        try {
            for (String key : operations.keySet()) {
                objectJSON.put(key, objectEncoder.encode((ParseFieldOperation) operations.get(key)));
            }
            if (state.objectId() != null) {
                objectJSON.put(KEY_OBJECT_ID, state.objectId());
            }
            return objectJSON;
        } catch (JSONException e) {
            throw new RuntimeException("could not serialize object to JSON");
        }
    }

    Task<Void> handleSaveResultAsync(JSONObject result, ParseOperationSet operationsBeforeSave) {
        State newState = null;
        if (result != null) {
            synchronized (this.mutex) {
                newState = ParseObjectCoder.get().decode(getState().newBuilder().clear(), result, new KnownParseObjectDecoder(collectFetchedObjects())).isComplete(false).build();
            }
        }
        return handleSaveResultAsync(newState, operationsBeforeSave);
    }

    public Task<Void> handleSaveResultAsync(final State result, final ParseOperationSet operationsBeforeSave) {
        Task forResult = Task.forResult(null);
        boolean success = result != null;
        synchronized (this.mutex) {
            ListIterator<ParseOperationSet> opIterator = this.operationSetQueue.listIterator(this.operationSetQueue.indexOf(operationsBeforeSave));
            opIterator.next();
            opIterator.remove();
            if (!success) {
                opIterator.next().mergeFrom(operationsBeforeSave);
                return forResult;
            }
            final OfflineStore store = Parse.getLocalDatastore();
            if (store != null) {
                forResult = forResult.onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.5
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<Void> task) throws Exception {
                        return store.fetchLocallyAsync(ParseObject.this).makeVoid();
                    }
                });
            }
            Task continueWith = forResult.continueWith(new Continuation<Void, Void>() { // from class: com.parse.ParseObject.6
                @Override // bolts.Continuation
                public Void then(Task<Void> task) throws Exception {
                    State newState;
                    synchronized (ParseObject.this.mutex) {
                        if (result.isComplete()) {
                            newState = result;
                        } else {
                            newState = ParseObject.this.getState().newBuilder().apply(operationsBeforeSave).apply(result).build();
                        }
                        ParseObject.this.setState(newState);
                    }
                    return null;
                }
            });
            if (store != null) {
                continueWith = continueWith.onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.7
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<Void> task) throws Exception {
                        return store.updateDataForObjectAsync(ParseObject.this);
                    }
                });
            }
            return continueWith.onSuccess(new Continuation<Void, Void>() { // from class: com.parse.ParseObject.8
                @Override // bolts.Continuation
                public Void then(Task<Void> task) throws Exception {
                    ParseObject.this.saveEvent.invoke(ParseObject.this, null);
                    return null;
                }
            });
        }
    }

    ParseOperationSet startSave() {
        ParseOperationSet currentOperations;
        synchronized (this.mutex) {
            currentOperations = currentOperations();
            this.operationSetQueue.addLast(new ParseOperationSet());
        }
        return currentOperations;
    }

    public void validateSave() {
    }

    public final void save() throws ParseException {
        ParseTaskUtils.wait(saveInBackground());
    }

    public final Task<Void> saveInBackground() {
        return ParseUser.getCurrentUserAsync().onSuccessTask(new Continuation<ParseUser, Task<String>>() { // from class: com.parse.ParseObject.10
            @Override // bolts.Continuation
            public Task<String> then(Task<ParseUser> task) throws Exception {
                ParseUser current = task.getResult();
                if (current == null) {
                    return Task.forResult(null);
                }
                if (!current.isLazy()) {
                    return Task.forResult(current.getSessionToken());
                }
                if (!ParseObject.this.isDataAvailable(ParseObject.KEY_ACL)) {
                    return Task.forResult(null);
                }
                final ParseACL acl = ParseObject.this.getACL(false);
                if (acl == null) {
                    return Task.forResult(null);
                }
                final ParseUser user = acl.getUnresolvedUser();
                if (user == null || !user.isCurrentUser()) {
                    return Task.forResult(null);
                }
                return user.saveAsync(null).onSuccess(new Continuation<Void, String>() { // from class: com.parse.ParseObject.10.1
                    @Override // bolts.Continuation
                    public String then(Task<Void> task2) throws Exception {
                        if (!acl.hasUnresolvedUser()) {
                            return user.getSessionToken();
                        }
                        throw new IllegalStateException("ACL has an unresolved ParseUser. Save or sign up before attempting to serialize the ACL.");
                    }
                });
            }
        }).onSuccessTask(new Continuation<String, Task<Void>>() { // from class: com.parse.ParseObject.9
            @Override // bolts.Continuation
            public Task<Void> then(Task<String> task) throws Exception {
                return ParseObject.this.saveAsync(task.getResult());
            }
        });
    }

    Task<Void> saveAsync(final String sessionToken) {
        return this.taskQueue.enqueue(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.11
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> toAwait) throws Exception {
                return ParseObject.this.saveAsync(sessionToken, toAwait);
            }
        });
    }

    public Task<Void> saveAsync(final String sessionToken, Task<Void> toAwait) {
        final ParseOperationSet operations;
        Task<Void> task;
        if (!isDirty()) {
            return Task.forResult(null);
        }
        synchronized (this.mutex) {
            updateBeforeSave();
            validateSave();
            operations = startSave();
        }
        synchronized (this.mutex) {
            task = deepSaveAsync(this.estimatedData, sessionToken);
        }
        return task.onSuccessTask(TaskQueue.waitFor(toAwait)).onSuccessTask(new Continuation<Void, Task<State>>() { // from class: com.parse.ParseObject.13
            @Override // bolts.Continuation
            public Task<State> then(Task<Void> task2) throws Exception {
                return ParseObject.getObjectController().saveAsync(ParseObject.this.getState(), operations, sessionToken, new KnownParseObjectDecoder(ParseObject.this.collectFetchedObjects()));
            }
        }).continueWithTask(new Continuation<State, Task<Void>>() { // from class: com.parse.ParseObject.12
            @Override // bolts.Continuation
            public Task<Void> then(final Task<State> saveTask) throws Exception {
                return ParseObject.this.handleSaveResultAsync(saveTask.getResult(), operations).continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.12.1
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<Void> task2) throws Exception {
                        return (task2.isFaulted() || task2.isCancelled()) ? task2 : saveTask.makeVoid();
                    }
                });
            }
        });
    }

    public Task<JSONObject> saveAsync(ParseHttpClient client, ParseOperationSet operationSet, String sessionToken) throws ParseException {
        return currentSaveEventuallyCommand(operationSet, PointerEncoder.get(), sessionToken).executeAsync(client);
    }

    public final void saveInBackground(SaveCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(saveInBackground(), callback);
    }

    void validateSaveEventually() throws ParseException {
    }

    public final void saveEventually(SaveCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(saveEventually(), callback);
    }

    public final Task<Void> saveEventually() {
        final ParseOperationSet operationSet;
        ParseRESTCommand command;
        if (!isDirty()) {
            Parse.getEventuallyQueue().fakeObjectUpdate();
            return Task.forResult(null);
        }
        synchronized (this.mutex) {
            updateBeforeSave();
            try {
                validateSaveEventually();
                List<ParseObject> unsavedChildren = new ArrayList<>();
                collectDirtyChildren(this.estimatedData, unsavedChildren, null);
                String localId = null;
                if (getObjectId() == null) {
                    localId = getOrCreateLocalId();
                }
                operationSet = startSave();
                operationSet.setIsSaveEventually(true);
                try {
                    command = currentSaveEventuallyCommand(operationSet, PointerOrLocalIdEncoder.get(), ParseUser.getCurrentSessionToken());
                    command.setLocalId(localId);
                    command.setOperationSetUUID(operationSet.getUUID());
                    command.retainLocalIds();
                    for (ParseObject object : unsavedChildren) {
                        object.saveEventually();
                    }
                } catch (ParseException exception) {
                    throw new IllegalStateException("Unable to saveEventually.", exception);
                }
            } catch (ParseException e) {
                return Task.forError(e);
            }
        }
        Task<JSONObject> runEventuallyTask = Parse.getEventuallyQueue().enqueueEventuallyAsync(command, this);
        enqueueSaveEventuallyOperationAsync(operationSet);
        command.releaseLocalIds();
        if (Parse.isLocalDatastoreEnabled()) {
            return runEventuallyTask.makeVoid();
        }
        return runEventuallyTask.onSuccessTask(new Continuation<JSONObject, Task<Void>>() { // from class: com.parse.ParseObject.14
            @Override // bolts.Continuation
            public Task<Void> then(Task<JSONObject> task) throws Exception {
                return ParseObject.this.handleSaveEventuallyResultAsync(task.getResult(), operationSet);
            }
        });
    }

    private Task<Void> enqueueSaveEventuallyOperationAsync(final ParseOperationSet operationSet) {
        if (operationSet.isSaveEventually()) {
            return this.taskQueue.enqueue(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.15
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> toAwait) throws Exception {
                    return toAwait.continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.15.1
                        @Override // bolts.Continuation
                        public Task<Void> then(Task<Void> task) throws Exception {
                            return Parse.getEventuallyQueue().waitForOperationSetAndEventuallyPin(operationSet, null).makeVoid();
                        }
                    });
                }
            });
        }
        throw new IllegalStateException("This should only be used to enqueue saveEventually operation sets");
    }

    public Task<Void> handleSaveEventuallyResultAsync(JSONObject json, ParseOperationSet operationSet) {
        final boolean success = json != null;
        return handleSaveResultAsync(json, operationSet).onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.16
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task) throws Exception {
                if (success) {
                    Parse.getEventuallyQueue().notifyTestHelper(5);
                }
                return task;
            }
        });
    }

    public void updateBeforeSave() {
    }

    public final void deleteEventually(DeleteCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(deleteEventually(), callback);
    }

    public final Task<Void> deleteEventually() {
        Task<JSONObject> runEventuallyTask;
        synchronized (this.mutex) {
            validateDelete();
            this.isDeletingEventually++;
            String localId = null;
            if (getObjectId() == null) {
                localId = getOrCreateLocalId();
            }
            ParseRESTCommand command = ParseRESTObjectCommand.deleteObjectCommand(getState(), ParseUser.getCurrentSessionToken());
            command.enableRetrying();
            command.setLocalId(localId);
            runEventuallyTask = Parse.getEventuallyQueue().enqueueEventuallyAsync(command, this);
        }
        if (Parse.isLocalDatastoreEnabled()) {
            return runEventuallyTask.makeVoid();
        }
        return runEventuallyTask.onSuccessTask(new Continuation<JSONObject, Task<Void>>() { // from class: com.parse.ParseObject.17
            @Override // bolts.Continuation
            public Task<Void> then(Task<JSONObject> task) throws Exception {
                return ParseObject.this.handleDeleteEventuallyResultAsync();
            }
        });
    }

    public Task<Void> handleDeleteEventuallyResultAsync() {
        synchronized (this.mutex) {
            this.isDeletingEventually--;
        }
        return handleDeleteResultAsync().onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.18
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task) throws Exception {
                Parse.getEventuallyQueue().notifyTestHelper(6);
                return task;
            }
        });
    }

    public Task<Void> handleFetchResultAsync(final State result) {
        Task<Void> task = Task.forResult(null);
        final OfflineStore store = Parse.getLocalDatastore();
        if (store != null) {
            task = task.onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.20
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task2) throws Exception {
                    return store.fetchLocallyAsync(ParseObject.this).makeVoid();
                }
            }).continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.19
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task2) throws Exception {
                    if (!(task2.getError() instanceof ParseException) || ((ParseException) task2.getError()).getCode() != 120) {
                        return task2;
                    }
                    return null;
                }
            });
        }
        Task<Void> task2 = task.onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.21
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task3) throws Exception {
                State newState;
                synchronized (ParseObject.this.mutex) {
                    if (result.isComplete()) {
                        newState = result;
                    } else {
                        newState = ParseObject.this.getState().newBuilder().apply(result).build();
                    }
                    ParseObject.this.setState(newState);
                }
                return null;
            }
        });
        if (store != null) {
            return task2.onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.23
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task3) throws Exception {
                    return store.updateDataForObjectAsync(ParseObject.this);
                }
            }).continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.22
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task3) throws Exception {
                    if (!(task3.getError() instanceof ParseException) || ((ParseException) task3.getError()).getCode() != 120) {
                        return task3;
                    }
                    return null;
                }
            });
        }
        return task2;
    }

    @Deprecated
    public final void refresh() throws ParseException {
        fetch();
    }

    @Deprecated
    public final void refreshInBackground(RefreshCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(fetchInBackground(), callback);
    }

    public <T extends ParseObject> T fetch() throws ParseException {
        return (T) ((ParseObject) ParseTaskUtils.wait(fetchInBackground()));
    }

    public <T extends ParseObject> Task<T> fetchAsync(final String sessionToken, Task<Void> toAwait) {
        return toAwait.onSuccessTask(new Continuation<Void, Task<State>>() { // from class: com.parse.ParseObject.26
            @Override // bolts.Continuation
            public Task<State> then(Task<Void> task) throws Exception {
                State state;
                Map<String, ParseObject> fetchedObjects;
                synchronized (ParseObject.this.mutex) {
                    state = ParseObject.this.getState();
                    fetchedObjects = ParseObject.this.collectFetchedObjects();
                }
                return ParseObject.getObjectController().fetchAsync(state, sessionToken, new KnownParseObjectDecoder(fetchedObjects));
            }
        }).onSuccessTask(new Continuation<State, Task<Void>>() { // from class: com.parse.ParseObject.25
            @Override // bolts.Continuation
            public Task<Void> then(Task<State> task) throws Exception {
                return ParseObject.this.handleFetchResultAsync(task.getResult());
            }
        }).onSuccess(new Continuation<Void, T>() { // from class: com.parse.ParseObject.24
            @Override // bolts.Continuation
            /* renamed from: then */
            public ParseObject then2(Task<Void> task) throws Exception {
                return ParseObject.this;
            }
        });
    }

    public final <T extends ParseObject> Task<T> fetchInBackground() {
        return (Task<T>) ParseUser.getCurrentSessionTokenAsync().onSuccessTask(new Continuation<String, Task<T>>() { // from class: com.parse.ParseObject.27
            @Override // bolts.Continuation
            public Task<T> then(Task<String> task) throws Exception {
                final String sessionToken = task.getResult();
                return ParseObject.this.taskQueue.enqueue(new Continuation<Void, Task<T>>() { // from class: com.parse.ParseObject.27.1
                    @Override // bolts.Continuation
                    public Task<T> then(Task<Void> toAwait) throws Exception {
                        return ParseObject.this.fetchAsync(sessionToken, toAwait);
                    }
                });
            }
        });
    }

    public final <T extends ParseObject> void fetchInBackground(GetCallback<T> callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(fetchInBackground(), callback);
    }

    public final <T extends ParseObject> Task<T> fetchIfNeededInBackground() {
        return isDataAvailable() ? Task.forResult(this) : (Task<T>) ParseUser.getCurrentSessionTokenAsync().onSuccessTask(new Continuation<String, Task<T>>() { // from class: com.parse.ParseObject.28
            @Override // bolts.Continuation
            public Task<T> then(Task<String> task) throws Exception {
                final String sessionToken = task.getResult();
                return ParseObject.this.taskQueue.enqueue(new Continuation<Void, Task<T>>() { // from class: com.parse.ParseObject.28.1
                    @Override // bolts.Continuation
                    public Task<T> then(Task<Void> toAwait) throws Exception {
                        return ParseObject.this.isDataAvailable() ? Task.forResult(ParseObject.this) : ParseObject.this.fetchAsync(sessionToken, toAwait);
                    }
                });
            }
        });
    }

    public <T extends ParseObject> T fetchIfNeeded() throws ParseException {
        return (T) ((ParseObject) ParseTaskUtils.wait(fetchIfNeededInBackground()));
    }

    public final <T extends ParseObject> void fetchIfNeededInBackground(GetCallback<T> callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(fetchIfNeededInBackground(), callback);
    }

    public void validateDelete() {
    }

    public Task<Void> deleteAsync(final String sessionToken, Task<Void> toAwait) {
        validateDelete();
        return toAwait.onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.30
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task) throws Exception {
                return ParseObject.this.state.objectId() == null ? task.cast() : ParseObject.this.deleteAsync(sessionToken);
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.29
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task) throws Exception {
                return ParseObject.this.handleDeleteResultAsync();
            }
        });
    }

    public Task<Void> deleteAsync(String sessionToken) throws ParseException {
        return getObjectController().deleteAsync(getState(), sessionToken);
    }

    Task<Void> handleDeleteResultAsync() {
        Task<Void> task = Task.forResult(null);
        synchronized (this.mutex) {
            this.isDeleted = true;
        }
        final OfflineStore store = Parse.getLocalDatastore();
        if (store != null) {
            return task.continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.31
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task2) throws Exception {
                    Task<Void> updateDataForObjectAsync;
                    synchronized (ParseObject.this.mutex) {
                        if (ParseObject.this.isDeleted) {
                            store.unregisterObject(ParseObject.this);
                            updateDataForObjectAsync = store.deleteDataForObjectAsync(ParseObject.this);
                        } else {
                            updateDataForObjectAsync = store.updateDataForObjectAsync(ParseObject.this);
                        }
                    }
                    return updateDataForObjectAsync;
                }
            });
        }
        return task;
    }

    public final Task<Void> deleteInBackground() {
        return ParseUser.getCurrentSessionTokenAsync().onSuccessTask(new Continuation<String, Task<Void>>() { // from class: com.parse.ParseObject.32
            @Override // bolts.Continuation
            public Task<Void> then(Task<String> task) throws Exception {
                final String sessionToken = task.getResult();
                return ParseObject.this.taskQueue.enqueue(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.32.1
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<Void> toAwait) throws Exception {
                        return ParseObject.this.deleteAsync(sessionToken, toAwait);
                    }
                });
            }
        });
    }

    public final void delete() throws ParseException {
        ParseTaskUtils.wait(deleteInBackground());
    }

    public final void deleteInBackground(DeleteCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(deleteInBackground(), callback);
    }

    public static <T extends ParseObject> Task<Void> deleteAllAsync(List<T> objects, final String sessionToken) {
        if (objects.size() == 0) {
            return Task.forResult(null);
        }
        int objectCount = objects.size();
        final List<ParseObject> uniqueObjects = new ArrayList<>(objectCount);
        HashSet<String> idSet = new HashSet<>();
        for (int i = 0; i < objectCount; i++) {
            ParseObject obj = objects.get(i);
            if (!idSet.contains(obj.getObjectId())) {
                idSet.add(obj.getObjectId());
                uniqueObjects.add(obj);
            }
        }
        return enqueueForAll(uniqueObjects, new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.33
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> toAwait) throws Exception {
                return ParseObject.deleteAllAsync(uniqueObjects, sessionToken, toAwait);
            }
        });
    }

    public static <T extends ParseObject> Task<Void> deleteAllAsync(final List<T> uniqueObjects, final String sessionToken, Task<Void> toAwait) {
        return toAwait.continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.34
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task) throws Exception {
                int objectCount = uniqueObjects.size();
                List<State> states = new ArrayList<>(objectCount);
                for (int i = 0; i < objectCount; i++) {
                    ParseObject object = (ParseObject) uniqueObjects.get(i);
                    object.validateDelete();
                    states.add(object.getState());
                }
                List<Task<Void>> batchTasks = ParseObject.getObjectController().deleteAllAsync(states, sessionToken);
                ArrayList arrayList = new ArrayList(objectCount);
                for (int i2 = 0; i2 < objectCount; i2++) {
                    final ParseObject parseObject = (ParseObject) uniqueObjects.get(i2);
                    arrayList.add(batchTasks.get(i2).onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.34.1
                        @Override // bolts.Continuation
                        public Task<Void> then(final Task<Void> batchTask) throws Exception {
                            return parseObject.handleDeleteResultAsync().continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.34.1.1
                                @Override // bolts.Continuation
                                public Task<Void> then(Task<Void> task2) throws Exception {
                                    return batchTask;
                                }
                            });
                        }
                    }));
                }
                return Task.whenAll(arrayList);
            }
        });
    }

    public static <T extends ParseObject> void deleteAll(List<T> objects) throws ParseException {
        ParseTaskUtils.wait(deleteAllInBackground(objects));
    }

    public static <T extends ParseObject> void deleteAllInBackground(List<T> objects, DeleteCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(deleteAllInBackground(objects), callback);
    }

    public static <T extends ParseObject> Task<Void> deleteAllInBackground(final List<T> objects) {
        return ParseUser.getCurrentSessionTokenAsync().onSuccessTask(new Continuation<String, Task<Void>>() { // from class: com.parse.ParseObject.35
            @Override // bolts.Continuation
            public Task<Void> then(Task<String> task) throws Exception {
                return ParseObject.deleteAllAsync(objects, task.getResult());
            }
        });
    }

    public static void collectDirtyChildren(Object node, final Collection<ParseObject> dirtyChildren, final Collection<ParseFile> dirtyFiles, final Set<ParseObject> alreadySeen, final Set<ParseObject> alreadySeenNew) {
        new ParseTraverser() { // from class: com.parse.ParseObject.36
            @Override // com.parse.ParseTraverser
            protected boolean visit(Object node2) {
                Set<ParseObject> seenNew;
                if (node2 instanceof ParseFile) {
                    if (dirtyFiles != null) {
                        ParseFile file = (ParseFile) node2;
                        if (file.getUrl() == null) {
                            dirtyFiles.add(file);
                        }
                    }
                } else if ((node2 instanceof ParseObject) && dirtyChildren != null) {
                    ParseObject object = (ParseObject) node2;
                    Set<ParseObject> seen = alreadySeen;
                    Set<ParseObject> seenNew2 = alreadySeenNew;
                    if (object.getObjectId() != null) {
                        seenNew = new HashSet<>();
                    } else if (seenNew2.contains(object)) {
                        throw new RuntimeException("Found a circular dependency while saving.");
                    } else {
                        Set<ParseObject> seenNew3 = new HashSet<>(seenNew2);
                        seenNew3.add(object);
                        seenNew = seenNew3;
                    }
                    if (!seen.contains(object)) {
                        Set<ParseObject> seen2 = new HashSet<>(seen);
                        seen2.add(object);
                        ParseObject.collectDirtyChildren(object.estimatedData, dirtyChildren, dirtyFiles, seen2, seenNew);
                        if (object.isDirty(false)) {
                            dirtyChildren.add(object);
                        }
                    }
                }
                return true;
            }
        }.setYieldRoot(true).traverse(node);
    }

    private static void collectDirtyChildren(Object node, Collection<ParseObject> dirtyChildren, Collection<ParseFile> dirtyFiles) {
        collectDirtyChildren(node, dirtyChildren, dirtyFiles, new HashSet(), new HashSet());
    }

    public boolean canBeSerialized() {
        boolean booleanValue;
        synchronized (this.mutex) {
            final Capture<Boolean> result = new Capture<>(true);
            new ParseTraverser() { // from class: com.parse.ParseObject.37
                @Override // com.parse.ParseTraverser
                protected boolean visit(Object value) {
                    if ((value instanceof ParseFile) && ((ParseFile) value).isDirty()) {
                        result.set(false);
                    }
                    if ((value instanceof ParseObject) && ((ParseObject) value).getObjectId() == null) {
                        result.set(false);
                    }
                    return ((Boolean) result.get()).booleanValue();
                }
            }.setYieldRoot(false).setTraverseParseObjects(true).traverse(this);
            booleanValue = result.get().booleanValue();
        }
        return booleanValue;
    }

    public static Task<Void> deepSaveAsync(Object object, final String sessionToken) {
        Set<ParseObject> objects = new HashSet<>();
        Set<ParseFile> files = new HashSet<>();
        collectDirtyChildren(object, objects, files);
        Set<ParseUser> users = new HashSet<>();
        for (ParseObject o : objects) {
            if ((o instanceof ParseUser) && ((ParseUser) o).isLazy()) {
                users.add((ParseUser) o);
            }
        }
        objects.removeAll(users);
        final AtomicBoolean filesComplete = new AtomicBoolean(false);
        List<Task<Void>> tasks = new ArrayList<>();
        for (ParseFile file : files) {
            tasks.add(file.saveAsync(sessionToken, null, null));
        }
        Task continueWith = Task.whenAll(tasks).continueWith(new Continuation<Void, Void>() { // from class: com.parse.ParseObject.38
            @Override // bolts.Continuation
            public Void then(Task<Void> task) throws Exception {
                filesComplete.set(true);
                return null;
            }
        });
        final AtomicBoolean usersComplete = new AtomicBoolean(false);
        List<Task<Void>> tasks2 = new ArrayList<>();
        for (ParseUser user : users) {
            tasks2.add(user.saveAsync(sessionToken));
        }
        Task continueWith2 = Task.whenAll(tasks2).continueWith(new Continuation<Void, Void>() { // from class: com.parse.ParseObject.39
            @Override // bolts.Continuation
            public Void then(Task<Void> task) throws Exception {
                usersComplete.set(true);
                return null;
            }
        });
        final Capture<Set<ParseObject>> remaining = new Capture<>(objects);
        return Task.whenAll(Arrays.asList(continueWith, continueWith2, Task.forResult(null).continueWhile(new Callable<Boolean>() { // from class: com.parse.ParseObject.40
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                return Boolean.valueOf(((Set) remaining.get()).size() > 0);
            }
        }, new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.41
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task) throws Exception {
                final List<ParseObject> current = new ArrayList<>();
                HashSet hashSet = new HashSet();
                for (ParseObject obj : (Set) remaining.get()) {
                    if (obj.canBeSerialized()) {
                        current.add(obj);
                    } else {
                        hashSet.add(obj);
                    }
                }
                remaining.set(hashSet);
                if (current.size() != 0 || !filesComplete.get() || !usersComplete.get()) {
                    return current.size() == 0 ? Task.forResult(null) : ParseObject.enqueueForAll(current, new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.41.1
                        @Override // bolts.Continuation
                        public Task<Void> then(Task<Void> toAwait) throws Exception {
                            return ParseObject.saveAllAsync(current, sessionToken, toAwait);
                        }
                    });
                }
                throw new RuntimeException("Unable to save a ParseObject with a relation to a cycle.");
            }
        })));
    }

    public static <T extends ParseObject> Task<Void> saveAllAsync(final List<T> uniqueObjects, final String sessionToken, Task<Void> toAwait) {
        return toAwait.continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.42
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task) throws Exception {
                int objectCount = uniqueObjects.size();
                List<State> states = new ArrayList<>(objectCount);
                List<ParseOperationSet> operationsList = new ArrayList<>(objectCount);
                List<ParseDecoder> decoders = new ArrayList<>(objectCount);
                for (int i = 0; i < objectCount; i++) {
                    ParseObject object = (ParseObject) uniqueObjects.get(i);
                    object.updateBeforeSave();
                    object.validateSave();
                    states.add(object.getState());
                    operationsList.add(object.startSave());
                    decoders.add(new KnownParseObjectDecoder(object.collectFetchedObjects()));
                }
                List<Task<State>> batchTasks = ParseObject.getObjectController().saveAllAsync(states, operationsList, sessionToken, decoders);
                ArrayList arrayList = new ArrayList(objectCount);
                for (int i2 = 0; i2 < objectCount; i2++) {
                    final ParseObject parseObject = (ParseObject) uniqueObjects.get(i2);
                    final ParseOperationSet operations = operationsList.get(i2);
                    arrayList.add(batchTasks.get(i2).continueWithTask(new Continuation<State, Task<Void>>() { // from class: com.parse.ParseObject.42.1
                        @Override // bolts.Continuation
                        public Task<Void> then(final Task<State> batchTask) throws Exception {
                            return parseObject.handleSaveResultAsync(batchTask.getResult(), operations).continueWithTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.42.1.1
                                @Override // bolts.Continuation
                                public Task<Void> then(Task<Void> task2) throws Exception {
                                    return (task2.isFaulted() || task2.isCancelled()) ? task2 : batchTask.makeVoid();
                                }
                            });
                        }
                    }));
                }
                return Task.whenAll(arrayList);
            }
        });
    }

    public static <T extends ParseObject> void saveAll(List<T> objects) throws ParseException {
        ParseTaskUtils.wait(saveAllInBackground(objects));
    }

    public static <T extends ParseObject> void saveAllInBackground(List<T> objects, SaveCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(saveAllInBackground(objects), callback);
    }

    public static <T extends ParseObject> Task<Void> saveAllInBackground(final List<T> objects) {
        return ParseUser.getCurrentUserAsync().onSuccessTask(new Continuation<ParseUser, Task<String>>() { // from class: com.parse.ParseObject.44
            @Override // bolts.Continuation
            public Task<String> then(Task<ParseUser> task) throws Exception {
                final ParseACL acl;
                final ParseUser user;
                ParseUser current = task.getResult();
                if (current == null) {
                    return Task.forResult(null);
                }
                if (!current.isLazy()) {
                    return Task.forResult(current.getSessionToken());
                }
                for (ParseObject object : objects) {
                    if (object.isDataAvailable(ParseObject.KEY_ACL) && (acl = object.getACL(false)) != null && (user = acl.getUnresolvedUser()) != null && user.isCurrentUser()) {
                        return user.saveAsync(null).onSuccess(new Continuation<Void, String>() { // from class: com.parse.ParseObject.44.1
                            @Override // bolts.Continuation
                            public String then(Task<Void> task2) throws Exception {
                                if (!acl.hasUnresolvedUser()) {
                                    return user.getSessionToken();
                                }
                                throw new IllegalStateException("ACL has an unresolved ParseUser. Save or sign up before attempting to serialize the ACL.");
                            }
                        });
                    }
                }
                return Task.forResult(null);
            }
        }).onSuccessTask(new Continuation<String, Task<Void>>() { // from class: com.parse.ParseObject.43
            @Override // bolts.Continuation
            public Task<Void> then(Task<String> task) throws Exception {
                return ParseObject.deepSaveAsync(objects, task.getResult());
            }
        });
    }

    public static <T extends ParseObject> Task<List<T>> fetchAllIfNeededInBackground(List<T> objects) {
        return fetchAllAsync(objects, true);
    }

    public static <T extends ParseObject> List<T> fetchAllIfNeeded(List<T> objects) throws ParseException {
        return (List) ParseTaskUtils.wait(fetchAllIfNeededInBackground(objects));
    }

    public static <T extends ParseObject> void fetchAllIfNeededInBackground(List<T> objects, FindCallback<T> callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(fetchAllIfNeededInBackground(objects), callback);
    }

    private static <T extends ParseObject> Task<List<T>> fetchAllAsync(final List<T> objects, final boolean onlyIfNeeded) {
        return (Task<List<T>>) ParseUser.getCurrentUserAsync().onSuccessTask(new Continuation<ParseUser, Task<List<T>>>() { // from class: com.parse.ParseObject.45
            @Override // bolts.Continuation
            public Task<List<T>> then(Task<ParseUser> task) throws Exception {
                final ParseUser user = task.getResult();
                return ParseObject.enqueueForAll(objects, new Continuation<Void, Task<List<T>>>() { // from class: com.parse.ParseObject.45.1
                    @Override // bolts.Continuation
                    public Task<List<T>> then(Task<Void> task2) throws Exception {
                        return ParseObject.fetchAllAsync(objects, user, onlyIfNeeded, task2);
                    }
                });
            }
        });
    }

    public static <T extends ParseObject> Task<List<T>> fetchAllAsync(final List<T> objects, final ParseUser user, final boolean onlyIfNeeded, Task<Void> toAwait) {
        if (objects.size() == 0) {
            return Task.forResult(objects);
        }
        List<String> objectIds = new ArrayList<>();
        String className = null;
        for (T object : objects) {
            if (!onlyIfNeeded || !object.isDataAvailable()) {
                if (className == null || object.getClassName().equals(className)) {
                    className = object.getClassName();
                    if (object.getObjectId() != null) {
                        objectIds.add(object.getObjectId());
                    } else if (!onlyIfNeeded) {
                        throw new IllegalArgumentException("All objects must exist on the server");
                    }
                } else {
                    throw new IllegalArgumentException("All objects should have the same class");
                }
            }
        }
        if (objectIds.size() == 0) {
            return Task.forResult(objects);
        }
        final ParseQuery<T> query = ParseQuery.getQuery(className).whereContainedIn(KEY_OBJECT_ID, objectIds);
        return toAwait.continueWithTask(new Continuation<Void, Task<List<T>>>() { // from class: com.parse.ParseObject.47
            @Override // bolts.Continuation
            public Task<List<T>> then(Task<Void> task) throws Exception {
                return query.findAsync(query.getBuilder().build(), user, null);
            }
        }).onSuccess(new Continuation<List<T>, List<T>>() { // from class: com.parse.ParseObject.46
            @Override // bolts.Continuation
            public List<T> then(Task<List<T>> task) throws Exception {
                HashMap hashMap = new HashMap();
                for (ParseObject parseObject : (List) task.getResult()) {
                    hashMap.put(parseObject.getObjectId(), parseObject);
                }
                for (ParseObject parseObject2 : objects) {
                    if (!onlyIfNeeded || !parseObject2.isDataAvailable()) {
                        ParseObject parseObject3 = (ParseObject) hashMap.get(parseObject2.getObjectId());
                        if (parseObject3 == null) {
                            throw new ParseException(101, "Object id " + parseObject2.getObjectId() + " does not exist");
                        } else if (!Parse.isLocalDatastoreEnabled()) {
                            parseObject2.mergeFromObject(parseObject3);
                        }
                    }
                }
                return objects;
            }
        });
    }

    public static <T extends ParseObject> Task<List<T>> fetchAllInBackground(List<T> objects) {
        return fetchAllAsync(objects, false);
    }

    public static <T extends ParseObject> List<T> fetchAll(List<T> objects) throws ParseException {
        return (List) ParseTaskUtils.wait(fetchAllInBackground(objects));
    }

    public static <T extends ParseObject> void fetchAllInBackground(List<T> objects, FindCallback<T> callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(fetchAllInBackground(objects), callback);
    }

    private ParseOperationSet currentOperations() {
        ParseOperationSet last;
        synchronized (this.mutex) {
            last = this.operationSetQueue.getLast();
        }
        return last;
    }

    private void applyOperations(ParseOperationSet operations, Map<String, Object> map) {
        for (String key : operations.keySet()) {
            Object newValue = operations.get(key).apply(map.get(key), key);
            if (newValue != null) {
                map.put(key, newValue);
            } else {
                map.remove(key);
            }
        }
    }

    private void rebuildEstimatedData() {
        synchronized (this.mutex) {
            this.estimatedData.clear();
            for (String key : this.state.keySet()) {
                this.estimatedData.put(key, this.state.get(key));
            }
            Iterator i$ = this.operationSetQueue.iterator();
            while (i$.hasNext()) {
                applyOperations(i$.next(), this.estimatedData);
            }
        }
    }

    public void performOperation(String key, ParseFieldOperation operation) {
        synchronized (this.mutex) {
            Object newValue = operation.apply(this.estimatedData.get(key), key);
            if (newValue != null) {
                this.estimatedData.put(key, newValue);
            } else {
                this.estimatedData.remove(key);
            }
            currentOperations().put(key, operation.mergeWithPrevious(currentOperations().get(key)));
        }
    }

    public void put(String key, Object value) {
        checkKeyIsMutable(key);
        performPut(key, value);
    }

    void performPut(String key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("key may not be null.");
        } else if (value == null) {
            throw new IllegalArgumentException("value may not be null.");
        } else {
            if (value instanceof JSONObject) {
                value = ParseDecoder.get().convertJSONObjectToMap((JSONObject) value);
            } else if (value instanceof JSONArray) {
                value = ParseDecoder.get().convertJSONArrayToList((JSONArray) value);
            }
            if (!ParseEncoder.isValidType(value)) {
                throw new IllegalArgumentException("invalid type for value: " + value.getClass().toString());
            }
            performOperation(key, new ParseSetOperation(value));
        }
    }

    public boolean has(String key) {
        return containsKey(key);
    }

    public void increment(String key) {
        increment(key, 1);
    }

    public void increment(String key, Number amount) {
        performOperation(key, new ParseIncrementOperation(amount));
    }

    public void add(String key, Object value) {
        addAll(key, Arrays.asList(value));
    }

    public void addAll(String key, Collection<?> values) {
        performOperation(key, new ParseAddOperation(values));
    }

    public void addUnique(String key, Object value) {
        addAllUnique(key, Arrays.asList(value));
    }

    public void addAllUnique(String key, Collection<?> values) {
        performOperation(key, new ParseAddUniqueOperation(values));
    }

    public void remove(String key) {
        checkKeyIsMutable(key);
        performRemove(key);
    }

    void performRemove(String key) {
        synchronized (this.mutex) {
            if (get(key) != null) {
                performOperation(key, ParseDeleteOperation.getInstance());
            }
        }
    }

    public void removeAll(String key, Collection<?> values) {
        checkKeyIsMutable(key);
        performOperation(key, new ParseRemoveOperation(values));
    }

    private void checkKeyIsMutable(String key) {
        if (!isKeyMutable(key)) {
            throw new IllegalArgumentException("Cannot modify `" + key + "` property of an " + getClassName() + " object.");
        }
    }

    boolean isKeyMutable(String key) {
        return true;
    }

    public boolean containsKey(String key) {
        boolean containsKey;
        synchronized (this.mutex) {
            containsKey = this.estimatedData.containsKey(key);
        }
        return containsKey;
    }

    public String getString(String key) {
        String str;
        synchronized (this.mutex) {
            checkGetAccess(key);
            Object value = this.estimatedData.get(key);
            if (!(value instanceof String)) {
                str = null;
            } else {
                str = (String) value;
            }
        }
        return str;
    }

    public byte[] getBytes(String key) {
        Object obj;
        synchronized (this.mutex) {
            checkGetAccess(key);
            Object value = this.estimatedData.get(key);
            if (!(value instanceof byte[])) {
                obj = null;
            } else {
                obj = (byte[]) value;
            }
        }
        return obj;
    }

    public Number getNumber(String key) {
        Number number;
        synchronized (this.mutex) {
            checkGetAccess(key);
            Object value = this.estimatedData.get(key);
            if (!(value instanceof Number)) {
                number = null;
            } else {
                number = (Number) value;
            }
        }
        return number;
    }

    public JSONArray getJSONArray(String key) {
        JSONArray jSONArray;
        synchronized (this.mutex) {
            checkGetAccess(key);
            Object value = this.estimatedData.get(key);
            if (value instanceof List) {
                value = PointerOrLocalIdEncoder.get().encode(value);
            }
            if (!(value instanceof JSONArray)) {
                jSONArray = null;
            } else {
                jSONArray = (JSONArray) value;
            }
        }
        return jSONArray;
    }

    public <T> List<T> getList(String key) {
        List<T> list;
        synchronized (this.mutex) {
            Object value = this.estimatedData.get(key);
            if (!(value instanceof List)) {
                list = null;
            } else {
                list = (List) value;
            }
        }
        return list;
    }

    public <V> Map<String, V> getMap(String key) {
        Map<String, V> map;
        synchronized (this.mutex) {
            Object value = this.estimatedData.get(key);
            if (!(value instanceof Map)) {
                map = null;
            } else {
                map = (Map) value;
            }
        }
        return map;
    }

    public JSONObject getJSONObject(String key) {
        JSONObject jSONObject;
        synchronized (this.mutex) {
            checkGetAccess(key);
            Object value = this.estimatedData.get(key);
            if (value instanceof Map) {
                value = PointerOrLocalIdEncoder.get().encode(value);
            }
            if (!(value instanceof JSONObject)) {
                jSONObject = null;
            } else {
                jSONObject = (JSONObject) value;
            }
        }
        return jSONObject;
    }

    public int getInt(String key) {
        Number number = getNumber(key);
        if (number == null) {
            return 0;
        }
        return number.intValue();
    }

    public double getDouble(String key) {
        Number number = getNumber(key);
        if (number == null) {
            return 0.0d;
        }
        return number.doubleValue();
    }

    public long getLong(String key) {
        Number number = getNumber(key);
        if (number == null) {
            return 0L;
        }
        return number.longValue();
    }

    public boolean getBoolean(String key) {
        boolean booleanValue;
        synchronized (this.mutex) {
            checkGetAccess(key);
            Object value = this.estimatedData.get(key);
            if (!(value instanceof Boolean)) {
                booleanValue = false;
            } else {
                booleanValue = ((Boolean) value).booleanValue();
            }
        }
        return booleanValue;
    }

    public Date getDate(String key) {
        Date date;
        synchronized (this.mutex) {
            checkGetAccess(key);
            Object value = this.estimatedData.get(key);
            if (!(value instanceof Date)) {
                date = null;
            } else {
                date = (Date) value;
            }
        }
        return date;
    }

    public ParseObject getParseObject(String key) {
        Object value = get(key);
        if (!(value instanceof ParseObject)) {
            return null;
        }
        return (ParseObject) value;
    }

    public ParseUser getParseUser(String key) {
        Object value = get(key);
        if (!(value instanceof ParseUser)) {
            return null;
        }
        return (ParseUser) value;
    }

    public ParseFile getParseFile(String key) {
        Object value = get(key);
        if (!(value instanceof ParseFile)) {
            return null;
        }
        return (ParseFile) value;
    }

    public ParseGeoPoint getParseGeoPoint(String key) {
        ParseGeoPoint parseGeoPoint;
        synchronized (this.mutex) {
            checkGetAccess(key);
            Object value = this.estimatedData.get(key);
            if (!(value instanceof ParseGeoPoint)) {
                parseGeoPoint = null;
            } else {
                parseGeoPoint = (ParseGeoPoint) value;
            }
        }
        return parseGeoPoint;
    }

    public ParseACL getACL() {
        return getACL(true);
    }

    public ParseACL getACL(boolean mayCopy) {
        synchronized (this.mutex) {
            checkGetAccess(KEY_ACL);
            Object acl = this.estimatedData.get(KEY_ACL);
            if (acl == null) {
                return null;
            }
            if (!(acl instanceof ParseACL)) {
                throw new RuntimeException("only ACLs can be stored in the ACL key");
            } else if (!mayCopy || !((ParseACL) acl).isShared()) {
                return (ParseACL) acl;
            } else {
                ParseACL copy = new ParseACL((ParseACL) acl);
                this.estimatedData.put(KEY_ACL, copy);
                return copy;
            }
        }
    }

    public void setACL(ParseACL acl) {
        put(KEY_ACL, acl);
    }

    public boolean isDataAvailable() {
        boolean isComplete;
        synchronized (this.mutex) {
            isComplete = this.state.isComplete();
        }
        return isComplete;
    }

    boolean isDataAvailable(String key) {
        boolean z;
        synchronized (this.mutex) {
            z = isDataAvailable() || this.estimatedData.containsKey(key);
        }
        return z;
    }

    /* JADX WARN: Generic types in debug info not equals: java.lang.Object != com.parse.ParseRelation<T extends com.parse.ParseObject> */
    public <T extends ParseObject> ParseRelation<T> getRelation(String key) {
        synchronized (this.mutex) {
            Object value = this.estimatedData.get(key);
            if (value instanceof ParseRelation) {
                ParseRelation<T> relation = (ParseRelation) value;
                relation.ensureParentAndKey(this, key);
                return relation;
            }
            ParseRelation<T> relation2 = new ParseRelation<>(this, key);
            this.estimatedData.put(key, relation2);
            return relation2;
        }
    }

    public Object get(String key) {
        Object value;
        synchronized (this.mutex) {
            if (key.equals(KEY_ACL)) {
                value = getACL();
            } else {
                checkGetAccess(key);
                value = this.estimatedData.get(key);
                if (value instanceof ParseRelation) {
                    ((ParseRelation) value).ensureParentAndKey(this, key);
                }
            }
        }
        return value;
    }

    private void checkGetAccess(String key) {
        if (!isDataAvailable(key)) {
            throw new IllegalStateException("ParseObject has no data for '" + key + "'. Call fetchIfNeeded() to get the data.");
        }
    }

    public boolean hasSameId(ParseObject other) {
        boolean z;
        synchronized (this.mutex) {
            z = getClassName() != null && getObjectId() != null && getClassName().equals(other.getClassName()) && getObjectId().equals(other.getObjectId());
        }
        return z;
    }

    void registerSaveListener(GetCallback<ParseObject> callback) {
        synchronized (this.mutex) {
            this.saveEvent.subscribe(callback);
        }
    }

    public void unregisterSaveListener(GetCallback<ParseObject> callback) {
        synchronized (this.mutex) {
            this.saveEvent.unsubscribe(callback);
        }
    }

    void setDefaultValues() {
        if (needsDefaultACL() && ParseACL.getDefaultACL() != null) {
            setACL(ParseACL.getDefaultACL());
        }
    }

    boolean needsDefaultACL() {
        return true;
    }

    public static void registerParseSubclasses() {
        registerSubclass(ParseUser.class);
        registerSubclass(ParseRole.class);
        registerSubclass(ParseInstallation.class);
        registerSubclass(ParseSession.class);
        registerSubclass(ParsePin.class);
        registerSubclass(EventuallyPin.class);
    }

    static void unregisterParseSubclasses() {
        unregisterSubclass(ParseUser.class);
        unregisterSubclass(ParseRole.class);
        unregisterSubclass(ParseInstallation.class);
        unregisterSubclass(ParseSession.class);
        unregisterSubclass(ParsePin.class);
        unregisterSubclass(EventuallyPin.class);
    }

    public static <T extends ParseObject> void pinAllInBackground(String name, List<T> objects, SaveCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(pinAllInBackground(name, objects), callback);
    }

    public static <T extends ParseObject> Task<Void> pinAllInBackground(String name, List<T> objects) {
        return pinAllInBackground(name, (List) objects, true);
    }

    private static <T extends ParseObject> Task<Void> pinAllInBackground(final String name, final List<T> objects, final boolean includeAllChildren) {
        if (!Parse.isLocalDatastoreEnabled()) {
            throw new IllegalStateException("Method requires Local Datastore. Please refer to `Parse#enableLocalDatastore(Context)`.");
        }
        Task<Void> task = Task.forResult(null);
        for (T object : objects) {
            task = task.onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.48
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task2) throws Exception {
                    if (!ParseObject.this.isDataAvailable(ParseObject.KEY_ACL)) {
                        return Task.forResult(null);
                    }
                    ParseACL acl = ParseObject.this.getACL(false);
                    if (acl == null) {
                        return Task.forResult(null);
                    }
                    ParseUser user = acl.getUnresolvedUser();
                    if (user == null || !user.isCurrentUser()) {
                        return Task.forResult(null);
                    }
                    return ParseUser.pinCurrentUserIfNeededAsync(user);
                }
            });
        }
        return task.onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.50
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task2) throws Exception {
                return Parse.getLocalDatastore().pinAllObjectsAsync(name != null ? name : ParseObject.DEFAULT_PIN, objects, includeAllChildren);
            }
        }).onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.ParseObject.49
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task2) throws Exception {
                if ("_currentUser".equals(name)) {
                    return task2;
                }
                for (ParseObject object2 : objects) {
                    if (object2 instanceof ParseUser) {
                        ParseUser user = (ParseUser) object2;
                        if (user.isCurrentUser()) {
                            return ParseUser.pinCurrentUserIfNeededAsync(user);
                        }
                    }
                }
                return task2;
            }
        });
    }

    public static <T extends ParseObject> void pinAll(String name, List<T> objects) throws ParseException {
        ParseTaskUtils.wait(pinAllInBackground(name, objects));
    }

    public static <T extends ParseObject> void pinAllInBackground(List<T> objects, SaveCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(pinAllInBackground(DEFAULT_PIN, objects), callback);
    }

    public static <T extends ParseObject> Task<Void> pinAllInBackground(List<T> objects) {
        return pinAllInBackground(DEFAULT_PIN, objects);
    }

    public static <T extends ParseObject> void pinAll(List<T> objects) throws ParseException {
        ParseTaskUtils.wait(pinAllInBackground(DEFAULT_PIN, objects));
    }

    public static <T extends ParseObject> void unpinAllInBackground(String name, List<T> objects, DeleteCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(unpinAllInBackground(name, objects), callback);
    }

    public static <T extends ParseObject> Task<Void> unpinAllInBackground(String name, List<T> objects) {
        if (!Parse.isLocalDatastoreEnabled()) {
            throw new IllegalStateException("Method requires Local Datastore. Please refer to `Parse#enableLocalDatastore(Context)`.");
        }
        if (name == null) {
            name = DEFAULT_PIN;
        }
        return Parse.getLocalDatastore().unpinAllObjectsAsync(name, objects);
    }

    public static <T extends ParseObject> void unpinAll(String name, List<T> objects) throws ParseException {
        ParseTaskUtils.wait(unpinAllInBackground(name, objects));
    }

    public static <T extends ParseObject> void unpinAllInBackground(List<T> objects, DeleteCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(unpinAllInBackground(DEFAULT_PIN, objects), callback);
    }

    public static <T extends ParseObject> Task<Void> unpinAllInBackground(List<T> objects) {
        return unpinAllInBackground(DEFAULT_PIN, objects);
    }

    public static <T extends ParseObject> void unpinAll(List<T> objects) throws ParseException {
        ParseTaskUtils.wait(unpinAllInBackground(DEFAULT_PIN, objects));
    }

    public static void unpinAllInBackground(String name, DeleteCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(unpinAllInBackground(name), callback);
    }

    public static Task<Void> unpinAllInBackground(String name) {
        if (!Parse.isLocalDatastoreEnabled()) {
            throw new IllegalStateException("Method requires Local Datastore. Please refer to `Parse#enableLocalDatastore(Context)`.");
        }
        if (name == null) {
            name = DEFAULT_PIN;
        }
        return Parse.getLocalDatastore().unpinAllObjectsAsync(name);
    }

    public static void unpinAll(String name) throws ParseException {
        ParseTaskUtils.wait(unpinAllInBackground(name));
    }

    public static void unpinAllInBackground(DeleteCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(unpinAllInBackground(), callback);
    }

    public static Task<Void> unpinAllInBackground() {
        return unpinAllInBackground(DEFAULT_PIN);
    }

    public static void unpinAll() throws ParseException {
        ParseTaskUtils.wait(unpinAllInBackground());
    }

    public <T extends ParseObject> Task<T> fetchFromLocalDatastoreAsync() {
        if (Parse.isLocalDatastoreEnabled()) {
            return Parse.getLocalDatastore().fetchLocallyAsync(this);
        }
        throw new IllegalStateException("Method requires Local Datastore. Please refer to `Parse#enableLocalDatastore(Context)`.");
    }

    public <T extends ParseObject> void fetchFromLocalDatastoreInBackground(GetCallback<T> callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(fetchFromLocalDatastoreAsync(), callback);
    }

    public void fetchFromLocalDatastore() throws ParseException {
        ParseTaskUtils.wait(fetchFromLocalDatastoreAsync());
    }

    public void pinInBackground(String name, SaveCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(pinInBackground(name), callback);
    }

    public Task<Void> pinInBackground(String name) {
        return pinAllInBackground(name, Collections.singletonList(this));
    }

    public Task<Void> pinInBackground(String name, boolean includeAllChildren) {
        return pinAllInBackground(name, Collections.singletonList(this), includeAllChildren);
    }

    public void pin(String name) throws ParseException {
        ParseTaskUtils.wait(pinInBackground(name));
    }

    public void pinInBackground(SaveCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(pinInBackground(), callback);
    }

    public Task<Void> pinInBackground() {
        return pinAllInBackground(DEFAULT_PIN, Arrays.asList(this));
    }

    public void pin() throws ParseException {
        ParseTaskUtils.wait(pinInBackground());
    }

    public void unpinInBackground(String name, DeleteCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(unpinInBackground(name), callback);
    }

    public Task<Void> unpinInBackground(String name) {
        return unpinAllInBackground(name, Arrays.asList(this));
    }

    public void unpin(String name) throws ParseException {
        ParseTaskUtils.wait(unpinInBackground(name));
    }

    public void unpinInBackground(DeleteCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(unpinInBackground(), callback);
    }

    public Task<Void> unpinInBackground() {
        return unpinAllInBackground(DEFAULT_PIN, Arrays.asList(this));
    }

    public void unpin() throws ParseException {
        ParseTaskUtils.wait(unpinInBackground());
    }
}

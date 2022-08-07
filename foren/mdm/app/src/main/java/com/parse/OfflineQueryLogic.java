package com.parse;

import bolts.Continuation;
import bolts.Task;
import com.http.config.URLConfig;
import com.hyphenate.util.EMPrivateConstant;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class OfflineQueryLogic {
    private final OfflineStore store;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public interface Decider {
        boolean decide(Object obj, Object obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public abstract class ConstraintMatcher<T extends ParseObject> {
        final ParseUser user;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Task<Boolean> matchesAsync(T t, ParseSQLiteDatabase parseSQLiteDatabase);

        public ConstraintMatcher(ParseUser user) {
            this.user = user;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OfflineQueryLogic(OfflineStore store) {
        this.store = store;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object getValue(Object container, String key) throws ParseException {
        return getValue(container, key, 0);
    }

    private static Object getValue(Object container, String key, int depth) throws ParseException {
        if (key.contains(".")) {
            String[] parts = key.split("\\.", 2);
            Object value = getValue(container, parts[0], depth + 1);
            if (value == null || value == JSONObject.NULL || (value instanceof Map) || (value instanceof JSONObject)) {
                return getValue(value, parts[1], depth + 1);
            }
            if (depth > 0) {
                Object restFormat = null;
                try {
                    restFormat = PointerEncoder.get().encode(value);
                } catch (Exception e) {
                }
                if (restFormat instanceof JSONObject) {
                    return getValue(restFormat, parts[1], depth + 1);
                }
            }
            throw new ParseException(102, String.format("Key %s is invalid.", key));
        } else if (container instanceof ParseObject) {
            ParseObject object = (ParseObject) container;
            if (!object.isDataAvailable()) {
                throw new ParseException(121, String.format("Bad key: %s", key));
            }
            char c = 65535;
            switch (key.hashCode()) {
                case -1949194674:
                    if (key.equals("updatedAt")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1836974455:
                    if (key.equals("_created_at")) {
                        c = 2;
                        break;
                    }
                    break;
                case 90495162:
                    if (key.equals("objectId")) {
                        c = 0;
                        break;
                    }
                    break;
                case 598371643:
                    if (key.equals("createdAt")) {
                        c = 1;
                        break;
                    }
                    break;
                case 792848342:
                    if (key.equals("_updated_at")) {
                        c = 4;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    return object.getObjectId();
                case 1:
                case 2:
                    return object.getCreatedAt();
                case 3:
                case 4:
                    return object.getUpdatedAt();
                default:
                    return object.get(key);
            }
        } else if (container instanceof JSONObject) {
            return ((JSONObject) container).opt(key);
        } else {
            if (container instanceof Map) {
                return ((Map) container).get(key);
            }
            if (container == JSONObject.NULL || container == null) {
                return null;
            }
            throw new ParseException(121, String.format("Bad key: %s", key));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int compareTo(Object lhs, Object rhs) {
        boolean lhsIsNullOrUndefined;
        if (lhs == JSONObject.NULL || lhs == null) {
            lhsIsNullOrUndefined = true;
        } else {
            lhsIsNullOrUndefined = false;
        }
        boolean rhsIsNullOrUndefined = rhs == JSONObject.NULL || rhs == null;
        if (lhsIsNullOrUndefined || rhsIsNullOrUndefined) {
            if (!lhsIsNullOrUndefined) {
                return 1;
            }
            return !rhsIsNullOrUndefined ? -1 : 0;
        } else if ((lhs instanceof Date) && (rhs instanceof Date)) {
            return ((Date) lhs).compareTo((Date) rhs);
        } else {
            if ((lhs instanceof String) && (rhs instanceof String)) {
                return ((String) lhs).compareTo((String) rhs);
            }
            if ((lhs instanceof Number) && (rhs instanceof Number)) {
                return Numbers.compare((Number) lhs, (Number) rhs);
            }
            throw new IllegalArgumentException(String.format("Cannot compare %s against %s", lhs, rhs));
        }
    }

    private static boolean compareList(Object constraint, List<?> values, Decider decider) {
        Iterator i$ = values.iterator();
        while (i$.hasNext()) {
            if (decider.decide(constraint, i$.next())) {
                return true;
            }
        }
        return false;
    }

    private static boolean compareArray(Object constraint, JSONArray values, Decider decider) {
        for (int i = 0; i < values.length(); i++) {
            try {
                if (decider.decide(constraint, values.get(i))) {
                    return true;
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    private static boolean compare(Object constraint, Object value, Decider decider) {
        if (value instanceof List) {
            return compareList(constraint, (List) value, decider);
        }
        if (value instanceof JSONArray) {
            return compareArray(constraint, (JSONArray) value, decider);
        }
        return decider.decide(constraint, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean matchesEqualConstraint(Object constraint, Object value) {
        if (constraint == null || value == null) {
            return constraint == value;
        }
        if ((constraint instanceof Number) && (value instanceof Number)) {
            return compareTo(constraint, value) == 0;
        }
        if (!(constraint instanceof ParseGeoPoint) || !(value instanceof ParseGeoPoint)) {
            return compare(constraint, value, new Decider() { // from class: com.parse.OfflineQueryLogic.1
                @Override // com.parse.OfflineQueryLogic.Decider
                public boolean decide(Object constraint2, Object value2) {
                    return constraint2.equals(value2);
                }
            });
        }
        ParseGeoPoint lhs = (ParseGeoPoint) constraint;
        ParseGeoPoint rhs = (ParseGeoPoint) value;
        return lhs.getLatitude() == rhs.getLatitude() && lhs.getLongitude() == rhs.getLongitude();
    }

    private static boolean matchesNotEqualConstraint(Object constraint, Object value) {
        return !matchesEqualConstraint(constraint, value);
    }

    private static boolean matchesLessThanConstraint(Object constraint, Object value) {
        return compare(constraint, value, new Decider() { // from class: com.parse.OfflineQueryLogic.2
            @Override // com.parse.OfflineQueryLogic.Decider
            public boolean decide(Object constraint2, Object value2) {
                return (value2 == null || value2 == JSONObject.NULL || OfflineQueryLogic.compareTo(constraint2, value2) <= 0) ? false : true;
            }
        });
    }

    private static boolean matchesLessThanOrEqualToConstraint(Object constraint, Object value) {
        return compare(constraint, value, new Decider() { // from class: com.parse.OfflineQueryLogic.3
            @Override // com.parse.OfflineQueryLogic.Decider
            public boolean decide(Object constraint2, Object value2) {
                return (value2 == null || value2 == JSONObject.NULL || OfflineQueryLogic.compareTo(constraint2, value2) < 0) ? false : true;
            }
        });
    }

    private static boolean matchesGreaterThanConstraint(Object constraint, Object value) {
        return compare(constraint, value, new Decider() { // from class: com.parse.OfflineQueryLogic.4
            @Override // com.parse.OfflineQueryLogic.Decider
            public boolean decide(Object constraint2, Object value2) {
                return (value2 == null || value2 == JSONObject.NULL || OfflineQueryLogic.compareTo(constraint2, value2) >= 0) ? false : true;
            }
        });
    }

    private static boolean matchesGreaterThanOrEqualToConstraint(Object constraint, Object value) {
        return compare(constraint, value, new Decider() { // from class: com.parse.OfflineQueryLogic.5
            @Override // com.parse.OfflineQueryLogic.Decider
            public boolean decide(Object constraint2, Object value2) {
                return (value2 == null || value2 == JSONObject.NULL || OfflineQueryLogic.compareTo(constraint2, value2) > 0) ? false : true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean matchesInConstraint(Object constraint, Object value) {
        if (constraint instanceof Collection) {
            for (Object requiredItem : (Collection) constraint) {
                if (matchesEqualConstraint(requiredItem, value)) {
                    return true;
                }
            }
            return false;
        }
        throw new IllegalArgumentException("Constraint type not supported for $in queries.");
    }

    private static boolean matchesNotInConstraint(Object constraint, Object value) {
        return !matchesInConstraint(constraint, value);
    }

    private static boolean matchesAllConstraint(Object constraint, Object value) {
        if (value == null || value == JSONObject.NULL) {
            return false;
        }
        if (!(value instanceof Collection)) {
            throw new IllegalArgumentException("Value type not supported for $all queries.");
        } else if (constraint instanceof Collection) {
            for (Object requiredItem : (Collection) constraint) {
                if (!matchesEqualConstraint(requiredItem, value)) {
                    return false;
                }
            }
            return true;
        } else {
            throw new IllegalArgumentException("Constraint type not supported for $all queries.");
        }
    }

    private static boolean matchesRegexConstraint(Object constraint, Object value, String options) throws ParseException {
        if (value == null || value == JSONObject.NULL) {
            return false;
        }
        if (options == null) {
            options = "";
        }
        if (!options.matches("^[imxs]*$")) {
            throw new ParseException(102, String.format("Invalid regex options: %s", options));
        }
        int flags = 0;
        if (options.contains("i")) {
            flags = 0 | 2;
        }
        if (options.contains("m")) {
            flags |= 8;
        }
        if (options.contains(EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME)) {
            flags |= 4;
        }
        if (options.contains(URLConfig.baidu_url)) {
            flags |= 32;
        }
        return Pattern.compile((String) constraint, flags).matcher((String) value).find();
    }

    private static boolean matchesExistsConstraint(Object constraint, Object value) {
        boolean z = false;
        if (constraint != null && ((Boolean) constraint).booleanValue()) {
            return (value == null || value == JSONObject.NULL) ? false : true;
        }
        if (value == null || value == JSONObject.NULL) {
            z = true;
        }
        return z;
    }

    private static boolean matchesNearSphereConstraint(Object constraint, Object value, Double maxDistance) {
        if (value == null || value == JSONObject.NULL) {
            return false;
        }
        return maxDistance == null || ((ParseGeoPoint) constraint).distanceInRadiansTo((ParseGeoPoint) value) <= maxDistance.doubleValue();
    }

    private static boolean matchesWithinConstraint(Object constraint, Object value) throws ParseException {
        if (value == null || value == JSONObject.NULL) {
            return false;
        }
        ArrayList<ParseGeoPoint> box = ((HashMap) constraint).get("$box");
        ParseGeoPoint southwest = box.get(0);
        ParseGeoPoint northeast = box.get(1);
        ParseGeoPoint target = (ParseGeoPoint) value;
        if (northeast.getLongitude() < southwest.getLongitude()) {
            throw new ParseException(102, "whereWithinGeoBox queries cannot cross the International Date Line.");
        } else if (northeast.getLatitude() < southwest.getLatitude()) {
            throw new ParseException(102, "The southwest corner of a geo box must be south of the northeast corner.");
        } else if (northeast.getLongitude() - southwest.getLongitude() <= 180.0d) {
            return target.getLatitude() >= southwest.getLatitude() && target.getLatitude() <= northeast.getLatitude() && target.getLongitude() >= southwest.getLongitude() && target.getLongitude() <= northeast.getLongitude();
        } else {
            throw new ParseException(102, "Geo box queries larger than 180 degrees in longitude are not supported. Please check point order.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean matchesStatelessConstraint(String operator, Object constraint, Object value, ParseQuery.KeyConstraints allKeyConstraints) throws ParseException {
        char c = 65535;
        switch (operator.hashCode()) {
            case -1622199595:
                if (operator.equals("$maxDistance")) {
                    c = '\f';
                    break;
                }
                break;
            case -443727559:
                if (operator.equals("$nearSphere")) {
                    c = 11;
                    break;
                }
                break;
            case 37905:
                if (operator.equals("$gt")) {
                    c = 3;
                    break;
                }
                break;
            case 37961:
                if (operator.equals("$in")) {
                    c = 5;
                    break;
                }
                break;
            case 38060:
                if (operator.equals("$lt")) {
                    c = 1;
                    break;
                }
                break;
            case 38107:
                if (operator.equals("$ne")) {
                    c = 0;
                    break;
                }
                break;
            case 1169149:
                if (operator.equals("$all")) {
                    c = 7;
                    break;
                }
                break;
            case 1175156:
                if (operator.equals("$gte")) {
                    c = 4;
                    break;
                }
                break;
            case 1179961:
                if (operator.equals("$lte")) {
                    c = 2;
                    break;
                }
                break;
            case 1181551:
                if (operator.equals("$nin")) {
                    c = 6;
                    break;
                }
                break;
            case 596003200:
                if (operator.equals("$exists")) {
                    c = '\n';
                    break;
                }
                break;
            case 1097791887:
                if (operator.equals("$within")) {
                    c = '\r';
                    break;
                }
                break;
            case 1139041955:
                if (operator.equals("$regex")) {
                    c = '\b';
                    break;
                }
                break;
            case 1362155002:
                if (operator.equals("$options")) {
                    c = '\t';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return matchesNotEqualConstraint(constraint, value);
            case 1:
                return matchesLessThanConstraint(constraint, value);
            case 2:
                return matchesLessThanOrEqualToConstraint(constraint, value);
            case 3:
                return matchesGreaterThanConstraint(constraint, value);
            case 4:
                return matchesGreaterThanOrEqualToConstraint(constraint, value);
            case 5:
                return matchesInConstraint(constraint, value);
            case 6:
                return matchesNotInConstraint(constraint, value);
            case 7:
                return matchesAllConstraint(constraint, value);
            case '\b':
                return matchesRegexConstraint(constraint, value, (String) allKeyConstraints.get("$options"));
            case '\t':
            case '\f':
                return true;
            case '\n':
                return matchesExistsConstraint(constraint, value);
            case 11:
                return matchesNearSphereConstraint(constraint, value, (Double) allKeyConstraints.get("$maxDistance"));
            case '\r':
                return matchesWithinConstraint(constraint, value);
            default:
                throw new UnsupportedOperationException(String.format("The offline store does not yet support the %s operator.", operator));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public abstract class SubQueryMatcher<T extends ParseObject> extends ConstraintMatcher<T> {
        private final ParseQuery.State<T> subQuery;
        private Task<List<T>> subQueryResults = null;

        protected abstract boolean matches(T t, List<T> list) throws ParseException;

        public SubQueryMatcher(ParseUser user, ParseQuery.State<T> subQuery) {
            super(user);
            this.subQuery = subQuery;
        }

        @Override // com.parse.OfflineQueryLogic.ConstraintMatcher
        public Task<Boolean> matchesAsync(final T object, ParseSQLiteDatabase db) {
            if (this.subQueryResults == null) {
                this.subQueryResults = OfflineQueryLogic.this.store.findAsync(this.subQuery, this.user, null, db);
            }
            return this.subQueryResults.onSuccess(new Continuation<List<T>, Boolean>() { // from class: com.parse.OfflineQueryLogic.SubQueryMatcher.1
                /* JADX WARN: Can't rename method to resolve collision */
                /* JADX WARN: Multi-variable type inference failed */
                @Override // bolts.Continuation
                public Boolean then(Task<List<T>> task) throws ParseException {
                    return Boolean.valueOf(SubQueryMatcher.this.matches(object, task.getResult()));
                }
            });
        }
    }

    private <T extends ParseObject> ConstraintMatcher<T> createInQueryMatcher(ParseUser user, Object constraint, final String key) {
        return new SubQueryMatcher<T>(user, ((ParseQuery.State.Builder) constraint).build()) { // from class: com.parse.OfflineQueryLogic.6
            /* JADX WARN: Incorrect types in method signature: (TT;Ljava/util/List<TT;>;)Z */
            @Override // com.parse.OfflineQueryLogic.SubQueryMatcher
            protected boolean matches(ParseObject parseObject, List list) throws ParseException {
                return OfflineQueryLogic.matchesInConstraint(list, OfflineQueryLogic.getValue(parseObject, key));
            }
        };
    }

    private <T extends ParseObject> ConstraintMatcher<T> createNotInQueryMatcher(ParseUser user, Object constraint, String key) {
        final ConstraintMatcher<T> inQueryMatcher = createInQueryMatcher(user, constraint, key);
        return (ConstraintMatcher<T>) new ConstraintMatcher<T>(user) { // from class: com.parse.OfflineQueryLogic.7
            /* JADX WARN: Incorrect types in method signature: (TT;Lcom/parse/ParseSQLiteDatabase;)Lbolts/Task<Ljava/lang/Boolean;>; */
            @Override // com.parse.OfflineQueryLogic.ConstraintMatcher
            public Task matchesAsync(ParseObject parseObject, ParseSQLiteDatabase db) {
                return inQueryMatcher.matchesAsync(parseObject, db).onSuccess(new Continuation<Boolean, Boolean>() { // from class: com.parse.OfflineQueryLogic.7.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // bolts.Continuation
                    public Boolean then(Task<Boolean> task) throws Exception {
                        return Boolean.valueOf(!task.getResult().booleanValue());
                    }
                });
            }
        };
    }

    private <T extends ParseObject> ConstraintMatcher<T> createSelectMatcher(ParseUser user, Object constraint, final String key) {
        Map<?, ?> constraintMap = (Map) constraint;
        ParseQuery.State<T> query = ((ParseQuery.State.Builder) constraintMap.get("query")).build();
        final String resultKey = (String) constraintMap.get("key");
        return new SubQueryMatcher<T>(user, query) { // from class: com.parse.OfflineQueryLogic.8
            /* JADX WARN: Incorrect types in method signature: (TT;Ljava/util/List<TT;>;)Z */
            @Override // com.parse.OfflineQueryLogic.SubQueryMatcher
            protected boolean matches(ParseObject parseObject, List list) throws ParseException {
                Object value = OfflineQueryLogic.getValue(parseObject, key);
                Iterator i$ = list.iterator();
                while (i$.hasNext()) {
                    if (OfflineQueryLogic.matchesEqualConstraint(value, OfflineQueryLogic.getValue((ParseObject) i$.next(), resultKey))) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    private <T extends ParseObject> ConstraintMatcher<T> createDontSelectMatcher(ParseUser user, Object constraint, String key) {
        final ConstraintMatcher<T> selectMatcher = createSelectMatcher(user, constraint, key);
        return (ConstraintMatcher<T>) new ConstraintMatcher<T>(user) { // from class: com.parse.OfflineQueryLogic.9
            /* JADX WARN: Incorrect types in method signature: (TT;Lcom/parse/ParseSQLiteDatabase;)Lbolts/Task<Ljava/lang/Boolean;>; */
            @Override // com.parse.OfflineQueryLogic.ConstraintMatcher
            public Task matchesAsync(ParseObject parseObject, ParseSQLiteDatabase db) {
                return selectMatcher.matchesAsync(parseObject, db).onSuccess(new Continuation<Boolean, Boolean>() { // from class: com.parse.OfflineQueryLogic.9.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // bolts.Continuation
                    public Boolean then(Task<Boolean> task) throws Exception {
                        return Boolean.valueOf(!task.getResult().booleanValue());
                    }
                });
            }
        };
    }

    private <T extends ParseObject> ConstraintMatcher<T> createMatcher(ParseUser user, final String operator, final Object constraint, final String key, final ParseQuery.KeyConstraints allKeyConstraints) {
        char c = 65535;
        switch (operator.hashCode()) {
            case -721570031:
                if (operator.equals("$dontSelect")) {
                    c = 3;
                    break;
                }
                break;
            case 242866687:
                if (operator.equals("$inQuery")) {
                    c = 0;
                    break;
                }
                break;
            case 427054964:
                if (operator.equals("$notInQuery")) {
                    c = 1;
                    break;
                }
                break;
            case 979339808:
                if (operator.equals("$select")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return createInQueryMatcher(user, constraint, key);
            case 1:
                return createNotInQueryMatcher(user, constraint, key);
            case 2:
                return createSelectMatcher(user, constraint, key);
            case 3:
                return createDontSelectMatcher(user, constraint, key);
            default:
                return (ConstraintMatcher<T>) new ConstraintMatcher<T>(user) { // from class: com.parse.OfflineQueryLogic.10
                    /* JADX WARN: Incorrect types in method signature: (TT;Lcom/parse/ParseSQLiteDatabase;)Lbolts/Task<Ljava/lang/Boolean;>; */
                    @Override // com.parse.OfflineQueryLogic.ConstraintMatcher
                    public Task matchesAsync(ParseObject parseObject, ParseSQLiteDatabase db) {
                        try {
                            return Task.forResult(Boolean.valueOf(OfflineQueryLogic.matchesStatelessConstraint(operator, constraint, OfflineQueryLogic.getValue(parseObject, key), allKeyConstraints)));
                        } catch (ParseException e) {
                            return Task.forError(e);
                        }
                    }
                };
        }
    }

    private <T extends ParseObject> ConstraintMatcher<T> createOrMatcher(ParseUser user, ArrayList<ParseQuery.QueryConstraints> queries) {
        final ArrayList<ConstraintMatcher<T>> matchers = new ArrayList<>();
        Iterator i$ = queries.iterator();
        while (i$.hasNext()) {
            matchers.add(createMatcher(user, i$.next()));
        }
        return (ConstraintMatcher<T>) new ConstraintMatcher<T>(user) { // from class: com.parse.OfflineQueryLogic.11
            /* JADX WARN: Incorrect types in method signature: (TT;Lcom/parse/ParseSQLiteDatabase;)Lbolts/Task<Ljava/lang/Boolean;>; */
            @Override // com.parse.OfflineQueryLogic.ConstraintMatcher
            public Task matchesAsync(final ParseObject parseObject, final ParseSQLiteDatabase db) {
                Task<Boolean> task = Task.forResult(false);
                Iterator i$2 = matchers.iterator();
                while (i$2.hasNext()) {
                    final ConstraintMatcher constraintMatcher = (ConstraintMatcher) i$2.next();
                    task = task.onSuccessTask(new Continuation<Boolean, Task<Boolean>>() { // from class: com.parse.OfflineQueryLogic.11.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // bolts.Continuation
                        public Task<Boolean> then(Task<Boolean> task2) throws Exception {
                            return task2.getResult().booleanValue() ? task2 : constraintMatcher.matchesAsync(parseObject, db);
                        }
                    });
                }
                return task;
            }
        };
    }

    private <T extends ParseObject> ConstraintMatcher<T> createMatcher(ParseUser user, ParseQuery.QueryConstraints queryConstraints) {
        final ArrayList arrayList = new ArrayList();
        for (final String key : queryConstraints.keySet()) {
            final Object queryConstraintValue = queryConstraints.get(key);
            if (key.equals("$or")) {
                arrayList.add(createOrMatcher(user, (ArrayList) queryConstraintValue));
            } else if (queryConstraintValue instanceof ParseQuery.KeyConstraints) {
                ParseQuery.KeyConstraints keyConstraints = (ParseQuery.KeyConstraints) queryConstraintValue;
                for (String operator : keyConstraints.keySet()) {
                    arrayList.add(createMatcher(user, operator, keyConstraints.get(operator), key, keyConstraints));
                }
            } else if (queryConstraintValue instanceof ParseQuery.RelationConstraint) {
                final ParseQuery.RelationConstraint relation = (ParseQuery.RelationConstraint) queryConstraintValue;
                arrayList.add(new ConstraintMatcher<T>(user) { // from class: com.parse.OfflineQueryLogic.12
                    /* JADX WARN: Incorrect types in method signature: (TT;Lcom/parse/ParseSQLiteDatabase;)Lbolts/Task<Ljava/lang/Boolean;>; */
                    @Override // com.parse.OfflineQueryLogic.ConstraintMatcher
                    public Task matchesAsync(ParseObject parseObject, ParseSQLiteDatabase db) {
                        return Task.forResult(Boolean.valueOf(relation.getRelation().hasKnownObject(parseObject)));
                    }
                });
            } else {
                arrayList.add(new ConstraintMatcher<T>(user) { // from class: com.parse.OfflineQueryLogic.13
                    /* JADX WARN: Incorrect types in method signature: (TT;Lcom/parse/ParseSQLiteDatabase;)Lbolts/Task<Ljava/lang/Boolean;>; */
                    @Override // com.parse.OfflineQueryLogic.ConstraintMatcher
                    public Task matchesAsync(ParseObject parseObject, ParseSQLiteDatabase db) {
                        try {
                            return Task.forResult(Boolean.valueOf(OfflineQueryLogic.matchesEqualConstraint(queryConstraintValue, OfflineQueryLogic.getValue(parseObject, key))));
                        } catch (ParseException e) {
                            return Task.forError(e);
                        }
                    }
                });
            }
        }
        return (ConstraintMatcher<T>) new ConstraintMatcher<T>(user) { // from class: com.parse.OfflineQueryLogic.14
            /* JADX WARN: Incorrect types in method signature: (TT;Lcom/parse/ParseSQLiteDatabase;)Lbolts/Task<Ljava/lang/Boolean;>; */
            @Override // com.parse.OfflineQueryLogic.ConstraintMatcher
            public Task matchesAsync(final ParseObject parseObject, final ParseSQLiteDatabase db) {
                Task<Boolean> task = Task.forResult(true);
                Iterator i$ = arrayList.iterator();
                while (i$.hasNext()) {
                    final ConstraintMatcher constraintMatcher = (ConstraintMatcher) i$.next();
                    task = task.onSuccessTask(new Continuation<Boolean, Task<Boolean>>() { // from class: com.parse.OfflineQueryLogic.14.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // bolts.Continuation
                        public Task<Boolean> then(Task<Boolean> task2) throws Exception {
                            return !task2.getResult().booleanValue() ? task2 : constraintMatcher.matchesAsync(parseObject, db);
                        }
                    });
                }
                return task;
            }
        };
    }

    static <T extends ParseObject> boolean hasReadAccess(ParseUser user, T object) {
        ParseACL acl;
        if (user == object || (acl = object.getACL()) == null || acl.getPublicReadAccess()) {
            return true;
        }
        if (user == null || !acl.getReadAccess(user)) {
            return false;
        }
        return true;
    }

    static <T extends ParseObject> boolean hasWriteAccess(ParseUser user, T object) {
        ParseACL acl;
        if (user == object || (acl = object.getACL()) == null || acl.getPublicWriteAccess()) {
            return true;
        }
        if (user == null || !acl.getWriteAccess(user)) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T extends ParseObject> ConstraintMatcher<T> createMatcher(ParseQuery.State<T> state, ParseUser user) {
        final boolean ignoreACLs = state.ignoreACLs();
        final ConstraintMatcher<T> constraintMatcher = createMatcher(user, state.constraints());
        return (ConstraintMatcher<T>) new ConstraintMatcher<T>(user) { // from class: com.parse.OfflineQueryLogic.15
            /* JADX WARN: Incorrect types in method signature: (TT;Lcom/parse/ParseSQLiteDatabase;)Lbolts/Task<Ljava/lang/Boolean;>; */
            @Override // com.parse.OfflineQueryLogic.ConstraintMatcher
            public Task matchesAsync(ParseObject parseObject, ParseSQLiteDatabase db) {
                return (ignoreACLs || OfflineQueryLogic.hasReadAccess(this.user, parseObject)) ? constraintMatcher.matchesAsync(parseObject, db) : Task.forResult(false);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends ParseObject> void sort(List<T> results, ParseQuery.State<T> state) throws ParseException {
        final List<String> keys = state.order();
        for (String key : state.order()) {
            if (!(key.matches("^-?[A-Za-z][A-Za-z0-9_]*$") || "_created_at".equals(key) || "_updated_at".equals(key))) {
                throw new ParseException(105, String.format("Invalid key name: \"%s\".", key));
            }
        }
        final String mutableNearSphereKey = null;
        final ParseGeoPoint mutableNearSphereValue = null;
        for (String queryKey : state.constraints().keySet()) {
            Object queryKeyConstraints = state.constraints().get(queryKey);
            if (queryKeyConstraints instanceof ParseQuery.KeyConstraints) {
                ParseQuery.KeyConstraints keyConstraints = (ParseQuery.KeyConstraints) queryKeyConstraints;
                if (keyConstraints.containsKey("$nearSphere")) {
                    mutableNearSphereKey = queryKey;
                    mutableNearSphereValue = (ParseGeoPoint) keyConstraints.get("$nearSphere");
                }
            }
        }
        if (!(keys.size() == 0 && mutableNearSphereKey == null)) {
            Collections.sort(results, new Comparator<T>() { // from class: com.parse.OfflineQueryLogic.16
                /* JADX WARN: Incorrect types in method signature: (TT;TT;)I */
                public int compare(ParseObject parseObject, ParseObject parseObject2) {
                    if (mutableNearSphereKey != null) {
                        try {
                            ParseGeoPoint lhsPoint = (ParseGeoPoint) OfflineQueryLogic.getValue(parseObject, mutableNearSphereKey);
                            ParseGeoPoint rhsPoint = (ParseGeoPoint) OfflineQueryLogic.getValue(parseObject2, mutableNearSphereKey);
                            double lhsDistance = lhsPoint.distanceInRadiansTo(mutableNearSphereValue);
                            double rhsDistance = rhsPoint.distanceInRadiansTo(mutableNearSphereValue);
                            if (lhsDistance != rhsDistance) {
                                return lhsDistance - rhsDistance > 0.0d ? 1 : -1;
                            }
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    for (String key2 : keys) {
                        boolean descending = false;
                        if (key2.startsWith("-")) {
                            descending = true;
                            key2 = key2.substring(1);
                        }
                        try {
                            try {
                                int result = OfflineQueryLogic.compareTo(OfflineQueryLogic.getValue(parseObject, key2), OfflineQueryLogic.getValue(parseObject2, key2));
                                if (result != 0) {
                                    if (descending) {
                                        result = -result;
                                    }
                                    return result;
                                }
                            } catch (IllegalArgumentException e2) {
                                throw new IllegalArgumentException(String.format("Unable to sort by key %s.", key2), e2);
                            }
                        } catch (ParseException e3) {
                            throw new RuntimeException(e3);
                        }
                    }
                    return 0;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Task<Void> fetchIncludeAsync(final OfflineStore store, final Object container, final String path, final ParseSQLiteDatabase db) throws ParseException {
        if (container == null) {
            return Task.forResult(null);
        }
        if (container instanceof Collection) {
            Task<Void> task = Task.forResult(null);
            for (final Object item : (Collection) container) {
                task = task.onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineQueryLogic.17
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<Void> task2) throws Exception {
                        return OfflineQueryLogic.fetchIncludeAsync(OfflineStore.this, item, path, db);
                    }
                });
            }
            return task;
        } else if (container instanceof JSONArray) {
            final JSONArray array = (JSONArray) container;
            Task<Void> task2 = Task.forResult(null);
            for (final int i = 0; i < array.length(); i++) {
                task2 = task2.onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineQueryLogic.18
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // bolts.Continuation
                    public Task<Void> then(Task<Void> task3) throws Exception {
                        return OfflineQueryLogic.fetchIncludeAsync(OfflineStore.this, array.get(i), path, db);
                    }
                });
            }
            return task2;
        } else if (path != null) {
            String[] parts = path.split("\\.", 2);
            final String key = parts[0];
            final String rest = parts.length > 1 ? parts[1] : null;
            return Task.forResult(null).continueWithTask(new Continuation<Void, Task<Object>>() { // from class: com.parse.OfflineQueryLogic.20
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // bolts.Continuation
                public Task<Object> then(Task<Void> task3) throws Exception {
                    if (container instanceof ParseObject) {
                        return OfflineQueryLogic.fetchIncludeAsync(store, container, null, db).onSuccess(new Continuation<Void, Object>() { // from class: com.parse.OfflineQueryLogic.20.1
                            @Override // bolts.Continuation
                            public Object then(Task<Void> task4) throws Exception {
                                return ((ParseObject) container).get(key);
                            }
                        });
                    }
                    if (container instanceof Map) {
                        return Task.forResult(((Map) container).get(key));
                    }
                    if (container instanceof JSONObject) {
                        return Task.forResult(((JSONObject) container).opt(key));
                    }
                    if (!JSONObject.NULL.equals(container)) {
                        return Task.forError(new IllegalStateException("include is invalid"));
                    }
                    return null;
                }
            }).onSuccessTask(new Continuation<Object, Task<Void>>() { // from class: com.parse.OfflineQueryLogic.19
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // bolts.Continuation
                public Task<Void> then(Task<Object> task3) throws Exception {
                    return OfflineQueryLogic.fetchIncludeAsync(OfflineStore.this, task3.getResult(), rest, db);
                }
            });
        } else if (JSONObject.NULL.equals(container)) {
            return Task.forResult(null);
        } else {
            if (container instanceof ParseObject) {
                return store.fetchLocallyAsync((ParseObject) container, db).makeVoid();
            }
            return Task.forError(new ParseException(121, "include is invalid for non-ParseObjects"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends ParseObject> Task<Void> fetchIncludesAsync(final OfflineStore store, final T object, ParseQuery.State<T> state, final ParseSQLiteDatabase db) {
        Set<String> includes = state.includes();
        Task<Void> task = Task.forResult(null);
        for (final String include : includes) {
            task = task.onSuccessTask(new Continuation<Void, Task<Void>>() { // from class: com.parse.OfflineQueryLogic.21
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // bolts.Continuation
                public Task<Void> then(Task<Void> task2) throws Exception {
                    return OfflineQueryLogic.fetchIncludeAsync(OfflineStore.this, object, include, db);
                }
            });
        }
        return task;
    }
}

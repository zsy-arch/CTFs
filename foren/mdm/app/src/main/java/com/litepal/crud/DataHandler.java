package com.litepal.crud;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;
import com.litepal.LitePalBase;
import com.litepal.crud.model.AssociationsInfo;
import com.litepal.exceptions.DataSupportException;
import com.litepal.exceptions.DatabaseGenerateException;
import com.litepal.util.BaseUtility;
import com.litepal.util.DBUtility;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/* loaded from: classes.dex */
public abstract class DataHandler extends LitePalBase {
    public static final String TAG = "DataHandler";
    private List<AssociationsInfo> fkInCurrentModel;
    private List<AssociationsInfo> fkInOtherModel;
    SQLiteDatabase mDatabase;
    private DataSupport tempEmptyModel;

    protected <T> List<T> query(Class<T> modelClass, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit, List<AssociationsInfo> foreignKeyAssociations) {
        Throwable th;
        ArrayList arrayList;
        Cursor cursor;
        Exception e;
        try {
            arrayList = new ArrayList();
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            List<Field> supportedFields = getSupportedFields(modelClass.getName());
            Cursor cursor2 = this.mDatabase.query(getTableName(modelClass), getCustomizedColumns(columns, foreignKeyAssociations), selection, selectionArgs, groupBy, having, orderBy, limit);
            try {
                if (cursor2.moveToFirst()) {
                    SparseArray<QueryInfoCache> queryInfoCacheSparseArray = new SparseArray<>();
                    do {
                        Object createInstanceFromClass = createInstanceFromClass(modelClass);
                        giveBaseObjIdValue((DataSupport) createInstanceFromClass, cursor2.getLong(cursor2.getColumnIndexOrThrow("id")));
                        setValueToModel(createInstanceFromClass, supportedFields, foreignKeyAssociations, cursor2, queryInfoCacheSparseArray);
                        if (foreignKeyAssociations != null) {
                            setAssociatedModel((DataSupport) createInstanceFromClass);
                        }
                        arrayList.add(createInstanceFromClass);
                    } while (cursor2.moveToNext());
                    queryInfoCacheSparseArray.clear();
                }
                if (cursor2 != null) {
                    cursor2.close();
                }
                return arrayList;
            } catch (Exception e2) {
                e = e2;
                throw new DataSupportException(e.getMessage(), e);
            }
        } catch (Exception e3) {
            e = e3;
        } catch (Throwable th3) {
            th = th3;
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    protected <T> T mathQuery(String tableName, String[] columns, String[] conditions, Class<T> type) {
        BaseUtility.checkConditionsCorrect(conditions);
        Cursor cursor = null;
        T result = null;
        try {
            try {
                cursor = this.mDatabase.query(tableName, columns, getWhereClause(conditions), getWhereArgs(conditions), null, null, null);
                if (cursor.moveToFirst()) {
                    result = (T) cursor.getClass().getMethod(genGetColumnMethod((Class<?>) type), Integer.TYPE).invoke(cursor, 0);
                }
                return result;
            } catch (Exception e) {
                throw new DataSupportException(e.getMessage(), e);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    protected void giveBaseObjIdValue(DataSupport baseObj, long id) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (id > 0) {
            DynamicExecutor.set(baseObj, "baseObjId", Long.valueOf(id), DataSupport.class);
        }
    }

    protected void putFieldsValue(DataSupport baseObj, List<Field> supportedFields, ContentValues values) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for (Field field : supportedFields) {
            if (!isIdColumn(field.getName())) {
                putFieldsValueDependsOnSaveOrUpdate(baseObj, field, values);
            }
        }
    }

    protected void putContentValuesForSave(DataSupport baseObj, Field field, ContentValues values) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object fieldValue = DynamicExecutor.getField(baseObj, field.getName(), baseObj.getClass());
        if (fieldValue != null) {
            if ("java.util.Date".equals(field.getType().getName())) {
                fieldValue = Long.valueOf(((Date) fieldValue).getTime());
            }
            Object[] parameters = {BaseUtility.changeCase(field.getName()), fieldValue};
            DynamicExecutor.send(values, "put", parameters, values.getClass(), getParameterTypes(field, fieldValue, parameters));
        }
    }

    protected void putContentValuesForUpdate(DataSupport baseObj, Field field, ContentValues values) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object fieldValue = takeGetMethodValueByField(baseObj, field);
        if ("java.util.Date".equals(field.getType().getName()) && fieldValue != null) {
            fieldValue = Long.valueOf(((Date) fieldValue).getTime());
        }
        Object[] parameters = {BaseUtility.changeCase(field.getName()), fieldValue};
        DynamicExecutor.send(values, "put", parameters, values.getClass(), getParameterTypes(field, fieldValue, parameters));
    }

    protected Object takeGetMethodValueByField(DataSupport dataSupport, Field field) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (shouldGetOrSet(dataSupport, field)) {
            return DynamicExecutor.send(dataSupport, makeGetterMethodName(field), null, dataSupport.getClass(), null);
        }
        return null;
    }

    protected void putSetMethodValueByField(DataSupport dataSupport, Field field, Object parameter) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (shouldGetOrSet(dataSupport, field)) {
            DynamicExecutor.send(dataSupport, makeSetterMethodName(field), new Object[]{parameter}, dataSupport.getClass(), new Class[]{field.getType()});
        }
    }

    protected void analyzeAssociatedModels(DataSupport baseObj, Collection<AssociationsInfo> associationInfos) {
        try {
            for (AssociationsInfo associationInfo : associationInfos) {
                if (associationInfo.getAssociationType() == 2) {
                    new Many2OneAnalyzer().analyze(baseObj, associationInfo);
                } else if (associationInfo.getAssociationType() == 1) {
                    new One2OneAnalyzer().analyze(baseObj, associationInfo);
                } else if (associationInfo.getAssociationType() == 3) {
                    new Many2ManyAnalyzer().analyze(baseObj, associationInfo);
                }
            }
        } catch (Exception e) {
            throw new DataSupportException(e.getMessage(), e);
        }
    }

    protected DataSupport getAssociatedModel(DataSupport baseObj, AssociationsInfo associationInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (DataSupport) takeGetMethodValueByField(baseObj, associationInfo.getAssociateOtherModelFromSelf());
    }

    protected Collection<DataSupport> getAssociatedModels(DataSupport baseObj, AssociationsInfo associationInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (Collection) takeGetMethodValueByField(baseObj, associationInfo.getAssociateOtherModelFromSelf());
    }

    protected DataSupport getEmptyModel(DataSupport baseObj) {
        if (this.tempEmptyModel != null) {
            return this.tempEmptyModel;
        }
        String className = null;
        try {
            className = baseObj.getClassName();
            this.tempEmptyModel = (DataSupport) Class.forName(className).newInstance();
            return this.tempEmptyModel;
        } catch (ClassNotFoundException e) {
            throw new DatabaseGenerateException(DatabaseGenerateException.CLASS_NOT_FOUND + className);
        } catch (InstantiationException e2) {
            throw new DataSupportException(className + DataSupportException.INSTANTIATION_EXCEPTION, e2);
        } catch (Exception e3) {
            throw new DataSupportException(e3.getMessage(), e3);
        }
    }

    protected String getWhereClause(String... conditions) {
        if (!isAffectAllLines(conditions) && conditions != null && conditions.length > 0) {
            return conditions[0];
        }
        return null;
    }

    protected String[] getWhereArgs(String... conditions) {
        if (isAffectAllLines(conditions) || conditions == null || conditions.length <= 1) {
            return null;
        }
        String[] whereArgs = new String[conditions.length - 1];
        System.arraycopy(conditions, 1, whereArgs, 0, conditions.length - 1);
        return whereArgs;
    }

    protected boolean isAffectAllLines(Object... conditions) {
        return conditions != null && conditions.length == 0;
    }

    protected String getWhereOfIdsWithOr(Collection<Long> ids) {
        StringBuilder whereClause = new StringBuilder();
        boolean needOr = false;
        for (Long l : ids) {
            long id = l.longValue();
            if (needOr) {
                whereClause.append(" or ");
            }
            needOr = true;
            whereClause.append("id = ");
            whereClause.append(id);
        }
        return BaseUtility.changeCase(whereClause.toString());
    }

    protected String getWhereOfIdsWithOr(long... ids) {
        StringBuilder whereClause = new StringBuilder();
        boolean needOr = false;
        for (long id : ids) {
            if (needOr) {
                whereClause.append(" or ");
            }
            needOr = true;
            whereClause.append("id = ");
            whereClause.append(id);
        }
        return BaseUtility.changeCase(whereClause.toString());
    }

    @Deprecated
    protected Class<?> findDataSupportClass(DataSupport baseObj) {
        Class<?> superClass;
        do {
            superClass = baseObj.getClass().getSuperclass();
            if (superClass == null) {
                break;
            }
        } while (DataSupport.class != superClass);
        if (superClass != null) {
            return superClass;
        }
        throw new DataSupportException(baseObj.getClass().getName() + DataSupportException.MODEL_IS_NOT_AN_INSTANCE_OF_DATA_SUPPORT);
    }

    protected boolean shouldGetOrSet(DataSupport dataSupport, Field field) {
        return (dataSupport == null || field == null) ? false : true;
    }

    protected String getIntermediateTableName(DataSupport baseObj, String associatedTableName) {
        return BaseUtility.changeCase(DBUtility.getIntermediateTableName(baseObj.getTableName(), associatedTableName));
    }

    protected String getTableName(Class<?> modelClass) {
        return BaseUtility.changeCase(DBUtility.getTableNameByClassName(modelClass.getName()));
    }

    protected Object createInstanceFromClass(Class<?> modelClass) {
        try {
            Constructor<?> constructor = findBestSuitConstructor(modelClass);
            return constructor.newInstance(getConstructorParams(modelClass, constructor));
        } catch (Exception e) {
            throw new DataSupportException(e.getMessage(), e);
        }
    }

    protected Constructor<?> findBestSuitConstructor(Class<?> modelClass) {
        Constructor<?>[] constructors = modelClass.getDeclaredConstructors();
        SparseArray<Constructor<?>> map = new SparseArray<>();
        int minKey = Integer.MAX_VALUE;
        for (Constructor<?> constructor : constructors) {
            int key = constructor.getParameterTypes().length;
            for (Class<?> parameterType : constructor.getParameterTypes()) {
                if (parameterType == modelClass) {
                    key += 10000;
                }
            }
            if (map.get(key) == null) {
                map.put(key, constructor);
            }
            if (key < minKey) {
                minKey = key;
            }
        }
        Constructor<?> bestSuitConstructor = map.get(minKey);
        if (bestSuitConstructor != null) {
            bestSuitConstructor.setAccessible(true);
        }
        return bestSuitConstructor;
    }

    protected Object[] getConstructorParams(Class<?> modelClass, Constructor<?> constructor) {
        Class<?>[] paramTypes = constructor.getParameterTypes();
        Object[] params = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            params[i] = getInitParamValue(modelClass, paramTypes[i]);
        }
        return params;
    }

    protected void setValueToModel(Object modelInstance, List<Field> supportedFields, List<AssociationsInfo> foreignKeyAssociations, Cursor cursor, SparseArray<QueryInfoCache> sparseArray) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int cacheSize = sparseArray.size();
        if (cacheSize > 0) {
            for (int i = 0; i < cacheSize; i++) {
                int columnIndex = sparseArray.keyAt(i);
                QueryInfoCache cache = sparseArray.get(columnIndex);
                setToModelByReflection(modelInstance, cache.field, columnIndex, cache.getMethodName, cursor);
            }
        } else {
            for (Field field : supportedFields) {
                String getMethodName = genGetColumnMethod(field);
                int columnIndex2 = cursor.getColumnIndex(BaseUtility.changeCase(isIdColumn(field.getName()) ? "id" : field.getName()));
                if (columnIndex2 != -1) {
                    setToModelByReflection(modelInstance, field, columnIndex2, getMethodName, cursor);
                    QueryInfoCache cache2 = new QueryInfoCache();
                    cache2.getMethodName = getMethodName;
                    cache2.field = field;
                    sparseArray.put(columnIndex2, cache2);
                }
            }
        }
        if (foreignKeyAssociations != null) {
            for (AssociationsInfo associationInfo : foreignKeyAssociations) {
                int columnIndex3 = cursor.getColumnIndex(getForeignKeyColumnName(DBUtility.getTableNameByClassName(associationInfo.getAssociatedClassName())));
                if (columnIndex3 != -1) {
                    try {
                        DataSupport associatedObj = (DataSupport) DataSupport.find(Class.forName(associationInfo.getAssociatedClassName()), cursor.getLong(columnIndex3));
                        if (associatedObj != null) {
                            putSetMethodValueByField((DataSupport) modelInstance, associationInfo.getAssociateOtherModelFromSelf(), associatedObj);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    protected List<AssociationsInfo> getForeignKeyAssociations(String className, boolean isEager) {
        if (!isEager) {
            return null;
        }
        analyzeAssociations(className);
        return this.fkInCurrentModel;
    }

    private Class<?>[] getParameterTypes(Field field, Object fieldValue, Object[] parameters) {
        if (!isCharType(field)) {
            return field.getType().isPrimitive() ? new Class[]{String.class, getObjectType(field.getType())} : "java.util.Date".equals(field.getType().getName()) ? new Class[]{String.class, Long.class} : new Class[]{String.class, field.getType()};
        }
        parameters[1] = String.valueOf(fieldValue);
        return new Class[]{String.class, String.class};
    }

    private Class<?> getObjectType(Class<?> primitiveType) {
        if (primitiveType != null && primitiveType.isPrimitive()) {
            String basicTypeName = primitiveType.getName();
            if ("int".equals(basicTypeName)) {
                return Integer.class;
            }
            if ("short".equals(basicTypeName)) {
                return Short.class;
            }
            if ("long".equals(basicTypeName)) {
                return Long.class;
            }
            if ("float".equals(basicTypeName)) {
                return Float.class;
            }
            if ("double".equals(basicTypeName)) {
                return Double.class;
            }
            if ("boolean".equals(basicTypeName)) {
                return Boolean.class;
            }
            if ("char".equals(basicTypeName)) {
                return Character.class;
            }
        }
        return null;
    }

    private Object getInitParamValue(Class<?> modelClass, Class<?> paramType) {
        String paramTypeName = paramType.getName();
        if ("boolean".equals(paramTypeName) || "java.lang.Boolean".equals(paramTypeName)) {
            return false;
        }
        if ("float".equals(paramTypeName) || "java.lang.Float".equals(paramTypeName)) {
            return Float.valueOf(0.0f);
        }
        if ("double".equals(paramTypeName) || "java.lang.Double".equals(paramTypeName)) {
            return Double.valueOf(0.0d);
        }
        if ("int".equals(paramTypeName) || "java.lang.Integer".equals(paramTypeName)) {
            return 0;
        }
        if ("long".equals(paramTypeName) || "java.lang.Long".equals(paramTypeName)) {
            return 0L;
        }
        if ("short".equals(paramTypeName) || "java.lang.Short".equals(paramTypeName)) {
            return 0;
        }
        if ("char".equals(paramTypeName) || "java.lang.Character".equals(paramTypeName)) {
            return ' ';
        }
        if ("[B".equals(paramTypeName) || "[Ljava.lang.Byte;".equals(paramTypeName)) {
            return new byte[0];
        }
        if ("java.lang.String".equals(paramTypeName)) {
            return "";
        }
        if (modelClass == paramType) {
            return null;
        }
        return createInstanceFromClass(paramType);
    }

    private boolean isCharType(Field field) {
        String type = field.getType().getName();
        return type.equals("char") || type.endsWith("Character");
    }

    private boolean isPrimitiveBooleanType(Field field) {
        return "boolean".equals(field.getType().getName());
    }

    private void putFieldsValueDependsOnSaveOrUpdate(DataSupport baseObj, Field field, ContentValues values) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (isUpdating()) {
            if (!isFieldWithDefaultValue(baseObj, field)) {
                putContentValuesForUpdate(baseObj, field, values);
            }
        } else if (isSaving()) {
            putContentValuesForSave(baseObj, field, values);
        }
    }

    private boolean isUpdating() {
        return UpdateHandler.class.getName().equals(getClass().getName());
    }

    private boolean isSaving() {
        return SaveHandler.class.getName().equals(getClass().getName());
    }

    private boolean isFieldWithDefaultValue(DataSupport baseObj, Field field) throws IllegalAccessException, SecurityException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException {
        DataSupport emptyModel = getEmptyModel(baseObj);
        Object realReturn = takeGetMethodValueByField(baseObj, field);
        Object defaultReturn = takeGetMethodValueByField(emptyModel, field);
        if (realReturn == null || defaultReturn == null) {
            return realReturn == defaultReturn;
        }
        return takeGetMethodValueByField(baseObj, field).toString().equals(takeGetMethodValueByField(emptyModel, field).toString());
    }

    private String makeGetterMethodName(Field field) {
        String getterMethodPrefix;
        String fieldName = field.getName();
        if (isPrimitiveBooleanType(field)) {
            if (fieldName.matches("^is[A-Z]{1}.*$")) {
                fieldName = fieldName.substring(2);
            }
            getterMethodPrefix = "is";
        } else {
            getterMethodPrefix = "get";
        }
        if (fieldName.matches("^[a-z]{1}[A-Z]{1}.*")) {
            return getterMethodPrefix + fieldName;
        }
        return getterMethodPrefix + BaseUtility.capitalize(fieldName);
    }

    private String makeSetterMethodName(Field field) {
        if (!isPrimitiveBooleanType(field) || !field.getName().matches("^is[A-Z]{1}.*$")) {
            return field.getName().matches("^[a-z]{1}[A-Z]{1}.*") ? "set" + field.getName() : "set" + BaseUtility.capitalize(field.getName());
        }
        return "set" + field.getName().substring(2);
    }

    private String genGetColumnMethod(Field field) {
        return genGetColumnMethod(field.getType());
    }

    private String genGetColumnMethod(Class<?> fieldType) {
        String typeName;
        if (fieldType.isPrimitive()) {
            typeName = BaseUtility.capitalize(fieldType.getName());
        } else {
            typeName = fieldType.getSimpleName();
        }
        String methodName = "get" + typeName;
        if ("getBoolean".equals(methodName)) {
            return "getInt";
        }
        if ("getChar".equals(methodName) || "getCharacter".equals(methodName)) {
            return "getString";
        }
        if ("getDate".equals(methodName)) {
            return "getLong";
        }
        if ("getInteger".equals(methodName)) {
            return "getInt";
        }
        if ("getbyte[]".equalsIgnoreCase(methodName)) {
            return "getBlob";
        }
        return methodName;
    }

    private String[] getCustomizedColumns(String[] columns, List<AssociationsInfo> foreignKeyAssociations) {
        if (columns == null) {
            return null;
        }
        if (foreignKeyAssociations != null && foreignKeyAssociations.size() > 0) {
            String[] tempColumns = new String[columns.length + foreignKeyAssociations.size()];
            System.arraycopy(columns, 0, tempColumns, 0, columns.length);
            for (int i = 0; i < foreignKeyAssociations.size(); i++) {
                tempColumns[columns.length + i] = getForeignKeyColumnName(DBUtility.getTableNameByClassName(foreignKeyAssociations.get(i).getAssociatedClassName()));
            }
            columns = tempColumns;
        }
        for (int i2 = 0; i2 < columns.length; i2++) {
            String columnName = columns[i2];
            if (isIdColumn(columnName)) {
                if (com.yolanda.nohttp.db.Field.ID.equalsIgnoreCase(columnName)) {
                    columns[i2] = BaseUtility.changeCase("id");
                }
                return columns;
            }
        }
        String[] customizedColumns = new String[columns.length + 1];
        System.arraycopy(columns, 0, customizedColumns, 0, columns.length);
        customizedColumns[columns.length] = BaseUtility.changeCase("id");
        return customizedColumns;
    }

    private void analyzeAssociations(String className) {
        Collection<AssociationsInfo> associationInfos = getAssociationInfo(className);
        if (this.fkInCurrentModel == null) {
            this.fkInCurrentModel = new ArrayList();
        } else {
            this.fkInCurrentModel.clear();
        }
        if (this.fkInOtherModel == null) {
            this.fkInOtherModel = new ArrayList();
        } else {
            this.fkInOtherModel.clear();
        }
        for (AssociationsInfo associationInfo : associationInfos) {
            if (associationInfo.getAssociationType() == 2 || associationInfo.getAssociationType() == 1) {
                if (associationInfo.getClassHoldsForeignKey().equals(className)) {
                    this.fkInCurrentModel.add(associationInfo);
                } else {
                    this.fkInOtherModel.add(associationInfo);
                }
            } else if (associationInfo.getAssociationType() == 3) {
                this.fkInOtherModel.add(associationInfo);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x016e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setAssociatedModel(com.litepal.crud.DataSupport r27) {
        /*
            Method dump skipped, instructions count: 376
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.litepal.crud.DataHandler.setAssociatedModel(com.litepal.crud.DataSupport):void");
    }

    private void setToModelByReflection(Object modelInstance, Field field, int columnIndex, String getMethodName, Cursor cursor) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object value = cursor.getClass().getMethod(getMethodName, Integer.TYPE).invoke(cursor, Integer.valueOf(columnIndex));
        if (field.getType() == Boolean.TYPE || field.getType() == Boolean.class) {
            if ("0".equals(String.valueOf(value))) {
                value = false;
            } else if ("1".equals(String.valueOf(value))) {
                value = true;
            }
        } else if (field.getType() == Character.TYPE || field.getType() == Character.class) {
            value = Character.valueOf(((String) value).charAt(0));
        } else if (field.getType() == Date.class) {
            long date = ((Long) value).longValue();
            value = date <= 0 ? null : new Date(date);
        }
        DynamicExecutor.setField(modelInstance, field.getName(), value, modelInstance.getClass());
    }

    /* loaded from: classes2.dex */
    public class QueryInfoCache {
        Field field;
        String getMethodName;

        QueryInfoCache() {
            DataHandler.this = this$0;
        }
    }
}

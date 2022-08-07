package com.litepal;

import com.litepal.annotation.Column;
import com.litepal.crud.DataSupport;
import com.litepal.crud.model.AssociationsInfo;
import com.litepal.exceptions.DatabaseGenerateException;
import com.litepal.parser.LitePalAttr;
import com.litepal.tablemanager.model.AssociationsModel;
import com.litepal.tablemanager.model.ColumnModel;
import com.litepal.tablemanager.model.TableModel;
import com.litepal.tablemanager.typechange.BlobOrm;
import com.litepal.tablemanager.typechange.BooleanOrm;
import com.litepal.tablemanager.typechange.DateOrm;
import com.litepal.tablemanager.typechange.DecimalOrm;
import com.litepal.tablemanager.typechange.NumericOrm;
import com.litepal.tablemanager.typechange.OrmChange;
import com.litepal.tablemanager.typechange.TextOrm;
import com.litepal.util.BaseUtility;
import com.litepal.util.DBUtility;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public abstract class LitePalBase {
    private static final int GET_ASSOCIATIONS_ACTION = 1;
    private static final int GET_ASSOCIATION_INFO_ACTION = 2;
    public static final String TAG = "LitePalBase";
    private Collection<AssociationsInfo> mAssociationInfos;
    private Collection<AssociationsModel> mAssociationModels;
    private OrmChange[] typeChangeRules = {new NumericOrm(), new TextOrm(), new BooleanOrm(), new DecimalOrm(), new DateOrm(), new BlobOrm()};
    private Map<String, List<Field>> classFieldsMap = new HashMap();

    protected TableModel getTableModel(String className) {
        String tableName = DBUtility.getTableNameByClassName(className);
        TableModel tableModel = new TableModel();
        tableModel.setTableName(tableName);
        tableModel.setClassName(className);
        for (Field field : getSupportedFields(className)) {
            tableModel.addColumnModel(convertFieldToColumnModel(field));
        }
        return tableModel;
    }

    protected Collection<AssociationsModel> getAssociations(List<String> classNames) {
        if (this.mAssociationModels == null) {
            this.mAssociationModels = new HashSet();
        }
        this.mAssociationModels.clear();
        for (String className : classNames) {
            analyzeClassFields(className, 1);
        }
        return this.mAssociationModels;
    }

    protected Collection<AssociationsInfo> getAssociationInfo(String className) {
        if (this.mAssociationInfos == null) {
            this.mAssociationInfos = new HashSet();
        }
        this.mAssociationInfos.clear();
        analyzeClassFields(className, 2);
        return this.mAssociationInfos;
    }

    protected List<Field> getSupportedFields(String className) {
        List<Field> fieldList = this.classFieldsMap.get(className);
        if (fieldList != null) {
            return fieldList;
        }
        List<Field> supportedFields = new ArrayList<>();
        try {
            recursiveSupportedFields(Class.forName(className), supportedFields);
            this.classFieldsMap.put(className, supportedFields);
            return supportedFields;
        } catch (ClassNotFoundException e) {
            throw new DatabaseGenerateException(DatabaseGenerateException.CLASS_NOT_FOUND + className);
        }
    }

    protected boolean isCollection(Class<?> fieldType) {
        return isList(fieldType) || isSet(fieldType);
    }

    protected boolean isList(Class<?> fieldType) {
        return List.class.isAssignableFrom(fieldType);
    }

    protected boolean isSet(Class<?> fieldType) {
        return Set.class.isAssignableFrom(fieldType);
    }

    protected boolean isIdColumn(String columnName) {
        return com.yolanda.nohttp.db.Field.ID.equalsIgnoreCase(columnName) || "id".equalsIgnoreCase(columnName);
    }

    protected String getForeignKeyColumnName(String associatedTableName) {
        return BaseUtility.changeCase(associatedTableName + com.yolanda.nohttp.db.Field.ID);
    }

    private void recursiveSupportedFields(Class<?> clazz, List<Field> supportedFields) {
        if (!(clazz == DataSupport.class || clazz == Object.class)) {
            Field[] fields = clazz.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    Column annotation = (Column) field.getAnnotation(Column.class);
                    if ((annotation == null || !annotation.ignore()) && !Modifier.isStatic(field.getModifiers()) && BaseUtility.isFieldTypeSupported(field.getType().getName())) {
                        supportedFields.add(field);
                    }
                }
            }
            recursiveSupportedFields(clazz.getSuperclass(), supportedFields);
        }
    }

    private void analyzeClassFields(String className, int action) {
        try {
            Field[] fields = Class.forName(className).getDeclaredFields();
            for (Field field : fields) {
                if (isPrivateAndNonPrimitive(field)) {
                    oneToAnyConditions(className, field, action);
                    manyToAnyConditions(className, field, action);
                }
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            throw new DatabaseGenerateException(DatabaseGenerateException.CLASS_NOT_FOUND + className);
        }
    }

    private boolean isPrivateAndNonPrimitive(Field field) {
        return Modifier.isPrivate(field.getModifiers()) && !field.getType().isPrimitive();
    }

    private void oneToAnyConditions(String className, Field field, int action) throws ClassNotFoundException {
        Class<?> fieldTypeClass = field.getType();
        if (LitePalAttr.getInstance().getClassNames().contains(fieldTypeClass.getName())) {
            Field[] reverseFields = Class.forName(fieldTypeClass.getName()).getDeclaredFields();
            boolean reverseAssociations = false;
            for (Field reverseField : reverseFields) {
                if (!Modifier.isStatic(reverseField.getModifiers())) {
                    Class<?> reverseFieldTypeClass = reverseField.getType();
                    if (className.equals(reverseFieldTypeClass.getName())) {
                        if (action == 1) {
                            addIntoAssociationModelCollection(className, fieldTypeClass.getName(), fieldTypeClass.getName(), 1);
                        } else if (action == 2) {
                            addIntoAssociationInfoCollection(className, fieldTypeClass.getName(), fieldTypeClass.getName(), field, reverseField, 1);
                        }
                        reverseAssociations = true;
                    } else if (isCollection(reverseFieldTypeClass) && className.equals(getGenericTypeName(reverseField))) {
                        if (action == 1) {
                            addIntoAssociationModelCollection(className, fieldTypeClass.getName(), className, 2);
                        } else if (action == 2) {
                            addIntoAssociationInfoCollection(className, fieldTypeClass.getName(), className, field, reverseField, 2);
                        }
                        reverseAssociations = true;
                    }
                }
            }
            if (reverseAssociations) {
                return;
            }
            if (action == 1) {
                addIntoAssociationModelCollection(className, fieldTypeClass.getName(), fieldTypeClass.getName(), 1);
            } else if (action == 2) {
                addIntoAssociationInfoCollection(className, fieldTypeClass.getName(), fieldTypeClass.getName(), field, null, 1);
            }
        }
    }

    private void manyToAnyConditions(String className, Field field, int action) throws ClassNotFoundException {
        if (isCollection(field.getType())) {
            String genericTypeName = getGenericTypeName(field);
            if (LitePalAttr.getInstance().getClassNames().contains(genericTypeName)) {
                Field[] reverseFields = Class.forName(genericTypeName).getDeclaredFields();
                boolean reverseAssociations = false;
                for (Field reverseField : reverseFields) {
                    if (!Modifier.isStatic(reverseField.getModifiers())) {
                        Class<?> reverseFieldTypeClass = reverseField.getType();
                        if (className.equals(reverseFieldTypeClass.getName())) {
                            if (action == 1) {
                                addIntoAssociationModelCollection(className, genericTypeName, genericTypeName, 2);
                            } else if (action == 2) {
                                addIntoAssociationInfoCollection(className, genericTypeName, genericTypeName, field, reverseField, 2);
                            }
                            reverseAssociations = true;
                        } else if (isCollection(reverseFieldTypeClass) && className.equals(getGenericTypeName(reverseField))) {
                            if (action == 1) {
                                addIntoAssociationModelCollection(className, genericTypeName, null, 3);
                            } else if (action == 2) {
                                addIntoAssociationInfoCollection(className, genericTypeName, null, field, reverseField, 3);
                            }
                            reverseAssociations = true;
                        }
                    }
                }
                if (reverseAssociations) {
                    return;
                }
                if (action == 1) {
                    addIntoAssociationModelCollection(className, genericTypeName, genericTypeName, 2);
                } else if (action == 2) {
                    addIntoAssociationInfoCollection(className, genericTypeName, genericTypeName, field, null, 2);
                }
            }
        }
    }

    private void addIntoAssociationModelCollection(String className, String associatedClassName, String classHoldsForeignKey, int associationType) {
        AssociationsModel associationModel = new AssociationsModel();
        associationModel.setTableName(DBUtility.getTableNameByClassName(className));
        associationModel.setAssociatedTableName(DBUtility.getTableNameByClassName(associatedClassName));
        associationModel.setTableHoldsForeignKey(DBUtility.getTableNameByClassName(classHoldsForeignKey));
        associationModel.setAssociationType(associationType);
        this.mAssociationModels.add(associationModel);
    }

    private void addIntoAssociationInfoCollection(String selfClassName, String associatedClassName, String classHoldsForeignKey, Field associateOtherModelFromSelf, Field associateSelfFromOtherModel, int associationType) {
        AssociationsInfo associationInfo = new AssociationsInfo();
        associationInfo.setSelfClassName(selfClassName);
        associationInfo.setAssociatedClassName(associatedClassName);
        associationInfo.setClassHoldsForeignKey(classHoldsForeignKey);
        associationInfo.setAssociateOtherModelFromSelf(associateOtherModelFromSelf);
        associationInfo.setAssociateSelfFromOtherModel(associateSelfFromOtherModel);
        associationInfo.setAssociationType(associationType);
        this.mAssociationInfos.add(associationInfo);
    }

    private String getGenericTypeName(Field field) {
        Type genericType = field.getGenericType();
        if (genericType == null || !(genericType instanceof ParameterizedType)) {
            return null;
        }
        return ((Class) ((ParameterizedType) genericType).getActualTypeArguments()[0]).getName();
    }

    private ColumnModel convertFieldToColumnModel(Field field) {
        String columnType = null;
        String fieldType = field.getType().getName();
        for (OrmChange ormChange : this.typeChangeRules) {
            columnType = ormChange.object2Relation(fieldType);
            if (columnType != null) {
                break;
            }
        }
        boolean nullable = true;
        boolean unique = false;
        String defaultValue = "";
        Column annotation = (Column) field.getAnnotation(Column.class);
        if (annotation != null) {
            nullable = annotation.nullable();
            unique = annotation.unique();
            defaultValue = annotation.defaultValue();
        }
        ColumnModel columnModel = new ColumnModel();
        columnModel.setColumnName(field.getName());
        columnModel.setColumnType(columnType);
        columnModel.setIsNullable(nullable);
        columnModel.setIsUnique(unique);
        columnModel.setDefaultValue(defaultValue);
        return columnModel;
    }
}

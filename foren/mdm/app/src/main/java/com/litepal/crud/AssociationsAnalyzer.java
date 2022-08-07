package com.litepal.crud;

import com.litepal.crud.model.AssociationsInfo;
import com.litepal.exceptions.DataSupportException;
import com.litepal.util.DBUtility;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class AssociationsAnalyzer extends DataHandler {
    protected Collection<DataSupport> getReverseAssociatedModels(DataSupport associatedModel, AssociationsInfo associationInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (Collection) takeGetMethodValueByField(associatedModel, associationInfo.getAssociateSelfFromOtherModel());
    }

    protected void setReverseAssociatedModels(DataSupport associatedModel, AssociationsInfo associationInfo, Collection<DataSupport> associatedModelCollection) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        putSetMethodValueByField(associatedModel, associationInfo.getAssociateSelfFromOtherModel(), associatedModelCollection);
    }

    protected Collection<DataSupport> checkAssociatedModelCollection(Collection<DataSupport> associatedModelCollection, Field associatedField) {
        Collection<DataSupport> collection;
        if (isList(associatedField.getType())) {
            collection = new ArrayList<>();
        } else if (isSet(associatedField.getType())) {
            collection = new HashSet<>();
        } else {
            throw new DataSupportException(DataSupportException.WRONG_FIELD_TYPE_FOR_ASSOCIATIONS);
        }
        if (associatedModelCollection != null) {
            collection.addAll(associatedModelCollection);
        }
        return collection;
    }

    protected void buildBidirectionalAssociations(DataSupport baseObj, DataSupport associatedModel, AssociationsInfo associationInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        putSetMethodValueByField(associatedModel, associationInfo.getAssociateSelfFromOtherModel(), baseObj);
    }

    protected void dealsAssociationsOnTheSideWithoutFK(DataSupport baseObj, DataSupport associatedModel) {
        if (associatedModel == null) {
            return;
        }
        if (associatedModel.isSaved()) {
            baseObj.addAssociatedModelWithFK(associatedModel.getTableName(), associatedModel.getBaseObjId());
        } else if (baseObj.isSaved()) {
            associatedModel.addAssociatedModelWithoutFK(baseObj.getTableName(), baseObj.getBaseObjId());
        }
    }

    protected void mightClearFKValue(DataSupport baseObj, AssociationsInfo associationInfo) {
        baseObj.addFKNameToClearSelf(getForeignKeyName(associationInfo));
    }

    private String getForeignKeyName(AssociationsInfo associationInfo) {
        return getForeignKeyColumnName(DBUtility.getTableNameByClassName(associationInfo.getAssociatedClassName()));
    }
}

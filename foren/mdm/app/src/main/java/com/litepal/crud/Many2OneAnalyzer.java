package com.litepal.crud;

import com.litepal.crud.model.AssociationsInfo;
import com.litepal.util.DBUtility;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/* loaded from: classes2.dex */
class Many2OneAnalyzer extends AssociationsAnalyzer {
    /* JADX INFO: Access modifiers changed from: package-private */
    public void analyze(DataSupport baseObj, AssociationsInfo associationInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (baseObj.getClassName().equals(associationInfo.getClassHoldsForeignKey())) {
            analyzeManySide(baseObj, associationInfo);
        } else {
            analyzeOneSide(baseObj, associationInfo);
        }
    }

    private void analyzeManySide(DataSupport baseObj, AssociationsInfo associationInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        DataSupport associatedModel = getAssociatedModel(baseObj, associationInfo);
        if (associatedModel != null) {
            Collection<DataSupport> reverseAssociatedModels = checkAssociatedModelCollection(getReverseAssociatedModels(associatedModel, associationInfo), associationInfo.getAssociateSelfFromOtherModel());
            setReverseAssociatedModels(associatedModel, associationInfo, reverseAssociatedModels);
            dealAssociatedModelOnManySide(reverseAssociatedModels, baseObj, associatedModel);
            return;
        }
        mightClearFKValue(baseObj, associationInfo);
    }

    private void analyzeOneSide(DataSupport baseObj, AssociationsInfo associationInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Collection<DataSupport> associatedModels = getAssociatedModels(baseObj, associationInfo);
        if (associatedModels == null || associatedModels.isEmpty()) {
            baseObj.addAssociatedTableNameToClearFK(DBUtility.getTableNameByClassName(associationInfo.getAssociatedClassName()));
            return;
        }
        for (DataSupport associatedModel : associatedModels) {
            buildBidirectionalAssociations(baseObj, associatedModel, associationInfo);
            dealAssociatedModelOnOneSide(baseObj, associatedModel);
        }
    }

    private void dealAssociatedModelOnManySide(Collection<DataSupport> associatedModels, DataSupport baseObj, DataSupport associatedModel) {
        if (!associatedModels.contains(baseObj)) {
            associatedModels.add(baseObj);
        }
        if (associatedModel.isSaved()) {
            baseObj.addAssociatedModelWithoutFK(associatedModel.getTableName(), associatedModel.getBaseObjId());
        }
    }

    private void dealAssociatedModelOnOneSide(DataSupport baseObj, DataSupport associatedModel) {
        dealsAssociationsOnTheSideWithoutFK(baseObj, associatedModel);
    }
}

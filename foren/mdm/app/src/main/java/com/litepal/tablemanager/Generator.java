package com.litepal.tablemanager;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.litepal.LitePalBase;
import com.litepal.exceptions.DatabaseGenerateException;
import com.litepal.parser.LitePalAttr;
import com.litepal.tablemanager.model.AssociationsModel;
import com.litepal.tablemanager.model.TableModel;
import com.litepal.util.BaseUtility;
import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes2.dex */
public abstract class Generator extends LitePalBase {
    public static final String TAG = "Generator";
    private Collection<AssociationsModel> mAllRelationModels;
    private Collection<TableModel> mTableModels;

    protected abstract void addOrUpdateAssociation(SQLiteDatabase sQLiteDatabase, boolean z);

    protected abstract void createOrUpgradeTable(SQLiteDatabase sQLiteDatabase, boolean z);

    protected Collection<TableModel> getAllTableModels() {
        if (this.mTableModels == null) {
            this.mTableModels = new ArrayList();
        }
        if (!canUseCache()) {
            this.mTableModels.clear();
            for (String className : LitePalAttr.getInstance().getClassNames()) {
                this.mTableModels.add(getTableModel(className));
            }
        }
        return this.mTableModels;
    }

    protected Collection<AssociationsModel> getAllAssociations() {
        if (this.mAllRelationModels == null || this.mAllRelationModels.isEmpty()) {
            this.mAllRelationModels = getAssociations(LitePalAttr.getInstance().getClassNames());
        }
        return this.mAllRelationModels;
    }

    protected void execute(String[] sqls, SQLiteDatabase db) {
        String throwSQL = "";
        if (sqls != null) {
            try {
                for (String sql : sqls) {
                    throwSQL = sql;
                    db.execSQL(BaseUtility.changeCase(sql));
                }
            } catch (SQLException e) {
                throw new DatabaseGenerateException(DatabaseGenerateException.SQL_ERROR + throwSQL);
            }
        }
    }

    private static void addAssociation(SQLiteDatabase db, boolean force) {
        new Creator().addOrUpdateAssociation(db, force);
    }

    private static void updateAssociations(SQLiteDatabase db) {
        new Upgrader().addOrUpdateAssociation(db, false);
    }

    private static void upgradeTables(SQLiteDatabase db) {
        new Upgrader().createOrUpgradeTable(db, false);
    }

    private static void create(SQLiteDatabase db, boolean force) {
        new Creator().createOrUpgradeTable(db, force);
    }

    private static void drop(SQLiteDatabase db) {
        new Dropper().createOrUpgradeTable(db, false);
    }

    private boolean canUseCache() {
        return this.mTableModels != null && this.mTableModels.size() == LitePalAttr.getInstance().getClassNames().size();
    }

    public static void create(SQLiteDatabase db) {
        create(db, true);
        addAssociation(db, true);
    }

    public static void upgrade(SQLiteDatabase db) {
        drop(db);
        create(db, false);
        updateAssociations(db);
        upgradeTables(db);
        addAssociation(db, false);
    }
}

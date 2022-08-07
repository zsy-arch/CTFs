package com.litepal.parser;

import android.text.TextUtils;
import com.litepal.exceptions.InvalidAttributesException;
import com.litepal.util.Const;
import com.litepal.util.SharedUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public final class LitePalAttr {
    private static LitePalAttr litePalAttr;
    private String cases;
    private List<String> classNames;
    private String dbName;
    private String storage;
    private int version;

    private LitePalAttr() {
    }

    public static LitePalAttr getInstance() {
        if (litePalAttr == null) {
            synchronized (LitePalAttr.class) {
                if (litePalAttr == null) {
                    litePalAttr = new LitePalAttr();
                }
            }
        }
        return litePalAttr;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getDbName() {
        return this.dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getStorage() {
        return this.storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public List<String> getClassNames() {
        if (this.classNames == null) {
            this.classNames = new ArrayList();
            this.classNames.add("com.litepal.model.Table_Schema");
        } else if (this.classNames.isEmpty()) {
            this.classNames.add("com.litepal.model.Table_Schema");
        }
        return this.classNames;
    }

    public void addClassName(String className) {
        getClassNames().add(className);
    }

    public String getCases() {
        return this.cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public boolean checkSelfValid() {
        if (TextUtils.isEmpty(this.dbName)) {
            throw new InvalidAttributesException(InvalidAttributesException.DBNAME_IS_EMPTY_OR_NOT_DEFINED);
        }
        if (!this.dbName.endsWith(Const.LitePal.DB_NAME_SUFFIX)) {
            this.dbName += Const.LitePal.DB_NAME_SUFFIX;
        }
        if (this.version < 1) {
            throw new InvalidAttributesException(InvalidAttributesException.VERSION_OF_DATABASE_LESS_THAN_ONE);
        } else if (this.version < SharedUtil.getLastVersion()) {
            throw new InvalidAttributesException(InvalidAttributesException.VERSION_IS_EARLIER_THAN_CURRENT);
        } else {
            if (TextUtils.isEmpty(this.cases)) {
                this.cases = Const.LitePal.CASES_LOWER;
            } else if (!this.cases.equals(Const.LitePal.CASES_UPPER) && !this.cases.equals(Const.LitePal.CASES_LOWER) && !this.cases.equals(Const.LitePal.CASES_KEEP)) {
                throw new InvalidAttributesException(this.cases + InvalidAttributesException.CASES_VALUE_IS_INVALID);
            }
            return true;
        }
    }
}
